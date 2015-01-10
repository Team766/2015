package org.usfirst.frc.team766.robot;

public class RobotValues {
	//Cheesy drive values
    public static final double sensitivityHigh = .85d;
    public static final double sensitivityLow = .75d;
	public static final double distanceFromBox = 5d;
	public static final double safteyDriveDistance = 10;
	public static final double driveDividor = 10;
	
	public enum ElevatorState{
		BOTTOM, MIDDLE, TOP, MANUAL_CONTROL
	}
}
