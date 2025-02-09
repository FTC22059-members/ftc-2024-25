package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.Servo;


public class NewWristSub extends SubsystemBase {
    Telemetry telemetry;

    private Servo wrist;

    public NewWristSub(HardwareMap hardwareMap, Telemetry tm) {
        wrist = hardwareMap.get(Servo.class, "intake");
        this.telemetry = tm;
    }

    @Override
    public void periodic() {
        telemetry.addData("Wrist position: ", wrist.getPosition());
    }

    public double getPos(){
        return wrist.getPosition();
    }

    public void setPos(double pos) {
        //telemetry.addData("Intake called with speed of ", speed);
        wrist.setPosition(pos);
    }
}