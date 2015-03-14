package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	Resets the Gyro after stopping everything
 */
public class ResetGyro extends CommandBase {
    public ResetGyro() {
    }

    protected void initialize() {
    	Drive.setSmoothing(false);
    	Drive.setPower(0);
    	Elevator.setElevatorSpeed(0);
    	Drive.resetCheesyGyro();
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
