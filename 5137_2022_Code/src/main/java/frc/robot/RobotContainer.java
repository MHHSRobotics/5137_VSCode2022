// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.DriveBaseSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
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
  public static DriveBaseSubsystem driveBase_Subsystem; 

  public static Command placeHolderCommand;

  // Joysticks
  public static Joystick driveController;
  
  public Command autoCommand;

  // Triggers
  public static Trigger ArTrigger;
  public static Trigger AlTrigger;


  //Controllers
  public static Joystick DriverController; // Static means that the method/class the variable or method belongs too doesn't need to be created
  public static Joystick AssistantController;


  public static Intake_Subsystem intake_Subsystem;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveController = new Joystick(Constants.portForDrive);
    driveBase_Subsystem = new DriveBaseSubsystem();
    // Configure the button bindings

    intake_Subsystem = new Intake_Subsystem(); 
    DriverController = new Joystick(Constants.driverControllerPort);
    AssistantController = new Joystick(Constants.assisControllerPort);
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
  //AsRBButton = new JoystickButton(AssistantController, Constants.RButtonPort);
  //AsRBButton.whileActiveContinuous(new ReversedOnIntake_Command());
  //AsRBButton.whenInactive(new OffIntake_Command());

  //AsLBButton = new JoystickButton(AssistantController, Constants.LBButtonPort);
  //AsLBButton.whenInactive(new OffIntake_Command());
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  //Autonomous Code 
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return placeHolderCommand;
    return autoCommand;
  }
  
}
