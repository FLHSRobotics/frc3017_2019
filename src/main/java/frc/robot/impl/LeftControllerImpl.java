package frc.robot.impl;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.interfaces.IController;

public class LeftControllerImpl implements IController{

    private ArrayList<VictorSP> liftMotors;
    private double power;

    @Override
    public void initController() {
        liftMotors = new ArrayList<VictorSP>(2);
    }

    @Override
    public void runController() { }

    public void addMotor(int pwmPin){
        VictorSP motor = new VictorSP(pwmPin);
        liftMotors.add(motor);
    }

    public void setPower(int motorIndex){
        liftMotors.get(motorIndex).set(this.power);
    }

    public void setInverted(int motorIndex, boolean inverted){
        liftMotors.get(motorIndex).setInverted(inverted);
    }

    public void setPower(double power){
        this.power = power;
    }

}