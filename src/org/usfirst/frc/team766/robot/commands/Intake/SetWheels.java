package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	
 */
public class SetWheels extends CommandBase {
	private double speed;
	private boolean temp;
	
	public SetWheels()
	{
		this(0,false);
	}
    public SetWheels(double speed, boolean temp) {
        this.speed = speed;
        this.temp = temp;
    }

    protected void initialize() {
    	Intake.setWheels(speed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if(temp)
    		return true;
    	return false;
    }

    protected void end() {
    	if(!temp)
    		Intake.setWheels(0d);
    }

    protected void interrupted() {
    	end();
    }
    
}

