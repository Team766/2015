package org.usfirst.frc.team766.robot.commands;

import org.usfirst.frc.team766.robot.Ports;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestHallEffectSensor extends Command {

    public TestHallEffectSensor() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	sensor = new AnalogInput(Ports.DIO_HallEffectSensor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Hall Effect Sensor Distance: " + sensor.getVoltage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	sensor.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    private AnalogInput sensor;
}