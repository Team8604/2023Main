// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  
  private WPI_VictorSPX leftLeader;
  private WPI_VictorSPX rightLeader;
  private WPI_VictorSPX leftFollower;
  private WPI_VictorSPX rightFollower;
  private MotorControllerGroup leftMotors;
  private MotorControllerGroup rightMotors;
  private DifferentialDrive differentialDrive;

  public Drivetrain() {
    // Init Left Leader
    leftLeader  = new WPI_VictorSPX(Constants.kLeftLeader);
    leftLeader.setNeutralMode(NeutralMode.Brake);

    // Init Right Leader
    rightLeader  = new WPI_VictorSPX(Constants.kRightLeader);
    rightLeader.setNeutralMode(NeutralMode.Brake);

    // Init left follower
    leftFollower  = new WPI_VictorSPX(Constants.kLeftFollower);
    leftFollower.setNeutralMode(NeutralMode.Brake);

    // Init right follower
    rightFollower  = new WPI_VictorSPX(Constants.kRightFollower);
    rightFollower.setNeutralMode(NeutralMode.Brake);

    leftMotors = new MotorControllerGroup(leftLeader, leftFollower);
    rightMotors = new MotorControllerGroup(rightLeader, rightFollower);

    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void set(double forward, double steer) {
    differentialDrive.arcadeDrive(steer, forward);
  }

  @Override
  public void periodic() {
    
  }
}
