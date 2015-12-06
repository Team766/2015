package org.usfirst.frc.team766.robot.testing;

import org.usfirst.frc.team766.lib.logFactory;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/*
 * Logs the angle of the gyro over time
 * 
 * Can take the text file and directly plot it in a graph
 * Displays the drift of the angle over time
 */
public class LogGyroAngle extends CommandBase{
	private int count;
	private int time;
	private int SAMPLESIZE = 100;
	
	public LogGyroAngle() {
		count = 0;
		time = 0;
	}

	protected void initialize() {
		logFactory.createInstance("Gyro");
		logFactory.getInstance("Gyro").print("\n\n\n\nStarting Gyro Values: \n");
	}

	protected void execute() {
		logFactory.getInstance("Gyro").printRaw(time + " " + Drive.getAngle());
		
		count++;
		time += 10;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected boolean isFinished() {
        return count >= SAMPLESIZE;
    }

    protected void end() {
    	logFactory.closeFile("Gyro");
    }

    protected void interrupted() {
    	end();
    }
}
