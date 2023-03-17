// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class Pneumatic extends InstantCommand {
  private boolean value;

  public Pneumatic(boolean value) {
    addRequirements(RobotContainer.grabber);
    this.value = value;
  }

  @Override
  public void initialize() {
    RobotContainer.grabber.set(value);
  }
}
