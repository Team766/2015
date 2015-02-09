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
	
	//private BufferedWriter bw;
	private PrintWriter pw;
	private Timer timer;
			
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
		pw.println(getTime() + "\t" + message);
	}
	
	public void print(String message, int value)
	{
		pw.println(getTime() + "\t" + message + value);
	}
	
	public void closeFile()
	{
		timer.stop();
		pw.close();
	}
	
	private String getTime()
    {
        int totalSeconds = (int)(timer.get());
        int seconds = totalSeconds % 60; 
        int minutes = (totalSeconds / 60) % 60; 
        int hours = totalSeconds / 3600; 
        return hours + ":" + minutes + ":" + seconds;
    }
}


