package org.usfirst.frc.team766.robot;

public class RobotValues {
	// Cheesy drive values
	public static final double sensitivityHigh = .85d;
	public static final double sensitivityLow = .75d;
	public static final double distanceFromBox = 5d;
	public static final double safteyDriveDistance = 10;
	public static final double driveDividor = 10;

	//Elevator
	public enum ElevatorState {
		BOTTOM, MIDDLE, TOP, MANUAL_CONTROL
	}
	//Encoder Height of elevator, needs to be changed
	public static double ElevatorTopHeight = 100;
	
	// Autonomous
	// Modes
	public static final int Auton_VisionDrive = 0;
	public static final int Auton_Move = 1;
	public static final int Auton_PickOneBox = 2;
	public static final int Auton_Rotate = 3;
	
	public static final int Auton_Max = 3;
	public static final int Auton_Min = 0;

	// Values for Autons
	public static final double DriveForwardDistance = 2;
	
	// Bearly Drive
	// Decrease for faster reaction times
	public static final double alpha = 0.5;
	
	//Elevator presets
	public static final int bottomPreset = 1;//Open to change. Prototype value
	public static final int middlePreset = 3;//Open to change. Prototype value
	public static final int topPreset = 5;//Open to change. Prototype value
	public static final int ElevatorPreset2 = 2;
	public static final int ElevatorPreset3 = 2;
	public static final int ElevatorPreset5 = 2;
	public static final int ElevatorPreset6 = 2;
	
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
	public static final double ElevatorKi = .1;
	
	//Intake PID
	public static final double 
		IntakeKP = 0.01,
		IntakeKI = 0.0,
		IntakeKD = 0.0,
		IntakeThreshold = .01;
	
	//Elevator
	public static final double SliderChangeTollerance = 0.1;
	
}
