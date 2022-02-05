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

  public DigitalInput limitSwitchExtend;
  public DigitalInput limitSwitchPivot;

  public boolean allowedExtend;
  public boolean allowedPivot;

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
    limitSwitchExtend = RobotContainer.LimitSwitchExtend;
    limitSwitchPivot = RobotContainer.LimitSwitchPivot;
    extensionMotor = RobotContainer.extensionMotor;
    pivotMotor = RobotContainer.pivotMotor;
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
    if (limitSwitchExtend.get() && allowedExtend) { //if the extend limit switch isn't pressed (naitive)
      extensionMotor.set(0.1);
      }
    else {
      extensionMotor.set(0);
      }

    if (limitSwitchPivot.get() && allowedPivot) { //if the pivot limit switch isn't pressed (naitive)
      pivotMotor.set(0.1);
    }
    else {
      pivotMotor.set(0);
    }
  }
}
