package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team6542.robot.subsystems.Intake;

public class TakeBox extends Command {
    private Intake intake = Intake.getInstance();
    private boolean boxIsIn = false;
    private static final double kSpeed = 0.2;

    public TakeBox() {
        requires(intake);
    }

    protected void execute() {
        boxIsIn = intake.getSwitch();
        if (boxIsIn) {intake.set(0);} else {intake.set(kSpeed);}
    }

    protected void end() {
        intake.set(0);
    }

    protected void interrupted() {
        end();
    }

    protected boolean isFinished() {
        return boxIsIn;
    }

}
