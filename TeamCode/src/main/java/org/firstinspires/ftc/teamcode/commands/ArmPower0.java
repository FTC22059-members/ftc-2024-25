package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;

import java.util.function.DoubleSupplier;

public class ArmPower0 extends CommandBase {
    private final ArmSub armSub;

    public ArmPower0(ArmSub armSub) {
        this.armSub = armSub;
        addRequirements(this.armSub);

    }

    @Override
    public void execute() {
        armSub.setPower0();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
