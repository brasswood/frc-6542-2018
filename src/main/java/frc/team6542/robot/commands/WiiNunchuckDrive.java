package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.subsystems.Drive;
import frc.team6542.robot.hid.WiiNunchuck;

public class WiiNunchuckDrive extends Command{
    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        double turn = WiiNunchuck.getInstance().getX();
        double speed = Math.hypot(WiiNunchuck.getInstance().getX(), WiiNunchuck.getInstance().getY());
        SmartDashboard.putNumber("turn", turn);
        SmartDashboard.putNumber("speed", speed);
        Drive.getInstance().steer(turn, speed);
    }

    @Override
    protected void end() {

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
