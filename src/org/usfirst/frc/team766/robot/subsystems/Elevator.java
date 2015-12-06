package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.Ports;
import org.usfirst.frc.team766.robot.RobotValues;

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
	private static final double ELEVATOR_BOTTOM_HEIGHT = 0;
	private static final int NUM_TEETH_SPROCKET = 24;
	private static final double CHAIN_PITCH = 3.0 / 8.0 * RobotValues.INCHES_TO_METERS;
	private static final double DISTANCE_PER_SPROCKET_ROTATION = CHAIN_PITCH
			* NUM_TEETH_SPROCKET;
	private static final int PULSES_PER_ROTATION = 256;
	private static final double DISTANCE_PER_PULSE = DISTANCE_PER_SPROCKET_ROTATION
			/ PULSES_PER_ROTATION;
	private static final boolean DYNAMIC_CALIBRATION = false;
	private static final double GRAVITY_COUNTERBALANCE = .05;
	// .02 starts going down, .11 starts going up. range can be from .03 to .1
	// Offset of just weight of mechanism. PID should compensate for rest

	private double gravityOffset = GRAVITY_COUNTERBALANCE;
	private boolean emergencyStopped = false;

	private Victor ElevatorA = new Victor(Ports.PWM_ElevatorA);
	private Victor ElevatorB = new Victor(Ports.PWM_ElevatorB);
	private Victor ElevatorC = new Victor(Ports.PWM_ElevatorC);
	
	private Encoder liftPos = new Encoder(Ports.DIO_ELEVATOR_ENCA,
			Ports.DIO_ELEVATOR_ENCB);
	private Solenoid brake = new Solenoid(Ports.Sol_ElevBrake);

	private Solenoid gripper = new Solenoid(Ports.Sol_Gripper);
//	private DigitalInput topStop = new DigitalInput(
//			Ports.DIO_HallEffectSensorTop);
//	private DigitalInput bottomStop = new DigitalInput(
//			Ports.DIO_HallEffectSensorBottom);
	private PowerDistributionPanel PDP = new PowerDistributionPanel();

	public Elevator() {
		liftPos.setDistancePerPulse(DISTANCE_PER_PULSE);
	}

	public void initDefaultCommand() {
		// setDefaultCommand(new Slider());
	}

	public void setElevatorSpeed(double speed) {


		// Compensating for deadband
		if (speed < 0)
			speed -= .06;
		else if (speed > 0)
			speed += .06;

		// Emergency stop. If elevator is at top stop, allow elevator to descend
		// but not rise. If elevator is at bottom stop allow elevator to rise
		// but not descend.
		emergencyStopped = (tooHigh() && speed > 0) || (tooLow() && speed < 0);

		if (emergencyStopped) {
			speed = 0;
		}

		setElevatorMotors(speed
				+ (DYNAMIC_CALIBRATION ? gravityOffset : GRAVITY_COUNTERBALANCE));
	}

	public void setElevatorSpeedRaw(double in) {
		setElevatorMotors(in
				+ (DYNAMIC_CALIBRATION ? gravityOffset : GRAVITY_COUNTERBALANCE));
	}

	public void resetEncoders() {
		liftPos.reset();
	}

	public double getEncoders() {
		return liftPos.getDistance()-ELEVATOR_BOTTOM_HEIGHT;
	}

	public double getVelocity() {// meters per second
		return liftPos.getRate();
	}

	public void setBrake(boolean stop) {
		if (stop)
			setElevatorMotors(0);
		brake.set(stop);
	}

	public boolean getBrake() {
		return brake.get();
	}

	public boolean getGripper() {
		return gripper.get();
	}

	// grip == true = closed
	public void setGripper(boolean grip) {
		gripper.set(grip);
		if (DYNAMIC_CALIBRATION) {
			if (grip) {
				Timer.delay(.2);
				Encoder calibrator = liftPos;
				calibrator.reset();
				setElevatorMotors(.3);
				double decreases = 0;
				double lastValue = 0;
				while (decreases < 3) {
					double velocity = calibrator.getRate();
					calibrator.reset();
					if (velocity < lastValue)
						decreases++;
					lastValue = velocity;
				}
				double currentPower = GRAVITY_COUNTERBALANCE;
				calibrator.reset();
				while (calibrator.getDistance() < .03) {
					currentPower += .2;// Increase for less accuracy but faster
										// calibrating
					setElevatorMotors(currentPower);
					Timer.delay(.05);// Give time to react to change.
				}
				gravityOffset = currentPower;
			} else
				gravityOffset = GRAVITY_COUNTERBALANCE;
		}
	}

	public boolean isEmergencyStopped() {
		return emergencyStopped;
	}

	public boolean getTopStop() {
		// Add when we get hall effect sensors on the robot
		// boolean out = topStop.get();
		// if(out)
		// RobotValues.ElevatorTopHeight = getEncoders();
		// return out;
		return false;// for testing
	}

	public boolean getBottomStop() {
		// Add when we get hall effect sensors on the robot
		// boolean out = bottomStop.get();
		// if(out)
		// resetEncoders();
		// return out;
		return false;// for testing
	}

	public double getElevatorCurrent() {
		return (PDP.getCurrent(0) + PDP.getCurrent(1)) / 2d;
	}

	private boolean tooHigh() {
		return getTopStop() || getEncoders() >= RobotValues.ElevatorTopHeight;
	}

	private boolean tooLow() {
		return getBottomStop() || getEncoders() <= 0;
	}
	private void setElevatorMotors(double s)
	{
		ElevatorA.set(s);
		ElevatorB.set(s);
		ElevatorC.set(s);
	}

}
