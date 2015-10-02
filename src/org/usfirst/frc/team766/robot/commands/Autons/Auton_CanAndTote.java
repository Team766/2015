package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;
import org.usfirst.frc.team766.robot.commands.Intake.CloseLeftArm;
import org.usfirst.frc.team766.robot.commands.Intake.CloseRightArm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auton_CanAndTote extends CommandGroup{
	public Auton_CanAndTote(){
		//Pick up the can
		addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(3));
    	addSequential(new AdjustGripper(true));
    	
    	//Move Forward to the tote
    	addSequential(new DriveForward(23 * RobotValues.INCHES_TO_METERS));
    	//Relase Can on tote
//    	addSequential(new MoveElevatorWaypoint(1));
//    	addSequential(new StackAdditionalTote());
    	
    	//Grip lower
    	addParallel(new CloseLeftArm(true));
    	addParallel(new CloseRightArm(true));
    	//Turn and Drop
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveForward(RobotValues.DriveForwardDistance));
    	addSequential(new MoveElevatorWaypoint(1));
    	addSequential(new AdjustGripper(false));
    	addParallel(new CloseLeftArm(false));
    	addParallel(new CloseRightArm(false));
    	addSequential(new MoveElevatorWaypoint(0));
    	addSequential(new AdjustGripper(true));
	}
}
