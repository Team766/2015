package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;

/**
 *	Runs the code sent by the text controller server
 */
public class TextToControls extends CommandBase {
	private double lastDistance;
	private int lastDegree;
	
	private DriveForward driver;
	private DriveTurn turner;
	
    public TextToControls() {
    	driver = new DriveForward(0);
    	turner = new DriveTurn(0);
    	lastDistance = 0;
    }

    protected void initialize() {
    }

    protected void execute() {
    	
//    	if (!driver.isRunning())
//			driver.start();
//    	if (!turner.isRunning())
//			turner.start();
    	
    	
    	if(Math.abs(OI.server.getDriveDistance() - lastDistance) > .2){
    		driver.changeGoal(OI.server.getDriveDistance());
    		turner.cancel();
    		driver.start();
    	}
    	if(Math.abs(OI.server.getDegrees() - lastDegree) > 5){
    		turner.changeGoal(OI.server.getDegrees());	
    		driver.cancel();
    		turner.start();
    	}
    	Elevator.setGripper(OI.server.getClawState());
    	
    	lastDistance = OI.server.getDriveDistance();
    	lastDegree = OI.server.getDegrees();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Drive.setPower(0);
    	Elevator.setGripper(false);
    }

    protected void interrupted() {
    	end();
    }
}
