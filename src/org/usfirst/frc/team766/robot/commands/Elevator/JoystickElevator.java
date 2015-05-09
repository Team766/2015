package org.usfirst.frc.team766.robot.commands.Elevator;

//import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Uses 3rd joystick to move elevator. For testing
 * 
 * @author Patrick Kao
 */
public class JoystickElevator extends CommandBase {
	private static final double DEADZONE = .3;// For joystick

//	private static final double BOTTOM_TRAVEL = 0;
//	private static final double TOP_TRAVEL = RobotValues.ElevatorTopHeight;
	private static final boolean PRINT = false;

	public JoystickElevator() {
		requires(Elevator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		emergencyStop = false;
		Elevator.setBrake(false);
	}

	// Called repeatedly whsen this Command is scheduled to run
	protected void execute() {
		pr("Current Current: " + Elevator.getElevatorCurrent());
		double user = OI.getSlider();
		if (Math.abs(user) < DEADZONE)
			user = 0;

//		double curHeight = Elevator.getEncoders();

		if (Elevator.getElevatorCurrent() > 10) {
			Elevator.setElevatorSpeedRaw(0);
			emergencyStop = true;
			System.out
					.println("Joystick Control: Elevator Current Spiked. Elevator Stopped.");
		} else {
			Elevator.setElevatorSpeedRaw(user);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return OI.getElevatorCancel() || emergencyStop;
	}

	// Called once after isFinished returns true
	protected void end() {
		Elevator.setElevatorSpeedRaw(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private void pr(Object text) {
		if (PRINT)
			System.out.println(text);
	}

	private boolean emergencyStop;
}
