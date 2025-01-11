package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;

public class MoveLinearSlideToPos extends CommandBase {
    private final LinearSlideSub linearSlideSub;
    private final Telemetry telemetry;
    private Integer finalPos;
    private Double power = null;
    private Boolean isExtending = true;

    public MoveLinearSlideToPos(LinearSlideSub linearSlideSub, Integer finalPos, Double power, Telemetry telemetry) {
        this.linearSlideSub = linearSlideSub;
        this.telemetry = telemetry;
        this.finalPos = finalPos;
        this.power = Math.abs(power);
        addRequirements(this.linearSlideSub);
    }

    @Override
    public void initialize(){
        isExtending = this.finalPos <= this.linearSlideSub.getMotor().getCurrentPosition();

        if (isExtending){
            this.power *= -1;
        }
    }

    @Override
    public void execute() {
        if (this.power != null & this.finalPos != null){
            telemetry.addLine("(lsToPos) Going to Pos " + this.finalPos + " at power " + power);
            telemetry.addLine("current pos " + this.linearSlideSub.getMotor().getCurrentPosition());
            linearSlideSub.move(power);
        }else if (this.power == null){
            telemetry.addLine("(lsToPos) Power isn't set!");
        }else if (this.finalPos == null){
            telemetry.addLine("(lsToPos) Final Position isn't set!");
        }else{
            telemetry.addLine("(lsToPos) Unknown execute if error!");
        }

        telemetry.update();
    }

    public boolean isFinished(){
        boolean finished = false;
        if (isExtending){
            //I don't know if people will like me for doing this. Only one way to find out!
            finished = this.finalPos<this.linearSlideSub.getMotor().getCurrentPosition();
        } else {
            finished = this.finalPos>this.linearSlideSub.getMotor().getCurrentPosition();
        }
        return finished;
    }
}
