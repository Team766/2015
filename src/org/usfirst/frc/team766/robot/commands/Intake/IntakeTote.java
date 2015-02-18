package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	Command to intake a tote into the robot
 */
public class IntakeTote extends CommandBase {
	
	private double 
	    stopCurrent,
	    leftPower,
	    rightPower,
	    lastLeftCurr,
	    lastRightCurr,
		curr_currentLeft,
		curr_currentRight,
		curr_Lrate,
		pastRateL,
		curr_Rrate,
		pastRateR,
		tollerance = 10;
		
    public IntakeTote() {
    	requires(Intake);
    }
    
    protected void initialize() {
    	
    }

    protected void execute() {
    
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Intake.setLeftIntake(0);
    	Intake.setLeftWheel(0);
    	Intake.setRightIntake(0);
    	Intake.setRightWheel(0);
    }

    protected void interrupted() {
    	end();
    }
}
