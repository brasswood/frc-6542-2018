package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import static frc.team6542.robot.RobotMap.*;

public class Intake extends Subsystem {

    private static Intake instance;
    private static final double kThreshold = 0;

    private AnalogInput stopSwitch = new AnalogInput(intakeSwitch);
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

    public boolean getSwitch() {
        return (stopSwitch.getVoltage() >= kThreshold);
    }
    public void initDefaultCommand() {

    }


}
