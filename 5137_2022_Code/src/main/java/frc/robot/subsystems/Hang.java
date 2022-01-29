// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Hang extends SubsystemBase 
{
  // Creates a new Hang. 
  public Hang() 
  { 
    public boolean allowed; 


    limitSwitchExtend = RobotContainer.LimitSwitchExtend;
    limitSwitchRotate = RobotContainer.LimitSwitchRotate;
    
    MotorController talonFX = new WPI_TalonFX(Constants.TalonFX);

    @Override
    public void periodic()
    {
      // This method will be called once per scheduler run

      public void hangGoUp()
      {
        if (limitSwitchExtend.get() && allowed)
        {
          talonFX.set(Constants.hangSpeed);
        }
        else
        {
          talonFX.stopMotor();
        } 
      }

      public void hangGoDown() 
      {
        talonFX.set(-Constants.hangSpeed);
      }

      public void hangRotate()
      {
        if (limitSwitchRotate.get())
        {
          talonFX.rotate(Joystick DriveJoystick1) //put joystick here, still don't know how yet...
        }
        else
        {
          talonFX.stopMotor();
        }
      }

      public void hangStop()
      {
        talonFX.stopMotor();
      }
    }
  }
}
