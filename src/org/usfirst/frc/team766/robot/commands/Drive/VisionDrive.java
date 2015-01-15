package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.Robot;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Autonmous command that uses the offboard/onboard processor to
 * drive and find targets using the reflective tape.
 *
 */
public class VisionDrive extends CommandBase {

    public VisionDrive() {
    }

    protected void initialize() {
    	Drive.setLeftPower(0);
    }

    protected void execute() {
    	Drive.setLeftPower(Robot.table.getNumber("leftMotor"));
    	Drive.setRightPower(Robot.table.getNumber("rightMotor"));
    	System.out.println("Left: " + Robot.table.getNumber("leftMotor") + " Right: " +
    						Robot.table.getNumber("rightMotor"));
    }

    protected boolean isFinished() {
        //return Robot.table.getBoolean("done");
    	return false;
    }

    protected void end() {
    	Drive.setPower(0);
    }

    protected void interrupted() {
    	end();
    }
}
