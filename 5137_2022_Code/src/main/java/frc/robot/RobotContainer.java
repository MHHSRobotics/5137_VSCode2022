// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.sql.Driver;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Shooter_Subsystem;

import frc.robot.subsystems.VertConveyor_Subsystem;
import frc.robot.subsystems.HorzConveyor_Subsystem;
import frc.robot.subsystems.DriveBase_Subsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Intake_Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.commands.Conveyor_Commands.RunHorzConveyorForward_Command;
import frc.robot.commands.Conveyor_Commands.RunHorzConveyorReverse_Command;
import frc.robot.commands.Conveyor_Commands.RunVertConveyorForward_Command;
import frc.robot.commands.Conveyor_Commands.RunVertConveyorReverse_Command;
import frc.robot.commands.Conveyor_Commands.StopHorzConveyor;
import frc.robot.commands.Conveyor_Commands.StopVertConveyor;
import frc.robot.commands.Hang_Commands.extendHang;
import frc.robot.commands.Hang_Commands.pivotHang;
import frc.robot.commands.Hang_Commands.stopExtendHang;
import frc.robot.commands.Hang_Commands.stopPivotHang;
import frc.robot.commands.Intake_Commands.OffIntake_Command;
import frc.robot.commands.Intake_Commands.OnIntake_Command;
import frc.robot.commands.Intake_Commands.ReversedOnIntake_Command;
import frc.robot.commands.Shooter_Commands.ManShoot_Command;
import frc.robot.commands.Shooter_Commands.stopShoot;
import frc.robot.subsystems.HangSubsystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

// public static final Joystick driveController = null;
  // The robot's subsystems and commands are defined here...

  public static Command m_autoCommand;

  public static DriveBase_Subsystem driveBase_Subsystem; 

  public static Command placeHolderCommand;
  
  public Command autoCommand;

  // Triggers
  public static Trigger ArTrigger;
  public static Trigger AlTrigger;
  public static Trigger rightTrigger;
  public static Trigger RXAxis;
  public static Trigger LYAxis;

  // Joystick buttons
  public static JoystickButton AButton; // A
  public static JoystickButton YButton; // Y

  //Xbox Controllers
  public static Joystick DriverController; // Static means that the method/class the variable or method belongs too doesn't need to be created
  public static Joystick AssistantController;

  //Joystick Button 
  public static JoystickButton XButton;
  public static JoystickButton BButton;

  // D Pad Buttons
  public static POVButton uDPadButton;
  public static POVButton dDPadButton;
  public static POVButton rDPadButton;
  public static POVButton lDPadButton;


  public static Intake_Subsystem intake_Subsystem;
  public static HorzConveyor_Subsystem horzConveyor_Subsystem;
  public static VertConveyor_Subsystem vertConveyor_Subsystem;
  public static HangSubsystem hang_Subsystem;
  public static Shooter_Subsystem shooter_Subsystem;

