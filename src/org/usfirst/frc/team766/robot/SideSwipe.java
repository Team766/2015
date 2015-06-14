package org.usfirst.frc.team766.robot;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.PrintDiagnostics;
import org.usfirst.frc.team766.robot.commands.Autons.AutonSelectorCommand;
import org.usfirst.frc.team766.robot.commands.Autons.OpenCvTest;
import org.usfirst.frc.team766.robot.commands.Drive.CheesyDriveCommand;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DrivePath;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.DriveUltrasonic;
import org.usfirst.frc.team766.robot.commands.Drive.ResetGyro;
import org.usfirst.frc.team766.robot.commands.Drive.TankDrive;
import org.usfirst.frc.team766.robot.commands.Drive.TestEncoders;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustBrake;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.CalibrateElevator;
import org.usfirst.frc.team766.robot.commands.Elevator.DropStack;
import org.usfirst.frc.team766.robot.commands.Elevator.JoystickElevator;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorHeight;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorHeightVelocity;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalSmall;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalTote;
import org.usfirst.frc.team766.robot.commands.Intake.CloseLeftArm;
import org.usfirst.frc.team766.robot.commands.Intake.CloseRightArm;
import org.usfirst.frc.team766.robot.commands.Intake.GraspTote;
import org.usfirst.frc.team766.robot.commands.Intake.OpenToteArms;
import org.usfirst.frc.team766.robot.commands.Intake.ResetIntakeEnc;
import org.usfirst.frc.team766.robot.commands.Intake.SetLeftWheel;
import org.usfirst.frc.team766.robot.commands.Intake.SetRightWheel;
import org.usfirst.frc.team766.robot.testing.DispEncoders;
import org.usfirst.frc.team766.robot.testing.LogGyroAngle;
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
 * 
 * @version 2.0
 */

public class SideSwipe extends IterativeRobot {
	private static final boolean TESTING = true;
	private static final boolean PRINT = false;

	private AutonSelectorCommand auton;
	private DriveUltrasonic dist;
	private PrintDiagnostics printOut;
	private boolean AutonCyclePrev;
	private boolean done;

	/**
	 * First method called when the robot first turns on.  Initializes the robot to its
	 * beginning state.  This includes all of the constants and commands that are put onto
	 * smartdashboard.
	 */
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
		SmartDashboard.putData(new LogGyroAngle());
		SmartDashboard.putData(new DrivePath());
		done = false;

		printOut = new PrintDiagnostics(true);

		CommandBase.myLog.print("SideSwipe 2015 Code");
		SmartDashboard.putNumber("P", RobotValues.UltrasonicDriveKp);
		SmartDashboard.putNumber("I", RobotValues.UltrasonicDriveKi);
		SmartDashboard.putNumber("D", RobotValues.UltrasonicDriveKd);

		SmartDashboard.putNumber("Drive Past Tote 1",
				RobotValues.ThreeToteAutonDrivePastToteDistance1);
		SmartDashboard.putNumber("Drive Past Tote 2",
				RobotValues.ThreeToteAutonDrivePastToteDistance2);
		SmartDashboard.putNumber("Distance to Auto Zone",
				RobotValues.ThreeToteAutonDistanceToAutoZone);
		SmartDashboard.putNumber("Drive To Next Tote",
				RobotValues.ThreeToteAutonDistanceToNextTote);

		SmartDashboard.putData(new CalibrateElevator());// Elevator must be at
														// bottom at start.
		if (TESTING) {
			SmartDashboard.putData(new DispEncoders());
			SmartDashboard.putData(new ShowStops());
			SmartDashboard.putData("Brake Off", new AdjustBrake(false));
			SmartDashboard.putData("Brake On", new AdjustBrake(true));
			SmartDashboard.putData("MoveElevatorHeight .5",
					new MoveElevatorHeight(.5));
			SmartDashboard.putData("MoveElevatorHeight 1",
					new MoveElevatorHeight(1));
			SmartDashboard.putData("MoveElevatorHeightVelocity .5",
					new MoveElevatorHeightVelocity(.5));
			SmartDashboard.putData("MoveElevatorHeightVelocity 1",
					new MoveElevatorHeightVelocity(1));
			SmartDashboard.putData("Close Grippers: ", new AdjustGripper(true));
			SmartDashboard.putData("Open Grippers: ", new AdjustGripper(false));
			SmartDashboard.putData(new StackAdditionalTote());
			SmartDashboard.putData(new StackAdditionalSmall());
			SmartDashboard.putData(new DropStack());

			SmartDashboard.putData("left intake wheel in", new SetLeftWheel(-1,
					false));
			SmartDashboard.putData("left intake wheel out", new SetLeftWheel(1,
					false));
			SmartDashboard.putData("right intake wheel in", new SetRightWheel(
					-1, false));
			SmartDashboard.putData("right intake wheel out", new SetRightWheel(
					1, false));
			SmartDashboard.putData("close left arm", new CloseLeftArm(true));
			SmartDashboard.putData("close right arm", new CloseRightArm(true));
		}
		
