// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.susan;

public class Susan extends CommandBase {
  private final susan m_susan;
  private final Joystick m_stick;

  double speed = 0.1; //to be confirmed - num
  
  /** Creates a new Susan. */
  public Susan(susan subsystem,Joystick stick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_susan = subsystem;
    m_stick = stick;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_susan.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_susan.arm(m_stick.getRawButton(Constants.susan_arm));
    m_susan.position(m_stick.getRawButton(Constants.susan_position),
                      m_stick.getRawButton(Constants.susan_rotate), 
                      m_stick.getRawButton(Constants.susan_red),
                      m_stick.getRawButton(Constants.susan_yellow),
                      m_stick.getRawButton(Constants.susan_green), 
                      m_stick.getRawButton(Constants.susan_blue),
                      speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_susan.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
