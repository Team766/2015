package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	
 */
public class SetLeftWheel extends CommandBase {
	private double inSpeed;
	private boolean temp;
	
	public SetLeftWheel()
	{
		this(0,false);
	}
    public SetLeftWheel(double inSpeed, boolean temp) {
        this.inSpeed = inSpeed;
        this.temp = temp;
    }

    protected void initialize() {
    	Intake.setLeftWheel(inSpeed*OI.getIntakeWheelSpeedMult());
    }

    protected void execute() {
    	Intake.setLeftWheel(inSpeed*OI.getIntakeWheelSpeedMult());
    }

    protected boolean isFinished() {
    	if(temp)
    		return true;
    	return false;
    }

    protected void end() {
    	if(!temp)
    		Intake.setLeftWheel(0d);
    }

    protected void interrupted() {
    	end();
    }
    
}

