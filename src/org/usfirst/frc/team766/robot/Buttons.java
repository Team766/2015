package org.usfirst.frc.team766.robot;

public class Buttons {
	public static final int Shifter = 1;
    public static final int Reverse = 2;
    public static final int QuickTurn = 2;
    public static final int DriverPickup = 7;
    public static final int DriverShoot = 1;
    public static final int DriverOverride = 3;
    public static final int DriverSmoothing = 4; //Feel free to change if not practical or wan to use button 4 for something else
    
    //BoxOp
	public static final int BoxStop = 1;
	
	
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
