// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class HangSubsystem extends SubsystemBase {
  Joystick assController;
  Spark extensionMotor;
  Spark pivotMotor;
  DigitalInput lowerExtendLimit;
  DigitalInput upperExtendLimit;
  DigitalInput lowerPivotLimit;
  DigitalInput upperPivotLimit;
  /** Creates a new HangSubsystem. */
  public HangSubsystem() {
    extensionMotor = new Spark(Constants.hangExtensionMotorPort);
    pivotMotor = new Spark(Constants.hangExtensionMotor);
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
    extensionMotor.set(pivotValue);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
