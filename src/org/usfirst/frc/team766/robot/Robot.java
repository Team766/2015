package org.usfirst.frc.team766.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Drive.TankDrive;
import org.usfirst.frc.team766.robot.commands.Drive.CheesyDriveCommand;

/*
 * 2015 Java Code
 * author: Brett Levenson
 */
public class Robot extends IterativeRobot {

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	CommandBase.init();
    	SmartDashboard.putBoolean("Tank Drive", false);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    }

    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	CommandBase.OI.setTankDrive(SmartDashboard.getBoolean("Tank Drive"));
    	
    	if(!CommandBase.OI.getTankDrive()){
            new CheesyDriveCommand().start();			
        }else{
            new TankDrive().start();
        }
    }

    public void disabledInit(){}

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
