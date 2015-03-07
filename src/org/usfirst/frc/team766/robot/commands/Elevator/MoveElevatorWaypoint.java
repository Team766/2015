package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;

/**
 *
 */
public class MoveElevatorWaypoint extends MoveElevatorHeight {
    
	public MoveElevatorWaypoint(){
		this(0);
	}
    public MoveElevatorWaypoint(int waypoint) {
    	super(RobotValues.ElevatorPresets[waypoint]);
    }    
}
