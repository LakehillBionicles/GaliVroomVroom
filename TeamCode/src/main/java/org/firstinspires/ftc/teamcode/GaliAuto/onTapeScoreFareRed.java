package org.firstinspires.ftc.teamcode.GaliAuto;

import static org.firstinspires.ftc.teamcode.GaliHardware.elbowDown;
import static org.firstinspires.ftc.teamcode.GaliHardware.elbowScore;
import static org.firstinspires.ftc.teamcode.GaliHardware.elbowTape;
import static org.firstinspires.ftc.teamcode.GaliHardware.fingerPortClosed;
import static org.firstinspires.ftc.teamcode.GaliHardware.fingerPortOpen;
import static org.firstinspires.ftc.teamcode.GaliHardware.fingerStarClosed;
import static org.firstinspires.ftc.teamcode.GaliHardware.fingerStarOpen;
import static org.firstinspires.ftc.teamcode.GaliHardware.wristDown;
import static org.firstinspires.ftc.teamcode.GaliHardware.wristScore;
import static org.firstinspires.ftc.teamcode.GaliHardware.wristTape;
import static org.firstinspires.ftc.teamcode.Vision.RedColorProcessor.centerRedRatio;
import static org.firstinspires.ftc.teamcode.Vision.RedColorProcessor.pos;
import static org.firstinspires.ftc.teamcode.Vision.RedColorProcessor.rightRedRatio;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.GaliV3.v3Roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.GaliV3.v3Roadrunner.trajectorysequence.TrajectorySequence;

import java.util.Objects;
@Disabled
@Autonomous


public class onTapeScoreFareRed extends GaliAutobase{
    Pose2d startPose = new Pose2d(0, 0, 0);
    public static double forWard = 0;
    public static double turn1 = 0;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        robot.fingerPort.setPosition(fingerPortClosed);
        robot.fingerStar.setPosition(fingerStarClosed);

        cameraStartup("Webcam 1");
        propDetection("red");

        TrajectorySequence center1 = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(-27, -15, Math.toRadians(0)))
                .lineToLinearHeading(new Pose2d(-40, -15, Math.toRadians(182)))
                .lineToLinearHeading(new Pose2d(-40, -3, Math.toRadians(182)))
                .lineToLinearHeading(new Pose2d(-52, -3, Math.toRadians(182)))
                .build();
        TrajectorySequence center2 = drive.trajectorySequenceBuilder(center1.end())
                .lineToLinearHeading(new Pose2d(-60, -3, Math.toRadians(274)))
                .lineToLinearHeading(new Pose2d(-60, 70, Math.toRadians(274)))
                .lineToLinearHeading(new Pose2d(-34, 86, Math.toRadians(274)))
                .build();

        TrajectorySequence left1 = drive.trajectorySequenceBuilder(startPose)
                .lineTo(new Vector2d(-29, 0))
                .turn(Math.toRadians(105))
                .lineToLinearHeading(new Pose2d(-27, -15, Math.toRadians(100)))
                .lineToLinearHeading(new Pose2d(-27, 3, Math.toRadians(92)))
                .build();
        TrajectorySequence left2 = drive.trajectorySequenceBuilder(left1.end())
                .lineToLinearHeading(new Pose2d(-27, 6, Math.toRadians(92)))
                .lineToLinearHeading(new Pose2d(-27, 5, Math.toRadians(-92)))
                .lineToLinearHeading(new Pose2d(-55, 5, Math.toRadians(-89)))
                .lineToLinearHeading(new Pose2d(-55, 70, Math.toRadians(-89)))
                .lineToLinearHeading(new Pose2d(-40, 87, Math.toRadians(-89)))
                .build();

        TrajectorySequence right1 = drive.trajectorySequenceBuilder(startPose)
                .lineToLinearHeading(new Pose2d(-27, 0, Math.toRadians(0)))
                .turn(Math.toRadians(-105))
                .lineToLinearHeading(new Pose2d(-27, 15, Math.toRadians(-100)))
                .lineToLinearHeading(new Pose2d(-27, 2, Math.toRadians(-92)))
                .build();

        TrajectorySequence right2 = drive.trajectorySequenceBuilder(right1.end())
                .lineToLinearHeading(new Pose2d(-27, 0, Math.toRadians(-92)))
                .lineToLinearHeading(new Pose2d(-55, 0, Math.toRadians(-89)))
                .lineToLinearHeading(new Pose2d(-55, 70, Math.toRadians(-89)))
                .lineToLinearHeading(new Pose2d(-22, 86, Math.toRadians(-89)))
                .build();



        while(!isStarted()) {
            telemetry.addData("position", pos);
            telemetry.addData("centerBlue", centerRedRatio);
            telemetry.addData("rightBlue", rightRedRatio);
            telemetry.update();
        }
        telemetry.addData("position", pos);
        telemetry.addData("centerBlue", centerRedRatio);
        telemetry.addData("rightBlue", rightRedRatio);
        telemetry.update();
        waitForStart();
        if(Objects.equals(pos, "center")) {
            drive.followTrajectorySequence(center1);
            robot.wrist.setPosition(wristTape);
            robot.elbow.setPosition(elbowTape);
            sleep(3000);
            robot.fingerPort.setPosition(fingerPortOpen);
            sleep(3000);
            robot.wrist.setPosition(wristDown);
            robot.elbow.setPosition(elbowDown);
            sleep(1000);
            drive.followTrajectorySequence(center2);
            robot.wrist.setPosition(wristScore);
            robot.elbow.setPosition(elbowScore);
            sleep(3000);
            robot.fingerStar.setPosition(fingerStarOpen);
            sleep(2000);
            robot.elbow.setPosition(elbowDown);
            robot.fingerPort.setPosition(fingerPortOpen);
            robot.fingerStar.setPosition(fingerStarOpen);
            robot.wrist.setPosition(wristDown);
            sleep(3000);
        }else if(Objects.equals(pos, "left")){
            drive.followTrajectorySequence(left1);
            robot.wrist.setPosition(wristTape);
            robot.elbow.setPosition(elbowTape);
            sleep(3000);
            robot.fingerPort.setPosition(fingerPortOpen);
            sleep(3000);
            robot.wrist.setPosition(wristDown);
            robot.elbow.setPosition(elbowDown);
            sleep(1000);
            drive.followTrajectorySequence(left2);
            sleep(1500);
            robot.elbow.setPosition(elbowScore);
            robot.wrist.setPosition(wristScore);
            sleep(3000);
            robot.fingerStar.setPosition(fingerStarOpen);
            sleep(2000);
            robot.elbow.setPosition(elbowDown);
            robot.wrist.setPosition(wristDown);
            sleep(3000);

        }else{
            drive.followTrajectorySequence(right1);
            robot.wrist.setPosition(wristTape);
            robot.elbow.setPosition(elbowTape);
            sleep(3000);
            robot.fingerPort.setPosition(fingerPortOpen);
            sleep(3000);
            robot.wrist.setPosition(wristDown);
            robot.elbow.setPosition(elbowDown);
            sleep(1000);
            drive.followTrajectorySequence(right2);
            robot.wrist.setPosition(wristScore);
            robot.elbow.setPosition(elbowScore);
            sleep(3000);
            robot.fingerStar.setPosition(fingerStarOpen);
            sleep(2000);
            robot.wrist.setPosition(wristDown);
            robot.elbow.setPosition(elbowDown);
            robot.fingerStar.setPosition(fingerStarOpen);
            robot.fingerPort.setPosition(fingerPortOpen);
            }
        }
    }
