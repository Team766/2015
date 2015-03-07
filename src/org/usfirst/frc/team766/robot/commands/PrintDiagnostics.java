package org.usfirst.frc.team766.robot.commands;


/**
 * Prints information about various subsystems.
 * @author Patrick Kao
 * @author Brett Levenson
 */
public class PrintDiagnostics extends CommandBase {
	String out;
	boolean continuous;
	
    public PrintDiagnostics() {
    	this(false);
    	System.out.println("Print Diagnostisc: ");
    }
    public PrintDiagnostics(boolean c)
    {
    	out = "";
    	continuous = c;
    }

    protected void initialize() {
    	if(!continuous)
    	{
	    	pr("Drive Statistics:");
	    	pr("Drive Angle: " + Drive.getAngle());
	    	pr("Drive Cheesy Angle: " + Drive.getCheesyAngle());
	    	pr("Drive Left Encoder: " +Drive.getLeftEncoderDistance());
	    	pr("Drive Right Encoder: " +Drive.getRightEncoderDistance());
	    	pr("Drive Average Encoder: " +Drive.getAverageEncoderDistance());
	    	pr("Drive Voltage: " +Drive.getVoltage());
	    	pr("Drive Smoothing: " +Drive.getSmoothing());
	    	pr("Drive Left Velocity: " + Drive.getLeftVelocity());
	    	pr("Drive Right Velocity: " + Drive.getRightVelocity());
	    	pr("Drive Average Velocity: " + Drive.getAverageVelocity());
	    	pr("Raw Left Encoder: " + Drive.getRawLeftEncoder());
	    	pr("Raw Right Encoder: " + Drive.getRawRightEncoder());
	    	pr("PDP Temp: " + Drive.getTemp());
	    	pr("Is High Gear: " + Drive.isHighGear());
	    	
	    	pr("\nElevator Statistics:");
	    	pr("Elevator Encoder Distance: "+Elevator.getEncoders());
	    	pr("Elevator Gripper: "+Elevator.getGripper());
	    	pr("Elevator Current: " +Elevator.getElevatorCurrent());
	    	pr("Elevator Bottom Stop: " + Elevator.getBottomStop());
	    	pr("Elevator Top Stop: " + Elevator.getTopStop());
	    	pr("Elevator Velocity: " + Elevator.getVelocity());
	    	
	    	pr("\nIntake Statistics:");
	    	pr("Left Encoder: " + Intake.getEncLeft());
	    	pr("Right Encoder: " + Intake.getEncRight());
	    	pr("Left Motor Current" + Intake.getIntakeCurrentLeft());
	    	pr("Right Motor Current" + Intake.getIntakeCurrentRight());
    	}
    }

    protected void execute() {
    	out = "Drive Statistics:" + "\n" +
    	"Drive Angle: " + CommandBase.Drive.getAngle() + "\n" +
    	"Drive Cheesy Angle: " + CommandBase.Drive.getCheesyAngle() + "\n" +
    	"Drive Left Encoder: " + CommandBase.Drive.getLeftEncoderDistance() + "\n" +
    	"Drive Right Encoder: " + CommandBase.Drive.getRightEncoderDistance() + "\n" +
    	"Drive Average Encoder: " + CommandBase.Drive.getAverageEncoderDistance() + "\n" +
    	"Drive Voltage: " + CommandBase.Drive.getVoltage() + "\n" +
    	"Drive Smoothing: " + CommandBase.Drive.getSmoothing() + "\n" +
    	"Drive Left Velocity: " + CommandBase.Drive.getLeftVelocity() + "\n" +
    	"Drive Right Velocity: " + CommandBase.Drive.getRightVelocity() + "\n" +
    	"Drive Average Velocity: " + CommandBase.Drive.getAverageVelocity() + "\n" +
    	"Raw Left Encoder: " + CommandBase.Drive.getRawLeftEncoder() + "\n" +
    	"Raw Right Encoder: " + CommandBase.Drive.getRawRightEncoder() + "\n" +
    	"PDP Temp: " + CommandBase.Drive.getTemp() + "\n" +
    	"Is High Gear: " + CommandBase.Drive.isHighGear() + "\n" +
    	
    	"\nElevator Statistics:" + "\n" +
    	"Elevator Encoder Distance: " + CommandBase.Elevator.getEncoders() + "\n" +
    	"Elevator Gripper: " + CommandBase.Elevator.getGripper() + "\n" +
    	"Elevator Current: " + CommandBase.Elevator.getElevatorCurrent() + "\n" +
    	"Elevator Bottom Stop: " + CommandBase.Elevator.getBottomStop() + "\n" +
    	"Elevator Top Stop: " + CommandBase.Elevator.getTopStop() + "\n" +
    	"Elevator Velocity: " + CommandBase.Elevator.getVelocity() + "\n" +
    	
    	"\nIntake Statistics:" + "\n" +
    	"Left Encoder: " + CommandBase.Intake.getEncLeft() + "\n" +
    	"Right Encoder: " + CommandBase.Intake.getEncRight() + "\n" +
    	"Left Motor Current" + CommandBase.Intake.getIntakeCurrentLeft() + "\n" +
    	"Right Motor Current" + CommandBase.Intake.getIntakeCurrentRight() + "\n";
    }

    protected boolean isFinished() {
    	if(continuous)
    		return false;
    	return true;
    }

    protected void end() {
    }
    
    public String getOut()
    {
    	return out;
    }

    protected void interrupted() {
    	end();
    }
    
    private void pr(Object text){
    	System.out.println("\t" + text);
    }
}
