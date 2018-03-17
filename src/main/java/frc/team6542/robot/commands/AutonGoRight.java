package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.subsystems.Drive;

import static frc.team6542.robot.Robot.*;

public class AutonGoRight extends CommandGroup{

    public AutonGoRight() {
        // requires(Drive.getInstance());
        addSequential(new RotateToTheta(SmartDashboard.getNumber(k_autonTurnTheta, autonTurnThetaDefault)));
        addSequential(new AutonDriveForward(SmartDashboard.getNumber(k_autonTurnSpeed, autonTurnSpeedDefault),
                SmartDashboard.getNumber(k_autonTurnTime, autonTurnTimeDefault)));
    }
}
