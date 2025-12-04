package org.firstinspires.ftc.teamcode.subsystems.Hood;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Hood extends SubsystemBase {
    private ServoEx servo;


    public Hood (HardwareMap hardwareMap) {
        servo = hardwareMap.get(ServoEx.class, HoodConstants.Ids.hoodServoId);
    }

    public void setAngle (double angle){
        servo.set(angle);
    }
}
