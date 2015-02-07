package org.usfirst.frc.team766.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Autons.AutonSelectorCommand;
import org.usfirst.frc.team766.robot.commands.Autons.OpenCvTest;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.TankDrive;
import org.usfirst.frc.team766.robot.commands.Drive.CheesyDriveCommand;

/**
 * 2015 Java Code main class
 * 
 * @author Brett Levenson
 */

public class Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	private AutonSelectorCommand auton;
	private boolean AutonCyclePrev;
	
    public void robotInit() {
    	CommandBase.init();
    	SmartDashboard.putBoolean("Tank Drive", false);
    	SmartDashboard.putNumber("Alpha", 0.5);
    	//SmartDashboard.putData("Test Hall Effect Sensor", new TestHallEffectSensor()); //Not necessary. Uncomment if you want to test.
    	SmartDashboard.putData(new OpenCvTest());
    	SmartDashboard.putData(new DriveTurn(90));
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
		//Auton Cyvler
		if(!AutonCyclePrev && CommandBase.OI.buttonAutonIncrement.get())
    		CommandBase.OI.incrementAutonMode(1);
    	else if(!AutonCyclePrev && CommandBase.OI.buttonAutonDecrement.get())
    		CommandBase.OI.incrementAutonMode(-1);
    	AutonCyclePrev = (CommandBase.OI.buttonAutonIncrement.get() || CommandBase.OI.buttonAutonDecrement.get());
	}

    public void autonomousInit() {
    	/*
    	 * Run auton command
    	 * Need to create an auton
    	 * selector that uses OI and
    	 * smartdashboard to allow autons
    	 * to be selected...Add here
    	 */

    	
//    	auton = new AutonSelectorCommand(CommandBase.OI.AutonMode);
//    	auton.start();
    	
    }

    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        System.out.println(CommandBase.Drive.getAngle());
    }

    public void teleopInit() {
    	//cancel auton
	if (auton != null) auton.cancel();
    	
    	CommandBase.OI.setTankDrive(SmartDashboard.getBoolean("Tank Drive"));
    	
    	if(!CommandBase.OI.getTankDrive()){
            new CheesyDriveCommand().start();			
        }else{
            new TankDrive().start();
        }
    	
    	//new UpdateElevatorInput();
    	
    }

    public void disabledInit(){
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
