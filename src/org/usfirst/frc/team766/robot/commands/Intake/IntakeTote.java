package org.usfirst.frc.team766.robot.commands.Intake;


import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Command to intake a tote into the robot
 */
public class IntakeTote extends CommandGroup {
	public IntakeTote()
	{
		addSequential(new GraspTote(), 100);
		addSequential(new RelocateTote(1, 1, 0.5, 0.5, true), 100);
		addSequential(new Reset(), 100);
	}
}
