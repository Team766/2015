package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 *	Resets the Gyro after stopping everything
 */
public class ResetGyro extends CommandBase {
	Timer timer;
	private boolean done;
	
    public ResetGyro() {
    	timer = new Timer();
    	done = false;
    }

    protected void initialize() {
    	Drive.setSmoothing(false);
    	timer.reset();
    	timer.start();
    	Drive.setPower(0);
    	Elevator.setElevatorSpeed(0);
    }

    protected void execute() {
    	if(timer.get() >= 1)
    	{
    		Drive.resetCheesyGyro();
    		Drive.resetGyro();
    		timer.reset();
    		done = true;
    	}
    }

    protected boolean isFinished() {
        return done && timer.get() >= 1;
    }

    protected void end() {
    	timer.stop();
    }

    protected void interrupted() {
    	end();
    }
}
