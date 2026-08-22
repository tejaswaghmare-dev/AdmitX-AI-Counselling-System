package com.admitx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class PreferenceFillingPage {

    private static final ObservableList<Preference> preferences =
            FXCollections.observableArrayList();

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String FIELD = "#101610";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#DC2626";

    public static Scene getScene() {

        Label title =
                new Label("Preference Filling");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label instruction =
                new Label(
                        "Add colleges and branches in the exact order of your preference."
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

        ComboBox<String> collegeCombo =
                new ComboBox<>();

        collegeCombo.getItems().addAll(
                "College of Engineering Pune",
                "Vishwakarma Institute of Technology",
                "Pune Institute of Computer Technology",
                "Walchand College of Engineering",
                "Sardar Patel Institute of Technology"
        );

        collegeCombo.setPromptText(
                "Select College"
        );

        ComboBox<String> branchCombo =
                new ComboBox<>();

        branchCombo.getItems().addAll(
                "Computer Engineering",
                "Information Technology",
                "Mechanical Engineering",
                "Electronics Engineering",
                "Civil Engineering"
        );

        branchCombo.setPromptText(
                "Select Branch"
        );

        styleField(collegeCombo);
        styleField(branchCombo);

        Button addButton =
                new Button("Add Preference");

        stylePrimaryButton(addButton);

        HBox addBox =
                new HBox(
                        12,
                        collegeCombo,
                        branchCombo,
                        addButton
                );

        addBox.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox addCard =
                new VBox(
                        14,
                        createSectionTitle("ADD PREFERENCE"),
                        addBox
                );

        addCard.setPadding(
                new Insets(20)
        );

        addCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        TableView<Preference> table =
                new TableView<>();

        TableColumn<Preference, Number> numberColumn =
                new TableColumn<>("Preference No.");

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>("preferenceNumber")
        );

        TableColumn<Preference, String> collegeColumn =
                new TableColumn<>("College");

        collegeColumn.setCellValueFactory(
                new PropertyValueFactory<>("college")
        );

        TableColumn<Preference, String> branchColumn =
                new TableColumn<>("Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch")
        );

        table.getColumns().addAll(
                numberColumn,
                collegeColumn,
                branchColumn
        );

        table.setItems(
                preferences
        );

        table.setPrefHeight(
                390
        );

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

        Label countLabel =
                new Label();

        updateCountLabel(
                countLabel
        );

        Button removeButton =
                new Button("Remove");

        styleDangerButton(
                removeButton
        );

        Button moveUpButton =
                new Button("Move Up");

        styleSecondaryButton(
                moveUpButton
        );

        Button moveDownButton =
                new Button("Move Down");

        styleSecondaryButton(
                moveDownButton
        );

        HBox managementButtons =
                new HBox(
                        10,
                        removeButton,
                        moveUpButton,
                        moveDownButton
                );

        managementButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox tableCard =
                new VBox(
                        12,
                        createSectionTitle("YOUR PREFERENCES"),
                        countLabel,
                        table,
                        managementButtons
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

        addButton.setOnAction(e -> {

            String college =
                    collegeCombo.getValue();

            String branch =
                    branchCombo.getValue();

            if (
                    college == null ||
                    branch == null
            ) {

                showAlert(
                        "Please select both college and branch."
                );

                return;
            }

            int preferenceNumber =
                    preferences.size() + 1;

            preferences.add(
                    new Preference(
                            preferenceNumber,
                            college,
                            branch
                    )
            );

            collegeCombo.setValue(null);
            branchCombo.setValue(null);

            renumber();

            table.refresh();

            updateCountLabel(
                    countLabel
            );
        });

        removeButton.setOnAction(e -> {

            Preference selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showAlert(
                        "Please select a preference."
                );

                return;
            }

            preferences.remove(
                    selected
            );

            renumber();

            table.refresh();

            updateCountLabel(
                    countLabel
            );
        });

        moveUpButton.setOnAction(e -> {

            int selectedIndex =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (selectedIndex > 0) {

                Preference item =
                        preferences.remove(
                                selectedIndex
                        );

                preferences.add(
                        selectedIndex - 1,
                        item
                );

                renumber();

                table.refresh();

                table.getSelectionModel()
                        .select(
                                selectedIndex - 1
                        );
            }
        });

        moveDownButton.setOnAction(e -> {

            int selectedIndex =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (
                    selectedIndex >= 0 &&
                    selectedIndex <
                            preferences.size() - 1
            ) {

                Preference item =
                        preferences.remove(
                                selectedIndex
                        );

                preferences.add(
                        selectedIndex + 1,
                        item
                );

                renumber();

                table.refresh();

                table.getSelectionModel()
                        .select(
                                selectedIndex + 1
                        );
            }
        });

        Label note =
                new Label(
                        "Your order matters. Preference 1 will be considered before Preference 2 during allotment."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;" +
                "-fx-font-size: 12px;"
        );

        Button backButton =
                new Button("← Back");

        styleSecondaryButton(
                backButton
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        CollegeSearchPage.getScene()
                )
        );

        Button previewButton =
                new Button(
                        "Preview Option Form →"
                );

        stylePrimaryButton(
                previewButton
        );

        previewButton.setOnAction(e -> {

            if (
                    preferences.isEmpty()
            ) {

                showAlert(
                        "Please add at least one preference."
                );

                return;
            }

            Navigation.goTo(
                    OptionPreviewPage.getScene()
            );
        });

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox bottomButtons =
                new HBox(
                        12,
                        backButton,
                        spacer,
                        previewButton
                );

        bottomButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        addCard,
                        tableCard,
                        note,
                        bottomButtons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Preference Filling",
                        scrollPane
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

    private static void styleField(
            Control control
    ) {

        control.setPrefHeight(
                40
        );

        control.setPrefWidth(
                260
        );

        control.setStyle(
                "-fx-background-color: " + FIELD + ";" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

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

        button.setPrefHeight(
                40
        );

        button.setPadding(
                new Insets(
                        0,
                        16,
                        0,
                        16
                )
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleDangerButton(
            Button button
    ) {

        button.setPrefHeight(
                40
        );

        button.setPadding(
                new Insets(
                        0,
                        16,
                        0,
                        16
                )
        );

        button.setStyle(
                "-fx-background-color: " + RED + ";" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }

    private static void updateCountLabel(
            Label label
    ) {

        label.setText(
                preferences.size() +
                        " preferences added"
        );

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );
    }

    private static void renumber() {

        for (
                int i = 0;
                i < preferences.size();
                i++
        ) {

            preferences.get(i)
                    .setPreferenceNumber(
                            i + 1
                    );
        }
    }

    public static ObservableList<Preference>
    getPreferences() {

        return preferences;
    }

    private static void showAlert(
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                "Preference Filling"
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }

    public static class Preference {

        private int preferenceNumber;

        private final String college;

        private final String branch;

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

        public String getBranch() {

            return branch;
        }
    }
}