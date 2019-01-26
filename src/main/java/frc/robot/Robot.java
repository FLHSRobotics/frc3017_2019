package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

  private SolenoidControllerImpl solenoidController;
  private DriveController driveController;
  private GryoController gryoController;

  private DoubleSolenoid solenoid2;

  /**
   * Robot Init method
   */
  @Override
  public void robotInit() {
    driveController = new DriveController();
    gryoController = new GryoController();
    solenoidController = new SolenoidControllerImpl();
    driveController.initController();
    solenoidController.initController();
    gryoController.initController();

     solenoid2 = new DoubleSolenoid(2, 3);

    
    
    m_robot = new DifferentialDrive(driveController.m_leftMotor, driveController.m_rightMotor);
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
    solenoidController.runController();

    if (joystick.getRawButton(3)) {
      solenoidController.setSolenoidForward();
    } else if (joystick.getRawButton(4)) {
      solenoidController.setSolenoidReverse();
    } else if(joystick.getRawButton(5)){
      solenoid2.set(Value.kForward);
    } else if(joystick.getRawButton(6)){
      solenoid2.set(Value.kReverse);
    }else {
      solenoidController.setSolenoidOff();
      solenoid2.set(Value.kOff);
    }
  }


}
