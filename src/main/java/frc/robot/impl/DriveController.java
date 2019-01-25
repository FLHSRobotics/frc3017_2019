package frc.robot.impl;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.interfaces.Controller;

public class DriveController implements Controller {

    //Spark Max CAN address
    public int frontLeftCAN    = 2;
    public int rearLeftCAN     = 3;
    public int frontRightCAN   = 0;
    public int rearRightCAN    = 1;

    private CANSparkMax  m_frontLeft;
    private CANSparkMax m_rearLeft;
    private CANSparkMax m_frontRight;
    private CANSparkMax m_rearRight;

    public SpeedControllerGroup m_leftMotor;
    public SpeedControllerGroup m_rightMotor;

    public DriveController(){
        m_frontLeft = new CANSparkMax(frontLeftCAN, MotorType.kBrushless);
        m_rearLeft = new CANSparkMax(rearLeftCAN, MotorType.kBrushless);
        m_leftMotor = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

        m_frontRight = new CANSparkMax(frontRightCAN, MotorType.kBrushless);
        m_rearRight = new CANSparkMax(rearRightCAN, MotorType.kBrushless);
        m_rightMotor = new SpeedControllerGroup(m_frontRight, m_rearRight);
    }
    

    @Override
    public void initController() {
        
    }

    @Override
    public void runController() {
        
    }


}