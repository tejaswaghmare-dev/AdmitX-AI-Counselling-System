package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class NoticeBoardPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Notice Board");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Stay updated with important CAP counselling announcements."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        Label sectionTitle =
                new Label("LATEST NOTICES");

        sectionTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox notices =
                new VBox(12);

        notices.getChildren().add(
                createNotice(
                        "CAP Schedule",
                        "CAP Round 1 allotment results have been published.",
                        "CAP UPDATE"
                )
        );

        notices.getChildren().add(
                createNotice(
                        "Counsellor Notice",
                        "Students are requested to verify their documents.",
                        "DOCUMENTS"
                )
        );

        notices.getChildren().add(
                createNotice(
                        "Important Update",
                        "Option form filling is now open.",
                        "OPTION FORM"
                )
        );

        VBox noticeCard =
                new VBox(
                        14,
                        sectionTitle,
                        notices
                );

        noticeCard.setPadding(
                new Insets(22)
        );

        noticeCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label note =
                new Label(
                        "Check the Notice Board regularly for CAP round schedules, document verification updates and option form announcements."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;"
        );

        Button back =
                new Button("← Dashboard");

        back.setPrefHeight(42);

        back.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        back.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        back.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        noticeCard,
                        note,
                        back
                );

        content.setPadding(
                new Insets(30)
        );

        content.setAlignment(
                Pos.TOP_LEFT
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Notice Board",
                        content
                )
        );
    }

    private static VBox createNotice(
            String heading,
            String message,
            String tag
    ) {

        Label tagLabel =
                new Label(tag);

        tagLabel.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 9px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 5 9 5 9;" +
                "-fx-background-radius: 14px;"
        );

        Label title =
                new Label(heading);

        title.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label text =
                new Label(message);

        text.setWrapText(true);

        text.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox textBox =
                new VBox(
                        6,
                        title,
                        text
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox header =
                new HBox(
                        12,
                        textBox,
                        spacer,
                        tagLabel
                );

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox box =
                new VBox(
                        header
                );

        box.setPadding(
                new Insets(16)
        );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color: #0F150F;" +
                "-fx-background-radius: 9px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 9px;"
        );

        return box;
    }
}