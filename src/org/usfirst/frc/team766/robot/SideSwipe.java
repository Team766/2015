package org.usfirst.frc.team766.robot;

import org.usfirst.frc.team766.robot.Ultrasonic.UltrasonicSensor;
import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.PrintDiagnostics;
import org.usfirst.frc.team766.robot.commands.Autons.AutonSelectorCommand;
import org.usfirst.frc.team766.robot.commands.Autons.OpenCvTest;
import org.usfirst.frc.team766.robot.commands.Drive.CheesyDriveCommand;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.DriveUltrasonic;
import org.usfirst.frc.team766.robot.commands.Drive.ResetGyro;
import org.usfirst.frc.team766.robot.commands.Drive.TankDrive;
import org.usfirst.frc.team766.robot.commands.Drive.TestEncoders;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustBrake;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.CalibrateElevator;
import org.usfirst.frc.team766.robot.commands.Elevator.DropStack;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorHeight;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorHeightVelocity;
import org.usfirst.frc.team766.robot.commands.Elevator.Slider;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalSmall;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalTote;
import org.usfirst.frc.team766.robot.commands.Intake.GraspTote;
import org.usfirst.frc.team766.robot.commands.Intake.OpenToteArms;
import org.usfirst.frc.team766.robot.commands.Intake.ResetIntakeEnc;
import org.usfirst.frc.team766.robot.testing.DispEncoders;
import org.usfirst.frc.team766.robot.testing.ShowStops;

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
	private static final boolean TESTING = true;
	private static final boolean PRINT = false;

	private AutonSelectorCommand auton;
	private DriveUltrasonic dist;
	private PrintDiagnostics printOut;
	private boolean AutonCyclePrev;
	private boolean done;

	public void robotInit() {
		CommandBase.init();
		SmartDashboard.putBoolean("Tank Drive", false);
		SmartDashboard.putNumber("Alpha", 0.5);
		SmartDashboard.putData(new OpenCvTest());
		SmartDashboard.putData(new DriveTurn(90));
		// SmartDashboard.putData(new PrintDiagnostics());
		dist = new DriveUltrasonic(1);
		SmartDashboard.putData(dist);
		SmartDashboard.putData(new TestEncoders());
		SmartDashboard.putData("Drive Backward 1.5 Meters: ", new DriveForward(
				-1.5));
		SmartDashboard.putData(new ResetGyro());
		SmartDashboard.putData(new ResetIntakeEnc());
		SmartDashboard.putData(new GraspTote());
		SmartDashboard.putData(new OpenToteArms());
		done = false;

		printOut = new PrintDiagnostics(true);

		CommandBase.myLog.print("SideSwipe 2015 Code");
		SmartDashboard.putNumber("P", RobotValues.UltrasonicDriveKp);
		SmartDashboard.putNumber("I", RobotValues.UltrasonicDriveKi);
		SmartDashboard.putNumber("D", RobotValues.UltrasonicDriveKd);
		SmartDashboard.putData(new CalibrateElevator());//Elevator must be at bottom at start.
		if (TESTING) {
			SmartDashboard.putData(new DispEncoders());
			SmartDashboard.putData(new ShowStops());
			SmartDashboard.putData("Brake Off", new AdjustBrake(false));
			SmartDashboard.putData("Brake On", new AdjustBrake(true));
			SmartDashboard.putData("MoveElevatorHeight .5",
					new MoveElevatorHeight(.5));
			SmartDashboard.putData("MoveElevatorHeightVelocity .5",
					new MoveElevatorHeightVelocity(.5));
			SmartDashboard.putData("Close Grippers: ", new AdjustGripper(true));
			SmartDashboard.putData("Open Grippers: ", new AdjustGripper(false));
			SmartDashboard.putData(new StackAdditionalTote());
			SmartDashboard.putData(new StackAdditionalSmall());
			SmartDashboard.putData(new DropStack());
		}
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		// Auton Cycler
		if (!AutonCyclePrev && CommandBase.OI.buttonAutonIncrement.get())
			CommandBase.OI.incrementAutonMode(1);
		else if (!AutonCyclePrev && CommandBase.OI.buttonAutonDecrement.get())
			CommandBase.OI.incrementAutonMode(-1);
		AutonCyclePrev = (CommandBase.OI.buttonAutonIncrement.get() || CommandBase.OI.buttonAutonDecrement
				.get());
	}

	public void autonomousInit() {
//		RobotValues.TurnAngleKp = SmartDashboard.getNumber("P");
//		RobotValues.TurnAngleKi = SmartDashboard.getNumber("I");
//		RobotValues.TurnAngleKd = SmartDashboard.getNumber("D");

		CommandBase.Elevator.resetEncoders();
		/*
		 * Run auton command Need to create an auton selector that uses OI and
		 * smartdashboard to allow autons to be selected...Add here
		 */
		done = true;

		 auton = new AutonSelectorCommand(CommandBase.OI.AutonMode);
		 auton.start();

	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
//		SmartDashboard.putNumber("gyro", CommandBase.Drive.getAngle());
//		SmartDashboard.putNumber("Graph", dist.graphError);
//		System.out.println("Cheesy Gyro: " + CommandBase.Drive.getCheesyAngle() + "\t Gyro" + CommandBase.Drive.getAngle());
	}

	public void teleopInit() {
		
		RobotValues.IntakeKP = SmartDashboard.getNumber("P");
		RobotValues.IntakeKI = SmartDashboard.getNumber("I");
		RobotValues.IntakeKD = SmartDashboard.getNumber("D");

		// cancel auton
		if (auton != null)
			auton.cancel();

		// Just for testing
		// CommandBase.OI.setTankDrive(SmartDashboard.getBoolean("Tank Drive"));

		if (!CommandBase.OI.getTankDrive()) {
			new CheesyDriveCommand().start();
		} else {
			new TankDrive().start();
		}

		new Slider().start();
		
		done = true;
		printOut.start();
	}

	public void disabledInit() {
		if (done)
			CommandBase.myLog.closeFile();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (PRINT) {
			SmartDashboard.putString("Test Prints", printOut.getOut());
			System.out.println(printOut.getOut());
		}
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

}
