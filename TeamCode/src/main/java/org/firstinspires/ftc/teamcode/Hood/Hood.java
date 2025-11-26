package org.firstinspires.ftc.teamcode.Hood;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Hood extends SubsystemBase {
    private ServoEx servoHood;


    public Hood (HardwareMap hardwareMap) {
        servoHood = new ServoEx(hardwareMap, "servoHood");
        servoHood.getServo().scaleRange(0, 360);
    }

    public void setAngle (double angle){
        servoHood.getServo().setPosition(-angle);
    }
    private double incrementServoHood;

    public void setIncrementServoHood() {
        setAngle(+10);
    }
    public void decrementServoHood() {
        setAngle(-10);
    }
}
