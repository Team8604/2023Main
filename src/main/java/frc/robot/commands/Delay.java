// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Delay extends CommandBase {
  private Timer timer;
  private double duration;

  public Delay(double duration) {
    timer = new Timer();
    timer.start();
    this.duration = duration;
  }

  @Override
  public void initialize() {
    timer.restart();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(duration);
  }
}
