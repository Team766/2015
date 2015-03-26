package org.usfirst.frc.team766.robot.commands.Intake;

import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.buttons.Button;

public abstract class SetWheelBase extends CommandBase {
	private Button boost;
	public SetWheelBase(Button boost) {
		this.boost = boost;
	}
	public double getIntakeWheelSpeedMult() {
		if (boost==null) return 1;
		return boost.get() ? 3 : 1;
	}
}
