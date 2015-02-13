package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.Ports;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
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
	private double stopTolerance = 0.5;
	
	private RobotValues.ElevatorState currentState;
	private int goal = 0;

	private Victor Elevator = new Victor(Ports.PWM_Elevators);
	private Encoder liftPos = new Encoder(Ports.DIO_ELEVATOR_ENCA,
			Ports.DIO_ELEVATOR_ENCB);
	private Solenoid brake = new Solenoid(Ports.Sol_ElevBrake);

	private Thread changeLimiter = new Thread(this);
	private double targetSpeed = 0;
	private PIDController smoother = new PIDController(RobotValues.ElevatorKp,
			RobotValues.ElevatorKi, 0, targetSpeed);

	public void initDefaultCommand() {
		changeLimiter.start();
	}

	public void setElevatorSpeed(double speed) {
		if(Math.abs(speed) <= stopTolerance)
			setBrake(true);
		else
			setBrake(false);
		smoother.setSetpoint(speed);
	}

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
	
	public void setBrake(boolean stop)
	{
		brake.set(!stop);
	}

	public void run() {
		while (true) {
			smoother.calculate(Elevator.get(), false);
			Elevator.set(smoother.getOutput());
			
			//update Brake
			setBrake(CommandBase.OI.getStop());
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
