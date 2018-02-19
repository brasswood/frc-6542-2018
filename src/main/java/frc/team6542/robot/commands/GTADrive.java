package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.team6542.robot.*;
import frc.team6542.robot.subsystems.*;

/**
 * This is the command that runs by default to drive the robot. It will take input from the controller, and use the
 * gyroscope as a PID source to calculate the motor output needed to keep the robot straight. Then it will output to
 * the Drive subsystem.
 */
public class GTADrive extends PIDCommand {
	
	private static final double kP = 0.01d;
	private static final double kI = 0;
	private static final double kD = 0;
	private static final double setpointTolerance = 1; // Satisfied if within 1 degree
	private final Drive drive = Drive.getInstance();
	private final MyGyro gyro = MyGyro.getInstance();
	private PIDController cont;
	private final MyXbox xbox = MyXbox.getInstance();
	private double turn, speed;

    public GTADrive() {
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		super("GTADrive", kP, kI, kD);
		requires(drive);
		setInterruptible(true);
		gyro.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        cont = getPIDController();
        gyro.calibrate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        turn = xbox.getX(Hand.kLeft);
        speed = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
        SmartDashboard.putNumber("x", turn);

        if (turn != 0) {

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
        drive.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive.stopMotors();
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
       if (turn == 0) {
           if (output > 0 && speed > 0) {
        	   drive.setForwardSpeed(Drive.Side.kRight, speed*(1 - output));
        	   drive.setForwardSpeed(Drive.Side.kLeft, speed);
           } else {
               drive.setForwardSpeed(Drive.Side.kRight, speed);
               drive.setForwardSpeed(Drive.Side.kLeft, speed*(1 + output));
           }
       }
	}

}
