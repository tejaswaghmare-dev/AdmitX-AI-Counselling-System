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
                        "https://openrouter.ai/api/v1/chat/completions";

        private static final String MODEL =
                        "openrouter/free";

        private static final HttpClient client =
                        HttpClient.newHttpClient();

        private static final String FALLBACK_MESSAGE =
                        "I'm your CAP Round Counsellor, here to help with MHT CET and engineering college counselling.";

        public static String askAI(String userMessage) {

                try {

                        
                        String apiKey =
                                  System.getenv("OPENROUTER_API_KEY");

                        if (apiKey == null
                                        || apiKey.isBlank()
                                        || apiKey.equals(
                                                        "PASTE_YOUR_OPENROUTER_API_KEY_HERE")) {

                                return "OpenRouter API key is not configured.";
                        }

                        String instructions =
                                        """
                                                        You are AdmitX Assistant, a specialized MHT CET CAP Round Counsellor.

                                                        YOUR ONLY PURPOSE:
                                                        Help students with engineering education, engineering colleges,
                                                        MHT CET and CAP counselling.

                                                        ALLOWED QUESTIONS:

                                                        1. MHT CET
                                                        2. MHT CET CAP counselling
                                                        3. Engineering colleges
                                                        4. Engineering colleges by location
                                                           - Pune
                                                           - Mumbai
                                                           - Nashik
                                                           - Nagpur
                                                           - Aurangabad
                                                           - Kolhapur
                                                           - or any other location
                                                        5. Engineering courses and branches
                                                        6. CSE, IT, Mechanical, Civil, E&TC, Electrical, AI/ML, etc.
                                                        7. College selection
                                                        8. College comparison
                                                        9. College preferences
                                                        10. CAP Round 1, Round 2, Round 3 and later rounds
                                                        11. Merit lists
                                                        12. MHT CET ranks and percentiles
                                                        13. College cutoffs
                                                        14. Seat allotment
                                                        15. Freeze
                                                        16. Betterment
                                                        17. Admission process
                                                        18. Eligibility
                                                        19. Required documents
                                                        20. Applications
                                                        21. Grievances
                                                        22. Engineering admission fees
                                                        23. Engineering college placements
                                                        24. Maharashtra engineering admission
                                                        25. Indian engineering education related specifically to engineering

                                                        IMPORTANT:

                                                        Questions like:
                                                        "engineering colleges in Pune"
                                                        "best engineering colleges in Pune"
                                                        "engineering colleges near me"
                                                        "CSE colleges in Pune"
                                                        "engineering colleges for 90 percentile"
                                                        "which college should I choose"
                                                        "CAP Round 2 information"
                                                        "what is betterment"
                                                        "COEP cutoff"
                                                        "engineering colleges with good placements"

                                                        ARE ALLOWED.

                                                        For allowed questions:
                                                        - Answer normally.
                                                        - Keep the answer short, simple and clear.
                                                        - Help the student make an informed college/counselling decision.
                                                        - Do not unnecessarily reject an engineering-related question.

                                                        OUT-OF-SCOPE QUESTIONS:

                                                        If the question is completely unrelated to:
                                                        - engineering
                                                        - engineering colleges
                                                        - MHT CET
                                                        - CAP counselling
                                                        - engineering admissions
                                                        - engineering education

                                                        FORMATTING RULES:

                                                        - Do not use Markdown bold formatting.
                                                        - Never use ** anywhere in the response.
                                                        - Do not use double asterisks for emphasis.
                                                        - Use plain text only.
                                                        - You may use simple bullet points using • or -.
                                                        - Keep responses clean and easy to read.

                                                        DO NOT answer the question.

                                                        For every unrelated question, respond EXACTLY with:

                                                        I'm your CAP Round Counsellor, here to help with MHT CET and engineering college counselling.

                                                        Do not add anything before or after that message.

                                                        If a question contains both an engineering/counselling topic and an unrelated topic,
                                                        answer ONLY the engineering/counselling part.
                                                        """;

                        JsonObject requestBody =
                                        new JsonObject();

                        requestBody.addProperty(
                                        "model",
                                        MODEL);

                        JsonArray messages =
                                        new JsonArray();

                        JsonObject systemMessage =
                                        new JsonObject();

                        systemMessage.addProperty(
                                        "role",
                                        "system");

                        systemMessage.addProperty(
                                        "content",
                                        instructions);

                        JsonObject userMessageObject =
                                        new JsonObject();

                        userMessageObject.addProperty(
                                        "role",
                                        "user");

                        userMessageObject.addProperty(
                                        "content",
                                        userMessage);

                        messages.add(
                                        systemMessage);

                        messages.add(
                                        userMessageObject);

                        requestBody.add(
                                        "messages",
                                        messages);

                        String body =
                                        requestBody.toString();

                        HttpRequest request =
                                        HttpRequest.newBuilder()
                                                        .uri(
                                                                        URI.create(
                                                                                        API_URL))
                                                        .header(
                                                                        "Authorization",
                                                                        "Bearer " + apiKey)
                                                        .header(
                                                                        "Content-Type",
                                                                        "application/json")
                                                        .header(
                                                                        "X-Title",
                                                                        "AdmitX")
                                                        .POST(
                                                                        HttpRequest.BodyPublishers.ofString(
                                                                                        body))
                                                        .build();

                        HttpResponse<String> response =
                                        client.send(
                                                        request,
                                                        HttpResponse.BodyHandlers.ofString());

                        System.out.println(
                                        "OPENROUTER STATUS CODE: "
                                                        + response.statusCode());

                        System.out.println(
                                        "OPENROUTER RESPONSE:");

                        System.out.println(
                                        response.body());

                        if (response.statusCode() < 200
                                        || response.statusCode() >= 300) {

                                return getApiError(
                                                response.statusCode(),
                                                response.body());
                        }

                        return extractAnswer(
                                        response.body());

                } catch (InterruptedException e) {

                        Thread.currentThread()
                                        .interrupt();

                        return "The AI request was interrupted. Please try again.";

                } catch (Exception e) {

                        e.printStackTrace();

                        return "Something went wrong while contacting the AI.";
                }
        }

        private static String getApiError(
                        int statusCode,
                        String body) {

                String message =
                                extractErrorMessage(
                                                body);

                if (statusCode == 400) {

                        return message == null
                                        ? "OpenRouter rejected the request."
                                        : "OpenRouter rejected the request: "
                                                        + message;
                }

                if (statusCode == 401) {

                        return "Invalid OpenRouter API key.";
                }

                if (statusCode == 402) {

                        return "OpenRouter credits or quota are not available.";
                }

                if (statusCode == 403) {

                        return "OpenRouter API permission denied.";
                }

                if (statusCode == 429) {

                        return "OpenRouter rate limit reached. Please try again shortly.";
                }

                if (statusCode >= 500) {

                        return "OpenRouter is temporarily unavailable. Please try again.";
                }

                return message == null
                                ? "AI request failed. Status: "
                                                + statusCode
                                : "AI request failed: "
                                                + message;
        }

        private static String extractErrorMessage(
                        String json) {

                try {

                        JsonObject root =
                                        JsonParser
                                                        .parseString(
                                                                        json)
                                                        .getAsJsonObject();

                        if (root.has(
                                        "error")
                                        && root.get(
                                                        "error")
                                                        .isJsonObject()) {

                                JsonObject error =
                                                root.getAsJsonObject(
                                                                "error");

                                if (error.has(
                                                "message")
                                                && !error.get(
                                                                "message")
                                                                .isJsonNull()) {

                                        return error.get(
                                                        "message")
                                                        .getAsString();
                                }
                        }

                } catch (Exception ignored) {
                }

                return null;
        }

        private static String extractAnswer(
                        String json) {

                try {

                        JsonObject root =
                                        JsonParser
                                                        .parseString(
                                                                        json)
                                                        .getAsJsonObject();

                        JsonArray choices =
                                        root.getAsJsonArray(
                                                        "choices");

                        if (choices == null
                                        || choices.isEmpty()) {

                                return "AI returned an empty response.";
                        }

                        JsonObject firstChoice =
                                        choices.get(0)
                                                        .getAsJsonObject();

                        if (!firstChoice.has(
                                        "message")) {

                                return "AI response contained no message.";
                        }

                        JsonObject message =
                                        firstChoice.getAsJsonObject(
                                                        "message");

                        if (!message.has(
                                        "content")
                                        || message.get(
                                                        "content")
                                                        .isJsonNull()) {

                                return "AI response contained no readable text.";
                        }

                        String content =
                                        message.get(
                                                        "content")
                                                        .getAsString();

                        if (content == null
                                        || content.isBlank()) {

                                return "AI returned an empty response.";
                        }

                        return content.trim();

                } catch (Exception e) {

                        e.printStackTrace();

                        return "Could not read the AI response.";
                }
        }
}