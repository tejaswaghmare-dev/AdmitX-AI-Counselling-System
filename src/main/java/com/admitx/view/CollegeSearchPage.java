package com.admitx.view;

import com.admitx.util.AsyncTaskRunner;

import javafx.scene.control.ProgressIndicator;
import com.admitx.dao.CollegeDAO;
import com.admitx.dao.MeritDAO;
import com.admitx.model.College;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

public class CollegeSearchPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String FIELD = "#101610";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String ORANGE = "#F97316";

    public static Scene getScene() {

        Scene loadingScene =
                createLoadingScene();

        AsyncTaskRunner.run(
                () -> new MeritDAO().isFinalPublished(),

                finalMeritPublished -> {

                    if (finalMeritPublished) {

                        Navigation.goTo(
                                createCollegeScene()
                        );

                    } else {

                        Navigation.goTo(
                                createLockedScene()
                        );
                    }
                },

                error -> {

                    error.printStackTrace();

                    showError(
                            "Loading Error",
                            "Could not check the final merit status. "
                                    + "Please check your internet connection."
                    );

                    Navigation.goTo(
                            StudentDashboardPage.getScene()
                    );
                }
        );

        return loadingScene;
    }

    private static Scene createCollegeScene() {

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label(
                        "College Search"
                );

        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Find colleges and courses based on your preferences."
                );

        subtitle.setWrapText(true);
        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        heading.setPadding(
                new Insets(0, 0, 2, 0)
        );

        // =====================================================
        // COLLEGE DATA
        // =====================================================

        ObservableList<College> colleges =
                FXCollections.observableArrayList();

        CollegeDAO collegeDAO =
                new CollegeDAO();

        // =====================================================
        // FILTERS
        // =====================================================

        DarkDropdown district =
                new DarkDropdown();

        district.getItems().add(
                "All Districts"
        );

        DarkDropdown branch =
                new DarkDropdown();

        branch.getItems().add(
                "All Branches"
        );

        DarkDropdown university =
                new DarkDropdown();

        university.getItems().add(
                "All Universities"
        );

        populateFilters(
                colleges,
                district,
                branch,
                university
        );

        district.setValue(
                "All Districts"
        );

        branch.setValue(
                "All Branches"
        );

        university.setValue(
                "All Universities"
        );

        TextField collegeName =
                new TextField();

        collegeName.setPromptText(
                "Search college name"
        );

        styleField(
                district
        );

        district.setMaxWidth(
                Double.MAX_VALUE
        );

        styleField(
                collegeName
        );

        collegeName.setMaxWidth(
                Double.MAX_VALUE
        );

        styleField(
                branch
        );

        branch.setMaxWidth(
                Double.MAX_VALUE
        );

        styleField(
                university
        );

        university.setMaxWidth(
                Double.MAX_VALUE
        );

        GridPane filters =
                new GridPane();

        filters.setHgap(
                15
        );

        filters.setVgap(
                16
        );

        filters.setMaxWidth(
                Double.MAX_VALUE
        );

        javafx.scene.layout.ColumnConstraints labelColumn1 =
                new javafx.scene.layout.ColumnConstraints();
        labelColumn1.setPercentWidth(14);

        javafx.scene.layout.ColumnConstraints fieldColumn1 =
                new javafx.scene.layout.ColumnConstraints();
        fieldColumn1.setPercentWidth(36);
        fieldColumn1.setHgrow(Priority.ALWAYS);

        javafx.scene.layout.ColumnConstraints labelColumn2 =
                new javafx.scene.layout.ColumnConstraints();
        labelColumn2.setPercentWidth(14);

        javafx.scene.layout.ColumnConstraints fieldColumn2 =
                new javafx.scene.layout.ColumnConstraints();
        fieldColumn2.setPercentWidth(36);
        fieldColumn2.setHgrow(Priority.ALWAYS);

        filters.getColumnConstraints().addAll(
                labelColumn1,
                fieldColumn1,
                labelColumn2,
                fieldColumn2
        );

        filters.add(
                createLabel(
                        "District"
                ),
                0,
                0
        );

        filters.add(
                district,
                1,
                0
        );

        filters.add(
                createLabel(
                        "College Name"
                ),
                2,
                0
        );

        filters.add(
                collegeName,
                3,
                0
        );

        filters.add(
                createLabel(
                        "Branch"
                ),
                0,
                1
        );

        filters.add(
                branch,
                1,
                1
        );

        filters.add(
                createLabel(
                        "University"
                ),
                2,
                1
        );

        filters.add(
                university,
                3,
                1
        );

        // =====================================================
        // SEARCH BUTTON
        // =====================================================

        Button searchButton =
                new Button(
                        "Search Colleges"
                );

        searchButton.setPrefHeight(
                44
        );

        searchButton.setMinWidth(
                155
        );

        stylePrimaryButton(
                searchButton
        );

        HBox searchAction =
                new HBox(
                        searchButton
                );

        searchAction.setAlignment(
                Pos.CENTER_RIGHT
        );

        VBox filterCard =
                new VBox(
                        18,
                        createSectionTitle(
                                "SEARCH & FILTERS"
                        ),
                        filters,
                        searchAction
                );

        filterCard.setPadding(
                new Insets(24)
        );

        filterCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:14px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:14px;"
        );

        // =====================================================
        // TABLE
        // =====================================================

        TableView<College> table =
                new TableView<>();

        ProgressIndicator loadingIndicator =
                new ProgressIndicator();

        loadingIndicator.setPrefSize(
                42,
                42
        );

        table.setPlaceholder(
                loadingIndicator
        );

        TableColumn<College, String> codeColumn =
                new TableColumn<>(
                        "College ID"
                );

        codeColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "collegeID"
                )
        );

        TableColumn<College, String> nameColumn =
                new TableColumn<>(
                        "College Name"
                );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "collegeName"
                )
        );

        TableColumn<College, String> districtColumn =
                new TableColumn<>(
                        "District"
                );

        districtColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "district"
                )
        );

        TableColumn<College, String> universityColumn =
                new TableColumn<>(
                        "University"
                );

        universityColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "university"
                )
        );

        TableColumn<College, String> branchColumn =
                new TableColumn<>(
                        "Branch"
                );

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "branch"
                )
        );

        TableColumn<College, Integer> intakeColumn =
                new TableColumn<>(
                        "Intake"
                );

        intakeColumn.setCellValueFactory(
                new PropertyValueFactory<>(
                        "intake"
                )
        );

        table.getColumns().addAll(
                codeColumn,
                nameColumn,
                districtColumn,
                universityColumn,
                branchColumn,
                intakeColumn
        );

        styleTableColumn(codeColumn, "College ID");
        styleTableColumn(nameColumn, "College Name");
        styleTableColumn(districtColumn, "District");
        styleTableColumn(universityColumn, "University");
        styleTableColumn(branchColumn, "Branch");
        styleTableColumn(intakeColumn, "Intake");

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setPrefHeight(
                460
        );

        table.setMinHeight(
                360
        );

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
                "-fx-background-radius:10px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:10px;"
        );

        table.setItems(
                colleges
        );

        Label resultLabel =
                new Label(
                        "Loading colleges..."
                );

        resultLabel.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        // =====================================================
        // SEARCH
        // =====================================================

        searchButton.setOnAction(e -> {

            ObservableList<College> filtered =
                    FXCollections.observableArrayList();

            String selectedDistrict =
                    district.getValue();

            String selectedBranch =
                    branch.getValue();

            String selectedUniversity =
                    university.getValue();

            String searchText =
                    collegeName
                            .getText()
                            .trim()
                            .toLowerCase();

            for (College c : colleges) {

                boolean districtMatch =
                        "All Districts".equals(
                                selectedDistrict
                        )
                                ||
                                (
                                        c.getDistrict() != null
                                                &&
                                                c.getDistrict()
                                                        .equalsIgnoreCase(
                                                                selectedDistrict
                                                        )
                                );

                boolean nameMatch =
                        searchText.isBlank()
                                ||
                                (
                                        c.getCollegeName() != null
                                                &&
                                                c.getCollegeName()
                                                        .toLowerCase()
                                                        .contains(
                                                                searchText
                                                        )
                                );

                boolean branchMatch =
                        "All Branches".equals(
                                selectedBranch
                        )
                                ||
                                (
                                        c.getBranch() != null
                                                &&
                                                c.getBranch()
                                                        .equalsIgnoreCase(
                                                                selectedBranch
                                                        )
                                );

                boolean universityMatch =
                        "All Universities".equals(
                                selectedUniversity
                        )
                                ||
                                (
                                        c.getUniversity() != null
                                                &&
                                                c.getUniversity()
                                                        .equalsIgnoreCase(
                                                                selectedUniversity
                                                        )
                                );

                if (
                        districtMatch
                                &&
                                nameMatch
                                &&
                                branchMatch
                                &&
                                universityMatch
                ) {

                    filtered.add(
                            c
                    );
                }
            }

            table.setItems(
                    filtered
            );

            resultLabel.setText(
                    filtered.size()
                            + " colleges found"
            );
        });

        // =====================================================
        // REFRESH
        // =====================================================

        Button refreshButton =
                new Button(
                        "Refresh"
                );

        refreshButton.setPrefHeight(
                44
        );

        refreshButton.setMinWidth(
                105
        );

        styleSecondaryButton(
                refreshButton
        );

        refreshButton.setOnAction(e -> {

            loadColleges(
                    collegeDAO,
                    colleges,
                    table,
                    resultLabel,
                    district,
                    branch,
                    university,
                    collegeName,
                    searchButton,
                    refreshButton,
                    loadingIndicator
            );
        });

        // =====================================================
        // VIEW INFORMATION
        // =====================================================

        Button informationButton =
                new Button(
                        "View College Information →"
                );

        informationButton.setPrefHeight(
                44
        );

        informationButton.setMinWidth(
                205
        );

        stylePrimaryButton(
                informationButton
        );

        informationButton.setOnAction(e -> {

            College selected =
                    table
                            .getSelectionModel()
                            .getSelectedItem();

            if (selected == null) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.WARNING
                        );

                alert.setTitle(
                        "College Selection"
                );

                alert.setHeaderText(
                        null
                );

                alert.setContentText(
                        "Please select a college first."
                );

                alert.showAndWait();

                return;
            }

            Navigation.goTo(
                    CollegeInfoPage.getScene(
                            selected
                    )
            );
        });

        

        

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        
                        refreshButton,
                        spacer,
                        informationButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // TABLE CARD
        // =====================================================

        VBox tableCard =
                new VBox(
                        14,
                        createSectionTitle(
                                "AVAILABLE COLLEGES"
                        ),
                        resultLabel,
                        table,
                        buttons
                );

        tableCard.setPadding(
                new Insets(24)
        );

        tableCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:14px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:14px;"
        );

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        24,
                        heading,
                        filterCard,
                        tableCard
                );

        content.setPadding(
                new Insets(20, 24, 30, 24)
        );

        content.setFillWidth(
                true
        );

        content.setMaxWidth(
                Double.MAX_VALUE
        );

        content.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setPannable(
                true
        );

        scrollPane.setStyle(
                "-fx-background:" + BG + ";" +
                "-fx-background-color:" + BG + ";"
        );

        loadColleges(
                collegeDAO,
                colleges,
                table,
                resultLabel,
                district,
                branch,
                university,
                collegeName,
                searchButton,
                refreshButton,
                loadingIndicator
        );

        return new Scene(
                StudentLayout.create(
                        "College Search",
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
                        "Checking college search availability..."
                );

        loadingLabel.setStyle(
                "-fx-font-size:14px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox loadingCard =
                new VBox(
                        18,
                        progressIndicator,
                        loadingLabel
                );

        loadingCard.setAlignment(
                Pos.CENTER
        );

        loadingCard.setPadding(
                new Insets(34)
        );

        loadingCard.setMaxWidth(
                420
        );

        loadingCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:14px;" +
                "-fx-background-radius:14px;"
        );

        javafx.scene.layout.StackPane content =
                new javafx.scene.layout.StackPane(
                        loadingCard
                );

        content.setPadding(
                new Insets(40)
        );

        content.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "College Search",
                        content
                )
        );
    }

    private static void loadColleges(
            CollegeDAO collegeDAO,
            ObservableList<College> colleges,
            TableView<College> table,
            Label resultLabel,
            DarkDropdown district,
            DarkDropdown branch,
            DarkDropdown university,
            TextField collegeName,
            Button searchButton,
            Button refreshButton,
            ProgressIndicator loadingIndicator
    ) {

        searchButton.setDisable(true);
        refreshButton.setDisable(true);

        resultLabel.setText(
                "Loading colleges..."
        );

        table.setPlaceholder(
                loadingIndicator
        );

        AsyncTaskRunner.run(
                collegeDAO::getAllColleges,

                loadedColleges -> {

                    colleges.clear();

                    if (loadedColleges != null) {

                        colleges.addAll(
                                loadedColleges
                        );
                    }

                    table.setItems(
                            colleges
                    );

                    resultLabel.setText(
                            colleges.size()
                                    + " colleges found"
                    );

                    resetFilters(
                            colleges,
                            district,
                            branch,
                            university
                    );

                    collegeName.clear();

                    Label emptyLabel =
                            new Label(
                                    "No colleges are available. "
                                            + "Ask counsellor to add colleges."
                            );

                    emptyLabel.setStyle(
                            "-fx-text-fill:" + MUTED + ";"
                    );

                    table.setPlaceholder(
                            emptyLabel
                    );

                    searchButton.setDisable(false);
                    refreshButton.setDisable(false);
                },

                error -> {

                    error.printStackTrace();

                    resultLabel.setText(
                            "Could not load colleges"
                    );

                    Label errorLabel =
                            new Label(
                                    "College data could not be loaded."
                            );

                    errorLabel.setStyle(
                            "-fx-text-fill:" + ORANGE + ";"
                    );

                    table.setPlaceholder(
                            errorLabel
                    );

                    searchButton.setDisable(false);
                    refreshButton.setDisable(false);

                    showError(
                            "Loading Error",
                            "Could not load colleges. "
                                    + "Please check your internet connection."
                    );
                }
        );
    }

    private static void resetFilters(
            ObservableList<College> colleges,
            DarkDropdown district,
            DarkDropdown branch,
            DarkDropdown university
    ) {

        district.getItems().setAll(
                "All Districts"
        );

        branch.getItems().setAll(
                "All Branches"
        );

        university.getItems().setAll(
                "All Universities"
        );

        populateFilters(
                colleges,
                district,
                branch,
                university
        );

        district.setValue(
                "All Districts"
        );

        branch.setValue(
                "All Branches"
        );

        university.setValue(
                "All Universities"
        );
    }

    private static void showError(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
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

    // =========================================================
    // LOCKED PAGE
    // =========================================================

    private static Scene createLockedScene() {

        Label title =
                new Label(
                        "College Search"
                );

        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label badge =
                new Label(
                        "●  LOCKED"
                );

        badge.setStyle(
                "-fx-background-color:#2A1B10;" +
                "-fx-text-fill:" + ORANGE + ";" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-padding:8 14 8 14;" +
                "-fx-background-radius:20px;" +
                "-fx-border-color:#5C3518;" +
                "-fx-border-radius:20px;"
        );

        Label message =
                new Label(
                        "College Search will be available after "
                                + "the Final Merit List is published."
                );

        message.setWrapText(
                true
        );

        message.setStyle(
                "-fx-font-size:14px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        Label instruction =
                new Label(
                        "Complete the merit process first, then return here "
                                + "to search colleges and continue preference filling."
                );

        instruction.setWrapText(
                true
        );

        instruction.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox card =
                new VBox(
                        16,
                        badge,
                        message,
                        instruction
                );

        card.setPadding(
                new Insets(28)
        );

        card.setMaxWidth(
                760
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:14px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:14px;"
        );

        Button finalMerit =
                new Button(
                        "View Final Merit Status"
                );

        finalMerit.setPrefHeight(
                44
        );

        stylePrimaryButton(
                finalMerit
        );

        finalMerit.setOnAction(e ->

                Navigation.goTo(
                        FinalMeritPage.getScene()
                )
        );

        Button dashboard =
                new Button(
                        "← Dashboard"
                );

        dashboard.setPrefHeight(
                44
        );

        styleSecondaryButton(
                dashboard
        );

        dashboard.setOnAction(e ->

                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        HBox buttons =
                new HBox(
                        12,
                        dashboard,
                        finalMerit
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        title,
                        card,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "College Search",
                        content
                )
        );
    }

    // =========================================================
    // POPULATE FILTERS
    // =========================================================

    private static void populateFilters(
            ObservableList<College> colleges,
            DarkDropdown district,
            DarkDropdown branch,
            DarkDropdown university
    ) {

        for (College college : colleges) {

            addUniqueIgnoreCase(
                    district,
                    college.getDistrict()
            );

            addUniqueIgnoreCase(
                    branch,
                    college.getBranch()
            );

            addUniqueIgnoreCase(
                    university,
                    college.getUniversity()
            );
        }
    }

    private static void addUniqueIgnoreCase(
            DarkDropdown comboBox,
            String value
    ) {

        if (value == null || value.isBlank()) {
            return;
        }

        String cleanedValue =
                value.trim();

        boolean alreadyPresent =
                comboBox.getItems()
                        .stream()
                        .anyMatch(existing ->
                                existing != null
                                        && existing.equalsIgnoreCase(
                                                cleanedValue
                                        )
                        );

        if (!alreadyPresent) {
            comboBox.getItems().add(
                    cleanedValue
            );
        }
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
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );

        return label;
    }

    // =========================================================
    // LABEL
    // =========================================================

    private static Label createLabel(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        return label;
    }

    // =========================================================
    // FIELD STYLE
    // =========================================================

    private static void styleField(
            Control control
    ) {

        control.setPrefHeight(
                44
        );

        control.setPrefWidth(
                210
        );

        if (control instanceof DarkDropdown dropdown) {

            dropdown.applyDarkStyle();

            return;
        }

        control.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-prompt-text-fill:" + MUTED + ";" +
                "-fx-border-color:#3A493A;" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:13px;" +
                "-fx-padding:0 12 0 12;"
        );
    }

    /*
     * Custom dropdown used instead of JavaFX ComboBox.
     *
     * The default ComboBox popup uses an internal ListView/VirtualFlow.
     * On some JavaFX/Windows combinations that skin draws a horizontal
     * focus separator inside the popup. This control owns its popup
     * completely, so there is no ListView and therefore no unwanted line.
     */
    private static final class DarkDropdown extends Button {

        private final ObservableList<String> items =
                FXCollections.observableArrayList();

        private final Popup popup =
                new Popup();

        private final VBox popupContent =
                new VBox();

        private String value;
        private String promptText = "";

        private DarkDropdown() {

            setAlignment(
                    Pos.CENTER_LEFT
            );

            setMaxWidth(
                    Double.MAX_VALUE
            );

            popup.setAutoHide(
                    true
            );

            popup.setAutoFix(
                    true
            );

            popup.setHideOnEscape(
                    true
            );

            popupContent.setSpacing(
                    0
            );

            popupContent.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web(FIELD),
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY
                            )
                    )
            );

            popupContent.setStyle(
                    "-fx-background-color:" + FIELD + ";" +
                    "-fx-border-color:#3A493A;" +
                    "-fx-border-width:1px;" +
                    "-fx-padding:0;"
            );

            popup.getContent().add(
                    popupContent
            );

            items.addListener(
                    (javafx.collections.ListChangeListener<String>) change ->
                            rebuildPopup()
            );

            setOnAction(e ->
                    togglePopup()
            );

            applyDarkStyle();
            updateButtonText();
        }

        private ObservableList<String> getItems() {
            return items;
        }

        private String getValue() {
            return value;
        }

        private void setValue(
                String value
        ) {

            this.value = value;

            updateButtonText();
        }

        private void setPromptText(
                String promptText
        ) {

            this.promptText =
                    promptText == null
                            ? ""
                            : promptText;

            updateButtonText();
        }

        private void applyDarkStyle() {

            setStyle(
                    "-fx-background-color:" + FIELD + ";" +
                    "-fx-text-fill:" + WHITE + ";" +
                    "-fx-border-color:#3A493A;" +
                    "-fx-border-width:1px;" +
                    "-fx-border-radius:8px;" +
                    "-fx-background-radius:8px;" +
                    "-fx-font-size:13px;" +
                    "-fx-padding:0 14 0 14;" +
                    "-fx-cursor:hand;"
            );
        }

        private void updateButtonText() {

            String shown =
                    value == null || value.isBlank()
                            ? promptText
                            : value;

            if (shown == null || shown.isBlank()) {
                shown = "Select";
            }

            setText(
                    shown + "   ▾"
            );
        }

        private void rebuildPopup() {

            popupContent.getChildren().clear();

            for (String item : items) {

                Label option =
                        new Label(item);

                option.setAlignment(
                        Pos.CENTER_LEFT
                );

                option.setMinHeight(
                        44
                );

                option.setPrefHeight(
                        44
                );

                option.setMaxWidth(
                        Double.MAX_VALUE
                );

                option.setBackground(
                        new Background(
                                new BackgroundFill(
                                        Color.web(FIELD),
                                        CornerRadii.EMPTY,
                                        Insets.EMPTY
                                )
                        )
                );

                applyOptionNormalStyle(
                        option
                );

                option.addEventHandler(
                        MouseEvent.MOUSE_ENTERED,
                        e -> applyOptionHoverStyle(
                                option
                        )
                );

                option.addEventHandler(
                        MouseEvent.MOUSE_EXITED,
                        e -> {

                            if (item.equals(value)) {

                                applyOptionSelectedStyle(
                                        option
                                );

                            } else {

                                applyOptionNormalStyle(
                                        option
                                );
                            }
                        }
                );

                option.addEventHandler(
                        MouseEvent.MOUSE_CLICKED,
                        e -> {

                            setValue(
                                    item
                            );

                            popup.hide();

                            rebuildPopup();
                        }
                );

                if (item.equals(value)) {

                    applyOptionSelectedStyle(
                            option
                    );
                }

                popupContent.getChildren().add(
                        option
                );
            }
        }

        private void togglePopup() {

            if (popup.isShowing()) {

                popup.hide();

                return;
            }

            rebuildPopup();

            javafx.geometry.Bounds bounds =
                    localToScreen(
                            getBoundsInLocal()
                    );

            if (bounds == null) {
                return;
            }

            double popupWidth =
                    Math.max(
                            getWidth(),
                            300
                    );

            popupContent.setPrefWidth(
                    popupWidth
            );

            popupContent.setMinWidth(
                    popupWidth
            );

            popupContent.setMaxWidth(
                    popupWidth
            );

            popup.show(
                    this,
                    bounds.getMinX(),
                    bounds.getMaxY() + 1
            );
        }

        private void applyOptionNormalStyle(
                Label option
        ) {

            option.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web(FIELD),
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY
                            )
                    )
            );

            option.setStyle(
                    "-fx-background-color:" + FIELD + ";" +
                    "-fx-text-fill:" + WHITE + ";" +
                    "-fx-font-size:13px;" +
                    "-fx-padding:0 14 0 14;" +
                    "-fx-border-color:transparent;" +
                    "-fx-border-width:0;" +
                    "-fx-background-insets:0;" +
                    "-fx-cursor:hand;"
            );
        }

        private void applyOptionHoverStyle(
                Label option
        ) {

            option.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.web(LIME),
                                    CornerRadii.EMPTY,
                                    Insets.EMPTY
                            )
                    )
            );

            option.setStyle(
                    "-fx-background-color:" + LIME + ";" +
                    "-fx-text-fill:#0B100B;" +
                    "-fx-font-size:13px;" +
                    "-fx-font-weight:bold;" +
                    "-fx-padding:0 14 0 14;" +
                    "-fx-border-color:transparent;" +
                    "-fx-border-width:0;" +
                    "-fx-background-insets:0;" +
                    "-fx-cursor:hand;"
            );
        }

        private void applyOptionSelectedStyle(
                Label option
        ) {

            applyOptionHoverStyle(
                    option
            );
        }
    }

    private static <T> void styleTableColumn(
            TableColumn<College, T> column,
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

    private static void stylePrimaryButton(
            Button button
    ) {

        String normal =
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#D0FF4D;" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);

        button.setOnMouseEntered(e ->
                button.setStyle(hover)
        );

        button.setOnMouseExited(e ->
                button.setStyle(normal)
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        String normal =
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String hover =
                "-fx-background-color:#1B241B;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        button.setStyle(normal);

        button.setOnMouseEntered(e ->
                button.setStyle(hover)
        );

        button.setOnMouseExited(e ->
                button.setStyle(normal)
        );
    }

}
