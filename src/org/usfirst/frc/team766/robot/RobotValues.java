package org.usfirst.frc.team766.robot;

public class RobotValues {
	// Cheesy drive values
	public static final double sensitivityHigh = .85d;
	public static final double sensitivityLow = .75d;
	public static final double distanceFromBox = 5d;
	public static final double safteyDriveDistance = 10;
	public static final double driveDividor = 10;

	public enum ElevatorState {
		BOTTOM, MIDDLE, TOP, MANUAL_CONTROL
	}

	// Autonomous
	// Modes
	public static final int Auton_VisionDrive = 0;
	public static final int Auton_Move = 1;

	public static final int Auton_Max = 1;
	public static final int Auton_Min = 0;

	// Values
	public static final double DriveForwardDistance = 10;

	// Bearly Drive
	// Decrease for faster reaction times
	public static final double alpha = 0.9;
	
	//Elevator presets
	public static final int bottomPreset = 1;//Open to change. Prototype value
	public static final int middlePreset = 3;//Open to change. Prototype value
	public static final int topPreset = 5;//Open to change. Prototype value
	
	// Values from here down are last year's values

	// drive
	public static final double Kp = 0.4;
	public static final double Kd = 0.2;
	public static final double driveTolerance = .01;

	// gyro turning

	public static final double AngleKp = 0.3;
	public static final double AngleKi = 0.0;
	public static final double AngleKd = 0.0;
	public static final double Angleoutputmax_low = -0.3;
	public static final double Angleoutputmax_high = 0.3;
	public static final double AngleThreshold = 1;

	// drive straight

	public static final double DriveKp = 0.0;
	public static final double DriveKi = 0.0;
	public static final double DriveKd = 0.0;
	public static final double Driveoutputmax_low = -0.5;
	public static final double Driveoutputmax_high = 0.5;
	public static final double DriveThreshold = .01;

	public static final double StraightKp = 0.0;
	public static final double StraightKi = 0.0;
	public static final double StraightKd = 0.0;
	public static final double Straightoutputmax_low = -0.1;
	public static final double Straightoutputmax_high = 0.1;
	public static final double StraightThreshold = Double.MIN_NORMAL; // so it never stops
															// trying
	
	//drive smoothly
	public static final double SmootherLeftKp = .1;
	public static final double SmootherRightKp = .1;
	public static final double SmootherLeftKi = .2;
	public static final double SmootherRightKi = .2;
	
	//elvate smoothly
	public static final double ElevatorKp = .1;
	public static final double ElevatorKi = .1;
}