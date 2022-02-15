// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.simulation.SparkMaxWrapper;

public class HangSubsystem extends SubsystemBase {

  public DigitalInput limitSwitchExtend;
  public DigitalInput limitSwitchPivot;

  public boolean allowedExtend;
  public boolean allowedPivot;

  Joystick assController;
  SparkMaxWrapper extensionMotor;
  SparkMaxWrapper pivotMotor;


  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    extensionMotor = new SparkMaxWrapper(Constants.hangExtensionMotorPort, MotorType.kBrushless);
    pivotMotor = new SparkMaxWrapper(Constants.hangPivotMotorPort, MotorType.kBrushless);
    assController = RobotContainer.assXBoxController;
  }

  public void config(){

  }

  public void extendHang(Joystick xBoxController){
    double extendValue = xBoxController.getRawAxis(Constants.assLYStickAxisPort);
    extensionMotor.set(extendValue);
  }

  public void pivotHang(Joystick xBoxController){
    double pivotValue = xBoxController.getRawAxis(Constants.assRXStickAxisPort);
    pivotMotor.set(pivotValue);
  }

  @Override
  public void periodic() {
    //limit switches :D
    extensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    pivotMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    pivotMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    
    extensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.extensionForwardLimit);
    pivotMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.pivotForwardLimit);
    pivotMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.pivotReverseLimit);
  }
}
