package com.oskarkraak.discordimagesender;

import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;
import java.net.URL;

public class Main {
    public static final String IMAGE_URL = "Empty";
    public static final String WEBHOOK_URL = "Empty";

    public static void main(String[] args) throws Exception {
        URL url = new URL(WEBHOOK_URL);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.addRequestProperty("Content-Type", "application/json");
        connection.addRequestProperty("User-Agent", "discord-image-sender");
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        String json = """
                {
                  "content": "%s",
                   "embeds": null,
                   "attachments": []
                }
                """.formatted(IMAGE_URL);
        OutputStream stream = connection.getOutputStream();
        stream.write(json.getBytes());
        stream.flush();
        stream.close();
        System.out.println( connection.getInputStream());
        connection.disconnect();
    }
}