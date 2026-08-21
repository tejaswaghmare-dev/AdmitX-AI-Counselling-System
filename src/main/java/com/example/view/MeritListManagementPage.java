package com.example.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MeritListManagementPage {

    public static Scene getScene() {

        Label title =
                new Label("Merit List Management");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label status =
                new Label(
                        "Current Status: Provisional Merit List Ready"
                );

        status.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Button generate =
                button("Generate Provisional Merit List");

        Button publish =
                button("Publish Merit List");

        Button edit =
                button("Edit Merit Rank");

        Button grievances =
                button("Approve Grievances");

        generate.setOnAction(e ->
                message(
                        "Merit List",
                        "Provisional merit list generated successfully."
                )
        );

        publish.setOnAction(e ->
                message(
                        "Published",
                        "Merit list published successfully."
                )
        );

        edit.setOnAction(e ->
                message(
                        "Edit Rank",
                        "Merit rank editing screen opened."
                )
        );

        grievances.setOnAction(e ->
                message(
                        "Grievances",
                        "Grievance approval section opened."
                )
        );

        VBox card =
                new VBox(
                        18,
                        status,
                        generate,
                        publish,
                        edit,
                        grievances
                );

        card.setPadding(
                new Insets(30)
        );

        card.setMaxWidth(600);

        card.setAlignment(
                Pos.CENTER
        );

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;"
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
                "Merit List",
                root
        );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static Button button(
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

    private static void message(
            String title,
            String text
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}