package org.usfirst.frc.team766.robot.commands.Intake;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *	Opens the piston intake arms
 */
public class OpenPistonArms extends CommandGroup {
	
    public OpenPistonArms() {
    	addParallel(new CloseLeftArm());
    	addParallel(new CloseRightArm());
    }
}
