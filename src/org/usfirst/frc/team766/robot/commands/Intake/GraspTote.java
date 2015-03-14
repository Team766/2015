package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;


/**
 *	Command that brings the intake arms around the tote
 */
public class GraspTote extends CommandBase {
	
	private PIDController pidL = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	private PIDController pidR = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	private final double stopEncDistance = 0.01;
	private final double tollerance = 10;
	private final double stopCurrent = 10;
	private double
		curr_Rrate,
		curr_Lrate,
		curr_currentLeft,
		curr_currentRight,
		leftPower,
		rightPower,
		pastRateR,
		pastRateL,
		lastLeftCurr,
		lastRightCurr;
	
    public GraspTote() {
        requires(Intake);
    }

    protected void initialize() {
    	pidL.setSetpoint(0);
    	pidR.setSetpoint(0);
    }

    protected void execute() {
    	curr_Rrate = Intake.getEncRight();
    	curr_Lrate = Intake.getEncLeft();
    	
    	curr_currentLeft = Intake.getIntakeCurrentLeft();
    	curr_currentRight = Intake.getIntakeCurrentRight();
    	
    	
    	pidL.calculate(Intake.getEncLeft(), false);
    	pidR.calculate(Intake.getEncRight(), false);
    	leftPower = pidL.getOutput();
    	rightPower = pidR.getOutput();
    	
    	if((Math.abs(curr_Rrate - pastRateR) >= stopEncDistance))
    		rightPower = 0d;
    	if((Math.abs(curr_Lrate - pastRateL) >= stopEncDistance))
    		leftPower = 0d;
    	
    	if(curr_currentLeft > lastLeftCurr)
    		leftPower = 0;
    	if(curr_currentRight > lastRightCurr)
    		rightPower = 0;
    	
    	Intake.setLeftIntake(leftPower);
    	Intake.setRightIntake(rightPower);
    	
    	lastLeftCurr = curr_currentLeft + tollerance;
    	lastRightCurr = curr_currentRight + tollerance;
    	pastRateL = curr_Rrate;
    	pastRateR = curr_Lrate;
    }

    protected boolean isFinished() {
        return ((curr_currentLeft > stopCurrent) && 
		    	   (curr_currentRight > stopCurrent)) &&
		    	   (Math.abs(curr_Rrate - pastRateR) <= stopEncDistance);
    }

    protected void end() {
    	Intake.setWheels(0);
    	Intake.setIntake(0);
    }

    protected void interrupted() {
    	end();
    }
}
