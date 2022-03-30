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
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.commands.Intake_Commands.OffIntake_Command;
import frc.robot.commands.Intake_Commands.OnIntake_Command;
import frc.robot.commands.Intake_Commands.ReversedOnIntake_Command;
import frc.robot.commands.Conveyor_Commands.RunHorzConveyorForward_Command;
import frc.robot.commands.Conveyor_Commands.RunHorzConveyorReverse_Command;
import frc.robot.commands.Conveyor_Commands.RunVertConveyorForward_Command;
import frc.robot.commands.Conveyor_Commands.RunVertConveyorReverse_Command;
import frc.robot.commands.Conveyor_Commands.StopHorzConveyor_Command;
import frc.robot.commands.Conveyor_Commands.StopVertConveyor_Command;
import frc.robot.commands.Drivebase_Commands.ArcadeDrive_Command;
import frc.robot.commands.Shooter_Commands.ManShoot_Command;
import frc.robot.commands.Shooter_Commands.backupManShoot_Command;
import frc.robot.commands.Shooter_Commands.stopShoot_Command;
import frc.robot.commands.Hang_Commands.extendHang_Command;
import frc.robot.commands.Hang_Commands.pivotHang_Command;
import frc.robot.commands.Hang_Commands.stopExtendHang_Command;
import frc.robot.commands.Hang_Commands.stopPivotHang_Command;

//CommandGroups
import frc.robot.CommandGroups.Shoot_Drive_CommandGroup;
import frc.robot.CommandGroups.Drive_Shoot_CommandGroup;
import frc.robot.CommandGroups.Complex_CommandGroup;

//Motors
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import java.io.IOException;
import java.nio.file.Path;
//Misc.
import java.sql.Driver;
import java.util.function.BooleanSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

