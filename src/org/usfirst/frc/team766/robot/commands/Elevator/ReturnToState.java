package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;


public class ReturnToState extends MoveElevatorHeight{
	public ReturnToState(){
		super(0);
	}
	
	@Override
	protected void initialize(){
		setPIDSetpoint(RobotValues.elevatorSavedHeight);
		super.initialize();
	}
}
