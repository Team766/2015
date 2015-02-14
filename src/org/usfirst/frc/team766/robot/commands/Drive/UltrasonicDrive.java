package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.UltrasonicSensor;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Uses a PID for the ultrasonic sensor to drive forward
 *
 * @author AWire
 * @author PKao
 * @author Blevenson
 */
public class UltrasonicDrive extends CommandBase {
	private static final double SLOWING_OFFSET = .2;
	
	private double targetDistance;
	private boolean beSafe;
	private double currentDistance;

	public UltrasonicDrive() {
		this(0,false);
	}

	public UltrasonicDrive(double distance, boolean beSafe) {
		targetDistance = distance;
		this.beSafe = beSafe;
	}

	protected void initialize() {
		Drive.resetEncoders();
		currentDistance = 0;
	}

	protected void execute() {
		// Drive forward using the PID here for the Ultrasonic sensor
		currentDistance = UltrasonicSensor.getInstance().getDistanceDouble();
		Drive.setPower(Math.min(targetDistance-currentDistance,1));
	}

	protected boolean isFinished() {
		return (currentDistance <= targetDistance)
				|| (beSafe && (Drive.getAverageEncoderDistance() >= RobotValues.safteyDriveDistance));
	}

	protected void end() {
		Drive.setPower(0.0d);
	}

	protected void interrupted() {
		System.out.println("0 Nose!!!  Someone interrupted the UltrasonicDrive Command...");
		end();
	}
}
