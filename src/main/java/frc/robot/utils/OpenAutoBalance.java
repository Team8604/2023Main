package frc.robot.utils;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

// Code taken from https://github.com/FRC3683/OpenAutoBalance/blob/main/java/autoBalance.java
// https://www.chiefdelphi.com/t/psa-balance-in-auto/429778
// with minor modifications:
// 1) removed score and balance functionality
// 2) moved configuration to Constants.java
public class OpenAutoBalance {
    private BuiltInAccelerometer mRioAccel;
    public int state;
    private int debounceCount;
    private double robotSpeedSlow;
    private double robotSpeedFast;
    private double onChargeStationDegree;
    private double levelDegree;
    private double debounceTime;

    public OpenAutoBalance() {
        mRioAccel = new BuiltInAccelerometer();
        state = 0;
        debounceCount = 0;

        /**********
         * CONFIG *
         **********/
        // Speed the robot drived while scoring/approaching station, default = 0.4
        // robotSpeedFast = Constants.kAutoBalanceRobotSpeedFast;
        robotSpeedFast = SmartDashboard.getNumber("robotSpeedFast", Constants.kAutoBalanceRobotSpeedFast);

        // Speed the robot drives while balancing itself on the charge station.
        // Should be roughly half the fast speed, to make the robot more accurate,
        // default = 0.2
        // robotSpeedSlow = Constants.kAutoBalanceRobotSpeedSlow;
        robotSpeedSlow = SmartDashboard.getNumber("robotSpeedSlow", Constants.kAutoBalanceRobotSpeedSlow);
        // Angle where the robot knows it is on the charge station, default = 13.0
        // onChargeStationDegree = Constants.kAutoBalanceOnChargeStationDegree;
        onChargeStationDegree = SmartDashboard.getNumber("onChargeStationDegree", Constants.kAutoBalanceOnChargeStationDegree);

        // Angle where the robot can assume it is level on the charging station
        // Used for exiting the drive forward sequence as well as for auto balancing,
        // default = 6.0
        // levelDegree = Constants.kAutoBalanceLevelDegree;
        levelDegree = SmartDashboard.getNumber("levelDegree", Constants.kAutoBalanceLevelDegree);

        // Amount of time a sensor condition needs to be met before changing states in
        // seconds
        // Reduces the impact of sensor noice, but too high can make the auto run
        // slower, default = 0.2
        // debounceTime = Constants.kAutoBalanceDebounceTime;
        debounceTime = SmartDashboard.getNumber("debounceTime", Constants.kAutoBalanceDebounceTime
        
        );

    }

    public double getPitch() {
        double result = Math.atan2((-mRioAccel.getX()),
                Math.sqrt(mRioAccel.getY() * mRioAccel.getY() + mRioAccel.getZ() * mRioAccel.getZ())) * 57.3;
        SmartDashboard.putNumber("pitch", result);
        return result;
    }

    public double getRoll() {
        double result = Math.atan2(mRioAccel.getY(), mRioAccel.getZ()) * 57.3;
        SmartDashboard.putNumber("roll", result);
        return result;
    }

    // returns the magnititude of the robot's tilt calculated by the root of
    // pitch^2 + roll^2, used to compensate for diagonally mounted rio
    public double getTilt() {
        double pitch = getPitch();
        double roll = getRoll();
        if ((pitch + roll) >= 0) {
            SmartDashboard.putNumber("tilt", Math.sqrt(pitch * pitch + roll * roll));
            return Math.sqrt(pitch * pitch + roll * roll);
        } else {
            SmartDashboard.putNumber("tilt", -Math.sqrt(pitch * pitch + roll * roll));
            return -Math.sqrt(pitch * pitch + roll * roll);
        }
    }

    public int secondsToTicks(double time) {
        return (int) (time * 50);
    }

    // routine for automatically driving onto and engaging the charge station.
    // returns a value from -1.0 to 1.0, which left and right motors should be set
    // to.
    public double autoBalanceRoutine() {
        switch (state) {
            // drive forwards to approach station, exit when tilt is detected
            case 0:
            SmartDashboard.putString("autoBalanceState", "approaching");
                if (getTilt() > onChargeStationDegree) {
                    debounceCount++;
                }
                if (debounceCount > secondsToTicks(debounceTime)) {
                    // state = 1;
                    state = 2;
                    debounceCount = 0;
                    return robotSpeedSlow;
                }
                return robotSpeedFast;
            // driving up charge station, drive slower, stopping when level
            case 1:
                SmartDashboard.putString("autoBalanceState", "leveling");
                if (getTilt() < levelDegree) {
                    debounceCount++;
                }
                if (debounceCount > secondsToTicks(debounceTime)) {
                    state = 2;
                    debounceCount = 0;
                    return 0;
                }
                return robotSpeedSlow;
            // on charge station, stop motors and wait for end of auto
            case 2:
                SmartDashboard.putString("autoBalanceState", "leveled");
                if (Math.abs(getTilt()) <= levelDegree / 2) {
                    debounceCount++;
                }
                if (debounceCount > secondsToTicks(debounceTime)) {
                    state = 4;
                    debounceCount = 0;
                    return 0;
                }
                if (getTilt() >= levelDegree) {
                    return 0.1;
                } else if (getTilt() <= -levelDegree) {
                    return -0.1;
                }
            case 3:
                return 0;
        }
        return 0;
    }
}
