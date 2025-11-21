package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.centering.centering;
import org.firstinspires.ftc.teamcode.driveTrain.driveTrain;
import org.firstinspires.ftc.teamcode.hood.hood;
import org.firstinspires.ftc.teamcode.indexerAll.indexerAll;
import org.firstinspires.ftc.teamcode.intake.intake;
import org.firstinspires.ftc.teamcode.shooter.shooter;


@TeleOp(name ="shooter",group ="Tec droid")
public class OpMode extends CommandOpMode {
    private GamepadEx controller;
    private shooter shooter;
    private intake intake;
    private driveTrain driveTrain;
    private centering centering;
    private hood hood;
    private indexerAll indexerAll;



     public void initialize () {
        shooter = new shooter(hardwareMap);
        intake = new intake(hardwareMap);
        controller = new GamepadEx(gamepad1);
        driveTrain = new driveTrain(hardwareMap);
        centering = new centering (hardwareMap);
        hood = new hood (hardwareMap);
        indexerAll = new indexerAll ();
    }
@Override
    public void runOpMode() {
        initialize();
        //wait for the game to start (driver presses PLAY)
        waitForStart();

        //run until the end of the match(driver presses STOP)
        while (opModeIsActive()) {
            driveTrain.drive(
                    (double)gamepad1.left_stick_x,
                    (double)gamepad1.left_stick_y,
                    (double)gamepad1.right_stick_x);

            if (gamepad1.right_trigger > 0.1){
                intake.setPowerIntake(1);
            }else {
                intake.stopPowerIntake(0);
            }

            if (gamepad1.left_trigger > 0.1){
                shooter.setPowerShooter(gamepad1.left_trigger);
            }else {
                shooter.stopPowerShooter(0);
            }

            if (gamepad1.rightBumperWasPressed()){
                centering.setCentering(+0.02);
            }else if (gamepad1.rightBumperWasReleased()){
                centering.setCentering(-0.02);
            }

            

            if (gamepad1.leftBumperWasPressed()) {
                hood.setAngle(+5);
            }else if (gamepad1.leftBumperWasReleased()) {
                hood.setAngle(-5);
            }

            if (gamepad1.aWasPressed()) {
                indexerAll.runIndexerAll();
            }else if (gamepad1.aWasReleased()) {
                indexerAll.runIndexerAll();
            }
        }
    }
}