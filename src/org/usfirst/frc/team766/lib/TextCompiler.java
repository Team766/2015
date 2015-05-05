package org.usfirst.frc.team766.lib;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import org.usfirst.frc.team766.robot.commands.CommandBase;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;

import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Format for file:
 * 
 * Commands
 * Drive 10
 * Turn 90
 * Drive 6
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
	
	public void readCommands(){
		if(scan.nextLine() != "Commands")return;
		while(scan.hasNextLine()){
			commands.add(new Command(scan.next(), scan.nextDouble()));
		}
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
	protected void execute() {
		for(Command c : commands){
			switch(c.getState()){
			case Drive:
				DriveForward driver = new DriveForward(c.getDistance());
				driver.start();
				while(!driver.isFinished())
					Scheduler.getInstance().run();	//Waits for driver to stop moving
				break;
			case Turn:
				DriveTurn turner = new DriveTurn(c.getDistance());
				turner.start();
				while(!turner.isFinished())
					Scheduler.getInstance().run();	//Waits for turner to stop moving
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
	private double distance;
	private CommandState value;
	
	public Command(String command, double v){
		distance = v; 
		switch(command.toLowerCase()){
			case "drive":
				value = CommandState.Drive;
				break;
			case "turn":
				value = CommandState.Turn;
				break;
			default:
				value = CommandState.None;
				break;
		}
	}
	
	public double getDistance(){
		return distance;
	}
	
	public CommandState getState(){
		return value;
	}
}

enum CommandState{
	Drive, Turn, None;
}