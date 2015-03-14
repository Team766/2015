package org.usfirst.frc.team766.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackAdditionalTote extends CommandGroup {
    
    public  StackAdditionalTote() {
    	addSequential(new SaveState());
    	addSequential(new MoveElevatorWaypoint(1));
//    	addSequential(new IntakeTote());
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(0));
    	addSequential(new AdjustGripper(true));
    	addSequential(new ReturnToState());
    	addSequential(new IncrementNumTotes(1));
    }
}
