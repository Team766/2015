package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.trajectory.AutoPaths;
import org.usfirst.frc.team766.robot.commands.Autons.SetTrajectoryPath;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DrivePath extends CommandGroup {

	/*
	 * Available Paths: 
	 * "InsideLanePathFar", 
	 * "CenterLanePathFar", 
	 * "WallLanePath",
	 * "InsideLanePathClose", 
	 * "StraightAheadPath"
	 */

	public DrivePath() {
		//addSequential(new PathDrive(AutoPaths.get("InsideLanePathFar"), 10000));
		addSequential(new SetTrajectoryPath("StraightAheadPath"));
		addSequential(new PathDrive(AutoPaths.get("StraightAheadPath"), 10000));
	}

}
