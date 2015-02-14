package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auton_PickOneBox extends CommandGroup {
    
    public  Auton_PickOneBox() {
    	addSequential(new AdjustGripper(false));
    	addSequential(new DriveForward(RobotValues.DriveForwardDistance));
    	addSequential(new AdjustGripper(true));
    }
}
