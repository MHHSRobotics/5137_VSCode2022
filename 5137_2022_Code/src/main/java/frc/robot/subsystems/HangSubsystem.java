// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.IdleMode;
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

  public RelativeEncoder leftEncoder;
  public RelativeEncoder rightEncoder;

  private SparkMaxLimitSwitch extforwardLimit;
  private SparkMaxLimitSwitch extreverseLimit;

  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    leftExtensionMotor = new SparkMaxWrapper(Constants.leftExtensionPort, MotorType.kBrushless);
    rightExtensionMotor = new SparkMaxWrapper(Constants.rightExtensionPort, MotorType.kBrushless);
    leftExtensionMotor.setInverted(true);
    leftEncoder = leftExtensionMotor.getEncoder();
    rightEncoder = rightExtensionMotor.getEncoder();
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
    leftExtensionMotor.setIdleMode(IdleMode.kBrake);
    rightExtensionMotor.setIdleMode(IdleMode.kBrake);
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

  public void resetHang(){
    //Emergency Hang Reset
    while (leftEncoder.getPosition() > 0.1 || rightEncoder.getPosition() > 0.1){ //Runs while hang is not reset
      if (-leftEncoder.getPosition() > 0.1){ //Checks left motor
        leftExtensionMotor.set(-0.2);
      } else {
        leftExtensionMotor.stopMotor();
      }
      if (rightEncoder.getPosition() > 0.1){ //Checks right motor
        rightExtensionMotor.set(-0.2);
      } else {
        rightExtensionMotor.stopMotor();
      }
    }
    leftExtensionMotor.stopMotor();
    rightExtensionMotor.stopMotor();
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
    leftExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    rightExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    rightExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

    leftExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 0);
    leftExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.extensionForwardLimit);
    rightExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, 0);
    rightExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.extensionForwardLimit);
  }
}
