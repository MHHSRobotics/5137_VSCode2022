//create variables for motors
//toggle intake, checks wether we want on and turns on intake motors (also checks for reverse)
//intake balls: electrical stuff, sets the talons to whatever value, reverses if reverse is true 

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
//import io.github.pseudoresonance.pixy2api.Pixy2; 
//what does this do

//motor variables 

public class Intake_Subsystem extends SubsystemBase { 
    boolean intakeDown = false;
    boolean toggleIntakeDirection = false;


    public Intake_Subsystem() {
        //constructor 
        //gives motor varuable values 
    }
    
    @Override
    public void periodic() {

        
    }
    
    //checks whether intake needs to end (?)
    public void toggleIntake(boolean wantsOn, boolean reversed) {
        if (wantsOn) {

        }
        else {
            endIntake();
        }
    }
    
    //checks which direction wheels are running 
    public void intakeBalls(boolean reversed) {
        if (reversed) {
            //reverse the motor 
            //used when trying push balls out of the robot (outTake)
        }
        else {
            //intakeVictor.set(-Constants.intakeVictorOutput);
            //run motor forward
            //used when trying to intake balls
           
        }
    } 

    //switvhes motor off
    public void endIntake() {
        //switches motor off by setting it's value to zero
    }

    //autonomous maybe possibly hopefully 
    public void autoIntake() {
        //auto
    }
}

