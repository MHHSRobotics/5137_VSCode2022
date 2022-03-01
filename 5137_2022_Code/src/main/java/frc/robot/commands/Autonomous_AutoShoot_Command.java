// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;


import edu.wpi.first.wpilibj2.command.CommandBase;

public class Autonomous_AutoShoot_Command extends CommandBase {
  /** Creates a new Autonomous_AutoShoot* _Command. */

  double m_time;

  Timer m_timer; 

  public Autonomous_AutoShoot_Command(double time) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_timer = new Timer();
    m_time = time;
    addRequirements(RobotContainer.shooter_Subsystem);
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
    System.out.println("This is the time: "+m_timer.get());
    RobotContainer.shooter_Subsystem.shoot(Constants.shooterAngle, false, false, true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter_Subsystem.endShoot();
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
