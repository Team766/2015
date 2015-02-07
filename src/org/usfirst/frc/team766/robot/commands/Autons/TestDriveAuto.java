package org.usfirst.frc.team766.robot.commands.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.UltrasonicDrive;

/**
 *
 */
public class TestDriveAuto extends CommandGroup {
    
    public  TestDriveAuto() {
    	//Drive forward using Ultrasonic Drive Command
    	addSequential(new UltrasonicDrive(RobotValues.distanceFromBox, false));
    	
    }
}
