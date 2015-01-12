package org.usfirst.frc.team766.robot;

import edu.wpi.first.wpilibj.AnalogInput;
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
	public static final int Sol_Shifter = 0;
	public static final int Sol_Light = 2;
	
	//Encoders
    public static final int DIO_LDriveEncA = 1;
    public static final int DIO_LDriveEncB = 2;
    public static final int DIO_RDriveEncA = 14;
    public static final int DIO_RDriveEncB = 13;
    
    //Limit Switchs
    public static final int DIO_LimitSwitch = 5;
    
    //Ultrasonnic Range Finder
	public static final int ULTRASONIC_PING = 3;
	public static final int ULTRASONIC_ECHO = 4;
	
	//Testing
	public static final int POTENTIOMETER = 6;
}
