package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.CounterBase;
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
	private static final double DISTANCE_PER_PULSE = (Math.PI * .09958)/256; //PI * Wheel Diameter / 256 * Encoder type
	
	private Victor leftDrive = new Victor(Ports.PWM_Left_Drive);
	private Victor rightDrive = new Victor(Ports.PWM_Right_Drive);

	private Encoder rightEncoder = new Encoder(Ports.DIO_RDriveEncA,
			Ports.DIO_RDriveEncB,false,CounterBase.EncodingType.k4X);
	private Encoder leftEncoder = new Encoder(Ports.DIO_LDriveEncA,
			Ports.DIO_LDriveEncB,false,CounterBase.EncodingType.k4X);

	private Solenoid Shifter = new Solenoid(Ports.Sol_Shifter);

	private Gyro gyro = new Gyro(Ports.GYRO);

	private PowerDistributionPanel PDP = new PowerDistributionPanel();

	private double leftTarget = 0;
	private double rightTarget = 0;
	private double rateOfChange = .05;//might need 2 variables
	private boolean smoothing = false;
	private DriveSmoother smoother;
	
	
	public Drive(){
		smoother = new DriveSmoother();
		smoother.start();
		rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
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
			leftTarget = -power;
		} else
			leftDrive.set(-power);
	}

	public synchronized void setRightPower(double power) {
		if (smoothing) {
			rightTarget = power;
		} else
			rightDrive.set(power);
	}
	
	private synchronized double getRightTarget(){ //Should I have these return Double.NaN if you aren't in smoothing mode? 
		return rightTarget;
	}
	
	private synchronized double getLeftTarget(){ //Should I have these return Double.NaN if you aren't in smoothing mode? 
		return leftTarget;
	}

	public void setHighGear(boolean isHighGear) {
		Shifter.set(isHighGear);
	}

	public double getLeftEncoderDistance() {
		return leftEncoder.getDistance();
	}

	public double getRightEncoderDistance() {
		return rightEncoder.getDistance();
	}
	
	public int getRawLeftEncoder(){
		return leftEncoder.getRaw();
	}
	
	public int getRawRightEncoder(){
		return rightEncoder.getRaw();
	}

	public double getAverageEncoderDistance() {
		return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0d;
	}

	public void resetEncoders() {
		rightEncoder.reset();
		leftEncoder.reset();
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
