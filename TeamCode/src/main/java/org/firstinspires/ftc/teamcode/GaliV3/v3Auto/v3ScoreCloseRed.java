package org.firstinspires.ftc.teamcode.GaliV3.v3Auto;


import static org.firstinspires.ftc.teamcode.GaliV3.v3Hardware.elbowNorminal;
import static org.firstinspires.ftc.teamcode.GaliV3.v3Hardware.shoulderPortScore;
import static org.firstinspires.ftc.teamcode.GaliV3.v3Hardware.shoulderStarScore;
import static org.firstinspires.ftc.teamcode.Vision.RedColorProcessor.centerRedRatio;
import static org.firstinspires.ftc.teamcode.Vision.RedColorProcessor.leftRedRatio;
import static org.firstinspires.ftc.teamcode.Vision.RedColorProcessor.rightRedRatio;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GaliV3.v3Hardware;
import org.firstinspires.ftc.teamcode.GaliV3.v3Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.GaliV3.v3Roadrunner.trajectorysequence.TrajectorySequence;

@Autonomous
public class v3ScoreCloseRed extends v3autoBase{
    Pose2d startPose = new Pose2d(0, 0, 0);
    double armTime = 0;
    double armTime2 = 0;
    String pos = "notSeen";
    @Override
    public void runOpMode() {
        super.runOpMode();
        cameraStartup("Webcam 1");
        propDetection("red");
        propPos("red", "close");
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        robot.door.setPosition(v3Hardware.doorClosed);

        TrajectorySequence center1 = drive.trajectorySequenceBuilder(startPose)
                .splineToLinearHeading(new Pose2d(-38, 10), Math.toRadians(0))
                .lineToLinearHeading(new Pose2d(-39, 10, Math.toRadians(-85)))
                .build();

        TrajectorySequence center2 = drive.trajectorySequenceBuilder(center1.end())
                //.lineToLinearHeading(new Pose2d(-38.5, -18, Math.toRadians(90)))
                .lineToLinearHeading(new Pose2d(-7, 36, Math.toRadians(-90)))
                .build();
        TrajectorySequence center3 = drive.trajectorySequenceBuilder(center2.end())
                .lineToLinearHeading(new Pose2d(-7, 37, Math.toRadians(-95)))
                .build();
        TrajectorySequence center4 = drive.trajectorySequenceBuilder(center3.end())
                .lineToLinearHeading(new Pose2d(0, 36, Math.toRadians(-90)))
                .build();
        TrajectorySequence right1 = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(-29, 4, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-29, -10, Math.toRadians(-90)))
                .lineToLinearHeading(new Pose2d(-29, -5, Math.toRadians(-90)))
                .build();

        TrajectorySequence right2 = drive.trajectorySequenceBuilder(right1.end())
                .lineToLinearHeading(new Pose2d(-19, 36, Math.toRadians(-90)))
                .build();
        TrajectorySequence right3 = drive.trajectorySequenceBuilder(right2.end())
                .lineToLinearHeading(new Pose2d(-19, 37, Math.toRadians(-95)))
                .build();
        TrajectorySequence right4 = drive.trajectorySequenceBuilder(right3.end())
                .lineToLinearHeading(new Pose2d(0, 36, Math.toRadians(-90)))
                .build();

        TrajectorySequence left1 = drive.trajectorySequenceBuilder(startPose)
                .splineToLinearHeading(new Pose2d(-19, 9), Math.toRadians(0))
                .lineToLinearHeading(new Pose2d(-20, 8, Math.toRadians(-165)))
                .build();

        TrajectorySequence left2 = drive.trajectorySequenceBuilder(left1.end())
                .lineToLinearHeading(new Pose2d(-13, 8, Math.toRadians(-165)))
                .lineToLinearHeading(new Pose2d(-5, 36, Math.toRadians(-90)))
                .build();
        TrajectorySequence left3 = drive.trajectorySequenceBuilder(left2.end())
                .lineToLinearHeading(new Pose2d(-5, 37, Math.toRadians(-95)))
                .build();
        TrajectorySequence left4 = drive.trajectorySequenceBuilder(left3.end())
                .lineToLinearHeading(new Pose2d(0, 36, Math.toRadians(-90)))
                .build();

