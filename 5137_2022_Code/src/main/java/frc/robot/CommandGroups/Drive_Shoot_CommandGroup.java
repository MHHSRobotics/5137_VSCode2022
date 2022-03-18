// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoShoot_Command;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoDrive_Command;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoIntakeDrive_Command;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoIntake_Command;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Drive_Shoot_CommandGroup extends SequentialCommandGroup {
  /** Creates a new DriveBack_Shoot_CommandGroup. */
  public Drive_Shoot_CommandGroup() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Autonomous_AutoDrive_Command(4.0, 0.7, 0.0),
      new Autonomous_AutoIntake_Command(1),
      new Autonomous_AutoIntakeDrive_Command(2.0, 0.7, 0.0),
      new Autonomous_AutoShoot_Command(5)
    );
  }
}
