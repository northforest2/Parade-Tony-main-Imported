package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import java.util.function.DoubleSupplier;

public class StickDrive extends Command {
    
  private final DriveTrain m_drivetrain;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;

  public StickDrive(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    m_drivetrain = subsystem;
    m_forward = forward;
    m_rotation = rotation;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  @Override
  public void execute() {
     m_drivetrain.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
  }



}
