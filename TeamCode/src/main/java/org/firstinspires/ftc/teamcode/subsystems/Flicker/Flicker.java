package org.firstinspires.ftc.teamcode.subsystems.Flicker;

import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.subsystems.Hood.HoodConstants;

import java.util.Random;
import java.util.function.DoubleSupplier;

public class Flicker extends SubsystemBase {
    private Servo servo;


    public Flicker (HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "flickerServo");

        servo.setDirection(Servo.Direction.REVERSE);
        setAngle(0.0);
    }

    public void setAngle(double angle) {
        double clampedAngle = MathUtils.clamp(angle, 0.0, 1.0);
        servo.setPosition(clampedAngle);
    }

    public Command setAngleCMD(double angle) {
        return new InstantCommand(() -> setAngle(angle));
    }

    public Double getPosition() {
        return servo.getPosition();
    }
}


