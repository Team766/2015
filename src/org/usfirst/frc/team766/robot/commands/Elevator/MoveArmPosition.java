package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Command that moves the elevator to the bottom preset
 *
 * @author Blevenson
 * @author PKao
 */

// Note: PID Constants need to be tuned
public class MoveArmPosition extends CommandBase {
	private PIDController positionPID; // Need to initialize PID Controller with
										// right constants.

	public MoveArmPosition() {
		requires(Elevator);
		Elevator.setGoal(0);
		positionPID.setSetpoint(0);
	}

	public MoveArmPosition(double goal) {
		positionPID.setSetpoint(goal);
	}

	public MoveArmPosition(RobotValues.ElevatorState waypoint) {
		switch (waypoint) {
			case BOTTOM:
				positionPID.setSetpoint(RobotValues.bottomPreset);
				break;
	
			case MIDDLE:
				positionPID.setSetpoint(RobotValues.middlePreset);
				break;
	
			case TOP:
				positionPID.setSetpoint(RobotValues.topPreset);
				break;
			default:
				break;
		}
	}

	protected void initialize() {
		Elevator.resetEnc();
		positionPID.reset();
	}

	protected void execute() {
		positionPID.calculate(Elevator.getEnc(), false);
		//Don't want to go too high or too low
		if((Elevator.getEnc() <= RobotValues.ElevatorTopHeight) &&
				(Elevator.getEnc() >= 0))
			Elevator.setElevatorSpeed(positionPID.getOutput());
	}

	protected boolean isFinished() {
		return positionPID.isDone() || Elevator.getBottomStop() || Elevator.getTopStop();
	}

	protected void end() {
		Elevator.setElevatorSpeed(0);
	}

	protected void interrupted() {
		end();
	}
}