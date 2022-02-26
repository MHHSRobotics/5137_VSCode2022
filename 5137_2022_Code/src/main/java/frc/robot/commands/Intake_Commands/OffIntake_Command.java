//also command scheduler stuff 
// but sets all values to false because motors arent running

package frc.robot.commands.Intake_Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class OffIntake_Command extends CommandBase{
    public OffIntake_Command() {
        addRequirements(RobotContainer.intake_Subsystem);
    }

    //Called when the command is initially scheduled
    @Override
    public void initialize() {
    }

    //Called every time the scheduler runs while the command is scheduled
    @Override
    public void execute() {
        RobotContainer.intake_Subsystem.toggleIntake(false, false); //motors are off and reverse is false
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.intake_Subsystem.toggleIntake(false, false); //motors are off and reverse false 
    }

    @Override
    public boolean isFinished() {
        return true; //maybe change to false to keep going like drivebase 
    }
}
