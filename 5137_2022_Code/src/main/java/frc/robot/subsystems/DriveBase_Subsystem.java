// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.Drivebase_Commands.ArcadeDrive_Command;

public class DriveBase_Subsystem extends SubsystemBase {

  	DifferentialDrive CashwinsDifferentialDrive;

	MotorController leftBack;
	MotorController leftFront;
	MotorController rightBack;
	MotorController rightFront;

	public MotorControllerGroup m_leftDrive;
	public MotorControllerGroup m_rightDrive;

	Joystick driveController;

	double newDriveSpeed;
	double actualDriveSpeed;
	double previousDriveSpeed;

	SlewRateLimiter rateLimiter;

  /** Creates a new DriveBaseSubsystem. */
  public DriveBase_Subsystem() {
	instantiateMotors();
	createDifferentialDrive(m_leftDrive, m_rightDrive);
	driveController = RobotContainer.driverController;
	CashwinsDifferentialDrive.setMaxOutput(0.4);
	rateLimiter = new SlewRateLimiter(0.8);
  }

  @Override
	public void periodic() {
		// This method will be called once per scheduler run
		rampArcadeDrive(driveController);
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
		m_leftDrive = new MotorControllerGroup(leftBack, leftFront);
		m_rightDrive = new MotorControllerGroup(rightBack, rightFront);
		m_rightDrive.setInverted(true);
	}

	public void createDifferentialDrive(MotorControllerGroup leftDrive, MotorControllerGroup rightDrive) 
	{
		CashwinsDifferentialDrive = new DifferentialDrive(leftDrive, rightDrive);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive_Command());
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

  public void rampArcadeDrive(Joystick driverController) {
	double driveValue = driverController.getRawAxis(Constants.LYStickAxisPort);
    double turnValue = driverController.getRawAxis(Constants.RXStickAxisPort);

	double rateLimitedDriveValue = rateLimiter.calculate(driveValue);
    CashwinsDifferentialDrive.curvatureDrive(-rateLimitedDriveValue, -turnValue, Constants.isQuickTurn);
	//System.out.println("Left side going at: " + m_leftDrive.get());
	//System.out.println("Right side going at: " + m_rightDrive.get());
  }
  public void drivePivot(double speed) { // TODO may need to make this negative
		CashwinsDifferentialDrive.arcadeDrive(0, speed);
	}

	public void drive(double speed, double pivot) {
		CashwinsDifferentialDrive.arcadeDrive(speed, pivot);
	}

	public void stop() {
		CashwinsDifferentialDrive.arcadeDrive(0, 0);
	}

	public void tankDriveSet(double leftSpeed, double rightSpeed){
		CashwinsDifferentialDrive.tankDrive(leftSpeed, rightSpeed);
	}

}
