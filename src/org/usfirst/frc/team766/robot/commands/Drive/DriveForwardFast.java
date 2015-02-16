package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that uses the encoders to move the robot
 * 
 * @author PKao
 */

//Mere Mortals. Do not touch. Experimental!

public class DriveForwardFast extends CommandBase {
	private static final boolean PRINT = false;
	private static final double SLOWDOWN_DISTANCE = .5;
	private static final double MAX_POWER = 1;// Max speed when accelerating
	private static final double KP = .1;
	private static final double FINISHED_THRESHOLD = .1;
	
	public DriveForwardFast() {
		this(0);
	}

	public DriveForwardFast(double distance) {
		targetDistance = distance;
		AnglePID.setSetpoint(0);
	}

	protected void initialize() {
		lastTimeStamp = Timer.getFPGATimestamp();
		lastDistance = Drive.getAverageEncoderDistance();
		Drive.resetGyro();
		Drive.resetEncoders();
		Drive.setSmoothing(false);
		Drive.setHighGear(false);
		Drive.setPower(0);
		VelocityPID.reset();
		AnglePID.reset();
	}
	
	protected void execute() {// remember to set lastTimeStamp to current time
								// at end of loop (Same for distance)
		double currentTime = Timer.getFPGATimestamp();
		double currentDistance = Drive.getAverageEncoderDistance();
		double elapsedTime = currentTime - lastTimeStamp;
		double elapsedDistance = currentDistance - lastDistance;
		double velocity = elapsedDistance / elapsedTime;
		double acceleration = velocity / elapsedTime;
		
		if (targetDistance - currentDistance <= SLOWDOWN_DISTANCE) {
			double totalDistance = 0;
			double decreasedVelocity = velocity;
			while(decreasedVelocity>0){
				decreasedVelocity-=acceleration;
				totalDistance+=decreasedVelocity;
			}
			double futureDifference = totalDistance- (targetDistance - currentDistance);//Is positive if moves farther
			currentPower+=futureDifference*KP;
			Drive.setPower(currentPower);
		} else {
			currentPower += .05;// Decrease to lower speed but be easier on
								// mechanics
			currentPower = Math.min(currentPower, MAX_POWER);
			Drive.setPower(currentPower);
		}
		
	}

	protected boolean isFinished() {
		return targetDistance - FINISHED_THRESHOLD<=Drive.getAverageEncoderDistance() && Drive.getAverageEncoderDistance() <=targetDistance + FINISHED_THRESHOLD;
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

	private double lastTimeStamp, lastDistance;
	private double targetDistance;
	private double currentPower = 0;// Only used when accelerating to smooth

	private PIDController VelocityPID = new PIDController(RobotValues.DriveKp,
			RobotValues.DriveKi, RobotValues.DriveKd,
			RobotValues.Driveoutputmax_low, RobotValues.Driveoutputmax_high,
			RobotValues.DriveThreshold);

	private PIDController AnglePID = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

}