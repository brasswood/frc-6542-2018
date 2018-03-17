package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.MyGyro;
import frc.team6542.robot.subsystems.Drive;

public class AutonDriveForward extends PIDCommand {

    private boolean finished = false;
    public Timer timer = new Timer();
    private double kSpeed;
    private double kTime;

    public AutonDriveForward(double speed, double time) {
        super(GTADrive.kP, GTADrive.kI, GTADrive.kD);
        requires(Drive.getInstance());
        this.kSpeed = speed;
        this.kTime = time;

    }
    @Override
    protected double returnPIDInput() {
        return MyGyro.getInstance().pidGet();
    }

    @Override
    protected void usePIDOutput(double output) {
        Drive drive = Drive.getInstance();
        SmartDashboard.putNumber("Gyro", MyGyro.getInstance().pidGet());
        SmartDashboard.putNumber("Compensate", MyGyro.getInstance().pidGet() - getPIDController().getSetpoint());
        if (!finished) {
            if (output > 0) {
                drive.setForwardSpeed(Drive.Side.kRight, kSpeed * (-1 - output));
                drive.setForwardSpeed(Drive.Side.kLeft, kSpeed * (-1 - output));
            } else {
                drive.setForwardSpeed(Drive.Side.kRight, kSpeed * (-1 + output));
                drive.setForwardSpeed(Drive.Side.kLeft, kSpeed * (-1 + output));
            }
            if (output < 0) {
                drive.setForwardSpeed(Drive.Side.kRight, kSpeed * (1 - output));
                drive.setForwardSpeed(Drive.Side.kLeft, kSpeed * (1 - output));
            } else {
                drive.setForwardSpeed(Drive.Side.kRight, kSpeed * (1 + output));
                drive.setForwardSpeed(Drive.Side.kLeft, kSpeed * (1 + output));
            }
        } else {drive.stopMotors();}
    }


    @Override
    protected void initialize() {
        timer.start();
        finished = false;
    }

    @Override
    protected void execute() {

        if (timer.get() > kTime) {
            finished = true;
            timer.stop();
            timer.reset();
        }

    }

    @Override
    protected void end() {
        timer.stop();
        timer.reset();
    }

    @Override
    protected void interrupted() {
        timer.stop();
        timer.reset();
    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

}
