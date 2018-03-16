package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.Robot;
import frc.team6542.robot.subsystems.Intake;

public class TakeBox extends Command {
    private Intake intake = Intake.getInstance();
    private boolean hold = false;
    // private boolean boxIsIn = false;
    // private static final double kTakeSpeed = 0.2;
    // private static final double kHoldSpeed = 0.2;

    public TakeBox() {
        requires(intake);
        setInterruptible(true);
    }

    protected void execute() {
        hold = !MyXbox.getInstance().getAButton();
        if (hold) {intake.set(SmartDashboard.getNumber("Hold Speed", -0.3));}
        else {intake.set(SmartDashboard.getNumber("Intake Speed", -0.7));}


    }

    protected void end() {
        System.out.println("Intake end");
        intake.set(0);
    }

    protected void interrupted() {
        System.out.println("Intake interrupt");
        end();
    }

    protected boolean isFinished() {
        return false;
    }

}
