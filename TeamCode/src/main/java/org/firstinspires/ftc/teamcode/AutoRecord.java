package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.subsystems.ArmSub;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

import org.firstinspires.ftc.teamcode.commands.DriveCmd;
import org.firstinspires.ftc.teamcode.subsystems.GripperSub;
import org.firstinspires.ftc.teamcode.subsystems.LinearSlideSub;
import org.firstinspires.ftc.teamcode.subsystems.NewWristSub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Autonomous(name = "Autonomous Record")
public class AutoRecord extends CommandOpMode {

    private BufferedReader reader;
    private DriveCmd driveCmd;
    private String line = "";
    private Double[] doubleDrive;
    private Double[][] gamepadRecord;
    private Double[][] gamepadTimeline;
    private long startTime;
    //private int previousIndex;
    private DrivetrainSub drive;
    private LinearSlideSub lsSub;
    private ArmSub armSub;
    private GripperSub gripperSub;
    private NewWristSub wristSub;

    @Override
    public void initialize() {
        startTime = -1;

        doubleDrive = new Double[8];
        gamepadRecord = new Double[30000][8];
        gamepadTimeline = new Double[30000][8];
        drive = new DrivetrainSub(hardwareMap, telemetry);
        lsSub = new LinearSlideSub(hardwareMap, telemetry);
        armSub = new ArmSub(hardwareMap, telemetry);
        gripperSub = new GripperSub(hardwareMap, telemetry);
        wristSub = new NewWristSub(hardwareMap, telemetry);

        try {
//            reader = new BufferedReader(new FileReader("/storage/self/primary/AutoLogs/1737499403.txt"));
            reader = new BufferedReader(new FileReader("/storage/emulated/0/AutoLogs/DEBUG.txt"));
            line = reader.readLine();
            boolean endLoop = false;

            System.out.println("I'M INSANE PART 1");

            while (!Objects.equals(line, "STOP")) {

                System.out.println("I'M INSANE PART 2");

                //get rid of square brackets for easier parsing
                try {
                    line = line.replace(" ", "");
                } catch (NullPointerException e) {
                    break;
                }
                line = line.replace("[","");
                line = line.replace("]","");

                //make a line into an array
                String[] stringDrive = line.split("[,]");

                System.out.println("I'M INSANE PART 3");


                //convert strings to doubles
                for (int j = 0; j <= 7; j++){
                    try {
                        doubleDrive[j] = Double.parseDouble(stringDrive[j]);
                    }catch (ArrayIndexOutOfBoundsException e){
//                        endLoop=true;
//                        break;
                    }
                }

                System.out.println("I'M INSANE PART 4");

                if (endLoop){
                    break;
                }


                System.out.println("I'M INSANE PART 5");

                gamepadRecord[doubleDrive[0].intValue()][0] = doubleDrive[1];
                gamepadRecord[doubleDrive[0].intValue()][1] = doubleDrive[2];
                gamepadRecord[doubleDrive[0].intValue()][2] = doubleDrive[3] * -1;
                gamepadRecord[doubleDrive[0].intValue()][3] = doubleDrive[0];
                gamepadRecord[doubleDrive[0].intValue()][4] = doubleDrive[4];
                gamepadRecord[doubleDrive[0].intValue()][5] = doubleDrive[5];
                gamepadRecord[doubleDrive[0].intValue()][6] = doubleDrive[6];
                gamepadRecord[doubleDrive[0].intValue()][7] = doubleDrive[7];
                System.out.println("JESUS HELP ME");
                System.out.println(Arrays.toString(doubleDrive));


                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("driveset: completed file conversion!");

        Double[] lastValue = new Double[8];
        lastValue[0] = 0.0;
        lastValue[1] = 0.0;
        lastValue[2] = 0.0;
        lastValue[3] = 0.0;
        lastValue[4] = 0.0;
        lastValue[5] = 0.0;
        lastValue[6] = 0.0;
        lastValue[7] = 0.0;


        for (int i=0; i<=29999; i++){
            if(gamepadRecord[i][0]!=null) {
                lastValue = gamepadRecord[i];
            }
            gamepadTimeline[i]=lastValue;
        }

        System.out.println(Arrays.deepToString(gamepadTimeline));

        System.out.println("driveset: completed arrayifing!");

        /*driveCmd = new DriveCmd(drive, null, null,
                () -> driveSet[(int)(System.currentTimeMillis()-startTime)][1],
                () -> driveSet[(int)(System.currentTimeMillis()-startTime)][0],
                () -> driveSet[(int)(System.currentTimeMillis()-startTime)][2],
                () -> 0.0, () -> 0.0, () -> false);
    */}

    public void run(){
        if(startTime==-1){
            startTime=System.currentTimeMillis();
        }

        //schedule(driveCmd);
        drive.move(
                gamepadTimeline[
                (int)(System.currentTimeMillis()-startTime)][1],
                gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][2],
                gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][0]);


        if (gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][4] != 0) {
            armSub.moveArm(gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][4]);
        }

        lsSub.move(gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][5]*-1);

        if (gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][7] == 1) {
            gripperSub.setPosition(gripperSub.getPosition() - 0.05);
        } else if (gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][7] == -1) {
            gripperSub.setPosition(gripperSub.getPosition() + 0.05);
        }

        if (gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][6] == 1) {
            wristSub.setPos(wristSub.getPos() + 0.1);
        } else if (gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][6] == -1) {
            wristSub.setPos(wristSub.getPos() - 0.1);
        }

        //System.out.println(Arrays.toString(driveSet[(int) (System.currentTimeMillis() - startTime)]));

    }
}