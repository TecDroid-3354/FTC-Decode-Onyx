package org.firstinspires.ftc.teamcode.IndexerAll;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Centering.Centering;
import org.firstinspires.ftc.teamcode.Hood.Hood;
import org.firstinspires.ftc.teamcode.Indexer.Indexer;
import org.firstinspires.ftc.teamcode.Indexer.Indexer;

public class IndexerAll{


    private Indexer indexer;
    private Hood hood;
    private Centering centering;

    public void initialize(HardwareMap hardwareMap) {
        indexer = new Indexer(hardwareMap);
        hood = new Hood (hardwareMap);
        centering = new Centering (hardwareMap);
    }

    public void hoodPlus() {
        hood.setIncrementServoHood();
    }
    public void hoodMinus() {
        hood.decrementServoHood();
    }
    public void centeringPlus() {
        centering.incrementCentering();
    }
    public void centeringMinus() {
        centering.decrementCentering();
    }
    public void startIndexerPlus(){
        indexer.incrementStartIndexer();
    }
    public void rightIndexerPlus(){
        indexer.incrementRightIndexer();
    }
    public void leftIndexerPlus(){
        indexer.incrementLeftIndexer();
    }
    public void startIndexerMinus(){
        indexer.decrementStartIndexer();
    }
    public void rightIndexerMinus(){
        indexer.decrementRightIndexer();
    }
    public void leftIndexerMinus(){
        indexer.decrementLeftIndexer();
    }
}