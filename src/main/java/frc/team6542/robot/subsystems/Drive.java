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
	private final int kForward = 1;
	
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
		kRight
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

    /**
     * Sets the motors of our Drive to the proper values to steer given the turn value and the linear speed.
     * @param turn    the turn "radius" (between -1 and 1) starting from the center of the circle and ending at the
     *                  center of the robot
     * @param speed   the linear speed which the outer side will travel
     */
    public void steer (double turn, double speed) {
		Side outer, inner;
		if (turn < 0 && turn >= -1) {
		    outer = Side.kRight;
		    inner = Side.kLeft;
		} else if (turn >= 0 && turn <= 1){
            outer = Side.kLeft;
            inner = Side.kRight;
        } else return;

		set(outer, speed * kForward);
		set(inner, speed * 2*(0.5d-Math.abs(turn)) * -kForward);

	}

}

