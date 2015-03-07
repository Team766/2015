package org.usfirst.frc.team766.robot.testing;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *
 */
public class DispEncoders extends CommandBase {

    public DispEncoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	counter++;
    	if(counter >= frequency){
    		counter = 0;
    		pr(Elevator.getEncoders());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void pr(Object text){
    	System.out.println("Display Encoders: " +text);
    }
    
    private double counter;
    private double frequency = 5;
}
