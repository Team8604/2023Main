// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveTime extends CommandBase {
  private Timer timer;
  /** Creates a new DriveTime. */
  public DriveTime() {
    addRequirements(RobotContainer.drivetrain, RobotContainer.arm, RobotContainer.grabber);
    timer = new Timer();
    timer.start();  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.hasElapsed(4)) {
      RobotContainer.drivetrain.set(.5, 0);
      return;
    }
    if(timer.hasElapsed(1.5)) {
      RobotContainer.drivetrain.set(-0.4, 0);
      return;
    }
    if(timer.hasElapsed(.5)) {
      RobotContainer.drivetrain.set(.5, 0);
      return;
    }
    RobotContainer.drivetrain.set(-.75, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.set(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(8);
  }
}
