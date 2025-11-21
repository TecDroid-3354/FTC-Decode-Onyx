package org.firstinspires.ftc.teamcode.Shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Shooter extends SubsystemBase {
    private Motor powerShooter;
    public Shooter (HardwareMap hardwareMap) {
        powerShooter = new Motor(hardwareMap, hardwareMap.get("Power Shooter 1").getDeviceName());
    }
    public void setPowerShooter(double power) {
        powerShooter.set(power = 1);
    }
    public void stopPowerShooter(double power) {

        powerShooter.set(power = 0);
    }

}