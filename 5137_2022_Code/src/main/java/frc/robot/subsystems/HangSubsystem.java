// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class HangSubsystem extends SubsystemBase {

  public DigitalInput limitSwitchExtend;
  public DigitalInput limitSwitchPivot;

  Joystick assController;
  WPI_TalonFX extensionMotor;
  WPI_TalonFX pivotMotor;
  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    extensionMotor = new WPI_TalonFX(Constants.hangExtensionMotorPort);
    pivotMotor = new WPI_TalonFX(Constants.hangExtensionMotorPort);
    assController = RobotContainer.assXBoxController;
    limitSwitchExtend = new DigitalInput(Constants.LimitSwitchLowerDIOPort);
    limitSwitchPivot = new DigitalInput(Constants.LimitSwitchLowerDIOPort);
  }

  public void configMotors(){

  }

  public void extendHang(){
    double extendValue = assController.getRawAxis(Constants.leftXYassJoystickPort);
    extensionMotor.set(extendValue);
  }

  public void pivotHang(){
    double pivotValue = assController.getRawAxis(Constants.rightLRassJoystickPort);
    extensionMotor.set(pivotValue);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
