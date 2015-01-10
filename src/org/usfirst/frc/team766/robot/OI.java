package org.usfirst.frc.team766.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick
    jLeft = new Joystick(0),
    jRight = new Joystick(1),
    jBox = new Joystick(2);
    
    public Button      
    buttonShifter = new JoystickButton(jLeft, Buttons.Shifter),
    buttonQuickTurn = new JoystickButton(jRight, Buttons.QuickTurn),
    buttonReverse = new JoystickButton(jRight, Buttons.Reverse),
    buttonDriverPickup = new JoystickButton(jRight, Buttons.DriverPickup),
    buttonDriverShoot = new JoystickButton(jRight, Buttons.DriverShoot),
    buttonDriverOverride = new JoystickButton(jRight, Buttons.DriverOverride);
  
    
    //Auton Stuff
    public int AutonMode = 0;
    public boolean TankDrive = false;
    public boolean UseGamepad = false;
    
	public OI(){
    
        //buttonDriverPickup.whileHeld(new Command);
	}
	
	//interface for gamepad support
	//we can map commands to multiple buttons, but not for drive inputs
	public boolean getShifter(){
		return buttonShifter.get();
	}
	public boolean getQuickTurn(){
		return buttonQuickTurn.get();
	}
	public boolean getReverse(){
		return buttonReverse.get();
	}
	
	public double getThrottle(){
		return jLeft.getY();
	}
	public double getSteer(){
		return jRight.getX();
	}
	//tank drive here
	public double getLeft(){
		return jLeft.getRawAxis(2);
	}
	public double getRight(){
		return jRight.getRawAxis(2);
	}
	/**
	 * Originally the gripper switch would be in the off
	 * position by default. Then, we removed manual control
	 * of the grippers. Now the grippers switch will by default be in the
	 * 'on' positition on the OI, but in code, 'on' will be
	 * its off position. This makes it easier to deal with commands, as
	 * we only want it to be running its command when it is not in its
	 * relaxed state.
	 * 
	 * <p>Previously the button would be 'on'
	 * if it was less than 0. Now, it'll be 
	 * true when greater than 0.
	 */
	public void setTankDrive(boolean in){
		TankDrive = in;
	}
	public boolean getTankDrive(){
		return TankDrive;
	}
	public void setUseGamepad(boolean in){
		UseGamepad = in;
	}
	public boolean getUseGamepad(){
		return UseGamepad;
	}

	public boolean getOverride() {
		return buttonDriverOverride.get();
	}
}

