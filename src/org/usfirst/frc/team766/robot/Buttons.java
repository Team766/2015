package org.usfirst.frc.team766.robot;

public class Buttons {
	public static final int Shifter = 1;
    public static final int Reverse = 2;
    public static final int QuickTurn = 2;
    public static final int DriverPickup = 7;
    public static final int DriverShoot = 1;
    public static final int DriverOverride = 3;
    
    /*
     * Auton:
     * AutonIncrement and AutonDecrement will be removed and other
     * buttons on the OI will be used.  No need to have specific buttons
     * for one job.  Since the auton cycle occurs during disabled, we can 
     * have buttons with multiple purposes.
     */
	public static final int AutonIncrement = 1;
	public static final int AutonDecrement = 2;
    public static final int CancelElevator = 0; //set up this variable when button number confirmed.
}
