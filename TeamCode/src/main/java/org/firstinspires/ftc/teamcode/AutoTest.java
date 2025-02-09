package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.ArmToPosition;
import org.firstinspires.ftc.teamcode.commands.IntakeForTimeCmd;
import org.firstinspires.ftc.teamcode.commands.MoveLinearSlideToPos;
import org.firstinspires.ftc.teamcode.commands.GripperMove;

@Autonomous(name = "Auto Test")

public class AutoTest extends AutoCommandOpMode {
    @Override
    public void logic(){

        //I am well aware that these names are probably the worst you'll ever see,
        //but it is only temporary.
        //Be glad I didn't name 'grab' 'sladf'.

        ArmToPosition arm30 = new ArmToPosition(arm, 30.0, drive, true);
        ArmToPosition arm0 = new ArmToPosition(arm, 0.0, drive, true);

        MoveLinearSlideToPos mls0 = new MoveLinearSlideToPos(linearSlideSub,0,.5,telemetry);
        MoveLinearSlideToPos mls500 = new MoveLinearSlideToPos(linearSlideSub, 500, .5, telemetry);

        GripperMove mwtl = new GripperMove(gripperSub, Constants.WristConstants.wristLeft, telemetry);
        GripperMove mwtc = new GripperMove(gripperSub, Constants.WristConstants.wristCenter, telemetry);
        GripperMove mwtr = new GripperMove(gripperSub, Constants.WristConstants.wristRight, telemetry);

        IntakeForTimeCmd grab = new IntakeForTimeCmd(intakeSub,0.8,1000, true);
        IntakeForTimeCmd eject = new IntakeForTimeCmd(intakeSub,0.5,3000, false);

        schedule(new SequentialCommandGroup(
            arm30,
            mwtc,
            mls500,
            grab,
            mwtr,
            mls0,
            eject,
            mwtl,
            arm0
        ));
    }
}