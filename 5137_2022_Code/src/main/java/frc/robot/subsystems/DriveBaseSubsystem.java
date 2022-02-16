// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.ArcadeDrive;

public class DriveBaseSubsystem extends SubsystemBase {

  	DifferentialDrive CashwinsDifferentialDrive;

	MotorController leftBack;
	MotorController leftFront;
	MotorController rightBack;
	MotorController rightFront;

	MotorControllerGroup m_leftDrive;
	MotorControllerGroup m_rightDrive;

	Joystick XBoxController;

	double newDriveSpeed;
	double actualDriveSpeed;
	double previousDriveSpeed;

	XboxController driveController;

  /** Creates a new DriveBaseSubsystem. */
  public DriveBaseSubsystem() {
	instantiateMotors();
	createDifferentialDrive(m_leftDrive, m_rightDrive);

	newDriveSpeed = 0;
	actualDriveSpeed = 0;
	previousDriveSpeed = 0;
  }

  @Override
	public void periodic() {
		// This method will be called once per scheduler run
		//rampArcadeDrive(XBoxController);
	}

	public void instantiateMotors()
	{
		leftBack = new WPI_TalonFX(Constants.leftBackCAN);
		leftFront = new WPI_TalonFX(Constants.leftFrontCAN);
		rightBack = new WPI_TalonFX(Constants.rightBackCAN);
		rightFront = new WPI_TalonFX(Constants.rightFrontCAN);

		createMotorControllerGroup(leftBack, leftFront, rightBack, rightFront);
	}

	public void createMotorControllerGroup(MotorController leftBack, MotorController leftFront, 
	                                       MotorController rightBack, MotorController rightFront)
	{
		//m_leftDrive = new MotorControllerGroup(leftBack, leftFront);
		//m_rightDrive = new MotorControllerGroup(rightBack, rightFront);
	}

	public void createDifferentialDrive(MotorControllerGroup leftDrive, MotorControllerGroup rightDrive) 
	{
		//CashwinsDifferentialDrive = new DifferentialDrive(leftDrive, rightDrive);
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new ArcadeDrive());
	}

  public double adjustJoystickValue(double joystick, double deadZone) {
		double adjustedJoystick;
		if (Math.abs(joystick) < deadZone) {
			adjustedJoystick = 0;
		} else {
			adjustedJoystick = ((1 / (1 - deadZone)) * (joystick - deadZone));
		}
		return adjustedJoystick;
		// An algorithm developed by the fantastic Sarah C. Lincoln that adjusts the
		// joysticks
		// to run scaled to the deadZone
	}

  public void rampArcadeDrive(Joystick XBoxController) {
	//double driveValue = XBoxController.getRawAxis(Constants.LYStickAxisPort);
    //double turnValue = XBoxController.getRawAxis(Constants.RXStickAxisPort);
    //CashwinsDifferentialDrive.curvatureDrive(-driveValue / Constants.driveSensitivity, turnValue / Constants.turnSensitivity, Constants.isQuickTurn);
  }

  public void drivePivot(double speed) { // TODO may need to make this negative
		//CashwinsDifferentialDrive.arcadeDrive(0, speed);
	}

	public void driveStraight(double speed) {
		CashwinsDifferentialDrive.arcadeDrive(speed, 0);
	}

	public void stop() {
		//CashwinsDifferentialDrive.arcadeDrive(0, 0);
	}

}
