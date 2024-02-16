package org.firstinspires.ftc.teamcode.GaliV3.v3Tele;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.GaliV3.v3Hardware;

@Config
@TeleOp
public class findShoulderPosition extends LinearOpMode {
    public v3Hardware robot = new v3Hardware();
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();
    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();
    public double drivePower = 1;
    boolean intakeOn = false, intakeSpit = false;
    double doorTimer = -2;
    double extendyBoiTimerExtend = -2;
    double extendyBoiTimeRetract = -2;
    double wristTimerDown = -2;

    double shoulderTimerDown = -2;

    double armDownTimer = -2;

    String doorPos = "closed";
    double elbowStarTimer = -3;
    double elbowPortTimer = -3;
    double wristLiftTimer =-3;
    String armPos = "down";
    public static double shoulderPos = 0.5;
    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        //robot.door.setPosition(v3Hardware.doorClosed);
        //robot.extendyBoi.setPosition(v3Hardware.extendyBoiDown);
        //robot.elbow.setPosition(v3Hardware.elbowNorminal);
        //robot.shoulderStar.setPosition(v3Hardware.shoulderStarDown);
        //robot.shoulderStar.setPosition(shoulderPos);
        //robot.wrist.setPosition(v3Hardware.wristDown);
        robot.shoulderPort.setPosition(shoulderPos);
        waitForStart();
        while (opModeIsActive()) {
            robot.shoulderPort.setPosition(shoulderPos);
        }
    }
}
