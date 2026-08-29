package com.admitx.config;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {

    private static Firestore firestore;

    static {
        initializeFirebase();
    }

    private static void initializeFirebase() {

        try {

            if (FirebaseApp.getApps().isEmpty()) {

                FileInputStream serviceAccount =
                        new FileInputStream(
                                "src/main/resources/java2026.json"
                        );

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

                serviceAccount.close();

                System.out.println(
                        "Firebase connected successfully!"
                );
            }

            firestore =
                    FirestoreClient.getFirestore();

        } catch (IOException e) {

            System.out.println(
                    "Firebase credential file not found or invalid."
            );

            e.printStackTrace();

        } catch (Exception e) {

            System.out.println(
                    "Firebase initialization failed."
            );

            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {

        if (firestore == null) {

            initializeFirebase();
        }

        return firestore;
    }
}