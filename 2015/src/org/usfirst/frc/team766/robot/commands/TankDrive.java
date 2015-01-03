
package org.usfirst.frc.team766.robot.commands;


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
