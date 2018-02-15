package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.subsystems.Elevator;

public class LowerElevator extends Command{

    private Elevator elevator = Elevator.getInstance();
    private static final double kSpeed = -0.7;

    public LowerElevator() {
        requires(elevator);
        setInterruptible(true);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void execute() {
        elevator.set(kSpeed);
    }

    protected void end() {
        elevator.set(0);
    }

    protected void interrupted() {
        end();
    }
}
