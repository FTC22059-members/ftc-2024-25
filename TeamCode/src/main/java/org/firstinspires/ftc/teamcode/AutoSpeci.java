package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmPower0;
import org.firstinspires.ftc.teamcode.commands.MoveWristIncrement;

@Autonomous(name = "Auto Specimen (A4, left edge)")
public class AutoSpeci extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                  gripHold
                , drive(3)
                , arm65
                , wrist01
                , drive(22)
                , lsSpeci
                , arm60
                , lsBack
                , wrist04
                , gripRelease
                , lsBottom
                , arm0
                , turnCCW(90)
                , drive(-32)
                , turnCW(90)
                , drive(-24)
                , new ArmPower0(arm)
        ));
    }
}