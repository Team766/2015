package org.usfirst.frc.team766.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *  Removes stacked totes from the robot
 */
public class DropStack extends CommandGroup {
    
    public  DropStack() {
        addSequential(new MoveElevatorWaypoint(0), 100);//Should timeouts be there?
        addSequential(new AdjustGripper(false), 100);//Should timeouts be there?
//        addSequential(new SetWheels(-1, true));
//        addSequential(new WaitCommand(10));
//        addSequential(new SetWheels(0, true));
        addSequential(new SetNumTotes(0));
    }
}
