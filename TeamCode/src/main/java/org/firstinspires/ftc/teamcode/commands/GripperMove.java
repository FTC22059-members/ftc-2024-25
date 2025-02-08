package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GripperSub;

/**

 * This command is a that controls the gripper for the Tele-op mode
 */

public class GripperMove extends CommandBase {

    private final GripperSub gripperSub;
    private final Telemetry telemetry;
    private final double targetLocation;

    /**
     * This command deals with the gripper in teleop.
     *
     * @param gripperSubParam The gripper sub to be imported
     */

    public GripperMove(GripperSub gripperSubParam, double targetLocationParam, Telemetry telemetry) {
        this.gripperSub = gripperSubParam;
        this.telemetry = telemetry;
        this.targetLocation = targetLocationParam;
        addRequirements(this.gripperSub);
    }

    @Override
    public void execute() {
        gripperSub.setPosition(targetLocation);
    }

    @Override
    public boolean isFinished(){
        return targetLocation == this.gripperSub.getPosition();
    }
}