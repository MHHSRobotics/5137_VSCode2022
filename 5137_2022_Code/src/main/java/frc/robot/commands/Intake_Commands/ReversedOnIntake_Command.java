//same as on intake but reverse is true 

package frc.robot.commands.Intake_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ReversedOnIntake_Command extends CommandBase{
    public ReversedOnIntake_Command() {
        addRequirements(RobotContainer.intake_Subsystem);
    }

    //Called when the command is initially scheduled
    @Override
    public void initialize() {
    }

    //Called every time the scheduler runs while the command is scheduled
    @Override
    public void execute() {
        RobotContainer.intake_Subsystem.intakeBallsOut(); //motors are on and reversed is true 
    }

    @Override
    public void end(boolean interrupted) {
        //RobotContainer.intake_Subsystem.toggleIntake(true, true); //motors are on and reversed is true 
    }

    @Override
    public boolean isFinished() {
        return true; //maybe change to false to keep going like drivebase 
    }
}
