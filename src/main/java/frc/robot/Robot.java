package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.impl.*;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private Joystick joystick;

  private DifferentialDrive m_robot;
  private AnalogInput ultrasonic;


  private SolenoidController _solenoidController;
  private DriveController _driveController;
  private GryoController _gryoController;

  /**
   * Robot Init method
   */
  @Override
  public void robotInit() {
    _driveController = new DriveController();
    _gryoController = new GryoController();
    _solenoidController = new SolenoidController();
    _driveController.initController();
    _solenoidController.initController();
    _gryoController.initController();
    
    
    m_robot = new DifferentialDrive(_driveController.m_leftMotor, _driveController.m_rightMotor);
    ultrasonic = new AnalogInput(0);

    joystick = new Joystick(0);
    
    //CameraServer.getInstance().startAutomaticCapture();
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
    double ultrasonic_reading = (ultrasonic.getValue()/2) - 53;
    SmartDashboard.putString("DB/String 0", String.format("%.3f",ultrasonic_reading));
  }

  /**
   * Tele Op Period Init
   */
  @Override
  public void teleopInit() {
  }

  /**
   * TeleOp Period Loop
   */
  @Override
  public void teleopPeriodic() {
    m_robot.arcadeDrive(joystick.getY(), joystick.getX());
    _solenoidController.runController();

    if (joystick.getRawButton(3)) {
      _solenoidController.setSolenoidForward();
    } else if (joystick.getRawButton(4)) {
      _solenoidController.setSolenoidReverse();
    } else {
      _solenoidController.setSolenoidOff();
    }
  }


}
