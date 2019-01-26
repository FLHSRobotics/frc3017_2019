package frc.robot.impl;

import edu.wpi.first.wpilibj.AnalogInput;
import frc.robot.interfaces.ISensor;

public class UltrasonicSensorImpl implements ISensor {

  private AnalogInput ultrasonic;

  public UltrasonicSensorImpl() { ultrasonic = new AnalogInput(0); }

  @Override
  public double getReading() {
    double ultrasonic_reading = (ultrasonic.getValue() / 2) - 53;
    return ultrasonic_reading;
  }

  @Override
  public void initSensor() {}
}