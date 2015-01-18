package org.usfirst.frc.team766.robot;

public class RobotValues {
	//Cheesy drive values
    public static final double sensitivityHigh = .85d;
    public static final double sensitivityLow = .75d;
	public static final double distanceFromBox = 5d;
	public static final double safteyDriveDistance = 10;
	public static final double driveDividor = 10;
	
	//Autonomous
		//Modes
		public static final int Auton_VisionDrive = 0;
		public static final int Auton_Move = 1;
		
		public static final int Auton_Max = 1;
		public static final int Auton_Min = 0;
		
		//Values
		public static final double DriveForwardDistance = 10;
	
	//Bearly Drive
	//Decrease for faster reaction times
	public static final double alpha = 0.9;
	
	public enum ElevatorState{
		BOTTOM, MIDDLE, TOP, MANUAL_CONTROL
	}
}
