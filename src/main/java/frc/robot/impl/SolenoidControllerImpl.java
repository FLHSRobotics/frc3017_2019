package frc.robot.impl;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.interfaces.IController;

/**
 * Solenoid Controller Class
 */
public class SolenoidControllerImpl implements IController {

  private ArrayList<DoubleSolenoid> solenoids;
  private Compressor compressor;

  public SolenoidControllerImpl() {
    compressor = new Compressor();
    solenoids = new ArrayList<DoubleSolenoid>(6);
  }

  @Override
  public void initController() {}

  /**
   * Main entry point of the Solenoid Controller class
   *
   * @param joystick Joystick Object
   */
  @Override
  public void runController() {
    compressor.setClosedLoopControl(true);

    if (compressor.getPressureSwitchValue()) {
      compressor.stop();
    } else {
      compressor.start();
    }
  }

  /**
   * Add a double solenoid
   *
   * @param forwardPort  forwardChannel
   * @param backwardPort backwardChannel
   */
  public void addSolenoid(int forwardPort, int backwardPort) {
    DoubleSolenoid solenoid = new DoubleSolenoid(forwardPort, backwardPort);
    solenoids.add(solenoid);
  }

  /**
   * Set Both Solenoid Forward
   */
  public void setSolenoidForward(int groupIndex) {
    solenoids.get(groupIndex).set(Value.kForward);
  }

  /**
   * Set Both Solenoid Reverse
   */
  public void setSolenoidReverse(int groupIndex) {
    solenoids.get(groupIndex).set(Value.kReverse);
  }

  /**
   * Set Both Solenoid Off
   */
  public void setSolenoidOff(int groupIndex) {
    solenoids.get(groupIndex).set(Value.kOff);
  }

  /**
   * Turn off all solenoid
   */
  public void setAllSolenoidOff() {
    for (int i = 0; i < solenoids.size(); i++)
      solenoids.get(i).set(Value.kOff);
  }
}