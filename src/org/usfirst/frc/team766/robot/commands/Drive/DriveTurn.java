package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Turns the robot x degrees
 */
public class DriveTurn extends CommandBase {
	private static final double ANGLES_TO_DEGREES = 1;
	private static final boolean PRINT = false;
	
	private PIDController pid = new PIDController(RobotValues.TurnAngleKp,
			RobotValues.TurnAngleKi, RobotValues.TurnAngleKd, -0.5, 0.5, .5);

	public DriveTurn() {
		this(0d);
	}

	public DriveTurn(double a) {
		pid.setSetpoint(a);
	}

	protected void initialize() {
		Drive.setSmoothing(false);
		Drive.resetGyro();
		Drive.resetEncoders();
		pid.reset();
		Drive.setHighGear(false);
	}

	protected void execute() {
		pid.calculate(Drive.getAngle(), false);
		
		double leftPower = pid.getOutput() * ANGLES_TO_DEGREES;
		double rightPower = -pid.getOutput() * ANGLES_TO_DEGREES;
		
		Drive.setLeftPower(-leftPower);
		Drive.setRightPower(-rightPower);
		
		if (++counter >= 20) {
			counter = 0;
			pr(pid.getError());
			pr("left: " + leftPower + "right: " + rightPower);
			pr("Gyro Angle: " + Drive.getAngle());
		}
	}

	public boolean isFinished() {
		return pid.isDone();
	}

	protected void end() {
		Drive.setPower(0d);
		Drive.setSmoothing(true);
	}

	protected void interrupted() {
		end();
	}

	private void pr(Object text) {
		if(PRINT) System.out.println("Drive Turn: " + text);
	}

	double counter = 0;

	public void changeGoal(int degrees) {
		pid.setSetpoint(degrees);
		initialize();
	}
}
