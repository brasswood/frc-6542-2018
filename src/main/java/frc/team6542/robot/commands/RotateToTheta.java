package frc.team6542.robot.commands;


import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.MyGyro;
import frc.team6542.robot.subsystems.Drive;

import static frc.team6542.robot.Robot.*;

import static edu.wpi.first.wpilibj.PIDSourceType.kDisplacement;

public class RotateToTheta extends PIDCommand {
    private double kP;
    private double kI;
    private double kD;
    private double theta;
    // private static final double kPercentTolerance = 10;
    private static final double kAbsoluteTolerance = 1;
    private MyGyro gyro = MyGyro.getInstance();
    private PIDController cont;

    public RotateToTheta(double theta) {
        super(0, 0, 0);
        requires(Drive.getInstance());
        this.theta = theta;
    }

    public RotateToTheta() {
        this(0);
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
        // cont.setPercentTolerance(kPercentTolerance);
	cont.setAbsoluteTolerance(kAbsoluteTolerance);
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
        theta = SmartDashboard.getNumber(k_autonTurnTheta, autonTurnThetaDefault);
        kP = SmartDashboard.getNumber("P", 0.2);
        kI = SmartDashboard.getNumber("I", 0.013);
        kD = SmartDashboard.getNumber("D", 0);
    }
}

