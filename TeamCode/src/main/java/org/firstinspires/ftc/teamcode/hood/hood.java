package org.firstinspires.ftc.teamcode.hood;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class hood extends SubsystemBase {
    private ServoEx servoHood;


    public hood (HardwareMap hardwareMap) {
        servoHood = new ServoEx(hardwareMap, "servoHood");
        servoHood.getServo().scaleRange(0.3, 1.0);
    }

    public void setAngle (double angle){
        servoHood.getServo().setPosition(angle);
    }




}
