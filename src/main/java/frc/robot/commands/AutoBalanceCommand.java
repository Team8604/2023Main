// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.utils.OpenAutoBalance;

public class AutoBalanceCommand extends CommandBase {

  private OpenAutoBalance autoBalance;

  public AutoBalanceCommand() {
    addRequirements(RobotContainer.drivetrain);

    autoBalance = new OpenAutoBalance();
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double speed =  autoBalance.autoBalanceRoutine();

    //TODO: System to counteract rotational drift
    RobotContainer.drivetrain.setRaw(speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.setRaw(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
