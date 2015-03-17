package org.usfirst.frc.team766.robot.commands.Elevator;

public class ToggleGripper extends AdjustGripper {

	public ToggleGripper() {
		super(false);
	}

	protected void initialize() {
		grip = !Elevator.getGripper();
		super.initialize();
	}

}
