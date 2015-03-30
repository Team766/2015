package org.usfirst.frc.team766.lib;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj.Timer;

/*
* Creates a log for storing values on the roboRio that can then be accesed using ftp
* username: admin
* password: 
* 
* Created by Brett Levenson
*/
 
public class logData {
	
	private PrintWriter pw;
	private Timer timer = new Timer();
	private boolean INDENT = false;
			
	public logData(){
		try {
			pw = new PrintWriter(new FileWriter("/tmp/log.txt"));
			timer.start();
		} catch (IOException e) {
			System.out.println("Something went wrong in the log's constructor");
			timer.stop();
			e.printStackTrace();
		}
	}
	
	public void print(String message)
	{
		try{
			if(INDENT)
				pw.println(getTime() + "\t\t" + message);
			else
				pw.println(getTime() + "\t" + message);
		}catch(NullPointerException e)
		{
			System.out.println("Null Pointer alert!");
		}
	}
	
	public void printRaw(String in)
	{
		try{
			pw.println(in);
		}catch(NullPointerException e)
		{
			System.out.println("Can't print raw value: " + in);
		}
	}
	
	public void print(String message, int value)
	{
		try{
			if(INDENT)
				pw.println(getTime() + "\t\t" + message + value);
			
			else
				pw.println(getTime() + "\t" + message + value);
		}catch(NullPointerException e)
		{
			System.out.println("Can't save log!");
		}
	}
	
	public void closeFile()
	{
		timer.stop();
		
		try{
			pw.close();
		}catch(NullPointerException e)
		{
			System.out.println("Can't save log!");
		}
	}
	
	private String getTime()
    {
        int totalSeconds = (int)(timer.get());
        int seconds = totalSeconds % 60; 
        int minutes = (totalSeconds / 60) % 60; 
        int hours = totalSeconds / 3600; 
        return hours + ":" + minutes + ":" + seconds;
    }

	public boolean isIndent() {
		return INDENT;
	}

	public void setIndent(boolean iNDENT) {
		INDENT = iNDENT;
	}
}


