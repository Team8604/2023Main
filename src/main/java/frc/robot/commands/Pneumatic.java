// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Pneumatic extends InstantCommand {
  private boolean value;
  private boolean elevator;

  public Pneumatic(boolean value, boolean elevator) {
    addRequirements(elevator ? RobotContainer.arm : RobotContainer.grabber);
    this.value = value;
    this.elevator = elevator;
  }

  @Override
  public void initialize() {
    if(elevator) {
      RobotContainer.arm.set(value);
    } else {
      RobotContainer.grabber.set(value);
    }
  }
}
