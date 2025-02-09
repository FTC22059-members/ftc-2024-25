package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.commands.ArmHighGoal;
import org.firstinspires.ftc.teamcode.commands.ArmLow;
import org.firstinspires.ftc.teamcode.commands.ArmLowGoal;
import org.firstinspires.ftc.teamcode.commands.ArmToPosition;
import org.firstinspires.ftc.teamcode.commands.DriveDistanceCmd;
import org.firstinspires.ftc.teamcode.commands.MoveLinearSlideToPos;
import org.firstinspires.ftc.teamcode.commands.GripperMove;
import org.firstinspires.ftc.teamcode.commands.MoveWrist;
import org.firstinspires.ftc.teamcode.commands.TurnCmd;
import org.firstinspires.ftc.teamcode.commands.ArmMed;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;
import org.firstinspires.ftc.teamcode.subsystems.ImuSub;
import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.teamcode.subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;
import org.firstinspires.ftc.teamcode.subsystems.GripperSub;
import org.firstinspires.ftc.teamcode.subsystems.NewWristSub;

public class AutoCommandOpMode extends CommandOpMode
{
    private double turnSpeed = 0.4;


    protected DrivetrainSub drive;
    protected ImuSub imu;
    protected GripperSub gripperSub;
    protected LinearSlideSub linearSlideSub;
    protected IntakeSub intakeSub;
    protected NewWristSub wristSub;

    protected ArmSub arm;
    private ArmLow armLow;
    private ArmLowGoal armLowGoal;
    public ArmMed armMed;
    public ArmHighGoal armHighGoal;

    public ArmToPosition arm0;
    public ArmToPosition arm65;
    public ArmToPosition arm60;
    public ArmToPosition armBottom;

    public GripperMove gripHold;
    public GripperMove gripRelease;

    public MoveLinearSlideToPos lsLowGoal;
    public MoveLinearSlideToPos lsBottom;
    public MoveLinearSlideToPos lsSpeci;
    public MoveLinearSlideToPos lsBack;

    public MoveWrist wrist01;
    public MoveWrist wrist04;

    @Override
    public void initialize() {
        //Initializing Hardware
        drive = new DrivetrainSub(hardwareMap, telemetry);
        imu = new ImuSub(hardwareMap, telemetry);

        arm = new ArmSub(hardwareMap, telemetry);
        armLow = new ArmLow(arm, telemetry);
        armLowGoal = new ArmLowGoal(arm, telemetry);
        armMed = new ArmMed(arm, drive);
        armHighGoal = new ArmHighGoal(arm, drive);
        arm0 = new ArmToPosition(arm, 0.0, drive, true);
        armBottom = new ArmToPosition(arm, -27, drive, true);
        arm65 = new ArmToPosition(arm, 65, drive, false);
        arm60 = new ArmToPosition(arm, 60, drive, false);

        gripperSub = new GripperSub(hardwareMap,telemetry);
        gripHold = new GripperMove(gripperSub, Constants.GrabberConstants.wristGrip, telemetry);
        gripRelease = new GripperMove(gripperSub, Constants.GrabberConstants.wristRelease, telemetry);

        linearSlideSub = new LinearSlideSub(hardwareMap, telemetry);
        lsLowGoal = new MoveLinearSlideToPos(linearSlideSub, (int)Constants.LinearSlideConstants.lsLowGoalConstant,0.5,telemetry);
        lsBottom = new MoveLinearSlideToPos(linearSlideSub,0,0.5,telemetry);
        lsSpeci = new MoveLinearSlideToPos(linearSlideSub, Constants.LinearSlideConstants.lsSpeci,0.5,telemetry);
        lsBack = new MoveLinearSlideToPos(linearSlideSub, Constants.LinearSlideConstants.lsBack,0.5,telemetry);

        wristSub = new NewWristSub(hardwareMap, telemetry);
        wrist01 = new MoveWrist(wristSub, telemetry, 0.1);
        wrist04 = new MoveWrist(wristSub, telemetry, 0.4);

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
        return new DriveDistanceCmd(inches, Constants.DriveConstants.driveSpeed, drive, telemetry);
    }

    public DriveDistanceCmd driveSlow(int inches){
        return new DriveDistanceCmd(inches, Constants.DriveConstants.driveSlowSpeed, drive, telemetry);
    }
}