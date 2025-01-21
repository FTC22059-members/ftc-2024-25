package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class LinearSlideSub extends SubsystemBase {

    Telemetry telemetry;

    private DcMotor linearSlideMotor;

    public LinearSlideSub(HardwareMap hardwareMap, Telemetry tm) {
        this.telemetry = tm;

        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");
        linearSlideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        resetEncoder();
    }

    @Override
    public void periodic() {
        telemetry.addData("Slide Position", linearSlideMotor.getCurrentPosition());
    }

    /**
     * Reset the encoder
     */
    public void resetEncoder(){
        this.linearSlideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.linearSlideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.linearSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void move(double power) {

        if ((this.linearSlideMotor.getCurrentPosition() > getUpwardLimit()) && (power > 0)) {
            power = 0;
        } else if ((this.linearSlideMotor.getCurrentPosition() < Constants.LinearSlideConstants.downwardLimit) && (power < 0)) {
            power = 0;
        }

        linearSlideMotor.setPower(power);
        //System.out.println("(lss) setting power to "+power);
    }

    /** This method gets the upward limit, and can be changed to dynamically calculate it based
     * on the angle of the arm.
     *
     * @return The upward extension limit for the linear slide.
     */

    private static double getUpwardLimit() {
        return Constants.LinearSlideConstants.upwardLimit;
    }

    //special move command that doesn't adhere to limits
    //TODO: Maybe should replace final line of move? They're the same.
    public void moveNoLimit(double power) {
        linearSlideMotor.setPower(power);
    }

    public void setPower(double power){
        linearSlideMotor.setPower(power);
    }

    public int getPosition(){
        return linearSlideMotor.getCurrentPosition();
    }
}