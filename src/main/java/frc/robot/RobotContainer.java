/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*---------------------------------------------------------------*/

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
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CrossAutoLine;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.SetTop;
import frc.robot.commands.SetTopDown;
import frc.robot.commands.SetBottom;
import frc.robot.commands.SetBottomDown;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.MetalWireThing;
import frc.robot.subsystems.Spinner;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
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
  public static SpeedController intake1;
  public static SpeedController intake2;
  public static SpeedControllerGroup intake;
  public static SpeedController spinnerController;
  public static SpeedController liftController;
  public static SpeedController metalWireMotor1;
  public static SpeedController metalWireMotor2;
  public static SpeedControllerGroup wireMotors;
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drivetrain drivetrain = new Drivetrain();
  private final Intake intakeSubsytem= new Intake();
  private final Spinner spinner=new Spinner();
  private final Lift lift = new Lift();
  private final MetalWireThing m= new MetalWireThing();
/*private final Command autoCommand= new StartEndCommand(()->drive.tankDrive(Constants.autoLineSpeed,Constants.autoLineSpeed),
()->drive.tankDrive(0, 0));*/
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
private  CrossAutoLine auto;
  public static GenericHID LeftController;
  public static GenericHID RightController;
  public static XboxController Xbox;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    
    //controller
 LeftController=new Joystick (Constants.joyLeft);
RightController= new Joystick (Constants.joyRight);
Xbox=new XboxController(Constants.xbox);
//pidJoystickButton=new JoystickButton(LeftController, Constants.pidButton);
  configureButtonBindings();
//motor controllers
 topLeft= new Spark(Constants.topLeft);
bottomLeft= new Spark(Constants.bottomLeft);
topRight= new Spark(Constants.topRight);
bottomRight= new Spark(Constants.bottomRight);
spinnerController= new Spark(Constants.spinner);
liftController= new Spark(Constants.lift);
metalWireMotor1= new Spark(Constants.metalWire1);
metalWireMotor2=new Spark(Constants.MetalWire2);
wireMotors=new SpeedControllerGroup(metalWireMotor1, metalWireMotor2);
left= new SpeedControllerGroup(topLeft, bottomLeft);
right = new SpeedControllerGroup (topRight,bottomRight);
drive= new DifferentialDrive(left, right);

intake1 = new Spark(Constants.intake1);
intake2 = new Spark(Constants.intake2);
//intake = new SpeedControllerGroup(intake1, intake2);
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
SmartDashboard.putNumber("Yaw",ahrs.getYaw());



    drivetrain.setDefaultCommand(new RunCommand(()->drive.tankDrive
    (-LeftController.getRawAxis(Constants.stickAxis), 
    -RightController.getRawAxis(Constants.stickAxis)), drivetrain));
    spinner.setDefaultCommand(new RunCommand(()->spinnerController.set(Spinner.setSpeed(Xbox)),spinner));
    lift.setDefaultCommand(new RunCommand(()->liftController.set(Xbox.getRawAxis(Constants.xboxLeftJoy)),lift));
    m.setDefaultCommand(new RunCommand(()->wireMotors.set(Xbox.getRawAxis(Constants.xboxRightJoy)),m));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() { 
   // pidJoystickButton.toggleWhenPressed(new DriveStraight(ahrs.getYaw(),drivetrain));
    new JoystickButton(LeftController,Constants.pidButton)
    .toggleWhenPressed(new PIDCommand(new PIDController(
      Constants.kp,Constants.ki,Constants.kd)
    , ()->ahrs.getYaw(),
     ()->ahrs.getYaw(),  
     output -> drive.tankDrive(Drivetrain.calcAverageInput()+output, Drivetrain.calcAverageInput()-output),
       drivetrain));
    
 new  JoystickButton(Xbox,Constants.aButton).toggleWhenPressed(new SetTop());
 new  JoystickButton(Xbox,Constants.bButton).toggleWhenPressed(new SetBottom());
 new  JoystickButton(Xbox,Constants.xButton).toggleWhenPressed(new SetTopDown());
 new  JoystickButton(Xbox,Constants.yButton).toggleWhenPressed(new SetBottomDown());  
}
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    auto=new CrossAutoLine(System.currentTimeMillis(),drivetrain);
    return auto;
  }
}
