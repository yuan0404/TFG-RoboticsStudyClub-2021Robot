// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class shooter extends SubsystemBase {
  WPI_TalonSRX chainplate;
  PWMVictorSPX shooter;
  DoubleSolenoid m_doublesolenoid;
  NetworkTable table;

  double Kp_aim = 0.03;//to be confirmed - num
  double Kp_distance = -0.5;//to be confirmed - num
  double desired_area = 10;//to be confirmed - num
  double desired_range = 10;//to be confirmed - num
  double max_speed = 0.2;//to be confirmed - num
  double Kp_speed = 0.1;//to be confirmed - num
  double speed2 = 1;//to be confirmed - num

  double x;
  double y;
  double area;
  boolean value;
  double turn;
  double range;
  double speed;

  /** Creates a new shooter. */
  public shooter() {
    chainplate = new WPI_TalonSRX(Constants.chainplate_shooter);
    shooter = new PWMVictorSPX(Constants.shooter);
    m_doublesolenoid = new DoubleSolenoid(Constants.ds_shooter_l,Constants.ds_shooter_r);
    table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public void aim(boolean button,boolean button2){
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    boolean value = tv.getBoolean(false);//to be confirmed - double/boolean & true/false
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putBoolean("LimelightValue", value);
    double turn = Kp_aim * x;
    double range = Kp_distance * (desired_area-area);
    double speed = Kp_speed * range;
    if(button && value){
      //pneumatic
      if(range > desired_range){ //to be confirmed - range/y & num
        m_doublesolenoid.set(DoubleSolenoid.Value.kReverse);
      }
      else if (range <= desired_range){
        m_doublesolenoid.set(DoubleSolenoid.Value.kForward);
      }

      //chainplate
      if(x > 0){
        if(turn < max_speed){
          chainplate.set(turn);
        }
        else{
          chainplate.set(max_speed);
        }
      }
      else if (x < 0){
        if(turn > -max_speed){
          chainplate.set(turn);
        }
        else{
          chainplate.set(-max_speed);
        }
      }
      else{
        chainplate.set(0);
      }
    }
    //if (! value)
    SmartDashboard.putNumber("LimelightTurn", turn);
    SmartDashboard.putNumber("LimelightRange", range);

    if(button2){
      shooter.set(speed);
    }
    SmartDashboard.putNumber("LimelightSpeed", speed);
  }

  public void shoot(boolean button){
    if(button){
      shooter.set(speed2);
    }
    SmartDashboard.putNumber("LimelightSpeed2", speed2);
  }
  
  public void stop(){
    shooter.set(0);
    chainplate.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
