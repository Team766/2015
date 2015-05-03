package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveForward;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
import org.usfirst.frc.team766.robot.commands.Drive.TimedDrive;
import org.usfirst.frc.team766.robot.commands.Elevator.CalibrateElevator;

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
                addSequential(new Auton_DriveForward());
                //addSequential(new DriveForward(RobotValues.DriveForwardDistance));
                //addSequential(new TimedDrive(2.25));
                break;
            case RobotValues.Auton_PickOneBox:
            	System.out.println("Pick One Box Auton");
            	addSequential(new Auton_PickOneBox());
            	addSequential(new CalibrateElevator());
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
            
            case RobotValues.Auton_Container:
            	System.out.println("One Container Auton");
            	addSequential(new Auton_OneContainer());
            	addSequential(new CalibrateElevator());
            	break;
            	
            case RobotValues.Auton_3Tote:
            	System.out.println("Three Tote Auton");
            	addSequential(new Auton_ThreeTotes());
            	break;
            
            case RobotValues.Auton_CalibrateElevator:
            	System.out.println("Calibrate Elevator Auton");
            	addSequential(new CalibrateElevator());
            	break;
            	
            case RobotValues.Auton_TextControlled:
            	System.out.println("Control the robot with text");
            	addSequential(new TextToControls());
            	break;
            	
            default:{
                System.out.println("Auton selection failed");
                break;
            }
        }
    }
}
