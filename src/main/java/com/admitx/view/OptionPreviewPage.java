package com.admitx.view;

import java.util.Optional;

import com.admitx.dao.PreferenceDAO;
import com.admitx.model.Student;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class OptionPreviewPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#141B14";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String WHITE =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    public static Scene getScene() {

        // =========================================================
        // LOGIN CHECK
        // =========================================================

        String studentEmail =
                Student.getInstance()
                        .getEmail();

        if (
                studentEmail == null ||
                studentEmail.isBlank()
        ) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Login Required",
                    "Please login again."
            );

            return StudentLoginPage.getScene();
        }

        PreferenceDAO preferenceDAO =
                new PreferenceDAO();

        boolean locked =
                preferenceDAO
                        .isPreferenceLocked();

        ObservableList<
                PreferenceFillingPage.Preference
        > preferences =
                PreferenceFillingPage
                        .getPreferences();

        // =========================================================
        // IF LOCKED, LOAD FIREBASE DATA
        // =========================================================

        if (locked) {

            preferences.clear();

            preferences.addAll(
                    preferenceDAO
                            .loadPreferences()
            );
        }

        // =========================================================
        // MAIN
        // =========================================================

        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color:"
                        + BG + ";"
        );

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Option Form Preview"
                );

        title.setStyle(
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Review your preferences carefully before locking the option form."
                );

        subtitle.setStyle(
                "-fx-text-fill:"
                        + MUTED + ";" +
                "-fx-font-size:13px;"
        );

        Label studentLabel =
                new Label(
                        "Student: "
                                + studentEmail
                );

        studentLabel.setStyle(
                "-fx-text-fill:"
                        + MUTED + ";" +
                "-fx-font-size:12px;"
        );

        Label statusLabel =
                new Label();

        if (locked) {

            statusLabel.setText(
                    "✓ OPTION FORM LOCKED"
            );

            statusLabel.setStyle(
                    "-fx-text-fill:"
                            + LIME + ";" +
                    "-fx-font-size:13px;" +
                    "-fx-font-weight:bold;"
            );

        } else {

            statusLabel.setText(
                    "Not locked yet"
            );

            statusLabel.setStyle(
                    "-fx-text-fill:"
                            + MUTED + ";" +
                    "-fx-font-size:12px;"
            );
        }

        // =========================================================
        // TABLE
        // =========================================================

        TableView<
                PreferenceFillingPage.Preference
        > table =
                new TableView<>();

        table.setItems(
                preferences
        );

        table.setPrefHeight(
                450
        );

        table.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        TableColumn<
                PreferenceFillingPage.Preference,
                Integer
        > numberColumn =
                new TableColumn<>(
                        "Preference No."
                );

        numberColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue()
                                        .getPreferenceNumber()
                        )
        );

        TableColumn<
                PreferenceFillingPage.Preference,
                String
        > collegeColumn =
                new TableColumn<>(
                        "College"
                );

        collegeColumn.setCellValueFactory(
                data ->
                        new ReadOnlyStringWrapper(
                                data.getValue()
                                        .getCollege()
                        )
        );

        TableColumn<
                PreferenceFillingPage.Preference,
                String
        > branchColumn =
                new TableColumn<>(
                        "Branch"
                );

        branchColumn.setCellValueFactory(
                data ->
                        new ReadOnlyStringWrapper(
                                data.getValue()
                                        .getBranch()
                        )
        );

        table.getColumns()
                .addAll(
                        numberColumn,
                        collegeColumn,
                        branchColumn
                );

        VBox tableCard =
                new VBox(
                        12,
                        table
                );

        tableCard.setPadding(
                new Insets(20)
        );

        tableCard.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:14;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:14;"
        );

        // =========================================================
        // EDIT BUTTON
        // =========================================================

        Button editButton =
                new Button(
                        "← Edit Preferences"
                );

        styleSecondaryButton(
                editButton
        );

        editButton.setOnAction(e ->

                Navigation.goTo(
                        PreferenceFillingPage
                                .getScene()
                )
        );

        // =========================================================
        // LOCK BUTTON
        // =========================================================

        Button lockButton =
                new Button(
                        locked
                                ? "✓ Choices Locked"
                                : "Lock Choices ✓"
                );

        stylePrimaryButton(
                lockButton
        );

        if (locked) {

            lockButton.setDisable(
                    true
            );

            editButton.setText(
                    "← View Preferences"
            );
        }

        // =========================================================
        // LOCK ACTION
        // =========================================================

        lockButton.setOnAction(e -> {

            // CHECK AGAIN FROM FIREBASE

            if (
                    preferenceDAO
                            .isPreferenceLocked()
            ) {

                showAlert(
                        Alert.AlertType.INFORMATION,
                        "Option Form",
                        "Your option form is already locked."
                );

                return;
            }

            if (preferences.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Option Form",
                        "Please add at least one preference."
                );

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
                    "Once locked, you cannot edit "
                            + "your preference list."
            );

            Optional<ButtonType> result =
                    confirmation.showAndWait();

            if (
                    result.isEmpty()
                    ||
                    result.get()
                            != ButtonType.OK
            ) {

                return;
            }

            boolean saved =
                    preferenceDAO
                            .savePreferences(
                                    preferences
                            );

            if (!saved) {

                showAlert(
                        Alert.AlertType.ERROR,
                        "Option Form",
                        "Failed to save preferences."
                );

                return;
            }

            showAlert(
                    Alert.AlertType.INFORMATION,
                    "Option Form",
                    "Your preferences have been locked successfully."
            );

            Navigation.goTo(
                    OptionConfirmationPage
                            .getScene()
            );
        });

        // =========================================================
        // BACK BUTTON
        // =========================================================

        Button backButton =
                new Button(
                        "← Back"
                );

        styleSecondaryButton(
                backButton
        );

        backButton.setOnAction(e ->

                Navigation.goTo(
                        PreferenceFillingPage
                                .getScene()
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox actions =
                new HBox(
                        12,
                        backButton,
                        editButton,
                        spacer,
                        lockButton
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        content.getChildren()
                .addAll(
                        title,
                        subtitle,
                        studentLabel,
                        statusLabel,
                        tableCard,
                        actions
                );

        // =========================================================
        // SCROLL
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .NEVER
        );

        scrollPane.setStyle(
                "-fx-background:"
                        + BG + ";" +
                "-fx-background-color:"
                        + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Option Form Preview",
                        scrollPane
                )
        );
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(
                44
        );

        button.setStyle(
                "-fx-background-color:"
                        + LIME + ";" +
                "-fx-text-fill:#071007;" +
                "-fx-background-radius:8;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 20 0 20;"
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(
                44
        );

        button.setStyle(
                "-fx-background-color:#253325;" +
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-background-radius:8;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 20 0 20;"
        );
    }

    private static void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}