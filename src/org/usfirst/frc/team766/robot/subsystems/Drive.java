package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive subsystem. Includes all parts of the robot uses to drive.
 * 
 * @author Brett Levenson
 * @author PKao
 */

public class Drive extends Subsystem{

	private Victor leftDrive = new Victor(Ports.PWM_Left_Drive);
	private Victor rightDrive = new Victor(Ports.PWM_Right_Drive);

	private Encoder rightEncoder = new Encoder(Ports.DIO_RDriveEncA,
			Ports.DIO_RDriveEncB);
	private Encoder leftEncoder = new Encoder(Ports.DIO_LDriveEncA,
			Ports.DIO_LDriveEncB);

	private Solenoid Shifter = new Solenoid(Ports.Sol_Shifter);

	private Gyro gyro = new Gyro(Ports.GYRO);

	private PowerDistributionPanel PDP = new PowerDistributionPanel();

	private double leftTarget = 0;
	private double rightTarget = 0;
	private double rateOfChange = .05;//might need 2 variables
	private boolean smoothing = true;
	private DriveSmoother smoother;
	
	
	public Drive(){
		smoother = new DriveSmoother();
		smoother.start();
	}
	
	protected void initDefaultCommand() {
	}

	/**
	 * Set power to both motors, useful for shutting them off
	 * 
	 * @param power
	 *            power value
	 */
	
	//For set methods, to set raw you need to not be in smoothing mode
	public synchronized void setPower(double power) {
		if (smoothing) {
			leftTarget = power;
			rightTarget = power;
		} else {
			leftDrive.set(power);
			rightDrive.set(power);
		}
	}
	
	public synchronized void setLeftPower(double power) {
		if (smoothing) {
			leftTarget = power;
		} else
			leftDrive.set(power);
	}

	public synchronized void setRightPower(double power) {
		if (smoothing) {
			rightTarget = -power;
		} else
			rightDrive.set(-power);
	}
	
	private synchronized double getRightTarget(){ //Should I have these return Double.NaN if you aren't in smoothing mode? 
		return rightTarget;
	}
	
	private synchronized double getLeftTarget(){ //Should I have these return Double.NaN if you aren't in smoothing mode? 
		return leftTarget;
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
		double counts = 256d * 4.0;
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
	
	private class DriveSmoother extends Command{
		private double lastRightOut,lastLeftOut; //Do not use variable directly. Use getters and setters to avoid conflict.
		
		@Override
		protected void initialize() {
			// TODO Auto-generated method stub			
		}

		@Override
		protected void execute() {
			double outputLeft = rateOfChange  * lastLeftOut + (1 - rateOfChange ) * getLeftTarget();
			double outputRight = rateOfChange  * lastRightOut + (1 - rateOfChange ) * getRightTarget();
			
			rightDrive.set(outputRight);
			leftDrive.set(outputLeft);
			
			lastRightOut = outputRight;
			lastLeftOut = outputLeft;
		}

		@Override
		protected boolean isFinished() {
		
			return false;
		}

		@Override
		protected void end() {
		
			
		}

		@Override
		protected void interrupted() {
		
			
		}
		
	}
	
}
