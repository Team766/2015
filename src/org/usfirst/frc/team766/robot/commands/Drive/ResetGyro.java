package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	Resets the Gyro after stopping everything
 */
public class ResetGyro extends CommandBase {
	
	private boolean fast;
	
	public ResetGyro() {
		//Default to slow(More thorough method)
		this(false);
    }
	
    public ResetGyro(boolean quick) {
    	fast = quick;
  
    }

    protected void initialize() {
    	Drive.setSmoothing(false);
    	Drive.setPower(0);
    	Elevator.setElevatorSpeed(0);
    	
    	if(!fast)
    		Drive.gyroInit();
    	Drive.resetGyro();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
