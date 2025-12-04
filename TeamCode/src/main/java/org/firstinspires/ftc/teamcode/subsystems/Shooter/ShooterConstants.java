package org.firstinspires.ftc.teamcode.subsystems.Shooter;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class ShooterConstants {
    public static class Ids {
        public static final String shooterMotorId = "shooterMotor";

    }

    @Configurable
    public static class PIDF {
        public static final PIDFCoefficients pidfCoefficients = new PIDFCoefficients(2.0, 0.0, 0.0, 15.0);
    }

    public static class MotorConfig {
        public static final DcMotor.ZeroPowerBehavior zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT;
        public static final DcMotorSimple.Direction motorDirection = DcMotorSimple.Direction.FORWARD;

    }
}
