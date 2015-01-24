package org.usfirst.frc.team766.robot.commands.Elevator;
import org.usfirst.frc.team766.robot.RobotValues;
import org.usfirst.frc.team766.robot.commands.CommandBase;

/**
 * Mount for moving camera
 *
 * @author blevenson
 */

public class CameraMount extends CommandBase{
		private double twist;
		private double pull;
		
		private double outR;
		private double outP;
		private double loopSpeed;
		private double looper;
		
		public CameraMount(){}

		protected void initialize() {
			twist = 0;
			pull = 0;
			outR = 0;
			outP = 0;
			loopSpeed = 10;
			looper = 0;
		}

		protected void execute() {
			twist = OI.getTwist();
			pull = OI.getLeft();
			
//			outR = (twist / 2) + 0.5;
//			outP = (pull / 2) + 0.5;
			
			
			if(looper > loopSpeed)
			{
				update();
				looper = 0;
			}
			Elevator.rotateCamera(outR);
			Elevator.rollCamera(outP);
			
			looper += 0.5;
		}
		
		public void update()
		{
			if(outR <= 0)outR = 0;
			if(outP <= 0)outP = 0;
			if(outR >= 1)outR = 1;
			if(outP >= 1)outP = 1;
		}

		protected boolean isFinished() {
			return false;
		}

		protected void end() {
			Elevator.rotateCamera(0.0);
			Elevator.rollCamera(0.0);
		}

		protected void interrupted() {
			end();
		}
	}

