package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Header {

    public static HBox create(String pageTitle) {

        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(15, 25, 15, 25));
        header.setStyle(
                "-fx-background-color: #0A0A0F;" +
                "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                "-fx-border-width: 0 0 1 0;"
        );

        // Logo/Icon
        Label logoIcon = new Label("🎓");
        logoIcon.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-opacity: 0.8;"
        );

        // Title
        Label title = new Label(pageTitle);
        title.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        // Spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        // Right side - Portal name
        HBox rightSection = new HBox(10);
        rightSection.setAlignment(Pos.CENTER_RIGHT);

        Label portal = new Label("🏛️ MHT CET CAP Counselling Portal");
        portal.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #5A7D9E;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-opacity: 0.7;"
        );

        rightSection.getChildren().add(portal);

        header.getChildren().addAll(
                logoIcon,
                title,
                spacer,
                rightSection
        );

        return header;
    }
}