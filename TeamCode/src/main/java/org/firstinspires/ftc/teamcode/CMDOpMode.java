package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.commands.AlignToAprilTagCMD;
import org.firstinspires.ftc.teamcode.commands.JoystickCmd;
import org.firstinspires.ftc.teamcode.shooter.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.Flicker.Flicker;
import org.firstinspires.ftc.teamcode.subsystems.Hood.Hood;
import org.firstinspires.ftc.teamcode.subsystems.Indexer.Indexer;
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Mecanum;
import org.firstinspires.ftc.teamcode.vision.Limelight;


// Personally, I chose to run my code using a command-based Op Mode since it works better for me
// In a regular LinearOpMode, processes are executed in a sequential workflow
// In an OpMode, on the other hand, code is executed through loops

/* To connect to the robot and deploy the code wirelessly, type the following in the terminal:
 *    adb connect 192.168.43.1:5555 (connects to Control Hub)
 *    adb connect 192.168.43.1:8080 (connects to FTC Dashboard)
 * To visit the FTC dashboard online (while connected to the Control Hub's internet)
 *    http://192.168.43.1:8080/?page=connection.html&pop=true
 */
@TeleOp(name = "CMD", group = "Op Mode")
public class CMDOpMode extends CommandOpMode {
    private Mecanum mecanum;
    private Intake intake;
    private Indexer indexer;
    private Flicker flicker;
    private Shooter shooter;
    private Hood hood;
    private IMU imu;
    private Limelight limelight;
    private GamepadEx controller;
    private int[] limelightIdFilter = new int[]{20, 24};


    private AlignToAprilTagCMD alignToAprilTagCMD;

    @Override
    public void initialize() {
        controller = new GamepadEx(gamepad1);
        imu = hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot revHubOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(revHubOrientation));

        limelight = new Limelight(
                hardwareMap,
                telemetry,
                () -> getHeading(AngleUnit.DEGREES));

        limelight.start();

        // Initializing the mecanum & its default command
        mecanum = new Mecanum(hardwareMap, telemetry);
        mecanum.setDefaultCommand(new JoystickCmd(
                () ->  controller.getLeftX(),
                () -> controller.getLeftY(),
                () -> controller.getRightX(),
                () -> getHeading(AngleUnit.DEGREES),
                mecanum
        ));

        intake = new Intake(hardwareMap);
        indexer = new Indexer(hardwareMap);
        flicker = new Flicker(hardwareMap);
        shooter = new Shooter(hardwareMap, telemetry);
        hood = new Hood(hardwareMap, () -> limelight.getClassifierDistanceCm(limelightIdFilter));

        alignToAprilTagCMD = new AlignToAprilTagCMD(mecanum, () -> limelight.getClassifierTx(limelightIdFilter));
        configureButtonBindings();
    }
    public void configureButtonBindings() {
        new GamepadButton(controller, GamepadKeys.Button.START)
                .whenPressed(new InstantCommand(
                        () -> imu.resetYaw()
                ));

        new GamepadButton(controller, GamepadKeys.Button.LEFT_BUMPER)
                .whenPressed(new InstantCommand(
                        () -> { intake.enable(); indexer.enable(); }
                ))
                .whenReleased(new InstantCommand(
                        () -> { intake.disable(); indexer.disable(); }
                ));

        new GamepadButton(controller, GamepadKeys.Button.Y)
                .whenPressed(flicker.shootSequence());

        // Shoot sequence
        new Trigger(() -> controller.gamepad.right_trigger > 0.1)
                .whileActiveContinuous(
                        new SequentialCommandGroup(
                                //hood.adjustAngleAccordingDistance(),
                                shooter.shootCMD(),
                                indexer.enableCMD(),
                                new WaitCommand(1400),
                                flicker.shootSequence()
                        )
                )
                .whenInactive(
                        new SequentialCommandGroup(
                                new InstantCommand(() -> shooter.stop()),
                                new InstantCommand(() -> indexer.disable())
                        )
                );

        new Trigger(() -> controller.gamepad.right_trigger > 0.1)
                .whileActiveContinuous(alignToAprilTagCMD);

    }

    public Double getHeading(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }

    public void periodic() {
        if (controller.gamepad.dpad_up) {
            hood.setAngle(hood.getPosition() + 0.01);
        } else if (controller.gamepad.dpad_down) {
            hood.setAngle(hood.getPosition() - 0.01);
        }

        telemetry.addData("robot heading", getHeading(AngleUnit.DEGREES));
        telemetry.addData("hood position", hood.getPosition());
    }

    // Main code body
    @Override
    public void runOpMode() {
        // Code executed at the very beginning, right after hitting the INIT Button
        initialize();

        selectAlliance();

        // Pauses OpMode until the START button is pressed on the Driver Hub
        waitForStart();

        // Run the scheduler
        while (!isStopRequested() && opModeIsActive()) {

            // Command for actually running the scheduler
            CommandScheduler.getInstance().run();
            periodic();

            telemetry.update();
        }

        // Cancels all previous commands
        reset();
    }

    public void selectAlliance() {
        // select side
        String[] options = {"BlueAlliance", "RedAlliance", "Test"};
        int index = 0;

        while (!isStarted() && !isStopRequested()) {

            if (gamepad1.dpad_down) {
                index = (index - 1 + options.length) % options.length;
            }
            if (gamepad1.dpad_up) {
                index = (index + 1) % options.length;
            }

            telemetry.addLine("Select the Alliance:");
            for (int i = 0; i < options.length; i++) {
                if (i == index) {
                    telemetry.addLine(" ➤ " + options[i]);
                } else {
                    telemetry.addLine("   " + options[i]);
                }
            }
            telemetry.update();

            sleep(200);
        }

        switch (options[index]) {
            case "BlueAlliance":
                limelightIdFilter = new int[]{20};
                break;

            case "RedAlliance":
                limelightIdFilter = new int[]{24};
                break;

            default:
                limelightIdFilter = new int[]{20, 24};
                break;
        }

    }
}