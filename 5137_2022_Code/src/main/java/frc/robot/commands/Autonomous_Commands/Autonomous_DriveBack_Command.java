// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous_Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;


import edu.wpi.first.wpilibj2.command.CommandBase;

public class Autonomous_DriveBack_Command extends CommandBase {
  /** Creates a new Autonomous_DriveBack_Command. */

  double m_time;
  double m_speed;

  Timer m_timer; 

  public Autonomous_DriveBack_Command(double time, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_timer = new Timer();
    m_time = time;
    m_speed = speed;
    addRequirements(RobotContainer.driveBase_Subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.driveBase_Subsystem.driveStraight(m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driveBase_Subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (m_timer.get() < m_time) {
      return false;
    }
    else {
      return true;
  }
  }
}