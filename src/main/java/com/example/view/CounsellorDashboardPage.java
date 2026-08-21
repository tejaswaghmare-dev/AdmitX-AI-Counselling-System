package com.example.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.layout.VBox;

public class CounsellorDashboardPage {

    public static Scene getScene() {

        Label title = new Label("Counsellor Dashboard");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label subtitle = new Label(
                "MHT CET CAP Counselling Management"
        );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #4D7C0F;"
        );

        GridPane stats = new GridPane();

        stats.setHgap(20);
        stats.setVgap(20);

        stats.add(createCard(
                "Total Students",
                "1250"
        ), 0, 0);

        stats.add(createCard(
                "Verified Students",
                "980"
        ), 1, 0);

        stats.add(createCard(
                "Pending Verification",
                "270"
        ), 2, 0);

        stats.add(createCard(
                "CAP Round Status",
                "Round 1 Active"
        ), 3, 0);

        

        
        VBox root = new VBox(
                25,
                title,
                subtitle,
                stats
             
        );

        root.setPadding(
                new Insets(35)
        );

        root.setAlignment(
                Pos.TOP_CENTER
        );

        root.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        BorderPane layout =
        CounsellorLayout.create(
                "Dashboard",
                root
        );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static VBox createCard(
            String title,
            String value
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4D7C0F;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        VBox card =
                new VBox(
                        8,
                        titleLabel,
                        valueLabel
                );

        card.setPadding(
                new Insets(18)
        );

        card.setPrefWidth(220);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        return card;
    }

    private static Button createButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(220);
        button.setPrefHeight(45);

        button.setStyle(
                "-fx-background-color: #0A0A0A;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-background-radius: 6px;"
        );

        return button;
    }
}