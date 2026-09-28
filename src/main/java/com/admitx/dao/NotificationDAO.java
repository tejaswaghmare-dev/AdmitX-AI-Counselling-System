package com.admitx.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.admitx.config.FirebaseConfig;
import com.admitx.model.Notification;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteBatch;

public class NotificationDAO {

    private final Firestore db =
            FirebaseConfig.getFirestore();

    public boolean createNotification(
            String studentEmail,
            String title,
            String message,
            String type
    ) {
        try {
            if (studentEmail == null || studentEmail.isBlank()) {
                return false;
            }

            Map<String, Object> data = new HashMap<>();

            data.put(
                    "studentEmail",
                    studentEmail.trim().toLowerCase()
            );

            data.put(
                    "title",
                    title == null ? "AdmitX Update" : title.trim()
            );

            data.put(
                    "message",
                    message == null ? "" : message.trim()
            );

            data.put(
                    "type",
                    type == null ? "GENERAL" : type.trim()
            );

            data.put("read", false);
            data.put("createdAt", Timestamp.now());

            db.collection("Notifications")
                    .add(data)
                    .get();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Notification> getNotifications(
            String studentEmail
    ) {
        List<Notification> notifications =
                new ArrayList<>();

        try {
            if (studentEmail == null || studentEmail.isBlank()) {
                return notifications;
            }

            QuerySnapshot snapshot =
                    db.collection("Notifications")
                            .whereEqualTo(
                                    "studentEmail",
                                    studentEmail.trim().toLowerCase()
                            )
                            .get()
                            .get();

            for (QueryDocumentSnapshot document
                    : snapshot.getDocuments()) {

                notifications.add(
                        toNotification(document)
                );
            }

            notifications.sort(
                    Comparator.comparing(
                            Notification::getCreatedAt,
                            Comparator.nullsLast(
                                    Comparator.naturalOrder()
                            )
                    ).reversed()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }

    public int getUnreadCount(
            String studentEmail
    ) {
        int count = 0;

        for (Notification notification
                : getNotifications(studentEmail)) {

            if (!notification.isRead()) {
                count++;
            }
        }

        return count;
    }

    public boolean markAsRead(
            String notificationId
    ) {
        try {
            if (notificationId == null
                    || notificationId.isBlank()) {
                return false;
            }

            db.collection("Notifications")
                    .document(notificationId)
                    .update("read", true)
                    .get();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean markAllAsRead(
            String studentEmail
    ) {
        try {
            if (studentEmail == null || studentEmail.isBlank()) {
                return false;
            }

            QuerySnapshot snapshot =
                    db.collection("Notifications")
                            .whereEqualTo(
                                    "studentEmail",
                                    studentEmail.trim().toLowerCase()
                            )
                            .get()
                            .get();

            WriteBatch batch =
                    db.batch();

            boolean changed = false;

            for (QueryDocumentSnapshot document
                    : snapshot.getDocuments()) {

                Boolean read =
                        document.getBoolean("read");

                if (!Boolean.TRUE.equals(read)) {
                    batch.update(
                            document.getReference(),
                            "read",
                            true
                    );

                    changed = true;
                }
            }

            if (changed) {
                batch.commit().get();
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ListenerRegistration listenForUnreadCount(
            String studentEmail,
            Consumer<Integer> onCountChanged
    ) {
        if (studentEmail == null
                || studentEmail.isBlank()
                || onCountChanged == null) {
            return null;
        }

        return db.collection("Notifications")
                .whereEqualTo(
                        "studentEmail",
                        studentEmail.trim().toLowerCase()
                )
                .addSnapshotListener(
                        (snapshot, error) -> {

                            if (error != null || snapshot == null) {
                                return;
                            }

                            int unread = 0;

                            for (DocumentSnapshot document
                                    : snapshot.getDocuments()) {

                                if (!Boolean.TRUE.equals(
                                        document.getBoolean("read")
                                )) {
                                    unread++;
                                }
                            }

                            onCountChanged.accept(unread);
                        }
                );
    }


    public int createNotificationForAllStudents(
            String title,
            String message,
            String type
    ) {
        int created = 0;

        try {
            QuerySnapshot students =
                    db.collection("Students")
                            .get()
                            .get();

            for (QueryDocumentSnapshot student : students.getDocuments()) {
                String email = student.getString("email");

                if (email == null || email.isBlank()) {
                    email = student.getId();
                }

                if (email != null
                        && !email.isBlank()
                        && email.contains("@")
                        && createNotification(email, title, message, type)) {
                    created++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return created;
    }

    private Notification toNotification(
            DocumentSnapshot document
    ) {
        return new Notification(
                document.getId(),
                document.getString("studentEmail"),
                document.getString("title"),
                document.getString("message"),
                document.getString("type"),
                Boolean.TRUE.equals(
                        document.getBoolean("read")
                ),
                document.getTimestamp("createdAt")
        );
    }
}
