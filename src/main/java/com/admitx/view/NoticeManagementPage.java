package com.admitx.view;

import java.util.List;

import com.admitx.dao.NoticeDAO;
import com.admitx.model.Notice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class NoticeManagementPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#131A13";

    private static final String INPUT =
            "#0D120D";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String TEXT =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    public static Scene getScene() {

        NoticeDAO noticeDAO =
                new NoticeDAO();

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Notice Management"
                );

        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Create, update and manage notices published to students."
                );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        // =========================================================
        // TITLE FIELD
        // =========================================================

        TextField noticeTitle =
                new TextField();

        noticeTitle.setPromptText(
                "Enter notice title"
        );

        styleTextField(
                noticeTitle
        );

        // =========================================================
        // DESCRIPTION
        // =========================================================

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

        // =========================================================
        // TAG
        // =========================================================

        ComboBox<String> tagBox =
                new ComboBox<>();

        tagBox.getItems()
                .addAll(
                        "GENERAL",
                        "CAP UPDATE",
                        "DOCUMENTS",
                        "OPTION FORM",
                        "MERIT LIST",
                        "ADMISSION",
                        "IMPORTANT"
                );

        tagBox.setPromptText(
                "Select Notice Tag"
        );

        tagBox.setPrefHeight(
                40
        );

        tagBox.setMaxWidth(
                Double.MAX_VALUE
        );

        tagBox.setStyle(
                "-fx-background-color:"
                        + INPUT + ";" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
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

        VBox tagFieldBox =
                createFieldBox(
                        "Notice Tag",
                        tagBox
                );

        // =========================================================
        // BUTTONS
        // =========================================================

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

        Button refresh =
                createDarkButton(
                        "Refresh",
                        100
                );

        HBox actions =
                new HBox(
                        10,
                        create,
                        edit,
                        clear,
                        delete,
                        refresh
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================================================
        // FORM CARD
        // =========================================================

        VBox formCard =
                new VBox(
                        14,
                        createSectionTitle(
                                "NOTICE DETAILS"
                        ),
                        titleBox,
                        descriptionBox,
                        tagFieldBox,
                        actions
                );

        styleCard(
                formCard
        );

        // =========================================================
        // TABLE
        // =========================================================

        TableView<Notice> table =
                new TableView<>();

        TableColumn<Notice, String>
                titleColumn =
                new TableColumn<>(
                        "Title"
                );

        titleColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "title"
                )
        );

        TableColumn<Notice, String>
                descriptionColumn =
                new TableColumn<>(
                        "Description"
                );

        descriptionColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "description"
                )
        );

        TableColumn<Notice, String>
                tagColumn =
                new TableColumn<>(
                        "Tag"
                );

        tagColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "tag"
                )
        );

        table.getColumns()
                .addAll(
                        titleColumn,
                        descriptionColumn,
                        tagColumn
                );

        table.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        table.setPrefHeight(
                340
        );

        table.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;"
        );

        // =========================================================
        // FIRESTORE DATA
        // =========================================================

        List<Notice> firebaseNotices =
                noticeDAO
                        .getAllNotices();

        ObservableList<Notice> notices =
                FXCollections
                        .observableArrayList(
                                firebaseNotices
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
                        createSectionTitle(
                                "PUBLISHED NOTICES"
                        ),
                        countLabel,
                        table
                );

        styleCard(
                tableCard
        );

        // =========================================================
        // CREATE
        // =========================================================

        create.setOnAction(e -> {

            if (
                    noticeTitle
                            .getText()
                            .isBlank()
                    ||
                    noticeText
                            .getText()
                            .isBlank()
            ) {

                show(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Enter notice title and description."
                );

                return;
            }

            String tag =
                    tagBox.getValue();

            if (
                    tag == null
                    ||
                    tag.isBlank()
            ) {

                tag =
                        "GENERAL";
            }

            boolean success =
                    noticeDAO
                            .createNotice(
                                    noticeTitle
                                            .getText()
                                            .trim(),

                                    noticeText
                                            .getText()
                                            .trim(),

                                    tag
                            );

            if (success) {

                show(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Notice created and published successfully."
                );

                Navigation.goTo(
                        getScene()
                );

            } else {

                show(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Unable to create notice."
                );
            }
        });

        // =========================================================
        // UPDATE
        // =========================================================

        edit.setOnAction(e -> {

            Notice selected =
                    table
                            .getSelectionModel()
                            .getSelectedItem();

            if (
                    selected == null
            ) {

                show(
                        Alert.AlertType.WARNING,
                        "No Notice Selected",
                        "Select a notice first."
                );

                return;
            }

            if (
                    noticeTitle
                            .getText()
                            .isBlank()
                    ||
                    noticeText
                            .getText()
                            .isBlank()
            ) {

                show(
                        Alert.AlertType.WARNING,
                        "Missing Information",
                        "Enter notice title and description."
                );

                return;
            }

            selected.setTitle(
                    noticeTitle
                            .getText()
                            .trim()
            );

            selected.setDescription(
                    noticeText
                            .getText()
                            .trim()
            );

            if (
                    tagBox.getValue()
                            != null
            ) {

                selected.setTag(
                        tagBox.getValue()
                );
            }

            boolean success =
                    noticeDAO
                            .updateNotice(
                                    selected
                            );

            if (success) {

                table.refresh();

                show(
                        Alert.AlertType.INFORMATION,
                        "Success",
                        "Notice updated successfully."
                );

            } else {

                show(
                        Alert.AlertType.ERROR,
                        "Error",
                        "Unable to update notice."
                );
            }
        });

        // =========================================================
        // DELETE
        // =========================================================

        delete.setOnAction(e -> {

            Notice selected =
                    table
                            .getSelectionModel()
                            .getSelectedItem();

            if (
                    selected == null
            ) {

                show(
                        Alert.AlertType.WARNING,
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

            confirmation
                    .showAndWait()
                    .ifPresent(response -> {

                        if (
                                response
                                != ButtonType.OK
                        ) {

                            return;
                        }

                        boolean success =
                                noticeDAO
                                        .deleteNotice(
                                                selected
                                                        .getNoticeId()
                                        );

                        if (success) {

                            notices.remove(
                                    selected
                            );

                            noticeTitle.clear();
                            noticeText.clear();

                            tagBox.setValue(
                                    null
                            );

                            updateCountLabel(
                                    countLabel,
                                    notices
                            );

                            show(
                                    Alert.AlertType.INFORMATION,
                                    "Success",
                                    "Notice deleted successfully."
                            );

                        } else {

                            show(
                                    Alert.AlertType.ERROR,
                                    "Error",
                                    "Unable to delete notice."
                            );
                        }
                    });
        });

        // =========================================================
        // CLEAR
        // =========================================================

        clear.setOnAction(e -> {

            table.getSelectionModel()
                    .clearSelection();

            noticeTitle.clear();

            noticeText.clear();

            tagBox.setValue(
                    null
            );
        });

        // =========================================================
        // REFRESH
        // =========================================================

        refresh.setOnAction(e ->

                Navigation.goTo(
                        getScene()
                )
        );

        // =========================================================
        // TABLE SELECTION
        // =========================================================

        table.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (
                                observable,
                                oldValue,
                                selected
                        ) -> {

                            if (
                                    selected == null
                            ) {

                                return;
                            }

                            noticeTitle.setText(
                                    selected.getTitle()
                            );

                            noticeText.setText(
                                    selected
                                            .getDescription()
                            );

                            tagBox.setValue(
                                    selected.getTag()
                            );
                        }
                );

        // =========================================================
        // ROOT
        // =========================================================

        VBox root =
                new VBox(
                        20,
                        heading,
                        formCard,
                        tableCard
                );

        root.setPadding(
                new Insets(
                        25
                )
        );

        root.setStyle(
                "-fx-background-color:"
                        + BG + ";"
        );

        // =========================================================
        // SCROLL
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        root
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background:"
                        + BG + ";" +
                "-fx-background-color:"
                        + BG + ";" +
                "-fx-border-color:transparent;"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "Notices",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =============================================================
    // SECTION TITLE
    // =============================================================

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + LIME + ";"
        );

        return label;
    }

    // =============================================================
    // FIELD
    // =============================================================

    private static VBox createFieldBox(
            String text,
            Control control
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        return new VBox(
                6,
                label,
                control
        );
    }

    // =============================================================
    // TEXT FIELD
    // =============================================================

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
                "-fx-background-color:"
                        + INPUT + ";" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#687268;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-padding:0 12 0 12;"
        );
    }

    // =============================================================
    // TEXT AREA
    // =============================================================

    private static void styleTextArea(
            TextArea area
    ) {

        area.setMaxWidth(
                Double.MAX_VALUE
        );

        area.setStyle(
                "-fx-control-inner-background:"
                        + INPUT + ";" +
                "-fx-text-fill:white;" +
                "-fx-prompt-text-fill:#687268;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );
    }

    // =============================================================
    // CARD
    // =============================================================

    private static void styleCard(
            Region region
    ) {

        region.setPadding(
                new Insets(
                        20
                )
        );

        region.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:10px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:10px;"
        );
    }

    // =============================================================
    // PRIMARY BUTTON
    // =============================================================

    private static Button createPrimaryButton(
            String text,
            double width
    ) {

        Button button =
                new Button(
                        text
                );

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color:"
                        + LIME + ";" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =============================================================
    // DARK BUTTON
    // =============================================================

    private static Button createDarkButton(
            String text,
            double width
    ) {

        Button button =
                new Button(
                        text
                );

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color:#1C251C;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:#354235;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =============================================================
    // DELETE BUTTON
    // =============================================================

    private static Button createDangerButton(
            String text,
            double width
    ) {

        Button button =
                new Button(
                        text
                );

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color:#DC2626;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =============================================================
    // COUNT
    // =============================================================

    private static void updateCountLabel(
            Label label,
            ObservableList<Notice> notices
    ) {

        label.setText(
                notices.size()
                        + " notices published"
        );

        label.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );
    }

    // =============================================================
    // ALERT
    // =============================================================

    private static void show(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        type
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}