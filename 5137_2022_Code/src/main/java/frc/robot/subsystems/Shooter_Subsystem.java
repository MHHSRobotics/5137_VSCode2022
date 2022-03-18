// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.simulation.SparkMaxWrapper;

public class Shooter_Subsystem extends SubsystemBase {

  SparkMaxWrapper shooterMotor;
  SparkMaxWrapper backSpinShooterMotor;
  Joystick driveController;

  boolean horizontalTurnGood;
  boolean velocityRunningGood;
  /** Creates a new Shooter_Subsystem. */
  public Shooter_Subsystem() {
    horizontalTurnGood = false;
    velocityRunningGood = false;
    shooterMotor = new SparkMaxWrapper(Constants.shooterId, MotorType.kBrushless);
    shooterMotor.setInverted(true);
    backSpinShooterMotor = new SparkMaxWrapper(Constants.backSpinShooterId, MotorType.kBrushless);

    driveController = RobotContainer.driverController;
    shooterMotor.setIdleMode(IdleMode.kCoast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  public boolean shoot(double angle, boolean overrideDB, boolean manual, boolean autonomous) { //called by command (constantly)  
    return checkReadyShoot(angle, overrideDB, manual, autonomous);
  }

  public boolean checkReadyShoot(double angle, boolean horizontalTurnEnabled, boolean manual, boolean autonomous) {
    velocityRunningGood = setVelo(angle, manual, autonomous);
 
    if (velocityRunningGood) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean setVelo(double angle, boolean manual, boolean autonomous) { //return when velocity is running optimally 
    
    double controllerMAGVelo;
    
    if (driveController.getPOV() == Constants.uDPadButtonValue) {
        controllerMAGVelo = Constants.InitiationLineShooterPerc;
    }
    else if (driveController.getPOV() == Constants.rDPadButtonValue) {
        controllerMAGVelo = Constants.FrontTrenchShooterPerc;
    }
    else if (driveController.getPOV() == Constants.dDPadButtonValue) {
        controllerMAGVelo = Constants.BackTrenchShooterPerc;
    }
    else if (driveController.getRawAxis(Constants.LTAxisPort) > 0.1) { // Should be right trigger on driver controller
        controllerMAGVelo = Constants.maxPercShooter * driveController.getRawAxis(Constants.LTAxisPort);
    }
    else if (autonomous) {
        controllerMAGVelo = Constants.InitiationLineShooterPerc;
    }
    else {
        controllerMAGVelo = 0;
    }

    //Return true if within a degree of error, or else don't
    shooterMotor.set(controllerMAGVelo);

    backSpinShooterMotor.set(-controllerMAGVelo / 1.3);
    RelativeEncoder encoder = shooterMotor.getEncoder();
    double veloReading = encoder.getVelocity();
    if (((controllerMAGVelo * 5700) <= (veloReading + Constants.veloError)) && // 5700 is the max rpm
     ((controllerMAGVelo * 5700) >= (veloReading - Constants.veloError))) {
       return true; 
     } 
     else {
        return false;
    }
 }

  public void stopShoot(){
    shooterMotor.stopMotor();
    backSpinShooterMotor.stopMotor();
  }
}

