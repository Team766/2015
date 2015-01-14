package org.usfirst.frc.team766.robot;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class UltrasonicSensor implements Runnable {

	private static final double TIMEOUT = 10;
	private static UltrasonicSensor us;

	public static UltrasonicSensor getInstance() {
		if (us == null) {
			us = new UltrasonicSensor();
		}
		return us;
	}

	private UltrasonicSensor() {
		serverThread.start();
	}

	public synchronized double getDistance() {
		return distance;
	}

	public synchronized void setValue(double d) {
		distance = d;
	}

	private byte[] getByte() {// only returns one byte
		double oldTime = Timer.getFPGATimestamp();
		while (true) {
			if (port.getBytesReceived() > 0) {
				return port.read(1);
			} else {
				if (Timer.getFPGATimestamp() - oldTime >= TIMEOUT) {
					return new byte[] { Byte.MIN_VALUE };
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private double readValue() {
		String distanceLine = new String();
		while (true) {
			byte[] curByte = getByte();
			try {
				String curString = new String(curByte, "US-ASCII");
				if (curString.equals("\n")) {
					if (distanceLine.length() == 5) {
						curString = curString.substring(curString.indexOf('R'));
						return Double.parseDouble(curString);
					}
				}
				distanceLine += curString;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// thread so robot doesn't hang
	public void run() {
		port = new SerialPort(9600, SerialPort.Port.kOnboard, 8,
				SerialPort.Parity.kNone, SerialPort.StopBits.kOne);// not sure
																	// about
																	// port type
		while (true) {
			setValue(readValue());
		}

	}

	private SerialPort port;
	private Thread serverThread = new Thread(this);
	private double distance = Double.NaN;
}
