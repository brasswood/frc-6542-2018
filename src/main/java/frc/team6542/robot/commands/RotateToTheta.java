package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.MyGyro;
import frc.team6542.robot.subsystems.Drive;

import static edu.wpi.first.wpilibj.PIDSourceType.kDisplacement;

public class RotateToTheta extends PIDCommand {
    private double kP = SmartDashboard.getNumber("P", 0);
    private double kI = SmartDashboard.getNumber("I", 0);
    private double kD = SmartDashboard.getNumber("D", 0);
    private double theta = SmartDashboard.getNumber("theta", 0);
    private static final double kPercentTolerance = 10;
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
        cont.setPID(kP, kI, kD);
        setSetpoint(theta);
        cont.setPercentTolerance(kPercentTolerance);

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

    @Override
    protected void end() {
        Drive.getInstance().stopMotors();
    }

    @Override
    protected void interrupted() {
    }

    /**
     * Grabs the kP, kI, kD, and theta values from the SmartDashboard and sets the instance variables.
     */
    private void getTestParameters() {
        theta = SmartDashboard.getNumber("theta", 0);
        kP = SmartDashboard.getNumber("P", 0);
        kI = SmartDashboard.getNumber("I", 0);
        kD = SmartDashboard.getNumber("D", 0);
    }

}
