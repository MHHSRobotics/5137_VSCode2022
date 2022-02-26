// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivebase_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ArcadeDrive_Command extends CommandBase {
  /** Creates a new ArcadeDrive. */
  public ArcadeDrive_Command() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveBase_Subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
        /* As you can see, whenever ArcadeDrive is scheduled to execute (which,
		due to being the default, is every 20ms), the code runs the method rampArcadeDrive() in the
		DriveBase subsystem. No other commands are here to potentially interrupt ArcadeDrive, so the
    isFinished() and end() methods are irrelevant.*/
    System.out.println("DriveBase is running...");
    RobotContainer.driveBase_Subsystem.rampArcadeDrive(RobotContainer.DriverController);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.driveBase_Subsystem.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
