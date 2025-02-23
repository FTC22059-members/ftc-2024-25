package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;
import org.firstinspires.ftc.teamcode.commands.ArmPower0;
import org.firstinspires.ftc.teamcode.commands.Pause;

@Autonomous(name = "Auto Bucket (A4, left edge)")
public class AutoBucketA4 extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                gripHold
                , drive(16)
                , turnCCW(87)
                , drive(47)
                , turnCCW(43)
                , drive(9)
                , new ArmLowGoal(arm,telemetry)
                , new Pause(2000)
                , lsLowGoal
                , gripRelease
                , lsBottom
                , armBottom
                , drive(-9)
                , turnCW(43)
                , drive(-81)
                , turnCW(90)
                , drive(-18)
                , new ArmPower0(arm)
        ));
    }
}