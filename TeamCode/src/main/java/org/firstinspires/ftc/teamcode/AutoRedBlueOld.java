package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


//The only reason that this exists is that I spent a lot of time working on it by accident.
//Can be partially used in the event that a preload is not available for some reason
@Autonomous(name = "Auto Red/Blue OLD")
@Disabled
public class AutoRedBlueOld extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                drive(12)
                /*step 1: go to central position with PinkAqua*/
                , arm0
                //, wristCenter NOTE: Original function has been removed (Feb. 4)
                , turnCW(90)
                , drive(15)
                , turnCCW(90)

                /*step 2: go to cage (this has a different name)*/
                , drive(9)
                /* do something with the arm*/
                , armBottom
                //, grab NOTE: Original function has been removed (Feb. 4)
                , arm0

                /*step 3: go to board*/
                , drive(-9)
                , turnCCW(90)
                , drive(40)
                , turnCCW(45)
                , drive(12)
                /*do something with the arm*/

                /*step 4: park*/
                , drive(-30)
                , turnCCW(45)
                , drive(-36)
                , turnCCW(70)
                , drive(24)
        ));
    }
}