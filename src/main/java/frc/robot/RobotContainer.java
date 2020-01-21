/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.SPI;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Drivetrain stuff
  public static SpeedController topLeft;
  public static SpeedController bottomLeft;
  public static SpeedController topRight;
  public static SpeedController bottomRight;
  public static SpeedControllerGroup left;
  public static SpeedControllerGroup right;
  public static DifferentialDrive drive;
  public static AHRS ahrs;

  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain drivetrain = new Drivetrain();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static GenericHID LeftController;
  public static GenericHID RightController;
  //private static Button pidJoystickButton;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    configureButtonBindings();
    //controller
 LeftController=new Joystick (Constants.joyLeft);
RightController= new Joystick (Constants.joyRight);
//motor controllers
 topLeft= new Spark(Constants.topLeft);
bottomLeft= new Spark(Constants.bottomLeft);
topRight= new Spark(Constants.topRight);
bottomRight= new Spark(Constants.bottomRight);
left= new SpeedControllerGroup(topLeft, bottomLeft);
right = new SpeedControllerGroup (topRight,bottomRight);
drive= new DifferentialDrive(left, right);
//drive.setSafetyEnabled(false);
//drive.setExpiration(0.1);
//drive.setMaxOutput(1.0);
//drive.setRightSideInverted(true);

try {
  ahrs=new AHRS(SPI.Port.kMXP);
} catch (final Exception e) {
  
    DriverStation.reportError("Error instantiating AHRS", true);
}

//SmartDashboard
SmartDashboard.putBoolean("AHRS callibrating", ahrs.isCalibrating()); 
SmartDashboard.putData("drive", new DriveWithJoysticks(drivetrain, LeftController.getRawAxis(Constants.stickAxis), RightController.getRawAxis(Constants.stickAxis)));




    drivetrain.setDefaultCommand(new RunCommand(()->drive.tankDrive
    (LeftController.getRawAxis(Constants.stickAxis), 
    RightController.getRawAxis(Constants.stickAxis)), drivetrain));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() { 
    //pidJoystickButton= new JoystickButton(LeftController, Constants.pidButton)
      //  .toggleWhenPressed(new DriveStraight(ahrs.getYaw(), drivetrain));

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
