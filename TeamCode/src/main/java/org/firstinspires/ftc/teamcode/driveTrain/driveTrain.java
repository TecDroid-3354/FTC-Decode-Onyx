package org.firstinspires.ftc.teamcode.driveTrain;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class driveTrain {

    private Motor frontRightMotor;
    private Motor frontLeftMotor;
    private Motor backRightMotor;
    private Motor backLeftMotor;

   public driveTrain(HardwareMap hardwareMap) {
       frontRightMotor = new Motor(hardwareMap, "frontRightMotor");
       frontLeftMotor = new Motor(hardwareMap, "frontRightMotor");
       backRightMotor = new Motor(hardwareMap, "backRightMotor");
       backLeftMotor = new Motor(hardwareMap, "backLeftMotor");
   }

    public void setFrontRightMotor(double power) {
        frontRightMotor.set(power);
    }
    public void setFrontLeftMotor(double power) {
       frontLeftMotor.set(power);
    }
    public void setBackRightMotor(double power) {
       backRightMotor.set(power);
    }
    public void setBackLeftMotor(double power) {
       backLeftMotor.set(power);
    }
}
