package org.firstinspires.ftc.teamcode.subsystems.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class IntakeConstants {
    public static class Ids {
        public static final String intakeMotorId = "intakeMotor";
        public static final String centeringLeftServoID = "centeringLeftServo";
        public static final String centeringRightServoID = "centeringRightServo";

    }

    public static class MotorConfig {
        public static final DcMotor.ZeroPowerBehavior zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT;
        public static final DcMotorSimple.Direction motorDirection = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction centeringLeftServoDirection = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction centeringRightServoDirection = DcMotorSimple.Direction.FORWARD;

    }
}
