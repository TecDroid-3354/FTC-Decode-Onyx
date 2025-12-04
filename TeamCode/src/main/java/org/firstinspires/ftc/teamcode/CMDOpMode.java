package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.commands.JoystickCmd;
import org.firstinspires.ftc.teamcode.subsystems.Intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Mecanum;


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
    private IMU imu;
    private GamepadEx controller;

    @Override
    public void initialize() {
        controller = new GamepadEx(gamepad1);
        imu = hardwareMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot revHubOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
        );

        imu.initialize(new IMU.Parameters(revHubOrientation));

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

        configureButtonBindings();
    }
    public void configureButtonBindings() {
        new GamepadButton(controller, GamepadKeys.Button.START)
                .whenPressed(new InstantCommand(
                        () -> imu.resetYaw()
                ));
    }

    public void periodic() {
        telemetry.addData("robot heading", getHeading(AngleUnit.DEGREES));

        if (controller.gamepad.a) {
            intake.enable();
        } else {
            intake.disable();
        }
    }

    // Main code body
    @Override
    public void runOpMode() {
        // Code executed at the very beginning, right after hitting the INIT Button
        initialize();

        // Pauses OpMode until the START button is pressed on the Driver Hub
        waitForStart();

        //limelight.getMotifPattern()

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

    public Double getHeading(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }
}