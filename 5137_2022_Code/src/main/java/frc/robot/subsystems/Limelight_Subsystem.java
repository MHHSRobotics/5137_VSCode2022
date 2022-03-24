// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Limelight_Subsystem extends SubsystemBase {
  /** Creates a new Limelight_Subsystem. */
  public Limelight_Subsystem() {

    float Kp = -0.1f;
    float min_command = 0.05f;
    
    double table = NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").getDouble(0);
    Object std;
    std::shared_ptr<NetworkTable>:: table = NetworkTable::GetTable("limelight");
    //std::shared_ptr<NetworkTable> table = NetworkTable::GetTable("limelight");
    //line above doesn't work but line 19 does the same thing without the error

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);

    float tx = table->GetNumber("tx");

    if (joystick->GetRawButton(9))
{
        float heading_error = -tx;
        float steering_adjust = 0.0f;
        if (tx > 1.0)
        {
                steering_adjust = Kp*heading_error - min_command;
        }
        else if (tx < 1.0)
        {
                steering_adjust = Kp*heading_error + min_command;
        }
        float left_command = steering_adjust;
        float right_command = steering_adjust;
        



        float tv = table->GetNumber("tv");
        float tx = table->GetNumber("tx");

        float steering_adjust = 0.0f;
        if (tv == 0.0f)
        {
                // We don't see the target, seek for the target by spinning in place at a safe speed.
                steering_adjust = 0.3f;
        }
        else
        {
                // We do see the target, execute aiming code
                float heading_error = tx;
                steering_adjust = Kp * tx;
        }

        left_command+=steering_adjust;
        right_command-=steering_adjust;

        CashwinsDifferentialDrive.tankDriveSet(left_command,right_command); //fill in right values for speed and pivot
}
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
