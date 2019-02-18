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

  private ShuffleboardTab tab;


  /**
   * Robot Init method
   */
  @Override
  public void robotInit() {
    driveController = new DriveControllerImpl(2, 3, 0, 1);
    gyroSensor = new GyroSensorImpl();
    solenoidController = new SolenoidControllerImpl();

    tab = Shuffleboard.getTab("Dashboard");

    driveController.initController();
    solenoidController.initController();
    gyroSensor.initSensor();

    m_robot = new DifferentialDrive(driveController.m_leftMotor, driveController.m_rightMotor);

    joystick = new Joystick(0);

    // Lift Motor Control
    leftLiftMotor = new VictorSP(0);
    rightLiftMotor = new VictorSP(1);

    rightLiftMotor.setInverted(true);

    // Double Solenoid
    solenoidController.addSolenoid(0, 1);
    solenoidController.addSolenoid(2, 3);
    // Signle Solenoid
    solenoidController.addSingleSolenoid(6);
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
    this.liftPower = 0.85;
    updateDashboard();
  }

  /**
   * TeleOp Period Loop
   */
  @Override
  public void teleopPeriodic() {
    m_robot.arcadeDrive(joystick.getY(), joystick.getX());
    solenoidController.runController();

    /**
     * Lift control
     */
    if (joystick.getPOV() == 0) {
      leftLiftMotor.set(-liftPower);
      rightLiftMotor.set(-liftPower);
    } else if (joystick.getPOV() == 180) {
      leftLiftMotor.set(-liftPower);
      rightLiftMotor.set(-liftPower);
    } else {
      leftLiftMotor.set(0);
      rightLiftMotor.set(0);
    }

    /**
     * Signle Solenoid Control Ball Suction block
     */
    if (joystick.getRawButton(9)) {
      solenoidController.setSolenoidForward(0);
      solenoidController.setSingleSolenoidOn(0);
    } else if (joystick.getRawButton(10)) {
      solenoidController.setSolenoidReverse(0);
      solenoidController.setSingleSolenoidOff(0);
    } else if (joystick.getRawButton(11)) {
      solenoidController.setSingleSolenoidOn(0);
    }

    /**
     * Double Solenoid Control
     */
    if (joystick.getRawButton(3)) {
      solenoidController.setSolenoidForward(0);
    } else if (joystick.getRawButton(4)) {
      solenoidController.setSolenoidReverse(0);
    } else if (joystick.getRawButton(5)) {
      solenoidController.setSolenoidForward(1);
    }
    if (joystick.getRawButton(6)) {
      solenoidController.setSolenoidReverse(1);
    }
  }

  private void updateDashboard() {
    ShuffleboardLayout solenoidLayout = tab.getLayout("Solenoid Status", BuiltInLayouts.kList).withSize(2, 2);
    ShuffleboardLayout lifLayout = tab.getLayout("Lift Status", BuiltInLayouts.kList).withSize(2, 2);
    ShuffleboardLayout driveLayout = tab.getLayout("Drive Status", BuiltInLayouts.kList).withSize(2, 3);
    // Solenoid Layout
    solenoidLayout.add("Ball Suction", solenoidController.getSingleSolenoid(0).get());
    solenoidLayout.add("Hatch Panel Piston",
        ((solenoidController.getSolenoid(0).get() == Value.kForward) ? "Retraced" : "Pushed"));

    //Lift Layout
    lifLayout.add("Lift Power",liftPower);
    //Drive Layout
    driveLayout.add("Joystick X value", joystick.getX());
    driveLayout.add("Joystick Y value", joystick.getY());
    driveLayout.add("Joystick Z value", joystick.getZ());
    //General
    tab.add("Compressor Status",solenoidController.getCompressor());
  }
}
