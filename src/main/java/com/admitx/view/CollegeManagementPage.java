package com.admitx.view;

import com.admitx.controller.CollegeAddController;
import com.admitx.model.College;

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
private static final String FIELD_HOVER = "#121A12";
private static final String RED = "#DC2626";

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
            6,
            title,
            subtitle
    );

    heading.setPadding(
            new Insets(0, 0, 4, 0)
    );

    // =========================================
    // INPUT FIELDS
    // =========================================

    TextField clgId =
            createTextField("College ID");

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

    TextField cutoff =
            createTextField("College Cutoff");

    // =========================================
    // FORM LABELS
    // =========================================

    VBox clgIdBox =
            createFieldBox("College ID", clgId);

    VBox collegeBox =
            createFieldBox("College Name", collegeName);

    VBox districtBox =
            createFieldBox("District", district);

    VBox universityBox =
            createFieldBox("University", university);

    VBox branchBox =
            createFieldBox("Branch", branch);

    VBox intakeBox =
            createFieldBox("Intake", intake);

    VBox cutoffBox =
            createFieldBox("College Cutoff (%)", cutoff);

    // =========================================
    // FORM GRID
    // =========================================

    GridPane form = new GridPane();

    form.setHgap(20);
    form.setVgap(18);

    // Row 1
    form.add(clgIdBox, 0, 0);
    form.add(collegeBox, 1, 0);
    form.add(districtBox, 2, 0);

    // Row 2
    form.add(universityBox, 0, 1);
    form.add(branchBox, 1, 1);
    form.add(intakeBox, 2, 1);

    // Row 3
    form.add(cutoffBox, 0, 2);

    ColumnConstraints c1 = new ColumnConstraints();
    ColumnConstraints c2 = new ColumnConstraints();
    ColumnConstraints c3 = new ColumnConstraints();

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
            "-fx-font-size: 17px;" +
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
                    12,
                    add,
                    edit,
                    clearButton,
                    delete
            );

    actions.setAlignment(Pos.CENTER_LEFT);

    VBox formCard =
            new VBox(
                    16,
                    formHeading,
                    form,
                    actions
            );

    formCard.setPadding(
            new Insets(22)
    );

    formCard.setStyle(
            "-fx-background-color: " + CARD + ";" +
            "-fx-background-radius: 12px;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 12px;"
    );

    // =========================================
    // TABLE
    // =========================================

    Label tableTitle =
            new Label("Registered Colleges");

    tableTitle.setStyle(
            "-fx-font-size: 17px;" +
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

    // =========================================
    // TABLE COLUMNS
    // =========================================

    TableColumn<College, String> clgIdColumn =
            new TableColumn<>("College ID");

    clgIdColumn.setCellValueFactory(
            new PropertyValueFactory<>("collegeID")
    );

    TableColumn<College, String> nameColumn =
            new TableColumn<>("College");

    nameColumn.setCellValueFactory(
            new PropertyValueFactory<>("collegeName")
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

    TableColumn<College, Double> cutoffColumn =
            new TableColumn<>("Cutoff (%)");

    cutoffColumn.setCellValueFactory(
            new PropertyValueFactory<>("cutoff")
    );

    table.getColumns().addAll(
            clgIdColumn,
            nameColumn,
            districtColumn,
            universityColumn,
            branchColumn,
            intakeColumn,
            cutoffColumn
    );

    styleTableColumn(clgIdColumn, "College ID");
    styleTableColumn(nameColumn, "College");
    styleTableColumn(districtColumn, "District");
    styleTableColumn(universityColumn, "University");
    styleTableColumn(branchColumn, "Branch");
    styleTableColumn(intakeColumn, "Intake");
    styleTableColumn(cutoffColumn, "Cutoff (%)");

    table.setColumnResizePolicy(
            TableView.CONSTRAINED_RESIZE_POLICY
    );

    table.setPrefHeight(390);
    table.setMinHeight(320);

    table.setStyle(
            "-fx-background-color: " + INPUT + ";" +
            "-fx-control-inner-background: " + INPUT + ";" +
            "-fx-table-cell-border-color: " + BORDER + ";" +
            "-fx-table-header-border-color: " + BORDER + ";" +
            "-fx-text-background-color: " + TEXT + ";" +
            "-fx-selection-bar: " + LIME + ";" +
            "-fx-selection-bar-text: #0B100B;" +
            "-fx-selection-bar-non-focused: " + LIME + ";" +
            "-fx-selection-bar-non-focused-text: #0B100B;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 10px;" +
            "-fx-background-radius: 10px;"
    );

    // =========================================
    // COLLEGE DATA
    // =========================================

    ObservableList<College> colleges =
            FXCollections.observableArrayList();

    CollegeAddController controller =
            new CollegeAddController();

    // Load colleges from Firestore
    for (College college : controller.getAllColleges()) {
        colleges.add(college);
    }

    table.setItems(colleges);

    // =========================================
    // ADD COLLEGE
    // =========================================

    add.setOnAction(e -> {

        if (
                clgId.getText().isBlank()
                || collegeName.getText().isBlank()
                || district.getText().isBlank()
                || university.getText().isBlank()
                || branch.getText().isBlank()
                || intake.getText().isBlank()
                || cutoff.getText().isBlank()
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
                            intake.getText().trim()
                    );

            double cutoffValue =
                    Double.parseDouble(
                            cutoff.getText().trim()
                    );

            if (intakeValue <= 0) {

                message(
                        "Invalid Intake",
                        "Intake must be greater than 0."
                );

                return;
            }

            if (cutoffValue < 0 || cutoffValue > 100) {

                message(
                        "Invalid Cutoff",
                        "College cutoff must be between 0 and 100."
                );

                return;
            }

            String clgid =
                    clgId.getText().trim();

            // Check duplicate College ID
            for (College college : colleges) {

                if (
                        college.getCollegeID()
                                .equalsIgnoreCase(clgid)
                ) {

                    message(
                            "Duplicate College ID",
                            "This College ID already exists."
                    );

                    return;
                }
            }

            College newCollege =
                    new College(
                            clgid,
                            collegeName.getText().trim(),
                            district.getText().trim(),
                            university.getText().trim(),
                            branch.getText().trim(),
                            intakeValue,
                            cutoffValue
                    );

            controller.addcollege(
                    newCollege.getCollegeID(),
                    newCollege.getCollegeName(),
                    newCollege.getDistrict(),
                    newCollege.getUniversity(),
                    newCollege.getBranch(),
                    newCollege.getIntake(),
                    newCollege.getCutoff()
            );

            colleges.add(newCollege);

            clear(
                    clgId,
                    collegeName,
                    district,
                    university,
                    branch,
                    intake,
                    cutoff
            );

            message(
                    "College Added",
                    "College added successfully."
            );

        } catch (NumberFormatException ex) {

            message(
                    "Invalid Input",
                    "Intake must be a whole number and cutoff must be a valid number."
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

                            clgId.setText(
                                    selected.getCollegeID()
                            );

                            collegeName.setText(
                                    selected.getCollegeName()
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

                            cutoff.setText(
                                    String.valueOf(
                                            selected.getCutoff()
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
                clgId.getText().isBlank()
                || collegeName.getText().isBlank()
                || district.getText().isBlank()
                || university.getText().isBlank()
                || branch.getText().isBlank()
                || intake.getText().isBlank()
                || cutoff.getText().isBlank()
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
                            intake.getText().trim()
                    );

            double cutoffValue =
                    Double.parseDouble(
                            cutoff.getText().trim()
                    );

            if (intakeValue <= 0) {

                message(
                        "Invalid Intake",
                        "Intake must be greater than 0."
                );

                return;
            }

            if (cutoffValue < 0 || cutoffValue > 100) {

                message(
                        "Invalid Cutoff",
                        "College cutoff must be between 0 and 100."
                );

                return;
            }

            String newCollegeID =
                    clgId.getText().trim();

            // Check duplicate College ID
            for (College college : colleges) {

                if (
                        college != selected
                        && college.getCollegeID()
                                .equalsIgnoreCase(
                                        newCollegeID
                                )
                ) {

                    message(
                            "Duplicate College ID",
                            "This College ID already exists."
                    );

                    return;
                }
            }

            String oldCollegeID =
                    selected.getCollegeID();

            // Update model
            selected.setCollegeID(newCollegeID);

            selected.setCollegeName(
                    collegeName.getText().trim()
            );

            selected.setDistrict(
                    district.getText().trim()
            );

            selected.setUniversity(
                    university.getText().trim()
            );

            selected.setBranch(
                    branch.getText().trim()
            );

            selected.setIntake(intakeValue);

            // Update cutoff
            selected.setCutoff(cutoffValue);

            // Update Firestore
            controller.updateCollege(
                    oldCollegeID,
                    selected
            );

            table.refresh();

            clear(
                    clgId,
                    collegeName,
                    district,
                    university,
                    branch,
                    intake,
                    cutoff
            );

            table.getSelectionModel()
                    .clearSelection();

            message(
                    "College Updated",
                    "College details updated successfully."
            );

        } catch (NumberFormatException ex) {

            message(
                    "Invalid Input",
                    "Intake must be a whole number and cutoff must be a valid number."
            );
        }
    });

    // =========================================
    // DELETE COLLEGE
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
                selected.getCollegeID()
                        + " - "
                        + selected.getCollegeName()
        );

        confirmation.showAndWait()
                .ifPresent(response -> {

                    if (response == ButtonType.OK) {

                        String collegeID =
                                selected.getCollegeID();

                        controller.deleteCollege(
                                collegeID
                        );

                        colleges.remove(selected);

                        clear(
                                clgId,
                                collegeName,
                                district,
                                university,
                                branch,
                                intake,
                                cutoff
                        );

                        table.getSelectionModel()
                                .clearSelection();

                        message(
                                "College Deleted",
                                "College deleted successfully."
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
                clgId,
                collegeName,
                district,
                university,
                branch,
                intake,
                cutoff
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
            new Insets(22)
    );

    tableCard.setStyle(
            "-fx-background-color: " + CARD + ";" +
            "-fx-background-radius: 12px;" +
            "-fx-border-color: " + BORDER + ";" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 12px;"
    );

    // =========================================
    // MAIN CONTENT
    // =========================================

    VBox content =
            new VBox(
                    22,
                    heading,
                    formCard,
                    tableCard
            );

    content.setPadding(
            new Insets(18, 22, 28, 22)
    );

    content.setFillWidth(true);
    content.setMaxWidth(Double.MAX_VALUE);

    content.setStyle(
            "-fx-background-color: " + BG + ";"
    );

    // =========================================
    // LAYOUT
    // =========================================

    ScrollPane scrollPane =
            new ScrollPane(content);

    scrollPane.setFitToWidth(true);
    scrollPane.setHbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
    );
    scrollPane.setVbarPolicy(
            ScrollPane.ScrollBarPolicy.NEVER
    );
    scrollPane.setPannable(true);
    scrollPane.setStyle(
            "-fx-background: " + BG + ";" +
            "-fx-background-color: " + BG + ";" +
            "-fx-border-color: transparent;"
    );

    BorderPane layout =
            CounsellorLayout.create(
                    "Colleges",
                    scrollPane
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

    field.setPromptText(prompt);

    field.setPrefHeight(44);

    field.setMaxWidth(
            Double.MAX_VALUE
    );

    String normal =
            "-fx-background-color: " + INPUT + ";" +
            "-fx-text-fill: " + TEXT + ";" +
            "-fx-prompt-text-fill: #687268;" +
            "-fx-border-color: #3A493A;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 0 12 0 12;";

    String hover =
            "-fx-background-color: " + FIELD_HOVER + ";" +
            "-fx-text-fill: " + TEXT + ";" +
            "-fx-prompt-text-fill: " + MUTED + ";" +
            "-fx-border-color: " + LIME + ";" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-font-size: 13px;" +
            "-fx-padding: 0 12 0 12;";

    field.setStyle(normal);

    field.setOnMouseEntered(e ->
            field.setStyle(hover)
    );

    field.setOnMouseExited(e -> {

        if (!field.isFocused()) {
            field.setStyle(normal);
        }
    });

    field.focusedProperty().addListener(
            (obs, oldValue, focused) ->
                    field.setStyle(
                            focused ? hover : normal
                    )
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
            "-fx-text-fill: " + TEXT + ";"
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

    button.setPrefWidth(width);
    button.setPrefHeight(42);

    String normal =
            "-fx-background-color: " + LIME + ";" +
            "-fx-text-fill: #0B100B;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;";

    String hover =
            "-fx-background-color: #D0FF4D;" +
            "-fx-text-fill: #0B100B;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;";

    button.setStyle(normal);

    button.setOnMouseEntered(e ->
            button.setStyle(hover)
    );

    button.setOnMouseExited(e ->
            button.setStyle(normal)
    );

    return button;
}

private static Button createDarkButton(
        String text,
        double width
) {

    Button button =
            new Button(text);

    button.setPrefWidth(width);
    button.setPrefHeight(42);

    String normal =
            "-fx-background-color: #1A221A;" +
            "-fx-text-fill: " + TEXT + ";" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: #354235;" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;";

    String hover =
            "-fx-background-color: #202B20;" +
            "-fx-text-fill: " + TEXT + ";" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-border-color: " + LIME + ";" +
            "-fx-border-width: 1px;" +
            "-fx-border-radius: 8px;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;";

    button.setStyle(normal);

    button.setOnMouseEntered(e ->
            button.setStyle(hover)
    );

    button.setOnMouseExited(e ->
            button.setStyle(normal)
    );

    return button;
}

private static Button createDangerButton(
        String text,
        double width
) {

    Button button =
            new Button(text);

    button.setPrefWidth(width);
    button.setPrefHeight(42);

    String normal =
            "-fx-background-color: " + RED + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;";

    String hover =
            "-fx-background-color: #EF4444;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;";

    button.setStyle(normal);

    button.setOnMouseEntered(e ->
            button.setStyle(hover)
    );

    button.setOnMouseExited(e ->
            button.setStyle(normal)
    );

    return button;
}

private static <T> void styleTableColumn(
        TableColumn<College, T> column,
        String title
) {

    Label header =
            new Label(title);

    header.setStyle(
            "-fx-text-fill: #172017;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;"
    );

    column.setText(null);
    column.setGraphic(header);
}

// =========================================
// CLEAR FORM
// =========================================

private static void clear(
        TextField clgId,
        TextField collegeName,
        TextField district,
        TextField university,
        TextField branch,
        TextField intake,
        TextField cutoff
) {

    clgId.clear();
    collegeName.clear();
    district.clear();
    university.clear();
    branch.clear();
    intake.clear();
    cutoff.clear();
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

    alert.setTitle(title);

    alert.setHeaderText(null);

    alert.setContentText(text);

    alert.showAndWait();
}


}
