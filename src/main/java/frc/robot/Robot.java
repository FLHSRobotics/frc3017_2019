package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.impl.*;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private Joystick joystick;

  private DifferentialDrive m_robot;

  private SolenoidControllerImpl solenoidController;
  private DriveControllerImpl driveController;
  private GyroSensorImpl gyroSensor;

  private VictorSP rightLiftMotor, leftLiftMotor;
  private Double liftPower;

  /**
   * Robot Init method
   */
  @Override
  public void robotInit() {
    driveController = new DriveControllerImpl(2, 3, 0, 1);

    driveController.initController();
    solenoidController.initController();
    gyroSensor.initSensor();

    // if the robot doesn't drive straight, invert one of the side
    driveController.m_leftMotor.setInverted(true);
    m_robot = new DifferentialDrive(driveController.m_leftMotor, driveController.m_rightMotor);

    joystick = new Joystick(0);

    // Lift Motor Control
    leftLiftMotor = new VictorSP(0);
    rightLiftMotor = new VictorSP(1);
    // invert one of the lift motor's direction
    rightLiftMotor.setInverted(true);
  }

  /**
   * Autonomous Period Init
   */
  @Override
  public void autonomousInit() {
    super.autonomousInit();
  }

  /**
   * Autonomous Period Loop
   */
  @Override
  public void autonomousPeriodic() {
    super.autonomousPeriodic();
  }

  /**
   * Tele Op Period Init
   */
  @Override
  public void teleopInit() {
    this.liftPower = 0.45;
    //updateDashboard();
  }

  /**
   * TeleOp Period Loop
   */
  @Override
  public void teleopPeriodic() {
    m_robot.arcadeDrive(joystick.getY(), joystick.getX());

    /**
     * Lift control
     */
    if (joystick.getPOV() == 0) {
      leftLiftMotor.set(liftPower);
      rightLiftMotor.set(liftPower);
    } else if (joystick.getPOV() == 180) {
      leftLiftMotor.set(-liftPower);
      rightLiftMotor.set(-liftPower);
    } else {
      leftLiftMotor.set(0);
      rightLiftMotor.set(0);
    }

  }

}
