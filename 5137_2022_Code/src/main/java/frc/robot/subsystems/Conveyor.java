// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
 
public class Conveyor extends SubsystemBase {
  /* Creates a new Conveyor. */
 
 
  private boolean ConveyorDirection;
  private boolean Conveyor_On;
 
  public Conveyor() {
    //add other motors under this constructor as needed
    MotorController WPI_TalonSRX;
    WPI_VictorSPX lstorageVictor;
    WPI_VictorSPX rstorageVictor;
    lstorageVictor = RobotContainer.lstorageVictor;
    rstorageVictor = RobotContainer.rstorageVictor;
    //pixy = RobotContainer.cartridgePixy;
}
 
 
  public boolean CheckConveyorDirection(){
    // if returns true then conveyor is moving forwards, if false then moving backwards or stopped
 
    return ConveyorDirection;
  }
 
  public void ConveyorSwitch(boolean Conveyor_On){
    //changes boolean Conveyor_On to true to start moving the conveyor or changes to false to stop conveyor
    if(Conveyor_On == false){
      Conveyor_On = true;
    }
    if(Conveyor_On == true){
      Conveyor_On = false;
    }
  }
 
  public void ReverseConveyor(boolean ConveyorDirection){
    //reverses conveyor (if forwards then makes it backwards and if backwards then makes it forwards)
    if(ConveyorDirection == true){
      ConveyorDirection = false;
    }
    if(ConveyorDirection == false){
      ConveyorDirection = true;
    }
  }
 
 
  /*from 2020 code
package frc.robot.subsystems;
 
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
 
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2CCC;
 
public class Storage_Subsystem extends SubsystemBase {
 
    Pixy2 pixy;
   
    WPI_VictorSPX lstorageVictor;
    WPI_VictorSPX rstorageVictor;
 
    boolean invertStore;
 
    public Storage_Subsystem() {
        lstorageVictor = RobotContainer.lstorageVictor;
        rstorageVictor = RobotContainer.rstorageVictor;
        pixy = RobotContainer.cartridgePixy;
    }
 
    public void store(boolean wantsOn, boolean shooting, boolean reversed, boolean reversedMatters, boolean wantsBottomBeltReversed) {
       
    if (wantsOn) { //l is top, r is bottom belts
        if(shooting) {
            rstorageVictor.set(Constants.storageSpeed);
        }
        else {
            rstorageVictor.set(0);
        }
        if(reversed && reversedMatters) {
            lstorageVictor.set(-Constants.storageSpeed);
        }
        else if (!reversed && reversedMatters) {
            lstorageVictor.set(Constants.storageSpeed);
        }
        else {
            //don't reset the top belt
        }
        if (wantsBottomBeltReversed) {
            rstorageVictor.set(-Constants.storageSpeed);
        }
       
       
    }
    else {
        lstorageVictor.set(0);
        rstorageVictor.set(0);
    }
 
    }
 
    public boolean checkIfFull() {
 
        if (pixy.getCCC().getBlocks(false, 1, 1) == 1) { //check if it sees the target (pink colored paper)
            return true;
        }
        else {
            return false;
        }
    }
}
  */
 
 
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

