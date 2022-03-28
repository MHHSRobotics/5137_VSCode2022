// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Timer;

public class ManShoot_Command extends CommandBase {
  /** Creates a new ManShoot_Command. */
  double m_Time;
  Timer m_Timer;
  public ManShoot_Command() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.shooter_Subsystem);
    m_Time = 2;
    m_Timer = new Timer();
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //RobotContainer.driveBase_Subsystem.lockWheels();
    if (RobotContainer.shooter_Subsystem.shoot(Constants.shooterAngle, false, true, false) == true) {//ready to shoot {
      //m_Timer.start();
      //if (m_Timer.get() > m_Time) {
        RobotContainer.vertConveyor_Subsystem.forwardVertConveyorOn();
        RobotContainer.horzConveyor_Subsystem.reverseHorzConveyorOn();
     // }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
