package frc.team6542.robot;

import edu.wpi.first.wpilibj.XboxController;

public class MyXbox extends XboxController {

    private static MyXbox instance;
    private static final int port = 0;
    private static final double kStickDeadband = 0.2d;
    public boolean handleDeadband = true;

    private MyXbox() {
        super(port);
    }

    public static MyXbox getInstance() {
        if (instance == null) {instance = new MyXbox();}
        return instance;
    }

    public void setHandleDeadband(boolean handleDeadband) {
        this.handleDeadband = handleDeadband;
    }

    @Override
    public double getX(Hand hand) {
        double x = super.getX(hand);
        return handleDeadband ? (Math.abs(x) > kStickDeadband ? x : 0.0) : x;
    }

    @Override
    public double getY(Hand hand) {
        double y = super.getY(hand);
        return handleDeadband ? (Math.abs(y) > kStickDeadband ? y : 0.0) : y;
    }
}
