/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class CrossAutoLine extends CommandBase {
  /**
   * Creates a new CrossAutoLine.
   */
private long time;
  public CrossAutoLine(long time,Drivetrain d) {
    time=this.time;
    // Use addRequirements() here to declare subsystem dependencies.
  addRequirements(d);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     time=System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.drive.tankDrive(Constants.autoLineSpeed, Constants.autoLineSpeed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drive.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return ((System.currentTimeMillis()-time)>Constants.autoLineTime);
  }
}
