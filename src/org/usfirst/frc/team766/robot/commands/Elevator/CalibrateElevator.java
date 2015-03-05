package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *
 */
public class CalibrateElevator extends CommandBase {

    public CalibrateElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    	Elevator.setGripper(true);
    	Elevator.setElevatorSpeed(.5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Elevator.getTopStop()){
    		Elevator.resetEnc();
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Elevator.setElevatorSpeedRaw(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    boolean isFinished;
}
