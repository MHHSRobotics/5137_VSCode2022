// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.RobotState;
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

	WPI_TalonFX rightFront;
	WPI_TalonFX rightBack;
	WPI_TalonFX leftFront;
	WPI_TalonFX leftBack;

	public MotorControllerGroup m_leftDrive;
	public MotorControllerGroup m_rightDrive;

	Joystick driveController;

	double newDriveSpeed;
	double actualDriveSpeed;
	double previousDriveSpeed;

	SlewRateLimiter forewardRateLimiter;
	SlewRateLimiter turnRateLimiter;

	ADXRS450_Gyro drive_gyro;
	private final DifferentialDriveOdometry m_odometry;
	Encoder leftEncoder;


  /** Creates a new DriveBaseSubsystem. */
  public DriveBase_Subsystem() {
	instantiateMotors();
	createDifferentialDrive(m_leftDrive, m_rightDrive);
	driveController = RobotContainer.driverController;
	CashwinsDifferentialDrive.setMaxOutput(0.4);
	forewardRateLimiter = new SlewRateLimiter(3);
	//turnRateLimiter = new SlewRateLimiter(3.5);
	drive_gyro = new ADXRS450_Gyro();
	drive_gyro.calibrate();
	m_odometry = new DifferentialDriveOdometry(getRotation2d());
	rightBack.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
	leftBack.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
	resetEncoders();
  }

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
		rampArcadeDrive(driveController);
		m_odometry.update(
			getRotation2d(), -leftBack.getSelectedSensorPosition() 
							* Constants.EncoderDistancePerPulse, 
							 rightBack.getSelectedSensorPosition() 
							* Constants.EncoderDistancePerPulse);
		System.out.println("Left at " + -leftBack.getSelectedSensorPosition() 
		* Constants.EncoderDistancePerPulse);
		System.out.println("Right at " + rightBack.getSelectedSensorPosition() 
		* Constants.EncoderDistancePerPulse);
	}

	public void instantiateMotors()
	{
		rightFront = new WPI_TalonFX(Constants.leftBackCAN);
		rightBack = new WPI_TalonFX(Constants.leftFrontCAN);
		leftFront = new WPI_TalonFX(Constants.rightBackCAN);
		leftBack = new WPI_TalonFX(Constants.rightFrontCAN);

		createMotorControllerGroup(rightFront, rightBack, leftFront, leftBack);
	}

	public void createMotorControllerGroup(MotorController leftBack, MotorController leftFront, 
	                                       MotorController rightBack, MotorController rightFront)
	{
		m_leftDrive = new MotorControllerGroup(leftBack, leftFront);
		m_rightDrive = new MotorControllerGroup(rightBack, rightFront);
		m_leftDrive.setInverted(true);
	}

	public void createDifferentialDrive(MotorControllerGroup leftDrive, MotorControllerGroup rightDrive) 
	{
		CashwinsDifferentialDrive = new DifferentialDrive(leftDrive, rightDrive);
	}

	public void initDefaultCommand() {
		if (RobotState.isTeleop()){
			setDefaultCommand(new ArcadeDrive_Command());
		}
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
	boolean shootForward = driverController.getRawButton(Constants.rightTriggerButton);

	double rateLimitedDriveValue = forewardRateLimiter.calculate(driveValue);
	//double rateLimitedTurnValue = turnRateLimiter.calculate(turnValue);
	if (RobotState.isTeleop()){
		if (shootForward){
			CashwinsDifferentialDrive.curvatureDrive(-rateLimitedDriveValue, turnValue, Constants.isQuickTurn);
		}
		else{
			CashwinsDifferentialDrive.curvatureDrive(rateLimitedDriveValue, turnValue, Constants.isQuickTurn);
		}
	}
	//System.out.println("Left side going at: " + m_leftDrive.get());
	//System.out.println("Right side going at: " + m_rightDrive.get());
  }
  public void drivePivot(double speed) { // TODO may need to make this negative
		CashwinsDifferentialDrive.arcadeDrive(0, speed);
	}

	public void drive(double speed, double pivot) {
		CashwinsDifferentialDrive.curvatureDrive(speed, pivot, false);
	}
	public void driveStraight(double speed){
		CashwinsDifferentialDrive.arcadeDrive(speed, 0);
	}

	public void stop() {
		CashwinsDifferentialDrive.arcadeDrive(0, 0);
	}

	public void lockWheels() {
		CashwinsDifferentialDrive.stopMotor();
	}

	private Rotation2d getRotation2d() {
		return Rotation2d.fromDegrees(getHeading());
	}

	public Pose2d getPose() {
		return m_odometry.getPoseMeters();
	}

	private void resetEncoders() {
		rightBack.setSelectedSensorPosition(0);
		leftBack.setSelectedSensorPosition(0);
	}

	public void resetOdometry(Pose2d pose) {
		resetEncoders();
		m_odometry.resetPosition(pose, getRotation2d());
	}

	public double getHeading() {
		return drive_gyro.getRotation2d().getDegrees();
	}

	public ADXRS450_Gyro getGyro(){
		return drive_gyro;
	}

	public DifferentialDriveWheelSpeeds getWheelSpeeds() {
		// Selected sensor velocity return meters per 100 ms so multiply by 10/10
		return new DifferentialDriveWheelSpeeds(-leftBack.getSelectedSensorVelocity() * 10 * Constants.EncoderDistancePerPulse,   
												 rightBack.getSelectedSensorVelocity() * 10 * Constants.EncoderDistancePerPulse); 
			
	}

	public void tankDriveVolts(double leftVolts, double rightVolts) {
		var batteryVoltage = RobotController.getBatteryVoltage();
    	if (Math.max(Math.abs(leftVolts), Math.abs(rightVolts)) > batteryVoltage) {
      		//leftVolts *= batteryVoltage / 12.0;
			  //rightVolts *= batteryVoltage / 12.0;
			leftVolts = leftVolts/12;
			rightVolts = rightVolts/12;
		}
		
		//leftVolts = Math.abs(leftVolts);
		//rightVolts = Math.abs(rightVolts);
		

		//leftVolts = leftVolts / Constants.scalingFactor;
		//rightVolts = rightVolts / Constants.scalingFactor;
		System.out.println("Tank drive volts: " + leftVolts + " : " + rightVolts 
							+ " Battery: " + batteryVoltage);

		m_leftDrive.setVoltage(-leftVolts);
		m_rightDrive.setVoltage(-rightVolts);
	}

}
