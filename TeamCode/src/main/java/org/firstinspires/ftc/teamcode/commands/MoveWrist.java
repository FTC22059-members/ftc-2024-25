package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.NewWristSub;

public class MoveWrist extends CommandBase {

    private final NewWristSub wristSub;
    private final double position;
    private final Telemetry telemetry;

    public MoveWrist(NewWristSub wristSubParam, Telemetry telemetry, double positionParam) {
        this.wristSub = wristSubParam;
        this.position = positionParam;
        this.telemetry = telemetry;
        addRequirements(this.wristSub);
    }

    @Override
    public void execute() {
        wristSub.setPos(position);
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
