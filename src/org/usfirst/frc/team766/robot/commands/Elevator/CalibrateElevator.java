package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *  Goes to bottom stop and reset enc then go to top and set top height. Also has buffer so we aren't so brutal with stops.
 */
public class CalibrateElevator extends CommandBase {
//	private static final double ENCODER_BUFFER_BOTTOM = .3;
//	private static final double ENCODER_BUFFER_TOP = .3;
//	
//    public CalibrateElevator() {
//    }
//
//    protected void initialize() {
//    	isFinished = false;
//    	encodersSet = false;
//    	Elevator.setGripper(true);
//    	Elevator.setElevatorSpeed(.5);
//    }
//
//    protected void execute() {
//
//    	if(Elevator.getBottomStop()){
//    		Elevator.resetEncoders();
//    		encodersSet = true;
//    	}
//    	
//    	if(encodersSet && Elevator.getEncoders() >= ENCODER_BUFFER_BOTTOM){
//    		Elevator.resetEncoders();
//    	}
//    	
//    	if(Elevator.getTopStop()){
//    		RobotValues.ElevatorTopHeight = Elevator.getEncoders() - ENCODER_BUFFER_TOP;
//    		isFinished = true;
//    	}
//    }
//
//    protected boolean isFinished() {
//        return isFinished;
//    }
//
//    protected void end() {
//    	Elevator.setElevatorSpeed(0);
//    }
//
//    protected void interrupted() {
//    	end();
//    }
//    
//    boolean isFinished, encodersSet;
	
	private static final double ENCODER_BUFFER_BOTTOM = .3;
	private static final double ENCODER_BUFFER_TOP = .3;
	
    public CalibrateElevator() {
    }

    protected void initialize() {
    	isFinished = false;
    	encodersSet = false;
    	Elevator.resetEncoders();
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
    
    boolean isFinished, encodersSet;
}


//package org.usfirst.frc.team766.robot.commands.Elevator;
//
//import org.usfirst.frc.team766.robot.commands.CommandBase;
//
///**
// * Goes to bottom stop and reset enc then go to top and set top height. Also has
// * buffer so we aren't so brutal with stops.
// * @author Patrick Kao
// */
//public class CalibrateElevator extends CommandBase {
//	private static final double CALIBRATE_THRESHOLD = .001;
//	private static final double CALIBRATE_HEIGHT = .01;// Elevator buffer
//
//	public CalibrateElevator() {
//	}
//
//	protected void initialize() {
//		isFinished = false;
//		encodersSet = false;
//		Elevator.setElevatorSpeed(-.2);
//		setTimeout(5);
//	}
//
//	protected void execute() {
//		if (Elevator.getElevatorCurrent() > 10) {
//			encodersSet = true;
//			Elevator.setElevatorSpeed(.15);
//		}
//		if (encodersSet
//				&& CALIBRATE_HEIGHT - CALIBRATE_THRESHOLD < Elevator
//						.getEncoders()
//				&& Elevator.getEncoders() < CALIBRATE_HEIGHT
//						+ CALIBRATE_THRESHOLD) {
//			Elevator.resetEncoders();
//			isFinished = true;
//		}
//
//	}
//
//	protected boolean isFinished() {
//		return isFinished || isTimedOut();
//	}
//
//	protected void end() {
//		Elevator.setElevatorSpeedRaw(0);
//	}
//
//	protected void interrupted() {
//		end();
//	}
//
//	boolean isFinished, encodersSet;
//}
