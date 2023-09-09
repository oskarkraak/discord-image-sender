package com.oskarkraak.discordimagesender;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

public class DiscordWebhook {
    final String webhookUrl;

    public DiscordWebhook(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public void sendText(String text) throws IOException {
        String json = """
                {
                    "content": "%s",
                    "embeds": null,
                    "attachments": []
                }
                """.formatted(text);
        sendPostRequest(json);
    }

    public void sendPostRequest(String json) throws IOException {
        URL url = new URL(webhookUrl);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "discord-image-sender");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        OutputStream stream = connection.getOutputStream();
        stream.write(json.getBytes());
        stream.flush();
        stream.close();
        connection.getInputStream();
        connection.disconnect();
    }
}
