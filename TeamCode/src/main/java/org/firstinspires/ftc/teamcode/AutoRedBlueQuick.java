package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.commands.ArmToPosition;

@Autonomous(name = "Auto Red/Blue quick")
@Disabled
public class AutoRedBlueQuick extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                drive(12)
                /*step 1: go to central position with PinkAqua*/
                , arm0
                , wristCenter
                , turnCW(90)
                , drive(15)
                , turnCCW(90)

                /*step 2: go to cage (this has a different name)*/
                , drive(10)
                /* do something with the arm*/
                , armBottom
                , grab
                , arm0

                /*step 3: go to board*/
                , drive(-9)
                , turnCCW(90)

                /*step 4: stop*/
                , wristLeft
                , new ArmToPosition(arm, -20, drive, false)
        ));
    }
}