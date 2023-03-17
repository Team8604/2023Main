// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmTeleop extends CommandBase {
  public ArmTeleop() {
    addRequirements(RobotContainer.arm);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double axis = RobotContainer.operator.getRawAxis(Constants.kLeftStickY);
    if(axis < 0.1 && axis > -0.1) axis = 0;
    double armPos = RobotContainer.arm.armMotor1.getSelectedSensorPosition();
    double armPower = axis * axis * Math.signum(axis) * Constants.kArmMultiplier;
    if(!RobotContainer.arm.unlockedMode) {
      if(armPos < Constants.kArmMinPOs && armPower < 0) {
        armPower = 0;
      }
      if(armPos > Constants.kArmMaxPos && armPower > 0) {
        armPower = 0;
      }
    }
    SmartDashboard.putNumber("Arm Power (%)", armPower * 100);
    RobotContainer.arm.armMotor1.set(ControlMode.PercentOutput, axis * axis * Math.signum(axis) * Constants.kArmMultiplier);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
