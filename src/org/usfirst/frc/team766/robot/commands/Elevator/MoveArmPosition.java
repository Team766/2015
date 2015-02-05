package org.usfirst.frc.team766.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team766.robot.subsystems.Elevator;

/**
 *	Command that moves the elevator to the bottom preset
 *	@author Blevenson
 */
public class MoveArmPosition extends Command {
	private boolean done;
	private int goal;
    public MoveArmPosition() {
        done = false;
        goal = 0;
    }
    
    public MoveArmPosition(int goal) {
        done = false;
        this.goal = goal;
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
