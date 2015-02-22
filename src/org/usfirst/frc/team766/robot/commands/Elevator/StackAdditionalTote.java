package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackAdditionalTote extends CommandGroup {
    
    public  StackAdditionalTote() {
    	double curHeight = CommandBase.Elevator.getGoal();
    	addSequential(new MoveElevatorWaypoint(1));
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(0));
    	addSequential(new AdjustGripper(true));
    	addSequential(new MoveElevatorHeight(curHeight));
    	RobotValues.numTotes++;
    }
}
