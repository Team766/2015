package org.usfirst.frc.team766.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team766.robot.subsystems.Elevator;

/**
 *	Command that moves the elevator to the bottom preset
 *	@author Blevenson
 */
public class MoveElevatorBottom extends Command {
	private boolean done;
    public MoveElevatorBottom() {
        done = false;
    }

    protected void initialize() {
    }

    protected void execute() {
    	//PID to move the elevator to position
    }

    protected boolean isFinished() {
        return done;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
