package org.usfirst.frc.team766.robot.commands.Grabber;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.buttons.Button;

/**
 *	Command to open and close the intake arms
 */
public class GrabberController extends CommandBase {
	
	private double speed;
	private Button button;
	
	public GrabberController()
	{
		this(0,null);
	}
	
	public GrabberController(double s)
	{
		this(s,null);
	}
	
    public GrabberController(double s, Button b) {
	    requires(grabber);
	    speed = s;
	    button = b;
    }

    protected void initialize() {
    	if(Math.abs(speed) > 0)
    		grabber.setArms(button.get() ? RobotValues.GrabberSpeedMax : speed);
    	else
    		grabber.setArms(speed);
    }

    protected void execute() {
    	if(Math.abs(speed) > 0)
    		grabber.setArms(button.get() ? RobotValues.GrabberSpeedMax : speed);
    	else
    		grabber.setArms(speed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
