package org.usfirst.frc.team766.robot.commands;

import org.usfirst.frc.team766.lib.logData;
import org.usfirst.frc.team766.robot.subsystems.Drive;
import org.usfirst.frc.team766.robot.OI;
import org.usfirst.frc.team766.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc.team766.robot.subsystems.Elevator;

/**
 * Create instances of the subsystems here
 * Later in commands import this class to
 * gain access to the systems presets
 *
 * @author Brett Levenson
 */
public abstract class CommandBase extends Command{
	
	public static Drive Drive;
	public static OI OI;
	public static Elevator Elevator;
	public static Intake Intake;
	public static NetworkTable table;
	public static logData myLog;
	
	public static void init(){
		Drive = new Drive();
		OI = new OI();
		Elevator = new Elevator();
		Intake = new Intake();
		table = NetworkTable.getTable("dataTable");
		myLog = new logData();
	}

}
