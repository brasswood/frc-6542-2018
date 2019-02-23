package frc.team6542.robot.hid;

import edu.wpi.first.wpilibj.GenericHID;

abstract class WiiController extends GenericHID implements MyHID{

    private final int takeChannel = 1;
    private final int expelChannel = 2;
    private final int elevatorUpChannel = 3;
    private final int elevatorDownChannel = 4;

    public WiiController() {
        super(0);
    }

    @Override
    public boolean getTakeButton() {
        return getRawButton(takeChannel);
    }

    @Override
    public boolean getExpelButton() {
        return getRawButton(expelChannel);
    }

    @Override
    public double getElevator() {
        return (getRawButton(elevatorUpChannel) ? 1 : 0) + (getRawButton(elevatorDownChannel) ? -1 : 0);
    }

    @Override
    public int getTakeChannel() {
        return takeChannel;
    }

    @Override
    public int getExpelChannel() {
        return expelChannel;
    }
}
