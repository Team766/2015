package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForwardCommand;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.VisionDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Choose which auton command to run
 * This runs after the AutonSelector is updated.
 * Runs the appropriate auton mode
 * 
 * @author Brett Levenson blevenson68@gmail.com
 */

public class AutonSelectorCommand extends CommandGroup{

    public AutonSelectorCommand(int mode){
    	//pass in the auton mode

        switch(mode){
        	//runs the VisionDriveCommand
//            case RobotValues.Auton_VisionDrive:
//                System.out.println("Vision Drive");
//                addSequential(new VisionDrive());
//                break;
//           
            //runs the Move Command
            case RobotValues.Auton_Move:
                System.out.println("Drive Forward Auton");
                addSequential(new DriveForwardCommand(RobotValues.DriveForwardDistance));
                break;
            
            case RobotValues.Auton_PickOneBox:
            	System.out.println("Pick One Box Auton");
            	addSequential(new Auton_PickOneBox());
            	break;
            	
            case RobotValues.Auton_Rotate:
                System.out.println("Rotate Auton");
                addSequential(new DriveTurn(90));
                break;	
            default:{
                System.out.println("Auton selection failed");
                break;
            }
        }
    }
}
