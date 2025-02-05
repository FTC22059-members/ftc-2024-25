package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;
import org.firstinspires.ftc.teamcode.commands.ArmPower0;

@Autonomous(name = "Auto Bucket (A4, left edge)")
public class AutoBucketA4 extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                gripHold
                , drive(12)
                , arm0
                //, wristCenter
                , turnCCW(85)
                , drive(50)
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
                , drive(-92)
                , turnCW(90)
                , drive(-12)
                , new ArmPower0(arm)
        ));
    }
}