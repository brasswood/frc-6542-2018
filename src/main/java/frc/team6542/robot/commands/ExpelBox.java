package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.subsystems.Intake;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class ExpelBox extends Command{
    private static double dir = -1;
    private Intake intake = Intake.getInstance();
    private MyXbox xbox = MyXbox.getInstance();

    public ExpelBox() {
        requires(intake);
        setInterruptible(true);
    }

    protected void execute() {
        intake.set(Math.hypot(xbox.getX(kRight), xbox.getY(kRight))* dir);
    }

    protected void end() {
        intake.set(0);
    }

    protected void interrupted() {
        end();
    }

    protected boolean isFinished() {
        return false;
    }
}
