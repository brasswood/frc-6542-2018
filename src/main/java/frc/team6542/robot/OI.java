/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team6542.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team6542.robot.hid.MyXbox;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

    Button intake = new JoystickButton((GenericHID) Robot.controller, Robot.controller.getExpelChannel());
    Button expel = new JoystickButton(MyXbox.getInstance(), 1);



    /*
    Jakob:

    The second argument of JoystickButton is the raw channel of the input. You will need to figure this number
    out. The driver station shows you the input it receives and on what channel when you press a button.
    Replace 0 and 1 with the numbers you find for the buttons you want to use to move the elevatorPWM up and down.

    If you want to change the speed of the elevatorPWM, change the kSpeed constant in RaiseElevator.java and
    LowerElevator.java.

    The PWM ports for the two sides of the elevatorPWM are defined in RobotMap.java, under elevatorPWM and
    rightElevator. Change them as needed.

    Ditto all of the above for the intake system. Another warning on the intake system, it will keep spinning
    towards the robot until it gets some analog voltage that exceeds kThreshold in Intake.java. Spinning the
    motors away will just spin them while the button is held down.

    -Andrew <3
     */
}
