package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Hood.Hood;
import org.firstinspires.ftc.teamcode.Indexer.Indexer;
import org.firstinspires.ftc.teamcode.IndexerAll.IndexerAll;
import org.firstinspires.ftc.teamcode.Centering.Centering;
import org.firstinspires.ftc.teamcode.Intake.Intake;
import org.firstinspires.ftc.teamcode.Shooter.Shooter;


@TeleOp(name ="OpMode",group ="Tec droid")
public class OpMode extends CommandOpMode {
    private GamepadEx controller;
    private Shooter shooter;
    private Intake intake;
    private DriveTrain driveTrain;
    private IndexerAll indexerAll;


    public void initialize() {
        shooter = new Shooter(hardwareMap);
        intake = new Intake(hardwareMap);
        controller = new GamepadEx(gamepad1);
        driveTrain = new DriveTrain(hardwareMap);
        indexerAll = new IndexerAll();
    }

    @Override
    public void runOpMode() {
        initialize();
        //wait for the game to start (driver presses PLAY)
        waitForStart();

        //run until the end of the match(driver presses STOP)
        while (opModeIsActive()) {
            driveTrain.drive(
                    (double) gamepad1.left_stick_x,
                    (double) gamepad1.left_stick_y,
                    (double) gamepad1.right_stick_x);

            if (gamepad1.right_trigger > 0.1) {
                intake.setPowerIntake(1);
            } else {
                intake.stopPowerIntake(0);
            }

            if (gamepad1.left_trigger > 0.1) {
                shooter.setPowerShooter(gamepad1.left_trigger);
            } else {
                shooter.stopPowerShooter(0);
            }

            if (gamepad1.a) {
                indexerAll.centeringPlus();
                indexerAll.hoodPlus();
                indexerAll.startIndexerPlus();
                indexerAll.rightIndexerPlus();
                indexerAll.leftIndexerPlus();
            }
            if (gamepad1.b) {
                indexerAll.centeringMinus();
                indexerAll.hoodMinus();
                indexerAll.startIndexerMinus();
                indexerAll.rightIndexerMinus();
                indexerAll.leftIndexerMinus();
            }
        }
    }
}