package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.commands.Intake.SetWheels;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *  Removes stacked totes from the robot
 */
public class DropStack extends CommandGroup {
    
    public  DropStack() {
        addSequential(new MoveElevatorWaypoint(0), 100);//Should timeouts be there?
        addSequential(new AdjustGripper(true), 100);//Should timeouts be there?
        addSequential(new SetWheels(-1));
        addSequential(new WaitCommand(10));
        addSequential(new SetWheels(0));
        addSequential(new SetNumTotes(0));
    }
}
