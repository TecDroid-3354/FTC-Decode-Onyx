package org.firstinspires.ftc.teamcode.subsystems.Hood

import org.firstinspires.ftc.teamcode.utils.Angle
import org.firstinspires.ftc.teamcode.utils.Distance
import java.util.function.Supplier

data class Point(
    val xDistanceToGoal: Distance,
    val yHoodAngle: Angle
)

data class LInterpolationConfig (
    val firstCoordinate: Point,
    val secondCoordinate: Point
)

class LinearInterpolationConstructor(val config: LInterpolationConfig, var distanceToGoal: Supplier<Distance>) {

    fun getDesiredPoint(): Double {
        val y = config.firstCoordinate.yHoodAngle.rotations  + ((distanceToGoal.get().meters - config.firstCoordinate.xDistanceToGoal.meters).times(
            (config.secondCoordinate.yHoodAngle.rotations - config.firstCoordinate.yHoodAngle.rotations))).div(
            (config.secondCoordinate.xDistanceToGoal.meters - config.firstCoordinate.xDistanceToGoal.meters))

        return y
    }
}