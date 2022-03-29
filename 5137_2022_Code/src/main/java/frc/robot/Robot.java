// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.net.PortUnreachableException;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SensorUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.simulation.PWMSim;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Conveyor_Commands.StopHorzConveyor_Command;
import frc.robot.subsystems.ColorSensor_Subsystem;
import frc.robot.subsystems.HangSubsystem;
import frc.robot.subsystems.VertConveyor_Subsystem;
import frc.robot.commands.Conveyor_Commands.StopVertConveyor_Command;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  public static UsbCamera driverCam;
  //public static UsbCamera converyorCam; 
  private Joystick xboxController;

  //ColorSensorV3 colorSensor;

  //private final ColorSensorV3 m_colorSensor = new ColorSensorV3(Constants.i2cPort);
  //private final ColorMatch m_ColorMatcher = new ColorMatch();

  public static String m_Shoot_Drive = Constants.shoot_DriveBack;
  public static String m_Drive_Shoot = Constants.driveBack_Shoot;
  public static String m_Complex = Constants.complex;
  private String m_autoSelected;
  SendableChooser<String> m_chooser = new SendableChooser<>();

  //public AddressableLED m_led;
  //public AddressableLEDBuffer m_ledBuffer;
  //public int m_rainbowFirstPixelHue;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    
    m_chooser.setDefaultOption("Shoot_Drive", m_Shoot_Drive);
    m_chooser.addOption("Shoot_Drive", m_Shoot_Drive);
    m_chooser.addOption("Drive_Shoot", m_Drive_Shoot);
    m_chooser.addOption("Complex",m_Complex);
    SmartDashboard.putData("Auto Choices",m_chooser);
    /*
    m_led = new AddressableLED(Constants.LEDPort);
    m_ledBuffer = new AddressableLEDBuffer(Constants.LEDLength);
    m_led.setLength(m_ledBuffer.getLength());

    m_led.setData(m_ledBuffer);
    m_led.start();
    */
    

    //driverCam = edu.wpi.first.cameraserver.CameraServer.startAutomaticCapture(0);

    //driverCam.setResolution(240, 180);
    //driverCam.setFPS(50);

    xboxController = RobotContainer.assistantController;
  }

  private void partyMode() {
    // For every pixel
    /*
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Calculate the hue - hue is easier for rainbows because the color
      // shape is a circle so only one value needs to precess
      final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_ledBuffer.getLength())) % 180;
      // Set the value
      m_ledBuffer.setHSV(i, hue, 255, 128);
    }
    // Increase by to make the rainbow "move"
    m_rainbowFirstPixelHue += 3;
    // Check bounds
    m_rainbowFirstPixelHue %= 180;
    */
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */

  
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    /*
    while (RobotContainer.YButton.getAsBoolean()){
      if (ColorSensor_Subsystem.checkConveyorEmpty() == false){
        new StopVertConveyor_Command();
      }
    }
   */
    //Color detectedColor = m_colorSensor.getColor();
    //String colorString;

    //if (detectedColor.equals(Constants.paper)){
    //    ColorSensor_Subsystem.colorString = "Empty Conveyor";
        
    //}
    //else {
    //    ColorSensor_Subsystem.colorString = "Ball Detected";
    //}
  
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    //partyMode();
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);

    m_autonomousCommand = RobotContainer.getAutonomousCommand(m_autoSelected);

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
