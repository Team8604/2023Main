// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class DriveFastMode extends InstantCommand {
  private boolean value;

  public DriveFastMode(boolean value) {
    this.value = value;
  }

  @Override
  public void initialize() {
    RobotContainer.drivetrain.fastMode = value;
  }
}
