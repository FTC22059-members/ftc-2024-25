package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auto Specimen")
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
        ));
    }
}