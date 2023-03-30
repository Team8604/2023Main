// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveArcade extends CommandBase {

  public DriveArcade() {
    addRequirements(RobotContainer.drivetrain);
  }

  @Override
  public void initialize() {}

  private double steerAdjust = 0;

  @Override
  public void execute() {
    double drive = RobotContainer.driver.getRawAxis(Constants.kRightStickY);
    double steer = RobotContainer.driver.getRawAxis(Constants.kLeftStickX);

    // == is boolean xnor. We want to go regular speed when neither fast or slow mode
    // is enabled, or when both (they cancel)
    if(RobotContainer.drivetrain.fastMode == RobotContainer.drivetrain.slowMode) {
      drive *= Constants.kDriveMultiplier;
      steer *= Constants.kSteerMultiplier;
    } else if(RobotContainer.drivetrain.fastMode) {
      // if we got here, fast and slow must have differing values, ergo fast is enabled
      // and slow is not
      drive *= Constants.kDriveFastMultiplier;
      steer *= Constants.kSteerFastMultiplier;
    } else {
      // same logic as above, slow mode
      drive *= Constants.kDriveSlowMultiplier;
      steer *= Constants.kSteerSlowMultiplier;
    }

    double expectedDrift = (300 / 0.49) * steer * steer * Math.signum(steer);

    double drift = RobotContainer.drivetrain.gyro.getRate() - expectedDrift;
    
    steerAdjust += drift * 0.005 * drive;

    if(drive > 0) {
      steer -= steerAdjust;
    } else {
      steer += steerAdjust;
    }

    SmartDashboard.putNumber("Robot Angle", RobotContainer.drivetrain.gyro.getAngle());
    SmartDashboard.putNumber("Steer Adjust", steerAdjust);
    SmartDashboard.putNumber("Drive Drift", drift);

    SmartDashboard.putNumber("Adjusted drive", drive);
    SmartDashboard.putNumber("Adjusted steer", steer);

    RobotContainer.drivetrain.set(drive, steer);

    if(drive < 0.1 && drive > -0.1) {
      steerAdjust = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