// The robot's subsystems and commands are defined here...
  
  /*private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();*/
  /*private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);*/



  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    DriverController = new Joystick(Constants.driverControllerPort);
    AssistantController = new Joystick(Constants.assisControllerPort);

    DriverController = new Joystick(Constants.portForDrive);
    
    

    // Configure the button bindings
    driveBase_Subsystem = new DriveBase_Subsystem();
    shooter_Subsystem = new Shooter_Subsystem();
    vertConveyor_Subsystem = new VertConveyor_Subsystem();
    horzConveyor_Subsystem = new HorzConveyor_Subsystem();
    intake_Subsystem = new Intake_Subsystem();
    hang_Subsystem = new HangSubsystem(); 
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

    BooleanSupplier booleanSupplyXBoxRT = () -> {
      if (DriverController.getRawAxis(Constants.LTAxisPort) > 0.1 && DriverController.getRawAxis(Constants.RTAxisPort) < 0.1)
      {
          return true;
      } else {
          return false;
      }
  };

    BooleanSupplier booleanSupplyAssistantRT = () -> {
      if (AssistantController.getRawAxis(Constants.RTAxisPort) > 0.1 && DriverController.getRawAxis(Constants.LTAxisPort) < 0.1) {
        return true;
      } 
      else {
          return false;
      }
    };
    
    BooleanSupplier booleanSupplyAssistantRX = () -> {
      if (Math.abs(AssistantController.getRawAxis(Constants.RXStickAxisPort)) > 0.1 && Math.abs(AssistantController.getRawAxis(Constants.LYStickAxisPort)) < Math.abs(AssistantController.getRawAxis(Constants.RXStickAxisPort))) {
          return true;
      }
      else {
          return false;
      }
    };
   
    BooleanSupplier booleanSupplyAssistantLT = () -> {
      if (AssistantController.getRawAxis(Constants.LTAxisPort) > 0.1 && DriverController.getRawAxis(Constants.RTAxisPort) < 0.1) {
        return true;
      } 
      else {
        return false;
      }
    };

    BooleanSupplier booleanSupplyAssistantLY = () -> {
        if (Math.abs(AssistantController.getRawAxis(Constants.LYStickAxisPort)) > 0.1 && Math.abs(AssistantController.getRawAxis(Constants.RXStickAxisPort)) < Math.abs(AssistantController.getRawAxis(Constants.LYStickAxisPort))) {
            return true;
        } 
        else {
            return false;
        }
      };
  
    rightTrigger = new Trigger(booleanSupplyXBoxRT);
    rightTrigger.whileActiveContinuous(new ManShoot_Command());
    rightTrigger.whenInactive(new stopShoot());

    //Up DPAD
    uDPadButton = new POVButton(DriverController, 0); //uDPadButtonValue
    uDPadButton.whenActive(new ManShoot_Command()); //makes manual shooter engage
    uDPadButton.whenInactive(new stopShoot());

    rDPadButton = new POVButton(DriverController, 90);
    rDPadButton.whenActive(new ManShoot_Command()); //makes manual shooter engage
    rDPadButton.whenInactive(new stopShoot());


    dDPadButton = new POVButton(DriverController, 180);
    dDPadButton.whenActive(new ManShoot_Command()); //makes manual shooter engage
    dDPadButton.whenInactive(new stopShoot());

    AlTrigger = new Trigger(booleanSupplyAssistantLT);
    AlTrigger.whileActiveContinuous(new OnIntake_Command());
    AlTrigger.whenInactive(new OffIntake_Command());

    ArTrigger = new Trigger(booleanSupplyAssistantRT);
    ArTrigger.whileActiveContinuous(new ReversedOnIntake_Command());
    ArTrigger.whenInactive(new OffIntake_Command()); 

    XButton = new JoystickButton(AssistantController, Constants.XButtonPort);
    XButton.whenHeld(new RunHorzConveyorReverse_Command());
    XButton.whenReleased(new StopHorzConveyor());

    BButton = new JoystickButton(AssistantController, Constants.BButtonPort);
    BButton.whenHeld(new RunHorzConveyorForward_Command());
    BButton.whenReleased(new StopHorzConveyor());

    AButton = new JoystickButton(AssistantController, Constants.AButtonPort);
    AButton.whenHeld(new RunVertConveyorReverse_Command());
    AButton.whenReleased(new StopVertConveyor());

    YButton = new JoystickButton(AssistantController, Constants.YButtonPort);
    YButton.whenHeld(new RunVertConveyorForward_Command());
    YButton.whenReleased(new StopVertConveyor());
    
    RXAxis = new Trigger(booleanSupplyAssistantRX);
    RXAxis.whileActiveContinuous(new pivotHang());
    RXAxis.whenInactive(new stopPivotHang());

    LYAxis = new Trigger(booleanSupplyAssistantLY);
    LYAxis.whileActiveContinuous(new extendHang());
    LYAxis.whenInactive(new stopExtendHang());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  //Autonomous Code 
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoCommand;
  }
}