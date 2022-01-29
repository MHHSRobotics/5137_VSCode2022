// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.OffIntake_Command;
import frc.robot.commands.OnIntake_Command;
import frc.robot.subsystems.Intake_Subsystem;
import frc.robot.commands.ReversedOnIntake_Command;




/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public OffIntake_Command offIntakeCommand = new OffIntake_Command();

  // Joystick buttons
  public static JoystickButton AButton; // A
  public static JoystickButton BButton; // B
  public static JoystickButton XButton; // ...
  public static JoystickButton YButton;
  public static JoystickButton SelectButton;
  public static JoystickButton StartButton;

  public static JoystickButton AsLBButton; // Left bumper button
  public static JoystickButton AsRBButton; // ...
  public static JoystickButton XbLBButton;
  public static JoystickButton XbRBButton;

  // Triggers
  public static Trigger XrTrigger;
  public static Trigger XlTrigger;
  public static Trigger ArTrigger;
  public static Trigger AlTrigger;

  // DPad Buttons (names)
  public static POVButton uDPadButton; // Up DPad
  public static POVButton dDPadButton; // Down DPad
  public static POVButton lDPadButton; // Left DPad
  public static POVButton rDPadButton; // Right DPad
  public static POVButton ulDPadButton; // Up-Left DPad
  public static POVButton urDPadButton; // Up-Right DPad
  public static POVButton dlDPadButton; // Down-Left DPad
  public static POVButton drDPadButton; // Down-Right DPad
  public static POVButton nDPadButton; // no press on DPad

  //Controllers
  public static Joystick DriverController; // Static means that the method/class the variable or method belongs too doesn't need to be created
  public static Joystick AssistantController;


  public static Intake_Subsystem intake_Subsystem;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    intake_Subsystem = new Intake_Subsystem(); 
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */


  //Don't know if Command Schedualr does this but just in case 
  private void configureButtonBindings() {
    BooleanSupplier booleanSupplyAssistantRT = () -> {
      if (AssistantController.getRawAxis(Constants.RTAxisPort) > 0.1 && DriverController.getRawAxis(Constants.LTAxisPort) < 0.1) {
          return true;
      } else {
          return false;
      }
  }; 
  BooleanSupplier booleanSupplyAssistantLT = () -> {
      if (AssistantController.getRawAxis(Constants.LTAxisPort) > 0.1 && DriverController.getRawAxis(Constants.RTAxisPort) < 0.1) {
          return true;
      } else {
          return false;
      }
  };
  
  //Intake with conveyor (need to add conveyor code)
  //right = reverse 
  AlTrigger = new Trigger(booleanSupplyAssistantLT);
  AlTrigger.whileActiveContinuous(new OnIntake_Command());
  AlTrigger.whenInactive(new OffIntake_Command());

  ArTrigger = new Trigger(booleanSupplyAssistantRT);
  ArTrigger.whileActiveContinuous(new ReversedOnIntake_Command());
  ArTrigger.whenInactive(new OffIntake_Command()); 

  //Intake Only 
  AsRBButton = new JoystickButton(AssistantController, Constants.RButtonPort);
  AsRBButton.whileActiveContinuous(new ReversedOnIntake_Command());
  AsRBButton.whenInactive(new OffIntake_Command());

  AsLBButton = new JoystickButton(AssistantController, Constants.LBButtonPort);
  AsLBButton.whenInactive(new OffIntake_Command());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  //Autonomous Code 
  /*
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  */
}
