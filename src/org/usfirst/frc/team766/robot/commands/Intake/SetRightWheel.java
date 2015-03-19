package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	
 */
public class SetRightWheel extends CommandBase {
	private double inSpeed;
	private boolean temp;
	
	public SetRightWheel()
	{
		this(0,false);
	}
    public SetRightWheel(double inSpeed, boolean temp) {
        this.inSpeed = inSpeed;
        this.temp = temp;
    }

    protected void initialize() {
    	Intake.setRightWheel(inSpeed*OI.getIntakeWheelSpeedMult());
    }

    protected void execute() {
    	Intake.setRightWheel(inSpeed*OI.getIntakeWheelSpeedMult());
    }

    protected boolean isFinished() {
    	if(temp)
    		return true;
    	return false;
    }

    protected void end() {
    	if(!temp)
    		Intake.setRightWheel(0d);
    }

    protected void interrupted() {
    	end();
    }
    
}

