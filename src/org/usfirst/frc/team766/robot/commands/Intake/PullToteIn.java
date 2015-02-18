package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.Ultrasonic.UltrasonicSensor;
import org.usfirst.frc.team766.robot.commands.CommandBase;
/**
 *  Command that reads the current sent to the motors and sets the intake motors.
 *  Used to pull a tote in.  Closes the arms around the tote and then sucks it into the
 *  robot.  When run it reads the current and uses that to determine if the motors are
 *  in contact with the tote.
 */
public class PullToteIn extends CommandBase {

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
	
	private PIDController pidL = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	private PIDController pidR = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	
	private State state_;

	private double stopEncDistance;

	private boolean done;
	
	private enum State{
		GRASP_TOTE, RESETING, INTAKING, STOP
	} 
	
	
	public PullToteIn() {
    	stopCurrent = 0;
    }
	
	public PullToteIn(double stop) {
    	stopCurrent = stop;
    }

    protected void initialize() {
    	curr_currentLeft = curr_currentRight = lastLeftCurr = 
    	lastRightCurr = leftPower = rightPower = curr_Lrate = 
    	pastRateL = curr_Rrate = pastRateR = 0;
    	done = false;
    	state_ = State.GRASP_TOTE;
    	
    	pidL.setSetpoint(0.6);
    	pidR.setSetpoint(0.6);
    	
    	Intake.resetEnc();
    	Intake.setWheels(1);
    }

    protected void execute() {
    	curr_Rrate = Intake.getEncRight();
    	curr_Lrate = Intake.getEncLeft();
    	
    	curr_currentLeft = Intake.getIntakeCurrentLeft();
    	curr_currentRight = Intake.getIntakeCurrentRight();
    	
    	switch(state_)
    	{
    		case GRASP_TOTE:
		    	pidL.calculate(Intake.getEncLeft(), false);
		    	pidR.calculate(Intake.getEncRight(), false);
		    	leftPower = pidL.getOutput();
		    	rightPower = pidR.getOutput();
		    	
		    	if(!(Math.abs(curr_Rrate - pastRateR) <= stopEncDistance))
		    		rightPower = pidR.getOutput();
		    	if(!(Math.abs(curr_Lrate - pastRateL) <= stopEncDistance))
		    		leftPower = pidL.getOutput();
		    	
		    	if(curr_currentLeft > lastLeftCurr)
		    		leftPower = pidL.getOutput();
		    	if(curr_currentRight > lastRightCurr)
		    		rightPower = pidR.getOutput();
		    	
		    	Intake.setLeftIntake(leftPower);
		    	Intake.setRightIntake(rightPower);
		    	
		    	lastLeftCurr = curr_currentLeft + tollerance;
		    	lastRightCurr = curr_currentRight + tollerance;
		    	pastRateL = curr_Rrate;
		    	pastRateR = curr_Lrate;
		    	
		    	if(((curr_currentLeft > stopCurrent) && 
		    	   (curr_currentRight > stopCurrent)) &&
		    	   (Math.abs(curr_Rrate - pastRateR) <= stopEncDistance))
		    			state_ = State.INTAKING;
		    	break;
    		case INTAKING:    			
    			Intake.setLeftWheel(1);
    			Intake.setRightWheel(1);
    			Intake.setLeftIntake(0.4);
    			Intake.setRightIntake(0.4);
    			if((UltrasonicSensor.getInstance().getDistanceDouble() * 1000) < 10)
    				state_ = State.RESETING;
    			break;
    		case RESETING:
    			pidL.setSetpoint(0);
    			pidR.setSetpoint(0);
    			
    			while(!done)
    			{
    				pidL.calculate(Intake.getEncLeft(), true);
    				pidR.calculate(Intake.getEncLeft(), true);
    				Intake.setLeftIntake(pidL.getOutput());
    				Intake.setRightIntake(pidR.getOutput());
    			}
    			state_ = State.STOP;
    			break;
    		case STOP:
    			done = true;
    			break;
    		default:
    			done = true;
    			break;
    	}
    }

    protected boolean isFinished() {
		return done;
    }

    protected void end() {
    	Intake.setWheels(0);
    	Intake.setLeftIntake(0);
    	Intake.setRightIntake(0);
    }

    protected void interrupted() {
    	state_ = State.RESETING;
    	System.out.println("Reseting intake");
    	execute();
    	System.out.println("Done reseting");
    	end();
    }
}
