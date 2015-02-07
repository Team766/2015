package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.Ports;
import org.usfirst.frc.team766.robot.RobotValues;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the elevator
 * 
 * TODO: -Add presets to arm.
 *
 * @author Blevenson
 * @author PKao
 */

public class Elevator extends Subsystem implements Runnable {
	private RobotValues.ElevatorState currentState;
	private int goal = 0;

	private Talon Elevator = new Talon(Ports.PWM_Elevators);
	private Encoder liftPos = new Encoder(Ports.DIO_ELEVATOR_ENCA,
			Ports.DIO_ELEVATOR_ENCB);

	private Thread changeLimiter = new Thread(this);
	private double targetSpeed = 0;
	private PIDController smoother = new PIDController(RobotValues.ElevatorKp,
			RobotValues.ElevatorKi, 0, targetSpeed);

	public void initDefaultCommand() {
		changeLimiter.start();
	}

	public void setElevatorSpeed(double speed) {
		smoother.setSetpoint(speed);
	}

	// public void setElevatorHeightWaypoint(int height) {
	// // takes elevator height and sets the motors to get to that height.
	// }
	//
	// public void setElevatorHeightFeet(double height){
	// //takes elevator height in feet and sets the location of the elevator to
	// that height.
	// }
	public void resetEnc() {
		liftPos.reset();
	}

	public double getEnc() {
		return liftPos.getDistance();
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public RobotValues.ElevatorState getState() {
		return currentState;
	}

	public void setState(RobotValues.ElevatorState currentState) {
		this.currentState = currentState;
	}

	public void run() {
		while (true) {
			smoother.calculate(Elevator.get());
			Elevator.set(smoother.getOutput());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
