package org.firstinspires.ftc.teamcode.subsystems.Intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;

import org.firstinspires.ftc.teamcode.subsystems.Indexer.IndexerConstants;
import org.firstinspires.ftc.teamcode.subsystems.Intake.IntakeConstants.Ids;

public class Intake extends SubsystemBase {
    private DcMotorEx motor;
    private CRServo centeringLeftServo;
    private CRServo centeringRightServo;
    public Intake (HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, Ids.intakeMotorId);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setDirection(IntakeConstants.MotorConfig.motorDirection);
        motor.setZeroPowerBehavior(IntakeConstants.MotorConfig.zeroPowerBehavior);

        centeringLeftServo = hardwareMap.get(CRServo.class, Ids.centeringLeftServoID);
        centeringLeftServo.setDirection(IntakeConstants.MotorConfig.centeringLeftServoDirection);

        centeringRightServo = hardwareMap.get(CRServo.class, Ids.centeringRightServoID);
        centeringRightServo.setDirection(IntakeConstants.MotorConfig.centeringRightServoDirection);

    }

    public void enable() {
        centeringRightServo.setPower(1.0);
        centeringLeftServo.setPower(1.0);
        motor.setPower(1.0);
    }

    public void disable() {
        centeringRightServo.setPower(0.0);
        centeringLeftServo.setPower(0.0);
        motor.setPower(0.0);
    }


}
