package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorWaypoint extends Command {
    private int waypoint;
    
	public MoveElevatorWaypoint(){
		this(0);
	}
    public MoveElevatorWaypoint(int waypoint) {
    	this.waypoint = waypoint;
    }

    protected void initialize() {
    	new MoveElevatorHeight(RobotValues.ElevatorPresets[waypoint]).start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
