package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *  Opens the left arm
 */
public class CloseLeftArm extends CommandBase {
	boolean state;
    public CloseLeftArm(boolean open) {
    	state = open;
    }

    protected void initialize() {
    	Intake.setLeftArm(state);
    }

    protected void execute() {
    	
    }
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Intake.setLeftArm(!state);
    }

    protected void interrupted() {
    	end();
    }
}
