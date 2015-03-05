package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.DriveUltrasonic;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;
import org.usfirst.frc.team766.robot.commands.Intake.PullToteIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auton_MoveToLandfill extends CommandGroup {
    
    public  Auton_MoveToLandfill() {
    	addParallel(new AdjustGripper(false));
    	addParallel(new MoveElevatorWaypoint(0));
    	addSequential(new DriveForward(RobotValues.DistanceToLandfill));
    	addSequential(new DriveUltrasonic(RobotValues.OptimalGripperDistance));
    	addSequential(new PullToteIn());
    	addSequential(new AdjustGripper(true));
    	addSequential(new DriveTurn(180));
    }
}
