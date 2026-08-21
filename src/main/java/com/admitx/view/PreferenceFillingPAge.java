package com.example.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PreferenceFillingPage {

    private static final ObservableList<Preference> preferences =
            FXCollections.observableArrayList();

    public static Scene getScene() {

        Label title = new Label("Preference Filling");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label instruction = new Label(
                "Add colleges and branches in the order of your preference."
        );

        instruction.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #4D7C0F;"
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

        collegeCombo.setPromptText("Select College");

        ComboBox<String> branchCombo =
                new ComboBox<>();

        branchCombo.getItems().addAll(
                "Computer Engineering",
                "Information Technology",
                "Mechanical Engineering",
                "Electronics Engineering",
                "Civil Engineering"
        );

        branchCombo.setPromptText("Select Branch");

        Button addButton =
                new Button("Add Preference");

        addButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 38px;"
        );

        TableView<Preference> table =
                new TableView<>();

        TableColumn<Preference, Number> numberColumn =
                new TableColumn<>("Preference No.");

        numberColumn.setCellValueFactory(
                new PropertyValueFactory<>("preferenceNumber")
        );

        numberColumn.setPrefWidth(120);

        TableColumn<Preference, String> collegeColumn =
                new TableColumn<>("College");

        collegeColumn.setCellValueFactory(
                new PropertyValueFactory<>("college")
        );

        collegeColumn.setPrefWidth(300);

        TableColumn<Preference, String> branchColumn =
                new TableColumn<>("Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch")
        );

        branchColumn.setPrefWidth(250);

        table.getColumns().addAll(
                numberColumn,
                collegeColumn,
                branchColumn
        );

        table.setItems(preferences);

        table.setPrefHeight(400);

        addButton.setOnAction(e -> {

            String college =
                    collegeCombo.getValue();

            String branch =
                    branchCombo.getValue();

            if (college == null || branch == null) {

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
        });

        Button removeButton =
                new Button("Remove");

        removeButton.setStyle(
                "-fx-background-color: #DC2626;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;"
        );

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

            preferences.remove(selected);

            renumber();
        });

        Button moveUpButton =
                new Button("Move Up");

        moveUpButton.setStyle(
                "-fx-background-color: #3F6212;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;"
        );

        moveUpButton.setOnAction(e -> {

            int selectedIndex =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (selectedIndex > 0) {

                Preference item =
                        preferences.remove(selectedIndex);

                preferences.add(
                        selectedIndex - 1,
                        item
                );

                renumber();

                table.getSelectionModel()
                        .select(selectedIndex - 1);
            }
        });

        Button moveDownButton =
                new Button("Move Down");

        moveDownButton.setStyle(
                "-fx-background-color: #3F6212;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;"
        );

        moveDownButton.setOnAction(e -> {

            int selectedIndex =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (selectedIndex >= 0
                    && selectedIndex <
                    preferences.size() - 1) {

                Preference item =
                        preferences.remove(selectedIndex);

                preferences.add(
                        selectedIndex + 1,
                        item
                );

                renumber();

                table.getSelectionModel()
                        .select(selectedIndex + 1);
            }
        });

        HBox addBox =
                new HBox(
                        15,
                        collegeCombo,
                        branchCombo,
                        addButton
                );

        addBox.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox managementButtons =
                new HBox(
                        12,
                        removeButton,
                        moveUpButton,
                        moveDownButton
                );

        managementButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        Button backButton =
                new Button("Back");

        backButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;"
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        CollegeSearchPage.getScene()
                )
        );

        Button previewButton =
                new Button("Preview Option Form");

        previewButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;"
        );

        previewButton.setOnAction(e -> {

            if (preferences.isEmpty()) {

                showAlert(
                        "Please add at least one preference."
                );

                return;
            }

            Navigation.goTo(
                    OptionPreviewPage.getScene()
            );
        });

        HBox bottomButtons =
                new HBox(
                        15,
                        backButton,
                        previewButton
                );

        bottomButtons.setAlignment(
                Pos.CENTER_RIGHT
        );

        VBox content =
                new VBox(
                        20,
                        title,
                        instruction,
                        addBox,
                        table,
                        managementButtons,
                        bottomButtons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Preference Filling",
                        content
                )
        );
    }

    private static void renumber() {

        for (int i = 0;
             i < preferences.size();
             i++) {

            preferences.get(i)
                    .setPreferenceNumber(i + 1);
        }
    }

    public static ObservableList<Preference>
    getPreferences() {

        return preferences;
    }

    private static void showAlert(
            String message) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Preference Filling");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Preference {

        private int preferenceNumber;

        private final String college;

        private final String branch;

        public Preference(
                int preferenceNumber,
                String college,
                String branch) {

            this.preferenceNumber =
                    preferenceNumber;

            this.college = college;
            this.branch = branch;
        }

        public int getPreferenceNumber() {
            return preferenceNumber;
        }

        public void setPreferenceNumber(
                int preferenceNumber) {

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