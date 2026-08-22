package com.admitx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class NoticeManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String INPUT = "#0D120D";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Notice Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Create, update and manage notices published to students."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        TextField noticeTitle =
                new TextField();

        noticeTitle.setPromptText(
                "Enter notice title"
        );

        styleTextField(
                noticeTitle
        );

        TextArea noticeText =
                new TextArea();

        noticeText.setPromptText(
                "Enter notice description"
        );

        noticeText.setPrefRowCount(
                4
        );

        noticeText.setWrapText(
                true
        );

        styleTextArea(
                noticeText
        );

        VBox titleBox =
                createFieldBox(
                        "Notice Title",
                        noticeTitle
                );

        VBox descriptionBox =
                createFieldBox(
                        "Notice Description",
                        noticeText
                );

        Button create =
                createPrimaryButton(
                        "Create Notice",
                        140
                );

        Button edit =
                createDarkButton(
                        "Update Notice",
                        140
                );

        Button clear =
                createDarkButton(
                        "Clear",
                        100
                );

        Button delete =
                createDangerButton(
                        "Delete Notice",
                        140
                );

        HBox actions =
                new HBox(
                        10,
                        create,
                        edit,
                        clear,
                        delete
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox formCard =
                new VBox(
                        14,
                        createSectionTitle("NOTICE DETAILS"),
                        titleBox,
                        descriptionBox,
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

        table.getColumns().addAll(
                titleColumn,
                descriptionColumn
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setPrefHeight(
                320
        );

        table.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
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

        table.setItems(
                notices
        );

        Label countLabel =
                new Label();

        updateCountLabel(
                countLabel,
                notices
        );

        VBox tableCard =
                new VBox(
                        12,
                        createSectionTitle("PUBLISHED NOTICES"),
                        countLabel,
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

        create.setOnAction(e -> {

            if (
                    noticeTitle.getText().isBlank()
                    || noticeText.getText().isBlank()
            ) {

                show(
                        "Missing Information",
                        "Enter notice title and description."
                );

                return;
            }

            notices.add(
                    new Notice(
                            noticeTitle.getText().trim(),
                            noticeText.getText().trim()
                    )
            );

            noticeTitle.clear();
            noticeText.clear();

            updateCountLabel(
                    countLabel,
                    notices
            );

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
                        "No Notice Selected",
                        "Select a notice first."
                );

                return;
            }

            if (
                    noticeTitle.getText().isBlank()
                    || noticeText.getText().isBlank()
            ) {

                show(
                        "Missing Information",
                        "Enter notice title and description."
                );

                return;
            }

            selected.setTitle(
                    noticeTitle.getText().trim()
            );

            selected.setDescription(
                    noticeText.getText().trim()
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
                        "No Notice Selected",
                        "Select a notice first."
                );

                return;
            }

            Alert confirmation =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirmation.setTitle(
                    "Delete Notice"
            );

            confirmation.setHeaderText(
                    "Delete selected notice?"
            );

            confirmation.setContentText(
                    selected.getTitle()
            );

            confirmation.showAndWait()
                    .ifPresent(response -> {

                        if (
                                response ==
                                ButtonType.OK
                        ) {

                            notices.remove(
                                    selected
                            );

                            noticeTitle.clear();
                            noticeText.clear();

                            updateCountLabel(
                                    countLabel,
                                    notices
                            );
                        }
                    });
        });

        clear.setOnAction(e -> {

            table.getSelectionModel()
                    .clearSelection();

            noticeTitle.clear();
            noticeText.clear();
        });

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, selected) -> {

                            if (selected != null) {

                                noticeTitle.setText(
                                        selected.getTitle()
                                );

                                noticeText.setText(
                                        selected.getDescription()
                                );
                            }
                        }
                );

        VBox root =
                new VBox(
                        20,
                        heading,
                        formCard,
                        tableCard
                );

        root.setPadding(
                new Insets(5)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
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

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    private static VBox createFieldBox(
            String text,
            Control control
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
                        control
                );

        return box;
    }

    private static void styleTextField(
            TextField field
    ) {

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
    }

    private static void styleTextArea(
            TextArea area
    ) {

        area.setMaxWidth(
                Double.MAX_VALUE
        );

        area.setStyle(
                "-fx-control-inner-background: " + INPUT + ";" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #687268;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;"
        );
    }

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

    private static void updateCountLabel(
            Label label,
            ObservableList<Notice> notices
    ) {

        label.setText(
                notices.size()
                        + " notices published"
        );

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );
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

        public void setTitle(
                String title
        ) {

            this.title = title;
        }

        public void setDescription(
                String description
        ) {

            this.description = description;
        }
    }
}