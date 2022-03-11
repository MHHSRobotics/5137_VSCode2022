// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoShoot_Command;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoDrive_Command;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoIntake_Command;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Shoot_Drive_CommandGroup extends SequentialCommandGroup{

  public Shoot_Drive_CommandGroup(){
    addCommands(
      new Autonomous_AutoShoot_Command(2),
      new Autonomous_AutoDrive_Command(3.0, 0.2, 0.0),
      new Autonomous_AutoIntake_Command(2)
      );
  }
}


