package org.usfirst.frc.team766.robot;

public class Buttons {
	public static final int Shifter = 1;
    public static final int Reverse = 2;
    public static final int QuickTurn = 2;
    public static final int DriverPickup = 7;
    public static final int DriverSlowMode = 3; //Pick a better number
    public static final int DriverShoot = 1;
    public static final int DriverOverride = 3;
    public static final int DriverSmoothing = 4; //Feel free to change if not practical or wan to use button 4 for something else
    
    //BoxOp
	public static final int BoxStop = 1;
	//Elevator
	public static final int ElevatorClamp = 2;
	public static final int preset1 = 3;
	public static final int preset2 = 4;
	public static final int preset3 = 5;
	public static final int preset4 = 6;
	public static final int preset5 = 7;
	public static final int preset6 = 8;
	public static final int preset7 = 9; 
	
	//Test Joystick
	public static final int BrakeOff= 7;
	public static final int BrakeOn= 8;
	public static final int gripperOpen= 11;
	public static final int gripperClose = 12;
	public static final int intakeIn= 4;
	public static final int intakeOut= 6;
	public static final int stackAdditionalTote= 9;
	public static final int stackAdditionalSmall = 10;
	public static final int stackAdditionalChute = 2;
	
    /*
     * Auton:
     * AutonIncrement and AutonDecrement will be removed and other
     * buttons on the OI will be used.  No need to have specific buttons
     * for one job.  Since the auton cycle occurs during disabled, we can 
     * have buttons with multiple purposes.
     */
	public static final int AutonIncrement = 1;
	public static final int AutonDecrement = 2;
	

}
