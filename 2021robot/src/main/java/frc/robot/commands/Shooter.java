// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.shooter;
import edu.wpi.first.wpilibj.Joystick;

public class Shooter extends CommandBase {
  /** Creates a new shooter. */
  private final shooter m_shooter;
  private final Joystick m_stick;

  public Shooter(shooter subsystem,Joystick stick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem;
    m_stick = stick;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_shooter.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.aim(m_stick.getRawButton(Constants.shooter_aim),m_stick.getRawButton(Constants.shooter_shoot));
    m_shooter.shoot(m_stick.getRawButton(Constants.shooter_shoot2));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
