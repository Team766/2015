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
	private static final double CALIBRATE_HEIGHT = .015;// Elevator buffer

	public CalibrateElevator() {
		requires(Elevator);
	}

	protected void initialize() {
		isFinished = false;
		encodersSet = false;
		numNoMoves = 0;
		Elevator.setBrake(false);
		Elevator.setElevatorSpeed(-.2);
		pr("Calibrate Elevator Start");
	}

	protected void execute() {
		double curHeight = Elevator.getEncoders();
		
		if (Elevator.getVelocity() < VELOCITY_THRESHOLD) {
			numNoMoves++;
			pr("Calibrate Elevator Encoder Velocity Low");
		}

		if (numNoMoves > 100 && !encodersSet) {
			Elevator.resetEncoders();
			encodersSet = true;
			Elevator.setElevatorSpeed(.05);
			pr("Calibrate Elevator: Encoders Reset");
		}

		if (encodersSet && curHeight > CALIBRATE_HEIGHT) {
			Elevator.resetEncoders();
			isFinished = true;
			pr("Calibrate Elevator: Encoders Finalized");
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
	
	private void pr(Object text){
		if(PRINT)System.out.println(text);
	}

	double numNoMoves;
	boolean isFinished, encodersSet;
}
