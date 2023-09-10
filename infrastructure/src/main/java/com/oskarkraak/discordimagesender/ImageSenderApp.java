package com.oskarkraak.discordimagesender;

import software.amazon.awscdk.App;
import software.amazon.awscdk.services.events.Schedule;
import software.amazon.awscdk.services.lambda.Code;

import java.util.Map;

public class ImageSenderApp {
    public static void main(final String[] args) {
        App app = new App();
        Schedule imageSendingSchedule = Schedule.expression("* * * * ?"); // TODO schedule
        Code lambdaCode = Code.fromAsset("../../"); // TODO code path
        String handler = "package::Handler"; // TODO handler
        Map<String, String> environment = Map.of(); // TODO env
        new ScheduledLambdaStack(app, "id", lambdaCode, handler, imageSendingSchedule, environment); // TODO id
        app.synth();
    }
}