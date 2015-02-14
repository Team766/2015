package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Command that uses the encoders to move the robot
 *
 * @author Blevenson
 * @author PKao
 */
// Note: PID code experimental
public class DriveForwardCommand extends CommandBase {
	private static final double ANGLE_TO_POWER_RATIO = .05;
	
	
	private PIDController DistancePID = new PIDController(RobotValues.DriveKp,
			RobotValues.DriveKi, RobotValues.DriveKd,
			RobotValues.Driveoutputmax_low, RobotValues.Driveoutputmax_high,
			RobotValues.DriveThreshold);
	
	private PIDController AnglePID = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

	public DriveForwardCommand() {
		this(0);
	}

	public DriveForwardCommand(double distance) {
		DistancePID.setSetpoint(distance);
		AnglePID.setSetpoint(0);
	}

	protected void initialize() {
		Drive.resetGyro();
		Drive.resetEncoders();
		DistancePID.reset();
		AnglePID.reset();
		Drive.setSmoothing(false);
		Drive.setShifter(false);
	}

	protected void execute() {
		DistancePID.calculate(Drive.getAverageEncoderDistance(), false);
		AnglePID.calculate(Drive.getAngle(), false);
		System.out.println(DistancePID.getOutput());
		System.out.println("D: " + DistancePID.getOutput() + "A: " + AnglePID.getOutput());
		Drive.setLeftPower(-DistancePID.getOutput() + AnglePID.getOutput() * ANGLE_TO_POWER_RATIO);
		Drive.setRightPower(-DistancePID.getOutput()- AnglePID.getOutput() * ANGLE_TO_POWER_RATIO);
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