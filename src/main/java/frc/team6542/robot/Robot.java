/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6542.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team6542.robot.commands.*;
import frc.team6542.robot.subsystems.Drive;
import frc.team6542.robot.subsystems.Elevator;
import frc.team6542.robot.subsystems.Intake;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static OI m_oi;
	// public static final ExampleSubsystem kExampleSubsystem
	//		= new ExampleSubsystem();
	// Command m_autonomousCommand;
    // SendableChooser<Command> m_chooser = new SendableChooser<>();
	private RotateToTheta m_testCommand;
	private SendableChooser<RotateToTheta> m_testToggle = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		// m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", m_chooser);
        SmartDashboard.putNumber("P", 0);
		SmartDashboard.putNumber("I", 0);
        SmartDashboard.putNumber("D", 0);
        SmartDashboard.putNumber("theta", 0);
        SmartDashboard.putData(Drive.getInstance());
        SmartDashboard.putData(Elevator.getInstance());
        SmartDashboard.putData(Intake.getInstance());
        MyGyro.getInstance().calibrate();
        // m_testToggle.addDefault("Don't Test", null);
        // m_testToggle.addObject("Do Test", new RotateToTheta());
        // SmartDashboard.putData("test", m_testToggle);
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
		// m_autonomousCommand = m_chooser.getSelected();
        Drive.getInstance().setDefaultCommand(null);
        Elevator.getInstance().setDefaultCommand(null);
        new MovePastLine().start();
        // m_testCommand = m_testToggle.getSelected();
		// m_testCommand = new RotateToTheta();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		/*
		 * if (m_autonomousCommand != null) {
		 * 	m_autonomousCommand.start();
		 * }
		 */

		/*if (m_testCommand != null) {
		    System.out.println("Start test");
		    m_testCommand.start();
        	}*/
		// m_testCommand.start();

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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		/*
		 *  if (m_autonomousCommand != null) {
		 *	m_autonomousCommand.cancel();
		 *  }
		 */
        if (m_testCommand != null){m_testCommand.cancel();}
        Drive.getInstance().setDefaultCommand(new GTADrive());
        Elevator.getInstance().setDefaultCommand(new MoveElevator());
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
