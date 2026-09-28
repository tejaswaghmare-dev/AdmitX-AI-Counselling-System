package com.admitx.view;

import java.util.List;

import com.admitx.dao.MeritDAO;
import com.admitx.dao.PreferenceDAO;
import com.admitx.dao.PreferenceDAO.PreferenceRecord;
import com.admitx.dao.PreferenceDAO.StudentPreferenceRecord;
import com.admitx.util.AsyncTaskRunner;

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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
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

    private static final String FIELD =
            "#0F150F";

    private static final String FIELD_HOVER =
            "#121A12";

    private static final String ORANGE =
            "#F97316";

    public static Scene getScene() {

        Scene loadingScene =
                createLoadingScene();

        AsyncTaskRunner.run(
                () -> {

                    PreferenceDAO preferenceDAO =
                            new PreferenceDAO();

                    boolean choiceFillingOpen =
                            preferenceDAO.isChoiceFillingOpen();

                    List<StudentPreferenceRecord> records =
                            preferenceDAO.getAllStudentPreferences();

                    int eligibleStudents =
                            new MeritDAO()
                                    .getFinalPublishedCount();

                    return new ManagementPageData(
                            choiceFillingOpen,
                            records == null
                                    ? List.of()
                                    : records,
                            eligibleStudents
                    );
                },

                pageData ->
                        Navigation.goTo(
                                createManagementScene(
                                        pageData
                                )
                        ),

                error -> {

                    error.printStackTrace();

                    showMessage(
                            Alert.AlertType.ERROR,
                            "Loading Error",
                            "Could not load option-form data. "
                                    + "Please check your internet connection."
                    );

                    Navigation.goTo(
                            CounsellorDashboardPage.getScene()
                    );
                }
        );

        return loadingScene;
    }

    private static Scene createManagementScene(
            ManagementPageData pageData
    ) {

        boolean choiceFillingOpen =
                pageData.choiceFillingOpen();

        ObservableList<StudentPreferenceRecord>
                studentRecords =
                FXCollections.observableArrayList(
                        pageData.records()
                );

        int eligibleStudents =
                pageData.eligibleStudents();

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
                        6,
                        title,
                        subtitle
                );

        heading.setPadding(
                new Insets(0, 0, 4, 0)
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
                "-fx-font-size:22px;" +
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
                        12,
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

            openButton.setDisable(true);

            AsyncTaskRunner.run(
                    () -> new PreferenceDAO()
                            .openChoiceFilling(),

                    success -> {

                        openButton.setDisable(false);

                        if (Boolean.TRUE.equals(success)) {

                            showMessage(
                                    Alert.AlertType.INFORMATION,
                                    "Choice Filling",
                                    "Choice filling has been opened successfully."
                            );

                            Navigation.goTo(getScene());

                        } else {

                            showMessage(
                                    Alert.AlertType.ERROR,
                                    "Choice Filling",
                                    "Unable to open choice filling."
                            );
                        }
                    },

                    error -> {

                        openButton.setDisable(false);
                        error.printStackTrace();

                        showMessage(
                                Alert.AlertType.ERROR,
                                "Choice Filling",
                                "Unable to open choice filling."
                        );
                    }
            );
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

            closeButton.setDisable(true);

            AsyncTaskRunner.run(
                    () -> new PreferenceDAO()
                            .closeChoiceFilling(),

                    success -> {

                        closeButton.setDisable(false);

                        if (Boolean.TRUE.equals(success)) {

                            showMessage(
                                    Alert.AlertType.INFORMATION,
                                    "Choice Filling",
                                    "Choice filling has been closed successfully."
                            );

                            Navigation.goTo(getScene());

                        } else {

                            showMessage(
                                    Alert.AlertType.ERROR,
                                    "Choice Filling",
                                    "Unable to close choice filling."
                            );
                        }
                    },

                    error -> {

                        closeButton.setDisable(false);
                        error.printStackTrace();

                        showMessage(
                                Alert.AlertType.ERROR,
                                "Choice Filling",
                                "Unable to close choice filling."
                        );
                    }
            );
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
                        18,
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

        styleTableColumn(emailColumn, "Student Email");
        styleTableColumn(countColumn, "Preferences");
        styleTableColumn(statusColumn, "Status");
        styleTableColumn(lockedAtColumn, "Locked At");

        studentTable.setPrefHeight(
                290
        );

        studentTable.setMinHeight(
                240
        );

        studentTable.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        studentTable.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-control-inner-background:" + FIELD + ";" +
                "-fx-table-cell-border-color:" + BORDER + ";" +
                "-fx-table-header-border-color:" + BORDER + ";" +
                "-fx-text-background-color:" + TEXT + ";" +
                "-fx-selection-bar:" + LIME + ";" +
                "-fx-selection-bar-text:#0B100B;" +
                "-fx-selection-bar-non-focused:" + LIME + ";" +
                "-fx-selection-bar-non-focused-text:#0B100B;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:10px;" +
                "-fx-background-radius:10px;"
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
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-background-color:" + FIELD + ";" +
                "-fx-padding:10 12 10 12;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;"
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

        styleTableColumn(preferenceNumberColumn, "Preference No.");
        styleTableColumn(collegeColumn, "College");
        styleTableColumn(branchColumn, "Branch");

        preferenceTable.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        preferenceTable.setPrefHeight(
                320
        );

        preferenceTable.setMinHeight(
                260
        );

        preferenceTable.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-control-inner-background:" + FIELD + ";" +
                "-fx-table-cell-border-color:" + BORDER + ";" +
                "-fx-table-header-border-color:" + BORDER + ";" +
                "-fx-text-background-color:" + TEXT + ";" +
                "-fx-selection-bar:" + LIME + ";" +
                "-fx-selection-bar-text:#0B100B;" +
                "-fx-selection-bar-non-focused:" + LIME + ";" +
                "-fx-selection-bar-non-focused-text:#0B100B;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:10px;" +
                "-fx-background-radius:10px;"
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
                "-fx-text-fill:#C6D0C0;" +
                "-fx-font-size:12px;" +
                "-fx-padding:16px;" +
                "-fx-background-radius:10px;" +
                "-fx-border-color:#38452B;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:10px;"
        );

        // =========================================================
        // CONTENT
        // =========================================================

        VBox root =
                new VBox(
                        22,
                        heading,
                        statusCard,
                        upperCards,
                        studentCard,
                        preferenceCard,
                        note
                );

        root.setPadding(
                new Insets(
                        20,
                        24,
                        30,
                        24
                )
        );

        root.setFillWidth(
                true
        );

        root.setMaxWidth(
                Double.MAX_VALUE
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
                        .NEVER
        );

        scrollPane.setPannable(
                true
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

    private static Scene createLoadingScene() {

        ProgressIndicator progressIndicator =
                new ProgressIndicator();

        progressIndicator.setPrefSize(
                42,
                42
        );

        Label loadingLabel =
                new Label(
                        "Loading option-form management..."
                );

        loadingLabel.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:13px;"
        );

        VBox loadingBox =
                new VBox(
                        14,
                        progressIndicator,
                        loadingLabel
                );

        loadingBox.setAlignment(
                Pos.CENTER
        );

        StackPane content =
                new StackPane(
                        loadingBox
                );

        content.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        return new Scene(
                CounsellorLayout.create(
                        "Option Form",
                        content
                ),
                1400,
                800
        );
    }

    private record ManagementPageData(
            boolean choiceFillingOpen,
            List<StudentPreferenceRecord> records,
            int eligibleStudents
    ) {
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
                "-fx-font-size:11px;" +
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
                64
        );

        String normal =
                "-fx-background-color:" + ROW + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;" +
                "-fx-padding:9 14 9 14;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:" + FIELD_HOVER + ";" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;" +
                "-fx-padding:9 14 9 14;" +
                "-fx-cursor:hand;";

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) {
                button.setStyle(hover);
            }
        });

        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) {
                button.setStyle(normal);
            }
        });

        button.disabledProperty().addListener(
                (obs, oldValue, disabled) -> {
                    if (disabled) {
                        button.setStyle(
                                "-fx-background-color:#111711;" +
                                "-fx-border-color:#263026;" +
                                "-fx-border-width:1px;" +
                                "-fx-border-radius:9px;" +
                                "-fx-background-radius:9px;" +
                                "-fx-padding:9 14 9 14;" +
                                "-fx-opacity:0.55;"
                        );
                    } else {
                        button.setStyle(normal);
                    }
                }
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

        String normal =
                "-fx-background-color:#18220F;" +
                "-fx-border-color:#3D5520;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;" +
                "-fx-padding:9 14 9 14;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#213010;" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:9px;" +
                "-fx-background-radius:9px;" +
                "-fx-padding:9 14 9 14;" +
                "-fx-cursor:hand;";

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) {
                button.setStyle(hover);
            }
        });

        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) {
                button.setStyle(normal);
            }
        });

        button.disabledProperty().addListener(
                (obs, oldValue, disabled) -> {
                    if (disabled) {
                        button.setStyle(
                                "-fx-background-color:#111711;" +
                                "-fx-border-color:#263026;" +
                                "-fx-border-width:1px;" +
                                "-fx-border-radius:9px;" +
                                "-fx-background-radius:9px;" +
                                "-fx-padding:9 14 9 14;" +
                                "-fx-opacity:0.55;"
                        );
                    } else {
                        button.setStyle(normal);
                    }
                }
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
                        11,
                        12,
                        11,
                        12
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
                        22
                )
        );

        region.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:12px;"
        );
    }

    private static <S, T> void styleTableColumn(
            TableColumn<S, T> column,
            String title
    ) {

        Label header =
                new Label(
                        title
                );

        header.setStyle(
                "-fx-text-fill:#172017;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        column.setText(
                null
        );

        column.setGraphic(
                header
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
