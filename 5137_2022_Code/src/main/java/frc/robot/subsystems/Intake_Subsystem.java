//create variables for motors
//toggle intake, checks wether we want on and turns on intake motors (also checks for reverse)
//intake balls: electrical stuff, sets the talons to whatever value, reverses if reverse is true 

package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
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


    public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakePort, MotorType.kBrushless);


    public Intake_Subsystem() {
        //constructor 
        //gives motor variable values 
    }

    
    @Override
    public void periodic() {

        
    }
    
    //checks whether intake needs to end (?)
    public void toggleIntake(boolean wantsOn, boolean reversed) {
        if (wantsOn) {
            if (reversed){
                intakeBallsOut();
            }
            else{
                intakeBallsIn();
            }
        }
        else {
            endIntake();
        }
    }
    
    //picks up balls from field into robot
    public void intakeBallsIn() {
        intakeMotor.set(Constants.intakeSpeed);
    } 

    //reverses intake to push balls out
    public void intakeBallsOut() {
        intakeMotor.set(Constants.intakeSpeed * -1);
    }

    //switches motor off
    public void endIntake() {
        //switches motor off by setting it's value to zero
        intakeMotor.stopMotor();
    }

    //autonomous maybe possibly hopefully 
    public void autoIntake() {
        //auto
    }
}

