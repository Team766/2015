package org.usfirst.frc.team766.robot.testing;

import java.util.Scanner;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;

/**
 * Controls the robot drive train using the standard Team 254 scheme.
 *
 *(modified a bit for use of Team 766)
 * @author richard@team254.com (Richard Lin)
 * 
 */
public class BearlyDriveTester {
  private static final double ANGLE_TO_POWER_RATIO = 1;
private double oldWheel = 0.0;
  private double quickStopAccumulator;
  private double throttleDeadband = 0.02;
  private double wheelDeadband = 0.02;
  
  private double scanThrottle, scanSteer;
  private boolean scanQuickTurn;
  
  //Bearly Driving
  private double lastRightOut;
  private double lastLeftOut;
  private double outputLeft;
  private double outputRight;
  private PIDController gyroPID = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

  public BearlyDriveTester() {
	  gyroPID.setSetpoint(0);
  }
  
  public static void main(String[] args)
  {
	  BearlyDriveTester driver = new BearlyDriveTester();
	  driver.initialize();
	  while(!driver.isFinished())
	  {
		  driver.execute();
	  }
	  driver.end();
  }

  protected void initialize() {
	  gyroPID.reset();
	  lastRightOut = 0;
	  lastLeftOut = 0;
	  outputLeft = 0;
	  outputRight = 0;
  }

  protected void execute() {
	  Scanner scan = new Scanner(System.in);
	  System.out.print("Cheesy Angle: ");
	  gyroPID.calculate(scan.nextDouble(), false); 
	  
	  System.out.print("QuickTurn: ");
	  scanQuickTurn = scan.nextBoolean();
    boolean isQuickTurn = scanQuickTurn;
    System.out.print("High Gear: ");
    boolean isHighGear = scan.nextBoolean();

    double wheelNonLinearity;

    System.out.print("Steer: ");
    scanSteer = scan.nextDouble();
    double wheel = -handleDeadband(scanSteer, wheelDeadband);
    System.out.print("Throttle: ");
    scanThrottle = scan.nextDouble();
    double throttle = handleDeadband(scanThrottle, throttleDeadband);

    double negInertia = wheel - oldWheel;
    oldWheel = wheel;
    
    if (isHighGear) {
      wheelNonLinearity = 0.6;
      // Apply a sin function that's scaled to make it feel better.
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
    } else {
      wheelNonLinearity = 0.5;
      // Apply a sin function that's scaled to make it feel better.
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
      wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) /
          Math.sin(Math.PI / 2.0 * wheelNonLinearity);
    }

    double leftPwm, rightPwm, overPower;
    double sensitivity = 1.7;

    double angularPower;
    double linearPower;

    // Negative inertia!
    double negInertiaAccumulator = 0.0;
    double negInertiaScalar;
    if (isHighGear) {
      negInertiaScalar = 5.0;
      sensitivity = RobotValues.sensitivityHigh;
    } else {
      if (wheel * negInertia > 0) {
        negInertiaScalar = 2.5;
      } else {
        if (Math.abs(wheel) > 0.65) {
          negInertiaScalar = 5.0;
        } else {
          negInertiaScalar = 3.0;
        }
      }
      sensitivity = RobotValues.sensitivityLow;

      if (Math.abs(throttle) > 0.1) {
       // sensitivity = 1.0 - (1.0 - sensitivity) / Math.abs(throttle);
      }
    }
    double negInertiaPower = negInertia * negInertiaScalar;
    negInertiaAccumulator += negInertiaPower;

    wheel = wheel + negInertiaAccumulator;
    if (negInertiaAccumulator > 1) {
      negInertiaAccumulator -= 1;
    } else if (negInertiaAccumulator < -1) {
      negInertiaAccumulator += 1;
    } else {
      negInertiaAccumulator = 0;
    }
    linearPower = throttle;

