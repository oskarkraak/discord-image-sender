package com.oskarkraak.discordimagesender;

import java.io.IOException;
import java.net.MalformedURLException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class ImageSenderLambda implements RequestHandler<Void, String> {
    @Override
    public String handleRequest(Void input, Context context) {
        String webhookUrl = System.getenv("WEBHOOK_URL");
        String imageUrl = System.getenv("IMAGE_URL");
        DiscordWebhook webhook = new DiscordWebhook(webhookUrl);
        try {
            webhook.sendText(imageUrl);
        } catch (MalformedURLException e) {
            return "Image URL is malformed: \"" + imageUrl + "\"";
        } catch (IOException e) {
            return "Could not send a POST request to webhook: \"" + webhookUrl + "\"";
        }
        return "Image posted successfully";
    }
}
