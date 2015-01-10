
package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;


/**
 *	Driving subsystem
 *	@author blevenson
 */
public class TankDrive extends CommandBase {

    public TankDrive() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	double leftC = OI.getLeft();
		double rightC = OI.getRight();
		boolean reverseC = OI.getReverse();
		if(reverseC){
			double RightSave = rightC;
			rightC = leftC;
			leftC = RightSave;
		}
		
		//Auto slow
		if((!CommandBase.OI.getOverride()) && (CommandBase.Drive.getUltrasonicDistance() <= RobotValues.distanceFromBox))
		{
			leftC = (leftC * CommandBase.Drive.getUltrasonicDistance()) / RobotValues.driveDividor;
			rightC = (rightC * CommandBase.Drive.getUltrasonicDistance()) / RobotValues.driveDividor;
		}
		
		Drive.setLeftPower(leftC);
		Drive.setRightPower(rightC);
		Drive.setShifter(OI.getShifter());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Drive.setPower(0.0);
		Drive.setShifter(false);
    }

    protected void interrupted() {
    	end();
    }
}
