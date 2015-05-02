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

	private static final int PORT = 9004;

	private Thread serverThread = new Thread(this);

	private static HashSet<String> names = new HashSet<String>();
	private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

	private String commands = "Drive Forward [distance] ##"
			+ "\tDrives the Robot Forward x distance ##" + "Turn [degrees] ##"
			+ "\tRotates the robnot x degrees around its center ##" + "help ##"
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
				if(input.contains("drive forward")){
					System.out.print("Driving Forward ");
					input = input.substring(14).trim();
					System.out.println(Integer.parseInt(input) + " meters");
				}else if(input.contains("left") && input.contains("turn")){
					//Turn Left 90
					if(input.length() > 10)
						System.out.println("Turning left " + input.substring(10) + " degress");
					else
						System.out.println("Turning left");
				}else if(input.contains("right") && input.contains("turn")){
					//Turn right 90
					if(input.length() > 11)
						System.out.println("Turning right " + input.substring(11) + " degress");
					else
						System.out.println("Turning right");
				}else if(input.contains("turn")){
					if(input.length() > 5)
					{
						input = input.substring(5).trim();
						System.out.println("Turning " + Integer.parseInt(input) + " degrees");
					}					
				}else if(input.equals("help")){
					for (PrintWriter writer : writers) {
						writer.println("MESSAGE " + commands);
						writer.flush();
					}
				}
				}catch(Exception e){
					for (PrintWriter writer : writers) {
						writer.println("MESSAGE BAD INPUT");
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
}