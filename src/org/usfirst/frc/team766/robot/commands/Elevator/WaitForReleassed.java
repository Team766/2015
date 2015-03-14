package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;


/**
 * Returns when the chute button was released
 */
public class WaitForReleassed extends CommandBase {
	private boolean set;
	public WaitForReleassed(boolean starting) {
		RobotValues.releasedChute = starting;
		set = true;
	}
	
	public WaitForReleassed(){		
		set = false;
	}

	protected void initialize() {
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		if(set)
			return true;
		return RobotValues.releasedChute;
	}

	protected void end() {
		RobotValues.releasedChute = false;
	}

	protected void interrupted() {
		end();
	}

}