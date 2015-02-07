package org.usfirst.frc.team766.robot.commands.Elevator.Presets;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveArmPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveElevatorBottom extends CommandGroup {
    
    public  MoveElevatorBottom() {
    	addSequential(new MoveArmPosition(RobotValues.ElevatorState.BOTTOM));
    }
}          