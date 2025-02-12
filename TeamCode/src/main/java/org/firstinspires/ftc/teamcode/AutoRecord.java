package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

import org.firstinspires.ftc.teamcode.commands.DriveCmd;

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

    @Override
    public void initialize() {
        startTime = -1;

        doubleDrive = new Double[4];
        gamepadRecord = new Double[30000][4];
        gamepadTimeline = new Double[30000][4];
        drive = new DrivetrainSub(hardwareMap, telemetry);

        try {
            reader = new BufferedReader(new FileReader("/storage/self/primary/AutoLogs/1737499403.txt"));
            line = reader.readLine();
            boolean endLoop = false;

            while (!Objects.equals(line, "STOP")) {

                //get rid of square brackets for easier parsing
                line = line.replace(" ","");
                line = line.replace("[","");
                line = line.replace("]","");

                //make a line into an array
                String[] stringDrive = line.split("[,]");

                //convert strings to doubles
                for (int j = 0; j <= 3; j++){
                    try {
                        doubleDrive[j] = Double.parseDouble(stringDrive[j]);
                    }catch (ArrayIndexOutOfBoundsException e){
                        endLoop=true;
                        break;
                    }
                }

                if (endLoop){
                    break;
                }

                gamepadRecord[doubleDrive[0].intValue()][0] = doubleDrive[1];
                gamepadRecord[doubleDrive[0].intValue()][1] = doubleDrive[2];
                gamepadRecord[doubleDrive[0].intValue()][2] = doubleDrive[3]*-1;
                gamepadRecord[doubleDrive[0].intValue()][3] = doubleDrive[0];

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("driveset: completed file conversion!");

        Double[] lastValue = new Double[4];

        for (int i=0; i<=29999; i++){
            if(gamepadRecord[i][0]!=null) {
                lastValue = gamepadRecord[i];
            }
            gamepadTimeline[i]=lastValue;
        }

        System.out.println(Arrays.deepToString(gamepadTimeline));

        System.out.println("driveset: completed arrayifing!");
    }

    public void run(){
        if(startTime==-1){
            startTime=System.currentTimeMillis();
        }

        drive.move(gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][1],
                gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][2],
                gamepadTimeline[(int)(System.currentTimeMillis()-startTime)][0]);

    }
}