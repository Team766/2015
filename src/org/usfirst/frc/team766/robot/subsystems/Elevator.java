package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorHeight;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the elevator
 * 
 * This version of the elevator uses a constant to counteract the weight of the
 * elevator.
 *
 * @author Blevenson
 * @author PKao
 */

public class Elevator extends Subsystem {
	private static final double GRAVITY_OFFSET = .1; // Needs to be calculated.
														// Offset of just
														// mechanism. PID should
														// compensate for rest
														// -Patrick

	private double gravityOffset = GRAVITY_OFFSET;
	private double stopTolerance = 0.001;

	// Goal: wanted position, between 0 and Elevator Encoder Height
	private double goal = 0;

	private Victor Elevator = new Victor(Ports.PWM_Elevators);
	private Encoder liftPos = new Encoder(Ports.DIO_ELEVATOR_ENCA,
			Ports.DIO_ELEVATOR_ENCB);
	private Solenoid brake = new Solenoid(Ports.Sol_ElevBrake);

	private Solenoid gripper = new Solenoid(Ports.Sol_Gripper);
	private DigitalInput topStop = new DigitalInput(
			Ports.DIO_HallEffectSensorTop);
	private DigitalInput bottomStop = new DigitalInput(
			Ports.DIO_HallEffectSensorBottom);
	private PowerDistributionPanel PDP = new PowerDistributionPanel();

	public Elevator() {
		Periodic p = new Periodic() {
			private double lastSlider;
			private double slider;

			@Override
			protected void initialize() {
				lastSlider = slider = 0;
			}

			protected void execute() {
				System.out.println("Elevator Current: " + getElevatorCurrent());
				// If elevator current is big, drop the smoother's max and
				// enlarge min output
				// Try and make them be scaled, i,e. the higher the current,
				// thee smaller the value
				// else, set both to 1 and -1 respectively

				// Move Elevator to slider
				slider = CommandBase.OI.getSlider();

				if (Math.abs(slider - lastSlider) <= RobotValues.SliderChangeTolerance) {
					// Convert the slider from -1 - 1 to 0 - TopHeight
					goal = (((-RobotValues.ElevatorTopHeight) / (2)) * (slider + 1));
					new MoveElevatorHeight(goal).start();
				}

				// Reset the elevator
				if (getTopStop())
					RobotValues.ElevatorTopHeight = getEnc();
				if (getBottomStop())
					resetEnc();

				// update Brake
				setBrake(CommandBase.OI.getStop());

				lastSlider = slider;
			}

		};
		p.start();
		gripper = new Solenoid(Ports.Sol_Gripper);
	}

	public void initDefaultCommand() {
	}

	public void setElevatorSpeed(double speed) {
		if (((speed > stopTolerance) && (getTopStop()))
				|| ((speed < stopTolerance) && getBottomStop()))
			Elevator.set(0);

		if (Math.abs(speed) <= stopTolerance)
			setBrake(true);
		else
			setBrake(false);

		Elevator.set(speed + GRAVITY_OFFSET);
	}

	public void setElevatorSpeedRaw(double in) {
		Elevator.set(in + GRAVITY_OFFSET);
	}

	public void resetEnc() {
		liftPos.reset();
	}

	public double getEnc() {
		return liftPos.getDistance();
	}

	public double getGoal() {
		return goal;
	}

	public void setGoal(double goal) {
		this.goal = goal;
	}

	public void setBrake(boolean stop) {
		Elevator.set(0);
		brake.set(!stop);
	}

	public boolean getGripper() {
		return gripper.get();
	}

	public void setGripper(boolean grip, boolean calibrateGravityOffset) { // true
																			// =
																			// closed
		gripper.set(!grip);
		if (calibrateGravityOffset && grip) {
			Timer.delay(.2);
			Encoder calibrator = liftPos;
			calibrator.reset();
			Elevator.set(.3);
			double decreases = 0;
			double lastValue = 0;
			while (decreases < 3) {
				double velocity = calibrator.getRate();
				calibrator.reset();
				if (velocity < lastValue)
					decreases++;
				lastValue = velocity;
			}
			double currentPower = .2; // Need to tune so neutral
			calibrator.reset();
			while (calibrator.getDistance() < .03) {
				currentPower += .2;// Increase for less accuracy but faster
									// calibrating
				Elevator.set(currentPower);
				Timer.delay(.05);// Give time to react to change.
			}
		}
	}

	public boolean getTopStop() {
		return topStop.get();
	}

	public boolean getBottomStop() {
		return bottomStop.get();
	}

	public double getElevatorCurrent() {
		return (PDP.getCurrent(0) + PDP.getCurrent(1)) / 2d;
	}

}
