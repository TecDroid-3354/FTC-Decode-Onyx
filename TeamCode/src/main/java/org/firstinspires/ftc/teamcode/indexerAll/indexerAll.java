package org.firstinspires.ftc.teamcode.indexerAll;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.indexer.indexer;

public class indexerAll{


    private GamepadEx gamepad1;
    private indexer indexer;

    public void initialize() {
        indexer = new indexer();
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