        while(!opModeIsActive()) {
            telemetry.addData("position", pos);
            telemetry.addData("leftRed", leftRedRatio);
            telemetry.addData("centerRed", centerRedRatio);
            telemetry.addData("rightRed", rightRedRatio);
            pos = propPos("red", "close");
            telemetry.update();
        }
        waitForStart();
        if(propPos.equals("center")) {
            drive.followTrajectorySequence(center1);
            robot.intake.setPower(0);
            robot.flipper.setPosition(v3Hardware.flipDown);
            sleep(700);
            robot.intake.setPower(-0.2);
            sleep(400);
            robot.intake.setPower(0);
            robot.flipper.setPosition(v3Hardware.flipUp);
            sleep(200);
            scoreBack();
            sleep(600);
            robot.intake.setPower(0);
            sleep(150);
            scoreStar();
            robot.portArm.setPower(0.15);
            robot.starArm.setPower(0.05);
            drive.followTrajectorySequence(center2);
            robot.starArm.setPower(0);
            robot.portArm.setPower(0);
            drive.followTrajectorySequence(center3);
            robot.door.setPosition(v3Hardware.doorOpen);
            resetRuntime();
            while (getRuntime() < 0.3) {
                robot.portArm.setPower(1);
                robot.starArm.setPower(1);
            }
            robot.portArm.setPower(0);
            robot.starArm.setPower(0);
            robot.shoulderStar.setPosition(shoulderStarScore - 0.04);
            robot.shoulderPort.setPosition(shoulderPortScore + 0.04);
            robot.elbow.setPosition(elbowNorminal);
            drive.followTrajectorySequence(center4);
        }
        else if(propPos.equals("right")){
            drive.followTrajectorySequence(left1);
            robot.intake.setPower(0);
            robot.flipper.setPosition(v3Hardware.flipDown);
            sleep(700);
            robot.intake.setPower(-0.2);
            sleep(400);
            robot.intake.setPower(0);
            robot.flipper.setPosition(v3Hardware.flipUp);
            sleep(200);
            scoreBack();
            sleep(600);
            robot.portArm.setPower(0.15);
            robot.starArm.setPower(0.07);
            drive.followTrajectorySequence(left2);
            robot.starArm.setPower(0);
            robot.portArm.setPower(0);
            drive.followTrajectorySequence(left3);
            robot.door.setPosition(v3Hardware.doorOpen);
            armTime2 = getRuntime();
            while (armTime < 0.3) {
                armTime = getRuntime() - armTime2;
                robot.portArm.setPower(1);
                robot.starArm.setPower(1);

            }
            robot.portArm.setPower(0);
            robot.starArm.setPower(0);
            robot.shoulderStar.setPosition(shoulderStarScore - 0.04);
            robot.shoulderPort.setPosition(shoulderPortScore + 0.04);
            robot.elbow.setPosition(elbowNorminal);
            telemetry.addData("Runtime", getRuntime()+1);
            telemetry.update();
            drive.followTrajectorySequence(left4);
        }
        else{
            drive.followTrajectorySequence(right1);
            robot.intake.setPower(0);
            robot.flipper.setPosition(v3Hardware.flipDown);
            sleep(700);
            robot.intake.setPower(-0.2);
            sleep(400);
            robot.intake.setPower(0);
            robot.flipper.setPosition(v3Hardware.flipUp);
            sleep(200);
            sleep(200);
            scoreBack();
            sleep(600);
            robot.intake.setPower(0);
            sleep(150);
            robot.portArm.setPower(0.15);
            robot.starArm.setPower(0.07);
            drive.followTrajectorySequence(right2);
            robot.starArm.setPower(0);
            robot.portArm.setPower(0);
            drive.followTrajectorySequence(right3);
            robot.door.setPosition(v3Hardware.doorOpen);
            armTime2 = getRuntime();
            while (armTime < 0.3) {
                armTime = getRuntime() - armTime2;
                robot.portArm.setPower(1);
                robot.starArm.setPower(1);

            }
            robot.portArm.setPower(0);
            robot.starArm.setPower(0);
            robot.shoulderStar.setPosition(shoulderStarScore - 0.04);
            robot.shoulderPort.setPosition(shoulderPortScore + 0.04);
            robot.elbow.setPosition(elbowNorminal);
            drive.followTrajectorySequence(right4);
        }
        resetArm();
    }
}
