/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.time.chrono.ThaiBuddhistEra;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveWithJoysticks;

public class Drivetrain extends SubsystemBase {
  public static SpeedController topLeft;
  public static SpeedController bottomLeft;
  public static SpeedController topRight;
  public static SpeedController bottomRight;
  public static SpeedControllerGroup left;
  public static SpeedControllerGroup right;
  public static DifferentialDrive drive;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
topLeft= new Spark(Constants.topLeft);
bottomLeft= new Spark(Constants.bottomLeft);
topRight= new Spark(Constants.topRight);
bottomRight= new Spark(Constants.bottomRight);
left= new SpeedControllerGroup(topLeft, bottomLeft);
right = new SpeedControllerGroup (topRight,bottomRight);
drive= new DifferentialDrive(left, right);
drive.setSafetyEnabled(true);
drive.setExpiration(0.1);
drive.setMaxOutput(1.0);
drive.setRightSideInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
