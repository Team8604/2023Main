// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // CAN IDs
    public static final int kPCM = 1;
    public static final int kLeftLeader = 4;
    public static final int kLeftFollower = 5;
    public static final int kRightLeader = 2;
    public static final int kRightFollower = 3;
    public static final int kArm1 = 6;
    public static final int kArm2 = 7;

    // Solenoid values
    public static final int kGrabberForward = 7;
    public static final int kGrabberReverse = 4;
    public static final int kElevatorForward = 6;
    public static final int kElevatorReverse = 5;

    // Controller bindings
    public static final int kButtonA = 1;
    public static final int kButtonB = 2;
    public static final int kButtonX = 3;
    public static final int kButtonY = 4;
    public static final int kBumperL = 5;
    public static final int kBumperR = 6;

    public static final int kLeftStickX = 0;
    public static final int kLeftStickY = 1;
    public static final int kRightStickX = 4;
    public static final int kRightStickY = 5;
    public static final int kLeftTriggerY = 2;
    public static final int kRightTriggerY = 3;

    public static final int kButton1 = 1;
    public static final int kButton2 = 2;
    public static final int kButton3 = 3;
    public static final int kButton4 = 4;

    // Control multipliers
    public static final double kDriveMultiplier = 0.7;
    public static final double kSteerMultiplier = 0.7;
    public static final double kDriveFastMultiplier = 1.0;
    public static final double kSteerFastMultiplier = 0.7;
    public static final double kDriveSlowMultiplier = 0.5;
    public static final double kSteerSlowMultiplier = 0.5;
    public static final double kArmSlowMultipler = 0.35;
    public static final double kArmMultiplier = 0.5;
    public static final double kArmMaxPowerPID = 0.4;
    public static final double kDrivetrainCounterSteer = 0.0;

    // Motor limits
    // Values copied from 2021 code
    public static final double kCurrentLimitPeak = 45;
    public static final double kCurrentLimitContinuous = 40;
    public static final double kCurrentLimitTime = 0.4;
    public static final StatorCurrentLimitConfiguration kCurrentLimitConfig = new StatorCurrentLimitConfiguration(true, Constants.kCurrentLimitContinuous,
        Constants.kCurrentLimitPeak, Constants.kCurrentLimitTime); 

    // PID settings
    public static final int kTimeoutMs = 0;
    public static final double kArmP = 0.025;
    public static final double kArmI = 0;
    public static final double kArmD = 0;
    public static final double kArmF = 0;
    public static final double kArmMaxError = 5000;
    public static final int kArmCorrectTicksRequired = 10;

    //Arm positions
    public static final double kArmMinPOs = 5000;
    public static final double kArmRetracted = 0;
    public static final double kArmMidCone = 60600;
    public static final double kArmMidCube = 75000;
    public static final double kArmSlowZone = 110000;
    public static final double kArmMaxPos = 135000;

    //Auto settings
    public static final double kAutoArmPos = 75000;
    public static final double kAutoPrereleaseTime = 3;
    public static final double kAutoPostreleaseTime = 1;
    public static final double kAutoDrivePower = 0.7;
    public static final double kAutoDriveTime = 2.5;
    public static final double kAutoDriveTimeLong = 2.75;

    //Auto balance settings

    // Speed the robot drives while balancing itself on the charge station.
    // Should be roughly half the fast speed, to make the robot more accurate
    public static final double kAutoBalanceRobotSpeedSlow = 0.3;

    // Speed the robot drived while scoring/approaching station
    public static final double kAutoBalanceRobotSpeedFast = 0.5;

    // Angle where the robot knows it is on the charge station
    public static final double kAutoBalanceOnChargeStationDegree = 13;

    // Angle where the robot can assume it is level on the charging station
    // Used for exiting the drive forward sequence as well as for auto balancing,
    public static final double kAutoBalanceLevelDegree = 3.0;

    // Amount of time a sensor condition needs to be met before changing states in
    // seconds
    // Reduces the impact of sensor noice, but too high can make the auto run
    // slower
    public static final double kAutoBalanceDebounceTime = 0.4;
}
