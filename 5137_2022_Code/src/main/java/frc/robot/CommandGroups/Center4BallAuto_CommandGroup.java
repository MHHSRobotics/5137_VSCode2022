// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.CommandGroups;

import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoIntakeDrive_Command;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoIntake_Command;
import frc.robot.commands.Autonomous_Commands.Autonomous_AutoShoot_Command;
import frc.robot.commands.Conveyor_Commands.RunHorzConveyorForward_Command;
import frc.robot.commands.Intake_Commands.OnIntake_Command;
import frc.robot.subsystems.DriveBase_Subsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Center4BallAuto_CommandGroup extends SequentialCommandGroup {
  /** Creates a new Center4BallAuto_CommandGroup. */
  DriveBase_Subsystem driveBase_Subsystem;

  public Center4BallAuto_CommandGroup() {
    driveBase_Subsystem = RobotContainer.driveBase_Subsystem;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Autonomous_AutoIntakeDrive_Command(1.5, -0.7, 0.0),
      new Autonomous_AutoIntakeDrive_Command(1, 0.7, 0.0),
      new Autonomous_AutoShoot_Command(5),
      //new Autonomous_AutoIntakeDrive_Command(4, -0.99, -0.1),
      runPathWeaver("CenterToPort"),
      new Autonomous_AutoIntakeDrive_Command(4, 0.9, 0.0),
      //runPathWeaver("CenterToHumanPlayer"),
      //new Autonomous_AutoIntake_Command(2),
      //runPathWeaver("CenterDriveBack"),
      new Autonomous_AutoShoot_Command(5)
    );
  }

  public RamseteCommand runPathWeaver(String file){
    Trajectory trajectory = getTrajectoryFromJSON(file);
    driveBase_Subsystem.resetOdometry(trajectory.getInitialPose());
    RamseteCommand ramseteCommand =
        new RamseteCommand(
            trajectory,
            driveBase_Subsystem::getPose,
            new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
            new SimpleMotorFeedforward(
                Constants.ksVolts,
                Constants.kvVoltSecondsPerMeter,
                Constants.kaVoltSecondsSquaredPerMeter),
            Constants.kDriveKinematics,
            driveBase_Subsystem::getWheelSpeeds,
            new PIDController(Constants.kPDriveVel, 0, 0),
            new PIDController(Constants.kPDriveVel, 0, 0),
            // RamseteCommand passes volts to the callback
            driveBase_Subsystem::tankDriveVolts,
            driveBase_Subsystem);
    driveBase_Subsystem.resetOdometry(trajectory.getInitialPose());
    //ramseteCommand.raceWith(new OnIntake_Command(), new RunHorzConveyorForward_Command());
    
    return ramseteCommand;
  }

  public Trajectory getTrajectoryFromJSON(String file){
    String trajectoryJSON = "Paths/" + file + ".wpilib.json";    
    Trajectory trajectory = new Trajectory();
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
    return trajectory;
  }
}
