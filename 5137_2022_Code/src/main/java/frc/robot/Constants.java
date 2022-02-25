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


    public static final int shooterId = 8;
    public static final int backSpinShooterId = 9; //change later

    public static final double uDPadButtonValue = 0.4;
    public static final double rDPadButtonValue = 0.6;
    public static final double dDPadButtonValue = 0.8;
    public static final double InitiationLineShooterPerc = 0;
    public static final int maxPercShooter = 1;
    public static final double BackTrenchShooterPerc = 0;
    public static final double FrontTrenchShooterPerc = 0;
    public static final int LTAxisPort = 3;
    public static final double veloError = 0;
    public static final double shooterAngle = 0;
    public static final int RTAxisPort = 2;

    public final static double driveSensitivity = 3.0; //bigger # means less sensitivity, from 0.5 to 2.0
    
    //10.0: baby speed, 9.0: tdddler mode, 7.0: fast toddler mode, 5.0: optimal turn speed, 4.5:
    public final static double turnSensitivity = 5.0; //4.5 seems nice
    public final static boolean isQuickTurn = true; //makes turning the drive base able to override constant-curvature turning for turn-in-place maneuvers.

    public static final int leftBackCAN = 1; 
    public static final int leftFrontCAN = 2;
    public static final int rightBackCAN = 5;
    public static final int rightFrontCAN = 6;

    // Controller Constants
    public static final int LYStickAxisPort = 1;
    public static final int RXStickAxisPort = 4;
    public static final int portForDrive = 0;

    //Xbox button ports 
    public final static int AButtonPort = 1;
    public final static int BButtonPort = 2;
    public final static int XButtonPort = 3;
    public final static int YButtonPort = 4;
    public final static int RightButtonPort = 11;
    

    //Motors have not been set to proper values 
    public static final int intakePort = 7;
    public static final double intakeSpeed = 0.3;
    
    public static final int assisControllerPort = 1;
    public static final int driverControllerPort = 0;
    public static final int horzConveyorPort = 11;
    public static final int vertConveyorPort = 10;
    public static final double conveyorSpeed = 0.3;

    public static final int hangExtensionMotorPort = 1;
    public static final int hangPivotMotorPort = 0;
    //public static final int assisControllerPort = 1;
    //public static final int driveControllerPort = 0;
    //public static final int LYStickAxisPort = 2;
    //public static final int RXStickAxisPort = 3;
    public static final int RXAxisPort = 0;
    public static final int RYAxisPort = 1;
    public static final float pivotReverseLimit = (float) 0.25;
    public static final float pivotForwardLimit = 0;
    public static final float extensionForwardLimit = 1000; //Change value to however many rotations is concidered the limit 
    

}
