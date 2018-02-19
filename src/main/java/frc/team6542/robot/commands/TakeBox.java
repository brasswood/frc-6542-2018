package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.subsystems.Intake;

public class TakeBox extends Command {
    private Intake intake = Intake.getInstance();
    private boolean boxIsIn = false;
    private static final double dir = 1;
    private static final double kTakeSpeed = 0.2;
    private static final double kHoldSpeed = 0.2;

    public TakeBox() {
        requires(intake);
    }

    protected void execute() {
        if (boxIsIn) {intake.set(kHoldSpeed);} else {intake.set(kTakeSpeed);}
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
