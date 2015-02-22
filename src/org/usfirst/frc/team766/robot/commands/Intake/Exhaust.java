package org.usfirst.frc.team766.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *  Exhausts the tote from the robot
 */
public class Exhaust extends CommandGroup {
	public Exhaust()
	{
		addSequential(new RelocateTote(-1, -1, 0.3, 0.3, false), 100);
	}
}
