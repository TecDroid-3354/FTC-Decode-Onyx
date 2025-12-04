package org.firstinspires.ftc.teamcode.subsystems.Hood;

import androidx.core.math.MathUtils;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.subsystems.Hood.HoodConstants.*;
public class Hood extends SubsystemBase {
    private Servo servo;


    public Hood (HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, Ids.hoodServoId);
        servo.setDirection(Servo.Direction.REVERSE);
        setAngle(Physics.minLimit);
    }

    public void setAngle (double angle){
        double clampedAngle = MathUtils.clamp(angle, Physics.minLimit, Physics.maxLimit);
        servo.setPosition(clampedAngle);
    }

    public Command setAngleCMD (double angle) {
        return new InstantCommand(() -> setAngle(angle));
    }

    public Double getPosition() {
        return servo.getPosition();
    }
}
