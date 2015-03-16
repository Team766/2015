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
    
    //BoxOp (Elevator)
    public static final int ToggleGripper = 1;
    public static final int StackAdditionalToteBoxOp= 2;
    public static final int IntakeFeeder = 3;
    public static final int DriveGround= 4;
    public static final int BrakeOff = 7;
    public static final int BrakeOn = 8;
    public static final int BoxStop = 9;
    public static final int ElevatorCancel = 5;//NO SPARE SWITCHES.
    public static final int IntakeWheelsIn= 10;
    public static final int IntakeWheelsOut= 11;
	public static final int GraspToteIntake = 12;
	public static final int preset1 = 14;
	public static final int preset2 = 15;
	public static final int preset3 = 16;
	public static final int preset4 = 17;
	public static final int preset5 = 18;
	public static final int preset6 = 19;
	public static final int ElevatorCancelAutomation = 21;
	
	//Test Joystick
	public static final int BrakeOffTest= 7;
	public static final int BrakeOnTest= 8;
	public static final int gripperOpen= 11;
	public static final int gripperClose = 12;
	public static final int intakeIn= 4;
	public static final int intakeOut= 6;
	public static final int StackAdditionalToteTest= 9;
	public static final int StackAdditionalSmall = 10;
	public static final int StackAdditionalChute = 2;
	
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
