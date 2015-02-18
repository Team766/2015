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

    // Called just before this Command runs the first time
    protected void initialize() {
    	new MoveElevatorHeight(RobotValues.ElevatorPresets[waypoint]).start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
