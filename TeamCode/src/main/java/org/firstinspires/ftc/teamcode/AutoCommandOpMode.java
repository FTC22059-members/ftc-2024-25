package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.ArmHighGoal;
import org.firstinspires.ftc.teamcode.commands.ArmLow;
import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.IntakeForTimeCmd;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.commands.ArmMed;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;
import org.firstinspires.ftc.teamcode.subsystems.WristSub;

public class AutoCommandOpMode extends CommandOpMode
{
    private double turnSpeed = 0.4;
    private double driveSpeed = 0.5;

    protected DrivetrainSub drive;
    protected ImuSub imu;
    protected WristSub wristSub;
    protected LinearSlideSub linearSlideSub;
    protected IntakeSub intakeSub;

    protected ArmSub arm;
    private ArmLow armLow;
    private ArmLowGoal armLowGoal;
    public ArmMed armMed;
    public ArmHighGoal armHighGoal;


    @Override
    public void initialize() {
        //Initializing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);
        wristSub = new WristSub(hardwareMap,telemetry);
        linearSlideSub = new LinearSlideSub(hardwareMap, telemetry);
        intakeSub = new IntakeSub(hardwareMap, telemetry);

        arm = new ArmSub(hardwareMap, telemetry);
        armLow = new ArmLow(arm, telemetry);
        armLowGoal = new ArmLowGoal(arm, telemetry);
        armMed = new ArmMed(arm, drive);
        armHighGoal = new ArmHighGoal(arm, drive);

        while(opModeInInit()){
            telemetry.update();
        }

        waitForStart();

        logic();
    }

    public void logic(){}

    public TurnCmd turnCW(int angle){
        return new TurnCmd(-angle,turnSpeed,drive,imu,telemetry);
    }

    public TurnCmd turnCCW(int angle){
        return new TurnCmd(angle,turnSpeed,drive,imu,telemetry);
    }

    public DriveDistanceCmd drive(int inches){
        return new DriveDistanceCmd(inches, driveSpeed, drive, telemetry);
    }
}