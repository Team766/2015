package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Driving subsystem
 *
 * @author blevenson
 */
public class BearlyDrive extends CommandBase {
	private double lastRightOut;
	private double lastLeftOut;
	private double alpha = 0.8;
	private double outputLeft;
	private double outputRight;
	
	public BearlyDrive(double alpha) {
		setAlpha(alpha);	
	}

	protected void initialize() {
		lastRightOut = 0;
		lastLeftOut = 0;
		outputLeft = 0;
		outputRight = 0;
	}

	protected void execute() {
		double inputLeft = OI.getLeft();
		double inputRight = OI.getRight();
		
		outputLeft = alpha * lastLeftOut + (1 - alpha) * inputLeft;
		outputRight = alpha * lastRightOut + (1 - alpha) * inputRight;
		
		Drive.setLeftPower(outputLeft);
		Drive.setRightPower(outputRight);
		Drive.setShifter(OI.getShifter());
		
		lastRightOut = outputRight;
		lastLeftOut = outputLeft;
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
	public void setAlpha(double a)
	{
		alpha = a;
	}
}
