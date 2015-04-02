package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.DropStack;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	One container auton
 */
public class Auton_OneContainer extends CommandGroup {
    
    public  Auton_OneContainer() {
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(2));
    	addSequential(new AdjustGripper(true));
    	addSequential(new DriveTurn(-90));
    	addSequential(new DriveForward(RobotValues.DriveForwardDistance));
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(0));
    }
}
