package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * 	Command that uses the encoders to move the robot
 *	@author Blevenson
 *	@author PKao
 */
//Note: PID code experimental
public class DriveForwardCommand extends CommandBase {
	private PIDController DistancePID = new PIDController(RobotValues.AngleKp, RobotValues.AngleKi,
			RobotValues.AngleKd, RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high, 
			RobotValues.AngleThreshold); //see PID class or hover for definition of all
	
    public DriveForwardCommand() {
    	DistancePID.setSetpoint(0);
    }
    
    public DriveForwardCommand(double distance){
    	DistancePID.setSetpoint(distance);
    }

    protected void initialize() {
    	Drive.resetEncoders();
    	DistancePID.reset();
    	Drive.setShifter(false);
    }

    protected void execute() {
    	DistancePID.calculate((Drive.getLeftEncoderDistance() + Drive.getRightEncoderDistance()) / 2.0);
    	Drive.setLeftPower(DistancePID.getOutput());
    	Drive.setRightPower(DistancePID.getOutput());
    }

    protected boolean isFinished() {
    	//added to remove error, needs to be changed
        return DistancePID.isDone();
    }

    protected void end() {
    	Drive.setPower(0d);
    }

    protected void interrupted() {
    	end();
    }
    
}