// public static final Joystick driveController = null;
  // The robot's subsystems and commands are defined here...

  //Xbox Controllers
  public static Joystick driverController;
  public static Joystick assistantController;

  //Triggers
  public static Trigger ArTrigger;
  public static Trigger AlTrigger;
  public static Trigger rightTrigger;
  public static Trigger LYAxis;
  public static Trigger RXAxis;
  public static Trigger leftTrigger;
  
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

  //Commands

  //Command Groups
  public static Shoot_Drive_CommandGroup shoot_DriveBack_CommandGroup;
  public static Drive_Shoot_CommandGroup driveBack_Shoot_CommandGroup;
  public static Complex_CommandGroup complex_CommandGroup;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    //Controllers
    driverController = new Joystick(Constants.driverControllerPort);
    assistantController = new Joystick(Constants.assisControllerPort);
    
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
   * @param assistantController 
   */

  private void configureButtonBindings() {
    BooleanSupplier booleanSupplyXBoxLT = () -> {
      if (driverController.getRawAxis(Constants.LTAxisPort) > 0.1 && driverController.getRawAxis(Constants.RTAxisPort) < 0.1)
      {
        return true;
      } else {
        return false;
      }
    };

    BooleanSupplier booleanSupplyXBoxRT = () -> {
      if (driverController.getRawAxis(Constants.LTAxisPort) < 0.1 && driverController.getRawAxis(Constants.RTAxisPort) > 0.1)
      {
        return true;
      } else {
        return false;
      }
    };

    BooleanSupplier booleanSupplyAssistantLT = () -> {
      if (assistantController.getRawAxis(Constants.LTAxisPort) > 0.1 && driverController.getRawAxis(Constants.RTAxisPort) < 0.1) {
        return true;
      } 
      else {
        return false;
      }
    };

    BooleanSupplier booleanSupplyAssistantRT = () -> {
      if (assistantController.getRawAxis(Constants.RTAxisPort) > 0.1 && driverController.getRawAxis(Constants.LTAxisPort) < 0.1) {
        return true;
      } 
      else {
          return false;
      }
    };

    BooleanSupplier booleanSupplyAssistantLY = () -> {
       if (Math.abs(assistantController.getRawAxis(Constants.LYStickAxisPort)) > 0.1 && Math.abs(assistantController.getRawAxis(Constants.RXStickAxisPort)) < Math.abs(assistantController.getRawAxis(Constants.LYStickAxisPort))) {
           return true;
       } 
       else {
          return false;
      }
    };

    BooleanSupplier booleanSupplyAssistantRX = () -> {
      if (Math.abs(assistantController.getRawAxis(Constants.RXStickAxisPort)) > 0.1 && Math.abs(assistantController.getRawAxis(Constants.LYStickAxisPort)) < Math.abs(assistantController.getRawAxis(Constants.RXStickAxisPort))) {
          return true;
      }
      else {
          return false;
      }
    };

    //Buton Bindings

    //DriverController Triggers
    rightTrigger = new Trigger(booleanSupplyXBoxLT);
    rightTrigger.whileActiveContinuous(new ManShoot_Command());
    rightTrigger.whenInactive(new stopShoot_Command());
    rightTrigger.whenInactive(new StopHorzConveyor_Command());
    rightTrigger.whenInactive(new StopVertConveyor_Command());

   // = new Trigger();
    //rightTrigger.whileActiveContinuous(new backupManShoot_Command());
    //rightTrigger.whenInactive(new stopShoot_Command());
    //DriverController Buttons

    //DriverController D-Pad
    uDPadButton = new POVButton(driverController, 0);
    uDPadButton.whileActiveContinuous(new ManShoot_Command()); //makes manual shooter engage
    uDPadButton.whenInactive(new stopShoot_Command());
    uDPadButton.whenInactive(new StopHorzConveyor_Command());
    uDPadButton.whenInactive(new StopVertConveyor_Command());

    rDPadButton = new POVButton(driverController, 90);
    rDPadButton.whileActiveContinuous(new ManShoot_Command()); //makes manual shooter engage
    rDPadButton.whenInactive(new stopShoot_Command()); 
    rDPadButton.whenInactive(new StopHorzConveyor_Command());
    rDPadButton.whenInactive(new StopVertConveyor_Command());

    dDPadButton = new POVButton(driverController, 180);
    dDPadButton.whileActiveContinuous(new ManShoot_Command()); //makes manual shooter engage
    dDPadButton.whenInactive(new stopShoot_Command()); 
    dDPadButton.whenInactive(new StopHorzConveyor_Command());
    dDPadButton.whenInactive(new StopVertConveyor_Command());

    //AssistController Triggers
    AlTrigger = new Trigger(booleanSupplyAssistantLT);
    AlTrigger.whileActiveContinuous(new OnIntake_Command());
    AlTrigger.whileActiveContinuous(new RunHorzConveyorReverse_Command());
    AlTrigger.whenInactive(new OffIntake_Command());
    AlTrigger.whenInactive(new StopHorzConveyor_Command());

    ArTrigger = new Trigger(booleanSupplyAssistantRT);
    ArTrigger.whileActiveContinuous(new ReversedOnIntake_Command());
    ArTrigger.whileActiveContinuous(new RunHorzConveyorForward_Command());
    ArTrigger.whenInactive(new OffIntake_Command());
    ArTrigger.whenInactive(new StopHorzConveyor_Command()); 

    RXAxis = new Trigger(booleanSupplyAssistantRX);
    RXAxis.whileActiveContinuous(new pivotHang_Command());
    RXAxis.whenInactive(new stopPivotHang_Command());

    LYAxis = new Trigger(booleanSupplyAssistantLY);
    LYAxis.whileActiveContinuous(new extendHang_Command());
    LYAxis.whenInactive(new stopExtendHang_Command());

    //AssistController Buttons
    XButton = new JoystickButton(assistantController, Constants.XButtonPort);
    XButton.whileHeld(new RunHorzConveyorForward_Command());
    XButton.whenReleased(new StopHorzConveyor_Command());

    BButton = new JoystickButton(assistantController, Constants.BButtonPort);
    BButton.whileHeld(new RunHorzConveyorReverse_Command());
    BButton.whenReleased(new StopHorzConveyor_Command());

    AButton = new JoystickButton(assistantController, Constants.AButtonPort);
    AButton.whileHeld(new RunVertConveyorReverse_Command());
    AButton.whenReleased(new StopVertConveyor_Command());

    YButton = new JoystickButton(assistantController, Constants.YButtonPort);
    YButton.whileHeld(new RunVertConveyorForward_Command());
    YButton.whenReleased(new StopVertConveyor_Command());

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public static Command getAutonomousCommand(String m_autoSelection) {
    /*
    driveBack_Shoot_CommandGroup = new Drive_Shoot_CommandGroup();
    shoot_DriveBack_CommandGroup = new Shoot_Drive_CommandGroup();
    complex_CommandGroup = new Complex_CommandGroup();
    
    switch (m_autoSelection) {
      case (Constants.driveBack_Shoot):
        return driveBack_Shoot_CommandGroup;
      case (Constants.shoot_DriveBack):
        return shoot_DriveBack_CommandGroup;
      case (Constants.complex):
        return complex_CommandGroup;
      default:
        return driveBack_Shoot_CommandGroup;
    */
    /*
    TrajectoryConfig config =
    new TrajectoryConfig(
            Constants.kMaxSpeedMetersPerSecond,
            Constants.kMaxAccelerationMetersPerSecondSquared)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(Constants.kDriveKinematics)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint);
        */
    String trajectoryJSON = "Paths/MoveStraight.wpilib.json";    
    Trajectory trajectory = new Trajectory();
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }

    driveBase_Subsystem.resetOdometry(trajectory.getInitialPose());

    RamseteCommand ramseteCommand =
        new RamseteCommand(
            trajectory,
            driveBase_Subsystem::getPose,
            new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.ksVolts,
                Constants.kvVoltSecondsPerMeter,
                Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            driveBase_Subsystem::getWheelSpeeds,
            new PIDController(Constants.kPDriveVel, 0, 0),
            new PIDController(Constants.kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            driveBase_Subsystem::tankDriveVolts,
            driveBase_Subsystem);

    // Reset odometry to the starting pose of the trajectory.
    driveBase_Subsystem.resetOdometry(trajectory.getInitialPose());

    System.out.println(trajectory.getTotalTimeSeconds());

    // Run path following command, then stop at the end.
    return ramseteCommand.andThen(() -> driveBase_Subsystem.tankDriveVolts(0, 0));
    }
  }

