package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *Opens the right intake arm
 */
public class CloseRightArm extends CommandBase {
	boolean state;
	
    public CloseRightArm(boolean in) {
    	state = in;
    }

    protected void initialize() {
    	Intake.setRightArm(state);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Intake.setRightArm(!state);
    }

    protected void interrupted() {
    	end();
    }
}
