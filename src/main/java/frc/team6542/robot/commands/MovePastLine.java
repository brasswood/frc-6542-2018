package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.team6542.robot.MyGyro;
import frc.team6542.robot.subsystems.Drive;

public class MovePastLine extends PIDCommand {

    private boolean finished = false;
    private Timer timer = new Timer();

    @Override
    protected double returnPIDInput() {
        return MyGyro.getInstance().pidGet();
    }

    @Override
    protected void usePIDOutput(double output) {
        Drive drive = Drive.getInstance();
        final double kSpeed = 0.5;
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


    public MovePastLine() {
        super(GTADrive.kP, GTADrive.kI, GTADrive.kD);
        requires(Drive.getInstance());
    }


    @Override
    protected void initialize() {

        timer.start();

    }

    @Override
    protected void execute() {

        final double kTime = 2;
        if (timer.get() > kTime) {
            finished = true;
            timer.stop();
        }

    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected boolean isFinished() {
        return finished;
    }

}
