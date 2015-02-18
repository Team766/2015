package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.Ports;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorHeight;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the elevator
 * 
 * TODO: -Add presets to arm.
 *
 * @author Blevenson
 * @author PKao
 */

public class Elevator extends Subsystem {
	private static final double GRAVITY_OFFSET = .05;
	
	private double stopTolerance = 0.001;

	private RobotValues.ElevatorState currentState;
	//Goal: wanted position, between 0 and Elevator Encoder Height  
	private double goal = 0;

	private Victor Elevator = new Victor(Ports.PWM_Elevators);
	private Encoder liftPos = new Encoder(Ports.DIO_ELEVATOR_ENCA,
			Ports.DIO_ELEVATOR_ENCB);
	private Solenoid brake = new Solenoid(Ports.Sol_ElevBrake);

	private double targetSpeed = 0;
	private PIDController smoother = new PIDController(RobotValues.ElevatorKp,
			RobotValues.ElevatorKi, 0, targetSpeed);
	private Solenoid gripper = new Solenoid(Ports.Sol_Gripper);
	private DigitalInput topStop = new DigitalInput(Ports.DIO_HallEffectSensorTop);
	private DigitalInput bottomStop = new DigitalInput(Ports.DIO_HallEffectSensorBottom);
	private PowerDistributionPanel PDP = new PowerDistributionPanel();
	
	public Elevator() {
		ChangeLimiter changeLimiter = new ChangeLimiter(){
			private double lastSlider;
			private double slider;
			
			@Override
			protected void initialize() {
				lastSlider = slider = 0;
			}

			protected void execute() {
				System.out.println("Elevator Current: " + getElevatorCurrent());
				//If elevator current is big, drop the smoother's max and enlarge min output
				//Try and make them be scaled, i,e. the higher the current, thee smaller the value
				//else, set both to 1 and -1 respectively
				
				smoother.calculate(Elevator.get(), false);
				double currentPID = smoother.getOutput();
				Elevator.set(currentPID<0?currentPID + GRAVITY_OFFSET: currentPID );
				
				//Move Elevator to slider
				slider = CommandBase.OI.getSlider();
				
				if(Math.abs(slider - lastSlider) <= RobotValues.SliderChangeTolerance)
				{
					//Convert the slider from -1 - 1 to 0 - TopHeight
					goal = (((-RobotValues.ElevatorTopHeight) / (2)) * (slider + 1));
					new MoveElevatorHeight(goal).start();
				}
				
				//Reset the elevator
				if(getTopStop())
					RobotValues.ElevatorTopHeight = getEnc();
				if(getBottomStop())
					resetEnc();
				
				// update Brake
				setBrake(CommandBase.OI.getStop());
				
				lastSlider = slider;
			}


		};
		changeLimiter.start();
		gripper = new Solenoid(Ports.Sol_Gripper);
	}

	public void initDefaultCommand() {
	}

	public void setElevatorSpeed(double speed) {
		if(((speed > stopTolerance) && (getTopStop())) ||
				((speed < stopTolerance) && getBottomStop()))
			Elevator.set(0);
		
		if (Math.abs(speed) <= stopTolerance)
			setBrake(true);
		else
			setBrake(false);

		smoother.setSetpoint(speed);
	}
	
	public void setElevatorSpeedRaw(double in)
	{
		Elevator.set(in);
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

	public RobotValues.ElevatorState getState() {
		return currentState;
	}

	public void setState(RobotValues.ElevatorState currentState) {
		this.currentState = currentState;
	}

	public void setBrake(boolean stop) {
		Elevator.set(0);
		brake.set(!stop);
	}
	
	public boolean getGripper(){
		return gripper.get();
	}
	
	public void setGripper(boolean toGripOrNotToGrip){//To do: figure out if setting to true closes or opens arm. For now, true = closed. I'm sorry, try not to change the name unless necessary
		gripper.set(!toGripOrNotToGrip);
	}
	
	public boolean getTopStop()
	{
		return topStop.get();
	}
	public boolean getBottomStop()
	{
		return bottomStop.get();
	}

	public double getElevatorCurrent() {
		return (PDP.getCurrent(0) + PDP.getCurrent(1)) / 2d;
	}

}
