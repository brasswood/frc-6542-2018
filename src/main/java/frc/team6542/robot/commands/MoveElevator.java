package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.hid.MyXbox;
import frc.team6542.robot.subsystems.Elevator;

import static edu.wpi.first.wpilibj.GenericHID.Hand.*;

public class MoveElevator extends Command {

    private static final double kScale = 0.7;

    public MoveElevator() {
        requires(Elevator.getInstance());
        setInterruptible(true);
    }

    protected boolean isFinished() {return false;}

    protected void execute() {
        Elevator.getInstance().set(MyXbox.getInstance().getY(kRight) * kScale);
    }

    protected void end() {Elevator.getInstance().set(0);}

    protected void interrupted() {end();}
}
