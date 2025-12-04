package org.firstinspires.ftc.teamcode.subsystems.Indexer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class IndexerConstants {
    public static class Ids {
       public static final String indexerUpServoId = "indexerUpServo";
        public static final String indexerDownServoId = "indexerDownServo";

        public static final String indexerMotorId = "indexerMotor";

    }

    public static class MotorConfig {
        public static final DcMotor.ZeroPowerBehavior zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT;
        public static final DcMotorSimple.Direction motorDirection = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction upServoDirection = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction downServoDirection = DcMotorSimple.Direction.FORWARD;

    }
}
