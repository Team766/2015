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

public class SideSwipe extends IterativeRobot {
	
	private AutonSelectorCommand auton;
	private boolean AutonCyclePrev;
	private boolean done;
	
    public void robotInit() {
    	CommandBase.init();
    	SmartDashboard.putBoolean("Tank Drive", false);
    	SmartDashboard.putNumber("Alpha", 0.5);
    	SmartDashboard.putData(new OpenCvTest());
    	SmartDashboard.putData(new DriveTurn(90));
    	done = false;
    	CommandBase.myLog.print("SideSwipe 2015 Code");
    	SmartDashboard.putNumber("P", RobotValues.DriveKp);
    	SmartDashboard.putNumber("I", RobotValues.DriveKi);
    	SmartDashboard.putNumber("D", RobotValues.DriveKd);
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
    	RobotValues.TurnAngleKp = SmartDashboard.getNumber("P");
    	RobotValues.TurnAngleKi = SmartDashboard.getNumber("I");
    	RobotValues.TurnAngleKd = SmartDashboard.getNumber("D");
    	
    	
    	
    	/*
    	 * Run auton command
    	 * Need to create an auton
    	 * selector that uses OI and
    	 * smartdashboard to allow autons
    	 * to be selected...Add here
    	 */
    	done = true;
    	
    	auton = new AutonSelectorCommand(CommandBase.OI.AutonMode);
    	auton.start();
    	
    }

    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    	SmartDashboard.putNumber("gyro", CommandBase.Drive.getAngle());
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
    	done = true;
    }

    public void disabledInit(){
    	if(done)CommandBase.myLog.closeFile();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
