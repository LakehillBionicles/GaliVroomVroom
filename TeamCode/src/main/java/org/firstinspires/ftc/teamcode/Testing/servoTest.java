package org.firstinspires.ftc.teamcode.Testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp
public class servoTest extends OpMode{

    public static double servoPosition = 1;
    public Servo pincer = null;

    @Override
    public void init() {
        pincer = hardwareMap.get(Servo.class,"pincer");
    }

    @Override
    public void loop() {
            pincer.setPosition(servoPosition);
    }
}
