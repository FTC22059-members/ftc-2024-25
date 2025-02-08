package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.GripperSub;
/**

 * This command is dedicated to a command that controls the wrist for the Tele-op mode
 */

public class GripperOpen extends CommandBase {

    private final GripperSub gripperSub;
    private final Telemetry telemetry;
    /**
     * This command deals with the wrist in teleop.
     *
     * @param gripperSubParam The wrist sub to be imported
     */

    public GripperOpen(GripperSub gripperSubParam, Telemetry telemetry) {
        this.gripperSub = gripperSubParam;
        this.telemetry = telemetry;
        addRequirements(this.gripperSub);
    }

    @Override
    public void execute() {
        gripperSub.setPosition(gripperSub.getPosition() - 0.05);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}