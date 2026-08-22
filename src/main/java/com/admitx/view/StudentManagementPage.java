package com.admitx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class StudentManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String BORDER = "#293529";

    public static Scene getScene() {

        Label title = new Label("Student Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle = new Label(
                "Search, verify and manage registered students."
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

        // =========================
        // SEARCH
        // =========================

        TextField search = new TextField();

        search.setPromptText(
                "Search by Application ID or Student Name"
        );

        search.setPrefHeight(42);

        search.setStyle(
                "-fx-background-color: #0D120D;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #6F7A6F;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 0 14 0 14;"
        );

        HBox.setHgrow(
                search,
                Priority.ALWAYS
        );

        Button searchButton =
                createPrimaryButton("Search", 110);

        HBox searchBox = new HBox(
                10,
                search,
                searchButton
        );

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

        searchBox.setPadding(
                new Insets(18)
        );

        searchBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        // =========================
        // TABLE
        // =========================

        TableView<Student> table =
                new TableView<>();

        TableColumn<Student, String> idColumn =
                new TableColumn<>("Application ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "applicationId"
                )
        );

        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Student Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "name"
                )
        );

        TableColumn<Student, String> categoryColumn =
                new TableColumn<>("Category");

        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "category"
                )
        );

        TableColumn<Student, String> statusColumn =
                new TableColumn<>("Document Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "status"
                )
        );

        idColumn.setPrefWidth(200);
        nameColumn.setPrefWidth(300);
        categoryColumn.setPrefWidth(150);
        statusColumn.setPrefWidth(200);

        table.getColumns().addAll(
                idColumn,
                nameColumn,
                categoryColumn,
                statusColumn
        );

        ObservableList<Student> students =
                FXCollections.observableArrayList(

                        new Student(
                                "MHTCET20260001",
                                "Yash Batte",
                                "Open",
                                "Pending"
                        ),

                        new Student(
                                "MHTCET20260002",
                                "Rahul Patil",
                                "OBC",
                                "Verified"
                        ),

                        new Student(
                                "MHTCET20260003",
                                "Sneha Sharma",
                                "EWS",
                                "Pending"
                        ),

                        new Student(
                                "MHTCET20260004",
                                "Amit Kulkarni",
                                "SC",
                                "Verified"
                        )
                );

        table.setItems(students);

        table.setPrefHeight(380);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        // =========================
        // SEARCH FUNCTION
        // =========================

        searchButton.setOnAction(e -> {

            String text =
                    search.getText()
                            .trim()
                            .toLowerCase();

            if (text.isEmpty()) {

                table.setItems(students);
                return;
            }

            ObservableList<Student> filtered =
                    FXCollections.observableArrayList();

            for (Student student : students) {

                if (
                        student.getApplicationId()
                                .toLowerCase()
                                .contains(text)

                        ||

                        student.getName()
                                .toLowerCase()
                                .contains(text)
                ) {

                    filtered.add(student);
                }
            }

            table.setItems(filtered);
        });

        search.setOnAction(e ->
                searchButton.fire()
        );

        // =========================
        // ACTION BUTTONS
        // =========================

        Button viewProfile =
                createDarkButton(
                        "View Profile",
                        135
                );

        Button verify =
                createDarkButton(
                        "Verify Documents",
                        155
                );

        Button approve =
                createPrimaryButton(
                        "Approve",
                        120
                );

        Button reject =
                createDangerButton(
                        "Reject",
                        110
                );

        viewProfile.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Student Management",
                        "Please select a student."
                );

                return;
            }

            showMessage(
                    "Student Profile",

                    "Name: "
                            + selected.getName()

                            + "\nApplication ID: "
                            + selected.getApplicationId()

                            + "\nCategory: "
                            + selected.getCategory()

                            + "\nDocument Status: "
                            + selected.getStatus()
            );
        });

        verify.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Student Management",
                        "Please select a student."
                );

                return;
            }

            selected.setStatus(
                    "Verified"
            );

            table.refresh();

            showMessage(
                    "Verification",
                    "Student documents verified successfully."
            );
        });

        approve.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Student Management",
                        "Please select a student."
                );

                return;
            }

            showMessage(
                    "Approved",
                    "Student application approved successfully."
            );
        });

        reject.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Student Management",
                        "Please select a student."
                );

                return;
            }

            showMessage(
                    "Rejected",
                    "Student application rejected."
            );
        });

        HBox actionButtons =
                new HBox(
                        10,
                        viewProfile,
                        verify,
                        approve,
                        reject
                );

        actionButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================
        // COMMENTS
        // =========================

        Label commentTitle =
                new Label("Counsellor Comments");

        commentTitle.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label commentDescription =
                new Label(
                        "Add remarks for the selected student."
                );

        commentDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        TextArea comments =
                new TextArea();

        comments.setPromptText(
                "Enter verification remarks or comments..."
        );

        comments.setPrefRowCount(3);

        comments.setWrapText(true);

        comments.setStyle(
                "-fx-control-inner-background: #0D120D;" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #6F7A6F;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );

        Button saveComment =
                createPrimaryButton(
                        "Save Comment",
                        140
                );

        saveComment.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showMessage(
                        "Student Management",
                        "Please select a student first."
                );

                return;
            }

            if (comments.getText()
                    .trim()
                    .isEmpty()) {

                showMessage(
                        "Comments",
                        "Please enter a comment."
                );

                return;
            }

            showMessage(
                    "Comment",
                    "Comment saved successfully."
            );

            comments.clear();
        });

        HBox commentButtonBox =
                new HBox(saveComment);

        commentButtonBox.setAlignment(
                Pos.CENTER_RIGHT
        );

        VBox commentBox =
                new VBox(
                        8,
                        commentTitle,
                        commentDescription,
                        comments,
                        commentButtonBox
                );

        commentBox.setPadding(
                new Insets(20)
        );

        commentBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        // =========================
        // CONTENT
        // =========================

        VBox content =
                new VBox(
                        20,
                        heading,
                        searchBox,
                        table,
                        actionButtons,
                        commentBox
                );

        content.setPadding(
                new Insets(5)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "Students",
                        content
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =========================
    // BUTTONS
    // =========================

    private static Button createPrimaryButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(width);
        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
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
        button.setPrefHeight(40);

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

    private static Button createDangerButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(width);
        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: #DC2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    // =========================
    // ALERT
    // =========================

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // =========================
    // STUDENT MODEL
    // =========================

    public static class Student {

        private final String applicationId;
        private final String name;
        private final String category;

        private String status;

        public Student(
                String applicationId,
                String name,
                String category,
                String status
        ) {

            this.applicationId =
                    applicationId;

            this.name =
                    name;

            this.category =
                    category;

            this.status =
                    status;
        }

        public String getApplicationId() {

            return applicationId;
        }

        public String getName() {

            return name;
        }

        public String getCategory() {

            return category;
        }

        public String getStatus() {

            return status;
        }

        public void setStatus(
                String status
        ) {

            this.status =
                    status;
        }
    }
}