package org.usfirst.frc.team766.lib;

import edu.wpi.first.wpilibj.Timer;

/*
* Creates a log for storing values on the roboRio that can then be accesed using ftp
* username: admin
* password: 
* 
* Created by Brett Levenson
*/
 
public class logData {
	
	//private PrintWriter pw;
	private Timer timer = new Timer();
	private boolean INDENT = false;
	private String name;
	
	private String html = "<body style=\"background-color:rgba(180, 28, 28, 0.8)\">";
			
	public logData(String name){
		this.name = name;
//		try {
			//pw = new PrintWriter(new FileWriter("/tmp/logs/" + name + ".txt"));
			timer.start();
//		} catch (IOException e) {
			System.out.println("Something went wrong in the log's constructor");
			timer.stop();
//		}
		html += "<h2 style = \"color: white\">" + name + "</h2>  <p style = \"color: #fc4\">";
	}
	
	public void print(String message)
	{
		try{
			if(INDENT){
				//pw.println(getTime() + "\t\t" + message);
				html += getTime() + "\t\t" + message + "<br>";
			}
			else{
				//pw.println(getTime() + "\t" + message);
				html += getTime() + "\t\t" + message + "<br>";
			}
		}catch(NullPointerException e)
		{
			System.out.println("Null Pointer alert!");
		}
	}
	
	public void printRaw(String in)
	{
		try{
			//pw.println(in);
			html += in + "<br>";
		}catch(NullPointerException e)
		{
			System.out.println("Can't print raw value: " + in);
		}
	}
	
	public void print(String message, int value)
	{
		try{
			if(INDENT){
				//pw.println(getTime() + "\t\t" + message + value);
				html += getTime() + "\t\t" + message + value + "<br>";
			}
			
			else{
				//pw.println(getTime() + "\t" + message + value);
				html += getTime() + "\t" + message + value + "<br>";
			}
		}catch(NullPointerException e)
		{
			System.out.println("Can't save log!");
		}
	}
	
	public void closeFile()
	{
		timer.stop();
		
		try{
			//pw.close();
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
        return "<p1 style = \"color: white\">" + hours + ":" + minutes + ":" + seconds + "</p1>";
    }

	public boolean isIndent() {
		return INDENT;
	}

	public void setIndent(boolean iNDENT) {
		INDENT = iNDENT;
	}
	
	public String getName(){
		return name;
	}
	
	public String getHTML(){
		return html;
	}
}


