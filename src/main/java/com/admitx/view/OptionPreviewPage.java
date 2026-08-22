package com.admitx.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class OptionPreviewPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String FIELD = "#101610";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Option Form Preview");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label instruction =
                new Label(
                        "Review your preferences carefully before locking your choices."
                );

        instruction.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        instruction
                );

        ObservableList<PreferenceFillingPage.Preference> preferences =
                PreferenceFillingPage.getPreferences();

        Label status =
                new Label("READY TO LOCK");

        status.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 20px;"
        );

        TableView<PreferenceFillingPage.Preference> table =
                new TableView<>();

        TableColumn<
                PreferenceFillingPage.Preference,
                Number> numberColumn =
                new TableColumn<>("Preference No.");

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>("preferenceNumber")
        );

        TableColumn<
                PreferenceFillingPage.Preference,
                String> collegeColumn =
                new TableColumn<>("College");

        collegeColumn.setCellValueFactory(
                new PropertyValueFactory<>("college")
        );

        TableColumn<
                PreferenceFillingPage.Preference,
                String> branchColumn =
                new TableColumn<>("Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch")
        );

        table.getColumns().addAll(
                numberColumn,
                collegeColumn,
                branchColumn
        );

        table.setItems(preferences);

        table.setPrefHeight(400);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-control-inner-background: " + FIELD + ";" +
                "-fx-table-cell-border-color: " + BORDER + ";" +
                "-fx-text-background-color: " + WHITE + ";" +
                "-fx-selection-bar: " + LIME + ";" +
                "-fx-selection-bar-non-focused: " + LIME + ";"
        );

        Label totalLabel =
                new Label(
                        "Total Preferences: " +
                        preferences.size()
                );

        totalLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox tableCard =
                new VBox(
                        12,
                        createSectionTitle("OPTION FORM"),
                        totalLabel,
                        table
                );

        tableCard.setPadding(
                new Insets(20)
        );

        tableCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label warning =
                new Label(
                        "Please check the order carefully. Once the option form is locked, "
                        + "your choices will be submitted for CAP allotment."
                );

        warning.setWrapText(true);

        warning.setStyle(
                "-fx-background-color: #211F0F;" +
                "-fx-text-fill: #D9E6C8;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #665F20;" +
                "-fx-border-radius: 8px;"
        );

        Button editButton =
                new Button("← Edit Preferences");

        styleSecondaryButton(
                editButton
        );

        editButton.setOnAction(e ->
                Navigation.goTo(
                        PreferenceFillingPage.getScene()
                )
        );

        Button lockButton =
                new Button("Lock Choices  ✓");

        stylePrimaryButton(
                lockButton
        );

        lockButton.setOnAction(e -> {

            if (preferences.isEmpty()) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.WARNING
                        );

                alert.setTitle(
                        "Option Form"
                );

                alert.setHeaderText(
                        null
                );

                alert.setContentText(
                        "Please add at least one preference."
                );

                alert.showAndWait();

                return;
            }

            Alert confirmation =
                    new Alert(
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

                        if (
                                response ==
                                ButtonType.OK
                        ) {

                            Navigation.goTo(
                                    OptionConfirmationPage
                                            .getScene()
                            );
                        }
                    });
        });

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        editButton,
                        spacer,
                        lockButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        status,
                        tableCard,
                        warning,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Option Form Preview",
                        content
                )
        );
    }

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }
}