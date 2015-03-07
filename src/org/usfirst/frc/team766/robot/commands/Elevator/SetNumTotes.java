package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetNumTotes extends Command {

    public SetNumTotes(int numTotes) {
        this.numTotes = numTotes;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotValues.numTotes = numTotes;
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
    
    int numTotes;
    
}
