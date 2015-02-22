package org.usfirst.frc.team766.robot.subsystems;

import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Extend this class in inner classes in subsystems.
 */
public abstract class Periodic extends CommandBase {

    public Periodic() {
    }

    protected void initialize() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
