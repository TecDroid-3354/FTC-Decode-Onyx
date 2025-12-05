package org.firstinspires.ftc.teamcode.subsystems.Hood;

import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.subsystems.Hood.HoodConstants.*;
import org.firstinspires.ftc.teamcode.utils.Distance;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class Hood extends SubsystemBase {
    private Servo servo;
    private DoubleSupplier aprilTagDistance;

    public Hood(HardwareMap hardwareMap, DoubleSupplier aprilTagDistance) {
        servo = hardwareMap.get(Servo.class, Ids.hoodServoId);
        this.aprilTagDistance = aprilTagDistance;

        servo.setDirection(Servo.Direction.REVERSE);
        setAngle(Physics.minLimit);
    }

    public void setAngle(double angle) {
        double clampedAngle = MathUtils.clamp(angle, Physics.minLimit, Physics.maxLimit);
        servo.setPosition(clampedAngle);
    }

    public Command setAngleCMD(double angle) {
        return new InstantCommand(() -> setAngle(angle));
    }

    public Command adjustAngleAccordingDistance() {
        double angle = (10.0 * aprilTagDistance.getAsDouble()) + 1.0;
        return setAngleCMD(angle);
    }

    public Double getPosition() {
        return servo.getPosition();
    }
}
