package org.firstinspires.ftc.teamcode.DriveTrain;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveTrain extends SubsystemBase {

    private Motor frontRightMotor;
    private Motor frontLeftMotor;
    private Motor backRightMotor;
    private Motor backLeftMotor;
    private MecanumDrive mecanum;
    private IMU imu;
    private RevHubOrientationOnRobot revHubOrientation;

   public DriveTrain(HardwareMap hardwareMap) {
       imu = hardwareMap.get(IMU.class, "imu");
       revHubOrientation = new RevHubOrientationOnRobot(
               RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
               RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
       );
       imu.initialize(new IMU.Parameters(revHubOrientation));

       imu.resetYaw();

       frontRightMotor = new Motor(hardwareMap, "frontRightMotor");
       frontLeftMotor = new Motor(hardwareMap, "frontRightMotor");
       backRightMotor = new Motor(hardwareMap, "backRightMotor");
       backLeftMotor = new Motor(hardwareMap, "backLeftMotor");

       mecanum = new MecanumDrive(
               frontLeftMotor,
               frontRightMotor,
               backLeftMotor,
               backRightMotor);
   }

   public void drive(Double x, Double y, Double z){
       double yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
       mecanum.driveFieldCentric(x, y, z, yaw);
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
