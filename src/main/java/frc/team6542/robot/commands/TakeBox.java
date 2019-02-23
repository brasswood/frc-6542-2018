package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.subsystems.Intake;

import static frc.team6542.robot.Robot.*;

public class TakeBox extends Command {
    private Intake intake = Intake.getInstance();

    public TakeBox() {
        requires(intake);
        setInterruptible(true);
    }

    protected void execute() {
        intake.set(SmartDashboard.getNumber(k_intakeSpeed, intakeSpeedDefault));
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
