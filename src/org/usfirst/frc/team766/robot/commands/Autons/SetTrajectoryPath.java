package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.lib.trajectory.AutoPaths;
import org.usfirst.frc.team766.robot.commands.CommandBase;


/**
 * Sets the trajectroy path for the path driver
 */
public class SetTrajectoryPath extends CommandBase {
	private String p;
    public SetTrajectoryPath(String path){
    	p = path;
    	OI.path = AutoPaths.get(path);
    }

    protected void initialize() {
    	OI.path = AutoPaths.get(p);
    }

    protected void execute() {
    	OI.path = AutoPaths.get(p);
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	OI.path = AutoPaths.get(p);
    }

    protected void interrupted() {
    	end();
    }
}
