package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmPower0;

@Autonomous(name = "Auto Specimen (A4, left edge)")
public class AutoSpeci extends AutoCommandOpMode {
    public void logic() {
        schedule(new SequentialCommandGroup(
                  arm0
                , wristCenter
                , drive(20)
                , arm25
                , lsSpeci
                , arm42
                , driveSlow(-10)
                , lsBottom
                , wristRight
                , arm0
                , turnCCW(90)
                , drive(-32)
                , turnCW(90)
                , drive(-12)
                , new ArmPower0(arm)
        ));
    }
}