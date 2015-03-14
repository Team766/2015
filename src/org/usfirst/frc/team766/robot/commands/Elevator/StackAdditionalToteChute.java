package org.usfirst.frc.team766.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackAdditionalToteChute extends CommandGroup {
    
    public  StackAdditionalToteChute() {
    	addSequential(new MoveElevatorWaypoint(2));
    	addSequential(new AdjustGripper(true));
    	addSequential(new MoveElevatorWaypoint(3));
    	addSequential(new AdjustGripper(false));
    	//addSequentail(new WaitForReleassed());
    	addSequential(new IncrementNumTotes(1));
    }
}
