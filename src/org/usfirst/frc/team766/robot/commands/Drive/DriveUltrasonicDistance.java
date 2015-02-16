package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.Ultrasonic.UltrasonicSensor;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Command that uses the ultrasonic sensor to move the robot
 * 
 * @author Blevenson
 */

public class DriveUltrasonicDistance extends CommandBase {
	private static final double ANGLE_TO_POWER_RATIO = 1;
	public double graphError = 0;
	
	private PIDController DistancePID = new PIDController(RobotValues.UltrasonicDriveKp,
			RobotValues.UltrasonicDriveKi, RobotValues.UltrasonicDriveKd,
			RobotValues.Driveoutputmax_low, RobotValues.Driveoutputmax_high,
			0.001);
	
	private PIDController AnglePID = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

	public DriveUltrasonicDistance() {
		this(0);
	}

	public DriveUltrasonicDistance(double distance) {
		DistancePID.setSetpoint(distance);
		AnglePID.setSetpoint(0);
	}

	protected void initialize() {
		Drive.resetGyro();
		DistancePID.reset();
		AnglePID.reset();
		Drive.setSmoothing(false);
		Drive.setHighGear(false);
	}

	protected void execute() {
		graphError = DistancePID.getCurrentError();
		DistancePID.calculate(UltrasonicSensor.getInstance().getDistanceDouble(), false);
		double gyroAngle = Drive.getAngle();
		AnglePID.calculate(gyroAngle , false);
		
		Drive.setLeftPower(DistancePID.getOutput() - AnglePID.getOutput() * ANGLE_TO_POWER_RATIO);
		Drive.setRightPower(DistancePID.getOutput() + AnglePID.getOutput() * ANGLE_TO_POWER_RATIO);
		System.out.println("Error: " + DistancePID.getCurrentError() + "left: " + (DistancePID.getOutput() - AnglePID.getOutput() * ANGLE_TO_POWER_RATIO)
				+ "right: " + (DistancePID.getOutput() + AnglePID.getOutput() * ANGLE_TO_POWER_RATIO));
	}

	protected boolean isFinished() {
		return DistancePID.isDone();
	}

	protected void end() {
		Drive.setPower(0d);
		Drive.setSmoothing(true);
	}

	protected void interrupted() {
		end();
	}
	
}