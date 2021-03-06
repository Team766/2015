package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.DriveUltrasonic;
import org.usfirst.frc.team766.robot.commands.Elevator.DropStack;
import org.usfirst.frc.team766.robot.commands.Elevator.StackAdditionalTote;
import org.usfirst.frc.team766.robot.commands.Intake.PullToteIn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auton_Coopertition extends CommandGroup {

	public Auton_Coopertition() {
		for (int i = 0; i < 2; i++) {
			addSequential(new PullToteIn(), 100);
			addSequential(new StackAdditionalTote());
			addSequential(new DriveTurn(90));
			addSequential(new DriveForward(RobotValues.k_moveToNextTote));
			addSequential(new DriveTurn(-90));
		}
		addSequential(new PullToteIn(), 100);
		addSequential(new StackAdditionalTote());
		
		addSequential(new DriveTurn(-90));
		addSequential(new DriveForward(-(RobotValues.Box_Width/2 + RobotValues.DistanceBetweenBoxes)));
		addSequential(new DriveTurn(-90));
		addSequential(new DriveForward(-RobotValues.DistanceToStep));
		addSequential(new DriveUltrasonic(RobotValues.OptimalGripperDistance));
		
		// Puts totes down
		addSequential(new DropStack(), 100);
	}
}
