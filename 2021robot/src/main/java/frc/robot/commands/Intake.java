// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.intake;
import edu.wpi.first.wpilibj.Joystick;

public class Intake extends CommandBase {
  private final intake m_intake;
  private final Joystick m_stick;

  /** Creates a new intake. */
  public Intake(intake subsystem,Joystick stick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_intake = subsystem;
    m_stick = stick;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intake.arm(m_stick.getRawButton(Constants.intake_arm));
    m_intake.in(m_stick.getRawButton(Constants.intake_in));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
