package org.firstinspires.ftc.teamcode.subsystems.Hood

import org.firstinspires.ftc.teamcode.utils.Angle
import org.firstinspires.ftc.teamcode.utils.Distance

class HoodConstants {
    object Ids {
        const val hoodServoId: String = "hoodServo"
    }

    object Physics {
        const val maxLimit: Double = 1.0
        const val minLimit: Double = 0.25
    }

    object Interpolation {
        val firstCoordinate: Point = Point(Distance.fromMeters(0.8), Angle.fromRotations(0.25))
        val secondCoordinate: Point = Point(Distance.fromMeters(1.5), Angle.fromRotations(0.65))

    }
}