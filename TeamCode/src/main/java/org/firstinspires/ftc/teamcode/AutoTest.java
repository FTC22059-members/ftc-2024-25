package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmToPosition;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.MoveLinearSlideToPos;
import org.firstinspires.ftc.teamcode.commands.MoveWristTo;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;
import org.firstinspires.ftc.teamcode.subsystems.WristSub;

@Autonomous(name = "Auto Test")

public class AutoTest extends AutoCommandOpMode {
    @Override
    public void logic(){
        LinearSlideSub linearSlideSub = new LinearSlideSub(hardwareMap, telemetry);
        ArmToPosition armPos=new ArmToPosition(arm, 30.0, drive, true);
        WristSub wristSub = new WristSub(hardwareMap,telemetry);
        schedule(new SequentialCommandGroup(
            armPos,
            new MoveLinearSlideToPos(linearSlideSub, -500, .5, telemetry),
            new MoveWristTo(wristSub, 0.5, telemetry)
        ));
    }
}