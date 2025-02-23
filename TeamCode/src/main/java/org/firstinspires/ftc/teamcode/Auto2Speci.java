package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmPower0;
import org.firstinspires.ftc.teamcode.commands.Pause;

@Autonomous(name = "Auto Two Specimens (A4, left edge)")
public class Auto2Speci extends AutoCommandOpMode {
    public void logic() {

        schedule(new SequentialCommandGroup(
                  gripHold
                , drive(3)
                , arm65
                , wrist07
                , drive(18)
                , lsSpeci
                , arm60
                , lsBack
                , wrist04
                , gripRelease
                , lsBottom
                , arm5
                , wrist03
                , drive(-18)
                , turnCW(89)
                , drive(56)
                , ls50
                , gripHold
                , new Pause(1000)
                , arm20
                , drive(-60)
                , turnCCW(90)
                , arm65
                , wrist07
                , drive(18)
                , lsSpeci50
                , arm55
                , lsBack2
                , wrist04
                , gripRelease
                , lsBottom2
                , arm0

                , new ArmPower0(arm)
        ));
    }
}