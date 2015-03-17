package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Elevator.DropStack;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalTote;
import org.usfirst.frc.team766.robot.commands.Intake.IntakeTote;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Pick up, reverse at an angle, charge the trash can so that it goes on the side.
 * Repeat for the rest of the totes and then drop the stack
 */
public class Auton_AggressiveThreeTote extends CommandGroup{
	public Auton_AggressiveThreeTote()
	{
		for(int i = 0; i < 2; i++)
		{
			addSequential(new IntakeTote(), 100);
			addSequential(new StackAdditionalTote());
			addSequential(new DriveTurn(RobotValues.turnNextTote));
			addSequential(new DriveForward(RobotValues.DistanceToPassTote));
			addSequential(new DriveTurn(-RobotValues.turnNextTote));
			addSequential(new DriveForward(RobotValues.DistanceToPassTote));
		}
		addSequential(new IntakeTote());
		addSequential(new StackAdditionalTote());
		addSequential(new DriveTurn(90));
		addSequential(new DriveForward(-RobotValues.k_moveBackToAutoZone));
		addSequential(new DropStack());
	}
}