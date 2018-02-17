package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.subsystems.Elevator;

import static edu.wpi.first.wpilibj.GenericHID.Hand.*;

public class MoveElevator extends Command{
    private MyXbox xbox = MyXbox.getInstance(0);

    public MoveElevator() {
        requires(Elevator.getInstance());
        setInterruptible(true);
    }

    protected boolean isFinished() {return false;}

    protected void execute() {
        Elevator.getInstance().set(xbox.getY(kLeft));
    }

    protected void end() {Elevator.getInstance().set(0);}

    protected void interrupted() {end();}
}
