package frc.robot.impl;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.interfaces.Controller;

public class GryoController implements Controller {

    //point to SPI port
    private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
    public ADXRS450_Gyro m_gyro;

    public GryoController(){
        m_gyro = new ADXRS450_Gyro(kGyroPort);
        m_gyro.calibrate();
    }

    @Override
    public void initController() {
        
    }

    @Override
    public void runController() {

    }

}