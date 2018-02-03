package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.subsystems.Drive;

import static edu.wpi.first.wpilibj.PIDSourceType.kDisplacement;

public class RotateToTheta extends PIDCommand {
    private double theta;
    private MyGyro gyro = MyGyro.getInstance();
    private PIDController cont;

    public RotateToTheta(double theta) {
        super(SmartDashboard.getNumber("P", 0), SmartDashboard.getNumber("I", 0),
                SmartDashboard.getNumber("D", 0));
        this.theta = theta;
    }

    @Override
    protected void initialize() {
        super.initialize();
        requires(Drive.getInstance());
        cont = getPIDController();
        cont.setPercentTolerance(10);
        gyro.setPIDSourceType(kDisplacement);
        gyro.calibrate();
        setSetpoint(gyro.pidGet() + theta);
    }
    @Override
    protected double returnPIDInput() {
        return gyro.pidGet();
    }

    @Override
    protected void usePIDOutput(double output) {
        Drive.getInstance().steer(1, output);
    }

    @Override
    protected boolean isFinished() {
        return cont.onTarget();
    }
}
