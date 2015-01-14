package org.usfirst.frc.team766.lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.Timer;

/*
* Creates a log for storing values on the roboRio that can then be accesed using ftp
* username: admin
* password: ••••••••
* 
* Created by Brett Levenson
*/
 
public class logData {
	
	private BufferedWriter bw;
	private Timer timer;
			
	public logData(){
		try{
			timer = new Timer();
			File file = new File("file:///log.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			
 
			timer.start();
		} catch (IOException e) {
			System.out.println("Something went wrong in the log's constructor");
			e.printStackTrace();
			timer.stop();
		}
	}
	
	public void print(String message)
	{
		try {
			bw.write(getTime() + "\t" + message + "\n");
			System.out.println("I be writing");
		} catch (IOException e) {
			System.out.println("I 2 stupid 2 no how 2 wite: #1");
			e.printStackTrace();
		}
	}
	
	public void print(String message, int value)
	{
		try {
			bw.write(getTime() + "\t" + message + value + "\n");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("I 2 stupid 2 no how 2 wite");
		}
	}
	
	public void closeFile()
	{
		try {
			timer.stop();
			bw.close();
		} catch (IOException e) {
			System.out.println("Failed to close log file");
			e.printStackTrace();
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
}


