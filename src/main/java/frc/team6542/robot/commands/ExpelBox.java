package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.MyXbox;
import frc.team6542.robot.Robot;
import frc.team6542.robot.subsystems.Intake;

import static edu.wpi.first.wpilibj.GenericHID.Hand.kRight;

public class ExpelBox extends Command{
    private static double dir = -1;
    // private static final double kSpeed;
    private double speed = SmartDashboard.getNumber("Expel Speed", 1);
    private Intake intake = Intake.getInstance();

    public ExpelBox() {
        requires(intake);
        setInterruptible(false);
    }

    protected void execute() {
        intake.set(speed);
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
        return !MyXbox.getInstance().getBButton();
    }
}
