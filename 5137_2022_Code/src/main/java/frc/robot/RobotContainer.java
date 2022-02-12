// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.RunConveyorTowardsIntake;
import frc.robot.commands.RunConveyorTowardsShooter;
import frc.robot.commands.StopConveyor;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.DriveBaseSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
 // public static final Joystick driveController = null;
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static DriveBaseSubsystem driveBase_Subsystem;
  public static Conveyor conveyor_subsystem;

  // Triggers
  public static Trigger ArTrigger;
  public static Trigger AlTrigger;

  //XBox Controllers
  public static Joystick driverController;
  public static Joystick assistantController;

  // Joystick buttons
  public static JoystickButton AButton; // A
  public static JoystickButton YButton; // Y

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    conveyor_subsystem = new Conveyor();
    driveBase_Subsystem = new DriveBaseSubsystem();
    driverController = new Joystick(Constants.portForDrive);
    assistantController = new Joystick(Constants.portForAssis);


    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   * @param AssistantController 
   */
  private void configureButtonBindings() {
    BooleanSupplier booleanSupplyAssistantRT = () -> {
      if (assistantController.getRawAxis(Constants.RTAxisPort) > 0.1 && driverController.getRawAxis(Constants.LTAxisPort) < 0.1) {
          return true;
      } else {
          return false;
      }
    };
    
    BooleanSupplier booleanSupplyAssistantLT = () -> {
      if (assistantController.getRawAxis(Constants.LTAxisPort) > 0.1 && driverController.getRawAxis(Constants.RTAxisPort) < 0.1) {
          return true;
      } else {
          return false;
      }
  }; 
    
    ArTrigger = new Trigger(booleanSupplyAssistantRT);
    ArTrigger.whileActiveContinuous(new RunConveyorTowardsIntake());
    ArTrigger.whenInactive(new StopConveyor());

    AlTrigger = new Trigger(booleanSupplyAssistantLT);
    AlTrigger.whileActiveContinuous(new RunConveyorTowardsShooter());
    AlTrigger.whenInactive(new StopConveyor());

    AButton = new JoystickButton(XboxController, Constants.AButtonPort);
    AButton.whenHeld(new RunConveyorTowardsIntake());
    AButton.whenReleased(new StopVertConveyor());

    YButton = new JoystickButton(XboxController, Constants.YButtonPort);
    YButton.whenHeld(new RunConveyorTowardsShooter());
    YButton.whenReleased(new StopVertConveyor());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
