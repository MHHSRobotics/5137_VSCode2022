// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Shooter_Subsystem extends SubsystemBase {

  CANSparkMax shooterMotor;

  Joystick driveController;

  boolean horizontalTurnGood;
  boolean velocityRunningGood;
  /** Creates a new Shooter_Subsystem. */
  public Shooter_Subsystem() {
    horizontalTurnGood = false;
    velocityRunningGood = false;
    shooterMotor = new CANSparkMax(Constants.shooterId, MotorType.kBrushless);

    driveController = RobotContainer.driveController;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void endShoot() { 
    shooterMotor.stopMotor();
    //table.getEntry("pipeline").setNumber(2); //sets pipeline number 1-9. 1 isnt limelight, 2 is (new*)
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
    double setVelo = 0;
    
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
      else if (driveController.getRawAxis(Constants.LTAxisPort) > 0.1) {
          controllerMAGVelo = Constants.maxPercShooter * driveController.getRawAxis(Constants.LTAxisPort);
      }
      else if (autonomous) {
          controllerMAGVelo = Constants.InitiationLineShooterPerc;
      }
      else {
          controllerMAGVelo = 0;
      }

      shooterMotor.set(controllerMAGVelo);
      //shooterFollowerTalon.set(ControlMode.Follower, Constants.shooterCAN);
      RelativeEncoder encoder = shooterMotor.getEncoder();
      double veloReading = encoder.getVelocity();

      System.out.println("Shooters Running at: " + driveController.getRawAxis(Constants.LTAxisPort) + "%");
      System.out.println("Velocity is reading as: " + veloReading);
      
      //Return true if within a degree of error, or else don't

      if ((controllerMAGVelo <= (veloReading + Constants.veloError)) && 
      (controllerMAGVelo >= (veloReading - Constants.veloError))) {
      return true; 
      }
      else {
      return false;
      }  
    
    }
}

