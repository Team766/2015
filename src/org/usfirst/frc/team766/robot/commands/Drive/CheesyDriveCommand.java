package org.usfirst.frc.team766.robot.commands.Drive;

import org.usfirst.frc.team766.lib.PIDController;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Controls the robot drive train using the standard Team 254 scheme.
 *
 *(modified a bit for use of Team 766)
 * @author richard@team254.com (Richard Lin)
 * 
 */
public class CheesyDriveCommand extends CommandBase {
  private static final double ANGLE_TO_POWER_RATIO = 1;
  private static final boolean NATUARL_REVERSE = false;  //Experimental
private double oldWheel = 0.0;
  private double quickStopAccumulator;
  private double throttleDeadband = 0.02; //0.02
  private double wheelDeadband = 0.02; //0.02
  
  //Bearly Driving
  private double lastRightOut;
  private double lastLeftOut;
  private double outputLeft;
  private double outputRight;
  private PIDController gyroPID = new PIDController(RobotValues.AngleKp,
			RobotValues.AngleKi, RobotValues.AngleKd,
			RobotValues.Angleoutputmax_low, RobotValues.Angleoutputmax_high,
			RobotValues.AngleThreshold);

  public CheesyDriveCommand() {
	  requires(Drive);
	  gyroPID.setSetpoint(0);
  }

  protected void initialize() {
    //drive.disableControllers() ;
	  Drive.resetGyro();
	  Drive.setSmoothing(false);
	  gyroPID.reset();
	  lastRightOut = 0;
	  lastLeftOut = 0;
	  outputLeft = 0;
	  outputRight = 0;
  }

  protected void execute() {
	  gyroPID.calculate(Drive.getAngle(), false); 
	  
	  
    if (DriverStation.getInstance().isAutonomous()) {
      return;
    }
    boolean isQuickTurn = OI.getQuickTurn();
    boolean isHighGear = OI.getShifter();

    double wheelNonLinearity;
    
    //Natural reversing
//    double beforeThrottle = OI.getThrottle();
//    double beforeSteer = OI.getSteer();
//    if(beforeThrottle < -0.05)
//    	beforeSteer = -beforeSteer;
//    	
//    double wheel = -handleDeadband(beforeSteer, wheelDeadband);
//    double throttle = handleDeadband(beforeThrottle, throttleDeadband);
    
    double wheel = -handleDeadband(OI.getSteer(), wheelDeadband);
    double throttle = handleDeadband(OI.getThrottle(), throttleDeadband);

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

    if(OI.getDriverSlowMode() || ((!Elevator.getGripper() && Math.abs(OI.getSteer()) > 0.05)) || OI.getQuickTurn())
    {
    	leftPwm *= RobotValues.SlowModeSLowFactor;
    	rightPwm *= RobotValues.SlowModeSLowFactor;
	    Drive.setLeftPower(bearafyLeftPower(leftPwm, true));
	    Drive.setRightPower(bearafyRightPower(rightPwm, true));
	    Drive.setHighGear(false);
    }
    else
    {
	    Drive.setLeftPower(bearafyLeftPower(leftPwm, false));
	    Drive.setRightPower(bearafyRightPower(rightPwm, false));
    	Drive.setHighGear(isHighGear);
    }
  }


  protected boolean isFinished() {
    return false;
  }

  protected void end() {
	  Drive.setPower(0);
	  Drive.setSmoothing(true);
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
  public double bearafyLeftPower(double in, boolean isSlow)
  {
  	  if(isSlow)
	  	outputLeft = RobotValues.SlowAlpha * lastLeftOut + (1 - RobotValues.SlowAlpha) * in;
	  else
	  	outputLeft = RobotValues.alpha * lastLeftOut + (1 - RobotValues.alpha) * in;
	  lastLeftOut = outputLeft;
	  
	  //Naturally reverses  -EXPERIMENTAL
	  if(!OI.getQuickTurn())
	  {		
		  if(Math.abs(OI.getSteer()) <= 0.01)
			  outputLeft = (outputLeft - gyroPID.getOutput() * ANGLE_TO_POWER_RATIO);
	  }
	  
		  //Accounts for drift in the gyro
//		  if(Math.abs(OI.getThrottle()) <= 0.01 && !OI.getQuickTurn())
	  if(Math.abs(OI.getThrottle()) <= 0.01 && Math.abs(OI.getSteer()) <= 0.01)
			  outputLeft = 0;
	  return outputLeft * RobotValues.leftSlowFactor;
  }
  public double bearafyRightPower(double in, boolean isSlow)
  {
  	  if(isSlow)
	  	outputRight = RobotValues.SlowAlpha * lastRightOut + (1 - RobotValues.SlowAlpha) * in;
	  else
	  	outputRight = RobotValues.alpha * lastRightOut + (1 - RobotValues.alpha) * in;
	  lastRightOut = outputRight;
	  
	  //Naturally reverses -EXPERIMENTAL
	  if(!OI.getQuickTurn())
	  {		
		  //Driving straight
		  if(Math.abs(OI.getSteer()) <= 0.01)
			  outputRight = (outputRight + gyroPID.getOutput() * ANGLE_TO_POWER_RATIO);
		  //If they are not moving, don't reset
		  //else if(Math.abs(OI.getThrottle()) <= 0.01)
		  else
			  Drive.resetGyro();
	  }
	  else
		  Drive.resetGyro();
	  
	  //if(Math.abs(OI.getThrottle()) <= 0.01 && !OI.getQuickTurn())
	  if(Math.abs(OI.getThrottle()) <= 0.01 && Math.abs(OI.getSteer()) <= 0.01)
	  {
		  outputRight = 0;
		  Drive.resetGyro();
	  }	  
	  return outputRight * RobotValues.rightSlowFactor;
  }
}
