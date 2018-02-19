package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

import static frc.team6542.robot.RobotMap.*;

public class Elevator extends Subsystem{
    private static Elevator ourInstance = new Elevator();
    private Spark elevator = new Spark(elevatorPWM);

    public static Elevator getInstance() {
        return ourInstance;
    }

    private Elevator() {
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void set(double speed) {
        elevator.set(speed);

    }
}
