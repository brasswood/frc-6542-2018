package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.subsystems.Intake;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class ExpelBox extends Command{
    private Intake intake = Intake.getInstance();
    private MyXbox xbox = MyXbox.getInstance(0);

    public ExpelBox() {
        requires(intake);
        setInterruptible(true);
    }

    protected void execute() {
        intake.set(Math.hypot(xbox.getX(kRight), xbox.getY(kRight)));
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
