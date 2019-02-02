package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
  private GyroSensorImpl gyroSensorImpl;

  private VictorSP liftMotor;

  /**
   * Robot Init method
   */
  @Override
  public void robotInit() {
    driveController = new DriveControllerImpl(2, 3, 0, 1);
    gyroSensorImpl = new GyroSensorImpl();
    solenoidController = new SolenoidControllerImpl();

    driveController.initController();
    solenoidController.initController();
    gyroSensorImpl.initSensor();

    m_robot = new DifferentialDrive(driveController.m_leftMotor,
                                    driveController.m_rightMotor);

    joystick = new Joystick(0);

    liftMotor = new VictorSP(0);

    solenoidController.addSolenoid(0, 1);
    solenoidController.addSolenoid(2, 3);
    solenoidController.addSingleSolenoid(4);
    solenoidController.addSingleSolenoid(5);
    solenoidController.addSingleSolenoid(6);
    solenoidController.addSingleSolenoid(7);
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
    super.teleopInit();
  }

  /**
   * TeleOp Period Loop
   */
  @Override
  public void teleopPeriodic() {
    m_robot.arcadeDrive(joystick.getY(), joystick.getX());
    solenoidController.runController();
    
    System.out.println(joystick.getPOV());
    if(joystick.getPOV() == 0){
      liftMotor.set(0.75);
    }else if(joystick.getPOV() == 180){
      liftMotor.set(-0.75);
    }else{
      liftMotor.set(0);
    }
    
    

    if (joystick.getRawButton(3)) {
      solenoidController.setSolenoidForward(0);
    } else if (joystick.getRawButton(4)) {
      solenoidController.setSolenoidReverse(0);
    } else if (joystick.getRawButton(5)) {
      solenoidController.setSolenoidForward(1);
    } else if (joystick.getRawButton(6)) {
      solenoidController.setSolenoidForward(1);
    } else if (joystick.getRawButton(9)){
      solenoidController.setSingleSolenoidOn(0);
    } else if(joystick.getRawButton(10)){
      solenoidController.setSingleSolenoidOn(1);
    } else if(joystick.getRawButton(11)){
      solenoidController.setSingleSolenoidOn(2);
    } else if(joystick.getRawButton(12)){
      solenoidController.setSingleSolenoidOn(3);
    }
    else {
      solenoidController.setAllSolenoidOff();
    }

  }
}
