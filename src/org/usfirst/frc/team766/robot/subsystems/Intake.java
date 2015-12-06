package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake subsystem. Includes all parts of the robot uses to suck a tote in.
 * 
 * @author Brett Levenson
 */

public class Intake extends Subsystem{
	private static final double MAX_OUTPUT = .3;
	private static final double MIN_OUTPUT = -.3;
	
//	private Victor leftIntake = new Victor(Ports.PWM_IntakeL);
//	private Victor rightIntake = new Victor(Ports.PWM_IntakeR);
	private Solenoid leftArm = new Solenoid(Ports.Sol_leftArm);
	private Solenoid rightArm = new Solenoid(Ports.Sol_rightArm);
	
	private Victor rightWheel = new Victor(Ports.PWM_IntakeWheelR);
	private Victor leftWheel = new Victor(Ports.PWM_IntakeWheelL);
	
	private Encoder leftEnc = new Encoder(Ports.DIO_LIntakeEncA, Ports.DIO_LIntakeEncB);
	private Encoder rightEnc = new Encoder(Ports.DIO_RIntakeEncA, Ports.DIO_RIntakeEncB);

	private PowerDistributionPanel PDP = new PowerDistributionPanel();

	
	protected void initDefaultCommand() {
	}
	
	@Deprecated
	public void setLeftIntake(double in)
	{
		if(in>MAX_OUTPUT) in = MAX_OUTPUT;
		if(in<MIN_OUTPUT) in = MIN_OUTPUT;
//		leftIntake.set(in);
	}
	@Deprecated
	public void setRightIntake(double in)
	{
		if(in>MAX_OUTPUT) in = MAX_OUTPUT;
		if(in<MIN_OUTPUT) in = MIN_OUTPUT;
//		rightIntake.set(in);
	}
	
	public void setLeftArm(boolean close)
	{
		leftArm.set(close);
	}
	public void setRightArm(boolean close)
	{
		rightArm.set(close);
	}
	
	public boolean getLeftArm()
	{
		return leftArm.get();
	}
	public boolean getRightArm()
	{
		return rightArm.get();
	}
	
	@Deprecated
	public double getLeftIntake()
	{
		return 0;
//		return leftIntake.get();
	}
	@Deprecated
	public double getRightIntake(){
		return 0;
//		return rightIntake.get();
	}
	public void setWheels(double in)
	{
		setLeftWheel(in);
		setRightWheel(in);
	}
	public void setLeftWheel(double in)
	{
		leftWheel.set(-in);
	}
	
	public void setRightWheel(double in)
	{
		rightWheel.set(in);
	}
	
	public double getIntakeCurrentRight()
	{
		return PDP.getCurrent(14);
	}
	public double getIntakeCurrentLeft()
	{
		return PDP.getCurrent(15);
	}
	
	public double getEncLeft()
	{
		return leftEnc.get();
	}
	public double getEncRight()
	{
		return rightEnc.get();
	}
	@Deprecated
	public void setIntake(double in)
	{
		setLeftIntake(in);
		setRightIntake(in);
	}
	public void resetEnc()
	{
		rightEnc.reset();
		leftEnc.reset();
	}

	public String getWheelSpeed() {
		return leftWheel.get() + "\t" + rightWheel.get();
	}
	
}
	
