package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmPower0;

@Autonomous(name = "Auto Specimen (A4, left edge)")
public class AutoSpeci extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                  gripHold
                , drive(1)
                , arm65
                , drive(24)
                , lsSpeci
                , arm60
                , lsBack
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