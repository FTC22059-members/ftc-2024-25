package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;

import java.util.function.DoubleSupplier;

public class MoveLinearSlide extends CommandBase {
    private final LinearSlideSub linearSlideSub;
    private final Telemetry telemetry;
    private DoubleSupplier leftY = null;
    private Double leftYNonDS = null;

    public MoveLinearSlide(LinearSlideSub linearSlideSub, DoubleSupplier leftY, Telemetry telemetry) {
        this.linearSlideSub = linearSlideSub;
        this.telemetry = telemetry;
        this.leftY = leftY;
        addRequirements(this.linearSlideSub);
    }

    /**
     * It's here if you need it. The reason why I made this has disappeared though.
     * @param linearSlideSub
     * @param leftYNonDS
     * @param telemetry
     */
    public MoveLinearSlide(LinearSlideSub linearSlideSub, Double leftYNonDS, Telemetry telemetry) {
        this.linearSlideSub = linearSlideSub;
        this.telemetry = telemetry;
        this.leftYNonDS = leftYNonDS;
        addRequirements(this.linearSlideSub);
    }

    @Override
    public void execute() {
        if(leftYNonDS==null) {

            linearSlideSub.move(this.leftY.getAsDouble());
        }else if (leftY==null){
            linearSlideSub.move(this.leftYNonDS);
        }else if (leftYNonDS==null&leftY==null){
            telemetry.addLine("(ls) A linear slide value hasn't been set!");
        }else if (leftYNonDS!=null&leftY!=null){
            telemetry.addLine("(ls) Both the double value and double supplier value have been set!");
        }else{
            telemetry.addLine("(ls) Unknown if error!");
        }
    }
}
