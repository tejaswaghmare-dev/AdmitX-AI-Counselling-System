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

public class NoticeManagementPage {

    public static Scene getScene() {

        VBox root = new VBox(20);
        root.setPadding(new Insets(35, 40, 40, 40));
        root.setAlignment(Pos.TOP_LEFT);
        root.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📢 Notice Management");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Create, edit and manage notices");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Input fields with dark theme
        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 14px;";

        String textAreaStyle =
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 10;" +
                "-fx-font-size: 14px;";

        TextField noticeTitle = new TextField();
        noticeTitle.setPromptText("Notice Title");
        noticeTitle.setStyle(fieldStyle);
        noticeTitle.setMaxWidth(600);

        TextArea noticeText = new TextArea();
        noticeText.setPromptText("Notice Description");
        noticeText.setStyle(textAreaStyle);
        noticeText.setPrefRowCount(3);
        noticeText.setMaxWidth(600);

        // Buttons
        HBox actions = new HBox(10);
        actions.setAlignment(Pos.CENTER_LEFT);

        Button create = createButton("➕ Create Notice", "#1E3A5F", "#E8EDF5");
        Button edit = createButton("✏️ Edit Notice", "#1E3A5F", "#E8EDF5");
        Button delete = createButton("🗑️ Delete Notice", "#7F1D1D", "#FCA5A5");

        actions.getChildren().addAll(create, edit, delete);

        // Table with dark theme
        TableView<Notice> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );

        TableColumn<Notice, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(200);
        titleColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<Notice, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descriptionColumn.setPrefWidth(500);
        descriptionColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        table.getColumns().addAll(titleColumn, descriptionColumn);
        table.setPrefHeight(300);

        table.setRowFactory(tv -> new TableRow<Notice>() {
            @Override
            protected void updateItem(Notice item, boolean empty) {
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

        ObservableList<Notice> notices = FXCollections.observableArrayList(
                new Notice("CAP Round 1", "CAP Round 1 allotment results published."),
                new Notice("Option Form", "Choice filling is now open.")
        );

        table.setItems(notices);

        // Back button
        Button back = createButton("← Back to Dashboard", "transparent", "#8AA8C7");
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

        // Keep original logic
        create.setOnAction(e -> {
            if (noticeTitle.getText().isBlank() || noticeText.getText().isBlank()) {
                show("Error", "Enter notice title and description.");
                return;
            }
            notices.add(new Notice(noticeTitle.getText(), noticeText.getText()));
            noticeTitle.clear();
            noticeText.clear();
            show("Success", "Notice created successfully.");
        });

        edit.setOnAction(e -> {
            Notice selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                show("Error", "Select a notice first.");
                return;
            }
            selected.setTitle(noticeTitle.getText());
            selected.setDescription(noticeText.getText());
            table.refresh();
            show("Success", "Notice updated successfully.");
        });

        delete.setOnAction(e -> {
            Notice selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                show("Error", "Select a notice first.");
                return;
            }
            notices.remove(selected);
            show("Success", "Notice deleted successfully.");
        });

        table.setOnMouseClicked(e -> {
            Notice selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                noticeTitle.setText(selected.getTitle());
                noticeText.setText(selected.getDescription());
            }
        });

        root.getChildren().addAll(title, subtitle, noticeTitle, noticeText, actions, table, back);

        return new Scene(root, 1100, 750);
    }

    private static Button createButton(String text, String bgColor, String textColor) {
        Button button = new Button(text);
        button.setPrefWidth(150);
        button.setPrefHeight(40);
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
        button.setOnMouseEntered(e ->
            button.setStyle(
                "-fx-background-color: " + (bgColor.equals("transparent") ? "rgba(74, 127, 181, 0.1)" : "#2A4A75") + ";" +
                "-fx-text-fill: " + (bgColor.equals("transparent") ? "#A8C4DF" : "#E8EDF5") + ";" +
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
        return button;
    }

    private static void show(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Notice {
        private String title;
        private String description;

        public Notice(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public void setTitle(String title) { this.title = title; }
        public void setDescription(String description) { this.description = description; }
    }
}
