package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.UltrasonicSensor;

/**
 * Autonmous command that uses the offboard/onboard processor to
 * drive and find targets using the reflective tape.
 *
 */
public class VisionDrive extends CommandBase {
    private int count;
    public VisionDrive() {
    	requires(Drive);
        count = 0;
    }

    protected void initialize() {
    	Drive.setLeftPower(0);
    	table.putBoolean("done", true);
    }

    protected void execute() {
    	CommandBase.table.putNumber("distance", UltrasonicSensor.getInstance().getDistanceDouble());
    	
    	Drive.setLeftPower(CommandBase.table.getNumber("leftMotor"));
    	Drive.setRightPower(CommandBase.table.getNumber("rightMotor"));
    	
    	//System.out.println("Left: " + Robot.table.getNumber("leftMotor") + " Right: " +
    	//					Robot.table.getNumber("rightMotor"));
    	//Count is used as a buffer to wait for the vision to connect with the robot, if it doesn't
    	
    	//connect, then it cancels this auton
        if(CommandBase.table.getBoolean("done"))
            count++;
        else
            count = 101;  //Needs to be greater so than it can be ready to end  
    }

    protected boolean isFinished() {
        return (CommandBase.table.getBoolean("done") && count > 100);
    	//return false;
    }

    protected void end() {
    	System.out.println("Ending vision drive");
    	Drive.setPower(0);
    }

    protected void interrupted() {
    	end();
    }
}
