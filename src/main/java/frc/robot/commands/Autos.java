package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

public class Autos {
    public static final Command midConeMove = new SequentialCommandGroup(
        new ArmPID(Constants.kAutoArmPos),
        new Delay(Constants.kAutoPrereleaseTime),
        new Pneumatic(false, false),
        new Delay(Constants.kAutoPostreleaseTime),
        new ParallelCommandGroup(
            new SequentialCommandGroup(
                new ArmPID(Constants.kArmRetracted),
                new Pneumatic(true, false)
            ),
            new DriveTime(Constants.kAutoDriveTime, Constants.kAutoDrivePower, 0)
        )
    );
    
    public static final Command midCone = new SequentialCommandGroup(
        new ArmPID(Constants.kAutoArmPos),
        new Delay(Constants.kAutoPrereleaseTime),
        new Pneumatic(false, false),
        new Delay(Constants.kAutoPostreleaseTime),
        new ArmPID(Constants.kArmRetracted),
        new Pneumatic(true, false)
    );

    public static final Command midConeBalance = new SequentialCommandGroup(
        new ArmPID(Constants.kAutoArmPos),
        new Delay(Constants.kAutoPrereleaseTime),
        new Pneumatic(false, false),
        new Delay(Constants.kAutoPostreleaseTime),
        new ParallelCommandGroup(
            new SequentialCommandGroup(
                new ArmPID(Constants.kArmRetracted),
                new Pneumatic(true, false)
            ),
            new AutoBalanceCommand()
        )
    );

    public static final SendableChooser<Command> autoChooser = new SendableChooser<>();
}
