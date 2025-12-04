package org.firstinspires.ftc.teamcode.subsystems.Indexer;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;

import org.firstinspires.ftc.teamcode.subsystems.Indexer.IndexerConstants.Ids;

public class Indexer extends SubsystemBase {

    private CRServoEx upServo;
    private CRServoEx downServo;
    private DcMotorEx motor;

    public Indexer (HardwareMap hardwareMap) {
        upServo = hardwareMap.get(CRServoEx.class, Ids.indexerUpServoId);
        upServo.setInverted(IndexerConstants.MotorConfig.upServoDirection == DcMotorSimple.Direction.REVERSE);

        downServo = hardwareMap.get(CRServoEx.class, Ids.indexerDownServoId);
        downServo.setInverted(IndexerConstants.MotorConfig.downServoDirection == DcMotorSimple.Direction.REVERSE);

        motor = hardwareMap.get(DcMotorEx.class, Ids.indexerMotorId);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(IndexerConstants.MotorConfig.motorDirection);
        motor.setZeroPowerBehavior(IndexerConstants.MotorConfig.zeroPowerBehavior);
    }

    public void enable() {
        upServo.set(1.0);
        downServo.set(1.0);
        motor.setPower(1.0);
    }

    public void disable() {
        upServo.set(0.0);
        downServo.set(0.0);
        motor.setPower(0.0);
    }
}





