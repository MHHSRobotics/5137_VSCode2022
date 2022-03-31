// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.simulation.SparkMaxWrapper;

public class HorzConveyor_Subsystem extends SubsystemBase {
  SparkMaxWrapper horzConveyorMotor = new SparkMaxWrapper(Constants.horzConveyorPort, MotorType.kBrushless);
  //MotorController HorzConveyorMotor = new Spark(Constants.horzConveyorPort);
  /** Creates a new Conveyor. */
  public HorzConveyor_Subsystem() {
    horzConveyorMotor.setSmartCurrentLimit(Constants.currentLimitConveyor);
  }

  public void forwardHorzConveyorOn(){ //shooterConveyor is going in towards the shooter
    horzConveyorMotor.set(Constants.conveyorSpeed);
  }

  public void reverseHorzConveyorOn(){ //intakeConveyor is going out towards the intake (AKA shooting out balls if stuck)
    horzConveyorMotor.set(-Constants.conveyorSpeed);
  }

  public void turnHorzConveyorOff()
  {
    horzConveyorMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}