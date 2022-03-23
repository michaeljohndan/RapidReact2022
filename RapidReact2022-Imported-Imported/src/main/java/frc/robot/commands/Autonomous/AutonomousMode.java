// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Shooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutonomousMode extends SequentialCommandGroup {
  
  DriveTrain m_driveTrain;
  IntakeSubsystem m_intakeSubsystem;
  ShooterSubsystem m_shooterSubsystem;

  /** Creates a new AutonomousMode. */
  public AutonomousMode(DriveTrain driveTrain, IntakeSubsystem intakeSubsystem, ShooterSubsystem shooterSubsystem) {
    m_driveTrain = driveTrain;
    m_intakeSubsystem = intakeSubsystem;
    m_shooterSubsystem = shooterSubsystem;

    addRequirements(m_driveTrain, m_intakeSubsystem, m_shooterSubsystem);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      // new TurnToAngle(90, m_driveTrain) //should be rotating 90deg CW
      // new TurnToAngle(-90, m_driveTrain)
      // new TurnToAngle(179, m_driveTrain)

      // new SuckForTime(1, m_intakeSubsystem),

      // new ShootForTime(0.7, 0.3, m_shooterSubsystem, m_intakeSubsystem),

      new DriveDistance(3, m_driveTrain)
      // new DriveNoPID(3, m_driveTrain) //negative is forward
    );
  }
}
