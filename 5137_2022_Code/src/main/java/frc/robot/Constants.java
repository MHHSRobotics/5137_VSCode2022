// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int hangExtensionMotorPort = 0;
    public static final int hangPivotMotorPort = 0;
    public static final double hangExtensionSpeed = 0;
    public static final int assControllerPort = 0;
    public static final int assLYStickAxisPort = 0;
    public static final int assRXStickAxisPort = 0;
    public static final int LimitSwitchPivotDIOPort = 0;
    public static final int LimitSwitchExtendDIOPort = 0;
    public static final int hangExtensionMotor = 0;
    public static final int extensionHangCAN = 0;
    public static final float pivotReverseLimit = (float) 0.25;
    public static final float pivotForwardLimit = 0;
    public static final float extensionForwardLimit = 0; //Change value to however many rotations is concidered the limit 
    public static int pivotHangCAN;
}
