package org.usfirst.frc.team766.robot;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Ports {
	//Drive
	public static final int PWM_Left_Drive = 0;
	public static final int PWM_Right_Drive = 1;
	public static final int PWM_Elevators = 2;
	
	//Solenoids
	public static final int Sol_Shifter = 1;
	
	//Encoders
    public static final int DIO_LDriveEncA = 1;
    public static final int DIO_LDriveEncB = 2;
    public static final int DIO_RDriveEncA = 14;
    public static final int DIO_RDriveEncB = 13;
    
    //Ultrasonnic Range Finder
	public static final int ULTRASONIC_PING = 1;
	public static final int ULTRASONIC_ECHO = 2;
}
