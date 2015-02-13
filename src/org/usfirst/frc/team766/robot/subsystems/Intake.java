package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Intake subsystem. Includes all parts of the robot uses to suck a tote in.
 * 
 * @author Brett Levenson
 */

public class Intake extends Subsystem{

	private Victor leftIntake = new Victor(Ports.PWM_IntakeL);
	private Victor rightIntake = new Victor(Ports.PWM_IntakeR);
	
	private Victor rightWheel = new Victor(Ports.PWM_IntakeWheelR);
	private Victor leftWheel = new Victor(Ports.PWM_IntakeWheelL);

	private PowerDistributionPanel PDP = new PowerDistributionPanel();

	
	protected void initDefaultCommand() {
	}
	
	public void setLeftIntake(double in)
	{
		leftIntake.set(in);
	}
	
	public void setRightIntake(double in)
	{
		rightIntake.set(in);
	}
	public void setWheels(double in)
	{
		setLeftWheel(in);
		setRightWheel(in);
	}
	public void setLeftWheel(double in)
	{
		leftWheel.set(in);
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
}
	