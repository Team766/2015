package org.usfirst.frc.team766.robot;

public class RobotValues {
	//Conversion constants
	public static final double INCHES_TO_METERS = 0.0254;
	
	// Cheesy drive values
	public static final double sensitivityHigh = .85d;
	public static final double sensitivityLow = .75d;
	public static final double distanceFromBox = 5d;
	public static final double safteyDriveDistance = 10;
	public static final double driveDividor = 10;

	//Encoder Height of elevator, needs to be changed
	public static double ElevatorTopHeight = 100;
	
	// Autonomous
	// Modes
	public static final int Auton_VisionDrive = 0;
	public static final int Auton_Move = 1;
	public static final int Auton_Rotate = 3;
	public static final int Auton_None = 5;
	public static final int Auton_PickOneBox = 2;
	public static final int Auton_MoveToLandfill = 4;
	public static final int Auton_Push3Boxes = 6;
	
	public static final int Auton_Max = 6;
	public static final int Auton_Min = 0;

	// Values for Autons
	public static final double DriveForwardDistance = 107 * INCHES_TO_METERS;//Distance to landmark
	public static final double DistanceToLandfill = (646.88/2 - 39 - 51 -20) * INCHES_TO_METERS;//20 just to give more room to ultrasonic drive
	public static final double OptimalGripperDistance = 0.45;
	public static final double DistanceBetweenBoxes = 33; //Distance from box edge to box edge
	public static final double Box_Width = 48 * INCHES_TO_METERS;
	
	// Bearly Drive
	// Decrease for faster reaction times
	public static final double alpha = 0.5;
	
	//Elevator presets
	private static final int ElevatorPresetBase = 1;//All values based on this one
	public static final int[] ElevatorPresets = {0,ElevatorPresetBase,ElevatorPresetBase*2,ElevatorPresetBase*3,ElevatorPresetBase*4,ElevatorPresetBase*5,ElevatorPresetBase*6};
	
	//Gyro DriveTurn
	public static double TurnAngleKp = 0.35;
	public static double TurnAngleKi = 0.01;
	public static double TurnAngleKd = 0.15;

	// drive straight

	public static final double DriveKp = 0.2;//Decrease to decrease robot speed
	public static final double DriveKi = 0.01;
	public static final double DriveKd = 0.1;
	public static final double Driveoutputmax_low = -10;
	public static final double Driveoutputmax_high = 10;
	public static final double DriveThreshold = .05;
	
	//Ultrasonic
	public static final double UltrasonicDriveKp = 6.4;
	public static final double UltrasonicDriveKi = 0.0;//0.01
	public static final double UltrasonicDriveKd = 0.0;
	
	// gyro turning in drive forward
	public static final double AngleKp = 0.05;
	public static final double AngleKi = 0.00;
	public static final double AngleKd = 0.03;
	public static final double Angleoutputmax_low = -10;
	public static final double Angleoutputmax_high = 10;
	public static final double AngleThreshold = Double.MIN_VALUE;
	
	
	//drive smoothly
	public static final double SmootherLeftKp = .1;
	public static final double SmootherRightKp = .1;
	public static final double SmootherLeftKi = .2;
	public static final double SmootherRightKi = .2;
	
	//elevate smoothly
	public static final double ElevatorKp = .1;
	public static final double ElevatorKi = .01;
	public static final double ElevatorKd = 0;
	public static final double ElevatorThreshold = .01;
	public static final double ElevatorMaxSpeed = 1;
	public static final double ElevatorMinSpeed = 1;
	
	//Intake PID
	public static final double 
		IntakeKP = 0.01,
		IntakeKI = 0.0,
		IntakeKD = 0.0,
		IntakeThreshold = .01;
	
	//Elevator
	public static final double SliderChangeTolerance = 0.1;

	public static final double k_moveToNextTote = 33 * INCHES_TO_METERS;
	
}
