package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * 	Command that uses the encoders to move the robot
 *	@author Blevenson
 */
public class DriveForwardCommand extends CommandBase {
	private double distance;
	
    public DriveForwardCommand() {
        distance = 0;
    }
    
    public DriveForwardCommand(double distance){
    	this.distance = distance;
    }

    protected void initialize() {
    }

    protected void execute() {
    	//PID uses the encoder values to drive the distance
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Drive.setPower(0d);
    }

    protected void interrupted() {
    	end();
    }
    
}
