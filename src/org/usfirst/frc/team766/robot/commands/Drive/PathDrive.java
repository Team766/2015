package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.trajectory.AutoPaths;
import org.usfirst.frc.team766.lib.trajectory.Path;
import org.usfirst.frc.team766.lib.trajectory.Trajectory;
import org.usfirst.frc.team766.lib.trajectory.TrajectoryFollower;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * DrivePathAction causes the robot to drive along a Path
 *
 */
public class PathDrive extends CommandBase {

	double heading;
	Path path;

	TrajectoryFollower followerLeft = new TrajectoryFollower("left");
	TrajectoryFollower followerRight = new TrajectoryFollower("right");
	private double direction;
	double kTurn = -3.0 / 80.0;

	public PathDrive(Path route, double timeout) {
		path = route;
		setTimeout(timeout);
	}

	public void execute() {
		// We need to set the Trajectory each update as it may have been flipped
		// from under us
		loadProfileNoReset(path.getLeftWheelTrajectory(),
				path.getRightWheelTrajectory());

		if (onTarget()) {
			Drive.setPower(0.0);
		} else {
			double distanceL = direction * Drive.getLeftEncoderDistance();
			double distanceR = direction * Drive.getRightEncoderDistance();
			System.out.println("DistanceL:" + distanceL);
			System.out.println("DistanceR:" + distanceR + "\n");
			
			double speedLeft = direction * followerLeft.calculate(distanceL);
			double speedRight = direction * followerRight.calculate(distanceR);
//			System.out.println("SpeedLeft:" + speedLeft);
//			System.out.println("SpeedRight:" + speedRight + "\n");
			
			double goalHeading = followerLeft.getHeading();
			double observedHeading = Drive.getGyroAngleInRadians();

			double angleDiffRads = getDifferenceInAngleRadians(observedHeading, goalHeading);
			double angleDiff = Math.toDegrees(angleDiffRads);

			double turn = kTurn * angleDiff;
			//Drive.setLeftPower(speedLeft + turn);
			//Drive.setRightPower(speedRight - turn);
			Drive.setLeftPower(speedLeft);
			Drive.setRightPower(speedRight);
		}
	}

	protected void end() {
		System.out.println("Done Drive " + Timer.getFPGATimestamp());
		Drive.setPower(0.0);
	}

	@Override
	protected void initialize() {
		path = OI.path;
		
		followerLeft.configure(1.5, 0, 0, 1.0 / 15.0, 1.0 / 34.0);
		followerRight.configure(1.5, 0, 0, 1.0 / 15.0, 1.0 / 34.0);

		System.out.println("Init Drive " + Timer.getFPGATimestamp());
		Drive.resetEncoders();
		
		loadProfile(path.getLeftWheelTrajectory(),
					path.getRightWheelTrajectory(), 1.0, 0.0);

	}

	@Override
	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return isTimedOut() || onTarget();
	}

	public void loadProfileNoReset(Trajectory leftProfile,
			Trajectory rightProfile) {
		followerLeft.setTrajectory(leftProfile);
		followerRight.setTrajectory(rightProfile);
	}

	public boolean onTarget() {
		return followerLeft.isFinishedTrajectory();
	}

	public void loadProfile(Trajectory leftProfile, Trajectory rightProfile,
			double direction, double heading) {
		reset();
		followerLeft.setTrajectory(leftProfile);
		followerRight.setTrajectory(rightProfile);
		this.direction = -direction;
		this.heading = heading;
	}

	public void reset() {
		followerLeft.reset();
		followerRight.reset();
		Drive.resetEncoders();
	}

	public double getDifferenceInAngleRadians(double from, double to) {
		return boundAngleNegPiToPiRadians(to - from);
	}

	public double boundAngleNegPiToPiRadians(double angle) {
		// Naive algorithm
		while (angle >= Math.PI) {
			angle -= 2.0 * Math.PI;
		}
		while (angle < -Math.PI) {
			angle += 2.0 * Math.PI;
		}
		return angle;
	}
}
