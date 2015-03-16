package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;


/**
 *	Opens the arms to 45 degrees
 */
public class GraspTote extends CommandBase {
	
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
	
    public GraspTote() {
        requires(Intake);
    }

    protected void initialize() {
    	pidL.setSetpoint(45);
    	pidR.setSetpoint(45);
    }

    protected void execute() {
//    	curr_Rrate = Intake.getEncRight();
//    	curr_Lrate = Intake.getEncLeft();
    	
    	pidL.calculate(Intake.getEncLeft() * DegreesToPulsesConstant, false);
    	pidR.calculate(Intake.getEncRight() * DegreesToPulsesConstant, false);
    	leftPower = pidL.getOutput();
    	rightPower = pidR.getOutput();
    	
//    	if((Math.abs(curr_Rrate - pastRateR) < stopEncDistance))
//    		rightPower = 0d;
//    	if((Math.abs(curr_Lrate - pastRateL) < stopEncDistance))
//    		leftPower = 0d;
    	
    	Intake.setLeftIntake(leftPower);
    	Intake.setRightIntake(rightPower);
    	
//    	pastRateL = curr_Rrate;
//    	pastRateR = curr_Lrate;
    	System.out.println("Left:  " + leftPower);
    	System.out.println("Right:  " + rightPower);
    	System.out.println(Intake.getEncLeft());
    }

    protected boolean isFinished() {
        return (pidL.isDone() && pidR.isDone());
//        		|| (Math.abs(curr_Rrate - pastRateR) <= stopEncDistance);
//    	return leftPower <= 0 && rightPower <= 0;
    }

    protected void end() {
    	Intake.setWheels(0);
    	Intake.setIntake(0);
    }

    protected void interrupted() {
    	end();
    }
}

//package org.usfirst.frc.team766.robot.commands.Intake;
//
//import org.usfirst.frc.team766.lib.PIDController;
//import org.usfirst.frc.team766.robot.RobotValues;
//import org.usfirst.frc.team766.robot.commands.CommandBase;
//
//
///**
// *	Command that brings the intake arms around the tote
// */
//public class GraspTote extends CommandBase {
//	
//	private PIDController pidL = new PIDController(RobotValues.IntakeKP, 
//			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
//	private PIDController pidR = new PIDController(RobotValues.IntakeKP, 
//			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
//	private final double stopEncDistance = 0.01;
//	private final double tollerance = 0.5;
//	private final double stopCurrent = 3;
//	private final double DegreesToPulsesConstant = 0.76705;
//	
//	private double
//		curr_Rrate,
//		curr_Lrate,
//		curr_currentLeft,
//		curr_currentRight,
//		leftPower,
//		rightPower,
//		pastRateR,
//		pastRateL,
//		lastLeftCurr,
//		lastRightCurr;
//	
//    public GraspTote() {
//        requires(Intake);
//    }
//
//    protected void initialize() {
//    	pidL.setSetpoint(45 * DegreesToPulsesConstant);
//    	pidR.setSetpoint(45 * DegreesToPulsesConstant);
//    }
//
//    protected void execute() {
//    	curr_Rrate = Intake.getEncRight();
//    	curr_Lrate = Intake.getEncLeft();
//    	
//    	curr_currentLeft = Intake.getIntakeCurrentLeft();
//    	curr_currentRight = Intake.getIntakeCurrentRight();
//    	
//    	
//    	pidL.calculate(Intake.getEncLeft() * DegreesToPulsesConstant, false);
//    	pidR.calculate(Intake.getEncRight() * DegreesToPulsesConstant, false);
//    	leftPower = pidL.getOutput();
//    	rightPower = pidR.getOutput();
//    	
//    	if((Math.abs(curr_Rrate - pastRateR) >= stopEncDistance))
//    		rightPower = 0d;
//    	if((Math.abs(curr_Lrate - pastRateL) >= stopEncDistance))
//    		leftPower = 0d;
//    	
//    	if(curr_currentLeft > lastLeftCurr)
//    		leftPower = 0;
//    	if(curr_currentRight> lastRightCurr)
//    		rightPower = 0;
//    	
//    	Intake.setLeftIntake(leftPower);
//    	Intake.setRightIntake(rightPower);
//    	
//    	lastLeftCurr = curr_currentLeft + tollerance;
//    	lastRightCurr = curr_currentRight + tollerance;
//    	pastRateL = curr_Rrate;
//    	pastRateR = curr_Lrate;
//    	
//    	System.out.println("Current Current: " + curr_currentLeft);
//    	System.out.println("Left Encoder: " + curr_Lrate);
//    	System.out.println("Right Encoder: " + curr_Rrate);
//    	System.out.println("Left: " + leftPower + "Right: " + rightPower);
//    }
//
//    protected boolean isFinished() {
////        return (pidL.isDone() && pidR.isDone()) || (((curr_currentLeft > stopCurrent) && 
////		    	   (curr_currentRight > stopCurrent))) ||
////		    	   (Math.abs(curr_Rrate - pastRateR) <= stopEncDistance);
//    	return leftPower <= 0 && rightPower <= 0;
//    }
//
//    protected void end() {
//    	Intake.setWheels(0);
//    	Intake.setIntake(0);
//    }
//
//    protected void interrupted() {
//    	end();
//    }
//}
