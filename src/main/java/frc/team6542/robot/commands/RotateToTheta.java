package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class RotateToTheta extends PIDCommand {
    double theta;
    MyGyro gyro = MyGyro.getInstance();
    public RotateToTheta(double p, double i, double d, double theta) {
        super(p, i, d);
        this.theta = theta;
    }

    @Override
    protected void initialize() {
        super.initialize();
        setSetpoint(theta);
    }

    @Override
    protected void execute() {
        super.execute();
    }

    @Override
    protected double returnPIDInput() {
        return gyro.pidGet();
    }

    @Override
    protected void usePIDOutput(double output) {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
