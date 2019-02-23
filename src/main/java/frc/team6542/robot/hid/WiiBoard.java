package frc.team6542.robot.hid;

public class WiiBoard extends WiiController {
    private static WiiBoard instance;

    private WiiBoard() {
    }

    public static WiiBoard getInstance() {
        if (instance == null) {
            instance = new WiiBoard();
        }
        return instance;
    }

    public double getX(Hand hand) {
        return getRawAxis(1);
    }

    public double getY(Hand hand) {
        return getRawAxis(2);
    }

}
