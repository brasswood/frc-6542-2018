package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.Robot;

import static frc.team6542.robot.RobotMap.*;

public class Intake extends Subsystem {

    private static Intake instance;

    private Spark motor = new Spark(intake);

    private Intake() {
        super();
    }

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }

    public void set(double value) {
        System.out.println(value);
        motor.set(value);
        Robot.table.getSubTable("Intake").getEntry("Motor").setDouble(value);
    }

    public void initDefaultCommand() {

    }



}
