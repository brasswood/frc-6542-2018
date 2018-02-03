package frc.team6542.robot.commands;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class MyGyro extends ADXRS450_Gyro {
    private static MyGyro ourInstance = new MyGyro();

    public static MyGyro getInstance() {
        return ourInstance;
    }

    private MyGyro() {
        super();
    }
}
