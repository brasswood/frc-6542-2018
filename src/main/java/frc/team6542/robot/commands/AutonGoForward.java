package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team6542.robot.Robot;
import frc.team6542.robot.subsystems.Drive;

public class AutonGoForward extends CommandGroup {

    // public static final double kSpeed = 0.5;
    // public static final double kTime = 2;
    private double kSpeed = Robot.autonForwardSpeed;
    private double kTime = Robot.autonForwardTime;

    public AutonGoForward() {
        requires(Drive.getInstance());
        addSequential(new AutonDriveForward(kSpeed, kTime));
    }

}
