package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowerToteToStack extends CommandGroup {
	public LowerToteToStack()
	{
		addSequential(new MoveElevatorHeight(RobotValues.ElevatorPresets[3] - (RobotValues.Box_Height / 3d)));
		addSequential(new AdjustGripper(false));
		addSequential(new WaitForReleassed(true)); //Tells the other command that this command is done running
	}
}
