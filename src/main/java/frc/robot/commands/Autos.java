package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.robot.Constants;

public class Autos {
    public static final Command midConeMove = new SequentialCommandGroup(
        new ArmPID(Constants.kAutoArmPos),
        new Delay(Constants.kAutoPrereleaseTime),
        new Pneumatic(false),
        new Delay(Constants.kAutoPostreleaseTime),
        new ParallelCommandGroup(
            new SequentialCommandGroup(
                new ArmPID(Constants.kArmRetracted),
                new Pneumatic(true)
            ),
            new DriveTime(Constants.kAutoDriveTime, Constants.kAutoDrivePower, 0)
        )
    );
    
    public static final Command midCone = new SequentialCommandGroup(
        new ArmPID(Constants.kAutoArmPos),
        new Delay(Constants.kAutoPrereleaseTime),
        new Pneumatic(false),
        new Delay(Constants.kAutoPostreleaseTime),
        new ArmPID(Constants.kArmRetracted),
        new Pneumatic(true)
    );
    
    public static final Command midConeBalance = new SequentialCommandGroup(
        new ArmPID(Constants.kAutoArmPos),
        new Delay(Constants.kAutoPrereleaseTime),
        new Pneumatic(false),
        new Delay(Constants.kAutoPostreleaseTime),
        new ArmPID(Constants.kArmRetracted),
        new Pneumatic(true)
        while (Robot.gyro.getAngle < 5 && Robot.gyro.getAngle > -5) {
            RobotContainer.drivetrain.set(.35,0);
        },
        while (Robot.gyro.getAngle > 10) {
            RobotContainer.drivetrain.set(0.1,0);
        },
        RobotContainer.drivetrain.set(0,0)
    );

    public static final SendableChooser<Command> autoChooser = new SendableChooser<>();
}
