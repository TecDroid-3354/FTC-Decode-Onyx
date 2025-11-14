package org.firstinspires.ftc.teamcode.intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class intake extends SubsystemBase {
    private Motor powerIntake ;
    public intake (HardwareMap hardwareMap) {
        powerIntake = new Motor(hardwareMap, hardwareMap.get("Power Intake 1").getDeviceName());
    }

    public void setPowerIntake(double power) {
        powerIntake.set(power);
    }

    public void stopPowerIntake(double power) {
        powerIntake.set(power = 1);
    }

}
