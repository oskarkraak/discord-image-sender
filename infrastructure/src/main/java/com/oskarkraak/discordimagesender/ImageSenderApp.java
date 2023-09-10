package com.oskarkraak.discordimagesender;

import software.amazon.awscdk.App;
import software.amazon.awscdk.services.events.Schedule;
import software.amazon.awscdk.services.lambda.Code;

import java.util.Map;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageSenderApp {
    public static void main(final String[] args) {
        Properties config = readProperties("../CONFIG.properties");
        App app = new App();
        Schedule imageSendingSchedule =
                Schedule.expression("cron(" + config.getProperty("schedule.cron.expression") + ")");
        Code lambdaCode = Code.fromAsset("../software/build/libs/software-1.0.jar");
        String handler = "com.oskarkraak.discordimagesender.ImageSenderLambda::handleRequest";
        Map<String, String> environment = Map.of(
                "WEBHOOK_URL", config.getProperty("discord.webhook.url"),
                "IMAGE_URL", config.getProperty("image.url")
        );
        new ScheduledLambdaStack(app, config.getProperty("stack.name"), lambdaCode, handler, imageSendingSchedule,
                environment);
        app.synth();
    }

    private static Properties readProperties(String path) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(path)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Could not read properties " + path, e);
        }
        return properties;
    }
}