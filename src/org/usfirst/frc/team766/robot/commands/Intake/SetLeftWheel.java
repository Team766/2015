package org.usfirst.frc.team766.robot.commands.Intake;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 *	
 */
public class SetLeftWheel extends SetWheelBase {
	private double inSpeed;
	private boolean temp;
	
	public SetLeftWheel()
	{
		this(0,false);
	}
	public SetLeftWheel(double inSpeed, boolean temp) {
		this(0, false, null);
	}
    public SetLeftWheel(double inSpeed, boolean temp, Button boost) {
    	super(boost);
        this.inSpeed = inSpeed;
        this.temp = temp;
    }

    protected void initialize() {
    	Intake.setLeftWheel(inSpeed*getIntakeWheelSpeedMult());
    }

    protected void execute() {
    	Intake.setLeftWheel(inSpeed*getIntakeWheelSpeedMult());
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

