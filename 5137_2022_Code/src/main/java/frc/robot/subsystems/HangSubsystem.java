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

  public boolean allowedExtend;

  Joystick assController;
  public SparkMaxWrapper leftExtensionMotor;
  public SparkMaxWrapper rightExtensionMotor; 

  private SparkMaxLimitSwitch extforwardLimit;
  private SparkMaxLimitSwitch extreverseLimit;

  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    leftExtensionMotor = new SparkMaxWrapper(Constants.rightExtensionPort, MotorType.kBrushless);
    rightExtensionMotor = new SparkMaxWrapper(Constants.leftExtensionPort, MotorType.kBrushless);
    assController = RobotContainer.assistantController;

    //extforwardLimit = extensionMotor.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    //extreverseLimit = extensionMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
  }

  public void config(){

  }

  public void extendHang(Joystick xBoxController){
    leftExtensionMotor.set(xBoxController.getRawAxis(Constants.LYStickAxisPort)*(-1));
    rightExtensionMotor.set(xBoxController.getRawAxis(Constants.LYStickAxisPort)*(-1));
  }

  public void stopExtendHang(){
    leftExtensionMotor.stopMotor();
    rightExtensionMotor.stopMotor();
  }

  @Override
  public void periodic() {
    //limit switches :D
    /*
    if (extforwardLimit.isPressed() || extreverseLimit.isPressed()){
      stopExtendHang();
    }
    */
    
    leftExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    rightExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    
    leftExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.extensionForwardLimit);
    rightExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.extensionForwardLimit);
  }
}
