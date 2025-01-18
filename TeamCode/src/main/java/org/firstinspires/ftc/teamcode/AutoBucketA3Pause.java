package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;
import org.firstinspires.ftc.teamcode.commands.ArmPower0;
import org.firstinspires.ftc.teamcode.commands.Pause;

@Autonomous(name = "Auto Bucket Pause (A3, left edge)")
public class AutoBucketA3Pause extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                drive(16)
                , arm0
                , wristCenter
                , turnCCW(85)
                , drive(26)
                , turnCCW(45)
                , drive(10)
                , new ArmLowGoal(arm,telemetry)
                , lsLowGoal
                , outtake
                , lsBottom
                , wristRight
                , armBottom
                , drive(-10)
                , turnCW(45)
                , new ArmPower0(arm)
                , new Pause(3000)
                , drive(-84)
                , turnCW(90)
                , drive(-18)
        ));
    }
}