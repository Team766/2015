package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	
 */
public class SetWheels extends CommandBase {
	private double speed;
	
    public SetWheels(double speed) {
        this.speed = speed;
    }

    protected void initialize() {
    	Intake.setWheels(speed);
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

