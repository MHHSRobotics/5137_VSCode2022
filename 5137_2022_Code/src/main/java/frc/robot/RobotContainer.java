// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//Controller and Input Types
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

//Subsystems
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.DriveBase_Subsystem;
import frc.robot.subsystems.Intake_Subsystem;
import frc.robot.subsystems.HorzConveyor_Subsystem;
import frc.robot.subsystems.VertConveyor_Subsystem;
import frc.robot.subsystems.Shooter_Subsystem;
import frc.robot.subsystems.HangSubsystem;

//Commands
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Intake_Commands.OffIntake_Command;
import frc.robot.commands.Intake_Commands.OnIntake_Command;
import frc.robot.commands.Intake_Commands.ReversedOnIntake_Command;
import frc.robot.commands.Conveyor_Commands.RunHorzConveyorForward_Command;
import frc.robot.commands.Conveyor_Commands.RunHorzConveyorReverse_Command;
import frc.robot.commands.Conveyor_Commands.RunVertConveyorForward_Command;
import frc.robot.commands.Conveyor_Commands.RunVertConveyorReverse_Command;
import frc.robot.commands.Conveyor_Commands.StopHorzConveyor;
import frc.robot.commands.Conveyor_Commands.StopVertConveyor;
import frc.robot.commands.Shooter_Commands.ManShoot_Command;
import frc.robot.commands.Shooter_Commands.stopShoot;
import frc.robot.commands.Hang_Commands.extendHang;
import frc.robot.commands.Hang_Commands.pivotHang;
import frc.robot.commands.Hang_Commands.stopExtendHang;
import frc.robot.commands.Hang_Commands.stopPivotHang;

//CommandGroups

//Motors
import edu.wpi.first.wpilibj.motorcontrol.Spark;

//Misc.
import java.sql.Driver;
import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

// Static means that the method/class the variable or method belongs too doesn't need to be created
public class RobotContainer {

  public Command autoCommand; //On Death Row

  //Xbox Controllers
  public static Joystick DriverController;
  public static Joystick AssistantController;

  // Triggers
  public static Trigger ArTrigger;
  public static Trigger AlTrigger;
  public static Trigger rightTrigger;
  public static Trigger LYAxis;
  public static Trigger RXAxis;
  
  //Buttons
  public static JoystickButton AButton;
  public static JoystickButton BButton;
  public static JoystickButton XButton;
  public static JoystickButton YButton;

  //D-Pad
  public static POVButton uDPadButton;
  public static POVButton dDPadButton;
  public static POVButton rDPadButton;
  public static POVButton lDPadButton;

  //Subsystems
  public static DriveBase_Subsystem driveBase_Subsystem; 
  public static Intake_Subsystem intake_Subsystem;
  public static HorzConveyor_Subsystem horzConveyor_Subsystem;
  public static VertConveyor_Subsystem vertConveyor_Subsystem;
  public static Shooter_Subsystem shooter_Subsystem;
  public static HangSubsystem hang_Subsystem;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    //Controllers
    DriverController = new Joystick(Constants.driverControllerPort);
    AssistantController = new Joystick(Constants.assisControllerPort);
    
    //Subsystems
    driveBase_Subsystem = new DriveBase_Subsystem(); 
    intake_Subsystem = new Intake_Subsystem();
    vertConveyor_Subsystem = new VertConveyor_Subsystem();
    horzConveyor_Subsystem = new HorzConveyor_Subsystem();
    shooter_Subsystem = new Shooter_Subsystem();
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

    //Boolean Suppliers
    BooleanSupplier booleanSupplyXBoxRT = () -> {
      if (DriverController.getRawAxis(Constants.LTAxisPort) > 0.1 && DriverController.getRawAxis(Constants.RTAxisPort) < 0.1)
      {
          return true;
      } else {
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

    BooleanSupplier booleanSupplyAssistantRT = () -> {
      if (AssistantController.getRawAxis(Constants.RTAxisPort) > 0.1 && DriverController.getRawAxis(Constants.LTAxisPort) < 0.1) {
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

    BooleanSupplier booleanSupplyAssistantRX = () -> {
      if (Math.abs(AssistantController.getRawAxis(Constants.RXStickAxisPort)) > 0.1 && Math.abs(AssistantController.getRawAxis(Constants.LYStickAxisPort)) < Math.abs(AssistantController.getRawAxis(Constants.RXStickAxisPort))) {
          return true;
      }
      else {
          return false;
      }
    };

    //Buton Bindings

    //DriverController Triggers
    rightTrigger = new Trigger(booleanSupplyXBoxRT);
    rightTrigger.whileActiveContinuous(new ManShoot_Command());
    rightTrigger.whenInactive(new stopShoot());

    //DriverController Buttons

    //DriverController D-Pad
    uDPadButton = new POVButton(DriverController, 0);
    uDPadButton.whenActive(new ManShoot_Command()); //makes manual shooter engage
    uDPadButton.whenInactive(new stopShoot());

    rDPadButton = new POVButton(DriverController, 90);
    rDPadButton.whenActive(new ManShoot_Command()); //makes manual shooter engage
    rDPadButton.whenInactive(new stopShoot());


    dDPadButton = new POVButton(DriverController, 180);
    dDPadButton.whenActive(new ManShoot_Command()); //makes manual shooter engage
    dDPadButton.whenInactive(new stopShoot());

    //AssistController Triggers
    AlTrigger = new Trigger(booleanSupplyAssistantLT);
    AlTrigger.whileActiveContinuous(new OnIntake_Command());
    AlTrigger.whenInactive(new OffIntake_Command());

    ArTrigger = new Trigger(booleanSupplyAssistantRT);
    ArTrigger.whileActiveContinuous(new ReversedOnIntake_Command());
    ArTrigger.whenInactive(new OffIntake_Command()); 

    RXAxis = new Trigger(booleanSupplyAssistantRX);
    RXAxis.whileActiveContinuous(new pivotHang());
    RXAxis.whenInactive(new stopPivotHang());

    LYAxis = new Trigger(booleanSupplyAssistantLY);
    LYAxis.whileActiveContinuous(new extendHang());
    LYAxis.whenInactive(new stopExtendHang());

    //AssistController Buttons
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
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() {
    // Autonomous needs to be merged :(
    return autoCommand;
  }
}