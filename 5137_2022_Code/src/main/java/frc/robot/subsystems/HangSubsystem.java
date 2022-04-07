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

  private SparkMaxLimitSwitch extforwardLimit;
  private SparkMaxLimitSwitch extreverseLimit;

  public RelativeEncoder leftEncoder;
  public RelativeEncoder rightEncoder;


  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    leftExtensionMotor = new SparkMaxWrapper(Constants.rightExtensionPort, MotorType.kBrushless);
    rightExtensionMotor = new SparkMaxWrapper(Constants.leftExtensionPort, MotorType.kBrushless);
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
    double hangSpeed = xBoxController.getRawAxis(Constants.LYStickAxisPort) * 0.4;
    leftExtensionMotor.set(hangSpeed);
    rightExtensionMotor.set(hangSpeed - hangSpeed * 0.06);
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
    if (assController.getRawButton(6) == false){
    //System.out.println("Left hang pos: " + -leftEncoder.getPosition());
    //System.out.println("Right hang pos: " + -rightEncoder.getPosition());
    if (-leftEncoder.getPosition() < 0){
      //leftExtensionMotor.stopMotor();
      System.out.println("Left Stopping");
    }
    if (-leftEncoder.getPosition() > 87){
      //leftExtensionMotor.stopMotor();
      System.out.println("Left Stopping");
    }
    if (-rightEncoder.getPosition() < 0){
      //rightExtensionMotor.stopMotor();
      System.out.println("Right Stopping");
    }
    if (-rightEncoder.getPosition() > 87){
      //rightExtensionMotor.stopMotor();
      System.out.println("Right Stopping");
    }
  }
    
    leftExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, false);
    rightExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, false);
    //leftExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    //rightExtensionMotor.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    
    //leftExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.extensionReverseLimit);
    //rightExtensionMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.extensionReverseLimit);
    
  }
}
