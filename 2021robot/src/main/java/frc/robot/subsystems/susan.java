// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class susan extends SubsystemBase {
  Solenoid m_solenoid;
  VictorSP susan;
  I2C.Port i2cPort;
  ColorSensorV3 m_colorSensor;
  ColorMatch m_colorMatcher;
  Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  Color kRedTarget = ColorMatch.makeColor(0.5,0.40,0.175);
  Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  int colorNow;
  int rotation;
  boolean colorChange;
  String colorString;
  ColorMatchResult match;

  /** Creates a new susan. */
  public susan() {
    m_solenoid = new Solenoid(Constants.s_susan);
    susan = new VictorSP(Constants.susan);
    i2cPort = I2C.Port.kOnboard;
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();

    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
    
    colorNow = 0;
    rotation = 0;
    colorChange = false;
    SmartDashboard.putBoolean("Change", colorChange);
    SmartDashboard.putNumber("Rotation", rotation);
  }

  public void arm(boolean button){
    m_solenoid.set(button);
  }

  public void position(boolean button,boolean buttonR,boolean redB, boolean yellowB, boolean greenB, boolean blueB, double speed){
    Color detectedColor = m_colorSensor.getColor();
    match = m_colorMatcher.matchClosestColor(detectedColor);
      
    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } 
    else if (match.color == kRedTarget) {
      colorString = "Red";
    } 
    else if (match.color == kGreenTarget) {
      colorString = "Green";
    } 
    else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } 
    else {
      colorString = "Unknown";
    }
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

    if(button){
      if(redB && colorString == "Red"){
        susan.set(0);
      }else if(yellowB && colorString == "Yellow" && match.confidence > 0.95){
        susan.set(0);
      }else if(greenB && colorString == "Green" && match.confidence > 0.92){
        susan.set(0);
      }else if(blueB && colorString == "Blue" && match.confidence > 0.90){
        susan.set(0);
      }else if(!greenB && !blueB && !redB && !yellowB){
        susan.set(0);
      }else{
        susan.set(speed);
      }
    }
    else if(buttonR){
      if(rotation>=8){
        susan.stopMotor();
      }
      else{
        susan.set(speed);
      }

      if(colorString == "Blue"){
        if(match.confidence>0.90){
          colorChange = true;
        }
      }

      if(colorChange==true){
        if(colorString!="Blue"){
          rotation++;
          colorChange = false;
        }
      }
    }
    else{
      susan.set(0);
    }
  }

  /*public void spin(boolean button,double speed){
    if(button){
      susan.set(speed);
    }
  }*/

  public void stop(){
    susan.set(0);
    m_solenoid.set(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}