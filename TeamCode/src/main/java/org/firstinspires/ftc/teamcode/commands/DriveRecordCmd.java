package org.firstinspires.ftc.teamcode.commands;

import android.os.Environment;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/**
 * This command is dedicated to a command that controls driving for the Tele-op mode
 */

public class DriveRecordCmd extends CommandBase {

    private final DrivetrainSub drivetrainSub;
    private final DoubleSupplier driveX;
    private final DoubleSupplier driveY;
    private final DoubleSupplier turnX;
    private final DoubleSupplier turnY;
    private final DoubleSupplier rightTrigger;
    private final DoubleSupplier leftTrigger;
    private final DoubleSupplier angleDegrees;
    private final BooleanSupplier fieldCentricity;
    private Telemetry telemetry;
    private double[] currentState = new double[4];
    private double startTime;
    private FileWriter log;
    private String directoryPath;
    private File directory;

    /**
     * This command deals with the driving in teleop.
     *
     * @param drivetrainSubParam The drivetrain sub to be imported
     * @param angleParam The angle returned by the IMU, implemented by a double supplier because this is run in init, and we need this value to be updated constantly
     * @param fieldCentricityParam Whether we're field centric or not. This is a supplier for the same reason that angleParam is.
     */

    public DriveRecordCmd(DrivetrainSub drivetrainSubParam, DoubleSupplier rightTrigger, DoubleSupplier leftTrigger, DoubleSupplier driveX, DoubleSupplier driveY, DoubleSupplier turnX, DoubleSupplier turnY, DoubleSupplier angleParam, BooleanSupplier fieldCentricityParam, Telemetry telemetry){
        this.drivetrainSub = drivetrainSubParam;
        this.driveX = driveX;
        this.driveY = driveY;
        this.turnX = turnX;
        this.turnY = turnY;
        this.rightTrigger = rightTrigger;
        this.leftTrigger = leftTrigger;
        this.telemetry = telemetry;
        angleDegrees = angleParam;
        fieldCentricity = fieldCentricityParam;

        directoryPath = Environment.getExternalStorageDirectory().getPath()+"/AutoLogs";
        directory = new File(directoryPath);
        directory.mkdir();
        addRequirements(this.drivetrainSub);
    }

    @Override
    public void initialize(){
        this.startTime=System.currentTimeMillis();

        try {
            log = new FileWriter(directoryPath+"/"+(long)startTime/1000+".txt");
            log.write("[0.0, 0.0, 0.0, 0.0], \n");
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(){
        double driveX = this.driveX.getAsDouble();
        double driveY = this.driveY.getAsDouble();
        double turnX = this.turnX.getAsDouble();
        double brakeMultiplier = 1;
        double rightTrigger = this.rightTrigger.getAsDouble();
        double leftTrigger = this.leftTrigger.getAsDouble();

        currentState[0]=System.currentTimeMillis()- startTime;
        currentState[1]=driveX;
        currentState[2]=driveY;
        currentState[3]=turnX;

        telemetry.addData("Current State", Arrays.toString(currentState));

        try {
            log.write(Arrays.toString(currentState).concat(", \n"));
            System.out.println("Success");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error");
        }


        if (leftTrigger > 0.05 && leftTrigger < 0.75) {
            brakeMultiplier = (1 - leftTrigger)/2;
        } else if (rightTrigger >= 0.75) {
            brakeMultiplier = 0.125;
        }

        // if precision mode is on (the right trigger is pulled down to some degree)
        if (rightTrigger > 0.05 && rightTrigger < 0.75) {
            brakeMultiplier = 1 - rightTrigger;
            //telemetry.addData("Precise Mode", "On");
            // also if precision mode is on, but it caps brake at 25% after 75% trigger
        } else if (rightTrigger >= 0.75) {
            brakeMultiplier = 0.25;
            //telemetry.addData("Precise Mode", "On");
        }


        if (fieldCentricity.getAsBoolean()) {
            // optional fifth parameter for squared inputs
            drivetrainSub.getDrive().driveFieldCentric(
                    driveX*-brakeMultiplier,
                    driveY*-brakeMultiplier,
                    turnX*-brakeMultiplier,
                    angleDegrees.getAsDouble(),   // gyro value passed in here must be in degrees
                    false
            );
        } else {
            // optional fourth parameter for squared inputs
            drivetrainSub.getDrive().driveRobotCentric(
                    driveX*-brakeMultiplier,
                    driveY*-brakeMultiplier,
                    turnX*-brakeMultiplier,
                    false
            );
        }
    }
}
