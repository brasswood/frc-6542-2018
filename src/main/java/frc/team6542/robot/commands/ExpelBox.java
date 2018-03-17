package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.subsystems.Intake;

import static frc.team6542.robot.Robot.*;

public class ExpelBox extends Command{
    private Intake intake = Intake.getInstance();

    public ExpelBox() {
        requires(intake);
        setInterruptible(false);
    }

    protected void execute() {
        intake.set(SmartDashboard.getNumber(k_expelSpeed, expelSpeedDefault));
    }

    protected void end() {
        System.out.println("Expel end");
        intake.set(0);
    }

    protected void interrupted() {
        System.out.println("Expel interrupt");
        end();
    }

    protected boolean isFinished() {
        return !MyXbox.getInstance().getAButton();
    }
}
