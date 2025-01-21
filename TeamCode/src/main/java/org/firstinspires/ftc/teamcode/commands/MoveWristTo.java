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
    private double targetLocation;

    /**
     * This command deals with the wrist in teleop.
     *
     * @param wristSubParam The wrist sub to be imported
     */

    public MoveWristTo(WristSub wristSubParam, double targetLocationParam, Telemetry telemetry) {
        this.wristSub = wristSubParam;
        this.telemetry = telemetry;
        this.targetLocation = targetLocationParam;
        addRequirements(this.wristSub);
    }

    @Override
    public void execute() {
        wristSub.setPosition(targetLocation);
    }

    @Override
    public boolean isFinished(){
        //System.out.println("(mwt) target "+targetLocation);
        //System.out.println("(mwt) position "+this.wristSub.getPosition());
        //System.out.println("(mwt) target==position? "+(targetLocation == this.wristSub.getPosition()));
        return targetLocation == this.wristSub.getPosition();
    }
}