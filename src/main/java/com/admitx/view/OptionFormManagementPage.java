package com.admitx.view;

import java.util.List;

import com.admitx.dao.MeritDAO;
import com.admitx.dao.PreferenceDAO;
import com.admitx.dao.PreferenceDAO.PreferenceRecord;
import com.admitx.dao.PreferenceDAO.StudentPreferenceRecord;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class OptionFormManagementPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#131A13";

    private static final String ROW =
            "#0F150F";

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
        // DAO
        // =========================================================

        PreferenceDAO preferenceDAO =
                new PreferenceDAO();

        MeritDAO meritDAO =
                new MeritDAO();

        // =========================================================
        // CURRENT CHOICE FILLING STATUS
        // =========================================================

        boolean choiceFillingOpen =
                preferenceDAO
                        .isChoiceFillingOpen();

        // =========================================================
        // LOAD STUDENT OPTION FORMS
        // =========================================================

        List<StudentPreferenceRecord> records =
                preferenceDAO
                        .getAllStudentPreferences();

        ObservableList<StudentPreferenceRecord>
                studentRecords =
                FXCollections.observableArrayList(
                        records
                );

        // =========================================================
        // COUNTS
        // =========================================================

        int eligibleStudents =
                meritDAO
                        .getFinalPublishedCount();

        int formsStarted =
                studentRecords.size();

        int formsLocked =
                0;

        for (
                StudentPreferenceRecord record :
                studentRecords
        ) {

            if (
                    record.isLocked()
            ) {

                formsLocked++;
            }
        }

        int pending =
                Math.max(
                        0,
                        eligibleStudents
                                - formsLocked
                );

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Option Form Management"
                );

        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Control choice filling and review student preference submissions."
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
        // STATUS BADGE
        // =========================================================

        Label statusBadge =
                new Label();

        Label currentStatus =
                new Label(
                        "Current Status"
                );

        currentStatus.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        Label currentValue =
                new Label();

        currentValue.setStyle(
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        Label statusDescription =
                new Label();

        statusDescription.setWrapText(
                true
        );

        statusDescription.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        if (choiceFillingOpen) {

            statusBadge.setText(
                    "●  CHOICE FILLING OPEN"
            );

            statusBadge.setStyle(
                    "-fx-background-color:#18220F;" +
                    "-fx-text-fill:"
                            + LIME + ";" +
                    "-fx-font-size:10px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-padding:7 12 7 12;" +
                    "-fx-background-radius:18px;" +
                    "-fx-border-color:#3D5520;" +
                    "-fx-border-radius:18px;"
            );

            currentValue.setText(
                    "Choice Filling Open"
            );

            statusDescription.setText(
                    "Eligible students can currently add, remove, reorder and lock their preferences."
            );

        } else {

            statusBadge.setText(
                    "●  CHOICE FILLING CLOSED"
            );

            statusBadge.setStyle(
                    "-fx-background-color:#211F0F;" +
                    "-fx-text-fill:#E7D65A;" +
                    "-fx-font-size:10px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-padding:7 12 7 12;" +
                    "-fx-background-radius:18px;" +
                    "-fx-border-color:#665F20;" +
                    "-fx-border-radius:18px;"
            );

            currentValue.setText(
                    "Choice Filling Closed"
            );

            statusDescription.setText(
                    "Students cannot currently modify or submit an unlocked option form."
            );
        }

        VBox statusCard =
                new VBox(
                        10,
                        statusBadge,
                        currentStatus,
                        currentValue,
                        statusDescription
                );

        styleCard(
                statusCard
        );

        // =========================================================
        // OPEN BUTTON
        // =========================================================

        Button openButton =
                createPrimaryAction(
                        "Open Choice Filling",
                        "Allow eligible students to add and modify preferences."
                );

        openButton.setDisable(
                choiceFillingOpen
        );

        openButton.setOnAction(e -> {

            boolean success =
                    preferenceDAO
                            .openChoiceFilling();

            if (success) {

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Choice Filling",
                        "Choice filling has been opened successfully."
                );

                Navigation.goTo(
                        getScene()
                );

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Choice Filling",
                        "Unable to open choice filling."
                );
            }
        });

        // =========================================================
        // CLOSE BUTTON
        // =========================================================

        Button closeButton =
                createAction(
                        "Close Choice Filling",
                        "Stop students from modifying or submitting unlocked option forms."
                );

        closeButton.setDisable(
                !choiceFillingOpen
        );

        closeButton.setOnAction(e -> {

            boolean success =
                    preferenceDAO
                            .closeChoiceFilling();

            if (success) {

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Choice Filling",
                        "Choice filling has been closed successfully."
                );

                Navigation.goTo(
                        getScene()
                );

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Choice Filling",
                        "Unable to close choice filling."
                );
            }
        });

        // =========================================================
        // REFRESH BUTTON
        // =========================================================

        Button refreshButton =
                createAction(
                        "Refresh Data",
                        "Reload option forms and status from Firestore."
                );

        refreshButton.setOnAction(e ->

                Navigation.goTo(
                        getScene()
                )
        );

        VBox actionsCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "OPTION FORM ACTIONS"
                        ),
                        openButton,
                        closeButton,
                        refreshButton
                );

        styleCard(
                actionsCard
        );

        // =========================================================
        // OVERVIEW
        // =========================================================

        VBox overviewCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "OPTION FORM OVERVIEW"
                        ),
                        createStatRow(
                                "Eligible Students",
                                String.valueOf(
                                        eligibleStudents
                                )
                        ),
                        createStatRow(
                                "Forms Started",
                                String.valueOf(
                                        formsStarted
                                )
                        ),
                        createStatRow(
                                "Forms Locked",
                                String.valueOf(
                                        formsLocked
                                )
                        ),
                        createStatRow(
                                "Pending",
                                String.valueOf(
                                        pending
                                )
                        )
                );

        styleCard(
                overviewCard
        );

        HBox upperCards =
                new HBox(
                        16,
                        actionsCard,
                        overviewCard
                );

        HBox.setHgrow(
                actionsCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                overviewCard,
                Priority.ALWAYS
        );

        actionsCard.setMaxWidth(
                Double.MAX_VALUE
        );

        overviewCard.setMaxWidth(
                Double.MAX_VALUE
        );

        // =========================================================
        // STUDENT TABLE
        // =========================================================

        Label studentTableTitle =
                createSectionTitle(
                        "STUDENT OPTION FORMS"
                );

        TableView<StudentPreferenceRecord>
                studentTable =
                new TableView<>();

        studentTable.setItems(
                studentRecords
        );

        TableColumn<StudentPreferenceRecord, String>
                emailColumn =
                new TableColumn<>(
                        "Student Email"
                );

        emailColumn.setCellValueFactory(
                data ->
                        new ReadOnlyStringWrapper(
                                data.getValue()
                                        .getStudentEmail()
                        )
        );

        TableColumn<StudentPreferenceRecord, Integer>
                countColumn =
                new TableColumn<>(
                        "Preferences"
                );

        countColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue()
                                        .getPreferenceCount()
                        )
        );

        TableColumn<StudentPreferenceRecord, String>
                statusColumn =
                new TableColumn<>(
                        "Status"
                );

        statusColumn.setCellValueFactory(
                data ->
                        new ReadOnlyStringWrapper(
                                data.getValue()
                                        .getStatus()
                        )
        );

        TableColumn<StudentPreferenceRecord, String>
                lockedAtColumn =
                new TableColumn<>(
                        "Locked At"
                );

        lockedAtColumn.setCellValueFactory(
                data ->
                        new ReadOnlyStringWrapper(
                                data.getValue()
                                        .getLockedAt()
                        )
        );

        emailColumn.setPrefWidth(
                300
        );

        countColumn.setPrefWidth(
                120
        );

        statusColumn.setPrefWidth(
                140
        );

        lockedAtColumn.setPrefWidth(
                350
        );

        studentTable.getColumns()
                .addAll(
                        emailColumn,
                        countColumn,
                        statusColumn,
                        lockedAtColumn
                );

        studentTable.setPrefHeight(
                260
        );

        studentTable.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        studentTable.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-control-inner-background:"
                        + ROW + ";" +
                "-fx-table-cell-border-color:"
                        + BORDER + ";" +
                "-fx-text-background-color:"
                        + TEXT + ";"
        );

        VBox studentCard =
                new VBox(
                        14,
                        studentTableTitle,
                        studentTable
                );

        styleCard(
                studentCard
        );

        // =========================================================
        // SELECTED STUDENT
        // =========================================================

        Label selectedStudentLabel =
                new Label(
                        "Select a student to view preferences."
                );

        selectedStudentLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        ObservableList<PreferenceRecord>
                preferenceItems =
                FXCollections.observableArrayList();

        TableView<PreferenceRecord>
                preferenceTable =
                new TableView<>();

        preferenceTable.setItems(
                preferenceItems
        );

        TableColumn<PreferenceRecord, Integer>
                preferenceNumberColumn =
                new TableColumn<>(
                        "Preference No."
                );

        preferenceNumberColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue()
                                        .getPreferenceNumber()
                        )
        );

        TableColumn<PreferenceRecord, String>
                collegeColumn =
                new TableColumn<>(
                        "College"
                );

        collegeColumn.setCellValueFactory(
                data ->
                        new ReadOnlyStringWrapper(
                                data.getValue()
                                        .getCollege()
                        )
        );

        TableColumn<PreferenceRecord, String>
                branchColumn =
                new TableColumn<>(
                        "Branch"
                );

        branchColumn.setCellValueFactory(
                data ->
                        new ReadOnlyStringWrapper(
                                data.getValue()
                                        .getBranch()
                        )
        );

        preferenceNumberColumn.setPrefWidth(
                150
        );

        collegeColumn.setPrefWidth(
                450
        );

        branchColumn.setPrefWidth(
                350
        );

        preferenceTable.getColumns()
                .addAll(
                        preferenceNumberColumn,
                        collegeColumn,
                        branchColumn
                );

        preferenceTable.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        preferenceTable.setPrefHeight(
                300
        );

        preferenceTable.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-control-inner-background:"
                        + ROW + ";" +
                "-fx-table-cell-border-color:"
                        + BORDER + ";" +
                "-fx-text-background-color:"
                        + TEXT + ";"
        );

        studentTable
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (
                                observable,
                                oldValue,
                                selected
                        ) -> {

                            preferenceItems.clear();

                            if (
                                    selected == null
                            ) {

                                selectedStudentLabel
                                        .setText(
                                                "Select a student to view preferences."
                                        );

                                return;
                            }

                            selectedStudentLabel
                                    .setText(
                                            selected.getStudentEmail()
                                                    + "  •  "
                                                    + selected.getStatus()
                                    );

                            preferenceItems.addAll(
                                    selected.getPreferences()
                            );
                        }
                );

        VBox preferenceCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "SELECTED STUDENT PREFERENCES"
                        ),
                        selectedStudentLabel,
                        preferenceTable
                );

        styleCard(
                preferenceCard
        );

        // =========================================================
        // NOTE
        // =========================================================

        Label note =
                new Label(
                        "Locked option forms are used for CAP allotment. "
                                + "Closing choice filling prevents students with unlocked "
                                + "forms from making further changes or submitting them."
                );

        note.setWrapText(
                true
        );

        note.setStyle(
                "-fx-background-color:#151B10;" +
                "-fx-text-fill:#B9C5B2;" +
                "-fx-font-size:12px;" +
                "-fx-padding:14px;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:#38452B;" +
                "-fx-border-radius:8px;"
        );

        // =========================================================
        // CONTENT
        // =========================================================

        VBox root =
                new VBox(
                        20,
                        heading,
                        statusCard,
                        upperCards,
                        studentCard,
                        preferenceCard,
                        note
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
                        "Option Form",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

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

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private static Button createAction(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-font-size:10px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        descriptionLabel
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label arrow =
                new Label(
                        "→"
                );

        arrow.setStyle(
                "-fx-text-fill:"
                        + MUTED + ";" +
                "-fx-font-size:16px;"
        );

        HBox graphic =
                new HBox(
                        10,
                        text,
                        spacer,
                        arrow
                );

        graphic.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button =
                new Button();

        button.setGraphic(
                graphic
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                60
        );

        button.setStyle(
                "-fx-background-color:"
                        + ROW + ";" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:8 14 8 14;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =========================================================
    // PRIMARY ACTION
    // =========================================================

    private static Button createPrimaryAction(
            String title,
            String description
    ) {

        Button button =
                createAction(
                        title,
                        description
                );

        button.setStyle(
                "-fx-background-color:#18220F;" +
                "-fx-border-color:#3D5520;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:8 14 8 14;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =========================================================
    // STAT ROW
    // =========================================================

    private static HBox createStatRow(
            String label,
            String value
    ) {

        Label labelText =
                new Label(
                        label
                );

        labelText.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label valueText =
                new Label(
                        value
                );

        valueText.setStyle(
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + TEXT + ";"
        );

        HBox row =
                new HBox(
                        labelText,
                        spacer,
                        valueText
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        10
                )
        );

        row.setStyle(
                "-fx-background-color:"
                        + ROW + ";" +
                "-fx-background-radius:7px;"
        );

        return row;
    }

    // =========================================================
    // CARD
    // =========================================================

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

    // =========================================================
    // MESSAGE
    // =========================================================

    private static void showMessage(
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