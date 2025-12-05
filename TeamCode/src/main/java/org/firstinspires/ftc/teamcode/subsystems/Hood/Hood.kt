package org.firstinspires.ftc.teamcode.subsystems.Hood

import androidx.core.math.MathUtils
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.SubsystemBase
import org.firstinspires.ftc.teamcode.subsystems.Hood.HoodConstants.Interpolation.firstCoordinate
import org.firstinspires.ftc.teamcode.subsystems.Hood.HoodConstants.Interpolation.secondCoordinate
import org.firstinspires.ftc.teamcode.utils.Distance
import org.firstinspires.ftc.teamcode.vision.Limelight
import java.util.function.Supplier

class Hood(hardwareMap: HardwareMap, val limelight: Limelight, idFilters: Supplier<IntArray>) : SubsystemBase() {
    private val servo: Servo
    private val linearInterpolationConstructor: LinearInterpolationConstructor

    init {
        servo = hardwareMap.get<Servo>(Servo::class.java, HoodConstants.Ids.hoodServoId)

        servo.direction = Servo.Direction.REVERSE
        setAngle(HoodConstants.Physics.minLimit)

        // Interpolation Constructor
        linearInterpolationConstructor = LinearInterpolationConstructor(
            LInterpolationConfig(firstCoordinate, secondCoordinate),
            { limelight.getClassifierDistance(idFilters.get()) }
        )
    }

    fun setAngle(angle: Double) {
        val clampedAngle =
            MathUtils.clamp(angle, HoodConstants.Physics.minLimit, HoodConstants.Physics.maxLimit)
        servo.setPosition(clampedAngle)
    }

    fun setAngleCMD(angle: Double): Command {
        return InstantCommand({ setAngle(angle) })
    }

    fun adjustAngleAccordingDistance(): Command {
        val angle = linearInterpolationConstructor.getDesiredPoint()
        return setAngleCMD(angle)
    }

    val position: Double
        get() = servo.getPosition()

    override fun periodic() {
        limelight.telemetry.addData("AngleAccordingDistance", linearInterpolationConstructor.getDesiredPoint())
    }
}
