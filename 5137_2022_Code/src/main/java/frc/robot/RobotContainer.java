// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.commands.moveHang;
import frc.robot.commands.stopPivotHang;
import frc.robot.commands.stopExtendHang;
import frc.robot.subsystems.HangSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
// The robot's subsystems and commands are defined here...

  public static Joystick DriverController;
  
  /*private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();*/
  /*private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);*/

  private Command autoCommand;

  public static Joystick AssistantController;
  public static HangSubsystem hang_Subsystem;

  public static Trigger RXAxis;
  public static Trigger RYAxis;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    AssistantController = new Joystick(Constants.assisControllerPort);
    DriverController = new Joystick(Constants.driveControllerPort);
    hang_Subsystem = new HangSubsystem();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    BooleanSupplier booleanSupplyAssistantRX = () -> {
      if (Math.abs(AssistantController.getRawAxis(Constants.RXAxisPort)) > 0.1) {
          return true;
      }
      else {
          return false;
      }
    };
   
    BooleanSupplier booleanSupplyAssistantRY = () -> {
      if (Math.abs(AssistantController.getRawAxis(Constants.RYAxisPort)) > 0.1) {
          return true;
      } 
      else {
          return false;da
      }
    };

    RXAxis = new Trigger(booleanSupplyAssistantRX);
    RXAxis.whileActiveContinuous(new moveHang());
    RXAxis.whenInactive(new stopPivotHang());

    RYAxis = new Trigger(booleanSupplyAssistantRY);
    RYAxis.whileActiveContinuous(new moveHang());
    RYAxis.whenInactive(new stopExtendHang());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}