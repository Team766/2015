package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Driving subsystem
 *
 * @author blevenson
 */
public class TankDrive extends CommandBase {
	
	public TankDrive() {
	}

	protected void initialize() {
		CommandBase.Drive.setSmoothing(false);
	}

	protected void execute() {
		double leftC = OI.getLeft();
		double rightC = OI.getRight();
		boolean reverseC = OI.getReverse();
		if (reverseC) {
			double RightSave = rightC;
			rightC = leftC;
			leftC = RightSave;
		}
		
		
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
		Drive.setSmoothing(true);
	}

	protected void interrupted() {
		end();
	}
}
