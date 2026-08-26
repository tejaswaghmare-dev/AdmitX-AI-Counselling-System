package com.admitx.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OpenAIService {

    private static final String API_URL =
            "https://api.openai.com/v1/responses";

    private static final HttpClient client =
            HttpClient.newHttpClient();

    public static String askAI(String userMessage) {

        try {

            String apiKey = System.getenv("OPen api-key:");

            if (apiKey == null || apiKey.isBlank()) {
                return "OpenAI API key is not configured.";
            }

            String safeMessage =
                    userMessage
                            .replace("\\", "\\\\")
                            .replace("\"", "\\\"")
                            .replace("\n", "\\n");

            String body = """
                    {
                      "model": "gpt-5.4-mini",
                      "instructions": "You are AdmitX Assistant. Help students with MHT CET CAP counselling. Keep answers short, simple and clear. Help with applications, documents, merit lists, grievances, college preferences, CAP rounds, freeze, betterment and admission.",
                      "input": "%s"
                    }
                    """.formatted(safeMessage);

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(API_URL))
                            .header(
                                    "Authorization",
                                    "Bearer " + apiKey
                            )
                            .header(
                                    "Content-Type",
                                    "application/json"
                            )
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(body)
                            )
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "STATUS CODE: "
                            + response.statusCode()
            );

            System.out.println(
                    "OPENAI RESPONSE:"
            );

            System.out.println(
                    response.body()
            );

            if (response.statusCode() < 200
                    || response.statusCode() >= 300) {

                return "AI request failed. Status: "
                        + response.statusCode();
            }

            return extractAnswer(
                    response.body()
            );

        } catch (Exception e) {

            e.printStackTrace();

            return "Something went wrong while contacting the AI.";
        }
    }

    private static String extractAnswer(
            String json
    ) {

        try {

            JsonObject root =
                    JsonParser
                            .parseString(json)
                            .getAsJsonObject();

            JsonArray output =
                    root.getAsJsonArray(
                            "output"
                    );

            if (output == null
                    || output.isEmpty()) {

                return "AI returned an empty response.";
            }

            for (int i = 0;
                 i < output.size();
                 i++) {

                JsonObject outputItem =
                        output.get(i)
                                .getAsJsonObject();

                if (!outputItem.has("content")) {
                    continue;
                }

                JsonArray content =
                        outputItem.getAsJsonArray(
                                "content"
                        );

                for (int j = 0;
                     j < content.size();
                     j++) {

                    JsonObject contentItem =
                            content.get(j)
                                    .getAsJsonObject();

                    if (contentItem.has("text")) {

                        return contentItem
                                .get("text")
                                .getAsString();
                    }
                }
            }

            return "AI response contained no readable text.";

        } catch (Exception e) {

            e.printStackTrace();

            return "Could not read the AI response.";
        }
    }
}