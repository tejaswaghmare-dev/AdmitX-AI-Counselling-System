package com.admitx.view;

import java.util.List;

import com.admitx.dao.CollegeDAO;
import com.admitx.dao.MeritDAO;
import com.admitx.model.College;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

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

        MeritDAO meritDAO =
                new MeritDAO();

        boolean finalMeritPublished =
                meritDAO.isFinalPublished();

        // =====================================================
        // BLOCK ACCESS IF FINAL MERIT NOT PUBLISHED
        // =====================================================

        if (!finalMeritPublished) {

            return createLockedScene();
        }

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label(
                        "College Search"
                );

        title.setStyle(
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Find colleges and courses based on your preferences."
                );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        5,
                        title,
                        subtitle
                );

        // =====================================================
        // COLLEGE DATA
        // =====================================================

        ObservableList<College> colleges =
                FXCollections.observableArrayList();

        CollegeDAO collegeDAO =
                new CollegeDAO();

        try {

            List<College> firebaseColleges =
                    collegeDAO.getAllColleges();

            if (firebaseColleges != null) {

                colleges.addAll(
                        firebaseColleges
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        // =====================================================
        // FILTERS
        // =====================================================

        ComboBox<String> district =
                new ComboBox<>();

        district.getItems().add(
                "All Districts"
        );

        ComboBox<String> branch =
                new ComboBox<>();

        branch.getItems().add(
                "All Branches"
        );

        ComboBox<String> university =
                new ComboBox<>();

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

        styleField(
                collegeName
        );

        styleField(
                branch
        );

        styleField(
                university
        );

        GridPane filters =
                new GridPane();

        filters.setHgap(
                15
        );

        filters.setVgap(
                15
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
                40
        );

        searchButton.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );

        VBox filterCard =
                new VBox(
                        18,
                        createSectionTitle(
                                "SEARCH & FILTERS"
                        ),
                        filters,
                        searchButton
                );

        filterCard.setPadding(
                new Insets(22)
        );

        filterCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        // =====================================================
        // TABLE
        // =====================================================

        TableView<College> table =
                new TableView<>();

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

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setPrefHeight(
                400
        );

        table.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-control-inner-background:" + FIELD + ";" +
                "-fx-table-cell-border-color:" + BORDER + ";" +
                "-fx-text-background-color:" + WHITE + ";" +
                "-fx-selection-bar:" + LIME + ";" +
                "-fx-selection-bar-non-focused:" + LIME + ";"
        );

        table.setItems(
                colleges
        );

        Label resultLabel =
                new Label(
                        colleges.size()
                                + " colleges found"
                );

        resultLabel.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        if (colleges.isEmpty()) {

            Label emptyLabel =
                    new Label(
                            "No colleges are available. "
                                    + "Ask counsellor to add colleges."
                    );

            emptyLabel.setStyle(
                    "-fx-text-fill:"
                            + MUTED
                            + ";"
            );

            table.setPlaceholder(
                    emptyLabel
            );
        }

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
                42
        );

        refreshButton.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-cursor:hand;"
        );

        refreshButton.setOnAction(e -> {

            try {

                colleges.clear();

                List<College> updatedColleges =
                        collegeDAO.getAllColleges();

                if (updatedColleges != null) {

                    colleges.addAll(
                            updatedColleges
                    );
                }

                table.setItems(
                        colleges
                );

                resultLabel.setText(
                        colleges.size()
                                + " colleges found"
                );

                district.getItems().clear();
                branch.getItems().clear();
                university.getItems().clear();

                district.getItems().add(
                        "All Districts"
                );

                branch.getItems().add(
                        "All Branches"
                );

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

                collegeName.clear();

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        });

        // =====================================================
        // VIEW INFORMATION
        // =====================================================

        Button informationButton =
                new Button(
                        "View College Information →"
                );

        informationButton.setPrefHeight(
                42
        );

        informationButton.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
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

        // =====================================================
        // BACK
        // =====================================================

        Button backButton =
                new Button(
                        "← Back"
                );

        backButton.setPrefHeight(
                42
        );

        backButton.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-cursor:hand;"
        );

        backButton.setOnAction(e ->

                Navigation.goTo(
                        FinalMeritPage.getScene()
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        backButton,
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
                        12,
                        createSectionTitle(
                                "AVAILABLE COLLEGES"
                        ),
                        resultLabel,
                        table,
                        buttons
                );

        tableCard.setPadding(
                new Insets(22)
        );

        tableCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        filterCard,
                        tableCard
                );

        content.setPadding(
                new Insets(30)
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

        scrollPane.setStyle(
                "-fx-background:" + BG + ";" +
                "-fx-background-color:" + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "College Search",
                        scrollPane
                )
        );
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
                "-fx-font-size:26px;" +
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
                new Insets(25)
        );

        card.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        Button finalMerit =
                new Button(
                        "View Final Merit Status"
                );

        finalMerit.setPrefHeight(
                42
        );

        finalMerit.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
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
                42
        );

        dashboard.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;" +
                "-fx-cursor:hand;"
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
            ComboBox<String> district,
            ComboBox<String> branch,
            ComboBox<String> university
    ) {

        for (College college : colleges) {

            if (
                    college.getDistrict() != null
                            &&
                            !college.getDistrict().isBlank()
                            &&
                            !district.getItems().contains(
                                    college.getDistrict()
                            )
            ) {

                district.getItems().add(
                        college.getDistrict()
                );
            }

            if (
                    college.getBranch() != null
                            &&
                            !college.getBranch().isBlank()
                            &&
                            !branch.getItems().contains(
                                    college.getBranch()
                            )
            ) {

                branch.getItems().add(
                        college.getBranch()
                );
            }

            if (
                    college.getUniversity() != null
                            &&
                            !college.getUniversity().isBlank()
                            &&
                            !university.getItems().contains(
                                    college.getUniversity()
                            )
            ) {

                university.getItems().add(
                        college.getUniversity()
                );
            }
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
                "-fx-font-size:11px;" +
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
                38
        );

        control.setPrefWidth(
                190
        );

        control.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-prompt-text-fill:" + MUTED + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );
    }
}