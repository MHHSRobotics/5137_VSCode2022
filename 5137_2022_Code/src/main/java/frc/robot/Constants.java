// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

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

    //D-Pad
    public static final int uDPadButtonValue = 0;
    public static final int rDPadButtonValue = 90;
    public static final int dDPadButtonValue = 180;

    //DriveBase
    public final static double driveSensitivity = 3.0;
    public final static double turnSensitivity = 4.5; //4.5 seems nice
    public final static boolean isQuickTurn = true; //makes turning the drive base able to override constant-curvature turning for turn-in-place maneuvers.
    
    public static final int leftBackCAN = 1; 
    public static final int leftFrontCAN = 2;
    public static final int rightBackCAN = 5;
    public static final int rightFrontCAN = 6;

    //Intake
    public static final int intakePort = 7; //Need to Change
    public static final double intakeSpeed = 0.3;

    //Conveyor
    public static final int horzConveyorPort = 10;
    public static final int vertConveyorPort = 11;
    public static final double conveyorSpeed = 0.1;

    //Shooter
    public static final int shooterId = 8;
    public static final int backSpinShooterId = 9; //Change Later
    
    public static final double InitiationLineShooterPerc = 0.4;
    public static final double BackTrenchShooterPerc = 0.6;
    public static final double FrontTrenchShooterPerc = 0.8;
    public static final int maxPercShooter = 1;

    public static final double veloError = 0;
    public static final double shooterAngle = 0;
    
    //Hnag
    public static final int hangExtensionMotorPort = 0;
    public static final int hangPivotMotorPort = 1;

    public static final float pivotReverseLimit = (float) 0.25;
    public static final float pivotForwardLimit = 0;
    public static final float extensionForwardLimit = 1000; //Change value to however many rotations is concidered the limit   

    //Auto-mousse 
    public static final String autoSelection = "shoot_DriveBack";
    public static final String driveBack_Shoot = "driveBack_Shoot";
    public static final String shoot_DriveBack = "shoot_DriveBack";
}
