package com.admitx.view;

import java.util.Optional;

import com.admitx.dao.PreferenceDAO;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

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
import javafx.scene.control.ProgressIndicator;
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

        Scene loadingScene =
                createLoadingScene();

        AsyncTaskRunner.run(
                () -> {

                    PreferenceDAO preferenceDAO =
                            new PreferenceDAO();

                    boolean locked =
                            preferenceDAO.isPreferenceLocked();

                    java.util.List<PreferenceFillingPage.Preference>
                            savedPreferences =
                            locked
                                    ? preferenceDAO.loadPreferences()
                                    : java.util.List.of();

                    return new PreviewPageData(
                            locked,
                            savedPreferences == null
                                    ? java.util.List.of()
                                    : savedPreferences
                    );
                },

                pageData ->
                        Navigation.goTo(
                                createPreviewScene(
                                        studentEmail,
                                        pageData
                                )
                        ),

                error -> {

                    error.printStackTrace();

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Loading Error",
                            "Could not load the option form. "
                                    + "Please check your internet connection."
                    );

                    Navigation.goTo(
                            PreferenceFillingPage.getScene()
                    );
                }
        );

        return loadingScene;
    }

    private static Scene createPreviewScene(
            String studentEmail,
            PreviewPageData pageData
    ) {

        boolean locked =
                pageData.locked();

        ObservableList<
                PreferenceFillingPage.Preference
        > preferences =
                PreferenceFillingPage
                        .getPreferences();

        if (locked) {

            preferences.setAll(
                    pageData.savedPreferences()
            );
        }

        // =========================================================
        // MAIN
        // =========================================================

        VBox content =
                new VBox(24);

        content.setPadding(
                new Insets(20, 24, 30, 24)
        );

        content.setFillWidth(true);
        content.setMaxWidth(Double.MAX_VALUE);

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

        statusLabel.setPadding(new Insets(8, 12, 8, 12));
        statusLabel.setStyle(
                statusLabel.getStyle() +
                "-fx-background-color:#151C12;" +
                "-fx-background-radius:18px;" +
                "-fx-border-color:#344234;" +
                "-fx-border-radius:18px;"
        );

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

        styleTableColumn(numberColumn, "Preference No.");
        styleTableColumn(collegeColumn, "College");
        styleTableColumn(branchColumn, "Branch");

        table.setStyle(
                "-fx-background-color:#0F150F;" +
                "-fx-control-inner-background:#0F150F;" +
                "-fx-table-cell-border-color:" + BORDER + ";" +
                "-fx-table-header-border-color:" + BORDER + ";" +
                "-fx-text-background-color:" + WHITE + ";" +
                "-fx-selection-bar:" + LIME + ";" +
                "-fx-selection-bar-text:#0B100B;" +
                "-fx-selection-bar-non-focused:" + LIME + ";" +
                "-fx-selection-bar-non-focused-text:#0B100B;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:10px;" +
                "-fx-background-radius:10px;"
        );

        VBox tableCard =
                new VBox(
                        12,
                        table
                );

        tableCard.setPadding(
                new Insets(22)
        );

        tableCard.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:12px;"
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

            ObservableList<PreferenceFillingPage.Preference>
                    preferenceSnapshot =
                    javafx.collections.FXCollections
                            .observableArrayList(
                                    preferences
                            );

            lockButton.setDisable(true);
            editButton.setDisable(true);

            AsyncTaskRunner.run(
                    () -> {

                        PreferenceDAO preferenceDAO =
                                new PreferenceDAO();

                        if (preferenceDAO.isPreferenceLocked()) {

                            return new LockResult(
                                    true,
                                    false
                            );
                        }

                        boolean saved =
                                preferenceDAO.savePreferences(
                                        preferenceSnapshot
                                );

                        return new LockResult(
                                false,
                                saved
                        );
                    },

                    lockResult -> {

                        lockButton.setDisable(false);
                        editButton.setDisable(false);

                        if (lockResult.alreadyLocked()) {

                            showAlert(
                                    Alert.AlertType.INFORMATION,
                                    "Option Form",
                                    "Your option form is already locked."
                            );

                            Navigation.goTo(
                                    OptionConfirmationPage.getScene()
                            );

                            return;
                        }

                        if (!lockResult.saved()) {

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
                                OptionConfirmationPage.getScene()
                        );
                    },

                    error -> {

                        lockButton.setDisable(false);
                        editButton.setDisable(false);
                        error.printStackTrace();

                        showAlert(
                                Alert.AlertType.ERROR,
                                "Option Form",
                                "Failed to save preferences."
                        );
                    }
            );
        });

        // =========================================================
        // BACK BUTTON
        // =========================================================

        

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox actions =
                new HBox(
                        12,
                       
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

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setPannable(true);

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

    private static Scene createLoadingScene() {

        ProgressIndicator progressIndicator =
                new ProgressIndicator();

        progressIndicator.setPrefSize(
                55,
                55
        );

        Label loadingLabel =
                new Label(
                        "Loading option form preview..."
                );

        loadingLabel.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:14px;"
        );

        VBox loadingCard =
                new VBox(
                        18,
                        progressIndicator,
                        loadingLabel
                );

        loadingCard.setAlignment(Pos.CENTER);
        loadingCard.setPadding(new Insets(34));
        loadingCard.setMaxWidth(420);
        loadingCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:14px;" +
                "-fx-background-radius:14px;"
        );

        VBox content = new VBox(loadingCard);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40));
        content.setStyle("-fx-background-color:" + BG + ";");

        return new Scene(
                StudentLayout.create(
                        "Option Form Preview",
                        content
                )
        );
    }

    private record PreviewPageData(
            boolean locked,
            java.util.List<PreferenceFillingPage.Preference>
                    savedPreferences
    ) {
    }

    private record LockResult(
            boolean alreadyLocked,
            boolean saved
    ) {
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(44);

        String normal =
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#071007;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 20 0 20;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#D0FF4D;" +
                "-fx-text-fill:#071007;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 20 0 20;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) button.setStyle(normal);
        });
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(44);

        String normal =
                "-fx-background-color:#171F17;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-border-color:#344034;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 20 0 20;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#202B20;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 20 0 20;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) button.setStyle(normal);
        });
    }

    private static <T> void styleTableColumn(
            TableColumn<PreferenceFillingPage.Preference, T> column,
            String title
    ) {
        Label header = new Label(title);
        header.setStyle(
                "-fx-text-fill:#172017;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );
        column.setText(null);
        column.setGraphic(header);
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
