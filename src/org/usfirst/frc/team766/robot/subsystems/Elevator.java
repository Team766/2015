package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the elevator
 * 
 * TODO: -Add presets to arm.
 *
 * @author Blevenson
 */
public class Elevator extends Subsystem {

	private Talon Elevator = new Talon(Ports.PWM_Elevators);
	private Servo servoRotate = new Servo(3);
	private Servo servoRoll = new Servo(4);

	public void initDefaultCommand() {
	}

	public void setElevatorSpeed(double speed) {
		Elevator.set(speed);
	}

	public void setElevatorHeightWaypoint(int height) {
		// takes elevator height and sets the motors to get to that height.
	}

	public void setElevatorHeightFeet(double height){
		//takes elevator height in feet and sets the location of the elevator to that height.
	}
	
	public void rotateCamera(double loc)
	{
		servoRotate.set(loc);
	}
	public void rollCamera(double loc)
	{
		servoRoll.set(loc);
	}
}
