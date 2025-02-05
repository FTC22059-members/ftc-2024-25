package org.firstinspires.ftc.teamcode;

import android.os.Environment;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.subsystems.DrivetrainSub;

import org.firstinspires.ftc.teamcode.commands.DriveCmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Autonomous(name = "Autonomous Record")
@Disabled
public class AutoRecord extends CommandOpMode {

    private BufferedReader reader;
    private DriveCmd driveCmd;
    private String line = "";
    private Double[] doubleDrive;
    private Double[][] driveSet;
    private long startTime;
    private int i;
    private int previousIndex;
    private DrivetrainSub drive;

    @Override
    public void initialize() {
        long startTime = System.currentTimeMillis();

        drive = new DrivetrainSub(hardwareMap, telemetry);
    }

    public void execute(){
        try {
            reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getPath()+"/AutoLogs/1737498911.txt"));
            line = reader.readLine();

            while (line != null) {

                //get rid of square brackets for easier parsing
                line = line.replace("[","");
                line = line.replace("]","");

                //make a line into an array
                String[] stringDrive = line.split("[,]");

                //convert strings to doubles
                for (int j = 0; j < 4; j++){
                    doubleDrive[i]=Double.parseDouble(stringDrive[i]);
                }

                for (int i = previousIndex; i < doubleDrive[0].intValue()+1; i++){
                    driveSet[i][0]=doubleDrive[1];
                    driveSet[i][1]=doubleDrive[2];
                    driveSet[i][2]=doubleDrive[3];
                }

                previousIndex=doubleDrive[0].intValue();

                // read next line
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        driveCmd = new DriveCmd(drive, null, null, () -> driveSet[(int) System.currentTimeMillis()][1], () -> driveSet[(int) System.currentTimeMillis()][0], () -> driveSet[(int) System.currentTimeMillis()][2], () -> 0.0, () -> 0.0, () -> false);

    }
}