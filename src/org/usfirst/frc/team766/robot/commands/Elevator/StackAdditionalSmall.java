<<<<<<< HEAD
package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackAdditionalSmall extends CommandGroup {
    
    public  StackAdditionalSmall() {
    	addSequential(new MoveElevatorWaypoint(1));
//    	addSequential(new IntakeTote());
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(0));
    	addSequential(new AdjustGripper(true));
    	addSequential(new MoveElevatorHeight(RobotValues.ElevatorPresets[1] + (2 * RobotValues.INCHES_TO_METERS)));
    	addSequential(new IncrementNumTotes(1));
    }
}
=======
package org.usfirst.frc.team766.robot.commands.Elevator;

import org.usfirst.frc.team766.robot.RobotValues;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StackAdditionalSmall extends CommandGroup {
    
    public  StackAdditionalSmall() {
    	addSequential(new MoveElevatorWaypoint(1));
//    	addSequential(new IntakeTote());
    	addSequential(new AdjustGripper(false));
    	addSequential(new MoveElevatorWaypoint(0));
    	addSequential(new AdjustGripper(true));
    	addSequential(new MoveElevatorHeight(RobotValues.ElevatorPresets[1] + (2 * RobotValues.INCHES_TO_METERS)));
    	addSequential(new IncrementNumTotes(1));
    }
}
>>>>>>> origin/master
