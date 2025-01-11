package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.WristSub;

/**

 * This command is dedicated to a command that controls the wrist for the Tele-op mode
 */

public class MoveWristTo extends CommandBase {

    private final WristSub wristSub;
    private final Telemetry telemetry;
    private double location;
    /**
     * This command deals with the wrist in teleop.
     *
     * @param wristSubParam The wrist sub to be imported
     */

    public MoveWristTo(WristSub wristSubParam, double locationParam, Telemetry telemetry) {
        this.wristSub = wristSubParam;
        this.telemetry = telemetry;
        this.location = locationParam;
        addRequirements(this.wristSub);
    }

    @Override
    public void execute() {
        wristSub.setPosition(location);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}