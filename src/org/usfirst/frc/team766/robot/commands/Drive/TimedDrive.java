package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that uses the encoders to move the robot
 * 
 * @author Blevenson
 */

public class TimedDrive extends CommandBase {
	private Timer timer;
	private double done;

	public TimedDrive() {
		this(0);
	}

	public TimedDrive(double time) {
		timer = new Timer();
		timer.reset();
		timer.stop();
		done = time;
	}


	protected void initialize() {
		timer.start();
		Drive.setPower(0.5);
		Drive.setSmoothing(false);
		Drive.setHighGear(false);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return timer.get() >= done;
	}

	protected void end() {
		Drive.setPower(0d);
		timer.stop();
		Drive.setSmoothing(true);
	}

	protected void interrupted() {
		end();
	}


}