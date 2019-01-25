package frc.robot.impl;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.interfaces.Controller;

public class DriveController implements Controller {

    //Spark Max CAN address
    private Integer frontLeftCAN    = 2;
    private Integer rearLeftCAN     = 3;
    private Integer frontRightCAN   = 0;
    private Integer rearRightCAN    = 1;

    private CANSparkMax  m_frontLeft;
    private CANSparkMax m_rearLeft;
    private CANSparkMax m_frontRight;
    private CANSparkMax m_rearRight;

    public SpeedControllerGroup leftMotorGroup;
    public SpeedControllerGroup rightMotorGroup;

    @Override
    public void initController() {
        m_frontLeft = new CANSparkMax(frontLeftCAN, MotorType.kBrushless);
        m_rearLeft = new CANSparkMax(rearLeftCAN, MotorType.kBrushless);
        leftMotorGroup = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

        m_frontRight = new CANSparkMax(frontRightCAN, MotorType.kBrushless);
        m_rearRight = new CANSparkMax(rearRightCAN, MotorType.kBrushless);
        rightMotorGroup = new SpeedControllerGroup(m_frontRight, m_rearRight);
    }

    @Override
    public void runController() {
        
    }


}