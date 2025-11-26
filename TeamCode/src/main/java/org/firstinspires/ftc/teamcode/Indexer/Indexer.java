package org.firstinspires.ftc.teamcode.Indexer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class Indexer extends SubsystemBase {

    private ServoEx startIndexerServo;
    private ServoEx leftIndexerServo;
    private ServoEx rightIndexerServo;
    private Motor indexerMotor;

    public Indexer (HardwareMap hardwareMap) {
        startIndexerServo = new ServoEx (hardwareMap, "indexerServo");
        startIndexerServo.getServo().scaleRange(0, 360);

        leftIndexerServo = new ServoEx(hardwareMap, "leftIndexerServo");
        leftIndexerServo.getServo().scaleRange(0, 360);

        rightIndexerServo = new ServoEx(hardwareMap, "rightIndexerServo");
        rightIndexerServo.getServo().scaleRange(0, 360);

        indexerMotor = new Motor(hardwareMap, "indexerMotor");
    }

    public void setStartIndexerServo (double angle) {
        startIndexerServo.set(angle);
    }

    public void setRightIndexerServo (double angle) {
        rightIndexerServo.set(angle);
    }

    public void setLeftIndexerServo(double angle) {
        leftIndexerServo.set(-angle);
    }

    public void setIndexerMotor (double power) {
        indexerMotor.set(power);
    }
    private double incrementStartIndexerServo;
    private double incrementRightInderxerServo;
    private double incrementLeftIndexerServo;
    public void incrementStartIndexer () {
        setStartIndexerServo(+10);
    }
    public void incrementRightIndexer () {
        setRightIndexerServo(+10);
    }
    public void incrementLeftIndexer() {
        setLeftIndexerServo(+10);
    }

    public void decrementStartIndexer() {
        setStartIndexerServo(-10);
    }
    public void decrementRightIndexer() {
        setRightIndexerServo(-10);
    }
    public void decrementLeftIndexer() {
        setLeftIndexerServo(-10);
    }
}





