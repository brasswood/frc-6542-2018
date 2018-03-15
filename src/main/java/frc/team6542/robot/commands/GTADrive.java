package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import frc.team6542.robot.*;
import frc.team6542.robot.subsystems.*;

/**
 * This is the command that runs by default to drive the robot. It will take input from the controller, and use the
 * gyroscope as a PID source to calculate the motor output needed to keep the robot straight. Then it will output to
 * the Drive subsystem.
 */
public class GTADrive extends PIDCommand {
	
	protected static final double kP = 0.01d;
	protected static final double kI = 0;
	protected static final double kD = 0;
	protected static final double setpointTolerance = 1; // Satisfied if within 1 degree
    private static final double kFineness = 0.3;
    private boolean fineControls = false;
	private final Drive drive = Drive.getInstance();
	private final MyGyro gyro = MyGyro.getInstance();
	private final MyXbox xbox = MyXbox.getInstance();
	private double turn, speed;
	private double turn_prev = 0;
	private boolean usePID = true;
	private Timer gyroTimer = new Timer();

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
        gyro.reset();
        setSetpoint(0);
        getPIDController().setAbsoluteTolerance(setpointTolerance);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        turn = xbox.getX(Hand.kLeft);
        speed = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
        SmartDashboard.putNumber("turn", turn);

        if (speed == 0) {
            gyro.reset();
        } else if (turn != 0) {
            usePID = false; // We only need PID to drive straight.
        } else if (turn_prev != 0) { // If we just now got out of a turn
            usePID = false;
            gyroTimer.start();
        }

        if (gyroTimer.get() > 1) {
            gyroTimer.stop();
            gyroTimer.reset();
            gyro.reset();
            usePID = true;
        }

        turn_prev = turn;

        fineControls = (xbox.getStartButtonPressed() != fineControls);
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
       double s = fineControls ? (kFineness * speed) : speed;
       if (usePID) {
           if (output > 0 && s > 0) {
        	   drive.setForwardSpeed(Drive.Side.kRight, s*(-1 - output));
        	   drive.setForwardSpeed(Drive.Side.kLeft, s*(-1 - output));
           } else {
               drive.setForwardSpeed(Drive.Side.kRight, s*(-1 + output));
               drive.setForwardSpeed(Drive.Side.kLeft, s*(-1 + output));
           }
           if (output < 0 && s > 0) {
        		drive.setForwardSpeed(Drive.Side.kRight, s*(1 - output));
        		drive.setForwardSpeed(Drive.Side.kLeft, s*(1 - output));
           } else {
        		drive.setForwardSpeed(Drive.Side.kRight, s*(1 + output));
        		drive.setForwardSpeed(Drive.Side.kLeft, s*(1 + output));
           }
       } else {
           drive.steer(turn, s);
       }
	}

}
