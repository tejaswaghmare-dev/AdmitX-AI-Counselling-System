package com.admitx.view;

import com.admitx.dao.NotificationDAO;
import com.admitx.model.Student;
import com.google.cloud.firestore.ListenerRegistration;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Header {

    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String BG = "#0D120D";

    private static final NotificationDAO notificationDAO =
            new NotificationDAO();

    public static HBox create(String pageTitle) {

        Label title =
                new Label(pageTitle);

        title.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label("MHT CET CAP Counselling");

        subtitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox pageInfo =
                new VBox(3, title, subtitle);

        pageInfo.setAlignment(Pos.CENTER_LEFT);

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label round =
                new Label("CAP 2026–27");

        round.setStyle(
                "-fx-background-color: #172117;" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #2B3A2B;" +
                "-fx-border-radius: 20px;" +
                "-fx-padding: 7px 13px;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Button notificationButton =
                new Button("🔔");

        notificationButton.setMinSize(40, 40);
        notificationButton.setPrefSize(40, 40);
        notificationButton.setMaxSize(40, 40);

        notificationButton.setStyle(
                "-fx-background-color: #151D15;" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #2B3A2B;" +
                "-fx-border-radius: 20px;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 16px;" +
                "-fx-cursor: hand;"
        );

        Label notificationBadge =
                new Label();

        notificationBadge.setAlignment(Pos.CENTER);
        notificationBadge.setVisible(false);
        notificationBadge.setManaged(false);

        notificationBadge.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 2px 5px;"
        );

        StackPane notificationBox =
                new StackPane(
                        notificationButton,
                        notificationBadge
                );

        StackPane.setAlignment(
                notificationBadge,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                notificationBadge,
                new Insets(-4, -6, 0, 0)
        );

        notificationButton.setOnAction(
                e -> Navigation.goTo(
                        NotificationPage.getScene()
                )
        );

        String email =
                Student.getInstance().getEmail();

        if (email != null && !email.isBlank()) {

            final ListenerRegistration[] registration =
                    new ListenerRegistration[1];

            registration[0] =
                    notificationDAO.listenForUnreadCount(
                            email,
                            count -> Platform.runLater(
                                    () -> updateBadge(
                                            notificationBadge,
                                            count
                                    )
                            )
                    );

            notificationBox.sceneProperty()
                    .addListener(
                            (observable, oldScene, newScene) -> {

                                if (oldScene != null
                                        && newScene == null
                                        && registration[0] != null) {

                                    registration[0].remove();
                                    registration[0] = null;
                                }
                            }
                    );
        }

        Label avatar =
                new Label("ST");

        avatar.setMinSize(34, 34);
        avatar.setAlignment(Pos.CENTER);

        avatar.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label student =
                new Label("Student");

        student.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        HBox userSection =
                new HBox(
                        10,
                        notificationBox,
                        avatar,
                        student
                );

        userSection.setAlignment(Pos.CENTER);

        HBox rightSection =
                new HBox(
                        18,
                        round,
                        userSection
                );

        rightSection.setAlignment(Pos.CENTER);

        HBox header =
                new HBox(
                        pageInfo,
                        spacer,
                        rightSection
                );

        header.setAlignment(Pos.CENTER_LEFT);

        header.setPadding(
                new Insets(16, 28, 16, 28)
        );

        header.setMinHeight(76);
        header.setPrefHeight(76);

        header.setStyle(
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: #202820;" +
                "-fx-border-width: 0 0 1 0;"
        );

        return header;
    }

    private static void updateBadge(
            Label badge,
            int unreadCount
    ) {
        if (unreadCount <= 0) {
            badge.setVisible(false);
            badge.setManaged(false);
            badge.setText("");
            return;
        }

        badge.setText(
                unreadCount > 99
                        ? "99+"
                        : String.valueOf(unreadCount)
        );

        badge.setManaged(true);
        badge.setVisible(true);
    }
}
