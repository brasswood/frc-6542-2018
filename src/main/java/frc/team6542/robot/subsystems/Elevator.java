package frc.team6542.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.Robot;
import frc.team6542.robot.commands.MoveElevator;

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
        setDefaultCommand(new MoveElevator());
    }

    public void set(double speed) {
        Robot.table.getSubTable("Elevator").getEntry("Motor").setDouble(speed);
        elevator.set(speed);

    }
}
