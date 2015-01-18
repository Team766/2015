package org.usfirst.frc.team766.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Autons.AutonSelectorCommand;
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
	public static NetworkTable table;
	private AutonSelectorCommand auton;
	private boolean AutonCyclePrev;
	
    public void robotInit() {
    	CommandBase.init();
    	SmartDashboard.putBoolean("Tank Drive", false);
    	table = NetworkTable.getTable("dataTable");
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
    	auton = new AutonSelectorCommand(CommandBase.OI.AutonMode).start();
    }

    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
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
    	
    	//Vision variables
    	table.putBoolean("done", true);
    	table.putNumber("leftMotor", 0d);
    	table.putNumber("rightMotor", 0d);
    }

    public void disabledInit(){
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //table.putBoolean("itWorked", true);
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
