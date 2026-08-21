package com.admitx.view;

import com.admitx.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CAPRound1ManagementPage {

    public static Scene getScene() {

        Label title =
                new Label("CAP Round 1 Management");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label status =
                new Label(
                        "CAP Round 1 Status: Ready"
                );

        status.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Button run =
                createButton("Run Seat Allotment");

        Button publish =
                createButton("Publish Results");

        Button report =
                createButton("View Allotment Report");

        run.setOnAction(e ->
                showMessage(
                        "Seat Allotment",
                        "CAP Round 1 seat allotment completed."
                )
        );

        publish.setOnAction(e ->
                showMessage(
                        "Results",
                        "CAP Round 1 results published."
                )
        );

        report.setOnAction(e ->
                showMessage(
                        "Allotment Report",
                        "CAP Round 1 allotment report opened."
                )
        );

        
        VBox card =
                new VBox(
                        18,
                        status,
                        run,
                        publish,
                        report
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPadding(
                new Insets(30)
        );

        card.setMaxWidth(600);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        VBox root =
                new VBox(
                        25,
                        title,
                        card
                );

        root.setAlignment(
                Pos.TOP_CENTER
        );

        root.setPadding(
                new Insets(30)
        );

        root.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        BorderPane layout =
        CounsellorLayout.create(
                "Cap Round 1",
                root
        );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static Button createButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(260);
        button.setPrefHeight(42);

        button.setStyle(
                "-fx-background-color: #0A0A0A;" +
                "-fx-text-fill: white;"
        );

        return button;
    }

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}