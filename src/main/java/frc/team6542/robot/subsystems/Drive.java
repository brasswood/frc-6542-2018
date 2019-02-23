package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.Robot;
import frc.team6542.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import frc.team6542.robot.commands.GTADrive;
import frc.team6542.robot.commands.WiiBoardDrive;
import frc.team6542.robot.commands.WiiNunchuckDrive;

/**
 *
 */
public class Drive extends Subsystem{

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Spark left = new Spark(RobotMap.leftDrivePWM);
	private Spark right = new Spark(RobotMap.rightDrivePWM);
	private static Drive instance;
	private final double max_speed = 0.75;

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
		String name = DriverStation.getInstance().getJoystickName(0);
    	if (name.equals("Xbox")) {
    		setDefaultCommand(new GTADrive());
		} else if (name.equals("Wii Board")) {
    		setDefaultCommand(new WiiBoardDrive());
		} else if (name.equals("Wii Nunchuck")) {
    		setDefaultCommand(new WiiNunchuckDrive());
		}
    }
    /**
     * Sets the side of drive, and takes into account the fact that
     * you must spin each side in opposite directions to go forward.
     * It accomplishes this by setting the right side to the
     * negative of the forward speed.
     * @param s 			The side
     * @param forwardSpeed	The speed of the side going in the forward direction
     */
    public void setForwardSpeed(Side s, double forwardSpeed) {
    	if (s == Side.kLeft) {
    		set(Side.kLeft, forwardSpeed);
    	} else if (s == Side.kRight) {
    		set(Side.kRight, -forwardSpeed);
    	}
    }

    public void set (Side s, double value) {
    	double o = value < max_speed ? value : max_speed;
    	if (s == Side.kLeft) {
    		left.set(o);
    		Robot.table.getSubTable("Intake").getEntry("Left Side").setDouble(o);
    	} else if (s == Side.kRight) {
    		right.set(o);
    		Robot.table.getSubTable("Intake").getEntry("Right Side").setDouble(o);
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

		setForwardSpeed(outer, speed);
		setForwardSpeed(inner, speed * 2*(0.5d-Math.abs(turn)));
	}

	public void stopMotors () {
        set(Side.kLeft, 0);
        set(Side.kRight, 0);
    }
}

