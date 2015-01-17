package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Driving subsystem
 *
 * @author blevenson
 */
public class BearlyDrive extends CommandBase {
	private double last_motor = 0;
	
	public BearlyDrive() {
	}

	protected void initialize() {
	}

	protected void execute() {
		double leftC = OI.getLeft();
		double rightC = OI.getRight();
		boolean shifterC = OI.getShifter();

		
		Drive.setLeftPower(leftC);
		Drive.setRightPower(rightC);
		Drive.setShifter(OI.getShifter());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Drive.setPower(0.0);
		Drive.setShifter(false);
	}

	protected void interrupted() {
		end();
	}
}
