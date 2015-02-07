package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.Ports;
import org.usfirst.frc.team766.robot.RobotValues;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive subsystem. Includes all parts of the robot uses to drive.
 * 
 * @author Brett Levenson
 * @author PKao
 */

public class Drive extends Subsystem implements Runnable {

	private Talon leftDrive = new Talon(Ports.PWM_Left_Drive);
	private Talon rightDrive = new Talon(Ports.PWM_Right_Drive);

	private Encoder rightEncoder = new Encoder(Ports.DIO_RDriveEncA,
			Ports.DIO_RDriveEncB);
	private Encoder leftEncoder = new Encoder(Ports.DIO_LDriveEncA,
			Ports.DIO_LDriveEncB);

	private Solenoid Shifter = new Solenoid(Ports.Sol_Shifter);

	private Gyro gyro = new Gyro(Ports.GYRO);

	private PowerDistributionPanel PDP = new PowerDistributionPanel();

	// Should I use arrays?
	private Thread changeLimiter = new Thread(this);
	private double leftTargetSpeed = 0;
	private double rightTargetSpeed = 0;
	private PIDController leftSmoother = new PIDController(
			RobotValues.SmootherLeftKp, RobotValues.SmootherLeftKi, 0,
			leftTargetSpeed); // Add Better PID Constants P
	private PIDController rightSmoother = new PIDController(
			RobotValues.SmootherRightKp, RobotValues.SmootherRightKi, 0,
			rightTargetSpeed); // Add Better PID Constants
	private boolean smoothing = true;

	protected void initDefaultCommand() {
		changeLimiter.start();
	}

	/**
	 * Set power to both motors, useful for shutting them off
	 * 
	 * @param power
	 *            power value
	 */
	public void setPower(double power) {
		if (smoothing) {
			leftSmoother.setSetpoint(power);
			rightSmoother.setSetpoint(power);
		} else {
			leftDrive.set(power);
			rightDrive.set(power);
		}
	}

	public void setLeftPower(double power) {
		if (smoothing) {
			leftSmoother.setSetpoint(power);
		} else
			leftDrive.set(power);
	}

	public void setRightPower(double power) {
		if (smoothing) {
			rightSmoother.setSetpoint(power);
		} else
			rightDrive.set(power);
	}

	public void setShifter(boolean highGear) {
		Shifter.set(!highGear);
	}

	public double getLeftEncoderDistance() {
		return translateDrive(leftEncoder.getRaw());
	}

	public double getRightEncoderDistance() {
		return translateDrive(rightEncoder.getRaw());
	}

	public double getAverageEncoderDistance() {
		return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0d;
	}

	public void resetEncoders() {
		rightEncoder.reset();
		leftEncoder.reset();
	}

	public float translateDrive(float trans) {
		double wheel_d = 0.0899;
		double counts = 256 * 4.0;
		return (float) ((trans / counts) * (Math.PI) * wheel_d);
	}

	public double getVoltage() {
		return PDP.getVoltage();
	}

	public double getTemp() {
		return PDP.getTemperature();
	}

	// Gyro
	public void resetGyro() {
		gyro.reset();
	}

	public double getAngle() {
		return gyro.getAngle();
	}

	public boolean getSmoothing() {
		return smoothing;
	}

	public void setSmoothing(boolean setSmooth) {
		smoothing = setSmooth;
	}

	public void run() {
		while (true) {
			if (smoothing) {
				leftSmoother.calculate(leftDrive.get());
				rightSmoother.calculate(rightDrive.get());
				leftDrive.set(leftSmoother.getOutput());
				rightDrive.set(rightSmoother.getOutput());
			}
			try {
				Thread.sleep(10);// Sleep time should be tuned
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
