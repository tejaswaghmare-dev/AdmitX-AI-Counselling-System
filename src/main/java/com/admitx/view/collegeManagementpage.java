package com.admitx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class CollegeManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String INPUT = "#0D120D";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String BORDER = "#293529";

    public static Scene getScene() {

        // =========================================
        // PAGE HEADING
        // =========================================

        Label title = new Label("College Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle = new Label(
                "Add, update and manage colleges participating in CAP counselling."
        );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading = new VBox(
                4,
                title,
                subtitle
        );

        // =========================================
        // INPUT FIELDS
        // =========================================

        TextField collegeName =
                createTextField("College Name");

        TextField district =
                createTextField("District");

        TextField university =
                createTextField("University");

        TextField branch =
                createTextField("Branch");

        TextField intake =
                createTextField("Intake");

        // =========================================
        // FORM LABELS
        // =========================================

        VBox collegeBox =
                createFieldBox(
                        "College Name",
                        collegeName
                );

        VBox districtBox =
                createFieldBox(
                        "District",
                        district
                );

        VBox universityBox =
                createFieldBox(
                        "University",
                        university
                );

        VBox branchBox =
                createFieldBox(
                        "Branch",
                        branch
                );

        VBox intakeBox =
                createFieldBox(
                        "Intake",
                        intake
                );

        GridPane form =
                new GridPane();

        form.setHgap(18);
        form.setVgap(16);

        form.add(
                collegeBox,
                0,
                0
        );

        form.add(
                districtBox,
                1,
                0
        );

        form.add(
                universityBox,
                2,
                0
        );

        form.add(
                branchBox,
                0,
                1
        );

        form.add(
                intakeBox,
                1,
                1
        );

        ColumnConstraints c1 =
                new ColumnConstraints();

        ColumnConstraints c2 =
                new ColumnConstraints();

        ColumnConstraints c3 =
                new ColumnConstraints();

        c1.setPercentWidth(33.33);
        c2.setPercentWidth(33.33);
        c3.setPercentWidth(33.33);

        form.getColumnConstraints().addAll(
                c1,
                c2,
                c3
        );

        // =========================================
        // FORM CARD
        // =========================================

        Label formTitle =
                new Label("College Information");

        formTitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label formDescription =
                new Label(
                        "Enter college and branch details below."
                );

        formDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox formHeading =
                new VBox(
                        3,
                        formTitle,
                        formDescription
                );

        Button add =
                createPrimaryButton(
                        "Add College",
                        140
                );

        Button edit =
                createDarkButton(
                        "Update College",
                        150
                );

        Button clearButton =
                createDarkButton(
                        "Clear",
                        100
                );

        Button delete =
                createDangerButton(
                        "Delete College",
                        140
                );

        HBox actions =
                new HBox(
                        10,
                        add,
                        edit,
                        clearButton,
                        delete
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox formCard =
                new VBox(
                        16,
                        formHeading,
                        form,
                        actions
                );

        formCard.setPadding(
                new Insets(20)
        );

        formCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        // =========================================
        // TABLE
        // =========================================

        Label tableTitle =
                new Label("Registered Colleges");

        tableTitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label tableDescription =
                new Label(
                        "Select a college from the table to edit or delete it."
                );

        tableDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox tableHeading =
                new VBox(
                        3,
                        tableTitle,
                        tableDescription
                );

        TableView<College> table =
                new TableView<>();

        TableColumn<College, String> nameColumn =
                new TableColumn<>("College");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "name"
                )
        );

        TableColumn<College, String> districtColumn =
                new TableColumn<>("District");

        districtColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "district"
                )
        );

        TableColumn<College, String> universityColumn =
                new TableColumn<>("University");

        universityColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "university"
                )
        );

        TableColumn<College, String> branchColumn =
                new TableColumn<>("Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "branch"
                )
        );

        TableColumn<College, Integer> intakeColumn =
                new TableColumn<>("Intake");

        intakeColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "intake"
                )
        );

        table.getColumns().addAll(
                nameColumn,
                districtColumn,
                universityColumn,
                branchColumn,
                intakeColumn
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setPrefHeight(330);

        table.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        // =========================================
        // COLLEGE DATA
        // =========================================

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

        table.setItems(
                colleges
        );

        // =========================================
        // ADD COLLEGE
        // =========================================

        add.setOnAction(e -> {

            if (
                    collegeName.getText().isBlank()
                    || district.getText().isBlank()
                    || university.getText().isBlank()
                    || branch.getText().isBlank()
                    || intake.getText().isBlank()
            ) {

                message(
                        "Missing Information",
                        "Please fill all college details."
                );

                return;
            }

            try {

                int intakeValue =
                        Integer.parseInt(
                                intake.getText()
                        );

                if (intakeValue <= 0) {

                    message(
                            "Invalid Intake",
                            "Intake must be greater than 0."
                    );

                    return;
                }

                colleges.add(
                        new College(
                                collegeName.getText(),
                                district.getText(),
                                university.getText(),
                                branch.getText(),
                                intakeValue
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
                        "College Added",
                        "College added successfully."
                );

            } catch (NumberFormatException ex) {

                message(
                        "Invalid Intake",
                        "Intake must be a valid number."
                );
            }
        });

        // =========================================
        // SELECT TABLE ROW
        // =========================================

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, selected) -> {

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
                        }
                );

        // =========================================
        // UPDATE COLLEGE
        // =========================================

        edit.setOnAction(e -> {

            College selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message(
                        "No College Selected",
                        "Please select a college from the table first."
                );

                return;
            }

            if (
                    collegeName.getText().isBlank()
                    || district.getText().isBlank()
                    || university.getText().isBlank()
                    || branch.getText().isBlank()
                    || intake.getText().isBlank()
            ) {

                message(
                        "Missing Information",
                        "Please fill all college details."
                );

                return;
            }

            try {

                int intakeValue =
                        Integer.parseInt(
                                intake.getText()
                        );

                if (intakeValue <= 0) {

                    message(
                            "Invalid Intake",
                            "Intake must be greater than 0."
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

                selected.setIntake(
                        intakeValue
                );

                table.refresh();

                message(
                        "College Updated",
                        "College details updated successfully."
                );

            } catch (NumberFormatException ex) {

                message(
                        "Invalid Intake",
                        "Intake must be a valid number."
                );
            }
        });

        // =========================================
        // DELETE
        // =========================================

        delete.setOnAction(e -> {

            College selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                message(
                        "No College Selected",
                        "Please select a college first."
                );

                return;
            }

            Alert confirmation =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmation.setTitle(
                    "Delete College"
            );

            confirmation.setHeaderText(
                    "Delete selected college?"
            );

            confirmation.setContentText(
                    selected.getName()
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (response == ButtonType.OK) {

                            colleges.remove(
                                    selected
                            );

                            clear(
                                    collegeName,
                                    district,
                                    university,
                                    branch,
                                    intake
                            );
                        }
                    });
        });

        // =========================================
        // CLEAR
        // =========================================

        clearButton.setOnAction(e -> {

            table.getSelectionModel()
                    .clearSelection();

            clear(
                    collegeName,
                    district,
                    university,
                    branch,
                    intake
            );
        });

        // =========================================
        // TABLE CARD
        // =========================================

        VBox tableCard =
                new VBox(
                        14,
                        tableHeading,
                        table
                );

        tableCard.setPadding(
                new Insets(20)
        );

        tableCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        VBox content =
                new VBox(
                        20,
                        heading,
                        formCard,
                        tableCard
                );

        content.setPadding(
                new Insets(5)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "Colleges",
                        content
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =========================================
    // TEXT FIELD
    // =========================================

    private static TextField createTextField(
            String prompt
    ) {

        TextField field =
                new TextField();

        field.setPromptText(
                prompt
        );

        field.setPrefHeight(
                40
        );

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setStyle(
                "-fx-background-color: " + INPUT + ";" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #687268;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 0 12 0 12;"
        );

        return field;
    }

    // =========================================
    // FIELD BOX
    // =========================================

    private static VBox createFieldBox(
            String text,
            TextField field
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox box =
                new VBox(
                        6,
                        label,
                        field
                );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        GridPane.setHgrow(
                box,
                Priority.ALWAYS
        );

        return box;
    }

    // =========================================
    // PRIMARY BUTTON
    // =========================================

    private static Button createPrimaryButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================
    // DARK BUTTON
    // =========================================

    private static Button createDarkButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color: #1C251C;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #354235;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================
    // DELETE BUTTON
    // =========================================

    private static Button createDangerButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color: #DC2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================================
    // CLEAR FORM
    // =========================================

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

    // =========================================
    // MESSAGE
    // =========================================

    private static void message(
            String title,
            String text
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                text
        );

        alert.showAndWait();
    }

    // =========================================
    // COLLEGE MODEL
    // =========================================

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

            this.name =
                    name;

            this.district =
                    district;

            this.university =
                    university;

            this.branch =
                    branch;

            this.intake =
                    intake;
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

        public void setName(
                String name
        ) {

            this.name =
                    name;
        }

        public void setDistrict(
                String district
        ) {

            this.district =
                    district;
        }

        public void setUniversity(
                String university
        ) {

            this.university =
                    university;
        }

        public void setBranch(
                String branch
        ) {

            this.branch =
                    branch;
        }

        public void setIntake(
                int intake
        ) {

            this.intake =
                    intake;
        }
    }
}