package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.team6542.robot.subsystems.Drive;

/**
 *
 */
public class GTADrive extends PIDCommand {
	
	static final double p = 0;
	static final double i = 0;
	static final double d = 0;
	final Drive drive = Drive.getInstance();
	private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    public GTADrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		super("GTADrive", p, i, d);
		gyro.setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType.kDisplacement);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
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
	}
}
