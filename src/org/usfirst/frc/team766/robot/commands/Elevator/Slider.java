package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Moves elevator based on slider (joystick) input
 */
public class Slider extends CommandBase {
	private double lastSlider;
	private double slider;
	
	MoveElevatorHeight mover = new MoveElevatorHeight();

	@Override
	protected void initialize() {
		lastSlider = slider = 0;
	}

	protected void execute() {
		// If elevator current is big, drop the smoother's max and
		// enlarge min output
		// Try and make them be scaled, i,e. the higher the current,
		// thee smaller the value
		// else, set both to 1 and -1 respectively

		// Move Elevator to slider
//		slider = CommandBase.OI.getSlider();
		//Note: for testing
		slider = CommandBase.OI.getLeftSliderThrottle();
		
		if (Math.abs(slider - lastSlider) <= RobotValues.SliderChangeTolerance) {
			// Convert the slider from -1 - 1 to 0 - TopHeight
			double goal = (((-RobotValues.ElevatorTopHeight) / (2)) * (slider + 1));
			mover.changeGoal(goal);
			mover.start();//Warning: Calls start multiple times?
		}

//		// Reset the elevator. Done in CalibrateElevator();
//		if (Elevator.getTopStop())
//			RobotValues.ElevatorTopHeight = Elevator.getEncoders();
//		if (Elevator.getBottomStop())
//			Elevator.resetEnc();

		// update Brake
//		Elevator.setBrake(CommandBase.OI.getStop());

		lastSlider = slider;
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}

}
