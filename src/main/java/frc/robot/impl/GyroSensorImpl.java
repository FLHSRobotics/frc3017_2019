package frc.robot.impl;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.interfaces.ISensor;

public class GyroSensorImpl implements ISensor {

  // point to SPI port
  private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
  public ADXRS450_Gyro gyroscope;

  public GyroSensorImpl() { gyroscope = new ADXRS450_Gyro(kGyroPort); }

  @Override
  public void initSensor() {
    gyroscope.calibrate();
  }

  @Override
  public double getReading() {
    return gyroscope.getAngle();
  }
}