// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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
  public SparkMaxWrapper extensionMotor;
  public SparkMaxWrapper pivotMotor; 

  private SparkMaxLimitSwitch extforwardLimit;
  private SparkMaxLimitSwitch extreverseLimit;

  private SparkMaxLimitSwitch pivforwardLimit;
  private SparkMaxLimitSwitch pivreverseLimit;

  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    extensionMotor = new SparkMaxWrapper(Constants.hangExtensionMotorPort, MotorType.kBrushless);
    pivotMotor = new SparkMaxWrapper(Constants.hangPivotMotorPort, MotorType.kBrushless);
    assController = RobotContainer.assistantController;

    //extforwardLimit = extensionMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    //extreverseLimit = extensionMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

    //pivforwardLimit = pivotMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    //pivreverseLimit = pivotMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
  }

  public void config(){

  }

  public void extendHang(Joystick xBoxController){
    extensionMotor.set(xBoxController.getRawAxis(Constants.LYStickAxisPort)*(-1));
  }

  public void pivotHang(Joystick xBoxController){
    pivotMotor.set(xBoxController.getRawAxis(Constants.RXStickAxisPort));
  }

  public void stopExtendHang(){
    extensionMotor.stopMotor();
  }

  public void stopPivotHang(){
    pivotMotor.stopMotor();
  }

  @Override
  public void periodic() {
    //limit switches :D
    /*
    if (extforwardLimit.isPressed() || extreverseLimit.isPressed()){
      stopExtendHang();
    }

    if (pivforwardLimit.isPressed() || pivreverseLimit.isPressed()){
      stopPivotHang();
    }
    */
    
    extensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    pivotMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    pivotMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    
    extensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.extensionForwardLimit);
    pivotMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.pivotForwardLimit);
    pivotMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.pivotReverseLimit);
    
    
  }
}
