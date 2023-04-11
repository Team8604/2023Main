// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveTime extends CommandBase {
  private Timer timer;

  private double duration;
  private double drive;
  private double steer;

  /** Creates a new DriveTime. */
  public DriveTime(double duration, double drive, double steer) {
    addRequirements(RobotContainer.drivetrain);
    timer = new Timer();
    timer.start();  

    this.duration = duration;
    this.drive = drive;
    this.steer = steer;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.restart();
    steerAdjust = 0;
  }

  private double steerAdjust = 0;

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(steer == 0){ 
      double drift = RobotContainer.drivetrain.gyro.getRate();
    
      steerAdjust += drift * 0.005 * drive;
  
      if(drive > 0) {
        steer -= steerAdjust;
      } else {
        steer += steerAdjust;
      }
  
    } else {
      RobotContainer.drivetrain.set(drive, steer);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.set(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(duration);
  }
}
