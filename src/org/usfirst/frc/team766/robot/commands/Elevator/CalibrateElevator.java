package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Goes to bottom stop and reset enc then go to top and set top height. Also has
 * buffer so we aren't so brutal with stops.
 * 
 * @author Patrick Kao
 */
public class CalibrateElevator extends CommandBase {
	private static final boolean PRINT = true;
	private static final double VELOCITY_THRESHOLD = .03;

	public CalibrateElevator() {
		requires(Elevator);
	}

	protected void initialize() {
		isFinished = false;
		numNoMoves = 0;
		Elevator.setBrake(false);
		Elevator.setElevatorSpeedRaw(-.25);
		pr("Calibrate Elevator Start");
	}

	protected void execute() {
		if (Math.abs(Elevator.getVelocity()) < VELOCITY_THRESHOLD) {
			numNoMoves++;
			pr("Calibrate Elevator Encoder Velocity Low");
		}

		if (numNoMoves > 12) {
			Elevator.resetEncoders();
			isFinished = true;
			pr("Calibrate Elevator: Encoders Reset");
		}

	}

	protected boolean isFinished() {
		return isFinished;
	}

	protected void end() {
		Elevator.setElevatorSpeedRaw(0);
	}

	protected void interrupted() {
		end();
	}

	private void pr(Object text) {
		if (PRINT)
			System.out.println(text);
	}

	double numNoMoves;
	boolean isFinished;
}
