// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class TestPneumatic extends InstantCommand {
  private boolean value;
  private boolean elevator;

  public TestPneumatic(boolean value, boolean elevator) {
    addRequirements(elevator ? RobotContainer.arm : RobotContainer.grabber);
    this.value = value;
    this.elevator = elevator;
  }

  @Override
  public void initialize() {
    if(elevator) {
      RobotContainer.arm.solenoid.set(value ? Value.kForward : Value.kReverse);
    } else {
      RobotContainer.grabber.set(value);
    }
  }
}
