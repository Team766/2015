package org.usfirst.frc.team766.robot;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.PrintDiagnostics;
import org.usfirst.frc.team766.robot.commands.Autons.AutonSelectorCommand;
import org.usfirst.frc.team766.robot.commands.Autons.OpenCvTest;
import org.usfirst.frc.team766.robot.commands.Drive.CheesyDriveCommand;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.DriveUltrasonic;
import org.usfirst.frc.team766.robot.commands.Drive.TankDrive;
import org.usfirst.frc.team766.robot.commands.Drive.TestEncoders;
import org.usfirst.frc.team766.robot.commands.Elevator.CalibrateElevator;
import org.usfirst.frc.team766.robot.commands.Elevator.Slider;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 2015 Java Code main class
 * 
 * @author Brett Levenson
 * @author Patrick Kao
 */

public class SideSwipe extends IterativeRobot {
	
	private AutonSelectorCommand auton;
	private DriveUltrasonic dist;
	private boolean AutonCyclePrev;
	private boolean done;
	
    public void robotInit() {
    	CommandBase.init();
    	SmartDashboard.putBoolean("Tank Drive", false);
    	SmartDashboard.putNumber("Alpha", 0.5);
    	SmartDashboard.putData(new OpenCvTest());
    	SmartDashboard.putData(new DriveTurn(90));
    	SmartDashboard.putData(new PrintDiagnostics());
    	dist = new DriveUltrasonic(1);
    	SmartDashboard.putData(dist);
    	SmartDashboard.putData( new TestEncoders());
    	SmartDashboard.putData("Drive Backward 1.5 Meters: " ,new DriveForward(-1.5));
    	done = false;
    	
    	CommandBase.myLog.print("SideSwipe 2015 Code");
    	SmartDashboard.putNumber("P", RobotValues.UltrasonicDriveKp);
    	SmartDashboard.putNumber("I", RobotValues.UltrasonicDriveKi);
    	SmartDashboard.putNumber("D", RobotValues.UltrasonicDriveKd);
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
    	
//    	auton = new AutonSelectorCommand(CommandBase.OI.AutonMode);
//    	auton.start();
    	
    }

    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();        
    	SmartDashboard.putNumber("gyro", CommandBase.Drive.getAngle());
    	SmartDashboard.putNumber("Graph", dist.graphError);
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
    	
    	new Slider().start();
    	done = true;
    }

    public void disabledInit(){
    	if(done)CommandBase.myLog.closeFile();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
		System.out.println("Cheesy Gyro: " + CommandBase.Drive.getCheesyAngle());
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
