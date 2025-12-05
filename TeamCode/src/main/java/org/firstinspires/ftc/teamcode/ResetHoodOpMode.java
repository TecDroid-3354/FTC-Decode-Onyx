package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.subsystems.Hood.Hood;
import org.firstinspires.ftc.teamcode.subsystems.Hood.HoodConstants;
import org.firstinspires.ftc.teamcode.utils.Distance;
import org.firstinspires.ftc.teamcode.vision.Limelight;

@TeleOp(name = "ResetHood", group = "Op Mode")
public class ResetHoodOpMode extends CommandOpMode {
    private Hood hood;

    @Override
    public void initialize() {
        hood = new Hood(hardwareMap, new Limelight(hardwareMap, telemetry, () -> 0.0), () -> new int[]{0});
    }

    // Main code body
    @Override
    public void runOpMode() {
        // Code executed at the very beginning, right after hitting the INIT Button
        initialize();

        // Pauses OpMode until the START button is pressed on the Driver Hub
        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {
            hood.setAngle(HoodConstants.Physics.maxLimit);
        }

        // Cancels all previous commands
        reset();
    }
}