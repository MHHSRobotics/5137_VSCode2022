// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class Auto_DriveBack extends CommandBase {

  double m_time;
  double m_speed;
  Timer timer = new Timer();
  /** Creates a new Auto_DriveBack. */
  public Auto_DriveBack(double time, double speed) {
    m_time = time;
    m_speed = speed; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveBase_Subsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while (timer.get() < m_time){
      RobotContainer.driveBase_Subsystem.driveStraight(m_speed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driveBase_Subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timer.get() < m_time){
      return false;
    }
    else {
      return true;
    }
  }
}
