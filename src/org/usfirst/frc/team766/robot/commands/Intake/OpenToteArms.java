package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;


/**
 *	Opens the arms to 45 degrees
 */
public class OpenToteArms extends CommandBase {
	
	private PIDController pidL = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	private PIDController pidR = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	private final double stopEncDistance = 0.01;
	private final double DegreesToPulsesConstant = 0.76705;
	
	private double
		curr_Rrate,
		curr_Lrate,
		leftPower,
		rightPower,
		pastRateR,
		pastRateL;
	
    public OpenToteArms() {
        requires(Intake);
    }

    protected void initialize() {
    	pidL.setSetpoint(-25);
    	pidR.setSetpoint(-25);
    }

    protected void execute() {
    	curr_Rrate = Intake.getEncRight();
    	curr_Lrate = Intake.getEncLeft();
    	
    	pidL.calculate(Intake.getEncLeft() * DegreesToPulsesConstant, false);
    	pidR.calculate(Intake.getEncRight() * DegreesToPulsesConstant, false);
    	leftPower = pidL.getOutput();
    	rightPower = pidR.getOutput();
    	
//    	if((Math.abs(curr_Rrate - pastRateR) >= stopEncDistance))
//    		rightPower = 0d;
//    	if((Math.abs(curr_Lrate - pastRateL) >= stopEncDistance))
//    		leftPower = 0d;
    	
    	Intake.setLeftIntake(leftPower);
    	Intake.setRightIntake(rightPower);
    	
    	pastRateL = curr_Rrate;
    	pastRateR = curr_Lrate;
    	System.out.println("Left:  " + leftPower);
    	System.out.println("Right:  " + rightPower);
    	System.out.println(Intake.getEncLeft());
    }

    protected boolean isFinished() {
//        return (pidL.isDone() && pidR.isDone());
//        		|| (Math.abs(curr_Rrate - pastRateR) <= stopEncDistance);
    	//return leftPower >= 0 && rightPower >= 0;
    	return pidL.isDone() && pidR.isDone();
    }

    protected void end() {
    	Intake.setWheels(0);
    	Intake.setIntake(0);
    }

    protected void interrupted() {
    	end();
    }
}
