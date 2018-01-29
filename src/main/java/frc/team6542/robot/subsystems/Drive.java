package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.team6542.robot.RobotMap;
import frc.team6542.robot.commands.*;
import edu.wpi.first.wpilibj.Spark;

/**
 *
 */
public class Drive extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Spark left = new Spark(RobotMap.leftDrivePWM);
	private Spark right = new Spark(RobotMap.rightDrivePWM);
	private static Drive instance;
	
	private Drive() {
		
	}
	
	public static Drive getInstance() {
		if (instance == null) {
			instance = new Drive();
		}
		return instance;
	}
	
	public enum Side {
		kLeft,
		kRight;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GTADrive());
    }
    
    public void set (Side s, double value) {
    	if (s == Side.kLeft) {
    		left.set(value);
    	} else if (s == Side.kRight) {
    		right.set(value);
    	}
    }
}

