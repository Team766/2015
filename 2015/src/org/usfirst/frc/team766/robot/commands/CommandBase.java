package org.usfirst.frc.team766.robot.commands;

import org.usfirst.frc.team766.robot.subsystems.Drive;
import org.usfirst.frc.team766.robot.OI;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Create instances of the subsystems here
 * Later in commands import this class to
 * gain access to the systems presets
 *
 * @author Brett Levenson
 */
public abstract class CommandBase extends Command{
	
	public static Drive Drive = new Drive();
	public static OI OI = new OI();
	
	public static void init(){
	}

}
