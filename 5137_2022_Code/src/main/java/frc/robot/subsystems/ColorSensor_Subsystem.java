package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Sensor things
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.I2C;


public class ColorSensor_Subsystem extends SubsystemBase {
    public static String colorString;

    public static boolean checkConveyorEmpty(){
        if (colorString.equals("Ball Detected")){
            return false;
        }
        else {
            return true; //if something goes wrong it's probably because of this 
        }
    }

}

