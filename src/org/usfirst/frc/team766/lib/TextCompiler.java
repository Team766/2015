package org.usfirst.frc.team766.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;

import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Reads commands from a text file and turns them into auton commands
 * 
 * Format for file:
 * 
 * Commands
 * Drive 10
 * Turn 90
 * Drive 6
 * Claw 1	//1 = open
 * Claw -1	//-1 -close
 * 
 * @author Brett Levenson
 */

public class TextCompiler extends CommandBase{
	private String filePath;
	private ArrayList<Command> commands;
	private boolean done;
	
	private Scanner scan;
	
	public TextCompiler(){
		this("");
		System.out.println("No file path given");
	}
	
	public TextCompiler(String in){
		filePath = in;
		done = false;
		openFile();
	}
	
	//Fills the command matrix with the commands from the file
	public void readCommands(){
		if(scan.nextLine() != "Commands"){
			System.out.println("File is not a command format");
			return;
		}
		while(scan.hasNextLine()){
			commands.add(new Command(scan.next(), scan.nextDouble()));
		}
		System.out.println(commands);
	}
	
	private void openFile(){
		try{
			scan = new Scanner(new File(filePath));
		}
		catch(Exception e){
			System.out.println("File failed to open");
		}
	}
	
	protected void initialize() {
		readCommands();
	}
	
	//Only runs once
	protected void execute() {
		for(Command c : commands){
			switch(c.getState()){
			
			case Drive:
				DriveForward driver = new DriveForward(c.getDistance());
				driver.start();
				
				//Waits for driver to stop moving
				//Creates sequential commands
				while(!driver.isFinished())
					Scheduler.getInstance().run();	//OPTIONAL  May need to be removed
				break; 
				
			case Turn:
				DriveTurn turner = new DriveTurn(c.getDistance());
				turner.start();
				while(!turner.isFinished())
					Scheduler.getInstance().run();	//Waits for turner to stop moving
				break;
				
			case Claw:
				AdjustGripper gripper = new AdjustGripper(c.getBool());
				gripper.start();
				while(gripper.isRunning());
				break;
				
			case None:
				//Run no command
				break;
				
			default:
				System.out.println("No command selected");
				break;
			}
		}
		done = true;
		
	}

	protected void interrupted() {
		end();
	}
	protected void end() {
		System.out.println("Finished running the commands in the text file");
	}

	protected boolean isFinished() {
		return done;
	}
}
class Command{
	/*
	 * Class used to store commands from the file
	 * which will then be looped through and run
	 */
	private double distance;
	private CommandState value;
	
	
	//Turns strings into commands
	public Command(String command, double v){
		distance = v; 
		switch(command.trim().toLowerCase()){
			case "drive":
				value = CommandState.Drive;
				break;
			case "turn":
				value = CommandState.Turn;
				break;
			case "claw":
				value = CommandState.Claw;
				break;
			default:
				value = CommandState.None;
				break;
		}
	}
	
	/**
	 * @return The value associated with this command
	 * Ex: Drive Forward would return the distance to drive.
	 * Ex: Turn would return degrees
	 */
	public double getDistance(){
		return distance;
	}
	
	public boolean getBool(){
		return distance > 0 ? true : false;
	}
	
	/**
	 * @return The command to run
	 */
	public CommandState getState(){
		return value;
	}
}

//Possible commands that can be run
enum CommandState{
	Drive, Turn, Claw, None;
}