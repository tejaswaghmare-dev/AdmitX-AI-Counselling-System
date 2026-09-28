package com.admitx.view;

import java.util.LinkedHashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.List;
import java.util.Set;
import com.admitx.util.AsyncTaskRunner;
import com.admitx.dao.MeritDAO;
import com.admitx.dao.CollegeDAO;
import com.admitx.dao.PreferenceDAO;
import com.admitx.model.College;
import com.admitx.model.Student;
import com.admitx.service.PreferenceFillingAIService;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PreferenceFillingPage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#141B14";

    private static final String FIELD =
            "#101610";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String WHITE =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    private static final ObservableList<Preference>
            preferences =
            FXCollections.observableArrayList();

    public static Scene getScene() {

        // =========================================================
        // CHECK LOGIN
        // =========================================================

        String studentEmail =
                Student.getInstance()
                        .getEmail();

        if (
                studentEmail == null ||
                studentEmail.isBlank()
        ) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "Login Required",
                    "Please login before filling preferences."
            );

            return StudentLoginPage.getScene();
        }

        Scene loadingScene =
                createLoadingScene();

        AsyncTaskRunner.run(
                () -> {

                    PreferenceDAO preferenceDAO = new PreferenceDAO();

                    CompletableFuture<MeritDAO.MeritRecord> meritFuture =
                            CompletableFuture.supplyAsync(
                                    () -> new MeritDAO().getCurrentStudentFinalMerit()
                            );

                    CompletableFuture<Boolean> lockedFuture =
                            CompletableFuture.supplyAsync(
                                    preferenceDAO::isPreferenceLocked
                            );

                    CompletableFuture<Boolean> choiceOpenFuture =
                            CompletableFuture.supplyAsync(
                                    preferenceDAO::isChoiceFillingOpen
                            );

                    CompletableFuture<List<Preference>> preferencesFuture =
                            CompletableFuture.supplyAsync(
                                    preferenceDAO::loadPreferences
                            );

                    CompletableFuture<List<College>> collegesFuture =
                            CompletableFuture.supplyAsync(
                                    () -> new CollegeDAO().getAllColleges()
                            );

                    MeritDAO.MeritRecord finalMerit = meritFuture.join();
                    boolean locked = lockedFuture.join();
                    boolean choiceFillingOpen = choiceOpenFuture.join();
                    List<Preference> savedPreferences = preferencesFuture.join();
                    List<College> colleges = collegesFuture.join();

                    if (finalMerit == null) {
                        return new PreferencePageData(
                                false,
                                false,
                                false,
                                List.of(),
                                List.of()
                        );
                    }

                    return new PreferencePageData(
                            true,
                            locked,
                            choiceFillingOpen,
                            savedPreferences == null ? List.of() : savedPreferences,
                            colleges == null ? List.of() : colleges
                    );
                },

                pageData -> {

                    if (!pageData.finalMeritAvailable()) {

                        showAlert(
                                Alert.AlertType.WARNING,
                                "Final Merit Required",
                                "Preference Filling is available only after "
                                        + "your Final Merit List is published."
                        );

                        Navigation.goTo(
                                FinalMeritPage.getScene()
                        );

                        return;
                    }

                    Navigation.goTo(
                            createPreferenceScene(
                                    pageData
                            )
                    );
                },

                error -> {

                    error.printStackTrace();

                    showAlert(
                            Alert.AlertType.ERROR,
                            "Loading Error",
                            getReadableErrorMessage(
                                    error,
                                    "Could not load preference data. Please try again."
                            )
                    );

                    Navigation.goTo(
                            StudentDashboardPage.getScene()
                    );
                }
        );

        return loadingScene;
    }

    private static Scene createPreferenceScene(
            PreferencePageData pageData
    ) {

        boolean locked =
                pageData.locked();

        boolean choiceFillingOpen =
                pageData.choiceFillingOpen();

        List<College> firebaseColleges =
                pageData.colleges();

        preferences.setAll(
                pageData.savedPreferences()
        );

        // =========================================================
        // MAIN CONTENT
        // =========================================================

        VBox content =
                new VBox(24);

        content.setPadding(
                new Insets(20, 24, 30, 24)
        );

        content.setFillWidth(true);
        content.setMaxWidth(Double.MAX_VALUE);

        content.setAlignment(
                Pos.TOP_LEFT
        );

        content.setStyle(
                "-fx-background-color:"
                        + BG + ";"
        );

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Preference Filling"
                );

        title.setStyle(
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;"
        );

        Label subtitle =
                new Label(
                        "Add colleges and branches in the order of your preference."
                );

        subtitle.setStyle(
                "-fx-text-fill:"
                        + MUTED + ";" +
                "-fx-font-size:13px;"
        );

        // =========================================================
        // LOCK STATUS
        // =========================================================

        Label lockStatus =
                new Label();

        if (locked) {

                lockStatus.setText(
                        "✓ OPTION FORM LOCKED"
                );

                lockStatus.setStyle(
                        "-fx-text-fill:"
                                + LIME + ";" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;"
                );

        } else if (!choiceFillingOpen) {

                lockStatus.setText(
                        "● CHOICE FILLING CLOSED"
                );

                lockStatus.setStyle(
                        "-fx-text-fill:#E7D65A;" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;"
                );

        } else {

                lockStatus.setText(
                        "● CHOICE FILLING OPEN"
                );

                lockStatus.setStyle(
                        "-fx-text-fill:"
                                + LIME + ";" +
                        "-fx-font-size:13px;" +
                        "-fx-font-weight:bold;"
                );
                }

        lockStatus.setPadding(
                new Insets(8, 12, 8, 12)
        );

        lockStatus.setStyle(
                lockStatus.getStyle() +
                "-fx-background-color:#151C12;" +
                "-fx-background-radius:18px;" +
                "-fx-border-color:#344234;" +
                "-fx-border-radius:18px;"
        );

        // =========================================================
        // COLLEGE COMBOBOX
        // =========================================================

        ComboBox<String> collegeBox =
                new ComboBox<>();

        collegeBox.setPromptText(
                "Select College"
        );

        collegeBox.setPrefWidth(
                350
        );

        collegeBox.setPrefHeight(
                42
        );

        // =========================================================
        // BRANCH COMBOBOX
        // =========================================================

        ComboBox<String> branchBox =
                new ComboBox<>();

        branchBox.setPromptText(
                "Select Branch"
        );

        branchBox.setPrefWidth(
                300
        );

        branchBox.setPrefHeight(
                42
        );

        branchBox.setDisable(
                true
        );

        styleComboBox(
                collegeBox
        );

        styleComboBox(
                branchBox
        );

        // =========================================================
        // UNIQUE COLLEGES
        // =========================================================

        Set<String> collegeNames =
                new LinkedHashSet<>();

        for (
                College college
                : firebaseColleges
        ) {

            if (
                    college.getCollegeName()
                            != null
                    &&
                    !college.getCollegeName()
                            .isBlank()
            ) {

                collegeNames.add(
                        college.getCollegeName()
                );
            }
        }

        collegeBox.getItems()
                .addAll(
                        collegeNames
                );

        // =========================================================
        // FINAL COLLEGE LIST FOR LAMBDA
        // =========================================================

        List<College> colleges =
                firebaseColleges;

        // =========================================================
        // WHEN COLLEGE SELECTED
        // =========================================================

        collegeBox.setOnAction(e -> {

            branchBox.getItems()
                    .clear();

            branchBox.setValue(
                    null
            );

            String selectedCollege =
                    collegeBox.getValue();

            if (
                    selectedCollege == null
            ) {

                branchBox.setDisable(
                        true
                );

                return;
            }

            Set<String> branches =
                    new LinkedHashSet<>();

            for (
                    College college
                    : colleges
            ) {

                if (
                        selectedCollege.equals(
                                college.getCollegeName()
                        )
                        &&
                        college.getBranch()
                                != null
                        &&
                        !college.getBranch()
                                .isBlank()
                ) {

                    branches.add(
                            college.getBranch()
                    );
                }
            }

            branchBox.getItems()
                    .addAll(
                            branches
                    );

            branchBox.setDisable(
                    branches.isEmpty()
            );
        });

        // =========================================================
        // ADD BUTTON
        // =========================================================

        Button addButton =
                new Button(
                        "+ Add Preference"
                );

        stylePrimaryButton(
                addButton
        );

        // =========================================================
        // FORM CARD
        // =========================================================

        HBox formRow =
                new HBox(
                        15,
                        collegeBox,
                        branchBox,
                        addButton
                );

        formRow.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox.setHgrow(collegeBox, Priority.ALWAYS);
        HBox.setHgrow(branchBox, Priority.ALWAYS);

        collegeBox.setMaxWidth(Double.MAX_VALUE);
        branchBox.setMaxWidth(Double.MAX_VALUE);

        VBox formCard =
                new VBox(
                        15,
                        new Label(
                                "Select College & Branch"
                        ),
                        formRow
                );

        ((Label) formCard
                .getChildren()
                .get(0))
                .setStyle(
                        "-fx-text-fill:"
                                + WHITE + ";" +
                        "-fx-font-size:16px;" +
                        "-fx-font-weight:bold;"
                );

        formCard.setPadding(
                new Insets(22)
        );

        formCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:12px;"
        );

        // =========================================================
        // TABLE
        // =========================================================

        TableView<Preference> table =
                new TableView<>();

        table.setItems(
                preferences
        );

        table.setPrefHeight(
                400
        );

        table.setColumnResizePolicy(
                TableView
                        .CONSTRAINED_RESIZE_POLICY
        );

        TableColumn<Preference, Integer>
                numberColumn =
                new TableColumn<>(
                        "Preference No."
                );

        numberColumn.setCellValueFactory(
                data ->
                        new ReadOnlyObjectWrapper<>(
                                data.getValue()
                                        .getPreferenceNumber()
                        )
        );

        TableColumn<Preference, String>
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

        TableColumn<Preference, String>
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

        table.getColumns()
                .addAll(
                        numberColumn,
                        collegeColumn,
                        branchColumn
                );

        styleTableColumn(numberColumn, "Preference No.");
        styleTableColumn(collegeColumn, "College");
        styleTableColumn(branchColumn, "Branch");

        table.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-control-inner-background:" + FIELD + ";" +
                "-fx-table-cell-border-color:" + BORDER + ";" +
                "-fx-table-header-border-color:" + BORDER + ";" +
                "-fx-text-background-color:" + WHITE + ";" +
                "-fx-selection-bar:" + LIME + ";" +
                "-fx-selection-bar-text:#0B100B;" +
                "-fx-selection-bar-non-focused:" + LIME + ";" +
                "-fx-selection-bar-non-focused-text:#0B100B;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:10px;" +
                "-fx-background-radius:10px;"
        );

        // =========================================================
        // ADD ACTION
        // =========================================================

        addButton.setOnAction(e -> {

            if (locked) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Option Form Locked",
                        "Your option form is already locked."
                );

                return;
            }

            String selectedCollege =
                    collegeBox.getValue();

            String selectedBranch =
                    branchBox.getValue();

            if (
                    selectedCollege == null ||
                    selectedBranch == null
            ) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Preference",
                        "Please select college and branch."
                );

                return;
            }

            // CHECK DUPLICATE
            for (
                    Preference preference
                    : preferences
            ) {

                if (
                        preference.getCollege()
                                .equals(
                                        selectedCollege
                                )
                        &&
                        preference.getBranch()
                                .equals(
                                        selectedBranch
                                )
                ) {

                    showAlert(
                            Alert.AlertType.WARNING,
                            "Duplicate Preference",
                            "This college and branch is already added."
                    );

                    return;
                }
            }

            preferences.add(
                    new Preference(
                            preferences.size()
                                    + 1,
                            selectedCollege,
                            selectedBranch
                    )
            );

            collegeBox.setValue(
                    null
            );

            branchBox.setValue(
                    null
            );

            branchBox.getItems()
                    .clear();

            branchBox.setDisable(
                    true
            );
        });

        // =========================================================
        // REMOVE BUTTON
        // =========================================================

        Button removeButton =
                new Button(
                        "Remove"
                );

        styleDangerButton(
                removeButton
        );

        removeButton.setOnAction(e -> {

            Preference selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Preference",
                        "Please select a preference to remove."
                );

                return;
            }

            preferences.remove(
                    selected
            );

            renumber();
        });

        // =========================================================
        // MOVE UP
        // =========================================================

        Button moveUpButton =
                new Button(
                        "↑ Move Up"
                );

        styleSecondaryButton(
                moveUpButton
        );

        moveUpButton.setOnAction(e -> {

            int index =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (index > 0) {

                Preference preference =
                        preferences.remove(
                                index
                        );

                preferences.add(
                        index - 1,
                        preference
                );

                renumber();

                table.getSelectionModel()
                        .select(
                                index - 1
                        );
            }
        });

        // =========================================================
        // MOVE DOWN
        // =========================================================

        Button moveDownButton =
                new Button(
                        "↓ Move Down"
                );

        styleSecondaryButton(
                moveDownButton
        );

        moveDownButton.setOnAction(e -> {

            int index =
                    table.getSelectionModel()
                            .getSelectedIndex();

            if (
                    index >= 0
                    &&
                    index <
                    preferences.size() - 1
            ) {

                Preference preference =
                        preferences.remove(
                                index
                        );

                preferences.add(
                        index + 1,
                        preference
                );

                renumber();

                table.getSelectionModel()
                        .select(
                                index + 1
                        );
            }
        });

        // =========================================================
        // AI PREFERENCE ANALYZE
        // =========================================================

        Button aiAnalyzeButton =
                new Button(
                        "✦ AI Preference Analyze"
                );

        styleAiButton(
                aiAnalyzeButton
        );

        aiAnalyzeButton.setOnAction(e -> {

            if (preferences.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "AI Preference Analyze",
                        "Please add at least one college preference before running the analysis."
                );

                return;
            }

            Student student =
                    Student.getInstance();

            double percentile =
                    parsePercentile(
                            student.getCetPercentile()
                    );

            if (percentile < 0) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "CET Percentile Required",
                        "Your CET percentile is not available. Please complete your academic details first."
                );

                return;
            }

            String prompt =
                    buildAiPreferencePrompt(
                            student,
                            firebaseColleges
                    );

            aiAnalyzeButton.setDisable(true);
            aiAnalyzeButton.setText("Analyzing...");

            AsyncTaskRunner.run(
                    () -> PreferenceFillingAIService.analyzePreferences(prompt),

                    result -> {

                        aiAnalyzeButton.setDisable(false);
                        aiAnalyzeButton.setText("✦ AI Preference Analyze");

                        Navigation.goTo(
                                AIPreferencePage.getScene(result)
                        );
                    },

                    error -> {

                        aiAnalyzeButton.setDisable(false);
                        aiAnalyzeButton.setText("✦ AI Preference Analyze");

                        showAlert(
                                Alert.AlertType.ERROR,
                                "AI Analysis Failed",
                                getReadableErrorMessage(
                                        error,
                                        "Unable to analyze the preference list."
                                )
                        );
                    }
            );
        });

        // =========================================================
        // PREVIEW
        // =========================================================

        Button previewButton =
                new Button(
                        "Preview Option Form →"
                );

        stylePrimaryButton(
                previewButton
        );

        previewButton.setOnAction(e -> {

            if (preferences.isEmpty()) {

                showAlert(
                        Alert.AlertType.WARNING,
                        "Preference",
                        "Please add at least one preference."
                );

                return;
            }

            Navigation.goTo(
                    OptionPreviewPage
                            .getScene()
            );
        });

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox leftActions =
                new HBox(
                        10,
                        removeButton,
                        moveUpButton,
                        moveDownButton
                );

        leftActions.setAlignment(
                Pos.CENTER_LEFT
        );

        HBox rightActions =
                new HBox(
                        10,
                        aiAnalyzeButton,
                        previewButton
                );

        rightActions.setAlignment(
                Pos.CENTER_RIGHT
        );

        HBox actionRow =
                new HBox(
                        12,
                        leftActions,
                        spacer,
                        rightActions
                );

        actionRow.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================================================
        // LOCK UI
        // =========================================================

        if (locked || !choiceFillingOpen) {

            collegeBox.setDisable(
                    true
            );

            branchBox.setDisable(
                    true
            );

            addButton.setDisable(
                    true
            );

            removeButton.setDisable(
                    true
            );

            moveUpButton.setDisable(
                    true
            );

            moveDownButton.setDisable(
                    true
            );
        }

        // =========================================================
        // CONTENT
        // =========================================================

        content.getChildren()
                .addAll(
                        title,
                        subtitle,
                        lockStatus,
                        formCard,
                        table,
                        actionRow
                );

        ScrollPane scrollPane =
                new ScrollPane(
                        content
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
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background:"
                        + BG + ";" +
                "-fx-background-color:"
                        + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Preference Filling",
                        scrollPane
                )
        );
    }

    private static Scene createLoadingScene() {

        ProgressIndicator progressIndicator =
                new ProgressIndicator();

        progressIndicator.setPrefSize(
                55,
                55
        );

        Label loadingLabel =
                new Label(
                        "Loading preference form..."
                );

        loadingLabel.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:14px;"
        );

        VBox loadingCard =
                new VBox(
                        18,
                        progressIndicator,
                        loadingLabel
                );

        loadingCard.setAlignment(Pos.CENTER);
        loadingCard.setPadding(new Insets(34));
        loadingCard.setMaxWidth(420);
        loadingCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:14px;" +
                "-fx-background-radius:14px;"
        );

        VBox content =
                new VBox(
                        loadingCard
                );

        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40));
        content.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Preference Filling",
                        content
                )
        );
    }

    private record PreferencePageData(
            boolean finalMeritAvailable,
            boolean locked,
            boolean choiceFillingOpen,
            List<Preference> savedPreferences,
            List<College> colleges
    ) {
    }

    // =============================================================
    // GET PREFERENCES
    // =============================================================

    public static ObservableList<Preference>
    getPreferences() {

        return preferences;
    }

    // =============================================================
    // RENUMBER
    // =============================================================

    private static void renumber() {

        for (
                int i = 0;
                i < preferences.size();
                i++
        ) {

            preferences
                    .get(i)
                    .setPreferenceNumber(
                            i + 1
                    );
        }
    }

    // =============================================================
    // AI PREFERENCE ANALYSIS
    // =============================================================

    private static String buildAiPreferencePrompt(
            Student student,
            List<College> colleges
    ) {

        StringBuilder prompt =
                new StringBuilder();

        prompt.append(
                "You are an AI assistant for Maharashtra MHT CET CAP engineering counselling.\n"
        );

        prompt.append(
                "Analyze only the data supplied below. Do not invent college cutoffs or guarantee admission.\n"
        );

        prompt.append(
                "Give practical, concise advice. Classify each preference as DREAM, AMBITIOUS, TARGET, or SAFER.\n"
        );

        prompt.append(
                "Important: preference order should primarily reflect what the student genuinely wants. "
                        + "A lower-probability dream choice can remain above safer choices.\n\n"
        );

        prompt.append(
                "STUDENT DATA\n"
        );

        prompt.append(
                "CET Percentile: "
                        + student.getCetPercentile()
                        + "\n"
        );

        prompt.append(
                "Category: "
                        + safeAiValue(
                                student.getCategory()
                        )
                        + "\n\n"
        );

        prompt.append(
                "CURRENT PREFERENCE LIST\n"
        );

        for (Preference preference : preferences) {

            College college =
                    findCollegeForAi(
                            colleges,
                            preference.getCollege(),
                            preference.getBranch()
                    );

            prompt.append(
                    preference.getPreferenceNumber()
                            + ". "
                            + preference.getCollege()
                            + " | Branch: "
                            + preference.getBranch()
            );

            if (college != null) {

                prompt.append(
                        " | Available cutoff data: "
                                + college.getCutoff()
                );

            } else {

                prompt.append(
                        " | Available cutoff data: NOT AVAILABLE"
                );
            }

            prompt.append("\n");
        }

        prompt.append(
                "\nReturn the analysis with these sections:\n"
                        + "1. Preference-by-preference analysis\n"
                        + "2. Overall risk level\n"
                        + "3. Missing target/safer choices\n"
                        + "4. Suggested improvements to the list\n"
                        + "5. Final counselling advice\n"
                        + "After the advice, add exactly one final line in this format: AI_ORDER: 1,3,2\n"
                        + "The AI_ORDER must contain every current preference number exactly once in your recommended order.\n"
                        + "Clearly state that actual CAP allotment depends on official cutoffs, category, seat availability and round movement."
        );

        return prompt.toString();
    }

    private static College findCollegeForAi(
            List<College> colleges,
            String collegeName,
            String branchName
    ) {

        if (
                colleges == null ||
                collegeName == null ||
                branchName == null
        ) {
            return null;
        }

        for (College college : colleges) {

            if (college == null) {
                continue;
            }

            String storedCollege =
                    college.getCollegeName();

            String storedBranch =
                    college.getBranch();

            if (
                    storedCollege != null &&
                    storedBranch != null &&
                    storedCollege.trim()
                            .equalsIgnoreCase(
                                    collegeName.trim()
                            ) &&
                    storedBranch.trim()
                            .equalsIgnoreCase(
                                    branchName.trim()
                            )
            ) {
                return college;
            }
        }

        return null;
    }

    private static String safeAiValue(
            String value
    ) {

        return value == null || value.isBlank()
                ? "Not provided"
                : value.trim();
    }

    public static boolean applyAiPreferenceOrder(String result) {

        if (result == null || result.isBlank()) {
            return false;
        }

        String marker = "AI_ORDER:";
        int markerIndex = result.lastIndexOf(marker);

        if (markerIndex < 0) {
            return false;
        }

        String orderLine = result.substring(markerIndex + marker.length()).trim();
        int lineBreak = orderLine.indexOf('\n');

        if (lineBreak >= 0) {
            orderLine = orderLine.substring(0, lineBreak).trim();
        }

        String[] parts = orderLine.split(",");

        if (parts.length != preferences.size()) {
            return false;
        }

        java.util.List<Preference> original =
                new java.util.ArrayList<>(preferences);
        java.util.List<Preference> reordered =
                new java.util.ArrayList<>();
        java.util.Set<Integer> used =
                new java.util.HashSet<>();

        try {
            for (String part : parts) {
                int preferenceNumber = Integer.parseInt(part.trim());

                if (preferenceNumber < 1 ||
                        preferenceNumber > original.size() ||
                        !used.add(preferenceNumber)) {
                    return false;
                }

                reordered.add(original.get(preferenceNumber - 1));
            }
        } catch (NumberFormatException e) {
            return false;
        }

        preferences.setAll(reordered);
        renumber();
        return true;
    }

    private static double parsePercentile(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {

            return -1;
        }

        try {

            String cleaned =
                    value
                            .replace("%", "")
                            .trim();

            double percentile =
                    Double.parseDouble(
                            cleaned
                    );

            if (
                    percentile < 0 ||
                    percentile > 100
            ) {

                return -1;
            }

            return percentile;

        } catch (NumberFormatException e) {

            return -1;
        }
    }

    private static String buildOverallSuggestion(
            int total,
            int strong,
            int target,
            int ambitious,
            int veryAmbitious,
            int unavailable
    ) {

        StringBuilder suggestion =
                new StringBuilder();

        suggestion.append(
                String.format(
                        "%nStrong choices: %d%nTarget choices: %d%nAmbitious choices: %d%nVery ambitious choices: %d",
                        strong,
                        target,
                        ambitious,
                        veryAmbitious
                )
        );

        if (unavailable > 0) {

            suggestion.append(
                    String.format(
                            "%nChoices without cutoff data: %d",
                            unavailable
                    )
            );
        }

        suggestion.append(
                "\n\n"
        );

        if (
                total >= 3 &&
                (strong + target) > 0 &&
                (ambitious + veryAmbitious) > 0
        ) {

            suggestion.append(
                    "Your preference list has a useful mix of ambitious and realistic choices. "
                            + "Keep the colleges in the order you actually want them, while retaining "
                            + "enough realistic choices lower in the list."
            );

        } else if (
                strong == 0 &&
                target == 0
        ) {

            suggestion.append(
                    "Your list is currently high-risk because most available choices are above your percentile range. "
                            + "Consider adding a few colleges or branches with cutoffs closer to or below your percentile."
            );

        } else if (
                ambitious == 0 &&
                veryAmbitious == 0
        ) {

            suggestion.append(
                    "Your list is comparatively safe, but you may be missing higher-opportunity choices. "
                            + "If there are colleges you strongly prefer, you can place some ambitious options above your safer choices."
            );

        } else {

            suggestion.append(
                    "Your list can be improved by keeping a balanced combination of ambitious, target and safer preferences. "
                            + "Do not remove a dream college only because its cutoff is higher; place choices in your genuine preference order."
            );
        }

        return suggestion.toString();
    }

    // =============================================================
    // STYLES
    // =============================================================

    private static void styleComboBox(
            ComboBox<String> box
    ) {

        box.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-border-color:#3A493A;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:13px;" +
                "-fx-mark-color:" + LIME + ";"
        );

        box.setButtonCell(
                new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(box.getPromptText());
                            setStyle(
                                    "-fx-background-color:transparent;" +
                                    "-fx-text-fill:" + MUTED + ";" +
                                    "-fx-font-size:13px;"
                            );
                        } else {
                            setText(item);
                            setStyle(
                                    "-fx-background-color:transparent;" +
                                    "-fx-text-fill:" + WHITE + ";" +
                                    "-fx-font-size:13px;"
                            );
                        }
                    }
                }
        );

        box.setCellFactory(listView ->
                new ListCell<>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setStyle("-fx-background-color:" + FIELD + ";");
                            return;
                        }

                        setText(item);

                        String normal =
                                "-fx-background-color:" + FIELD + ";" +
                                "-fx-text-fill:" + WHITE + ";" +
                                "-fx-font-size:13px;" +
                                "-fx-padding:9 12 9 12;" +
                                "-fx-border-color:transparent;" +
                                "-fx-cursor:hand;";

                        String hover =
                                "-fx-background-color:" + LIME + ";" +
                                "-fx-text-fill:#0B100B;" +
                                "-fx-font-size:13px;" +
                                "-fx-font-weight:bold;" +
                                "-fx-padding:9 12 9 12;" +
                                "-fx-border-color:transparent;" +
                                "-fx-cursor:hand;";

                        setStyle(isSelected() ? hover : normal);

                        setOnMouseEntered(e -> setStyle(hover));
                        setOnMouseExited(e -> setStyle(isSelected() ? hover : normal));
                    }
                }
        );
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        String normal =
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#071007;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#D0FF4D;" +
                "-fx-text-fill:#071007;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) button.setStyle(normal);
        });
    }

    private static void styleAiButton(
            Button button
    ) {

        button.setPrefHeight(42);

        String normal =
                "-fx-background-color:#172217;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#071007;" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);

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
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        String normal =
                "-fx-background-color:#1A221A;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-border-color:#354235;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#202B20;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) button.setStyle(normal);
        });
    }

    private static void styleDangerButton(
            Button button
    ) {

        button.setPrefHeight(42);

        String normal =
                "-fx-background-color:#991B1B;" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#DC2626;" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);
        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) button.setStyle(hover);
        });
        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) button.setStyle(normal);
        });
    }

    private static <T> void styleTableColumn(
            TableColumn<Preference, T> column,
            String title
    ) {

        Label header = new Label(title);

        header.setStyle(
                "-fx-text-fill:#172017;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        column.setText(null);
        column.setGraphic(header);
    }

    private static String getReadableErrorMessage(
            Throwable error,
            String fallback
    ) {

        if (error == null) {
            return fallback;
        }

        Throwable current = error;

        while ((current instanceof CompletionException
                || current instanceof java.util.concurrent.ExecutionException)
                && current.getCause() != null) {

            current = current.getCause();
        }

        String message = current.getMessage();

        if (message == null || message.isBlank()) {
            return fallback;
        }

        return message;
    }

    private static void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

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

    // =============================================================
    // PREFERENCE MODEL
    // =============================================================

    public static class Preference {

        private int preferenceNumber;

        private String college;

        private String branch;

        public Preference(
                int preferenceNumber,
                String college,
                String branch
        ) {

            this.preferenceNumber =
                    preferenceNumber;

            this.college =
                    college;

            this.branch =
                    branch;
        }

        public int getPreferenceNumber() {

            return preferenceNumber;
        }

        public void setPreferenceNumber(
                int preferenceNumber
        ) {

            this.preferenceNumber =
                    preferenceNumber;
        }

        public String getCollege() {

            return college;
        }

        public void setCollege(
                String college
        ) {

            this.college =
                    college;
        }

        public String getBranch() {

            return branch;
        }

        public void setBranch(
                String branch
        ) {

            this.branch =
                    branch;
        }
    }
}
