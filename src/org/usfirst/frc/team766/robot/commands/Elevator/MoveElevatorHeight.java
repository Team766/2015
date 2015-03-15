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
public class MoveElevatorHeight extends CommandBase {
	private static final boolean PRINT = true;

	private PIDController positionPID = new PIDController(
			RobotValues.ElevatorKp, RobotValues.ElevatorKi,
			RobotValues.ElevatorKd, RobotValues.ElevatorMinSpeed,
			RobotValues.ElevatorMaxSpeed, RobotValues.ElevatorThreshold);

	public MoveElevatorHeight() {
		this(0);
	}

	public MoveElevatorHeight(double goal) {
		requires(Elevator);
		positionPID.setSetpoint(goal);
	}

	protected void initialize() {
		Elevator.setBrake(false);
		positionPID.reset();
	}

	protected void execute() {
		positionPID.calculateDebug(Elevator.getEncoders(), false);
		// Don't want to go too high or too low
		
		if (isFinished()) {
			Elevator.setElevatorSpeed(positionPID.getOutput());
		}else
			Elevator.setElevatorSpeed(0d);
	}

	protected boolean isFinished() {
		return positionPID.isDone() || Elevator.getBottomStop()
				|| Elevator.getTopStop() || (OI.getElevatorCancel()) || 
				(Elevator.getEncoders() <= RobotValues.ElevatorTopHeight)
				&& (Elevator.getEncoders() >= 0);
	}


	protected void end() {
		Elevator.setBrake(true);
		Elevator.setElevatorSpeed(0);
	}

	public void changeGoal(double goal) {
		positionPID.setSetpoint(goal);
		initialize();
	}
	
	@Deprecated
	public void setPIDSetpoint(double goal){//Don't use this, use changeGoal instead.
		positionPID.setSetpoint(goal);
	}

	protected void interrupted() {
		end();
	}

	private void pr(Object text) {
		if (PRINT)
			System.out.println("MoveElevatorHeght: " + text);
	}
}