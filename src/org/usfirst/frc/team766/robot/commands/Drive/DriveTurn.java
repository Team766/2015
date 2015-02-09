package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	Turns the robot x degrees
 */
public class DriveTurn extends CommandBase {
	private PIDController pid = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);
	private double setPoint;

	public DriveTurn() {
        this(0d);
    }
    public DriveTurn(double a) {
        pid.setSetpoint(a);
        setPoint = a;
    }

    protected void initialize() {
    	Drive.resetGyro();
		Drive.resetEncoders();
		pid.reset();
		Drive.setShifter(false);
    }

    protected void execute() {
    	pid.calculate(Drive.getAngle());
    	//pid.calculate(((1d / 360d) * (Drive.getAngle() + 360d)) - 1d);
    	
    	if(setPoint > 0)
    	{
    		Drive.setRawLeft(pid.getOutput());
    		Drive.setRawRight(-pid.getOutput());
    	}else
    	{
    		Drive.setRawLeft(-pid.getOutput());
    		Drive.setRawRight(pid.getOutput());
    	}
    	System.out.println("left: " + pid.getOutput() + "right: " + (-pid.getOutput()));
    }

    protected boolean isFinished() {
        return pid.isDone();
    }

    protected void end() {
    	Drive.setPower(0d);
    }

    protected void interrupted() {
    	end();
    }
}
