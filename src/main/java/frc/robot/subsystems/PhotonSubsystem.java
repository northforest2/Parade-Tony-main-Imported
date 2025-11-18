package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PhotonSubsystem extends SubsystemBase {
    /** Creates a new PhotonSubsystem. */
    PhotonCamera camera1 = new PhotonCamera("Camera1");

    private final Transform3d cameraTransformation = new Transform3d(0.3683, 0, 0.28575, new Rotation3d());
    private final AprilTagFieldLayout fieldLayout = AprilTagFieldLayout
            .loadField(AprilTagFields.k2025ReefscapeAndyMark);

    PhotonPoseEstimator poseEstimator = new PhotonPoseEstimator(fieldLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR,
            cameraTransformation);

    // constructor
    public PhotonSubsystem() {
        poseEstimator.setMultiTagFallbackStrategy(PoseStrategy.LOWEST_AMBIGUITY);
    }

    public Optional<EstimatedRobotPose> getRobotPose() {
        Optional<EstimatedRobotPose> robotPose = Optional.empty();

        for (PhotonPipelineResult change : camera1.getAllUnreadResults()) {
            robotPose = poseEstimator.update(change);
        }

        return robotPose;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}
