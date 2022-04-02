// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.util.Color;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //MOTORS HAVE NOT BEEN SET TO PROPER VALUES

    //Controllers
    public static final int driverControllerPort = 0; //White Knight
    public static final int assisControllerPort = 1; //Black Jack

    //Joysticks
    public static final int LXStickAxisPort = 0;
    public static final int LYStickAxisPort = 1;
    public static final int RXStickAxisPort = 4;
    public static final int RYStickAxisPort = 5;

    //Triggers
    public static final int RTAxisPort = 2;
    public static final int LTAxisPort = 3;

    //Buttons
    public final static int AButtonPort = 1;
    public final static int BButtonPort = 2;
    public final static int XButtonPort = 3;
    public final static int YButtonPort = 4;
    public final static int rightTriggerButton = 6;

    //D-Pad
    public static final int uDPadButtonValue = 0;
    public static final int rDPadButtonValue = 90;
    public static final int dDPadButtonValue = 180;

    //DriveBase
    public final static double driveSensitivity = 3.0;
    public final static double turnSensitivity = 4.5; //4.5 seems nice
    public final static boolean isQuickTurn = true; //makes turning the drive base able to override constant-curvature turning for turn-in-place maneuvers.
    
    public static final int leftFrontCAN = 1;
    public static final int leftBackCAN = 2; 
    public static final int rightFrontCAN = 5;
    public static final int rightBackCAN = 6;

    //Intake
    public static final int intakePort = 16;
    public static final double intakeSpeed = 0.4;

    //Conveyor
    public static final int vertConveyorPort = 13;
    public static final int horzConveyorPort = 9;
    public static final double conveyorSpeed = 0.4;

    //Shooter
    public static final int shooterId = 7;
    public static final int backSpinShooterId = 3;
    
    public static final double InitiationLineShooterPerc = 0.15;
    public static final double BackTrenchShooterPerc = 0.3;
    public static final double FrontTrenchShooterPerc = 0.25;
    public static final double maxPercShooter = 0.25;
    public static final double autonomousShooterPerc = 0.25;

    public static final double veloError = 400;
    public static final double shooterAngle = 0;
    
    //Hnag
    public static final int hangExtensionMotorPort = 4; // Might be switched
    public static final int hangPivotMotorPort = 8; // Might be switched

    public static final float pivotReverseLimit = (float) 0.25;
    public static final float pivotForwardLimit = 0;
    public static final float extensionForwardLimit = 1000; //Change value to however many rotations is concidered the limit   

    //Auto-mousse
    public static final String driveBack_Shoot = "drive_Shoot";
    public static final String shoot_DriveBack = "shoot_Drive";
    public static final String complex = "complex"; //Change this later

    //Color Sensor
    public static final I2C.Port i2cPort = I2C.Port.kOnboard;
    public static final Color paper = new Color(1, 1, 1);

     //LED
    public static final int LEDPort = 9;
    public static final int LEDLength = 10;
    

    // Path Weaver
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
    public static final double ksVolts = 1.8;
    public static final double kvVoltSecondsPerMeter = 1.3;
    public static final double kaVoltSecondsSquaredPerMeter = 0.2;
    public static final double kTrackwidthMeters = 0.65;
    public static final DifferentialDriveKinematics kDriveKinematics =
    new DifferentialDriveKinematics(kTrackwidthMeters);
    public static final double kPDriveVel = 1;
    public static final double DriveBaseWheelDiameterMeters = 0.152;
    public static final int DriveBaseEncoderCPR = 2048;
    public static final double EncoderDistancePerPulse = (Math.PI*DriveBaseWheelDiameterMeters) / ((double) DriveBaseEncoderCPR * 5.8);
    public static final double scalingFactor = 1/58.0;
}
