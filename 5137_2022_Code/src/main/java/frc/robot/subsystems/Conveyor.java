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

public class Conveyor extends SubsystemBase {
  SparkMaxWrapper SparkMAX = new SparkMaxWrapper(Constants.conveyorPort, MotorType.kBrushless);
    //MotorController SparkMAX = new Spark(Constants.conveyorPort);
  /** Creates a new Conveyor. */
  public Conveyor() {}

  public void shooterConveyorOn(){ //shooterConveyor is going in towards the shooter
    SparkMAX.set(Constants.conveyorSpeed);
  }

  public void intakeConveyorOn(){ //intakeConveyor is going out towards the intake (AKA shooting out balls if stuck)
    SparkMAX.set(-Constants.conveyorSpeed);
  }

  public void turnConveyorOff()
  {
    SparkMAX.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
