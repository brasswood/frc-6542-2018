package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.team6542.robot.Robot;
import frc.team6542.robot.subsystems.Drive;

public class AutonGoLeft extends CommandGroup{

    // private static final double kSpeed = 0.5;
    // private static final double kTime = 2;
    // private static final double kTheta = -45;
    private double kSpeed = Robot.autonTurnSpeed;
    private double kTime = Robot.autonTurnTime;
    private double kTheta = -Robot.autonTurnTheta;

    public AutonGoLeft() {
        requires(Drive.getInstance());
        addSequential(new RotateToTheta(kTheta));
        addSequential(new AutonDriveForward(kSpeed, kTime));
    }

}
