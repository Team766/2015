package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *  Goes to bottom stop and reset enc then go to top and set top height. Also has buffer so we aren't so brutal with stops.
 */
public class CalibrateElevator extends CommandBase {
	public static final double ENCODER_BUFFER = .3;
	
    public CalibrateElevator() {
    }

    protected void initialize() {
    	isFinished = false;
    	encodersSet = false;
    	Elevator.setGripper(true);
    	Elevator.setElevatorSpeed(.5);
    }

    protected void execute() {

    	if(Elevator.getBottomStop()){
    		Elevator.resetEncoders();
    		encodersSet = true;
    	}
    	
    	if(encodersSet && Elevator.getEncoders() >= ENCODER_BUFFER){
    		Elevator.resetEncoders();
    	}
    	
    	if(Elevator.getTopStop()){
    		RobotValues.ElevatorTopHeight = Elevator.getEncoders();
    		isFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	Elevator.setElevatorSpeed(0);
    }

    protected void interrupted() {
    	end();
    }
    
    boolean isFinished, encodersSet;
}