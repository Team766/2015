package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.UltrasonicSensor;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *  Command that reads the current sent to the motors and sets the intake motors.
 *  Used to pull a tote in.  Closes the arms around the tote and then sucks it into the
 *  robot.  When run it reads the current and uses that to determine if the motors are
 *  in contact with the tote.
 */
public class PullToteIn extends Command {

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
	
	private State _state;

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
    	_state = State.GRASP_TOTE;
    	
    	pidL.setSetpoint(0.6);
    	pidR.setSetpoint(0.6);
    	
    	CommandBase.Intake.resetEnc();
    	CommandBase.Intake.setWheels(1);
    }

    protected void execute() {
    	switch(_state)
    	{
    		case GRASP_TOTE:
		    	pidL.basicCalculate(CommandBase.Intake.getEncLeft());
		    	pidR.basicCalculate(CommandBase.Intake.getEncRight());
		    	leftPower = pidL.getOutput();
		    	rightPower = pidR.getOutput();
		    	
		    	curr_Rrate = CommandBase.Intake.getEncRight();
		    	curr_Lrate = CommandBase.Intake.getEncLeft();
		    	
		    	curr_currentLeft = CommandBase.Intake.getIntakeCurrentLeft();
		    	curr_currentRight = CommandBase.Intake.getIntakeCurrentRight();
		    	
		    	if(!(Math.abs(curr_Rrate - pastRateR) <= stopEncDistance))
		    		rightPower = pidR.getOutput();
		    	if(!(Math.abs(curr_Lrate - pastRateL) <= stopEncDistance))
		    		leftPower = pidL.getOutput();
		    	
		    	if(curr_currentLeft > lastLeftCurr)
		    		leftPower = pidL.getOutput();
		    	if(curr_currentRight > lastRightCurr)
		    		rightPower = pidR.getOutput();
		    	
		    	CommandBase.Intake.setLeftIntake(leftPower);
		    	CommandBase.Intake.setRightIntake(rightPower);
		    	
		    	lastLeftCurr = curr_currentLeft + tollerance;
		    	lastRightCurr = curr_currentRight + tollerance;
		    	pastRateL = curr_Rrate;
		    	pastRateR = curr_Lrate;
		    	
		    	if(((curr_currentLeft > stopCurrent) && 
		    	   (curr_currentRight > stopCurrent)) &&
		    	   (Math.abs(curr_Rrate - pastRateR) <= stopEncDistance))
		    			_state = State.INTAKING;
		    	break;
    		case INTAKING:
    			CommandBase.Intake.setLeftWheel(1);
    			CommandBase.Intake.setRightWheel(1);
    			CommandBase.Intake.setLeftIntake(0.4);
    			CommandBase.Intake.setRightIntake(0.4);
    			if((UltrasonicSensor.getInstance().getDistanceDouble() * 1000) < 10)
    				_state = State.RESETING;
    			break;
    		case RESETING:
    			pidL.reset();
    			pidR.reset();
    			pidL.setSetpoint(-2);
    			pidR.setSetpoint(2);
    			
    			while(!done)
    			{
    				pidL.calculate(CommandBase.Intake.getEncLeft(), true);
    				pidR.calculate(CommandBase.Intake.getEncLeft(), true);
    				CommandBase.Intake.setLeftIntake(pidL.getOutput());
    				CommandBase.Intake.setRightIntake(pidR.getOutput());
    			}
    			_state = State.STOP;
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
    	CommandBase.Intake.setWheels(0);
    	CommandBase.Intake.setLeftIntake(0);
    	CommandBase.Intake.setRightIntake(0);
    }

    protected void interrupted() {
    	end();
    }
}
