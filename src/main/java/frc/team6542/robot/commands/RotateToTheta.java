package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.MyGyro;
import frc.team6542.robot.subsystems.Drive;

import static edu.wpi.first.wpilibj.PIDSourceType.kDisplacement;

public class RotateToTheta extends PIDCommand {
    private double theta, p, i, d;
    private MyGyro gyro = MyGyro.getInstance();
    private PIDController cont;

    public RotateToTheta() {
        super(0, 0, 0);
        requires(Drive.getInstance());
    }

    @Override
    protected void initialize() {
        gyro.setPIDSourceType(kDisplacement);
        gyro.reset();
        // comment out when done testing
        getTestParameters();
        cont = getPIDController();
        cont.setPID(p, i, d);
        setSetpoint(theta);
        cont.setPercentTolerance(10);

    }
    @Override
    protected double returnPIDInput() {
        return gyro.pidGet();
    }

    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("Gyro", gyro.pidGet());
        SmartDashboard.putNumber("Setpoint", getSetpoint());
        SmartDashboard.putNumber("aP", p);
        Drive.getInstance().steer(1, output);
    }

    @Override
    protected boolean isFinished() {
        return cont.onTarget();
    }

    @Override
    protected void end() {
        Drive.getInstance().set(Drive.Side.kLeft, 0);
        Drive.getInstance().set(Drive.Side.kRight, 0);
    }

    @Override
    protected void interrupted() {
    }

    /**
     * Grabs the p, i, d, and theta values from the SmartDashboard and sets the instance variables.
     */
    private void getTestParameters() {
        theta = SmartDashboard.getNumber("theta", 0);
        p = SmartDashboard.getNumber("P", 0);
        i = SmartDashboard.getNumber("I", 0);
        d = SmartDashboard.getNumber("D", 0);
    }

}
