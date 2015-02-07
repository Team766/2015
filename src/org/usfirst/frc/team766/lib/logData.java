package org.usfirst.frc.team766.lib;

import java.io.File;
import java.io.FileOutputStream;
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
			FileOutputStream fOut = new FileOutputStream(new File("/tmp/log.txt"));
			fOut.close();
		} catch (IOException e) {
			System.out.println("Something went wrong in the log's constructor");
			e.printStackTrace();
		}
	}
	
	public void print(String message)
	{
		//			bw.write(getTime() + "\t" + message);
		//			bw.newLine();
					pw.println(getTime() + "\t" + message);
					System.out.println("I be writing");
	}
	
	public void print(String message, int value)
	{
		//			bw.write(getTime() + "\t" + message + value);
//			bw.newLine();
		pw.println(getTime() + "\t" + message + value);
	}
	
	public void closeFile()
	{
		timer.stop();
		pw.close();
		//bw.close();
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


