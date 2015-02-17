package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *Takes whether or not to grip a tote
 */
public class AdjustGripper extends CommandBase {
	boolean grip;
    public AdjustGripper(boolean grip) {
    	this.grip = grip;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Elevator.setElevator(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Elevator.setElevator(true);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Elevator.setElevator(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
}