package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.subsystems.Drive;

import static frc.team6542.robot.Robot.*;

public class AutonGoForward extends CommandGroup {

    public AutonGoForward() {
        requires(Drive.getInstance());
        addSequential(new AutonDriveForward(SmartDashboard.getNumber(k_autonForwardSpeed, autonForwardSpeedDefault),
                SmartDashboard.getNumber(k_autonForwardTime, autonForwardTimeDefault)));
    }

}
