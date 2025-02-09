package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.GripperSub;

/**
 * This command is dedicated to a command that controls the wrist for the Tele-op mode
 */

public class MoveWristRight extends CommandBase {

    private final GripperSub gripperSub;
    private final Telemetry telemetry;

    /**
     * This command deals with the wrist in teleop.
     *
     * @param gripperSubParam The wrist sub to be imported
     */

    public MoveWristRight(GripperSub gripperSubParam, Telemetry telemetry){
        this.gripperSub = gripperSubParam;
        this.telemetry = telemetry;
        addRequirements(this.gripperSub);
    }

    @Override
   public void execute() {
        double wristPosition = this.gripperSub.getPosition();

//        if ((wristPosition != Constants.WristConstants.wristRight) && (wristPosition != Constants.WristConstants.wristCenter) && (wristPosition != Constants.WristConstants.wristLeft)) {
//            this.wristSub.setPosition(Constants.WristConstants.wristCenter);
//        }

        if (wristPosition == Constants.WristConstants.wristLeft){
            this.gripperSub.setPosition(Constants.WristConstants.wristCenter);
            telemetry.addData("Wrist moved to center", wristPosition);
        } else if(wristPosition == Constants.WristConstants.wristCenter){
            this.gripperSub.setPosition(Constants.WristConstants.wristRight);
            telemetry.addData("Wrist moved to right", wristPosition);

        }
    }
}