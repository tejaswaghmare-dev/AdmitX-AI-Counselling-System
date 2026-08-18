package com.admitx.view;

import com. admitx.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MeritListManagementPage {

    public static Scene getScene() {

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📋 Merit List Management");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Generate and manage merit lists");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Card
        VBox card = new VBox(16);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30, 35, 35, 35));
        card.setMaxWidth(600);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label status = new Label("📊 Current Status: Provisional Merit List Ready");
        status.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4ADE80;"
        );

        Button generate = createButton("🔄 Generate Provisional Merit List");
        Button publish = createButton("📢 Publish Merit List");
        Button edit = createButton("✏️ Edit Merit Rank");
        Button grievances = createButton("✅ Approve Grievances");

        generate.setOnAction(e -> message("Merit List", "Provisional merit list generated successfully."));
        publish.setOnAction(e -> message("Published", "Merit list published successfully."));
        edit.setOnAction(e -> message("Edit Rank", "Merit rank editing screen opened."));
        grievances.setOnAction(e -> message("Grievances", "Grievance approval section opened."));

        card.getChildren().addAll(status, generate, publish, edit, grievances);

        Button back = new Button("← Back to Dashboard");
        back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        back.setOnMouseEntered(e ->
            back.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnMouseExited(e ->
            back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnAction(e -> Navigation.goTo(CounsellorDashboardPage.getScene()));

        // Footer
        Label footer = new Label("© 2026 AdmitX · Merit List Management");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;" +
                "-fx-padding: 20 0 0 0;"
        );

        root.getChildren().addAll(title, subtitle, card, back, footer);

        return new Scene(root, 1000, 700);
    }

    private static Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(280);
        button.setPrefHeight(44);
        button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
        );
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
            )
        );
        button.setOnMouseExited(e ->
            button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-alignment: CENTER_LEFT;" +
                "-fx-padding: 0 0 0 15;"
            )
        );
        return button;
    }

    private static void message(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}