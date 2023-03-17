// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmPID extends CommandBase {

  private double target;
  private int correctTicks;

  public ArmPID(double target) {
    addRequirements(RobotContainer.arm);
    this.target = target;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    RobotContainer.arm.armMotor1.set(ControlMode.Position, target);
    double delta = Math.abs(RobotContainer.arm.armMotor1.getSelectedSensorPosition() - target);
    SmartDashboard.putNumber("Arm Error", delta);
    if(delta < Constants.kArmMaxError) {
      correctTicks++;
    } else {
      correctTicks = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return correctTicks > Constants.kArmCorrectTicksRequired;
  }
}
