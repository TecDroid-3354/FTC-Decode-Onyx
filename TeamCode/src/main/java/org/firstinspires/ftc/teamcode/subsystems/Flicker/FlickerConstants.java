package org.firstinspires.ftc.teamcode.subsystems.Flicker;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class FlickerConstants {
    public static class Ids {
        public static final String flickerServoId = "flickerServo";

    }

    public static class MotorConfig {
        public static final Servo.Direction motorDirection = Servo.Direction.REVERSE;


    }

    public static class Physics {
        public static final Double maxLimit = 0.25;
        public static final Double minLimit = 0.0;


    }
}
