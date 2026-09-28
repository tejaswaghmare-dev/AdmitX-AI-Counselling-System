package com.admitx.config;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

public final class CloudinaryConfig {

    private static Cloudinary cloudinary;

    private static final String CLOUD_NAME =
            System.getenv("CLOUDINARY_CLOUD_NAME");

    private static final String API_KEY =
            System.getenv("CLOUDINARY_API_KEY");

    private static final String API_SECRET =
            System.getenv("CLOUDINARY_API_SECRET");

    private CloudinaryConfig() {
    }

    public static synchronized Cloudinary getCloudinary() {

        if (cloudinary != null) {
            return cloudinary;
        }

        if (CLOUD_NAME == null || CLOUD_NAME.isBlank()) {
            throw new IllegalStateException(
                    "Cloudinary cloud name is not configured."
            );
        }

        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException(
                    "Cloudinary API key is not configured."
            );
        }

        if (API_SECRET == null || API_SECRET.isBlank()) {
            throw new IllegalStateException(
                    "Cloudinary API secret is not configured."
            );
        }

        Map<String, Object> config =
                new HashMap<>();

        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        config.put("secure", true);

        cloudinary = new Cloudinary(config);

        return cloudinary;
    }
}