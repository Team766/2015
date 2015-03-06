package org.usfirst.frc.team766.robot.commands;


/**
 * Prints information about various subsystems.
 * @author Patrick Kao
 * @author Brett Levenson
 */
public class PrintDiagnostics extends CommandBase {

    public PrintDiagnostics() {
    	System.out.println("Print Diagnostisc: ");
    }

    protected void initialize() {
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

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
    
    private void pr(Object text){
    	System.out.println(text);
    }
}
