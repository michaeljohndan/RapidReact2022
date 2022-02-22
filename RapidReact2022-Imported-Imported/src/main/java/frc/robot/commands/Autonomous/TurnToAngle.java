// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnToAngle extends PIDCommand {
  private boolean isFinished;
  private DriveTrain m_driveTrain;

  /** Creates a new RotateCW. */
  public TurnToAngle(double angle, DriveTrain driveTrain) {
    super(
        // The controller that the command will use
        new PIDController(1, 0, 0),
        // This should return the measurement
        () -> driveTrain.getHeading(),
        // This should return the setpoint (can also be a constant)
        () -> angle,
        // This uses the output
        output -> {
          // Use the output here
          SmartDashboard.putNumber("auto output", 0.1 * output);
          // SmartDashboard.putNumber("auto input", value);
          driveTrain.arcadeDrive(0, 0.1 * output);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    
    m_driveTrain = driveTrain;
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(5, 10);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    isFinished = getController().atSetpoint();

    SmartDashboard.putBoolean("tta PID isFinished", isFinished);
    SmartDashboard.putNumber("tta position error", this.getController().getPositionError());
    SmartDashboard.putNumber("tta measurement", this.m_measurement.getAsDouble());
    SmartDashboard.putNumber("tta setpointsource", this.m_setpoint.getAsDouble());

    if (isFinished) {
      getController().reset();
      m_driveTrain.resetGyro();
      getController().disableContinuousInput();
    }
    
    return isFinished;
  }

  @Override
  public void initialize() {
    m_driveTrain.resetGyro();
  }
}