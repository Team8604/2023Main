// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static Drivetrain drivetrain = new Drivetrain();
  public static Grabber grabber = new Grabber();
  public static Arm arm = new Arm();

  public static Compressor compressor = new Compressor(Constants.kPCM, PneumaticsModuleType.CTREPCM);

  public static Joystick driver = new Joystick(0);
  public static Joystick operator = new Joystick(1);

  public static JoystickButton operatorAButton = new JoystickButton(operator, Constants.kButtonA);
  public static JoystickButton operatorBButton = new JoystickButton(operator, Constants.kButtonB);
  public static JoystickButton driverXButton = new JoystickButton(driver, Constants.kButtonX);
  public static JoystickButton driverYButton = new JoystickButton(driver, Constants.kButtonY);

  public RobotContainer() {
    compressor.enableDigital();

    drivetrain.setDefaultCommand(new DriveArcade());
    arm.setDefaultCommand(new ArmTeleop());

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    operatorAButton.onTrue(new Pneumatic(true));
    operatorAButton.onFalse(new Pneumatic(false));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new ArmPID(0);
  }
}
