package com.admitx.view;

import java.util.List;

import com.admitx.dao.NoticeDAO;
import com.admitx.model.Notice;
import com.admitx.util.AsyncTaskRunner;

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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
                        6,
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
                44
        );

        tagBox.setMaxWidth(
                Double.MAX_VALUE
        );

        tagBox.setStyle(
                "-fx-background-color:" + INPUT + ";" +
                "-fx-border-color:#3A493A;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;"
        );

        tagBox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? tagBox.getPromptText() : item);
                setStyle(
                        "-fx-background-color:" + INPUT + ";" +
                        "-fx-text-fill:" + (empty || item == null ? MUTED : TEXT) + ";" +
                        "-fx-padding:0 10 0 10;"
                );
            }
        });

        tagBox.setCellFactory(list -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("-fx-background-color:" + INPUT + ";");
                    return;
                }

                setText(item);
                setStyle(
                        "-fx-background-color:" + INPUT + ";" +
                        "-fx-text-fill:" + TEXT + ";" +
                        "-fx-padding:10 12 10 12;"
                );

                setOnMouseEntered(e -> setStyle(
                        "-fx-background-color:#1A241A;" +
                        "-fx-text-fill:" + LIME + ";" +
                        "-fx-padding:10 12 10 12;"
                ));

                setOnMouseExited(e -> setStyle(
                        "-fx-background-color:" + INPUT + ";" +
                        "-fx-text-fill:" + TEXT + ";" +
                        "-fx-padding:10 12 10 12;"
                ));
            }
        });

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

        Region actionSpacer = new Region();
        HBox.setHgrow(actionSpacer, Priority.ALWAYS);

        HBox actions =
                new HBox(
                        10,
                        create,
                        edit,
                        clear,
                        actionSpacer,
                        refresh,
                        delete
                );

        actions.setAlignment(
                Pos.CENTER_LEFT
        );
        actions.setFillHeight(true);

        // =========================================================
        // FORM CARD
        // =========================================================

        VBox formCard =
                new VBox(
                        16,
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
                390
        );

        table.setStyle(
                "-fx-background-color:" + INPUT + ";" +
                "-fx-control-inner-background:" + INPUT + ";" +
                "-fx-table-cell-border-color:#202B20;" +
                "-fx-selection-bar:#263426;" +
                "-fx-selection-bar-non-focused:#202A20;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;" +
                "-fx-text-background-color:" + TEXT + ";"
        );

        styleTableColumn(titleColumn, "TITLE");
        styleTableColumn(descriptionColumn, "DESCRIPTION");
        styleTableColumn(tagColumn, "TAG");

        // =========================================================
        // FIRESTORE DATA
        // =========================================================

        ObservableList<Notice> notices =
                FXCollections
                        .observableArrayList();

        table.setItems(
                notices
        );

        Label countLabel =
                new Label();

        updateCountLabel(
                countLabel,
                notices
        );

        ProgressIndicator loadingIndicator =
                new ProgressIndicator();

        loadingIndicator.setPrefSize(42, 42);

        table.setPlaceholder(
                loadingIndicator
        );

        Runnable loadNotices = () -> {

            create.setDisable(true);
            edit.setDisable(true);
            delete.setDisable(true);
            refresh.setDisable(true);
            table.setPlaceholder(loadingIndicator);

            AsyncTaskRunner.run(
                    () -> new NoticeDAO().getAllNotices(),

                    loadedNotices -> {

                        List<Notice> loaded =
                                loadedNotices == null
                                        ? List.of()
                                        : loadedNotices;

                        notices.setAll(loaded);

                        updateCountLabel(
                                countLabel,
                                notices
                        );

                        Label emptyLabel =
                                new Label(
                                        "No notices have been published."
                                );

                        emptyLabel.setStyle(
                                "-fx-text-fill:" + MUTED + ";"
                        );

                        table.setPlaceholder(emptyLabel);
                        create.setDisable(false);
                        edit.setDisable(false);
                        delete.setDisable(false);
                        refresh.setDisable(false);
                    },

                    error -> {

                        error.printStackTrace();

                        Label errorLabel =
                                new Label(
                                        "Could not load notices."
                                );

                        errorLabel.setStyle(
                                "-fx-text-fill:#F97316;"
                        );

                        table.setPlaceholder(errorLabel);
                        create.setDisable(false);
                        edit.setDisable(false);
                        delete.setDisable(false);
                        refresh.setDisable(false);

                        show(
                                Alert.AlertType.ERROR,
                                "Loading Error",
                                "Could not load notices. "
                                        + "Please check your internet connection."
                        );
                    }
            );
        };

        loadNotices.run();

        VBox tableCard =
                new VBox(
                        14,
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

            String titleText =
                    noticeTitle.getText().trim();

            String descriptionText =
                    noticeText.getText().trim();

            String noticeTag = tag;

            create.setDisable(true);

            AsyncTaskRunner.run(
                    () -> new NoticeDAO().createNotice(
                            titleText,
                            descriptionText,
                            noticeTag
                    ),

                    success -> {

                        create.setDisable(false);

                        if (Boolean.TRUE.equals(success)) {

                            clearForm(
                                    table,
                                    noticeTitle,
                                    noticeText,
                                    tagBox
                            );

                            show(
                                    Alert.AlertType.INFORMATION,
                                    "Success",
                                    "Notice created and published successfully."
                            );

                            loadNotices.run();

                        } else {

                            show(
                                    Alert.AlertType.ERROR,
                                    "Error",
                                    "Unable to create notice."
                            );
                        }
                    },

                    error -> {

                        create.setDisable(false);
                        error.printStackTrace();

                        show(
                                Alert.AlertType.ERROR,
                                "Error",
                                "Unable to create notice."
                        );
                    }
            );
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

            String updatedTag =
                    tagBox.getValue() == null
                            ? selected.getTag()
                            : tagBox.getValue();

            Notice updatedNotice =
                    new Notice(
                            selected.getNoticeId(),
                            noticeTitle.getText().trim(),
                            noticeText.getText().trim(),
                            updatedTag,
                            selected.isPublished(),
                            selected.getCreatedAt()
                    );

            edit.setDisable(true);

            AsyncTaskRunner.run(
                    () -> new NoticeDAO().updateNotice(
                            updatedNotice
                    ),

                    success -> {

                        edit.setDisable(false);

                        if (Boolean.TRUE.equals(success)) {

                            clearForm(
                                    table,
                                    noticeTitle,
                                    noticeText,
                                    tagBox
                            );

                            show(
                                    Alert.AlertType.INFORMATION,
                                    "Success",
                                    "Notice updated successfully."
                            );

                            loadNotices.run();

                        } else {

                            show(
                                    Alert.AlertType.ERROR,
                                    "Error",
                                    "Unable to update notice."
                            );
                        }
                    },

                    error -> {

                        edit.setDisable(false);
                        error.printStackTrace();

                        show(
                                Alert.AlertType.ERROR,
                                "Error",
                                "Unable to update notice."
                        );
                    }
            );
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

                        String noticeId =
                                selected.getNoticeId();

                        delete.setDisable(true);

                        AsyncTaskRunner.run(
                                () -> new NoticeDAO().deleteNotice(
                                        noticeId
                                ),

                                success -> {

                                    delete.setDisable(false);

                                    if (Boolean.TRUE.equals(success)) {

                                        notices.remove(selected);

                                        clearForm(
                                                table,
                                                noticeTitle,
                                                noticeText,
                                                tagBox
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
                                },

                                error -> {

                                    delete.setDisable(false);
                                    error.printStackTrace();

                                    show(
                                            Alert.AlertType.ERROR,
                                            "Error",
                                            "Unable to delete notice."
                                    );
                                }
                        );
                    });
        });

        // =========================================================
        // CLEAR
        // =========================================================

        clear.setOnAction(e -> {

            clearForm(
                    table,
                    noticeTitle,
                    noticeText,
                    tagBox
            );
        });

        // =========================================================
        // REFRESH
        // =========================================================

        refresh.setOnAction(e ->
                loadNotices.run()
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
                        22,
                        heading,
                        formCard,
                        tableCard
                );

        root.setPadding(
                new Insets(18, 24, 30, 24)
        );

        root.setFillWidth(true);
        root.setMaxWidth(Double.MAX_VALUE);

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
                        .NEVER
        );

        scrollPane.setPannable(true);

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
                "-fx-font-size:11px;" +
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

        field.setPrefHeight(44);
        field.setMaxWidth(Double.MAX_VALUE);

        String normal =
                "-fx-background-color:" + INPUT + ";" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-prompt-text-fill:#687268;" +
                "-fx-border-color:#3A493A;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-padding:0 12 0 12;";

        String focus =
                "-fx-background-color:#101710;" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-prompt-text-fill:" + MUTED + ";" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-padding:0 12 0 12;";

        field.setStyle(normal);
        field.focusedProperty().addListener(
                (obs, oldValue, focused) ->
                        field.setStyle(focused ? focus : normal)
        );
    }

    // =============================================================
    // TEXT AREA
    // =============================================================

    private static void styleTextArea(
            TextArea area
    ) {

        area.setWrapText(true);
        area.setStyle(
                "-fx-control-inner-background:" + INPUT + ";" +
                "-fx-background-color:" + INPUT + ";" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-prompt-text-fill:" + MUTED + ";" +
                "-fx-highlight-fill:" + LIME + ";" +
                "-fx-highlight-text-fill:#0B100B;" +
                "-fx-border-color:#3A493A;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-padding:8px;"
        );
    }

    // =============================================================
    // CARD
    // =============================================================

    private static void styleCard(
            Region region
    ) {

        region.setPadding(
                new Insets(22)
        );

        region.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:12px;"
        );
    }

    // =============================================================
    // PRIMARY BUTTON
    // =============================================================

    private static Button createPrimaryButton(
            String text,
            double width
    ) {

        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(44);

        String normal =
                "-fx-background-color:#B7FF00;" +
                "-fx-text-fill:#0B100B;" +
                "-fx-border-color:#B7FF00;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#C7FF3A;" +
                "-fx-text-fill:#0B100B;" +
                "-fx-border-color:#C7FF3A;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> button.setStyle(normal));

        return button;
    }

    // =============================================================
    // DARK BUTTON
    // =============================================================

    private static Button createDarkButton(
            String text,
            double width
    ) {

        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(44);

        String normal =
                "-fx-background-color:#1C251C;" +
                "-fx-text-fill:white;" +
                "-fx-border-color:#354235;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#253125;" +
                "-fx-text-fill:white;" +
                "-fx-border-color:#B7FF00;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> button.setStyle(normal));

        return button;
    }

    // =============================================================
    // DELETE BUTTON
    // =============================================================

    private static Button createDangerButton(
            String text,
            double width
    ) {

        Button button = new Button(text);
        button.setPrefWidth(width);
        button.setPrefHeight(44);

        String normal =
                "-fx-background-color:#DC2626;" +
                "-fx-text-fill:white;" +
                "-fx-border-color:#DC2626;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#EF4444;" +
                "-fx-text-fill:white;" +
                "-fx-border-color:#EF4444;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> button.setStyle(normal));

        return button;
    }

    private static <T> void styleTableColumn(
            TableColumn<Notice, T> column,
            String title
    ) {

        Label header = new Label(title);
        header.setStyle(
                "-fx-text-fill:#172017;" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;"
        );

        column.setText(null);
        column.setGraphic(header);
    }

    private static void clearForm(
            TableView<Notice> table,
            TextField noticeTitle,
            TextArea noticeText,
            ComboBox<String> tagBox
    ) {

        table.getSelectionModel()
                .clearSelection();

        noticeTitle.clear();
        noticeText.clear();
        tagBox.setValue(null);
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
