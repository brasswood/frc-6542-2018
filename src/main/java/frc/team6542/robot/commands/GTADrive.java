package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.team6542.robot.*;
import frc.team6542.robot.subsystems.*;

/**
 * This is the command that runs by default to drive the robot. It will take input from the controller, and use the
 * gyroscope as a PID source to calculate the motor output needed to keep the robot straight. Then it will output to
 * the Drive subsystem.
 */
public class GTADrive extends PIDCommand {
	
	static final double p = 0;
	static final double i = 0;
	static final double d = 0;
	private final Drive drive = Drive.getInstance();
	private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	private PIDController cont;
	final MyXbox xbox = MyXbox.getInstance(0);
	double turn, speed;

    public GTADrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		super("GTADrive", p, i, d);
		gyro.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        cont = getPIDController();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        turn = xbox.getX(Hand.kLeft);
        speed = xbox.getTriggerAxis(Hand.kRight);
        if (Math.abs(turn) > 0.01d) {
            if (cont.isEnabled()) {
                cont.disable();
            }
            drive.steer(turn, speed);
        } else if (!cont.isEnabled()) {
            cont.enable();
        }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        cont.disable();
        cont.free();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive.set(Drive.Side.kLeft, 0);
    	drive.set(Drive.Side.kRight, 0);
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return gyro.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
       if (Math.abs(turn) < 0.01d) {
           if (output < 0) {
               drive.set(Drive.Side.kLeft, output * speed);
               drive.set(Drive.Side.kRight, speed);
           } else {
               drive.set(Drive.Side.kLeft, speed);
               drive.set(Drive.Side.kRight, output * speed);
           }
       }
	}

}
