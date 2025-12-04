package org.firstinspires.ftc.teamcode.subsystems.Indexer;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.subsystems.Indexer.IndexerConstants.Ids;

public class Indexer extends SubsystemBase {

    private CRServo upServo;
    private CRServo downServo;
    private DcMotorEx motor;

    public Indexer (HardwareMap hardwareMap) {
        upServo = hardwareMap.get(CRServo.class, Ids.indexerUpServoId);
        upServo.setDirection(IndexerConstants.MotorConfig.upServoDirection);

        downServo = hardwareMap.get(CRServo.class, Ids.indexerDownServoId);
        downServo.setDirection(IndexerConstants.MotorConfig.downServoDirection);

        motor = hardwareMap.get(DcMotorEx.class, Ids.indexerMotorId);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(IndexerConstants.MotorConfig.motorDirection);
        motor.setZeroPowerBehavior(IndexerConstants.MotorConfig.zeroPowerBehavior);
    }

    public void enable() {
        upServo.setPower(1.0);
        downServo.setPower(1.0);
        motor.setPower(1.0);
    }

    public void disable() {
        upServo.setPower(0.0);
        downServo.setPower(0.0);
        motor.setPower(0.0);
    }
}





