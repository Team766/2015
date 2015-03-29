package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.TimedDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Choose which auton command to run
 * This runs after the AutonSelector is updated.
 * Runs the appropriate auton mode
 * @author Patrick Kao
 * @author Brett Levenson blevenson68@gmail.com
 */

public class AutonSelectorCommand extends CommandGroup{

    public AutonSelectorCommand(int mode){
    	//pass in the auton mode

        switch(mode){
            //runs the Move Command
            case RobotValues.Auton_Move:
                System.out.println("Drive Forward to Landmark Auton");
                addSequential(new DriveForward(RobotValues.DriveForwardDistance));
                //addSequential(new TimedDrive(2.25));
                break;
            case RobotValues.Auton_PickOneBox:
            	System.out.println("Pick One Box Auton");
            	addSequential(new Auton_PickOneBox());
            	break;
            	
            case RobotValues.Auton_Rotate:
                System.out.println("Rotate Auton");
                addSequential(new DriveTurn(90));
                break;
            
            case RobotValues.Auton_MoveToLandfill://Align robot with box
            	System.out.println("Move to Landfill Auton");
            	addSequential(new Auton_MoveToLandfill());
            	break;
            
            case RobotValues.Auton_Push3Boxes:
            	System.out.println("Push 3 Boxes Auto");
            	addSequential(new Auton_Push3Boxes());
            	break;
            	
            case RobotValues.Auton_VisionDrive:
            	System.out.println("Vision Drive Auto");
            	addSequential(new OpenCvTest());
            	break;
        
            case RobotValues.Auton_Coopertition:
            	System.out.println("Coopertition Auto");
            	addSequential(new Auton_Coopertition());
            	break;
            	
            case RobotValues.Auton_None:
            	System.out.println("No auton selected");
            	break;
            	
            default:{
                System.out.println("Auton selection failed");
                break;
            }
        }
    }
}
