package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;

public class MoveLinearSlideToPos extends CommandBase {
    private final LinearSlideSub linearSlideSub;
    private final Telemetry telemetry;
    private final Integer finalPos;
    private Double power;
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
        isExtending = this.linearSlideSub.getPosition() < this.finalPos;

        if (!isExtending){
            this.power *= -1;
        }
    }

    @Override
    public void execute() {
        if (this.power != null & this.finalPos != null){
            //telemetry.addLine("(lsToPos) Going to Pos " + this.finalPos + " at power " + power);
            //telemetry.addLine("current pos " + this.linearSlideSub.getPosition());
            //System.out.println("(lsToPos) Going to Pos " + this.finalPos + " at power " + power);
            //System.out.println("(lsToPos) current pos " + this.linearSlideSub.getPosition());
            linearSlideSub.move(power);
        }/*else if (this.power == null & this.finalPos != null){
            telemetry.addLine("(lsToPos) Power isn't set!");
        }else if (this.power != null & this.finalPos == null){
            telemetry.addLine("(lsToPos) Final Position isn't set!");
        }else{
            telemetry.addLine("(lsToPos) Unknown execute if error!");
        }*/

        //telemetry.update();
    }

    public boolean isFinished(){
        boolean finished;
        if (isExtending){
            finished = this.linearSlideSub.getPosition() > this.finalPos;
        } else {
            finished = this.linearSlideSub.getPosition() < this.finalPos;
        }
        //telemetry.addData("(lsToPos) Is Extending?", this.isExtending);
        //telemetry.addData("(lsToPos) finished?", finished);
        //System.out.println("(lsToPos) Is Extending? " + this.isExtending);
        //System.out.println("(lsToPos) finished? " + finished);
        return finished;
    }

    @Override
    public void end(boolean interrupted){
        linearSlideSub.setPower(0);
        //System.out.println("(lsToPos) ended.");
    }
}
