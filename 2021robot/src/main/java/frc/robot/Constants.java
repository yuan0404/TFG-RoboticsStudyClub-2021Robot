// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    //MotorControllor Port
    public final static int chainplate_shooter = 0;
    public final static int shooter = 1;
    public final static int intake = 2;
    public final static int susan = 3;
    
    //Sensor Channel

    //Pneumatics Port
    public final static int ds_shooter_l = 0;
    public final static int ds_shooter_r = 1;
    public final static int ds_intake_l = 2;
    public final static int ds_intake_r = 3;
    public final static int s_susan = 4;

    //Joystick Number
    public final static int joystick_Drive = 0;
    public final static int joystick_Control = 1;

    //Joystick Button
    public final static int axis_l_x = 0;
    public final static int axis_l_y = 1;
    public final static int axis_r_x = 4;
    public final static int axis_r_y = 5;
    public final static int trigger_l = 2;
    public final static int trigger_r = 3;
    public final static int button_A = 1;
    public final static int button_B = 2;
    public final static int button_X = 3;
    public final static int button_Y = 4;
    public final static int button_LB = 5;
    public final static int button_RB = 6;
    public final static int button_Axis_l = 9;
    public final static int button_Axis_r = 10;
    public final static int POV_up = 0;
    public final static int POV_upRight = 45;
    public final static int POV_Right = 90;
    public final static int POV_downRight = 135;
    public final static int POV_down = 180;
    public final static int POV_downleft = 225;
    public final static int POV_left = 270;
    public final static int POV_upLeft = 315;

    public final static int shooter_aim = 0;
    public final static int shooter_shoot = 1;
    public final static int shooter_shoot2 = 2;
    public final static int intake_arm = 3;
    public final static int intake_in = 5;
    public final static int susan_arm = 7;
    public final static int susan_position = 8;
    public final static int susan_rotate = 9;
    public final static int susan_red = 10;
    public final static int susan_yellow = 11;
    public final static int susan_green = 12;
    public final static int susan_blue = 13;
}
