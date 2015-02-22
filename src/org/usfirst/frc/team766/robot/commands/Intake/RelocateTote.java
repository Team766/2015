package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.Ultrasonic.UltrasonicSensor;


/**
 *	Command that gives command groups access to 
 */
public class RelocateTote extends CommandBase {
	private final double wantedDistance = .5;
	private  double
		wheelR,
		wheelL,
		intakeL,
		intakeR,
		lastDistance;
	private boolean intaking;
    public RelocateTote() {
    	this(0,0,0,0, false);
    }
    public RelocateTote(double wheelR, double wheelL)
    {
    	this(wheelR, wheelL, 0,0, false);
    }
    public RelocateTote(double wheelR, double wheelL, double l, double r)
    {
    	this(wheelR, wheelL, l, r, false);
    }
    public RelocateTote(double wheelR, double wheelL, double l, double r, boolean in)
    {
        requires(Intake);
    	this.wheelR = wheelR;
    	this.wheelL = wheelL;
    	intakeL = l;
    	intakeR = r;
    	intaking = in;
    }
    
    protected void initialize() {
    	lastDistance = UltrasonicSensor.getInstance().getDistanceDouble();
    	Intake.setLeftIntake(intakeL);
    	Intake.setRightIntake(intakeR);
    	Intake.setLeftWheel(wheelL);
    	Intake.setRightWheel(wheelR);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	if(intaking)
    		if(UltrasonicSensor.getInstance().getDistanceDouble() <= lastDistance - wantedDistance)
				return true;
    	else //Exhausting
			if(UltrasonicSensor.getInstance().getDistanceDouble() >= lastDistance + wantedDistance)
				return true;
        return false;
    }

    protected void end() {
    	Intake.setIntake(0);
    	Intake.setWheels(0);
    }

    protected void interrupted() {
    	end();
    }
}
