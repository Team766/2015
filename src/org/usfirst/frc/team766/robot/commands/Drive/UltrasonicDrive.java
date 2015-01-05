package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * 	Uses a PID for the ultrasonic sensor to drive forward
 *	@author Blevenson
 */
public class UltrasonicDrive extends CommandBase {

	private double distanceFromBox;
	
    public UltrasonicDrive(double distance) {
    	distanceFromBox = distance;
    }

    protected void initialize() {
    	Drive.resetEncoders();
    }

    protected void execute() {
    	//Drive forward using the PID here for the Ultrasonic sensor
    	
    	if(Drive.getUltrasonicDistance() <= 0)end();
    	if(Drive.getAverageEncoderDistance() >= RobotValues.safteyDriveDistance)end();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }
    
    protected void interrupted() {
    	end();
    }
}
