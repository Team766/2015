package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *
 */
public class ResetIntakeEnc extends CommandBase {

    public ResetIntakeEnc() {
    }

    protected void initialize() {
    	Intake.setIntake(0d);
    	Intake.setWheels(0d);
    	Intake.resetEnc();
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
}
