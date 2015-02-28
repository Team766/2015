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
	private static final boolean DYNAMIC_CALIBRATION = true;
	private static final double GRAVITY_COUNTERBALANCE = .1; // Needs to be
																// calculated.
	// Offset of just
	// mechanism. PID should
	// compensate for rest
	// -Patrick

	private double gravityOffset = GRAVITY_COUNTERBALANCE;
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

		Elevator.set(speed
				+ (DYNAMIC_CALIBRATION ? gravityOffset : GRAVITY_COUNTERBALANCE));
	}

	public void setElevatorSpeedRaw(double in) {
		Elevator.set(in
				+ (DYNAMIC_CALIBRATION ? gravityOffset : GRAVITY_COUNTERBALANCE));
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

	public void setGripper(boolean grip) { // true
											// =
											// closed
		gripper.set(!grip);
		if (DYNAMIC_CALIBRATION && grip) {
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
			double currentPower = GRAVITY_COUNTERBALANCE;
			calibrator.reset();
			while (calibrator.getDistance() < .03) {
				currentPower += .2;// Increase for less accuracy but faster
									// calibrating
				Elevator.set(currentPower);
				Timer.delay(.05);// Give time to react to change.
			}
			gravityOffset = currentPower;
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
