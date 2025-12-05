package org.firstinspires.ftc.teamcode.subsystems.Flicker;

import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.command.WaitCommand;

import org.firstinspires.ftc.teamcode.subsystems.Hood.HoodConstants;

import java.util.Random;
import java.util.function.DoubleSupplier;

public class Flicker extends SubsystemBase {
    private Servo servo;


    public Flicker (HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, FlickerConstants.Ids.flickerServoId);

        servo.setDirection(FlickerConstants.MotorConfig.motorDirection);
        setAngle(0.0);
    }

    public void setAngle(double angle) {
        double clampedAngle = MathUtils.clamp(angle, FlickerConstants.Physics.minLimit, FlickerConstants.Physics.maxLimit);
        servo.setPosition(clampedAngle);
    }

    public void raise() {
        servo.setPosition(FlickerConstants.Physics.maxLimit);
    }

    public void lower() {
        servo.setPosition(FlickerConstants.Physics.minLimit);
    }

    public Command shootSequence() {
        return new SequentialCommandGroup(
                new InstantCommand(this::raise),
                new WaitCommand(300),
                new InstantCommand(this::lower)
        );
    }

    public Command setAngleCMD(double angle) {
        return new InstantCommand(() -> setAngle(angle));
    }

    public Double getPosition() {
        return servo.getPosition();
    }
}


