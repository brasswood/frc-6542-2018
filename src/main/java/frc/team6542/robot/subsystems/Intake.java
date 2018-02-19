package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import static frc.team6542.robot.RobotMap.*;

public class Intake extends Subsystem {

    private static Intake instance;
    private static final double kThreshold = 0;

    private Spark leftMotor = new Spark(leftIntake);
    private Spark rightMotor = new Spark(rightIntake);

    private Intake() {
        super();
    }

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }

    public void set(double leftValue, double rightValue) {
        leftMotor.set(leftValue);
        rightMotor.set(rightValue);
    }

    public void set(double value) {
        set(value, value);
    }

    public void initDefaultCommand() {

    }


}
