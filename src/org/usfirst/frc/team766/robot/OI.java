package org.usfirst.frc.team766.robot;

import org.usfirst.frc.team766.robot.commands.Elevator.AdjustBrake;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.LowerToteToStack;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalSmall;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalTote;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalToteChute;
import org.usfirst.frc.team766.robot.commands.Intake.GraspTote;
import org.usfirst.frc.team766.robot.commands.Intake.SetWheels;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick jLeft = new Joystick(0), jRight = new Joystick(1),
			jBox = new Joystick(2), jTest = new Joystick(3);

	public Button
	// Driving
	buttonShifter = new JoystickButton(jLeft, Buttons.Shifter),
			buttonQuickTurn = new JoystickButton(jRight, Buttons.QuickTurn),
			buttonReverse = new JoystickButton(jRight, Buttons.Reverse),
			buttonDriverOverride = new JoystickButton(jRight,
					Buttons.DriverOverride),
			buttonDriverDriveSmoothing = new JoystickButton(jRight,
					Buttons.DriverSmoothing),
			buttonDriverSlowMode = new JoystickButton(jLeft,
					Buttons.DriverSlowMode),

			// Elevator
			buttonStopElevator = new JoystickButton(jBox, Buttons.BoxStop),
			buttonElevatorClamp = new JoystickButton(jBox,
					Buttons.ElevatorClamp),
			buttonElevatorPreset1 = new JoystickButton(jBox, Buttons.preset1),
			buttonElevatorPreset2 = new JoystickButton(jBox, Buttons.preset2),
			buttonElevatorPreset3 = new JoystickButton(jBox, Buttons.preset3),
			buttonElevatorPreset4 = new JoystickButton(jBox, Buttons.preset4),
			buttonElevatorPreset5 = new JoystickButton(jBox, Buttons.preset5),
			buttonElevatorPreset6 = new JoystickButton(jBox, Buttons.preset6),
			
			buttonElevatorCancel = new JoystickButton(jBox, Buttons.ElevatorCancel),
			buttonElevatorCancelAutomation = new JoystickButton(jBox, Buttons.ElevatorCancelAutomation),
			
			// Auton
			buttonAutonIncrement = new JoystickButton(jBox,
					Buttons.AutonIncrement),
			buttonAutonDecrement = new JoystickButton(jBox,
					Buttons.AutonDecrement),

			//Intake
			buttonGraspTote = new JoystickButton(jBox, Buttons.GraspTote),
			
			
			buttonStackAdditionalChute = new JoystickButton(jTest,
					Buttons.stackAdditionalChute),
			buttonStackAdditional = new JoystickButton(jTest,
					Buttons.stackAdditionalSmall),
			buttonStackAdditionalTote = new JoystickButton(jTest,
					Buttons.stackAdditionalTote),
					

			// For testing
			buttonBrakeOn = new JoystickButton(jTest, Buttons.BrakeOn),
			buttonBrakeOff = new JoystickButton(jTest, Buttons.BrakeOff),
			buttonGripperOpen = new JoystickButton(jTest, Buttons.gripperOpen),
			buttonGripperClose = new JoystickButton(jTest, Buttons.gripperClose),
			buttonIntakeIn = new JoystickButton(jTest, Buttons.intakeIn),
			buttonIntakeOut = new JoystickButton(jTest, Buttons.intakeOut);

	// Auton Stuff
	public int AutonMode = 3;
	public boolean TankDrive = false;
	public boolean UseGamepad = false;

	public OI() {
		
		buttonElevatorClamp.whenPressed(new AdjustGripper(true));
		buttonElevatorClamp.whenReleased(new AdjustGripper(false));
		
		//Turned of so doesn't interfere with slider.
		buttonElevatorPreset1.whenPressed(new MoveElevatorWaypoint(0));
		buttonElevatorPreset2.whenPressed(new MoveElevatorWaypoint(1));
		buttonElevatorPreset3.whenPressed(new MoveElevatorWaypoint(2));
		buttonElevatorPreset4.whenPressed(new MoveElevatorWaypoint(3));
		buttonElevatorPreset5.whenPressed(new MoveElevatorWaypoint(4));
		buttonElevatorPreset6.whenPressed(new MoveElevatorWaypoint(5));
		
		buttonIntakeIn.whileHeld(new SetWheels(-.3));
		buttonIntakeOut.whileHeld(new SetWheels(.3));

		buttonBrakeOn.whenPressed(new AdjustBrake(true));
		buttonBrakeOff.whenPressed(new AdjustBrake(false));
		buttonGripperOpen.whenPressed(new AdjustGripper(false));
		buttonGripperClose.whenPressed(new AdjustGripper(true));
		
		//Intake
		buttonGraspTote.whenPressed(new GraspTote());
		
		StackAdditionalTote stackAdd = new StackAdditionalTote();
		StackAdditionalSmall stackSmall = new StackAdditionalSmall();
		StackAdditionalToteChute stackChute = new StackAdditionalToteChute();
		buttonStackAdditionalTote.whenPressed(stackAdd);
		buttonStackAdditional.whenPressed(stackSmall);
		buttonStackAdditionalChute.whileHeld(stackChute);
		buttonStackAdditionalChute.whenReleased(new LowerToteToStack());
		
		//Cancel Automated Commands
		buttonElevatorCancelAutomation.cancelWhenPressed(stackAdd);
		buttonElevatorCancelAutomation.cancelWhenPressed(stackSmall);
		buttonElevatorCancelAutomation.cancelWhenPressed(stackChute);
	}

	public boolean getShifter() {
		return buttonShifter.get();
	}

	public boolean getQuickTurn() {
		return buttonQuickTurn.get();
	}

	public boolean getReverse() {
		return buttonReverse.get();
	}

	public boolean getDriveSmoother() {
		return buttonDriverDriveSmoothing.get();
	}

	public double getThrottle() {
		return jLeft.getY();
	}

	public double getSteer() {
		return jRight.getX();
	}

	public double getTestX() {
		return jTest.getX();
	}
	
	public double getTestY() {
		return jTest.getY();
	}
	public double getTest3()
	{
		return jTest.getRawAxis(3);
	}

	// tank drive
	public double getLeft() {
		return jLeft.getY();
	}

	public double getRight() {
		return jRight.getY();
	}

	public void setTankDrive(boolean in) {
		TankDrive = in;
	}

	public boolean getTankDrive() {
		return TankDrive;
	}

	public void setUseGamepad(boolean in) {
		UseGamepad = in;
	}

	public boolean getUseGamepad() {
		return UseGamepad;
	}

	public boolean getOverride() {
		return buttonDriverOverride.get();
	}

	// For testing. Not needed right now.
	// public boolean getGripperOpen() {
	// return buttonGrippperOpen.get();
	// }
	//
	// public boolean getGripperClose() {
	// return buttonGrippperClose.get();
	// }

	/**
	 * Adds or subtracts the current auton mode.
	 * 
	 * @param direction
	 *            : negative if decrementing and positive if incrementing.
	 */
	public void incrementAutonMode(int direction) {
		// increment
		if (direction > 0)
			AutonMode++;
		// decrement
		else if (direction < 0)
			AutonMode--;
		else
			System.out.println("incrementAutonMode can't take parm 0!");

		// Reset the auton cycle
		if (AutonMode > RobotValues.Auton_Max) {
			AutonMode = RobotValues.Auton_Min;
		} else if (AutonMode < RobotValues.Auton_Min) {
			AutonMode = RobotValues.Auton_Max;
		}
	}

	public double getTwist() {
		return jLeft.getTwist();
	}

	public boolean getStop() {
		return buttonStopElevator.get();
	}

	public double getSlider() {
		return jBox.getX();
	}

	public boolean getDriverSlowMode() {
		return buttonDriverSlowMode.get();
	}
	
	public boolean getElevatorCancel()
	{
		return buttonElevatorCancel.get();
	}
	public boolean getCancelAutomation()
	{
		return buttonElevatorCancelAutomation.get();
	}
}
