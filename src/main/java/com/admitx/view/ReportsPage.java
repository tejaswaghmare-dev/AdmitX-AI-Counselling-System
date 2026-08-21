package com.example.view;

import com.example.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ReportsPage {

    public static Scene getScene() {

        Label title = new Label("Reports");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Button studentReport =
                createButton("Student Report");

        Button meritReport =
                createButton("Merit Report");

        Button collegeReport =
                createButton("College-wise Report");

        Button branchReport =
                createButton("Branch-wise Report");

        Button categoryReport =
                createButton("Category-wise Report");

        Button roundReport =
                createButton("Round-wise Report");

        studentReport.setOnAction(e ->
                show("Student Report"));

        meritReport.setOnAction(e ->
                show("Merit Report"));

        collegeReport.setOnAction(e ->
                show("College-wise Report"));

        branchReport.setOnAction(e ->
                show("Branch-wise Report"));

        categoryReport.setOnAction(e ->
                show("Category-wise Report"));

        roundReport.setOnAction(e ->
                show("Round-wise Report"));

        

        VBox card = new VBox(
                15,
                studentReport,
                meritReport,
                collegeReport,
                branchReport,
                categoryReport,
                roundReport
        );

        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.setMaxWidth(500);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;"
        );

        VBox root = new VBox(
                25,
                title,
                card
        );

        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));

        root.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        BorderPane layout =
        CounsellorLayout.create(
                "Reports",
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

        Button button = new Button(text);

        button.setPrefWidth(260);
        button.setPrefHeight(42);

        button.setStyle(
                "-fx-background-color: #0A0A0A;" +
                "-fx-text-fill: white;"
        );

        return button;
    }

    private static void show(String report) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Report");
        alert.setHeaderText(report);
        alert.setContentText(
                report + " generated successfully."
        );

        alert.showAndWait();
    }
}