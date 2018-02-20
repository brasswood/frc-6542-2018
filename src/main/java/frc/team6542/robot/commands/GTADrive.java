package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
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
	
	private static final double kP = 0.01d;
	private static final double kI = 0;
	private static final double kD = 0;
	private static final double setpointTolerance = 1; // Satisfied if within 1 degree
	private final Drive drive = Drive.getInstance();
	private final MyGyro gyro = MyGyro.getInstance();
	private PIDController cont;
	private final MyXbox xbox = MyXbox.getInstance();
	private double turn, speed;
	private double turn_prev = 0;
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
        cont = getPIDController();
        gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        turn = xbox.getX(Hand.kLeft);
        speed = xbox.getTriggerAxis(Hand.kRight) - xbox.getTriggerAxis(Hand.kLeft);
        SmartDashboard.putNumber("turn", turn);

        if (turn != 0) {

            if (cont.isEnabled()) {
                cont.disable();
            }

            drive.steer(turn, speed);

        } else {

            if (turn_prev != 0) { // If we just now got out of a turn

                gyroTimer.start();
                
            }

            if (gyroTimer.get() > 1) {

                gyroTimer.stop();
                gyroTimer.reset();
                gyro.reset();
                cont.enable();
                setSetpoint(0);
                cont.setAbsoluteTolerance(setpointTolerance);

            } else {

                drive.steer(turn, speed);

            }

        }

        turn_prev = turn;
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
