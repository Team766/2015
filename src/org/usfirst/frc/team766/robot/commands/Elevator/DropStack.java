package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *  Removes stacked totes from the robot
 */
public class DropStack extends CommandGroup {
    
    public  DropStack() {
        addSequential(new MoveElevatorWaypoint(0), 100);
        addSequential(new AdjustGripper(true), 100);
        CommandBase.Intake.setWheels(-1);
        addParallel(new WaitCommand(10));
        CommandBase.Intake.setWheels(0);
        RobotValues.numTotes = 0;
    }
}
