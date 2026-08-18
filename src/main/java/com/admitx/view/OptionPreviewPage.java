package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class OptionPreviewPage {

    public static Scene getScene() {

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("Option Form Preview");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label instruction = new Label("Review your preferences carefully before locking your choices.");
        instruction.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;"
        );

        // Table Card
        VBox tableCard = new VBox(15);
        tableCard.setPadding(new Insets(25, 30, 30, 30));
        tableCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        TableView<PreferenceFillingPage.Preference> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );

        TableColumn<PreferenceFillingPage.Preference, Number> numberColumn = new TableColumn<>("Preference No.");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("preferenceNumber"));
        numberColumn.setPrefWidth(130);
        numberColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<PreferenceFillingPage.Preference, String> collegeColumn = new TableColumn<>("College");
        collegeColumn.setCellValueFactory(new PropertyValueFactory<>("college"));
        collegeColumn.setPrefWidth(350);
        collegeColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<PreferenceFillingPage.Preference, String> branchColumn = new TableColumn<>("Branch");
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));
        branchColumn.setPrefWidth(300);
        branchColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        table.getColumns().addAll(numberColumn, collegeColumn, branchColumn);

        ObservableList<PreferenceFillingPage.Preference> preferences = PreferenceFillingPage.getPreferences();
        table.setItems(preferences);
        table.setPrefHeight(400);

        table.setRowFactory(tv -> new TableRow<PreferenceFillingPage.Preference>() {
            @Override
            protected void updateItem(PreferenceFillingPage.Preference item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    setStyle("-fx-background-color: rgba(10, 10, 15, 0.4);" +
                             "-fx-text-fill: #E8EDF5;" +
                             "-fx-border-color: rgba(74, 127, 181, 0.05);");
                }
            }
        });

        Label totalLabel = new Label("Total Preferences: " + preferences.size());
        totalLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;"
        );

        tableCard.getChildren().addAll(table, totalLabel);

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        Button editButton = new Button("✏️ Edit Preferences");
        editButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        editButton.setOnMouseEntered(e ->
            editButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        editButton.setOnMouseExited(e ->
            editButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        editButton.setOnAction(e -> Navigation.goTo(PreferenceFillingPage.getScene()));

        Button lockButton = new Button("🔒 Lock Choices");
        lockButton.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        lockButton.setOnMouseEntered(e ->
            lockButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(7, 138, 92, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        lockButton.setOnMouseExited(e ->
            lockButton.setStyle(
                "-fx-background-color: #065F46;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(6, 95, 70, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        // Keep original logic
        lockButton.setOnAction(e -> {
            if (preferences.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Option Form");
                alert.setHeaderText(null);
                alert.setContentText("Please add at least one preference.");
                alert.showAndWait();
                return;
            }
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Option Form");
            confirmation.setHeaderText("Lock your preferences?");
            confirmation.setContentText("Once locked, your preference list will be submitted for CAP allotment.");
            confirmation.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Navigation.goTo(OptionConfirmationPage.getScene());
                }
            });
        });

        buttons.getChildren().addAll(editButton, lockButton);

        content.getChildren().addAll(title, instruction, tableCard, buttons);

        return new Scene(
                StudentLayout.create("Option Form Preview", content)
        );
    }
}
