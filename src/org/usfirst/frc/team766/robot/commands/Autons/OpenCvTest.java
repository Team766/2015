package org.usfirst.frc.team766.robot.commands.Autons;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team766.lib.AxisCamera;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team766.robot.Ultrasonic.UltrasonicSensor;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Driving subsystem
 *
 * @author blevenson
 */
public class OpenCvTest extends CommandBase {
	private Mat hsv = new Mat();
	private Mat satImg = new Mat();
	private Mat yellowImg = new Mat();
	private Mat img = new Mat();
	
	private double leftMotorC = 0d;
	private double rightMotorC = 0d;
	private boolean done;
	
	private Point yellowLeft;
	private Point yellowRight;
	
	private Point left;
	private Point right;
	private Point bigCenter;
	private Point leftDivider;
	private Point rightDivider;
	
	private AxisCamera cam;
	private double oldArea;
	private double area;
	private double bigHeight = 0;
	private double bigWidth = 0;
	private double centerWidth = 213;
	private double setValue = 0;
	
	private int picCount = 0;
	//Box Tracer
		//min
		public int B_HMIN = 20;
		private int B_SMIN = 80;
		private int B_VMIN = 125;
		//Max
		private int B_HMAX = 100;
		private int B_SMAX = 255;
		private int B_VMAX = 255;
	
	private final boolean PRINT = false;
	//Used with Ultrasonic to tell when to stop
	private double kDistToPickUp = 600;
	
	//Distance starts at +1 so it doesn't end before connecting
	private double distance = ++kDistToPickUp;
	
	static
	{
		System.load("/usr/local/lib/lib_OpenCV/java/libopencv_java2410.so");
	}

	protected void initialize() {
		cam = new AxisCamera("169.254.2.2");
		
		left = new Point();
		right = new Point();
		yellowLeft = new Point();
		yellowRight = new Point();
		leftDivider = new Point();
		rightDivider = new Point();
		bigCenter = new Point();
		oldArea = 0;
		area = 0;
		
		leftDivider.y = 0;
	}

	protected void execute() {
		distance = UltrasonicSensor.getInstance().getDistanceDouble();
		img = cam.getImage();
		if (img.empty()) return;
		System.out.println("SCN: " + img.channels());
		
		Imgproc.cvtColor(img, hsv, Imgproc.COLOR_BGR2HSV);
		
		//Real Values
		Core.inRange(hsv, new Scalar(B_HMIN, B_SMIN, B_VMIN), new Scalar(B_HMAX, B_SMAX, B_VMAX), yellowImg);
		
		Imgproc.erode(yellowImg, yellowImg, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,5)));
		Imgproc.erode(yellowImg, yellowImg, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3)));
		Imgproc.erode(yellowImg, yellowImg, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3,3)));
		Imgproc.dilate(yellowImg, yellowImg, Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5,5)));
				
		//Track image
		Mat yellowHierarchy = new Mat();
		List<MatOfPoint> yellowContours = new ArrayList<MatOfPoint>();
		
		Imgproc.findContours(yellowImg, yellowContours, yellowHierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_TC89_KCOS);
		Imgproc.drawContours(yellowImg, yellowContours, -1, new Scalar(255,255,0));
		
		if(yellowContours.size() > 0)
		{
			yellowLeft = new Point(Imgproc.boundingRect(yellowContours.get(0)).x, Imgproc.boundingRect(yellowContours.get(0)).y);
			yellowRight = new Point(Imgproc.boundingRect(yellowContours.get(0)).x, Imgproc.boundingRect(yellowContours.get(0)).y);
		}
					
		//Core.rectangle(satImg, left, right, new Scalar(255,255,0));
		
		for(MatOfPoint point : yellowContours)
		{
			Rect rectangle = Imgproc.boundingRect(point);
			
			//System.out.println("(" + rectangle.x + " " + rectangle.y + ")");
			//Find farthest left and right corners of tote
			if(rectangle.x < yellowLeft.x)
				yellowLeft.x = rectangle.x;
			if(rectangle.y < yellowLeft.y)
				yellowLeft.y = rectangle.y;
			
			if((rectangle.x + rectangle.width) > yellowRight.x)
				yellowRight.x = rectangle.x + rectangle.width;
			if((rectangle.y + rectangle.height) > yellowRight.y)
				yellowRight.y = rectangle.y + rectangle.height;
		}
		
		Core.rectangle(yellowImg, yellowLeft, yellowRight, new Scalar(255,255,0));
		
		//get average of box
