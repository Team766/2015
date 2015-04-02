package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.CalibrateElevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Drives forward into the auto zone during auton using the drive forward command.
 *	Once there it recalibrates the elevator
 */
public class Auton_DriveForward extends CommandGroup {
    
    public  Auton_DriveForward() {
    	addSequential(new DriveForward(RobotValues.DriveForwardDistance));
    	addSequential(new AdjustGripper(false));
    	addSequential(new CalibrateElevator());
    }
}
