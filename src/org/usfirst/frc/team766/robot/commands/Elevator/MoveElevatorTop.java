package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveElevatorTop extends CommandGroup {
    
    public  MoveElevatorTop() {
    	addSequential(new MoveArmPosition(RobotValues.ElevatorState.TOP));
    }
}