package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Intake.IntakeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackAdditionalTote extends CommandGroup {
    
    public  StackAdditionalTote() {
    	double curHeight = CommandBase.Elevator.getEncoders();
    	addSequential(new MoveElevatorWaypoint(1));
    	addSequential(new IntakeTote());
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(0));
    	addSequential(new AdjustGripper(true));
    	addSequential(new MoveElevatorHeight(curHeight));
    	addSequential(new IncrementNumTotes(1));
    }
}
