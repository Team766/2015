package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *  Opens the left arm
 */
public class CloseLeftArm extends CommandBase {

    public CloseLeftArm() {
    }

    protected void initialize() {
    	Intake.setLeftArm(true);
    }

    protected void execute() {
    	
    }
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Intake.setLeftArm(false);
    }

    protected void interrupted() {
    	end();
    }
}
