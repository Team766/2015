package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	Subsytem to control the grabbing arms put on from team 1678 during SVR
 */
public class ContainerGrabber extends Subsystem {
    
	private boolean speedBoost = false;
	
	private Victor rightControl = new Victor(Ports.PWM_GrabberR);
	private Victor leftControl = new Victor(Ports.PWM_GrabberL);
	
	protected void initDefaultCommand() {
	}
	
	
	public void setLeftArm(double s)
	{
		leftControl.set(s);
	}
	public void setRightArm(double s)
	{
		rightControl.set(s);
	}
	public void setArms(double s)
	{
		setLeftArm(s);
		setRightArm(s);
	}
	
	public double getLeftArm()
	{
		return leftControl.get();
	}
	public double getRightArm()
	{
		return rightControl.get();
	}
	
	public boolean getSpeeedBoost()
	{
		return speedBoost;
	}

	public void setSpeedBoost(boolean s)
	{
		speedBoost = s;
	}
	
}

