package frc.team6542.robot.hid;

public class WiiNunchuck extends WiiController{
    private static WiiNunchuck ourInstance = new WiiNunchuck();

    public static WiiNunchuck getInstance() {
        return ourInstance;
    }

    private WiiNunchuck() {
    }

    public double getX(Hand hand) {
        return getRawAxis(1);
    }

    public double getY(Hand hand) {
        return getRawAxis(2);
    }
}
