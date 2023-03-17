// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Grabber extends SubsystemBase {
  
  private DoubleSolenoid solenoid;
  private boolean open;

  public Grabber() {
    solenoid = new DoubleSolenoid(Constants.kPCM, PneumaticsModuleType.CTREPCM, Constants.kGrabberForward, Constants.kGrabberReverse);
    set(true);
  }

  public void set(boolean open) {
    this.open = open;
    solenoid.set(open ? Value.kForward : Value.kReverse);
  }

  public boolean isOpen() {
    return open;
  }

  @Override
  public void periodic() {}
}
