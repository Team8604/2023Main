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
  }

  @Override
  public void initialize() {
    autoBalance = new OpenAutoBalance();
    steerAdjust = 0;
  }

  private double steerAdjust;

  @Override
  public void execute() {
    double drive =  autoBalance.autoBalanceRoutine();

    double drift = RobotContainer.drivetrain.gyro.getRate();
    
    steerAdjust += drift * 0.005 * drive;
  
    double steer = 0;

    if(drive > 0) {
      steer -= steerAdjust;
    } else {
      steer += steerAdjust;
    }

    RobotContainer.drivetrain.setRaw(drive, steer);
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
