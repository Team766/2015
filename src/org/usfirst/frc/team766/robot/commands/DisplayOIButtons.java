package org.usfirst.frc.team766.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Experimental. Just for testing purposes.
 */
public class DisplayOIButtons extends CommandBase {

	public DisplayOIButtons() {
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		pr("OI Buttons:");
		pr("Elevator Preset 1: " + OI.buttonElevatorPreset1.get());
		pr("Elevator Preset 2: " + OI.buttonElevatorPreset2.get());
		pr("Elevator Preset 3: " + OI.buttonElevatorPreset3.get());
		pr("Elevator Preset 4: " + OI.buttonElevatorPreset4.get());
		pr("Elevator Preset 5: " + OI.buttonElevatorPreset5.get());
		pr("Elevator Preset 6: " + OI.buttonElevatorPreset6.get());
		pr("Button Stop Elevator: " + OI.buttonStopElevator.get());
		pr("buttonAutonIncrement: " + OI.buttonAutonIncrement.get());
		pr("buttonAutonDecrement: " + OI.buttonAutonDecrement.get());
		pr("Button Driver Override: " + OI.buttonDriverOverride.get());
		pr("Button Driver Drive Smoothing: " + OI.buttonDriverDriveSmoothing.get());
		pr("Button Driver Slow Mode: " + OI.buttonDriverSlowMode.get());
		pr("Button Reverse: " + OI.buttonReverse.get());
		
		SmartDashboard.putBoolean("Elevator Preset 1: " , OI.buttonElevatorPreset1.get());
		SmartDashboard.putBoolean("Elevator Preset 2: " , OI.buttonElevatorPreset2.get());
		SmartDashboard.putBoolean("Elevator Preset 3: " , OI.buttonElevatorPreset3.get());
		SmartDashboard.putBoolean("Elevator Preset 4: " , OI.buttonElevatorPreset4.get());
		SmartDashboard.putBoolean("Elevator Preset 5: " , OI.buttonElevatorPreset5.get());
		SmartDashboard.putBoolean("Elevator Preset 6: " , OI.buttonElevatorPreset6.get());
		SmartDashboard.putBoolean("Button Stop Elevator: " , OI.buttonStopElevator.get());
		SmartDashboard.putBoolean("buttonAutonIncrement: " , OI.buttonAutonIncrement.get());
		SmartDashboard.putBoolean("buttonAutonDecrement: " , OI.buttonAutonDecrement.get());
		SmartDashboard.putBoolean("Button Driver Override: " , OI.buttonDriverOverride.get());
		SmartDashboard.putBoolean("Button Driver Drive Smoothing: " , OI.buttonDriverDriveSmoothing.get());
		SmartDashboard.putBoolean("Button Driver Slow Mode: " , OI.buttonDriverSlowMode.get());
		SmartDashboard.putBoolean("Button Reverse: " , OI.buttonReverse.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	public void pr(Object text) {
		System.out.println(text);
	}
}
