package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import com.revrobotics.spark.config.LimitSwitchConfig.Type;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends SubsystemBase {
    private final SparkMax leftLeader = new SparkMax(1, MotorType.kBrushless);
    private final SparkMax rightLeader = new SparkMax(3, MotorType.kBrushless);
    private final SparkMax leftFollower = new SparkMax(2, MotorType.kBrushless);
    private final SparkMax rightFollower = new SparkMax(4, MotorType.kBrushless);

    private final DifferentialDrive drive = new DifferentialDrive(leftLeader, rightLeader);

    //

    public DriveTrain() {

        SparkMaxConfig globalConfig = new SparkMaxConfig();
        SparkMaxConfig RightLeaderConfig = new SparkMaxConfig();
        SparkMaxConfig LeftFollowerConfig = new SparkMaxConfig();
        SparkMaxConfig RightFollowerConfig = new SparkMaxConfig();

        globalConfig
                .smartCurrentLimit(50)
                .idleMode(IdleMode.kCoast);

        RightLeaderConfig
                .apply(globalConfig)
                .inverted(true);

        LeftFollowerConfig
                .apply(globalConfig)
                .follow(leftLeader);

        RightFollowerConfig
                .apply(globalConfig)
                .follow(rightLeader);

        leftLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(RightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftFollower.configure(LeftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightFollower.configure(RightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void arcadeDrive(double fwd, double rot) {
        drive.arcadeDrive(fwd, rot);
    }

    public void stop() {
        drive.stopMotor();
    }

}
