package com.admitx.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public final class FirebaseConfig {

    private static Firestore firestore;

    private FirebaseConfig() {
    }

    public static synchronized Firestore getFirestore() {

        if (firestore != null) {
            return firestore;
        }

        initializeFirebase();

        if (firestore == null) {
            throw new IllegalStateException(
                    "Firestore is not available because Firebase initialization failed."
            );
        }

        return firestore;
    }

    private static void initializeFirebase() {

        if (firestore != null) {
            return;
        }

        try {

            if (FirebaseApp.getApps().isEmpty()) {

                try (InputStream serviceAccount = openCredentialStream()) {

                    FirebaseOptions options =
                            FirebaseOptions.builder()
                                    .setCredentials(
                                            GoogleCredentials.fromStream(
                                                    serviceAccount
                                            )
                                    )
                                    .build();

                    FirebaseApp.initializeApp(
                            options
                    );
                }

                System.out.println(
                        "Firebase connected successfully!"
                );
            }

            firestore =
                    FirestoreClient.getFirestore();

            if (firestore == null) {
                throw new IllegalStateException(
                        "FirestoreClient returned null."
                );
            }

        } catch (Exception e) {

            firestore = null;

            throw new IllegalStateException(
                    "Firebase initialization failed: "
                            + readableMessage(e),
                    e
            );
        }
    }

    private static InputStream openCredentialStream()
            throws Exception {

        /*
         * Recommended:
         *
         * Windows PowerShell:
         * $env:GOOGLE_APPLICATION_CREDENTIALS="C:\\path\\to\\java2026.json"
         *
         * This keeps the service-account key OUT of src/main/resources
         * and OUT of your Git repository.
         */
        String externalPath =
                System.getenv(
                        "GOOGLE_APPLICATION_CREDENTIALS"
                );

        if (externalPath == null ||
                externalPath.isBlank()) {

            externalPath =
                    System.getProperty(
                            "GOOGLE_APPLICATION_CREDENTIALS"
                    );
        }

        if (externalPath != null &&
                !externalPath.isBlank()) {

            File credentialFile =
                    new File(
                            externalPath.trim()
                    );

            if (!credentialFile.isFile()) {
                throw new IllegalStateException(
                        "Firebase credential file does not exist: "
                                + credentialFile.getAbsolutePath()
                );
            }

            return new FileInputStream(
                    credentialFile
            );
        }

        /*
         * Development fallback only.
         *
         * If you currently keep java2026.json in:
         * src/main/resources/java2026.json
         *
         * Maven/IDE places it on the runtime classpath and this code finds it.
         */
        InputStream classpathStream =
                FirebaseConfig.class
                        .getResourceAsStream(
                                "/java2026.json"
                        );

        if (classpathStream != null) {
            return classpathStream;
        }

        throw new IllegalStateException(
                "Firebase credentials were not found. "
                        + "Set GOOGLE_APPLICATION_CREDENTIALS to the full path "
                        + "of your Firebase service-account JSON file. "
                        + "For local development only, you may alternatively place "
                        + "java2026.json in src/main/resources."
        );
    }

    private static String readableMessage(
            Throwable throwable
    ) {

        if (throwable == null) {
            return "Unknown error.";
        }

        String message =
                throwable.getMessage();

        if (message == null ||
                message.isBlank()) {

            return throwable.getClass()
                    .getSimpleName();
        }

        return message;
    }
}
