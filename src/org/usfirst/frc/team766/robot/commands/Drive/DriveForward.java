package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Command that uses the encoders to move the robot
 * 
 * @author PKao
 * @author Blevenson
 */

public class DriveForward extends CommandBase {
	private static final double ANGLE_TO_POWER_RATIO = 1;
	private static final boolean PRINT = false;

	private PIDController DistancePID = new PIDController(RobotValues.DriveKp,
			RobotValues.DriveKi, RobotValues.DriveKd,
			RobotValues.Driveoutputmax_low, RobotValues.Driveoutputmax_high,
			RobotValues.DriveThreshold);

	private PIDController AnglePID = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

	public DriveForward() {
		this(0);
	}

	public DriveForward(double distance) {
		DistancePID.setSetpoint(distance);
		AnglePID.setSetpoint(0);
	}
	
	public DriveForward(double distance, double angle) {
		DistancePID.setSetpoint(distance);
		AnglePID.setSetpoint(angle);
	}

	protected void initialize() {
		Drive.resetGyro();
		Drive.resetEncoders();
		DistancePID.reset();
		AnglePID.reset();
		Drive.setSmoothing(false);
		Drive.setHighGear(false);
	}

	protected void execute() {
		DistancePID.calculate(Drive.getAverageEncoderDistance(), false);
		double gyroAngle = Drive.getAngle();
		AnglePID.calculate(gyroAngle, false);

		pr("Gyro Angle: " + gyroAngle);
		pr("D: " + DistancePID.getOutput() + "A: " + AnglePID.getOutput());
		Drive.setLeftPower(-DistancePID.getOutput() - AnglePID.getOutput()
				* ANGLE_TO_POWER_RATIO);
		Drive.setRightPower(-DistancePID.getOutput() + AnglePID.getOutput()
				* ANGLE_TO_POWER_RATIO);
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

	private void pr(Object text) {
		if (PRINT)
			System.out.println("Drive Forward: " + text);
	}

}