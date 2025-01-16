package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;

@Autonomous(name = "Auto Red/Blue")
public class AutoRedBlue extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                drive(12)
                /*step 1: go to central position with PinkAqua*/
                , arm0
                , wristCenter
                , turnCCW(85)
                , drive(50)
                , turnCCW(45)
                , drive(8)
                , new ArmLowGoal(arm,telemetry)
                , lsLowGoal
                , outtake
                , lsBottom
                , wristLeft
                , armBottom
                , drive(-8)
                , turnCW(45)
                , drive(-92)
                , turnCW(90)
                , drive(-12)
        ));
    }
}