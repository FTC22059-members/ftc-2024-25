package org.firstinspires.ftc.teamcode.commands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.NewWristSub;
import org.firstinspires.ftc.teamcode.subsystems.WristSub;

public class MoveNewWrist extends CommandBase {

    private final NewWristSub wristSub;
    private final double speed;
    private final Telemetry telemetry;

    public MoveNewWrist(NewWristSub wristSubParam, Telemetry telemetry, double speedParam) {
        this.wristSub = wristSubParam;
        this.speed = speedParam;
        this.telemetry = telemetry;
        addRequirements(this.wristSub);
    }

    @Override
    public void execute() {
        wristSub.setPos(wristSub.getServo().getPosition() + speed);
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
