package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Takes whether or not to grip a tote. Pass true into the constructor to close
 * the gripper arm
 */
public class AdjustGripper extends CommandBase {
	public static final double GRIP_TIME = 1;
	
	boolean grip, isDone;
	double startTime;


	public AdjustGripper(boolean grip) {
		this.grip = grip;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Elevator.setGripper(grip);
		setTimeout(GRIP_TIME);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}