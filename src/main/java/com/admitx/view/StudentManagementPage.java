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

public class StudentManagementPage {

    public static Scene getScene() {

        VBox content = new VBox(20);
        content.setPadding(new Insets(30));
        content.setStyle(
                "-fx-background-color: #0A0A0F;"
        );

        Label title = new Label("Student Management");
        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        // Search Box
        HBox searchBox = new HBox(10);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        TextField search = new TextField();
        search.setPromptText("Search by Application ID or Name");
        search.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 350px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 14px;"
        );

        Button searchButton = new Button("Search");
        searchButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 110px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        searchButton.setOnMouseEntered(e ->
            searchButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 110px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        searchButton.setOnMouseExited(e ->
            searchButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 110px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        searchBox.getChildren().addAll(search, searchButton);

        // Table with dark theme
        TableView<Student> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 12px;" +
                "-fx-border-width: 1px;"
        );

        TableColumn<Student, String> idColumn = new TableColumn<>("Application ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("applicationId"));
        idColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");
        idColumn.setPrefWidth(180);

        TableColumn<Student, String> nameColumn = new TableColumn<>("Student Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");
        nameColumn.setPrefWidth(200);

        TableColumn<Student, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");
        categoryColumn.setPrefWidth(150);

        TableColumn<Student, String> statusColumn = new TableColumn<>("Document Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");
        statusColumn.setPrefWidth(150);

        table.getColumns().addAll(idColumn, nameColumn, categoryColumn, statusColumn);

        // Dark theme for table rows
        table.setRowFactory(tv -> new TableRow<Student>() {
            @Override
            protected void updateItem(Student item, boolean empty) {
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

        ObservableList<Student> students = FXCollections.observableArrayList(
                new Student("MHTCET20260001", "Yash Batte", "Open", "Pending"),
                new Student("MHTCET20260002", "Rahul Patil", "OBC", "Verified"),
                new Student("MHTCET20260003", "Sneha Sharma", "EWS", "Pending"),
                new Student("MHTCET20260004", "Amit Kulkarni", "SC", "Verified")
        );

        table.setItems(students);
        table.setPrefHeight(400);

        // Search logic (unchanged)
        searchButton.setOnAction(e -> {
            String text = search.getText().toLowerCase();
            ObservableList<Student> filtered = FXCollections.observableArrayList();
            for (Student student : students) {
                if (student.getApplicationId().toLowerCase().contains(text) ||
                    student.getName().toLowerCase().contains(text)) {
                    filtered.add(student);
                }
            }
            table.setItems(filtered);
        });

        // Action Buttons
        HBox actionButtons = new HBox(10);
        actionButtons.setAlignment(Pos.CENTER_LEFT);

        Button viewProfile = createButton("View Profile");
        viewProfile.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showMessage("Student Profile",
                        "Name: " + selected.getName() +
                        "\nApplication ID: " + selected.getApplicationId() +
                        "\nCategory: " + selected.getCategory() +
                        "\nDocument Status: " + selected.getStatus());
            }
        });

        Button verify = createButton("Verify Documents");
        verify.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setStatus("Verified");
                table.refresh();
                showMessage("Verification", "Student documents verified successfully.");
            }
        });

        Button approve = createButton("Approve");
        approve.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showMessage("Approved", "Student application approved successfully.");
            }
        });

        Button reject = createButton("Reject");
        reject.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 130px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        reject.setOnMouseEntered(e ->
            reject.setStyle(
                "-fx-background-color: #991B1B;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 130px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.5);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        reject.setOnMouseExited(e ->
            reject.setStyle(
                "-fx-background-color: #7F1D1D;" +
                "-fx-text-fill: #FCA5A5;" +
                "-fx-pref-width: 130px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(220, 38, 38, 0.3);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        reject.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                showMessage("Rejected", "Student application rejected.");
            }
        });

        actionButtons.getChildren().addAll(viewProfile, verify, approve, reject);

        // Comments Section
        VBox commentBox = new VBox(8);
        Label commentLabel = new Label("Comments");
        commentLabel.setStyle(
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;"
        );

        TextArea comments = new TextArea();
        comments.setPromptText("Enter comments for selected student");
        comments.setPrefRowCount(3);
        comments.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 10;" +
                "-fx-font-size: 14px;"
        );

        Button saveComment = createButton("Save Comment");
        saveComment.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedItem() != null) {
                showMessage("Comment", "Comment saved successfully.");
            }
        });

        commentBox.getChildren().addAll(commentLabel, comments, saveComment);

        // Back Button
        Button back = createButton("Back to Dashboard");
        back.setOnAction(e ->
                Navigation.goTo(CounsellorDashboardPage.getScene())
        );

        // Assemble content
        content.getChildren().addAll(
                title,
                searchBox,
                table,
                actionButtons,
                commentBox,
                back
        );

        return new Scene(content, 1200, 750);
    }

    private static Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(140);
        button.setPrefHeight(40);
        button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        button.setOnMouseExited(e ->
            button.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        return button;
    }

    private static void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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

        public Student(String applicationId, String name, String category, String status) {
            this.applicationId = applicationId;
            this.name = name;
            this.category = category;
            this.status = status;
        }

        public String getApplicationId() { return applicationId; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}