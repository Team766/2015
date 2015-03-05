package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Drives the robot forward using a velocity-based PID loop
 * 
 * @author Patrick Kao
 */
public class DriveForwardVelocity extends CommandBase {

	// Constants need to be tuned. Units: meters per second
	private static final double AMAX = .05;
	private static final double VMAX = .3;
	private static final double STOP_THRESHOLD = .1;

	private enum phaseType {
		RAMP_UP, MAX_VEL, RAMP_DOWN
	}

	public DriveForwardVelocity() {
		this(0);
	}

	public DriveForwardVelocity(double goal) {
		changeGoal(goal);
	}

	protected void initialize() {
		velocityPID.reset();
		AnglePID.reset();
		Drive.resetEncoders();
		Drive.resetGyro();
		Drive.setSmoothing(false);
		Drive.setHighGear(false);
		lastTime = Timer.getFPGATimestamp();
	}

	protected void execute() {
		double curSpeed = Drive.getAverageVelocity();
		double curPosition = Drive.getAverageEncoderDistance();
		double timeElapsed = Timer.getFPGATimestamp() - lastTime;

		if (Math.abs(targetPosition - curPosition) <= distanceInRamp(curSpeed)) {
			phase = phaseType.RAMP_DOWN;
		}

		if (phase == phaseType.RAMP_UP) {
			if (Math.abs(curSpeed) < VMAX) {
				velocityTarget += AMAX * timeElapsed * direction;
			} else {
				phase = phaseType.MAX_VEL;
			}
		}

		if (phase == phaseType.MAX_VEL) {
			velocityTarget = VMAX * direction;
		} else if (phase == phaseType.RAMP_DOWN) {
			velocityTarget -= AMAX * timeElapsed * direction;
		}

		velocityPID.setSetpoint(velocityTarget);
		velocityPID.calculate(curSpeed, false);

		double gyroAngle = Drive.getAngle();
		AnglePID.calculate(gyroAngle, false);

		Drive.setLeftPower(-velocityPID.getOutput() - AnglePID.getOutput());
		Drive.setRightPower(-velocityPID.getOutput() + AnglePID.getOutput());

		lastTime = Timer.getFPGATimestamp();
	}

	protected boolean isFinished() {
		return Drive.getAverageVelocity() <= STOP_THRESHOLD;
	}

	protected void end() {
		Drive.setPower(0);
	}

	protected void interrupted() {
		end();
	}

	public void changeGoal(double goal) {
		targetPosition = goal;
		direction = goal / Math.abs(goal);
		phase = phaseType.RAMP_UP;
	}

	private static double distanceInRamp(double velocity) {
		return Math.pow(AMAX, 2) / (2 * Math.abs(velocity));
	}

	private PIDController velocityPID = new PIDController(RobotValues.DriveKp,
			RobotValues.DriveKi, RobotValues.DriveKd,
			RobotValues.Driveoutputmax_low, RobotValues.Driveoutputmax_high,
			RobotValues.DriveThreshold);

	private PIDController AnglePID = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

	private phaseType phase;
	private double targetPosition, direction;
	private double velocityTarget = 0;
	private double lastTime;
}
