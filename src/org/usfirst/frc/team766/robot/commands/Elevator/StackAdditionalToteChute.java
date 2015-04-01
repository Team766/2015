package org.usfirst.frc.team766.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Press after the tote has been dropped onto the stack from the chute
 */
public class StackAdditionalToteChute extends CommandGroup {
    
    public  StackAdditionalToteChute() {
    	addSequential(new MoveElevatorWaypoint(2));
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(1));
    	addSequential(new AdjustGripper(true));
    	addSequential(new MoveElevatorWaypoint(3));
    	addSequential(new IncrementNumTotes(1));
    }
}
