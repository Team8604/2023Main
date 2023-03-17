// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Arm extends SubsystemBase {

  public DoubleSolenoid solenoid;
  public WPI_TalonFX armMotor1;
  public WPI_TalonFX armMotor2;

  public Arm() {
    solenoid = new DoubleSolenoid(Constants.kPCM, PneumaticsModuleType.CTREPCM, Constants.kElevatorForward, Constants.kElevatorReverse);
  
    armMotor1 = new WPI_TalonFX(Constants.kArm1);
    armMotor1.configStatorCurrentLimit(Constants.kCurrentLimitConfig);
    armMotor1.setNeutralMode(NeutralMode.Brake);

    armMotor2 = new WPI_TalonFX(Constants.kArm2);
    armMotor2.follow(armMotor1);
    armMotor2.configStatorCurrentLimit(Constants.kCurrentLimitConfig);
    armMotor2.setNeutralMode(NeutralMode.Brake);
    armMotor2.setInverted(TalonFXInvertType.OpposeMaster);

    armMotor1.configFactoryDefault();
    armMotor1.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, Constants.kTimeoutMs);
    armMotor1.config_kF(0, Constants.kArmF, Constants.kTimeoutMs);
    armMotor1.config_kP(0, Constants.kArmP, Constants.kTimeoutMs);
    armMotor1.config_kI(0, Constants.kArmI, Constants.kTimeoutMs);
    armMotor1.config_kD(0, Constants.kArmD, Constants.kTimeoutMs);
  }

  // 0 is the resting position
  public double targetAngle = 0;

  @Override
  public void periodic() {
    // The arm controls itself as a single unit and 
    // commands simply set the target
    // This is done to reduce the change of a controls 
    // miscommunication causing the robot to damage itself 


    double axis = RobotContainer.operator.getRawAxis(Constants.kLeftStickY);
    // double axis = 0;
    if(axis < 0.1 && axis > -0.1) axis = 0;
    double armPos = armMotor1.getSelectedSensorPosition();
    double armPower = axis * axis * Math.signum(axis) * Constants.kArmMultiplier;
    if(armPos < Constants.MinArmPos && armPower < 0) {
      armPower = 0;
    }
    if(armPos > Constants.MaxArmTicks && armPower > 0) {
      armPower = 0;
    }
    armMotor1.set(ControlMode.PercentOutput, armPower);
    SmartDashboard.putNumber("Arm Position (Ticks)", armPos);
    SmartDashboard.putNumber("Arm Power (%)", armPower * 100);
  }
}
