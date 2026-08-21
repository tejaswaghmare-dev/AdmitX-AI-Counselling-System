package com.example.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Round1ConfirmationPage {

    public static Scene getScene(String decision) {

        Label title =
                new Label("CAP Round 1 Confirmation");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label result =
                new Label(decision);

        result.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        Label college =
                new Label(
                        "College: College of Engineering Pune"
                );

        Label branch =
                new Label(
                        "Branch: Computer Engineering"
                );

        Label category =
                new Label(
                        "Category: Open"
                );

        Label message;

        if (decision.equals("Seat Accepted")) {

            message = new Label(
                    "You have accepted the allotted seat."
            );

        } else if (
                decision.equals("Betterment Requested")
        ) {

            message = new Label(
                    "You have requested betterment in the next CAP round."
            );

        } else {

            message = new Label(
                    "You have rejected the allotted seat."
            );
        }

        message.setWrapText(true);

        message.setStyle(
                "-fx-text-fill: #3F6212;" +
                "-fx-font-size: 15px;"
        );

        VBox card =
                new VBox(
                        15,
                        result,
                        college,
                        branch,
                        category,
                        message
                );

        card.setPadding(
                new Insets(30)
        );

        card.setAlignment(
                Pos.CENTER_LEFT
        );

        card.setMaxWidth(600);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        Button dashboard =
                new Button("Go to Dashboard");

        dashboard.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        Button nextRound =
                new Button("Continue to CAP Round 2");

        nextRound.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 40px;"
        );

        nextRound.setOnAction(e ->
                Navigation.goTo(
                        CAPRound2Page.getScene()
                )
        );

        VBox buttons =
                new VBox(
                        12,
                        nextRound,
                        dashboard
                );

        buttons.setAlignment(
                Pos.CENTER
        );

        VBox content =
                new VBox(
                        25,
                        title,
                        card,
                        buttons
                );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "CAP Round 1 Confirmation",
                        content
                )
        );
    }
}