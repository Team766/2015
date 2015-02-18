package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.DriveUltrasonicDistance;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auton_MoveToLandfill extends CommandGroup {
    
    public  Auton_MoveToLandfill() {
    	addParallel(new AdjustGripper(false));
    	addParallel(new MoveElevatorWaypoint(0));//Replace with whatever preset is used to pick up a box on the ground
    	addSequential(new DriveForward(RobotValues.DistanceToLandfill));
    	addSequential(new DriveUltrasonicDistance(RobotValues.OptimalGripperDistance));
    	addSequential(new AdjustGripper(true));
    	addSequential(new DriveTurn(180));
    }
}
