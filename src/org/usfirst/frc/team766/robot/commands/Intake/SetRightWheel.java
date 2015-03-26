package org.usfirst.frc.team766.robot.commands.Intake;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 *	
 */
public class SetRightWheel extends SetWheelBase {
	private double inSpeed;
	private boolean temp;
	
	public SetRightWheel() 	{
		this(0,false, null);
	}
	public SetRightWheel(double inSpeed, boolean temp) {
		this(inSpeed, temp, null);
	}
    public SetRightWheel(double inSpeed, boolean temp, Button boost) {
    	super(boost);
        this.inSpeed = inSpeed;
        this.temp = temp;
    }

    protected void initialize() {
    	Intake.setRightWheel(inSpeed*getIntakeWheelSpeedMult());
    }

    protected void execute() {
    	Intake.setRightWheel(inSpeed*getIntakeWheelSpeedMult());
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

