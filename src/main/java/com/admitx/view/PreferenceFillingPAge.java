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

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("Preference Filling");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label instruction = new Label("Add colleges and branches in the order of your preference.");
        instruction.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;"
        );

        // Form Card
        VBox formCard = new VBox(20);
        formCard.setPadding(new Insets(25, 30, 30, 30));
        formCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        // Add section
        HBox addBox = new HBox(15);
        addBox.setAlignment(Pos.CENTER_LEFT);

        ComboBox<String> collegeCombo = new ComboBox<>();
        collegeCombo.getItems().addAll(
                "College of Engineering Pune",
                "Vishwakarma Institute of Technology",
                "Pune Institute of Computer Technology",
                "Walchand College of Engineering",
                "Sardar Patel Institute of Technology"
        );
        collegeCombo.setPromptText("Select College");
        collegeCombo.setStyle(getComboBoxStyle());

        ComboBox<String> branchCombo = new ComboBox<>();
        branchCombo.getItems().addAll(
                "Computer Engineering",
                "Information Technology",
                "Mechanical Engineering",
                "Electronics Engineering",
                "Civil Engineering"
        );
        branchCombo.setPromptText("Select Branch");
        branchCombo.setStyle(getComboBoxStyle());

        Button addButton = new Button("+ Add Preference");
        addButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        addButton.setOnMouseEntered(e ->
            addButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        addButton.setOnMouseExited(e ->
            addButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        addBox.getChildren().addAll(collegeCombo, branchCombo, addButton);

        // Table with dark theme
        TableView<Preference> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );

        TableColumn<Preference, Number> numberColumn = new TableColumn<>("Preference No.");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("preferenceNumber"));
        numberColumn.setPrefWidth(120);
        numberColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<Preference, String> collegeColumn = new TableColumn<>("College");
        collegeColumn.setCellValueFactory(new PropertyValueFactory<>("college"));
        collegeColumn.setPrefWidth(300);
        collegeColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<Preference, String> branchColumn = new TableColumn<>("Branch");
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));
        branchColumn.setPrefWidth(250);
        branchColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        table.getColumns().addAll(numberColumn, collegeColumn, branchColumn);
        table.setItems(preferences);
        table.setPrefHeight(350);

        table.setRowFactory(tv -> new TableRow<Preference>() {
            @Override
            protected void updateItem(Preference item, boolean empty) {
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

        // Management buttons
        HBox managementButtons = new HBox(12);
        managementButtons.setAlignment(Pos.CENTER_LEFT);

        Button removeButton = new Button("🗑️ Remove");
        removeButton.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        removeButton.setOnMouseEntered(e ->
            removeButton.setStyle(
                "-fx-background-color: #991B1B;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.5);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        removeButton.setOnMouseExited(e ->
            removeButton.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        Button moveUpButton = new Button("⬆ Move Up");
        moveUpButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        moveUpButton.setOnMouseEntered(e ->
            moveUpButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        moveUpButton.setOnMouseExited(e ->
            moveUpButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        Button moveDownButton = new Button("⬇ Move Down");
        moveDownButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        moveDownButton.setOnMouseEntered(e ->
            moveDownButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        moveDownButton.setOnMouseExited(e ->
            moveDownButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        managementButtons.getChildren().addAll(removeButton, moveUpButton, moveDownButton);

        // Bottom buttons
        HBox bottomButtons = new HBox(15);
        bottomButtons.setAlignment(Pos.CENTER_RIGHT);

        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e ->
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e ->
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnAction(e -> Navigation.goTo(CollegeSearchPage.getScene()));

        Button previewButton = new Button("👁️ Preview Option Form");
        previewButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        previewButton.setOnMouseEntered(e ->
            previewButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        previewButton.setOnMouseExited(e ->
            previewButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 190px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        bottomButtons.getChildren().addAll(backButton, previewButton);

        formCard.getChildren().addAll(
                addBox,
                table,
                managementButtons,
                bottomButtons
        );

        content.getChildren().addAll(title, instruction, formCard);

        // Keep all original logic
        addButton.setOnAction(e -> {
            String college = collegeCombo.getValue();
            String branch = branchCombo.getValue();
            if (college == null || branch == null) {
                showAlert("Please select both college and branch.");
                return;
            }
            int preferenceNumber = preferences.size() + 1;
            preferences.add(new Preference(preferenceNumber, college, branch));
            collegeCombo.setValue(null);
            branchCombo.setValue(null);
            renumber();
        });

        removeButton.setOnAction(e -> {
            Preference selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("Please select a preference.");
                return;
            }
            preferences.remove(selected);
            renumber();
        });

        moveUpButton.setOnAction(e -> {
            int selectedIndex = table.getSelectionModel().getSelectedIndex();
            if (selectedIndex > 0) {
                Preference item = preferences.remove(selectedIndex);
                preferences.add(selectedIndex - 1, item);
                renumber();
                table.getSelectionModel().select(selectedIndex - 1);
            }
        });

        moveDownButton.setOnAction(e -> {
            int selectedIndex = table.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < preferences.size() - 1) {
                Preference item = preferences.remove(selectedIndex);
                preferences.add(selectedIndex + 1, item);
                renumber();
                table.getSelectionModel().select(selectedIndex + 1);
            }
        });

        previewButton.setOnAction(e -> {
            if (preferences.isEmpty()) {
                showAlert("Please add at least one preference.");
                return;
            }
            Navigation.goTo(OptionPreviewPage.getScene());
        });

        return new Scene(
                StudentLayout.create("Preference Filling", content)
        );
    }

    private static void renumber() {
        for (int i = 0; i < preferences.size(); i++) {
            preferences.get(i).setPreferenceNumber(i + 1);
        }
    }

    public static ObservableList<Preference> getPreferences() {
        return preferences;
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Preference Filling");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static String getComboBoxStyle() {
        return "-fx-background-color: rgba(10, 10, 15, 0.6);" +
               "-fx-text-fill: #E8EDF5;" +
               "-fx-prompt-text-fill: #5A7D9E;" +
               "-fx-pref-height: 40px;" +
               "-fx-pref-width: 220px;" +
               "-fx-background-radius: 8px;" +
               "-fx-border-color: rgba(74, 127, 181, 0.2);" +
               "-fx-border-radius: 8px;" +
               "-fx-border-width: 1px;" +
               "-fx-padding: 0 10 0 10;" +
               "-fx-font-size: 14px;";
    }

    public static class Preference {
        private int preferenceNumber;
        private final String college;
        private final String branch;

        public Preference(int preferenceNumber, String college, String branch) {
            this.preferenceNumber = preferenceNumber;
            this.college = college;
            this.branch = branch;
        }

        public int getPreferenceNumber() { return preferenceNumber; }
        public void setPreferenceNumber(int preferenceNumber) { this.preferenceNumber = preferenceNumber; }
        public String getCollege() { return college; }
        public String getBranch() { return branch; }
    }
}