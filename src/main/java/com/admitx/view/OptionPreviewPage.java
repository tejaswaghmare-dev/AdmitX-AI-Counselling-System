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

        Label title = new Label("Option Form Preview");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label instruction = new Label(
                "Review your preferences carefully before locking your choices."
        );

        instruction.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #4D7C0F;"
        );

        TableView<PreferenceFillingPage.Preference> table =
                new TableView<>();

        TableColumn<
                PreferenceFillingPage.Preference,
                Number
                > numberColumn =
                new TableColumn<>("Preference No.");

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>("preferenceNumber")
        );

        numberColumn.setPrefWidth(130);

        TableColumn<
                PreferenceFillingPage.Preference,
                String
                > collegeColumn =
                new TableColumn<>("College");

        collegeColumn.setCellValueFactory(
                new PropertyValueFactory<>("college")
        );

        collegeColumn.setPrefWidth(350);

        TableColumn<
                PreferenceFillingPage.Preference,
                String
                > branchColumn =
                new TableColumn<>("Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch")
        );

        branchColumn.setPrefWidth(300);

        table.getColumns().addAll(
                numberColumn,
                collegeColumn,
                branchColumn
        );

        ObservableList<
                PreferenceFillingPage.Preference
                > preferences =
                PreferenceFillingPage.getPreferences();

        table.setItems(preferences);

        table.setPrefHeight(400);

        Label totalLabel =
                new Label(
                        "Total Preferences: "
                                + preferences.size()
                );

        totalLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Button editButton =
                new Button("Edit Preferences");

        editButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;"
        );

        editButton.setOnAction(e ->
                Navigation.goTo(
                        PreferenceFillingPage.getScene()
                )
        );

        Button lockButton =
                new Button("Lock Choices");

        lockButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;"
        );

        lockButton.setOnAction(e -> {

            if (preferences.isEmpty()) {

                Alert alert = new Alert(
                        Alert.AlertType.WARNING
                );

                alert.setTitle("Option Form");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Please add at least one preference."
                );

                alert.showAndWait();

                return;
            }

            Alert confirmation = new Alert(
                    Alert.AlertType.CONFIRMATION
            );

            confirmation.setTitle(
                    "Confirm Option Form"
            );

            confirmation.setHeaderText(
                    "Lock your preferences?"
            );

            confirmation.setContentText(
                    "Once locked, your preference list "
                            + "will be submitted for CAP allotment."
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (response ==
                                ButtonType.OK) {

                            Navigation.goTo(
                                    OptionConfirmationPage
                                            .getScene()
                            );
                        }
                    });
        });

        HBox buttons =
                new HBox(
                        15,
                        editButton,
                        lockButton
                );

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        VBox content =
                new VBox(
                        20,
                        title,
                        instruction,
                        table,
                        totalLabel,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Option Form Preview",
                        content
                )
        );
    }
}