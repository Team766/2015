package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  Command that reads the current sent to the motors and sets the intake motors.
 *  Used to pull a tote in.  Closes the arms around the tote and then sucks it into the
 *  robot.  When run it reads the current and uses that to determine if the motors are
 *  in contact with the tote.
 */
public class PullToteIn extends Command {

    private double stopCurrent;
    private double leftPower;
    private double rightPower;
    private double lastLeftCurr;
    private double lastRightCurr;
	private double curr_currentLeft;
	private double curr_currentRight;
	private double tollerance = 10;
	
	public PullToteIn() {
    	stopCurrent = 0;
    }
	
	public PullToteIn(double stop) {
    	stopCurrent = stop;
    }

    protected void initialize() {
    	curr_currentLeft = 0;
    	curr_currentRight = 0;
    	lastLeftCurr = 0;
    	lastRightCurr = 0;
    	leftPower = 0;
    	rightPower = 0;
    	
    	CommandBase.Intake.setWheels(1);
    }

    protected void execute() {
    	curr_currentLeft = CommandBase.Intake.getIntakeCurrentLeft();
    	curr_currentRight = CommandBase.Intake.getIntakeCurrentRight();
    	
    	if(curr_currentLeft > lastLeftCurr)
    		leftPower = 0.5;
    	if(curr_currentRight > lastRightCurr)
    		rightPower = 0.5;
    	
    	//If we are tyring to pull the tote in and the encoders are
    	//not moving, we have hit the tote.
    	
    	CommandBase.Intake.setLeftIntake(leftPower);
    	CommandBase.Intake.setRightIntake(rightPower);
    	
    	lastLeftCurr = curr_currentLeft + tollerance;
    	lastRightCurr = curr_currentRight + tollerance;
    }

    protected boolean isFinished() {
		return (curr_currentLeft > stopCurrent) && 
				(curr_currentRight > stopCurrent) ;
    }

    protected void end() {
    	CommandBase.Intake.setWheels(0);
    	CommandBase.Intake.setLeftIntake(0);
    	CommandBase.Intake.setRightIntake(0);
    }

    protected void interrupted() {
    	end();
    }
}
