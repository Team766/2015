package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;


/**
 *	Command that resets the intake arms
 */
public class Reset extends CommandBase {
	
	private PIDController pidL = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	private PIDController pidR = new PIDController(RobotValues.IntakeKP, 
			RobotValues.IntakeKI, RobotValues.IntakeKD, RobotValues.IntakeThreshold);
	
    public Reset() {
        requires(Intake);
    }

    protected void initialize() {
    	pidL.setSetpoint(0);
		pidR.setSetpoint(0);
    }

    protected void execute() {
		pidL.calculate(Intake.getEncLeft(), true);
		pidR.calculate(Intake.getEncLeft(), true);
		Intake.setLeftIntake(pidL.getOutput());
		Intake.setRightIntake(pidR.getOutput());
    }

    protected boolean isFinished() {
        return pidL.isDone() && pidR.isDone();
    }

    protected void end() {
    	Intake.setIntake(0);
    	Intake.setWheels(0);
    }

    protected void interrupted() {
    	end();
    }
}
