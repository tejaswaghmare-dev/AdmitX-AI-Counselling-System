package com.admitx.view;

import com.admitx.view.Navigation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class CollegeManagementPage {

    public static Scene getScene() {

        VBox root = new VBox(20);
        root.setPadding(new Insets(35, 40, 40, 40));
        root.setAlignment(Pos.TOP_LEFT);
        root.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("🏛️ College Management");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Add, edit and manage colleges");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Form Card
        VBox formCard = new VBox(15);
        formCard.setPadding(new Insets(20, 25, 25, 25));
        formCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 200px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 13px;";

        HBox form = new HBox(10);
        form.setAlignment(Pos.CENTER_LEFT);

        TextField collegeName = new TextField();
        collegeName.setPromptText("College Name");
        collegeName.setStyle(fieldStyle);

        TextField district = new TextField();
        district.setPromptText("District");
        district.setStyle(fieldStyle);

        TextField university = new TextField();
        university.setPromptText("University");
        university.setStyle(fieldStyle);

        TextField branch = new TextField();
        branch.setPromptText("Branch");
        branch.setStyle(fieldStyle);

        TextField intake = new TextField();
        intake.setPromptText("Intake");
        intake.setStyle(fieldStyle);

        form.getChildren().addAll(collegeName, district, university, branch, intake);

        // Action Buttons
        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_LEFT);

        Button add = createActionButton("➕ Add College", "#1E3A5F", "#E8EDF5");
        Button edit = createActionButton("✏️ Edit College", "#1E3A5F", "#E8EDF5");
        Button delete = createActionButton("🗑️ Delete College", "#7F1D1D", "#FCA5A5");

        actions.getChildren().addAll(add, edit, delete);

        formCard.getChildren().addAll(form, actions);

        // Table
        TableView<College> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );

        TableColumn<College, String> nameColumn = new TableColumn<>("College");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(280);
        nameColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, String> districtColumn = new TableColumn<>("District");
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
        districtColumn.setPrefWidth(150);
        districtColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, String> universityColumn = new TableColumn<>("University");
        universityColumn.setCellValueFactory(new PropertyValueFactory<>("university"));
        universityColumn.setPrefWidth(200);
        universityColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, String> branchColumn = new TableColumn<>("Branch");
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));
        branchColumn.setPrefWidth(200);
        branchColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, Integer> intakeColumn = new TableColumn<>("Intake");
        intakeColumn.setCellValueFactory(new PropertyValueFactory<>("intake"));
        intakeColumn.setPrefWidth(100);
        intakeColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        table.getColumns().addAll(nameColumn, districtColumn, universityColumn, branchColumn, intakeColumn);
        table.setPrefHeight(300);

        table.setRowFactory(tv -> new TableRow<College>() {
            @Override
            protected void updateItem(College item, boolean empty) {
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

        ObservableList<College> colleges = FXCollections.observableArrayList(
                new College("College of Engineering Pune", "Pune", "SPPU", "Computer Engineering", 120),
                new College("Vishwakarma Institute of Technology", "Pune", "SPPU", "Information Technology", 180)
        );

        table.setItems(colleges);

        // Keep all original logic
        add.setOnAction(e -> {
            if (collegeName.getText().isBlank() || district.getText().isBlank() || 
                university.getText().isBlank() || branch.getText().isBlank() || intake.getText().isBlank()) {
                message("Error", "Please fill all fields.");
                return;
            }
            try {
                colleges.add(new College(
                        collegeName.getText(),
                        district.getText(),
                        university.getText(),
                        branch.getText(),
                        Integer.parseInt(intake.getText())
                ));
                clear(collegeName, district, university, branch, intake);
                message("Success", "College added successfully.");
            } catch (NumberFormatException ex) {
                message("Error", "Intake must be a number.");
            }
        });

        edit.setOnAction(e -> {
            College selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                message("Error", "Select a college first.");
                return;
            }
            selected.setName(collegeName.getText());
            selected.setDistrict(district.getText());
            selected.setUniversity(university.getText());
            selected.setBranch(branch.getText());
            try {
                selected.setIntake(Integer.parseInt(intake.getText()));
                table.refresh();
                message("Success", "College updated successfully.");
            } catch (NumberFormatException ex) {
                message("Error", "Intake must be a number.");
            }
        });

        delete.setOnAction(e -> {
            College selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                message("Error", "Select a college first.");
                return;
            }
            colleges.remove(selected);
            message("Success", "College deleted successfully.");
        });

        table.setOnMouseClicked(e -> {
            College selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                collegeName.setText(selected.getName());
                district.setText(selected.getDistrict());
                university.setText(selected.getUniversity());
                branch.setText(selected.getBranch());
                intake.setText(String.valueOf(selected.getIntake()));
            }
        });

        Button back = createActionButton("← Back to Dashboard", "transparent", "#8AA8C7");
        back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 180px;" +
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
                "-fx-pref-width: 180px;" +
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
                "-fx-pref-width: 180px;" +
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

        root.getChildren().addAll(title, subtitle, formCard, table, back);

        return new Scene(root, 1250, 800);
    }

    private static Button createActionButton(String text, String bgColor, String textColor) {
        Button button = new Button(text);
        button.setPrefHeight(40);
        button.setPrefWidth(150);
        button.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 13px;"
        );
        if (!bgColor.equals("transparent")) {
            button.setOnMouseEntered(e ->
                button.setStyle(
                    "-fx-background-color: " + (bgColor.equals("#7F1D1D") ? "#991B1B" : "#2A4A75") + ";" +
                    "-fx-text-fill: " + textColor + ";" +
                    "-fx-background-radius: 8px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;" +
                    "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                    "-fx-border-radius: 8px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-font-size: 13px;"
                )
            );
            button.setOnMouseExited(e ->
                button.setStyle(
                    "-fx-background-color: " + bgColor + ";" +
                    "-fx-text-fill: " + textColor + ";" +
                    "-fx-background-radius: 8px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;" +
                    "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                    "-fx-border-radius: 8px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-font-size: 13px;"
                )
            );
        }
        return button;
    }

    private static void clear(TextField a, TextField b, TextField c, TextField d, TextField e) {
        a.clear(); b.clear(); c.clear(); d.clear(); e.clear();
    }

    private static void message(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public static class College {
        private String name;
        private String district;
        private String university;
        private String branch;
        private int intake;

        public College(String name, String district, String university, String branch, int intake) {
            this.name = name;
            this.district = district;
            this.university = university;
            this.branch = branch;
            this.intake = intake;
        }

        public String getName() { return name; }
        public String getDistrict() { return district; }
        public String getUniversity() { return university; }
        public String getBranch() { return branch; }
        public int getIntake() { return intake; }

        public void setName(String name) { this.name = name; }
        public void setDistrict(String district) { this.district = district; }
        public void setUniversity(String university) { this.university = university; }
        public void setBranch(String branch) { this.branch = branch; }
        public void setIntake(int intake) { this.intake = intake; }
    }
}
