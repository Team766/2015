package org.usfirst.frc.team766.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Ports {
	//Drive
	public static final int PWM_Left_Drive = 1;
	public static final int PWM_Right_Drive = 0;
//	public static final int PWM_IntakeR = 3;
//	public static final int PWM_IntakeL = 4;
	public static final int PWM_IntakeWheelR = 4;
	public static final int PWM_IntakeWheelL = 6;
	
	//Elevator
	public static final int PWM_ElevatorA = 5;
	public static final int PWM_ElevatorB = 7;
	public static final int PWM_ElevatorC = 8;
	
	//Solenoids
	public static final int Sol_Shifter = 0;
	public static final int Sol_ElevBrake = 1;
	public static final int Sol_Gripper = 2;
	public static final int Sol_leftArm = 3;
	public static final int Sol_rightArm = 4;
	
	//Grabber
	public static final int PWM_GrabberL = 3;
	public static final int PWM_GrabberR = 4;
	
	//Encoders
    public static final int 
		DIO_LDriveEncA = 0,
		DIO_LDriveEncB = 1,
		DIO_RDriveEncA = 8,
		DIO_RDriveEncB = 9,
		DIO_ELEVATOR_ENCA = 2,
		DIO_ELEVATOR_ENCB = 3,
		DIO_LIntakeEncA = 4,
		DIO_LIntakeEncB = 5,
		DIO_RIntakeEncA = 6,
		DIO_RIntakeEncB = 7;
	
	public static final int GYRO = 0;
	
	//Hall Effect Sensor
	public static final int DIO_HallEffectSensorBottom = 10;
	public static final int DIO_HallEffectSensorTop = 11;
}
