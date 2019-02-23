package frc.team6542.robot.hid;

public interface MyHID {
    
    boolean getTakeButton();
    boolean getExpelButton();
    double getElevator();
    int getTakeChannel();
    int getExpelChannel();

}