//		left.x = (left.x + yellowLeft.x) / 2;
//		left.y = (left.y + yellowLeft.y) / 2;
//		right.x = (right.x + yellowRight.x) / 2;
//		right.y = (right.y + yellowRight.y) / 2;
		
		satImg = yellowImg;
		left = yellowLeft;
		right = yellowRight;
		
		Core.rectangle(satImg, left, right, new Scalar(255,255,0));
		
		bigHeight = (Math.sqrt(Math.pow((left.x - left.x), 2) + Math.pow((right.y - left.y), 2)));
		bigWidth = (Math.sqrt(Math.pow((right.x - left.x), 2) + Math.pow((left.y - left.y), 2)));
		//Check Width
		if((bigWidth / bigHeight) >= 2.3)
		{
			System.out.println("Recalculate Width");
		}
		//Check Height
		else if((bigHeight / bigWidth) >= 0.55)
			System.out.println("Recalculate Height");
		area = bigHeight * bigWidth;
		System.out.println(bigHeight + " " + bigWidth);
		
		//Calculate center of tote.  Used to track the tote's position in the frame
		bigCenter.x = left.x + bigWidth/2;
		bigCenter.y = left.y + bigHeight/2;
		Core.circle(satImg, bigCenter, 10, new Scalar(255, 0, 0));
		
		//Chech center of tote
		if((bigCenter.x > (satImg.width()/2)) && (bigCenter.x < (satImg.width())))
		{
			setValue = (1 / (satImg.width() - rightDivider.x)) * (bigCenter.x - rightDivider.x);
			rightMotorC = -setValue;
			leftMotorC = setValue;
		}
		else if((bigCenter.x < (satImg.width()/2)) && ( bigCenter.x > 0))
		{
			setValue = (((1 / (leftDivider.x)) * (bigCenter.x))- 1);
			rightMotorC = setValue;
			leftMotorC = -(setValue);
		}
		
		
		//truncate values
		area = (int)area / 1000;
		
//		if(oldArea > area)
//		{
//			System.out.println("You are going away from the box");
//		}else if(area > oldArea)
//		{
//			System.out.println("You are approching the tote");
//		}
		
//		System.out.println("Old: " + oldArea + "  New Area: " + area);
		oldArea = area;
		
		rightDivider.y = satImg.height();
		leftDivider.x = ((satImg.width() / 2) - (centerWidth / 2));
		rightDivider.x = ((satImg.width() / 2) + (centerWidth / 2));
		
		if((bigCenter.x <= rightDivider.x) && (bigCenter.x >= leftDivider.x))
		{
			if(bigCenter.x >= leftDivider.x && (bigCenter.x <= (satImg.width() / 2)))
				setValue = (1 / ((satImg.width() / 2) - leftDivider.x)) * (bigCenter.x - leftDivider.x);
			else if((bigCenter.x <= rightDivider.x) && (bigCenter.x >= (satImg.width() / 2)))
				setValue = (((-1 / (rightDivider.x - (satImg.width() / 2))) * (bigCenter.x - (satImg.width() / 2))) + 1);
			leftMotorC = setValue;
			rightMotorC = setValue;
		}
		
		Core.rectangle(satImg, leftDivider, rightDivider, new Scalar(255,255,0));

		if(PRINT)savePics(img);
		
		if(distance < kDistToPickUp) done = true;
		
		//Recalculate values
		centerWidth = ((540d / 4700d) * (distance - 300d)) + 100d;
		
		
		Drive.setLeftPower(leftMotorC);
		Drive.setRightPower(rightMotorC);
		System.out.println("Left: " + leftMotorC + " Right: " + rightMotorC);

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {

	}

	protected void interrupted() {
		end();
	}
	
	
	public void savePics(Mat img) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			System.out.println("0 noses.....I 2 tyd to swep");
		}
		// change address for your computer
		Highgui.imwrite("C://Users/Student/ImagePics/filteredBox_" + picCount + ".jpeg", img);
		picCount++;
		
		if(picCount >= 10)done = true;
		
		System.out.println("Looping: " + picCount);
	}
}