    // Quickturn!
    if (isQuickTurn) {
      if (Math.abs(linearPower) < 0.2) {
        double alpha = 0.1;
        quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha *
            limit(wheel, 1.0) * 5;
      }
      overPower = 1.0;
      if (isHighGear) {
        sensitivity = 1.0;
      } else {
        sensitivity = 1.0;
      }
      angularPower = wheel;
    } else {
      overPower = 0.0;
      angularPower = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator;
      if (quickStopAccumulator > 1) {
        quickStopAccumulator -= 1;
      } else if (quickStopAccumulator < -1) {
        quickStopAccumulator += 1;
      } else {
        quickStopAccumulator = 0.0;
      }
    }

    rightPwm = leftPwm = linearPower;
    leftPwm += angularPower;
    rightPwm -= angularPower;

    if (leftPwm > 1.0) {
      rightPwm -= overPower * (leftPwm - 1.0);
      leftPwm = 1.0;
    } else if (rightPwm > 1.0) {
      leftPwm -= overPower * (rightPwm - 1.0);
      rightPwm = 1.0;
    } else if (leftPwm < -1.0) {
      rightPwm += overPower * (-1.0 - leftPwm);
      leftPwm = -1.0;
    } else if (rightPwm < -1.0) {
      leftPwm += overPower * (-1.0 - rightPwm);
      rightPwm = -1.0;
    }

    System.out.print("Slow Mode: ");
    if(scan.nextBoolean())
    {
    	leftPwm *= RobotValues.SlowModeSLowFactor;
    	rightPwm *= RobotValues.SlowModeSLowFactor;
	    System.out.println("Slow Mode Left: " + bearafyLeftPower(leftPwm, RobotValues.SlowAlpha));
	    System.out.println("Slow Mode Right: " + bearafyRightPower(rightPwm, RobotValues.SlowAlpha));
	    System.out.println("Slow Mode Shifter: " + false);
    }
    else
    {
    	System.out.println("Normal Mode Left: " + bearafyLeftPower(leftPwm, RobotValues.alpha));
    	System.out.println("Normal Mode Right: " + bearafyRightPower(rightPwm, RobotValues.alpha));
    	System.out.println("Normal Mode Shifter: " + isHighGear);
    }
    scan.close();
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
	  end();
  }

  public double handleDeadband(double val, double deadband) {
    return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
  }
  public static double limit(double v, double limit) {
	return (Math.abs(v) < limit) ? v : limit * (v < 0 ? -1 : 1);
  }
  public double bearafyLeftPower(double in, double alpha)
  {
	  outputLeft = alpha * lastLeftOut + (1 - alpha) * in;
	  lastLeftOut = outputLeft;
	  
	  //Naturally reverses  -EXPERIMENTAL
	  if(!scanQuickTurn)
	  {		
		  //If you want to turn, without quick turning
		  if(Math.abs(scanSteer) > 0.001 && scanThrottle < -0.001)
			  outputLeft = -outputLeft;
	  }
	  
	  if(Math.abs(scanSteer) < 0.05)
		  outputLeft = (outputLeft -gyroPID.getOutput() * ANGLE_TO_POWER_RATIO);
	  //Accounts for drift in the gyro
	  if(Math.abs(scanThrottle) <= 0.001)
		  outputLeft = 0;
	  return outputLeft;
  }
  public double bearafyRightPower(double in, double alpha)
  {
	  outputRight = alpha * lastRightOut + (1 - alpha) * in;
	  lastRightOut = outputRight;
	  
	  //Naturally reverses -EXPERIMENTAL
	  if(!scanQuickTurn)
	  {		
		  //If you want to turn, without quick turning
		  if(Math.abs(scanSteer) > 0.001 && scanThrottle < -0.001)
			  outputRight = -outputRight;
	  }
	  
	  if(Math.abs(scanSteer) < 0.05)
		  outputRight = (outputRight + gyroPID.getOutput() * ANGLE_TO_POWER_RATIO);
	  else
		  System.out.println("Resetting Gyro");
	  if(Math.abs(scanThrottle) <= 0.001)
	  {
		  outputRight = 0;
		  System.out.println("Resetting Gyro");
	  }
	  return outputRight;
  }
}
