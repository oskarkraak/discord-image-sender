package com.oskarkraak.discordimagesender.cdk;

import software.amazon.awscdk.App;
import software.amazon.awscdk.services.events.Schedule;
import software.amazon.awscdk.services.lambda.Code;

public class ImageSenderApp {
    public static void main(final String[] args) {
        App app = new App();
        Schedule imageSendingSchedule = Schedule.expression("* * * * ?"); // TODO schedule
        Code lambdaCode = Code.fromAsset("../../"); // TODO code path
        String handler = "package::Handler"; // TODO handler
        new ScheduledLambdaStack(app, "id", lambdaCode, handler, imageSendingSchedule); // TODO id
        app.synth();
    }
}