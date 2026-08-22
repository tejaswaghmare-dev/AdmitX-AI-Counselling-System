package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Header {

    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String BG = "#0D120D";

    public static HBox create(String pageTitle) {

        Label title = new Label(pageTitle);

        title.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle = new Label("MHT CET CAP Counselling");

        subtitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox pageInfo = new VBox(3, title, subtitle);
        pageInfo.setAlignment(Pos.CENTER_LEFT);

        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        Label round = new Label("CAP 2026–27");

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

        Label notification = new Label("●");

        notification.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 0 5 0 8;"
        );

   Label avatar = new Label("ST");

avatar.setMinSize(
        34,
        34
);

avatar.setAlignment(
        Pos.CENTER
);

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
                8,
                notification,
                avatar,
                student
        );

userSection.setAlignment(
        Pos.CENTER
);

        HBox rightSection = new HBox(
                18,
                round,
                userSection
        );

        rightSection.setAlignment(Pos.CENTER);

        HBox header = new HBox(
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
}