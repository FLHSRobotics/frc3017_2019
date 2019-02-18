package frc.robot.impl;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import frc.robot.interfaces.ISensor;

public class GyroSensorImpl implements ISensor {

  public ADXRS450_Gyro gyroscope;

  public GyroSensorImpl() { gyroscope = new ADXRS450_Gyro(); }

  @Override
  public void initSensor() {
    gyroscope.calibrate();
  }

  @Override
  public double getReading() {
    return gyroscope.getAngle();
  }

  public ADXRS450_Gyro getGryo(){
    return gyroscope;
  }
}