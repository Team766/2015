package org.usfirst.frc.team766.robot;

<<<<<<< HEAD
import org.usfirst.frc.team766.robot.RobotValues;
=======
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorBottom;
>>>>>>> FETCH_HEAD

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
    buttonDriverOverride = new JoystickButton(jRight, Buttons.DriverOverride),
<<<<<<< HEAD
    
    buttonAutonIncrement = new JoystickButton(jBox, Buttons.AutonIncrement),
    buttonAutonDecrement = new JoystickButton(jBox, Buttons.AutonDecrement);    
=======
    buttonCancelElevator = new JoystickButton(jRight, Buttons.CancelElevator);
>>>>>>> FETCH_HEAD
  
    
    //Auton Stuff
    public int AutonMode = 0;
    public boolean TankDrive = false;
    public boolean UseGamepad = false;
    
	public OI(){
        //buttonDriverPickup.whileHeld(new Command);
		buttonCancelElevator.cancelWhenPressed(new MoveElevatorBottom());
		//Repeat for MoveElevatorTop(), etc...
	}
	
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
	//tank drive
	public double getLeft(){
		return jLeft.getY();
	}
	public double getRight(){
		return jRight.getY();
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
	
	/**
	 * Adds or subtracts the current auton mode.
	 * @param direction: negative if decrementing and positive if incrementing.
	 */
	public void incrementAutonMode(int direction){
		//increment
		if(direction > 0)AutonMode++;
		//decrement
		else if(direction < 0)AutonMode--;
		else System.out.println("incrementAutonMode can't take parm 0!");
        
		//Reset the auton cycle
        if (AutonMode > RobotValues.Auton_Max){
            AutonMode = RobotValues.Auton_Min;
        }
        else if (AutonMode < RobotValues.Auton_Min){
            AutonMode = RobotValues.Auton_Max;
        }
	}
}

