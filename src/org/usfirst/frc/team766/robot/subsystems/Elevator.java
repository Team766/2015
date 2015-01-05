package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the elevator
 * 
 * TODO:
 * 	-Add presets to arm.
 *	@author Blevenson
 */
public class Elevator extends Subsystem {
    
	private Talon Elevator = new Talon(Ports.PWM_Elevators);
	
    public void initDefaultCommand() {}
    
    public void setElevatorSpeed(double speed)
    {
    	Elevator.set(speed);
    }
    public void setElevatorHeight(int height)
    {
    	//takes elevator height and sets the motors to get to that height.
    }
}

