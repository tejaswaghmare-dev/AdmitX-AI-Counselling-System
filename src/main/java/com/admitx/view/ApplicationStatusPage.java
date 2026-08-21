package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ApplicationStatusPage {

    public static Scene getScene() {

        Label title = new Label("Application Status");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label applicationIdLabel =
                new Label("Application ID: MHTCET20260001");

        applicationIdLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #3F6212;"
        );

        Label statusTitle =
                new Label("Current Status");

        statusTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label status =
                new Label("Submitted");

        status.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;" +
                "-fx-padding: 10 0 10 0;"
        );

        Label description =
                new Label(
                        "Your application has been successfully submitted " +
                        "and is currently under verification."
                );

        description.setWrapText(true);

        description.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #3F6212;"
        );

        Label stepsTitle =
                new Label("Application Progress");

        stepsTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label steps =
                new Label(
                        "✓ Draft\n" +
                        "✓ Submitted\n" +
                        "● Under Verification\n" +
                        "○ Verified\n" +
                        "○ Merit List"
                );

        steps.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #3F6212;" +
                "-fx-line-spacing: 10px;"
        );

        Button dashboardButton =
                new Button("Go to Dashboard");

        dashboardButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        dashboardButton.setOnAction(e ->
                Navigation.goTo(StudentDashboardPage.getScene())
        );

        Button meritButton =
                new Button("View Provisional Merit List");

        meritButton.setStyle(
                "-fx-background-color: #3F6212;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        meritButton.setOnAction(e ->
            Navigation.goTo(
                    ProvisionalMeritPage.getScene()
            )
        );

        VBox card = new VBox(
                18,
                applicationIdLabel,
                statusTitle,
                status,
                description,
                stepsTitle,
                steps,
                dashboardButton,
                meritButton
        );

        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(30));
        card.setMaxWidth(650);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        VBox content = new VBox(card);

        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40));

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Application Status",
                        content
                )
        );
    }
}