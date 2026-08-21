package com.example.view;



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

        Label title =
                new Label("Notice Management");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        TextField noticeTitle =
                new TextField();

        noticeTitle.setMaxWidth(Double.MAX_VALUE);

        noticeTitle.setPromptText(
                "Notice Title"
        );

        TextArea noticeText =
                new TextArea();

        noticeText.setMaxWidth(Double.MAX_VALUE);

        noticeText.setPromptText(
                "Notice Description"
        );

        noticeText.setPrefRowCount(3);

        Button create =
                button("Create Notice");

        Button edit =
                button("Edit Notice");

        Button delete =
                button("Delete Notice");

        TableView<Notice> table =
                new TableView<>();

        TableColumn<Notice, String> titleColumn =
                new TableColumn<>("Title");

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );

        TableColumn<Notice, String> descriptionColumn =
                new TableColumn<>("Description");

        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<>("description")
        );

        

        ObservableList<Notice> notices =
                FXCollections.observableArrayList(
                        new Notice(
                                "CAP Round 1",
                                "CAP Round 1 allotment results published."
                        ),
                        new Notice(
                                "Option Form",
                                "Choice filling is now open."
                        )
                );

        table.setItems(notices);
        table.setPrefHeight(300);

        create.setOnAction(e -> {

            if (noticeTitle.getText().isBlank()
                    || noticeText.getText().isBlank()) {

                show(
                        "Error",
                        "Enter notice title and description."
                );

                return;
            }

            notices.add(
                    new Notice(
                            noticeTitle.getText(),
                            noticeText.getText()
                    )
            );

            noticeTitle.clear();
            noticeText.clear();

            show(
                    "Success",
                    "Notice created successfully."
            );
        });

        edit.setOnAction(e -> {

            Notice selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                show(
                        "Error",
                        "Select a notice first."
                );

                return;
            }

            selected.setTitle(
                    noticeTitle.getText()
            );

            selected.setDescription(
                    noticeText.getText()
            );

            table.refresh();

            show(
                    "Success",
                    "Notice updated successfully."
            );
        });

        delete.setOnAction(e -> {

            Notice selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                show(
                        "Error",
                        "Select a notice first."
                );

                return;
            }

            notices.remove(selected);

            show(
                    "Success",
                    "Notice deleted successfully."
            );
        });

        table.setOnMouseClicked(e -> {

            Notice selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                noticeTitle.setText(
                        selected.getTitle()
                );

                noticeText.setText(
                        selected.getDescription()
                );
            }
        });

        HBox actions =
                new HBox(
                        10,
                        create,
                        edit,
                        delete
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

       

        VBox root =
                new VBox(
                        20,
                        title,
                        noticeTitle,
                        noticeText,
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
                "Notices",
                root
        );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static Button button(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(150);
        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: #0A0A0A;" +
                "-fx-text-fill: white;"
        );

        return button;
    }

    private static void show(
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

    public static class Notice {

        private String title;
        private String description;

        public Notice(
                String title,
                String description
        ) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(
                String description
        ) {
            this.description = description;
        }
    }
}