package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import static frc.team6542.robot.RobotMap.*;

public class Elevator extends Subsystem{
    private static Elevator ourInstance = new Elevator();
    private Spark left = new Spark(leftElevator);
    private Spark right = new Spark(rightElevator);

    public static Elevator getInstance() {
        return ourInstance;
    }

    private Elevator() {
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void set(double speed) {
        left.set(speed);
        right.set(speed);
    }
}
