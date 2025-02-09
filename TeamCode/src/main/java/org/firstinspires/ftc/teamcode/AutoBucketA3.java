package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;
import org.firstinspires.ftc.teamcode.commands.ArmPower0;
import org.firstinspires.ftc.teamcode.commands.Pause;

@Autonomous(name = "Auto Bucket (A3, left edge)")
public class AutoBucketA3 extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                gripHold
                , drive(16)
                , turnCCW(85)
                , drive(28)
                , turnCCW(45)
                , drive(9)
                , new ArmLowGoal(arm,telemetry)
                , new Pause(2000)
                , lsLowGoal
                , gripRelease
                , lsBottom
                , armBottom
                , drive(-9)
                , turnCW(45)
                , drive(-86)
                , turnCW(90)
                , drive(-18)
                , new ArmPower0(arm)
        ));
    }
}