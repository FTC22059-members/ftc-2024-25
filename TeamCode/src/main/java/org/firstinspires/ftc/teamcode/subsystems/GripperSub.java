package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class GripperSub extends SubsystemBase {
    Telemetry telemetry;

    public Servo gripper;

    public GripperSub(HardwareMap hardwareMap, Telemetry tm) {
        gripper = hardwareMap.get(Servo.class, "wrist");
        //TODO: Change configuration name from wrist to gripper
        this.telemetry = tm;
    }

    @Override
    public void periodic() {
        telemetry.addData("Gripper position: ", gripper.getPosition());
    }

    public Servo getServo(){
        return gripper;
    }

    /**
    * Sets the position of the wrist.
    *
    * @param position the angle of the wrist
     */
    public void setPosition(double position) {
        //TO-DO: Get limit of position
//        if (position < 0.35) {
            gripper.setPosition(position);
//        }
    }

   public double getPosition () {
        return gripper.getPosition();
   }

}