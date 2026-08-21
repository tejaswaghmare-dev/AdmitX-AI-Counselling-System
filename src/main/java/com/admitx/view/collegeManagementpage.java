package com.example.view;

import com.example.view.Navigation;

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

        Label title = new Label("College Management");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        TextField collegeName = new TextField();
        collegeName.setPromptText("College Name");

        TextField district = new TextField();
        district.setPromptText("District");

        TextField university = new TextField();
        university.setPromptText("University");

        TextField branch = new TextField();
        branch.setPromptText("Branch");

        TextField intake = new TextField();
        intake.setPromptText("Intake");

        Button add = button("Add College");

        Button edit = button("Edit College");

        Button delete = button("Delete College");

        TableView<College> table =
                new TableView<>();

        TableColumn<College, String> nameColumn =
                new TableColumn<>("College");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<College, String> districtColumn =
                new TableColumn<>("District");

        districtColumn.setCellValueFactory(
                new PropertyValueFactory<>("district")
        );

        TableColumn<College, String> universityColumn =
                new TableColumn<>("University");

        universityColumn.setCellValueFactory(
                new PropertyValueFactory<>("university")
        );

        TableColumn<College, String> branchColumn =
                new TableColumn<>("Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch")
        );

        TableColumn<College, Integer> intakeColumn =
                new TableColumn<>("Intake");

        intakeColumn.setCellValueFactory(
                new PropertyValueFactory<>("intake")
        );

        table.getColumns().addAll(
                nameColumn,
                districtColumn,
                universityColumn,
                branchColumn,
                intakeColumn
        );

        ObservableList<College> colleges =
                FXCollections.observableArrayList(
                        new College(
                                "College of Engineering Pune",
                                "Pune",
                                "SPPU",
                                "Computer Engineering",
                                120
                        ),
                        new College(
                                "Vishwakarma Institute of Technology",
                                "Pune",
                                "SPPU",
                                "Information Technology",
                                180
                        )
                );

        table.setItems(colleges);
        table.setPrefHeight(350);

        add.setOnAction(e -> {

            if (collegeName.getText().isBlank()
                    || district.getText().isBlank()
                    || university.getText().isBlank()
                    || branch.getText().isBlank()
                    || intake.getText().isBlank()) {

                message(
                        "Error",
                        "Please fill all fields."
                );

                return;
            }

            try {

                colleges.add(
                        new College(
                                collegeName.getText(),
                                district.getText(),
                                university.getText(),
                                branch.getText(),
                                Integer.parseInt(
                                        intake.getText()
                                )
                        )
                );

                clear(
                        collegeName,
                        district,
                        university,
                        branch,
                        intake
                );

                message(
                        "Success",
                        "College added successfully."
                );

            } catch (NumberFormatException ex) {

                message(
                        "Error",
                        "Intake must be a number."
                );
            }
        });

        edit.setOnAction(e -> {

            College selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message(
                        "Error",
                        "Select a college first."
                );

                return;
            }

            selected.setName(
                    collegeName.getText()
            );

            selected.setDistrict(
                    district.getText()
            );

            selected.setUniversity(
                    university.getText()
            );

            selected.setBranch(
                    branch.getText()
            );

            try {

                selected.setIntake(
                        Integer.parseInt(
                                intake.getText()
                        )
                );

                table.refresh();

                message(
                        "Success",
                        "College updated successfully."
                );

            } catch (NumberFormatException ex) {

                message(
                        "Error",
                        "Intake must be a number."
                );
            }
        });

        delete.setOnAction(e -> {

            College selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message(
                        "Error",
                        "Select a college first."
                );

                return;
            }

            colleges.remove(selected);

            message(
                    "Success",
                    "College deleted successfully."
            );
        });

        table.setOnMouseClicked(e -> {

            College selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                collegeName.setText(
                        selected.getName()
                );

                district.setText(
                        selected.getDistrict()
                );

                university.setText(
                        selected.getUniversity()
                );

                branch.setText(
                        selected.getBranch()
                );

                intake.setText(
                        String.valueOf(
                                selected.getIntake()
                        )
                );
            }
        });

        HBox form = new HBox(
                10,
                collegeName,
                district,
                university,
                branch,
                intake
        );

        form.setAlignment(
                Pos.CENTER
        );

        HBox actions = new HBox(
                10,
                add,
                edit,
                delete
        );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

       

        VBox root = new VBox(
                20,
                title,
                form,
                actions,
                table
                
        );

        root.setPadding(
                new Insets(30)
        );

        root.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        BorderPane layout =
        CounsellorLayout.create(
                "Colleges",
                root
        );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static Button button(String text) {

        Button button =
                new Button(text);

        button.setPrefHeight(40);
        button.setPrefWidth(150);

        button.setStyle(
                "-fx-background-color: #0A0A0A;" +
                "-fx-text-fill: white;"
        );

        return button;
    }

    private static void clear(
            TextField a,
            TextField b,
            TextField c,
            TextField d,
            TextField e
    ) {

        a.clear();
        b.clear();
        c.clear();
        d.clear();
        e.clear();
    }

    private static void message(
            String title,
            String text
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

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

        public College(
                String name,
                String district,
                String university,
                String branch,
                int intake
        ) {
            this.name = name;
            this.district = district;
            this.university = university;
            this.branch = branch;
            this.intake = intake;
        }

        public String getName() {
            return name;
        }

        public String getDistrict() {
            return district;
        }

        public String getUniversity() {
            return university;
        }

        public String getBranch() {
            return branch;
        }

        public int getIntake() {
            return intake;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public void setBranch(String branch) {
            this.branch = branch;
        }

        public void setIntake(int intake) {
            this.intake = intake;
        }
    }
}