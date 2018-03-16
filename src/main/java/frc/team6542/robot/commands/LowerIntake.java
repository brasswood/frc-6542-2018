package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.subsystems.Intake;

public class LowerIntake extends Command {

    public LowerIntake() {
        requires(Intake.getInstance());
        setInterruptible(false);
    }

    protected void execute() {
        Intake.getInstance().lower();
    }
    @Override
    protected boolean isFinished() {
        return !MyXbox.getInstance().getYButton();
    }

    @Override
    protected void interrupted() {
        end();
    }

    protected void end() {
        Intake.getInstance().stopLowering();
    }
}
