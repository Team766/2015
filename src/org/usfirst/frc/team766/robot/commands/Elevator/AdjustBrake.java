package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *
 */
public class AdjustBrake extends CommandBase {

    public AdjustBrake(boolean brake) {
    	this.brake = brake;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Elevator.setBrake(brake);
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
    
    private boolean brake;
}
