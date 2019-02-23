package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.subsystems.Drive;
import frc.team6542.robot.hid.WiiBoard;

public class WiiBoardDrive extends Command{
    public WiiBoardDrive() {
        requires(Drive.getInstance());
        setInterruptible(true);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double turn = WiiBoard.getInstance().getX();
        double speed = Math.hypot(turn, WiiBoard.getInstance().getY());
        SmartDashboard.putNumber("turn", turn);
        SmartDashboard.putNumber("speed", speed);
        Drive.getInstance().steer(turn, speed);
    }

    @Override
    protected void end() {
        Drive.getInstance().stopMotors();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
