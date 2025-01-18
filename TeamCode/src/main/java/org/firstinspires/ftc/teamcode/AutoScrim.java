package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "Auto Scrimmage")
@Disabled
public class AutoScrim extends AutoCommandOpMode {

    @Override
    public void logic() {
        schedule(new SequentialCommandGroup(
                armMed
                , drive(12)
                , turnCW(90)
                , drive(18)
                , turnCW(90)
                , drive(10)
        ));
    }
}