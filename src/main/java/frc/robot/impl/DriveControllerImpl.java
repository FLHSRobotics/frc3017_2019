package frc.robot.impl;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.interfaces.IController;

public class DriveControllerImpl implements IController {

  // Spark Max CAN address
  private int frontLeftCAN = 2;
  private int rearLeftCAN = 3;
  private int frontRightCAN = 0;
  private int rearRightCAN = 1;

  private CANSparkMax frontLeft;
  private CANSparkMax rearLeft;
  private CANSparkMax frontRight;
  private CANSparkMax rearRight;

  public SpeedControllerGroup m_leftMotor;
  public SpeedControllerGroup m_rightMotor;

  public DriveControllerImpl(int frontLeftCAN, int rearLeftCAN,
                             int frontRightCAN, int rearRightCAN) {
    this.frontLeftCAN = frontLeftCAN;
    this.rearLeftCAN = rearLeftCAN;
    this.frontRightCAN = frontLeftCAN;
    this.rearLeftCAN = rearLeftCAN;
  }

  @Override
  public void initController() {
    frontLeft = new CANSparkMax(frontLeftCAN, MotorType.kBrushless);
    rearLeft = new CANSparkMax(rearLeftCAN, MotorType.kBrushless);
    m_leftMotor = new SpeedControllerGroup(frontLeft, rearLeft);

    frontRight = new CANSparkMax(frontRightCAN, MotorType.kBrushless);
    rearRight = new CANSparkMax(rearRightCAN, MotorType.kBrushless);
    m_rightMotor = new SpeedControllerGroup(frontRight, rearRight);
  }

  @Override
  public void runController() {}
}