package org.firstinspires.ftc.teamcode.IndexerAll;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Indexer.Indexer;
import org.firstinspires.ftc.teamcode.Indexer.Indexer;

public class IndexerAll{


    private GamepadEx gamepad1;
    private Indexer indexer;

    public void initialize() {
        indexer = new Indexer();
        gamepad1 = new GamepadEx (new Gamepad());
    }

    public void runIndexerAll(){
        initialize();
        //wait for the game to start (driver presses PLAY)


        //run until the end of the match(driver presses STOP)


        if (gamepad1.getButton(GamepadKeys.Button.A)) {
            indexer.setLeftIndexerServo (+5);
            indexer.setRightIndexerServo(+5);
            indexer.setStartIndexerServo(+5);
            indexer.setIndexerMotor(1);
        }else {
            indexer.setLeftIndexerServo (0);
            indexer.setRightIndexerServo(0);
            indexer.setStartIndexerServo(0);
            indexer.setIndexerMotor(0);
        }
    }
}
