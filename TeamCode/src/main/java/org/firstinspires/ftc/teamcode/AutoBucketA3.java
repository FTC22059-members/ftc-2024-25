package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;
import org.firstinspires.ftc.teamcode.commands.ArmPower0;

@Autonomous(name = "Auto Bucket (A3, left edge)")
public class AutoBucketA3 extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                gripHold
                , drive(16)
                , arm0
                //, wristCenter
                , turnCCW(85)
                , drive(26)
                , turnCCW(45)
                , drive(4)
                , new ArmLowGoal(arm,telemetry)
                , lsLowGoal
                , gripRelease
                //, outtake
                , lsBottom
                //, wristRight
                , armBottom
                , drive(-4)
                , turnCW(45)
                , drive(-84)
                , turnCW(90)
                , drive(-18)
                , new ArmPower0(arm)
        ));
    }
}