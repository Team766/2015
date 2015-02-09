package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 *	Turns the robot x degrees
 */
public class DriveTurn extends CommandBase {
	private static final double ANGLE_TO_POWER_RATIO = 0.005;
	private PIDController pid = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

	public DriveTurn() {
        this(0d);
    }
    public DriveTurn(double a) {
        pid.setSetpoint(a);
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
    	
    	Drive.setLeftPower(pid.getOutput() * ANGLE_TO_POWER_RATIO);
		Drive.setRightPower(-pid.getOutput() * ANGLE_TO_POWER_RATIO);

    
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
