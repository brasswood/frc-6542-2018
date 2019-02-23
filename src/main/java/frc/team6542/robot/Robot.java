/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6542.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.commands.*;
import frc.team6542.robot.hid.MyXbox;
import frc.team6542.robot.subsystems.Drive;
import frc.team6542.robot.subsystems.Elevator;
import frc.team6542.robot.subsystems.Intake;
import frc.team6542.robot.hid.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	private static OI m_oi;
	private Command testCommand = new RotateToTheta();
	public static NetworkTable table = NetworkTableInstance.getDefault().getTable("SmartDashboard");
	public static final String k_expelSpeed = "Expel Speed";
	public static final String k_intakeSpeed = "Intake Speed";
	public static final String k_autonForwardSpeed = "Auton Forward Speed";
	public static final String k_autonForwardTime = "Auton Forward Time";
	public static final String k_autonTurnSpeed = "Auton Turn Speed";
	public static final String k_autonTurnTime = "Auton Turn Time";
	public static final String k_autonTurnTheta = "Auton Turn Theta";
	public static final double expelSpeedDefault = -1;
	public static final double intakeSpeedDefault = 1;
	public static final double autonForwardSpeedDefault = 0.5;
	public static final double autonForwardTimeDefault = 3;
	public static final double autonTurnSpeedDefault = 0.5;
	public static final double autonTurnTimeDefault = 2;
	public static final double autonTurnThetaDefault = 45;
	public static MyHID controller;

	private Command m_autonomousCommand;
    private SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		MyGyro.getInstance().calibrate();

		//SmartDashboard put systems data
        SmartDashboard.putData(Drive.getInstance());
        SmartDashboard.putData(Elevator.getInstance());
        SmartDashboard.putData(Intake.getInstance());
        SmartDashboard.putData(Scheduler.getInstance());


		// Add SmartDashboard tweaking values

		// Experimental speeds
		// SmartDashboard.putNumber(k_expelSpeed, expelSpeedDefault);
		// SmartDashboard.putNumber(k_intakeSpeed, intakeSpeedDefault);
		SmartDashboard.putNumber(k_autonForwardSpeed, autonForwardSpeedDefault);
		SmartDashboard.putNumber(k_autonForwardTime, autonForwardTimeDefault);
		SmartDashboard.putNumber(k_autonTurnSpeed, autonTurnSpeedDefault);
		SmartDashboard.putNumber(k_autonTurnTime, autonTurnTimeDefault);
		SmartDashboard.putNumber(k_autonTurnTheta, autonTurnThetaDefault);

		// PID for RotateToTheta
		SmartDashboard.putNumber("P", 0);
		SmartDashboard.putNumber("I", 0);
		// SmartDashboard.putNumber("D", 0);

		// SmartDashboard chooser
		m_chooser.addDefault("AutonGoForward", new AutonGoForward());
		m_chooser.addObject("AutonGoLeft", new AutonGoLeft());
		m_chooser.addObject("AutonGoRight", new AutonGoRight());
		m_chooser.addObject("Test RotateToTheta", testCommand);
		SmartDashboard.putData("Auto mode", m_chooser);

		// Get the type of controller
		try {
			String name = DriverStation.getInstance().getJoystickName(0);
			if (name.equals("Xbox")) {
				controller = MyXbox.getInstance();
			} else if (name.equals("Wii Board")) {
				controller = WiiBoard.getInstance();
			} else if (name.equals("Wii Nunchuck")) {
				controller = WiiNunchuck.getInstance();
			} else {
				throw new NullControllerException();
			}
		} catch (NullControllerException e) {
			e.printStackTrace();
		}
    }

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
        Scheduler.getInstance().removeAll();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		MyGyro.getInstance().reset();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();

        if (m_autonomousCommand != null) {
		 	m_autonomousCommand.start();
        }
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
	    Scheduler.getInstance().run();

	}

	@Override
	public void teleopInit() {

        if (m_autonomousCommand != null){m_autonomousCommand.cancel();}
        if (DriverStation.getInstance().getJoystickName(0).equals("Xbox Controller")) {

		}
		m_oi.intake.whenPressed(new TakeBox());
		m_oi.expel.whileHeld(new ExpelBox());

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
