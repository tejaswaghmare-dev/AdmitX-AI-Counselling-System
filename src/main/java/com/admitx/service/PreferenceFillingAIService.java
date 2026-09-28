package com.admitx.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class PreferenceFillingAIService {

        private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";

        private static final String DEFAULT_MODEL = "openrouter/free";

        private static final String OPENROUTER_API_KEY =
        System.getenv("OPENROUTER_API_KEY");

        private static final HttpClient CLIENT = HttpClient.newBuilder()
                        .connectTimeout(
                                        Duration.ofSeconds(20))
                        .build();

        private static final String INSTRUCTIONS = """
                        You are AdmitX AI Preference Analyzer for MHT CET CAP engineering counselling.

                        Analyze the student's CET percentile, category, selected colleges,
                        branches and available cutoff data.

                        Give each selected college a CHOICE SCORE from 0 to 100.

                        The CHOICE SCORE should represent how good that college and branch
                        choice is for the student based on:
                        - CET percentile
                        - category
                        - available cutoff
                        - college
                        - branch
                        - admission possibility

                        IMPORTANT:
                        Show ONLY the selected colleges and their CHOICE SCORE.

                        Do NOT provide:
                        - analysis
                        - classification
                        - reason
                        - admission assessment
                        - risk level
                        - missing choices
                        - suggestions
                        - counselling advice
                        - explanations
                        - headings

                        Do not add or remove colleges.
                        Do not invent cutoff data.
                        Never guarantee admission.

                        OUTPUT FORMAT:

                        1. College Name - Branch
                        CHOICE_SCORE: 92

                        2. College Name - Branch
                        CHOICE_SCORE: 85

                        3. College Name - Branch
                        CHOICE_SCORE: 78

                        AI_ORDER: 1,2,3

                        FORMATTING RULES:
                        - Follow the output format exactly.
                        - Plain text only.
                        - Never use Markdown.
                        - Never use **.
                        - Never use *.
                        - Never use #.
                        - Never use headings.
                        - Never use bold text.
                        - CHOICE_SCORE must be between 0 and 100.
                        - AI_ORDER must use the ORIGINAL preference numbers.
                        - Include every preference exactly once in AI_ORDER.
                        - AI_ORDER must be the final line.
                        - Do not write anything after AI_ORDER.
                        """;

        private PreferenceFillingAIService() {
        }

        public static String analyzePreferences(
                        String preferenceData) {

                if (preferenceData == null ||
                                preferenceData.isBlank()) {

                        throw new IllegalArgumentException(
                                        "Preference data is empty.");
                }

                String apiKey = getApiKey();

                String model = getModel();

                try {

                        JsonObject requestBody = new JsonObject();

                        requestBody.addProperty(
                                        "model",
                                        model);

                        JsonArray messages = new JsonArray();

                        JsonObject systemMessage = new JsonObject();

                        systemMessage.addProperty(
                                        "role",
                                        "system");

                        systemMessage.addProperty(
                                        "content",
                                        INSTRUCTIONS);

                        messages.add(
                                        systemMessage);

                        JsonObject userMessage = new JsonObject();

                        userMessage.addProperty(
                                        "role",
                                        "user");

                        userMessage.addProperty(
                                        "content",
                                        preferenceData.trim());

                        messages.add(
                                        userMessage);

                        requestBody.add(
                                        "messages",
                                        messages);

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(
                                                        URI.create(
                                                                        API_URL))
                                        .timeout(
                                                        Duration.ofSeconds(
                                                                        90))
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
                                                                        requestBody.toString()))
                                        .build();

                        HttpResponse<String> response = CLIENT.send(
                                        request,
                                        HttpResponse.BodyHandlers.ofString());

                        System.out.println(
                                        "OPENROUTER PREFERENCE STATUS: "
                                                        + response.statusCode());

                        if (response.statusCode() < 200 ||
                                        response.statusCode() >= 300) {

                                throw createApiException(
                                                response.statusCode(),
                                                response.body());
                        }

                        return extractAnswer(
                                        response.body());

                } catch (IllegalStateException e) {

                        throw e;

                } catch (InterruptedException e) {

                        Thread.currentThread()
                                        .interrupt();

                        throw new IllegalStateException(
                                        "Preference analysis was interrupted. Please try again.",
                                        e);

                } catch (java.net.http.HttpTimeoutException e) {

                        throw new IllegalStateException(
                                        "The AI request timed out. Please try again.",
                                        e);

                } catch (java.net.ConnectException e) {

                        throw new IllegalStateException(
                                        "Could not connect to OpenRouter. Please check your internet connection.",
                                        e);

                } catch (Exception e) {

                        throw new IllegalStateException(
                                        "Unable to contact the AI preference analyzer: "
                                                        + safeExceptionMessage(
                                                                        e),
                                        e);
                }
        }

        private static String getApiKey() {

                if (OPENROUTER_API_KEY == null ||
                                OPENROUTER_API_KEY.isBlank() ||
                                OPENROUTER_API_KEY.equals(
                                                "PASTE_YOUR_OPENROUTER_API_KEY_HERE")) {

                        throw new IllegalStateException(
                                        "OpenRouter API key is not configured.");
                }

                return OPENROUTER_API_KEY.trim();
        }

        private static String getModel() {

                return DEFAULT_MODEL;
        }

        private static IllegalStateException createApiException(
                        int statusCode,
                        String body) {

                String apiMessage = extractErrorMessage(
                                body);

                String cleanApiMessage = apiMessage == null
                                ? null
                                : sanitizeMessage(
                                                apiMessage);

                if (statusCode == 400) {

                        return new IllegalStateException(
                                        cleanApiMessage == null
                                                        ? "OpenRouter rejected the preference analysis request."
                                                        : "OpenRouter rejected the request: "
                                                                        + cleanApiMessage);
                }

                if (statusCode == 401) {

                        return new IllegalStateException(
                                        "The OpenRouter API key is invalid or expired.");
                }

                if (statusCode == 402) {

                        return new IllegalStateException(
                                        "OpenRouter credits or quota are not available.");
                }

                if (statusCode == 403) {

                        return new IllegalStateException(
                                        "The OpenRouter API request was denied.");
                }

                if (statusCode == 404) {

                        return new IllegalStateException(
                                        cleanApiMessage == null
                                                        ? "The configured OpenRouter model or endpoint was not found."
                                                        : "OpenRouter resource not found: "
                                                                        + cleanApiMessage);
                }

                if (statusCode == 429) {

                        return new IllegalStateException(
                                        cleanApiMessage == null
                                                        ? "OpenRouter rate limit reached. Please try again shortly."
                                                        : "OpenRouter rate limit reached: "
                                                                        + cleanApiMessage);
                }

                if (statusCode >= 500) {

                        return new IllegalStateException(
                                        "OpenRouter is temporarily unavailable. Please try again.");
                }

                return new IllegalStateException(
                                cleanApiMessage == null
                                                ? "AI request failed with status "
                                                                + statusCode + "."
                                                : "AI request failed: "
                                                                + cleanApiMessage);
        }

        private static String extractErrorMessage(
                        String json) {

                if (json == null ||
                                json.isBlank()) {

                        return null;
                }

                try {

                        JsonObject root = JsonParser
                                        .parseString(
                                                        json)
                                        .getAsJsonObject();

                        if (root.has("error") &&
                                        root.get("error")
                                                        .isJsonObject()) {

                                JsonObject error = root.getAsJsonObject(
                                                "error");

                                if (error.has("message") &&
                                                !error.get("message")
                                                                .isJsonNull()) {

                                        return error
                                                        .get("message")
                                                        .getAsString();
                                }
                        }

                } catch (Exception ignored) {
                }

                return null;
        }

        private static String sanitizeMessage(
                        String message) {

                if (message == null ||
                                message.isBlank()) {

                        return "Unknown API error.";
                }

                return message.replaceAll(
                                "sk-or-[A-Za-z0-9_\\-]+",
                                "[API KEY HIDDEN]");
        }

        private static String extractAnswer(
                        String json) {

                if (json == null ||
                                json.isBlank()) {

                        throw new IllegalStateException(
                                        "The AI preference analyzer returned an empty response.");
                }

                try {

                        JsonObject root = JsonParser
                                        .parseString(
                                                        json)
                                        .getAsJsonObject();

                        JsonArray choices = root.getAsJsonArray(
                                        "choices");

                        if (choices == null ||
                                        choices.isEmpty()) {

                                throw new IllegalStateException(
                                                "The AI preference analyzer returned an empty response.");
                        }

                        StringBuilder answer = new StringBuilder();

                        for (JsonElement choiceElement : choices) {

                                if (choiceElement == null ||
                                                !choiceElement.isJsonObject()) {

                                        continue;
                                }

                                JsonObject choice = choiceElement.getAsJsonObject();

                                if (!choice.has("message") ||
                                                !choice.get("message")
                                                                .isJsonObject()) {

                                        continue;
                                }

                                JsonObject message = choice.getAsJsonObject(
                                                "message");

                                if (!message.has("content") ||
                                                message.get("content")
                                                                .isJsonNull()) {

                                        continue;
                                }

                                String text = message.get(
                                                "content").getAsString();

                                if (text == null ||
                                                text.isBlank()) {

                                        continue;
                                }

                                if (answer.length() > 0) {

                                        answer.append(
                                                        "\n");
                                }

                                answer.append(
                                                text.trim());
                        }

                        if (answer.length() == 0) {

                                throw new IllegalStateException(
                                                "The AI preference analyzer returned no readable text.");
                        }

                        String cleanedAnswer = answer
                                        .toString()
                                        .replace("**", "")
                                        .replace("###", "")
                                        .replace("##", "")
                                        .replace("#", "")
                                        .trim();

                        return cleanedAnswer;

                } catch (IllegalStateException e) {

                        throw e;

                } catch (Exception e) {

                        throw new IllegalStateException(
                                        "Could not read the preference analysis response.",
                                        e);
                }
        }

        private static String safeExceptionMessage(
                        Throwable throwable) {

                if (throwable == null) {

                        return "Unknown error.";
                }

                String message = throwable.getMessage();

                if (message == null ||
                                message.isBlank()) {

                        return throwable
                                        .getClass()
                                        .getSimpleName();
                }

                return sanitizeMessage(
                                message);
        }
}