package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *Opens the right intake arm
 */
public class OpenRightArm extends CommandBase {

    public OpenRightArm() {
    }

    protected void initialize() {
    	Intake.setRightArm(true);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Intake.setRightArm(false);
    }

    protected void interrupted() {
    	end();
    }
}
