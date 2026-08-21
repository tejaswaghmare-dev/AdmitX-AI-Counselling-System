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

public class StudentManagementPage {

    public static Scene getScene() {

        Label title = new Label("Student Management");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        TextField search =
                new TextField();

        search.setMaxWidth(Double.MAX_VALUE);

        search.setPromptText(
                "Search by Application ID or Name"
        );

        Button searchButton =
                new Button("Search");

        searchButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 110px;" +
                "-fx-pref-height: 36px;"
        );

        TableView<Student> table =
                new TableView<>();

        TableColumn<Student, String> idColumn =
                new TableColumn<>("Application ID");

        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("applicationId")
        );

        TableColumn<Student, String> nameColumn =
                new TableColumn<>("Student Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Student, String> categoryColumn =
                new TableColumn<>("Category");

        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("category")
        );

        TableColumn<Student, String> statusColumn =
                new TableColumn<>("Document Status");

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );

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

        table.setPrefHeight(400);

        searchButton.setOnAction(e -> {

            String text =
                    search.getText()
                            .toLowerCase();

            ObservableList<Student> filtered =
                    FXCollections.observableArrayList();

            for (Student student : students) {

                if (student.getApplicationId()
                        .toLowerCase()
                        .contains(text)
                        ||
                    student.getName()
                        .toLowerCase()
                        .contains(text)) {

                    filtered.add(student);
                }
            }

            table.setItems(filtered);
        });

        Button viewProfile =
                createButton("View Profile");

        viewProfile.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                showMessage(
                        "Student Profile",
                        "Name: " + selected.getName()
                        + "\nApplication ID: "
                        + selected.getApplicationId()
                        + "\nCategory: "
                        + selected.getCategory()
                        + "\nDocument Status: "
                        + selected.getStatus()
                );
            }
        });

        Button verify =
                createButton("Verify Documents");

        verify.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                selected.setStatus("Verified");

                table.refresh();

                showMessage(
                        "Verification",
                        "Student documents verified successfully."
                );
            }
        });

        Button approve =
                createButton("Approve");

        approve.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                showMessage(
                        "Approved",
                        "Student application approved successfully."
                );
            }
        });

        Button reject =
                createButton("Reject");

        reject.setStyle(
                "-fx-background-color: #DC2626;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 130px;" +
                "-fx-pref-height: 40px;"
        );

        reject.setOnAction(e -> {

            Student selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                showMessage(
                        "Rejected",
                        "Student application rejected."
                );
            }
        });

        TextArea comments =
                new TextArea();

        comments.setMaxWidth(Double.MAX_VALUE);

        comments.setPromptText(
                "Enter comments for selected student"
        );

        comments.setPrefRowCount(3);

        Button saveComment =
                createButton("Save Comment");

        saveComment.setOnAction(e -> {

            if (table.getSelectionModel()
                    .getSelectedItem() != null) {

                showMessage(
                        "Comment",
                        "Comment saved successfully."
                );
            }
        });

        HBox searchBox =
                new HBox(
                        10,
                        search,
                        searchButton
                );

        searchBox.setAlignment(
                Pos.CENTER_LEFT
        );

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

        VBox commentBox =
                new VBox(
                        8,
                        new Label("Comments"),
                        comments,
                        saveComment
                );

        VBox content =
                new VBox(
                        20,
                        title,
                        searchBox,
                        table,
                        actionButtons,
                        commentBox
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
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

    private static Button createButton(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(140);
        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: #0A0A0A;" +
                "-fx-text-fill: white;"
        );

        return button;
    }

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

            this.name = name;
            this.category = category;
            this.status = status;
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

        public void setStatus(String status) {
            this.status = status;
        }
    }
}