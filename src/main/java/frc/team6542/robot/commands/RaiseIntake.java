package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.subsystems.Intake;

public class RaiseIntake extends Command {

    public RaiseIntake() {
        requires(Intake.getInstance());
        setInterruptible(false);
    }

    protected void execute() {
        Intake.getInstance().raise();
    }
    @Override
    protected boolean isFinished() {
        return !MyXbox.getInstance().getXButton();
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        end();
    }

    protected void end() {
        Intake.getInstance().stopLowering();
    }
}
