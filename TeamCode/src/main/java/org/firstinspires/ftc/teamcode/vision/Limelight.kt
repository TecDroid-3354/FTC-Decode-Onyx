package org.firstinspires.ftc.teamcode.vision

import com.qualcomm.hardware.limelightvision.LLResult
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.SubsystemBase
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.vision.VisionConstants.LimelightPhysicalDescription
import org.firstinspires.ftc.teamcode.vision.VisionConstants.AprilTagsPhysicalDescription
import org.firstinspires.ftc.teamcode.utils.Angle
import org.firstinspires.ftc.teamcode.utils.Distance
import java.util.function.DoubleSupplier
import kotlin.math.tan

class Limelight(
    hardwareMap: HardwareMap,
    val telemetry: Telemetry,
    val heading: DoubleSupplier
) : SubsystemBase() {

    private var limelight: Limelight3A? = null
    private var llResult: LLResult? = null
    private var obeliskId = 0

    private var ty = 0.0
    private var tx = 0.0
    private var ta = 0.0

    private var distanceFromLimelightToGoalInches = Distance.fromInches(0.0)

    init {
        limelight = hardwareMap.get<Limelight3A?>(
            Limelight3A::class.java,
            VisionConstants.LimelightIdentification.Id
        ) // Retrieves pipeline
        limelight!!.pipelineSwitch(VisionConstants.LimelightConfiguration.PipelineIndex) // Gets the limelight pipeline
        limelight!!.setPollRateHz(VisionConstants.LimelightConfiguration.PollRateHz)

    }

    fun start() {
        // We start the limelight specifically at this point so that it doesn't take any energy
        // before the start button is pressed in match
        limelight!!.start()
    }

    fun getTx(): Double = tx
    fun getTy(): Double = ty
    fun getTa(): Double = ta

    fun getClassifierTx(filterArray: IntArray): Double {

        if (llResult != null && llResult!!.isValid) {
            val fiducialResult = llResult!!.fiducialResults

            for (detectedId in fiducialResult) {
                for (id in filterArray) {
                    if (detectedId.fiducialId == id) {
                        return tx
                    }
                }
            }
        }

        return 0.0
    }

    private fun getObeliskId() {
        if (llResult != null && llResult!!.isValid) {
            val fiducialResult = llResult!!.fiducialResults

            outerLoop@ for (detectedId in fiducialResult) {
                for (aprilTagId in VisionConstants.AprilTagsIdentification.ObeliskIds) {
                    if (detectedId.fiducialId == aprilTagId) {
                        obeliskId = detectedId.fiducialId
                        break@outerLoop
                        break
                    }
                }
            }
        }
    }

    fun getClassifierDistance(filterArray: IntArray): Distance {
        if (llResult != null && llResult!!.isValid) {
            val fiducialResult = llResult!!.fiducialResults

            for (detectedId in fiducialResult) {
                for (id in filterArray) {
                    if (detectedId.fiducialId == id) {
                        return distanceFromLimelightToGoalInches
                    }
                }
            }
        }

        return Distance.fromInches(0.0)
    }

    override fun periodic() {
        // Updating limelights' robot orientation with the Yaw
        limelight!!.updateRobotOrientation(heading.asDouble)

        // LLResult is like a container full of information about what Limelight sees
        llResult = limelight!!.getLatestResult()


        getObeliskId()
        telemetry.addData("Id detected", getObeliskId())

        if (llResult != null && llResult!!.isValid()) {

            // Offset to target in degrees (from crosshair)
            val targetOffsetAngle_Vertical = Angle.fromDegrees(llResult!!.getTy())
            // Needs to be in radians for tan() method
            val angleToGoalRadians =
                Math.toRadians(LimelightPhysicalDescription.LLMountAngleFromHorizontal.degrees + targetOffsetAngle_Vertical.degrees)

            // Calculated distance from limelight lens to goal (in inches)
            distanceFromLimelightToGoalInches =
                (AprilTagsPhysicalDescription.GoalHeightFromGround - LimelightPhysicalDescription.LLHeightFromGroundToLens) / tan(angleToGoalRadians)
            telemetry.addData("TargetDistanceMeters", distanceFromLimelightToGoalInches.meters)

            // We will first get a (MetaTag2) Pose3D. From here, we will extract its Tx, Ty & Ta components
            tx = llResult!!.getTx()
            ty = llResult!!.getTy()
            ta = llResult!!.getTa()

            telemetry.addData(
                "Tx",
                tx
            )
        } else {
            tx = 0.0
            ty = 0.0
            ta = 0.0
        }
    }
}