package org.usfirst.frc.team766.robot.commands;


/**
 * Prints information about various subsystems.
 * @author Patrick Kao
 */
public class PrintDiagnostics extends CommandBase {

    public PrintDiagnostics() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pr("Drive Statistics:");
    	pr("Drive Angle: " + Drive.getAngle());
    	pr("Drive Left Encoder: " +Drive.getLeftEncoderDistance());
    	pr("Drive Right Encoder: " +Drive.getRightEncoderDistance());
    	pr("Drive Average Encoder: " +Drive.getAverageEncoderDistance());
    	pr("Drive Voltage: " +Drive.getVoltage());
    	pr("Drive Smoothing: " +Drive.getSmoothing());
    	pr("Is High Gear: " + Drive.isHighGear());
    	pr("Elevator Statistics:");
    	pr("Elevator Encoder Distance: "+Elevator.getEncoders());
    	pr("Elevator Gripper: "+Elevator.getGripper());
    	pr("Elevator Current: " +Elevator.getElevatorCurrent());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private void pr(Object text){
    	System.out.println("Print Diagnostisc: " + text);
    }
}
