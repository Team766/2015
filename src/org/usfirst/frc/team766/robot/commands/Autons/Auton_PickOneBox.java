package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;
import org.usfirst.frc.team766.robot.commands.Elevator.DropStack;
import org.usfirst.frc.team766.robot.commands.Elevator.MoveElevatorWaypoint;
import org.usfirst.frc.team766.robot.commands.Intake.PullToteIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auton_PickOneBox extends CommandGroup {
    
    public  Auton_PickOneBox() {
    	addSequential(new PullToteIn());
    	addSequential(new AdjustGripper(true));
    	addParallel(new MoveElevatorWaypoint(3));
    	addSequential(new DriveForward(RobotValues.DriveForwardDistance));
    	addSequential(new DropStack());
    }
}
