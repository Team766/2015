package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.DriveUltrasonic;
import org.usfirst.frc.team766.robot.commands.Elevator.AdjustGripper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auton_Push3Boxes extends CommandGroup {
	
	public Auton_Push3Boxes() {//Position robot behind tote pointing toward auton zone
		for (int i = 0; i < 3; i++) {
			double distanceToPlace = (RobotValues.DriveForwardDistance - 27)
					* RobotValues.INCHES_TO_METERS;
			addSequential(new DriveForward(distanceToPlace));
			addSequential(new DriveForward(-distanceToPlace-1));
			addSequential(new DriveTurn(-90));
			addSequential(new DriveForward(RobotValues.DistanceBetweenBoxes + RobotValues.Box_Width/2));
			addSequential(new DriveTurn(90));
			addSequential(new DriveUltrasonic(.3));
		}
	}
}
