package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

public class JoystickIntake extends CommandBase {
	private static final boolean PRINT = true;
	
	public JoystickIntake() {
		requires(Intake);
	}

	protected void initialize() {
		Intake.setIntake(0d);
	}

	protected void execute() {
		double user = OI.getTest3();
		if(Math.abs(user) <= 0.05)
			user = 0;
			
		Intake.setIntake(user);
		System.out.println("Intake out: " + user);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Intake.setIntake(0d);
	}

	protected void interrupted() {
		end();
	}
	
	private void pr(Object text){
		if(PRINT)System.out.println(text);
	}
}
