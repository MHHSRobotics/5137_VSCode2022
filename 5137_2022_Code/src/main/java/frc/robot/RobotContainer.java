// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.HorzConveyor_Subsystem;
import frc.robot.subsystems.DriveBase_Subsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.OffIntake_Command;
import frc.robot.commands.OnIntake_Command;
import frc.robot.subsystems.Intake_Subsystem;
import frc.robot.commands.ReversedOnIntake_Command;
import frc.robot.commands.RunHorzConveyorForward_Command;
import frc.robot.commands.RunHorzConveyorReverse_Command;
import frc.robot.commands.StopHorzConveyor;





/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

// public static final Joystick driveController = null;
  // The robot's subsystems and commands are defined here...
  public static DriveBase_Subsystem driveBase_Subsystem; 

  public static Command placeHolderCommand;

  // Joysticks
  public static Joystick driveController;
  
  public Command autoCommand;

  // Triggers
  public static Trigger ArTrigger;
  public static Trigger AlTrigger;


  //Xbox Controllers
  public static Joystick DriverController; // Static means that the method/class the variable or method belongs too doesn't need to be created
  public static Joystick AssistantController;

  //Joystick Button 
  public static JoystickButton XButton;
  public static JoystickButton BButton;


  public static Intake_Subsystem intake_Subsystem;
  public static HorzConveyor_Subsystem horzConveyor_Subsystem;

public static Object storage_Subsystem;

public static Object shooter_Subsystem;



  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveController = new Joystick(Constants.portForDrive);
    driveBase_Subsystem = new DriveBase_Subsystem();
    horzConveyor_Subsystem = new HorzConveyor_Subsystem();
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
   * @param AssistantController 
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

  XButton = new JoystickButton(AssistantController, Constants.XButtonPort);
  XButton.whenHeld(new RunHorzConveyorForward_Command());
  XButton.whenReleased(new StopHorzConveyor());

  BButton = new JoystickButton(AssistantController, Constants.BButtonPort);
  BButton.whenHeld(new RunHorzConveyorReverse_Command());
  BButton.whenReleased(new StopHorzConveyor());
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
    //return autoCommand;
  }
  
}
