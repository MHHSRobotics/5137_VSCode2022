// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class HangSubsystem extends SubsystemBase {
  Joystick assController;
  WPI_TalonFX extensionMotor;
  WPI_TalonFX pivotMotor;
  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    extensionMotor = new WPI_TalonFX(Constants.hangExtensionMotorPort);
    pivotMotor = new WPI_TalonFX(Constants.hangExtensionMotor);
    assController = RobotContainer.assXBoxController;
  }

  public void configMotors(){

  }

  public void extendHang(){
    extensionMotor.set(Constants.hangExtensionSpeed);
  }

  public void retractHang(){
    extensionMotor.set(-Constants.hangExtensionSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
