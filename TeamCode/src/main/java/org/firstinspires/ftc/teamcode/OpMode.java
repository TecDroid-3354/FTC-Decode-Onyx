package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.driveTrain.driveTrain;
import org.firstinspires.ftc.teamcode.intake.intake;
import org.firstinspires.ftc.teamcode.shooter.shooter;


@TeleOp(name ="shooter",group ="Tec droid")
public class OpMode extends CommandOpMode {
    private GamepadEx controller;
    private shooter shooter;
    private intake intake;
    private driveTrain driveTrain;



     public void initialize () {
        shooter = new shooter(hardwareMap);
        intake = new intake(hardwareMap);
        controller = new GamepadEx(gamepad1);
        driveTrain = new driveTrain(hardwareMap);
    }
@Override
    public void runOpMode() {
        initialize();
        //wait for the game to start (driver presses PLAY)
        waitForStart();

        //run until the end of the match(driver presses STOP)
        while (opModeIsActive()) {

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

            if (gamepad1.left_stick_y>0.1) {
                driveTrain.setFrontRightMotor(gamepad1.left_stick_y);
                driveTrain.setFrontLeftMotor(gamepad1.left_stick_y);
                driveTrain.setBackRightMotor(gamepad1.left_stick_y);
                driveTrain.setBackLeftMotor(gamepad1.left_stick_y);
            }else if (gamepad1.left_stick_y<-0.1) {
                driveTrain.setFrontRightMotor(gamepad1.left_stick_y);
                driveTrain.setFrontLeftMotor(gamepad1.left_stick_y);
                driveTrain.setBackRightMotor(gamepad1.left_stick_y);
                driveTrain.setBackLeftMotor(gamepad1.left_stick_y);
            }if (gamepad1.left_stick_y == 0){
                driveTrain.setFrontRightMotor(0);
                driveTrain.setFrontLeftMotor(0);
                driveTrain.setBackRightMotor(0);
                driveTrain.setBackLeftMotor(0);
            }

            if (gamepad1.left_stick_x>0.1) {
                driveTrain.setFrontRightMotor(-gamepad1.left_stick_y);
                driveTrain.setFrontLeftMotor(gamepad1.left_stick_y);
                driveTrain.setBackRightMotor(gamepad1.left_stick_y);
                driveTrain.setBackLeftMotor(-gamepad1.left_stick_y);
            }else if (gamepad1.left_stick_x<0.1){
                driveTrain.setFrontRightMotor(gamepad1.left_stick_y);
                driveTrain.setFrontLeftMotor(-gamepad1.left_stick_y);
                driveTrain.setBackRightMotor(-gamepad1.left_stick_y);
                driveTrain.setBackLeftMotor(gamepad1.left_stick_y);
            }if (gamepad1.left_stick_x == 0){
                driveTrain.setFrontRightMotor(0);
                driveTrain.setFrontLeftMotor(0);
                driveTrain.setBackRightMotor(0);
                driveTrain.setBackLeftMotor(0);
            }



        }

    }
}