package org.firstinspires.ftc.teamcode;

public final class Constants {
    public static final class DriveConstants{
        public static final double driveSpeed = 0.5;
        public static final double driveSlowSpeed = 0.2;
        public static final double driveMaxSpeed = 0.6;
        public static final boolean doINeedMoreConstants = true;
        public static final double wheelDiameter = 96/25.4;
    }
    public static final class  WristConstants{
        public static final double wristLeft = 0.03;
        public static final double wristCenter = 0.375;
        public static final double wristRight = 0.75;
//        public static final double wristStart = -0.3;
        public static final boolean thereIGaveYouMoreConstants = true;
    }
    public static final class LinearSlideConstants {
        public static final double upwardLimit = 2400;
        public static final double downwardLimit = 0;
        public static final double lsLowGoalConstant = 2000;
        public static final int lsSpeci = 1600;
    }

    public static final class ArmConstants {
        public static final int armLow = 50;
        public static final int armMed = 150;
        public static final int armLowGoal = 530;
        public static final int armHighGoal = 600; //TODO: Need to determine correct value.
    }
}
