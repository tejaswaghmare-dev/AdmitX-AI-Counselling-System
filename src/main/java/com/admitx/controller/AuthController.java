package com.admitx.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.cloudinary.json.JSONObject;

public class AuthController {

    private final String API_KEY = System.getenv("FIREBASE_API_KEY");

    // ============================
    // SIGN UP
    // ============================

    public boolean signUp(String email, String password) {

        JSONObject payload = new JSONObject();

        payload.put("email", email);
        payload.put("password", password);
        payload.put("returnSecureToken", true);

        try {

            HttpClient client = HttpClient.newHttpClient();

            String url =
                    "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="
                    + API_KEY;

            URI uri = URI.create(url);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("SIGN UP STATUS: " + response.statusCode());
            System.out.println("SIGN UP RESPONSE: " + response.body());

            return response.statusCode() == 200;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // ============================
    // SIGN IN
    // ============================

    public boolean signIn(String email, String password) {

        JSONObject payload = new JSONObject();

        payload.put("email", email);
        payload.put("password", password);
        payload.put("returnSecureToken", true);

        try {

            HttpClient client = HttpClient.newHttpClient();

            String url =
                    "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                    + API_KEY;

            URI uri = URI.create(url);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                    .build();

            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("SIGN IN STATUS: " + response.statusCode());
            System.out.println("SIGN IN RESPONSE: " + response.body());

            return response.statusCode() == 200;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}