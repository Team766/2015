package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.robot.commands.CommandBase;

public class ArcadeDrive extends CommandBase{

	protected void end() {
		Drive.setPower(0d);
	}

	@Override
	protected void execute() {
		Drive.setLeftPower(OI.getArcadeY() + OI.getArcadeX());
		Drive.setRightPower(OI.getArcadeY() - OI.getArcadeX());
	}

	protected void initialize() {
		Drive.setHighGear(false);
		CommandBase.Drive.setSmoothing(false);
	}

	@Override
	protected void interrupted() {
		end();
	}

	protected boolean isFinished() {
		return false;
	}
}
