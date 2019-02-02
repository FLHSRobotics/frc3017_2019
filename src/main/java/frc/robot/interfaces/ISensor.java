package frc.robot.interfaces;

public abstract interface ISensor {
  public void initSensor();

  public double getReading();
}