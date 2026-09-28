package com.admitx.view;

import java.text.SimpleDateFormat;
import java.util.List;

import com.admitx.dao.NotificationDAO;
import com.admitx.model.Notification;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class NotificationPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    private static final NotificationDAO notificationDAO =
            new NotificationDAO();

    public static Scene getScene() {

        VBox content =
                new VBox(18);

        content.setPadding(
                new Insets(4)
        );

        Label heading =
                new Label("Notifications");

        heading.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        Label subtitle =
                new Label(
                        "Updates about your application, documents and counselling process."
                );

        subtitle.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 12px;"
        );

        VBox headingBox =
                new VBox(4, heading, subtitle);

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button markAllButton =
                new Button("Mark all as read");

        markAllButton.setStyle(
                "-fx-background-color: #172117;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-border-color: #344234;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 9px 14px;" +
                "-fx-cursor: hand;"
        );

        HBox top =
                new HBox(
                        12,
                        headingBox,
                        spacer,
                        markAllButton
                );

        top.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox notificationList =
                new VBox(10);

        Label loading =
                new Label("Loading notifications...");

        loading.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;"
        );

        notificationList.getChildren().add(loading);

        ScrollPane scrollPane =
                new ScrollPane(notificationList);

        scrollPane.setFitToWidth(true);

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: transparent;"
        );

        VBox.setVgrow(
                scrollPane,
                Priority.ALWAYS
        );

        content.getChildren().addAll(
                top,
                scrollPane
        );

        Scene scene =
                new Scene(
                        StudentLayout.create(
                                "Notifications",
                                content
                        )
                );

        String email =
                Student.getInstance().getEmail();

        loadNotifications(
                email,
                notificationList,
                markAllButton
        );

        markAllButton.setOnAction(
                e -> {
                    markAllButton.setDisable(true);
                    markAllButton.setText("Updating...");

                    AsyncTaskRunner.run(
                            () -> notificationDAO.markAllAsRead(email),
                            saved -> {
                                markAllButton.setText("Mark all as read");
                                markAllButton.setDisable(false);

                                loadNotifications(
                                        email,
                                        notificationList,
                                        markAllButton
                                );
                            },
                            error -> {
                                markAllButton.setText("Mark all as read");
                                markAllButton.setDisable(false);
                            }
                    );
                }
        );

        return scene;
    }

    private static void loadNotifications(
            String email,
            VBox notificationList,
            Button markAllButton
    ) {
        AsyncTaskRunner.run(
                () -> notificationDAO.getNotifications(email),
                notifications -> renderNotifications(
                        notifications,
                        notificationList,
                        markAllButton
                ),
                error -> {
                    notificationList.getChildren().clear();

                    Label failed =
                            new Label(
                                    "Could not load notifications."
                            );

                    failed.setStyle(
                            "-fx-text-fill: #FCA5A5;" +
                            "-fx-font-size: 13px;"
                    );

                    notificationList.getChildren().add(failed);
                }
        );
    }

    private static void renderNotifications(
            List<Notification> notifications,
            VBox notificationList,
            Button markAllButton
    ) {
        notificationList.getChildren().clear();

        if (notifications == null
                || notifications.isEmpty()) {

            markAllButton.setDisable(true);

            VBox empty =
                    new VBox(8);

            empty.setAlignment(
                    Pos.CENTER
            );

            empty.setPadding(
                    new Insets(70)
            );

            Label bell =
                    new Label("🔔");

            bell.setStyle(
                    "-fx-font-size: 34px;"
            );

            Label title =
                    new Label(
                            "No notifications yet"
                    );

            title.setStyle(
                    "-fx-text-fill: " + WHITE + ";" +
                    "-fx-font-size: 17px;" +
                    "-fx-font-weight: bold;"
            );

            Label message =
                    new Label(
                            "Counsellor and CAP updates will appear here."
                    );

            message.setStyle(
                    "-fx-text-fill: " + MUTED + ";" +
                    "-fx-font-size: 12px;"
            );

            empty.getChildren().addAll(
                    bell,
                    title,
                    message
            );

            notificationList
                    .getChildren()
                    .add(empty);

            return;
        }

        boolean hasUnread = false;

        for (Notification notification
                : notifications) {

            if (!notification.isRead()) {
                hasUnread = true;
            }

            notificationList
                    .getChildren()
                    .add(
                            createNotificationCard(
                                    notification
                            )
                    );
        }

        markAllButton.setDisable(!hasUnread);
    }

    private static VBox createNotificationCard(
            Notification notification
    ) {

        Label dot =
                new Label(
                        notification.isRead()
                                ? "○"
                                : "●"
                );

        dot.setStyle(
                "-fx-text-fill: "
                        + (notification.isRead()
                        ? MUTED
                        : LIME)
                        + ";" +
                "-fx-font-size: 13px;"
        );

        Label title =
                new Label(
                        safe(
                                notification.getTitle(),
                                "AdmitX Update"
                        )
                );

        title.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        Label time =
                new Label(
                        formatTime(notification)
                );

        time.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 10px;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox titleRow =
                new HBox(
                        9,
                        dot,
                        title,
                        spacer,
                        time
                );

        titleRow.setAlignment(
                Pos.CENTER_LEFT
        );

        Label message =
                new Label(
                        safe(
                                notification.getMessage(),
                                ""
                        )
                );

        message.setWrapText(true);

        message.setStyle(
                "-fx-text-fill: #CCD5CC;" +
                "-fx-font-size: 12px;" +
                "-fx-line-spacing: 2px;"
        );

        VBox card =
                new VBox(
                        8,
                        titleRow,
                        message
                );

        card.setPadding(
                new Insets(15, 17, 15, 17)
        );

        card.setStyle(
                "-fx-background-color: "
                        + (notification.isRead()
                        ? CARD
                        : "#162016")
                        + ";" +
                "-fx-border-color: "
                        + (notification.isRead()
                        ? BORDER
                        : "#40552A")
                        + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 9px;" +
                "-fx-background-radius: 9px;" +
                "-fx-cursor: hand;"
        );

        if (!notification.isRead()) {
            card.setOnMouseClicked(
                    e -> {
                        notificationDAO.markAsRead(
                                notification.getId()
                        );

                        dot.setText("○");
                        dot.setStyle(
                                "-fx-text-fill: " + MUTED + ";" +
                                "-fx-font-size: 13px;"
                        );

                        card.setStyle(
                                "-fx-background-color: " + CARD + ";" +
                                "-fx-border-color: " + BORDER + ";" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-radius: 9px;" +
                                "-fx-background-radius: 9px;" +
                                "-fx-cursor: hand;"
                        );
                    }
            );
        }

        return card;
    }

    private static String formatTime(
            Notification notification
    ) {
        if (notification.getCreatedAt() == null) {
            return "";
        }

        return new SimpleDateFormat(
                "dd MMM, hh:mm a"
        ).format(
                notification.getCreatedAt().toDate()
        );
    }

    private static String safe(
            String value,
            String fallback
    ) {
        return value == null || value.isBlank()
                ? fallback
                : value;
    }
}
