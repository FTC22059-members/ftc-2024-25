package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;

import java.util.concurrent.TimeUnit;

/**
 * This command is dedicated to a command that controls the intake for the Tele-op mode
 */

public class Pause extends CommandBase {
    private final long intakeTime;

    /**
     * Pauses the robot for a certain amount of time
     *
     * @param time The time to pause for (in ms).
     */

    public Pause(long time){
        this.intakeTime = time;
    }

    @Override
    public void execute(){
        try {
            TimeUnit.MILLISECONDS.sleep(this.intakeTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
