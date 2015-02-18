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
public class Auton_Move3Boxes extends CommandGroup {
	public Auton_Move3Boxes() {

		addSequential(new AdjustGripper(true));

		for (int i = 0; i < 3; i++) {
			double distanceToPlace = (RobotValues.DriveForwardDistance - 27)
					* RobotValues.INCHES_TO_METERS;
			addSequential(new DriveForward(distanceToPlace));
			addSequential(new AdjustGripper(false));
			addSequential(new DriveForward(-distanceToPlace));
			addSequential(new DriveTurn(-90));
			addSequential(new DriveUltrasonic(RobotValues.OptimalGripperDistance));
			addSequential(new AdjustGripper(true));
			addSequential(new DriveTurn(90));
		}
	}
}
