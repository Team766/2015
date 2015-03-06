package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *
 */
public class CalibrateElevator extends CommandBase {

    public CalibrateElevator() {
    }

    protected void initialize() {
    	isFinished = false;
    	Elevator.setGripper(true);
    	Elevator.setElevatorSpeed(.5);
    }

    protected void execute() {
    	if(Elevator.getTopStop()){
    		Elevator.resetEnc();
    		isFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	Elevator.setElevatorSpeedRaw(0);
    }

    protected void interrupted() {
    	end();
    }
    
    boolean isFinished;
}
