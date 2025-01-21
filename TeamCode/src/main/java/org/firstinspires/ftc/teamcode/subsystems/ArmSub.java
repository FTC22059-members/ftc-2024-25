package org.firstinspires.ftc.teamcode.subsystems;

import static java.lang.Math.abs;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Constants.ArmConstants;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class ArmSub extends SubsystemBase{
    Telemetry telemetry;

    public DcMotorEx armMotor;

    public double degree;

    public ArmSub(HardwareMap hardwareMap, Telemetry telemetry) {
        this.armMotor = hardwareMap.get(DcMotorEx.class, "armMotor");
        this.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.armMotor.setTargetPosition(19);
        this.armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.telemetry = telemetry;

    }

    @Override
    public void periodic() {
        telemetry.addData("Arm Pos", armMotor.getCurrentPosition());
        telemetry.addData("Arm Deg", getDeg());
    }

    public DcMotor getMotor(){
        return armMotor;
    }

    public void armUp() {
        setPos(getMotor().getCurrentPosition() + 20, 1);
    }

    public void armDown() {
        setPos(getMotor().getCurrentPosition() - 20, 1);
    }

    public void moveArm(double speed) {
        //setPos((int) (getMotor().getCurrentPosition() + (20*speed)), 1);
        if (speed>0) {
            setPos((int) (getMotor().getCurrentPosition() + 30), 1);
        } else {
            setPos((int) (getMotor().getCurrentPosition() - 30), -1);
        }
    }


    public void armLow() {
        setPos(ArmConstants.armLow, -1);
    }

    public void armMed(){
        setPos(ArmConstants.armMed, -1);
    }

    public void armLowGoal() {
        setPos(ArmConstants.armLowGoal, -1);
    }

    public void armHighGoal() {
        setPos(ArmConstants.armHighGoal, -1);
    }

    public void setPos(int pos, int dir) {
        armMotor.setTargetPosition(pos);
        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //if moving down, set velocity to a certain amount.

        if (armMotor.getCurrentPosition()<pos) {
            armMotor.setVelocity(450);
        } else {
            armMotor.setVelocity(300);
        }
    }

    public void setPower0(){
        armMotor.setPower(0);
    }

    public double getDeg(){
        return convertDeg(armMotor.getCurrentPosition());
    }

    public double convertDeg(int ticks){
        return (ticks-135.0)*107.0/708.0;
    }
    public int convertTicks(double deg){
        return (int)((deg*708/107)+135);
    }
}
