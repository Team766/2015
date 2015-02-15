package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *
 */

public class TestEncoders extends CommandBase {
	
	public TestEncoders() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Drive.resetEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int leftValue = Drive.getRawLeftEncoder();
		int rightValue = Drive.getRawRightEncoder();
		
		if (leftValue != lastValueLeft) {
			pr("Left Value: " + leftValue);
			pr("Left Perceived Distance: "+ Drive.getLeftEncoderDistance()); //might be a bit off b/c of the delay
		}
		
		if (rightValue != lastValueRight) {
			pr("Right Value: " + rightValue);
			pr("Right Perceived Distance: "+ Drive.getRightEncoderDistance()); //might be a bit off b/c of the delay
		}
		
		lastValueLeft = leftValue;
		lastValueRight = rightValue;
}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private void pr(Object text) {
		System.out.println("Test Encoders: " + text);
	}

	private int lastValueLeft = 0;
	private int lastValueRight = 0;
}
