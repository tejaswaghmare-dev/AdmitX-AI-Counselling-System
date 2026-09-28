package com.admitx.model;

import com.google.cloud.Timestamp;

public class Notification {

    private String id;
    private String studentEmail;
    private String title;
    private String message;
    private String type;
    private boolean read;
    private Timestamp createdAt;

    public Notification() {
    }

    public Notification(
            String id,
            String studentEmail,
            String title,
            String message,
            String type,
            boolean read,
            Timestamp createdAt
    ) {
        this.id = id;
        this.studentEmail = studentEmail;
        this.title = title;
        this.message = message;
        this.type = type;
        this.read = read;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public boolean isRead() {
        return read;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
