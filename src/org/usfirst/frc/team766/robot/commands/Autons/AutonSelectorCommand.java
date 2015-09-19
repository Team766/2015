package org.usfirst.frc.team766.robot.commands.Autons;

import org.usfirst.frc.team766.lib.TextCompiler;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.Drive.DriveTurn;
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

        switch(RobotValues.Autons[mode]){
            //runs the Move Command
            case "Move Forward":
                System.out.println("Drive Forward to Landmark Auton");
                addSequential(new Auton_DriveForward());
                //addSequential(new DriveForward(RobotValues.DriveForwardDistance));
                //addSequential(new TimedDrive(2.25));
                break;
            case "Pickup One Tote":
            	System.out.println("Pick One Box Auton");
            	addSequential(new Auton_PickOneBox());
            	addSequential(new CalibrateElevator());
            	break;
            	
            case "Rotate 90 degrees":
                System.out.println("Rotate Auton");
                addSequential(new DriveTurn(90));
                break;
            
            case "Move to landfill"://Align robot with box
            	System.out.println("Move to Landfill Auton");
            	addSequential(new Auton_MoveToLandfill());
            	break;
            
            case "3 Tote Hot":
            	System.out.println("Push 3 Boxes Auto");
            	addSequential(new Auton_Push3Boxes());
            	break;
            	
            case "Vision Drive":
            	System.out.println("Vision Drive Auto");
            	addSequential(new OpenCvTest());
            	break;
        
            case "Coopertition":
            	System.out.println("Coopertition Auto");
            	addSequential(new Auton_Coopertition());
            	break;
            	
            case "No Auton Selected":
            	System.out.println("No Auton Selected");
            	break;
            
            case "One Container":
            	System.out.println("One Container Auton");
            	addSequential(new Auton_OneContainer());
            	addSequential(new CalibrateElevator());
            	break;
            	
            case "Three Tote":
            	System.out.println("Three Tote Auton");
            	addSequential(new Auton_ThreeTotes());
            	break;
            
            case "Calibrate Elevator":
            	System.out.println("Calibrate Elevator Auton");
            	addSequential(new CalibrateElevator());
            	break;
            	
            case "Text Controlled":
            	System.out.println("Control the robot with text");
            	addSequential(new TextToControls());
            	break;
            	
            case "File Compiler":
            	System.out.println("Run commands in file");
            	addSequential(new TextCompiler("/tmp/Commands.txt"));
            	break;
            case "Container & Tote":
            	System.out.println("Contianer then Tote");
            	addSequential(new Auton_CanAndTote());
            	break;
            	
            default:{
                System.out.println("Auton selection failed");
                break;
            }
        }
    }
}
