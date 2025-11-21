package org.firstinspires.ftc.teamcode.centering;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class centering{

    private ServoEx centeringRight;
    private ServoEx centeringLeft;

    public centering(HardwareMap hardwareMap) {
        centeringRight = new ServoEx(hardwareMap, "centeringRight");
        centeringRight.getServo().scaleRange(0.3, 1);
        centeringLeft = new ServoEx(hardwareMap, "centeringLeft");
        centeringLeft.getServo().scaleRange(-0.3, -1);
    }

public void setCentering(double angle){
        centeringLeft.set(-angle);
        centeringRight.set(angle);
}



}
