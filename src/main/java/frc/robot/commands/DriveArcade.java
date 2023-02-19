// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class DriveArcade extends CommandBase {

  public DriveArcade() {
    addRequirements(RobotContainer.m_drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double drive = RobotContainer.driver.getRawAxis(Constants.kLeftStickY);
    double rotate = RobotContainer.driver.getRawAxis(Constants.kLeftStickX);
    RobotContainer.driver.getRawButton(Constants.kButtonA);

    RobotContainer.m_drivetrain.set(drive, rotate);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
