package org.firstinspires.ftc.teamcode.commands

import com.seattlesolvers.solverslib.command.CommandBase
import com.seattlesolvers.solverslib.controller.PIDController
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.ChassisSpeeds
import org.firstinspires.ftc.teamcode.subsystems.drivetrain.Mecanum
import java.util.function.DoubleSupplier

class AlignToAprilTagCMD(val mecanum: Mecanum, val tx: DoubleSupplier) : CommandBase() {
    val pidController = PIDController(1.0, 0.0, 0.0)

    init {
        addRequirements(mecanum)
    }

    override fun initialize() {

    }

    override fun execute() {
        val rx = pidController.calculate(tx.asDouble, 0.0)
        mecanum.setChassisSpeeds(ChassisSpeeds(0.0, 0.0, rx))
    }

    override fun end(interrupted: Boolean) {
     
    }

    override fun isFinished(): Boolean {
        return -0.5 <= tx.asDouble || tx.asDouble <= 0.5
    }
}