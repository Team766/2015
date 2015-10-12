package org.usfirst.frc.team766.robot.commands.Drive;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.usfirst.frc.team766.robot.commands.CommandBase;

public class RecordPath extends CommandBase{
	
	private PrintWriter pw;
	private String left, right;
	private String FileName;
	private int totalElements;
	
	public RecordPath(){
		totalElements = 0;
		left = right = "";
		FileName = "RecordedPath";
		
		try {
			pw = new PrintWriter(new FileWriter("/var/local/paths/" + FileName + ".txt"));
		} catch (IOException e) {
			System.out.println("Failed to start recording path");
			e.printStackTrace();
		}
	}
	
	protected void initialize() {
		Drive.resetEncoders();
		Drive.resetGyro();
	}
	
	protected void execute() {
		/*
		 * Format:
		 * 	position velocity acceleration jerk heading dt x y
		 */
		
		//Record the values
		addLeft(String.format("%.3f %.3f %.3f %.3f %.3f %.3f %.3f %.3f", Drive.getLeftEncoderDistance(), Drive.getLeftVelocity(), Drive.getAcceleration(), 0d, 0d, 0.010, 0d, 0d));
		addRight(String.format("%.3f %.3f %.3f %.3f %.3f %.3f %.3f %.3f", Drive.getRightEncoderDistance(), Drive.getRightVelocity(), Drive.getAcceleration(), 0d, 0d, 0.010, 0d, 0d));
		
		totalElements++;
	}
	
	public void addLeft(String in){
		left += in + "\n";
	}
	
	public void addRight(String in){
		right += in + "\n";
	}
	
	public void print(String message)
	{
		try{
			pw.println(message + "\n");
		}catch(NullPointerException e){
			System.out.println("Null Pointer alert!");
		}
	}

	protected boolean isFinished() {
		//May want totalElements > #, so file doesn't get too large
		return false;
	}
	
	//Most likely going to be the method called to close file
	protected void interrupted() {
		//Must call end(), its where the "Magic" happens
		end();
	}
	
	protected void end() {
		//Add total elements to top of path
		print(String.format("%s\n%d\n%s", FileName, totalElements, left + right));
		
		//Save Path
		closeFile();
	}
	
	//Save the path file
	private void closeFile()
	{	
		try{
			pw.close();
		}catch(NullPointerException e)
		{
			System.out.println("Can't save path!");
		}
	}
	
}
