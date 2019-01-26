package frc.robot.impl;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.interfaces.Controller;

/**
 * Solenoid Controller Class
 */
public class SolenoidControllerImpl implements Controller {

  private DoubleSolenoid solenoid1;
  private DoubleSolenoid solenoid2;
  private Compressor compressor;

  public SolenoidControllerImpl(){
    solenoid1 = new DoubleSolenoid(0, 1);
    compressor = new Compressor();
  }

  @Override
  public void initController() {
    
  }

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
   * Set Both Solenoid Forward
   */
  public void setSolenoidForward() {
    solenoid1.set(Value.kForward);
    //solenoid2.set(Value.kReverse);
  }

  /**
   * Set Both Solenoid Reverse
   */
  public void setSolenoidReverse() {
    solenoid1.set(Value.kReverse);
    //solenoid2.set(Value.kForward);
  }

  /**
   * Set Both Solenoid Off
   */
  public void setSolenoidOff() {
    solenoid1.set(Value.kOff);
    //solenoid2.set(Value.kOff);
  }

}