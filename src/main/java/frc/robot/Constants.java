/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    //buttons and axis values on controller
   public static int aButton =1;
   public static int bButton =2;
   public static int xButton =3;
   public static int yButton =4;
   public static int leftButton=5;
   public static int rightButton=6;
   public static int stickAxis=1;
   public static int xboxLeftJoy=1;
   public static int xboxRightJoy=5;

   public static int pidButton=1;
   //public static int rightAxis=5;
// pwm values
public static int topRight=5;
public static int bottomRight=6;
public static int topLeft=3;
public static int bottomLeft=4;
public static int intake2=8;
public static int intake1 =7;
public static int spinner=2;
public static int lift=1;
public static int metalWire1=0;
public static int MetalWire2=9;
//joysticks
public static int xbox=0;
public static int joyLeft=1;
public static int joyRight=2;
public static int leftTrigger=2;
public static int rightTrigger=3;


//PID values
public static double kp=1.0;
public static double ki=0;
public static double kd=0;


//autonomous
public static double autoLineSpeed=-0.7;
public static double autoLineTime=2500.0;

}
