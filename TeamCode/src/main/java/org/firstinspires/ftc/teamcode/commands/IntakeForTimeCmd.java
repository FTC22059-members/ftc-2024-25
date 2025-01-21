package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

import java.util.concurrent.TimeUnit;

/**
 * This command is dedicated to a command that controls the intake for the Tele-op mode
 */

public class IntakeForTimeCmd extends CommandBase {

    private final IntakeSub intakeSub;
    private double power;
    private final long intakeTime;
    private final boolean grab;

    /**
     * This command deals with the intake in teleop.
     *
     * @param intakeSubParam The intake sub to be imported
     * @param power The speed to move the intake
     * @param time The time to move the intake for (in ms).
     * @param grab True intakes piece, False ejects it.
     */

    public IntakeForTimeCmd(IntakeSub intakeSubParam, double power, long time, boolean grab){
        this.intakeSub = intakeSubParam;
        this.power = Math.abs(power);
        this.intakeTime = time;
        this.grab = grab;
        addRequirements(this.intakeSub);

        if (!grab){
            this.power *= -1;
        }
    }

    @Override
    public void execute(){
        this.intakeSub.setSpeed(power);
        try {
            TimeUnit.MILLISECONDS.sleep(this.intakeTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.intakeSub.setSpeed(0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
