package com.admitx.view;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.admitx.dao.MeritDAO;
import com.admitx.dao.CollegeDAO;
import com.admitx.dao.PreferenceDAO;
import com.admitx.model.College;
import com.admitx.model.Student;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PreferenceFillingPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#141B14";

    private static final String FIELD =
            "#101610";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String WHITE =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    private static final ObservableList<Preference>
            preferences =
            FXCollections.observableArrayList();

    public static Scene getScene() {

        // =========================================================
        // CHECK LOGIN
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
                    "Please login before filling preferences."
            );

            return StudentLoginPage.getScene();
        }

        // =========================================================
        // CHECK FINAL MERIT ELIGIBILITY
        // =========================================================

        MeritDAO meritDAO =
                new MeritDAO();

        if (
                meritDAO.getCurrentStudentFinalMerit()
                        == null
        ) {

        showAlert(
                Alert.AlertType.WARNING,
                "Final Merit Required",
                "Preference Filling is available only after "
                        + "your Final Merit List is published."
        );

        return FinalMeritPage.getScene();
        }

        // =========================================================
        // DAO
        // =========================================================

        PreferenceDAO preferenceDAO =
                new PreferenceDAO();

        boolean locked =
                preferenceDAO
                        .isPreferenceLocked();

        boolean choiceFillingOpen =
                preferenceDAO
                        .isChoiceFillingOpen();
        // =========================================================
        // LOAD SAVED PREFERENCES WHEN LOCKED
        // =========================================================

        if (locked) {

            preferences.clear();

            preferences.addAll(
                    preferenceDAO
                            .loadPreferences()
            );
        }

        // =========================================================
        // LOAD COLLEGES FROM FIREBASE
        // =========================================================

        CollegeDAO collegeDAO =
                new CollegeDAO();

        List<College> firebaseColleges =
                collegeDAO.getAllColleges();

        if (firebaseColleges == null) {

            firebaseColleges =
                    FXCollections
                            .observableArrayList();
        }

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        VBox content =
                new VBox(22);

        content.setPadding(
                new Insets(30)
        );

        content.setAlignment(
                Pos.TOP_LEFT
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
                        "Preference Filling"
                );

        title.setStyle(
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Add colleges and branches in the order of your preference."
                );

        subtitle.setStyle(
                "-fx-text-fill:"
                        + MUTED + ";" +
                "-fx-font-size:13px;"
        );

        // =========================================================
        // LOCK STATUS
        // =========================================================

        Label lockStatus =
                new Label();

        if (locked) {

                lockStatus.setText(
                        "✓ OPTION FORM LOCKED"
                );

                lockStatus.setStyle(
                        "-fx-text-fill:"
                                + LIME + ";" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;"
                );

        } else if (!choiceFillingOpen) {

                lockStatus.setText(
                        "● CHOICE FILLING CLOSED"
                );

                lockStatus.setStyle(
                        "-fx-text-fill:#E7D65A;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;"
                );

        } else {

                lockStatus.setText(
                        "● CHOICE FILLING OPEN"
                );

                lockStatus.setStyle(
                        "-fx-text-fill:"
                                + LIME + ";" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;"
                );
                }

        // =========================================================
        // COLLEGE COMBOBOX
        // =========================================================

        ComboBox<String> collegeBox =
                new ComboBox<>();

        collegeBox.setPromptText(
                "Select College"
        );

        collegeBox.setPrefWidth(
                350
        );

        collegeBox.setPrefHeight(
                42
        );

        // =========================================================
        // BRANCH COMBOBOX
        // =========================================================

        ComboBox<String> branchBox =
                new ComboBox<>();

        branchBox.setPromptText(
                "Select Branch"
        );

        branchBox.setPrefWidth(
                300
        );

        branchBox.setPrefHeight(
                42
        );

        branchBox.setDisable(
                true
        );

        styleComboBox(
                collegeBox
        );

        styleComboBox(
                branchBox
        );

        // =========================================================
        // UNIQUE COLLEGES
        // =========================================================

        Set<String> collegeNames =
                new LinkedHashSet<>();

        for (
                College college
                : firebaseColleges
        ) {

            if (
                    college.getCollegeName()
                            != null
                    &&
                    !college.getCollegeName()
                            .isBlank()
            ) {

                collegeNames.add(
                        college.getCollegeName()
                );
            }
        }

        collegeBox.getItems()
                .addAll(
                        collegeNames
                );

        // =========================================================
        // FINAL COLLEGE LIST FOR LAMBDA
        // =========================================================

        List<College> colleges =
                firebaseColleges;

        // =========================================================
        // WHEN COLLEGE SELECTED
        // =========================================================

        collegeBox.setOnAction(e -> {

            branchBox.getItems()
                    .clear();

            branchBox.setValue(
                    null
            );

            String selectedCollege =
                    collegeBox.getValue();

            if (
                    selectedCollege == null
            ) {

                branchBox.setDisable(
                        true
                );

                return;
            }

            Set<String> branches =
                    new LinkedHashSet<>();

            for (
                    College college
                    : colleges
            ) {

                if (
                        selectedCollege.equals(
                                college.getCollegeName()
                        )
                        &&
                        college.getBranch()
                                != null
                        &&
                        !college.getBranch()
                                .isBlank()
                ) {

                    branches.add(
                            college.getBranch()
                    );
                }
            }

            branchBox.getItems()
                    .addAll(
                            branches
                    );

            branchBox.setDisable(
                    branches.isEmpty()
            );
        });

        // =========================================================
        // ADD BUTTON
        // =========================================================

        Button addButton =
                new Button(
                        "+ Add Preference"
                );

        stylePrimaryButton(
                addButton
        );

        // =========================================================
        // FORM CARD
        // =========================================================

        HBox formRow =
                new HBox(
                        15,
                        collegeBox,
                        branchBox,
                        addButton
                );

        formRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox formCard =
                new VBox(
                        15,
                        new Label(
                                "Select College & Branch"
                        ),
                        formRow
                );

        ((Label) formCard
                .getChildren()
                .get(0))
                .setStyle(
                        "-fx-text-fill:"
                                + WHITE + ";" +
                        "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;"
                );

        formCard.setPadding(
                new Insets(20)
        );

        formCard.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:14;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:14;"
        );

        // =========================================================
        // TABLE
        // =========================================================

        TableView<Preference> table =
                new TableView<>();

        table.setItems(
                preferences
        );

        table.setPrefHeight(
                400
        );

        table.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        TableColumn<Preference, Integer>
                numberColumn =
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

        TableColumn<Preference, String>
                collegeColumn =
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

        TableColumn<Preference, String>
                branchColumn =
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

        // =========================================================
        // ADD ACTION
        // =========================================================

        addButton.setOnAction(e -> {

            if (
                    preferenceDAO
                            .isPreferenceLocked()
            ) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Option Form Locked",
                        "Your option form is already locked."
                );

                return;
            }

            String selectedCollege =
                    collegeBox.getValue();

            String selectedBranch =
                    branchBox.getValue();

            if (
                    selectedCollege == null ||
                    selectedBranch == null
            ) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Preference",
                        "Please select college and branch."
                );

                return;
            }

            // CHECK DUPLICATE
            for (
                    Preference preference
                    : preferences
            ) {

                if (
                        preference.getCollege()
                                .equals(
                                        selectedCollege
                                )
                        &&
                        preference.getBranch()
                                .equals(
                                        selectedBranch
                                )
                ) {

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Duplicate Preference",
                            "This college and branch is already added."
                    );

                    return;
                }
            }

            preferences.add(
                    new Preference(
                            preferences.size()
                                    + 1,
                            selectedCollege,
                            selectedBranch
                    )
            );

            collegeBox.setValue(
                    null
            );

            branchBox.setValue(
                    null
            );

            branchBox.getItems()
                    .clear();

            branchBox.setDisable(
                    true
            );
        });

        // =========================================================
        // REMOVE BUTTON
        // =========================================================

        Button removeButton =
                new Button(
                        "Remove"
                );

        styleDangerButton(
                removeButton
        );

        removeButton.setOnAction(e -> {

            Preference selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Preference",
                        "Please select a preference to remove."
                );

                return;
            }

            preferences.remove(
                    selected
            );

            renumber();
        });

        // =========================================================
        // MOVE UP
        // =========================================================

        Button moveUpButton =
                new Button(
                        "↑ Move Up"
                );

        styleSecondaryButton(
                moveUpButton
        );

        moveUpButton.setOnAction(e -> {

            int index =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (index > 0) {

                Preference preference =
                        preferences.remove(
                                index
                        );

                preferences.add(
                        index - 1,
                        preference
                );

                renumber();

                table.getSelectionModel()
                        .select(
                                index - 1
                        );
            }
        });

        // =========================================================
        // MOVE DOWN
        // =========================================================

        Button moveDownButton =
                new Button(
                        "↓ Move Down"
                );

        styleSecondaryButton(
                moveDownButton
        );

        moveDownButton.setOnAction(e -> {

            int index =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (
                    index >= 0
                    &&
                    index <
                    preferences.size() - 1
            ) {

                Preference preference =
                        preferences.remove(
                                index
                        );

                preferences.add(
                        index + 1,
                        preference
                );

                renumber();

                table.getSelectionModel()
                        .select(
                                index + 1
                        );
            }
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
                        CollegeSearchPage
                                .getScene()
                )
        );

        // =========================================================
        // PREVIEW
        // =========================================================

        Button previewButton =
                new Button(
                        "Preview Option Form →"
                );

        stylePrimaryButton(
                previewButton
        );

        previewButton.setOnAction(e -> {

            if (preferences.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Preference",
                        "Please add at least one preference."
                );

                return;
            }

            Navigation.goTo(
                    OptionPreviewPage
                            .getScene()
            );
        });

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox actionRow =
                new HBox(
                        12,
                        removeButton,
                        moveUpButton,
                        moveDownButton,
                        spacer,
                        backButton,
                        previewButton
                );

        actionRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================================================
        // LOCK UI
        // =========================================================

        if (locked) {

            collegeBox.setDisable(
                    true
            );

            branchBox.setDisable(
                    true
            );

            addButton.setDisable(
                    true
            );

            removeButton.setDisable(
                    true
            );

            moveUpButton.setDisable(
                    true
            );

            moveDownButton.setDisable(
                    true
            );
        }

        // =========================================================
        // CONTENT
        // =========================================================

        content.getChildren()
                .addAll(
                        title,
                        subtitle,
                        lockStatus,
                        formCard,
                        table,
                        actionRow
                );

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
                        "Preference Filling",
                        scrollPane
                )
        );
    }

    // =============================================================
    // GET PREFERENCES
    // =============================================================

    public static ObservableList<Preference>
    getPreferences() {

        return preferences;
    }

    // =============================================================
    // RENUMBER
    // =============================================================

    private static void renumber() {

        for (
                int i = 0;
                i < preferences.size();
                i++
        ) {

            preferences
                    .get(i)
                    .setPreferenceNumber(
                            i + 1
                    );
        }
    }

    // =============================================================
    // STYLES
    // =============================================================

    private static void styleComboBox(
            ComboBox<String> box
    ) {

        box.setStyle(
                "-fx-background-color:"
                        + FIELD + ";" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;" +
                "-fx-text-fill:"
                        + WHITE + ";"
        );
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setStyle(
                "-fx-background-color:"
                        + LIME + ";" +
                "-fx-text-fill:#071007;" +
                "-fx-background-radius:8;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;"
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setStyle(
                "-fx-background-color:#253325;" +
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-background-radius:8;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;"
        );
    }

    private static void styleDangerButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setStyle(
                "-fx-background-color:#991B1B;" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:8;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;"
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

    // =============================================================
    // PREFERENCE MODEL
    // =============================================================

    public static class Preference {

        private int preferenceNumber;

        private String college;

        private String branch;

        public Preference(
                int preferenceNumber,
                String college,
                String branch
        ) {

            this.preferenceNumber =
                    preferenceNumber;

            this.college =
                    college;

            this.branch =
                    branch;
        }

        public int getPreferenceNumber() {

            return preferenceNumber;
        }

        public void setPreferenceNumber(
                int preferenceNumber
        ) {

            this.preferenceNumber =
                    preferenceNumber;
        }

        public String getCollege() {

            return college;
        }

        public void setCollege(
                String college
        ) {

            this.college =
                    college;
        }

        public String getBranch() {

            return branch;
        }

        public void setBranch(
                String branch
        ) {

            this.branch =
                    branch;
        }
    }
}