package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.*;
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
	
	private static final double p = 0.01d;
	private static final double i = 0;
	private static final double d = 0;
	private static final double setpointTolerance = 1; // Satisfied if within 1 degree
	private static final double steeringTolerance = 0.2d; // Controller doesn't always reset to 0
	private final Drive drive = Drive.getInstance();
	private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	private PIDController cont;
	private final MyXbox xbox = MyXbox.getInstance(0);
	private double turn, speed;

    public GTADrive() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		super("GTADrive", p, i, d);
		requires(drive);
		gyro.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        cont = getPIDController();
        gyro.calibrate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Gyro 2", gyro.pidGet());
    	System.out.print("This command is working");
        turn = xbox.getX(Hand.kLeft);
        speed = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
        SmartDashboard.putNumber("x", turn);
        if (Math.abs(turn) > steeringTolerance) {
            if (cont.isEnabled()) {
                cont.disable();
            }
            drive.steer(turn, speed);
        } else if (!cont.isEnabled()) {
            cont.enable();
            gyro.reset();
            setSetpoint(0);
            cont.setAbsoluteTolerance(setpointTolerance);
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
       SmartDashboard.putNumber("Gyro", gyro.pidGet());
       SmartDashboard.putNumber("PIDOutput", output);
       if (Math.abs(turn) < steeringTolerance) {
           if (output > 0 && speed > 0) {
        	   drive.setForwardSpeed(Drive.Side.kRight, speed*(-1 - output));
        	   drive.setForwardSpeed(Drive.Side.kLeft, speed*(-1 - output));
           } else {
               drive.setForwardSpeed(Drive.Side.kRight, speed*(-1 + output));
               drive.setForwardSpeed(Drive.Side.kLeft, speed*(-1 + output));
           }
           if (output < 0 && speed > 0) {
        		drive.setForwardSpeed(Drive.Side.kRight, speed*(1 - output));
        		drive.setForwardSpeed(Drive.Side.kLeft, speed*(1 - output));
           } else {
        		drive.setForwardSpeed(Drive.Side.kRight, speed*(1 + output));
        		drive.setForwardSpeed(Drive.Side.kLeft, speed*(1 + output));
        	   }
       }
	}
}
