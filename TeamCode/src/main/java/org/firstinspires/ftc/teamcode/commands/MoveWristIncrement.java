package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.NewWristSub;

public class MoveWristIncrement extends CommandBase {

    private final NewWristSub wristSub;
    private final double posChange;
    private final Telemetry telemetry;

    public MoveWristIncrement(NewWristSub wristSubParam, Telemetry telemetry, double posChangeParam) {
        this.wristSub = wristSubParam;
        this.posChange = posChangeParam;
        this.telemetry = telemetry;
        addRequirements(this.wristSub);
    }

    @Override
    public void execute() {
        wristSub.setPos(wristSub.getPos() + posChange);
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
