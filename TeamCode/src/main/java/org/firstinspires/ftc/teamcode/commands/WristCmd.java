package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.GripperSub;

/**
 * This command is dedicated to a command that controls the wrist for the Tele-op mode
 */

public class WristCmd extends CommandBase {

    private final GripperSub gripperSub;
    double speed;

    /**
     * This command deals with the wrist in teleop.
     *
     * @param gripperSubParam The wrist sub to be imported
     * @param speedParam The speed to move the wrist
     */

    public WristCmd(GripperSub gripperSubParam, double speedParam){
        this.gripperSub = gripperSubParam;
        speed = speedParam;
        addRequirements(this.gripperSub);
    }
}
