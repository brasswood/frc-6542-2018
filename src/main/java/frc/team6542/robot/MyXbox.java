package frc.team6542.robot;

import edu.wpi.first.wpilibj.XboxController;

public class MyXbox extends XboxController {

    private static MyXbox instance;

    private MyXbox(int port) {
        super(port);
    }

    public static MyXbox getInstance(int port) {
        if (instance == null) {instance = new MyXbox(port);}
        return instance;
    }
}
