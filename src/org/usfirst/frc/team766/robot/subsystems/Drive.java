package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

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
    
    private Solenoid Shifter = new Solenoid(Ports.Sol_Shifter);
    
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
		leftDrive.set(-power);
	}
	public void setRightPower(double power){
		rightDrive.set(power);
	}
	public void setShifter(boolean highGear){
		Shifter.set(!highGear);
	}
}
