/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import jdk.nashorn.internal.ir.BreakNode;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  VictorSP left_1 = new VictorSP(0);
  VictorSP left_2 = new VictorSP(1);
  VictorSP right_1 = new VictorSP(2);
  VictorSP right_2 = new VictorSP(3);
  SpeedControllerGroup left = new SpeedControllerGroup(left_1, left_2);
  SpeedControllerGroup right = new SpeedControllerGroup(right_1,right_2);  
  private final DifferentialDrive m_robotDrive
      = new DifferentialDrive(left, right);

  private final Joystick m_stick = new Joystick(0);
  private final Timer m_timer = new Timer();
  //Encoder enc; 
  WPI_TalonSRX m_arm;
  DigitalInput limitswitch_1;
  DigitalInput limitswitch_2;
   //private static final double cpr = 7/4; //if am-2861a
   ///!private static final double cpr = 360; //if am-3132
   // private static final double cpr = 5; //if am-3314a
   // private static final double cpr = 1024; //if am-3445
   // private static final double cpr = 64; //if am-4027
 
   //!private static final double whd = 6; // for 6 inch wheel
 

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    //enc = new Encoder(0,1);
    m_arm = new WPI_TalonSRX(0);
    limitswitch_1 = new DigitalInput(0);
    limitswitch_2 = new DigitalInput(1);
    //enc.setDistancePerPulse(Math.PI*whd/cpr); //distance per pulse is pi* (wheel diameter / counts per revolution)
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // Drive for 2 seconds
    
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    //chassis
    m_arm.setNeutralMode(NeutralMode.Coast);
    m_robotDrive.arcadeDrive(m_stick.getY()*0.4, m_stick.getX()*0.4);
    /**encoder
    *double dist = enc.getDistance();
    *SmartDashboard.putNumber("Encoder", dist);
    */
    //arm
    
    //limitswitch
    boolean ls_1 = limitswitch_1.get();
    boolean ls_2 = limitswitch_2.get();
    SmartDashboard.putBoolean("limitswitch_1", ls_1);
    SmartDashboard.putBoolean("limitswitch_2", ls_2);
    if(limitswitch_1.get() == false && m_stick.getRawButton(1))
    {
      m_arm.set(0.3);
    }
    else if(limitswitch_2.get() == false && m_stick.getRawButton(2))
    {
      m_arm.set(-0.3);
    }
    else if(limitswitch_1.get() == true || limitswitch_2.get() == true)
    {
      m_arm.stopMotor();
      m_arm.setNeutralMode(NeutralMode.Brake);
    }
    else
    {
      m_arm.set(0);
    } 
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