		//Text controller
		System.out.println("Before Server starting");
		CommandBase.OI.server.start();
		System.out.println("After Server starting");
	}
	
	/**
	 * Method called repeatedly while the robot is in the disabled state.
	 * Updates the commands that display and select the different autonomous modes.
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();

		SmartDashboard.putString("Autonomous Mode: ",
				RobotValues.Autons[CommandBase.OI.AutonMode]);
		// Auton Cycler
		if (!AutonCyclePrev && CommandBase.OI.getAutonIncrement())
			CommandBase.OI.incrementAutonMode(1);
		else if (!AutonCyclePrev && CommandBase.OI.getAutonDecrement())
			CommandBase.OI.incrementAutonMode(-1);
		AutonCyclePrev = (CommandBase.OI.getAutonIncrement() || CommandBase.OI
				.getAutonDecrement());
	}

	/**
	 * This method is run once at the start of the autonomous period.
	 * Queues the autonomous command up that was selected during disabled periodic method.
	 * This is also the location where the constants used during the autonomous commands are updated
	 * to allow for easier testing.  Values that don't change are set in the robotInit function.
	 */
	public void autonomousInit() {
		// RobotValues.TurnAngleKp = SmartDashboard.getNumber("P");
		// RobotValues.TurnAngleKi = SmartDashboard.getNumber("I");
		// RobotValues.TurnAngleKd = SmartDashboard.getNumber("D");

		RobotValues.ThreeToteAutonDrivePastToteDistance1 = SmartDashboard
				.getNumber("Drive Past Tote 1");
		RobotValues.ThreeToteAutonDrivePastToteDistance2 = SmartDashboard
				.getNumber("Drive Past Tote 2");
		RobotValues.ThreeToteAutonDistanceToAutoZone = SmartDashboard
				.getNumber("Distance to Auto Zone");
		RobotValues.ThreeToteAutonDistanceToNextTote = SmartDashboard
				.getNumber("Drive To Next Tote");
		CommandBase.Elevator.resetEncoders();
		/*
		 * Run auton command Need to create an auton selector that uses OI and
		 * smartdashboard to allow autons to be selected...Add here
		 */
		done = true;

		auton = new AutonSelectorCommand(CommandBase.OI.AutonMode);
		auton.start();

	}
	
	/**
	 * This function is run repeatedly during the autonomous period of the match.
	 * This updates the commands that control the state of the robot during autonomous.
	 * When debugging, this method is helpful because it can display the live values of 
	 * the robot to the console to see what is wrong. 
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		System.out.println("Gyro Position: " + CommandBase.Drive.getAngle());
		// SmartDashboard.putNumber("gyro", CommandBase.Drive.getAngle());
		// SmartDashboard.putNumber("Graph", dist.graphError);
		// System.out.println("Cheesy Gyro: " +
		// CommandBase.Drive.getCheesyAngle() + "\t Gyro" +
		// CommandBase.Drive.getAngle());
	}
	
	/**
	 * The first function called at the start of the tele-operated period of the match.
	 * This is where the commands that control the elevator and drive base are initiated.
	 */
	public void teleopInit() {

		RobotValues.IntakeKP = SmartDashboard.getNumber("P");
		RobotValues.IntakeKI = SmartDashboard.getNumber("I");
		RobotValues.IntakeKD = SmartDashboard.getNumber("D");

		// cancel auton
		if (auton != null)
			auton.cancel();

		// Just for testing
		// CommandBase.OI.setTankDrive(SmartDashboard.getBoolean("Tank Drive"));
		
		new JoystickElevator().start();
		
		if (!CommandBase.OI.getTankDrive()) {
			new CheesyDriveCommand().start();
		} else {
			new TankDrive().start();
		}

		done = true;
		printOut.start();
	}

	/**
	 * The method called at the start of the disabled period.  This method is mainly used to close
	 * files and different features after the robot has completed during the autonomous and teleop periods.
	 */
	public void disabledInit() {
		if (done)
			CommandBase.myLog.closeFile();
	}

	/**
	 * This method is called repeatedly during the driver controlled section of the match, or during the
	 * test period.  This updates the different subsystem commands that actuate the robot.  This also displays
	 * useful information in the console while the robot is running.
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (PRINT) {
			SmartDashboard.putString("Test Prints", printOut.getOut());
			System.out.println(printOut.getOut());
		}
		// System.out.println("Ultrasonic Sensor: " +
		// UltrasonicSensor.getInstance().getDistanceDouble());
		// System.out.println("Encoder Height: " +
		// CommandBase.Elevator.getEncoders());
		// System.out.println("Gyro Position: " + CommandBase.Drive.getAngle());
		// System.out.println("Drive Encoders L, R:  " +
		// CommandBase.Drive.getLeftEncoderDistance() + ", " +
		// CommandBase.Drive.getRightEncoderDistance());

	}
	
	/**
	 * This method is called repeatedly during the practice period.  This simply updates the live window features
	 * so that smartdashboard can display the current state of the robot.  This method is not currently in use because
	 * it is unnecessary and repetitive.
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

}
