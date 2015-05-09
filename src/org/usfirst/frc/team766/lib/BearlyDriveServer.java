package org.usfirst.frc.team766.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * Text line robot controls from a distance
 */
public class BearlyDriveServer implements Runnable {
	private static BearlyDriveServer instance_;

	private static final int PORT = 9009;

	private Thread serverThread = new Thread(this);

	private static HashSet<String> names = new HashSet<String>();
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
	//Sendable values
	private double distance = 0;
	private int degrees = 0;
	private boolean claw = false;

	private String commands = "Drive Forward [distance] ##"
			+ "\tDrives the Robot Forward x distance ##" + "Turn [degrees] ##"
			+ "\tRotates the robot x degrees around its center ##" + "Turn Left ##"
			+ "\tRotates the robot left 90 degrees around its center ##" + "Turn Right ##"
			+ "\tRotates the robot right 90 degrees around its center ##" + "Turn Left [degrees] ##"
			+ "\tRotates the robot left x degrees around its center ##" + "Turn Right [degrees] ##"
			+ "\tRotates the robot right x degrees around its center ##" + "Open/Close/Toggle Claw ##"
			+ "\tOpens, closses, or toggles the state of the elevator's arm ##" + "help ##"
			+ "\tList all the commands and what they do";

	private String name;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public static BearlyDriveServer getInstance() {
		if (instance_ == null)
			try {
				instance_ = new BearlyDriveServer(
						new ServerSocket(PORT).accept());
			} catch (IOException e) {
				System.out.println("Failed to start Bearly Drive Server");
			}
		return instance_;
	}

	private BearlyDriveServer(Socket socket) {
		this.socket = socket;
		serverThread.start();
	}
	
	public void start() {
		try{
			serverThread.start();
		}catch(Exception e){
			System.out.println("Failed to connect thread");
		}
	}

	public void run() {
		try {

			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				out.println("SUBMITNAME");
				name = in.readLine();
				if (name == null) {
					return;
				}
				synchronized (names) {
					if (!names.contains(name)) {
						names.add(name);
						break;
					}
				}
			}

			out.println("NAMEACCEPTED");
			writers.add(out);

			while (true) {
				String input = in.readLine();
				if (input == null) {
					return;
				}
				for (PrintWriter writer : writers) {
					writer.println("MESSAGE " + name + ": " + input);
				}
				
				try{
					
				input = input.toLowerCase();
				//Drive Forward [degrees]
				if(input.contains("drive forward")){
					System.out.print("Driving Forward ");
					input = input.substring(14).trim();
					System.out.println(Integer.parseInt(input) + " meters");
					distance = Double.parseDouble(input);
				//Turn left
				}else if(input.contains("left") && input.contains("turn")){
					//Turn Left 90
					if(input.length() > 10){
						System.out.println("Turning left " + input.substring(10) + " degress");
						degrees = -Math.abs(Integer.parseInt(input.substring(10)));
					}
					else{
						System.out.println("Turning left");
						degrees = -90;
					}
				//Turn right
				}else if(input.contains("right") && input.contains("turn")){
					//Turn right 90
					if(input.length() > 11){
						System.out.println("Turning right " + input.substring(11) + " degress");
						degrees = Math.abs(Integer.parseInt(input.substring(11)));
					}
					else{
						System.out.println("Turning right");
						degrees = 90;
					}
				//Turn [degrees]
				}else if(input.contains("turn")){
					if(input.length() > 5)
					{
						input = input.substring(5).trim();
						System.out.println("Turning " + Integer.parseInt(input) + " degrees");
						degrees = Integer.parseInt(input);
					}
					
				//Open claw
				}else if(input.contains("claw")){
					input = input.trim().substring(0,input.length()-5);
					//open or close
					if(input.contains("open")){
						System.out.println("Opening the claw");
						claw = false;
					}
					else if(input.contains("close")){
						System.out.println("Closing the claw");
						claw = true;
					}
					else if(input.contains("toggle")){
						System.out.println("Toggling the state of the claw");
						claw = !claw;
					}
					else{
						//Prints invalid input
						throw new Exception();
					}
					
				//Help	
				}else if(input.equals("help")){
					for (PrintWriter writer : writers) {
						writer.println("MESSAGE " + commands);
						writer.flush();
					}
				}
				}catch(Exception e){
					for (PrintWriter writer : writers) {
						writer.println("MESSAGE Invalid Input");
					}
				}
			}
			
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			// This client is going down! Remove its name and its print
			// writer from the sets, and close its socket.
			if (name != null) {
				names.remove(name);
			}
			if (out != null) {
				writers.remove(out);
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	public double getDriveDistance(){
		return distance;
	}
	
	public int getDegrees(){
		return degrees;
	}
	
	public boolean getClawState(){
		return claw;
	}
}