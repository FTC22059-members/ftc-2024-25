package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class LinearSlideSub extends SubsystemBase {

    private final DcMotor armMotor;
    Telemetry telemetry;

    private DcMotor linearSlideMotor;

    public LinearSlideSub(HardwareMap hardwareMap, Telemetry tm) {
        this.telemetry = tm;

        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlideMotor");
        armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");

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

    public void move(double speed) {
        double armX = Math.cos(Math.toRadians(ticksToDeg(armMotor.getCurrentPosition()))) * (ticksToInches(linearSlideMotor.getCurrentPosition()) + 18);

        if ((armX > 36) && (speed > 0)) {
            speed = 0;
        } else if ((getMotor().getCurrentPosition() < Constants.LinearSlideConstants.downwardLimit) && (speed < 0)) {
            speed = 0;
        }

        if ((armX > 38) && (speed <= 0)) {
            speed = -0.5;
        }

        if (getMotor().getCurrentPosition() > 2900 && (speed > 0)) {
            speed = 0;
        }

        telemetry.addData("armX", armX);
        telemetry.addData("lsPos", getMotor().getCurrentPosition());

        linearSlideMotor.setPower(speed);
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

    public DcMotor getMotor(){
        return linearSlideMotor;
    }

    public void setPower(double power){
        linearSlideMotor.setPower(power);
    }

    public int getPosition(){
        return linearSlideMotor.getCurrentPosition();
    }

    public int ticksToInches(int ticks) {
        return ticks / 112;
    }

    public double ticksToDeg(int ticks){
        return (ticks-135.0)*107.0/708.0;
    }
}