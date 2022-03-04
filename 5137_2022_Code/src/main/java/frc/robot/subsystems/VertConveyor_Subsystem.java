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

public class VertConveyor_Subsystem extends SubsystemBase {
  SparkMaxWrapper VertConveyorMotor = new SparkMaxWrapper(Constants.vertConveyorPort, MotorType.kBrushless);
  
  //MotorController VeryConveyorMotor = new Spark(Constants.vertConveyorPort);
  /** Creates a new Conveyor. */
  public void VertConveyor() {}

  public void forwardVertConveyorOn(){ //shooterConveyor is going in towards the shooter
    VertConveyorMotor.set(Constants.conveyorSpeed);
  }

  public void reverseVertConveyorOn(){ //intakeConveyor is going out towards the intake (AKA shooting out balls if stuck)
    VertConveyorMotor.set(-Constants.conveyorSpeed);
  }

  public void turnVertConveyorOff()
  {
    VertConveyorMotor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}