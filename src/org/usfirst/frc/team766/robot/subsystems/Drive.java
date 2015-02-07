package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * Drive subsystem.
 * Includes all parts of the robot uses to drive.
 * 
 * @author Brett Levenson
 *
 */

public class Drive extends Subsystem{
	
	private Talon leftDrive = new Talon(Ports.PWM_Left_Drive);
	private Talon rightDrive = new Talon(Ports.PWM_Right_Drive);
	
	private Encoder rightEncoder = new Encoder(Ports.DIO_RDriveEncA, Ports.DIO_RDriveEncB);
	private Encoder leftEncoder = new Encoder(Ports.DIO_LDriveEncA, Ports.DIO_LDriveEncB);
	    
    private Solenoid Shifter = new Solenoid(Ports.Sol_Shifter);
    
    private Gyro gyro = new Gyro(Ports.GYRO);
    
    private PowerDistributionPanel PDP = new PowerDistributionPanel();
    
	protected void initDefaultCommand() {
	}
	/**
	 * Set power to both motors, 
	 * useful for shutting them off
	 * @param speed power value
	 */
	public void setPower(double speed){
		setLeftPower(speed);
		setRightPower(speed);
	}
	public void setLeftPower(double power){
		leftDrive.set(power);
	}
	public void setRightPower(double power){
		rightDrive.set(power);
	}
	public void setShifter(boolean highGear){
		Shifter.set(!highGear);
	}
	
	public double getLeftEncoderDistance() {
		return translateDrive(leftEncoder.getRaw());
	}
	public double getRightEncoderDistance() {
		return translateDrive(rightEncoder.getRaw());
	}
	public double getAverageEncoderDistance(){
		return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0d;
	}
	public void resetEncoders(){
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	public float translateDrive(float trans){
		double wheel_d = 0.0899;
		double counts = 256 * 4.0;
		return (float) ((trans / counts) * (Math.PI) * wheel_d);
	}

	public double getVoltage()
	{
		return PDP.getVoltage();
	}
	public double getTemp()
	{
		return PDP.getTemperature();
	}
	
	//Gyro
	public void resetGyro()
	{
		gyro.reset();
	}
	public double getAngle()
	{
		return gyro.getAngle();
	}

}
