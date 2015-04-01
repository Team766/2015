package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.ResetGyro;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.DropStack;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalTote;
import org.usfirst.frc.team766.robot.commands.Intake.OpenPistonArms;
import org.usfirst.frc.team766.robot.commands.Intake.SetLeftWheel;
import org.usfirst.frc.team766.robot.commands.Intake.SetRightWheel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *	Three tote auton
 *  
 *  This picks up the three yellow totes and drops them into the auto zone
 *  while nocking the containers out of the way
 *  	1.  Pick up 1 tote
 *  	2.  Turn -45 degrees
 *  ---------------------------  Reset Gyro
 *  	3.  Go Forward to move around the tote
 *  	4.  Turn 90 degrees
 *  ---------------------------  Reset Gyro		_____________________________
 *  	5.  Go forward to the next tote										|
 *  	6.	Turn -45 degrees to straighten out for the next tote			| Do this while intaking	
 *  	7.	Go forward to be infront of the next tote		________________|																		
 *  	8.  Repeat steps 1 - 7
 *  	9.  Pick up 1 tote
 *  	10.  Rotate 90 degrees to face the auto zone
 *  	11.  Drive forward into the auto zone
 *  	12.  Drop the stack in the auto zone
 */
public class Auton_ThreeTotes extends CommandGroup {
    
    public  Auton_ThreeTotes() {
//    	for(int i = 0; i < 2; i++)
//    	{
    		//Pick up the tote
    		addSequential(new MoveElevatorWaypoint(0));
			addSequential(new AdjustGripper(true));
			addSequential(new MoveElevatorWaypoint(2));
			
			addParallel(new OpenPistonArms(false));
			addParallel(new SetLeftWheel(-1,true));
			addParallel(new SetRightWheel(-1,true));
			
			addSequential(new DriveTurn(30));
			addSequential(new ResetGyro(true));
			addSequential(new DriveForward(RobotValues.ThreeToteAutonDrivePastToteDistance1));
			addSequential(new DriveTurn(-60));
			addSequential(new ResetGyro(true));
			addSequential(new DriveForward(RobotValues.ThreeToteAutonDrivePastToteDistance2));
			
//			//Go around the container
//			addSequential(new DriveTurn(45));
//			addSequential(new WaitCommand(0.25));
//			addSequential(new ResetGyro(true));
//			addSequential(new DriveForward(RobotValues.ThreeToteAutonDrivePastToteDistance1));
//			addSequential(new DriveTurn(-90));
//			addSequential(new WaitCommand(0.25));
//			addSequential(new ResetGyro(true));
//			
//			//Intake the arms while you approach the next tote
//			addParallel(new OpenPistonArms(false));
//			addParallel(new SetLeftWheel(-1,true));
//			addParallel(new SetRightWheel(-1,true));
//			
//			//Drive into the next tote and straighten out
//			addSequential(new DriveForward(RobotValues.ThreeToteAutonDrivePastToteDistance2));
//			addSequential(new DriveTurn(45));
//			addSequential(new WaitCommand(0.25));
//			addSequential(new ResetGyro(true));
//			
//			//Pick up the next tote
			addSequential(new DriveForward(RobotValues.ThreeToteAutonDistanceToNextTote));
//			
//			//Close the intake systems
			addParallel(new OpenPistonArms(true));
			addParallel(new SetLeftWheel(0,true));
			addParallel(new SetRightWheel(0,true));
			
//    	}
//
//    	//After picking up the first two totes, set up for the next one
//    	
//    	//Pick up the third tote
//		addSequential(new StackAdditionalTote());
//		
//		//Rotate to face the Auto Zone with the totes, and drive into the auto zone
//		addSequential(new DriveTurn(90));
//		addSequential(new ResetGyro(true));
//		addSequential(new DriveForward(RobotValues.ThreeToteAutonDistanceToAutoZone));
//		
//		//Once in the auto zone, put the stack down for the points
//		addSequential(new DropStack());
			
    	
    }
}
