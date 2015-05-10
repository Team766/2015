package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;

/**
 *	Runs the code sent by the text controller server
 */
public class TextToControls extends CommandBase {
	private double lastDistance;
	private int lastDegree;
	private int lastWaypoint;
	
	private DriveForward driver;
	private DriveTurn turner;
	private MoveElevatorWaypoint raiser;
	
    public TextToControls() {
    	driver = new DriveForward(0);
    	turner = new DriveTurn(0);
    	raiser = new MoveElevatorWaypoint(OI.server.getWaypoint());
    }

    protected void initialize() {
    	lastDistance = 0;
    	lastDegree = 0;
    	lastWaypoint = 0;
    }

    protected void execute() {
    	
    	if(Math.abs(OI.server.getDriveDistance() - lastDistance) > .2){
    		driver.changeGoal(OI.server.getDriveDistance());
    		turner.cancel();
    		raiser.cancel();
    		driver.start();
    	}
    	if(Math.abs(OI.server.getDegrees() - lastDegree) > 5){
    		turner.changeGoal(OI.server.getDegrees());	
    		driver.cancel();
    		raiser.cancel();
    		turner.start();
    	}
    	if(Math.abs(OI.server.getWaypoint() - lastWaypoint) > 1){
    		raiser.changeGoal(RobotValues.ElevatorPresets[OI.server.getWaypoint()]);
    		driver.cancel();
    		turner.cancel();
    		raiser.start();
    	}
    	Elevator.setGripper(OI.server.getClawState());
    	
    	lastDistance = OI.server.getDriveDistance();
    	lastDegree = OI.server.getDegrees();
    	lastWaypoint = OI.server.getWaypoint();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Drive.setPower(0);
    	Elevator.setGripper(false);
    	Elevator.setElevatorSpeed(0);
    	Elevator.setBrake(true);
    }

    protected void interrupted() {
    	end();
    }
}
