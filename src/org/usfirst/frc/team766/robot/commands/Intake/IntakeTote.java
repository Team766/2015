package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.Ultrasonic.UltrasonicSensor;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	Command to intake a tote into the robot
 */
public class IntakeTote extends CommandBase {
	
	private final double WheelIntakeSpeed = 1;
	private final double rightArmInwardMax = 10;
	private final double leftArmInwardMax = 10;
	private double 
	    leftPower,
	    rightPower,
	    lastLeftCurr,
	    lastRightCurr,
		curr_currentLeft,
		curr_currentRight,
		curr_Lrate,
		pastRateL,
		curr_Rrate,
		pastRateR;
	private final double encoder_tolerance = 1;
	private final double tolerance = 10;
	private final double stopCurrent = 10;
	private final double HoldPressure = 0.3;
	private double leftWheel;
	private double rightWheel;
	
    public IntakeTote() {
    	requires(Intake);
    }
    
    protected void initialize() {
	    leftPower =
	    rightPower =
	    lastLeftCurr =
	    lastRightCurr =
		curr_currentLeft =
		curr_currentRight =
		curr_Lrate =
		pastRateL =
		curr_Rrate =
		leftWheel = 
		rightWheel = 
		pastRateR  = 0;
    	
    }

    protected void execute() {
    	curr_Rrate = Intake.getEncRight();
    	curr_Lrate = Intake.getEncLeft();
    	curr_currentLeft = Intake.getIntakeCurrentLeft();
    	curr_currentRight = Intake.getIntakeCurrentRight();
    	
    	//If left Intake arm is stopped
    	if(isStopped(curr_currentLeft,lastLeftCurr, curr_Lrate, pastRateL))
    	{
    		leftPower = HoldPressure;
    		leftWheel = WheelIntakeSpeed;
    		rightWheel = WheelIntakeSpeed;
    	}
    	
    	
    	if(curr_Rrate > rightArmInwardMax)
    		rightWheel = 0;
    	if(curr_Lrate > leftArmInwardMax)
    		leftWheel = 0;
    	
    	if(UltrasonicSensor.getInstance().getDistanceDouble() < 0.3)
    	{
    		//STOP- open arms
    	}
    	
    	Intake.setLeftIntake(leftPower);
    	Intake.setRightIntake(rightPower);
    	Intake.setLeftWheel(leftWheel);
    	Intake.setRightWheel(rightWheel);
    	
    	lastLeftCurr = curr_currentLeft;
    	lastRightCurr = curr_currentRight;
    	pastRateR = curr_Rrate;
    	pastRateL = curr_Lrate;
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
    
    private boolean isStopped(double curr, double last, double pos, double last_pos)
    {
    	if(Math.abs(pos) < last_pos + encoder_tolerance)
    	{
	    	if(curr - tolerance > last)
	    		return true;
    	}
    	
    	return false;
    }
}
