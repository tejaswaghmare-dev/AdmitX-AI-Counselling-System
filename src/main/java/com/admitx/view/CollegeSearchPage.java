package com.admitx.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CollegeSearchPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String FIELD = "#101610";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title = new Label("College Search");

        title.setStyle(
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label subtitle = new Label(
                "Find colleges and courses based on your preferences."
        );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox heading = new VBox(
                5,
                title,
                subtitle
        );

        ComboBox<String> district = new ComboBox<>();
        district.getItems().addAll(
                "All Districts",
                "Pune",
                "Mumbai",
                "Nagpur",
                "Nashik",
                "Kolhapur",
                "Aurangabad"
        );
        district.setValue("All Districts");

        TextField collegeName = new TextField();
        collegeName.setPromptText("Search college name");

        ComboBox<String> branch = new ComboBox<>();
        branch.getItems().addAll(
                "All Branches",
                "Computer Engineering",
                "Information Technology",
                "Mechanical Engineering",
                "Electronics Engineering",
                "Civil Engineering"
        );
        branch.setValue("All Branches");

        ComboBox<String> collegeType = new ComboBox<>();
        collegeType.getItems().addAll(
                "All Types",
                "Government",
                "Government Aided",
                "Private"
        );
        collegeType.setValue("All Types");

        ComboBox<String> university = new ComboBox<>();
        university.getItems().addAll(
                "All Universities",
                "SPPU",
                "Mumbai University",
                "Shivaji University",
                "Nagpur University"
        );
        university.setValue("All Universities");

        styleField(district);
        styleField(collegeName);
        styleField(branch);
        styleField(collegeType);
        styleField(university);

        GridPane filters = new GridPane();

        filters.setHgap(15);
        filters.setVgap(15);

        filters.add(createLabel("District"), 0, 0);
        filters.add(district, 1, 0);

        filters.add(createLabel("College Name"), 2, 0);
        filters.add(collegeName, 3, 0);

        filters.add(createLabel("Branch"), 0, 1);
        filters.add(branch, 1, 1);

        filters.add(createLabel("College Type"), 2, 1);
        filters.add(collegeType, 3, 1);

        filters.add(createLabel("University"), 0, 2);
        filters.add(university, 1, 2);

        Button searchButton =
                new Button("Search Colleges");

        searchButton.setPrefHeight(40);

        searchButton.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );

        VBox filterCard = new VBox(
                18,
                createSectionTitle("SEARCH & FILTERS"),
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

        TableView<College> table =
                new TableView<>();

        TableColumn<College, String> codeColumn =
                new TableColumn<>("Code");

        codeColumn.setCellValueFactory(
                new PropertyValueFactory<>("code")
        );

        TableColumn<College, String> nameColumn =
                new TableColumn<>("College Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<College, String> districtColumn =
                new TableColumn<>("District");

        districtColumn.setCellValueFactory(
                new PropertyValueFactory<>("district")
        );

        TableColumn<College, String> branchColumn =
                new TableColumn<>("Branch");

        branchColumn.setCellValueFactory(
                new PropertyValueFactory<>("branch")
        );

        TableColumn<College, String> typeColumn =
                new TableColumn<>("Type");

        typeColumn.setCellValueFactory(
                new PropertyValueFactory<>("type")
        );

        table.getColumns().addAll(
                codeColumn,
                nameColumn,
                districtColumn,
                branchColumn,
                typeColumn
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        table.setPrefHeight(400);

        table.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-control-inner-background:" + FIELD + ";" +
                "-fx-table-cell-border-color:" + BORDER + ";" +
                "-fx-text-background-color:" + WHITE + ";" +
                "-fx-selection-bar:" + LIME + ";" +
                "-fx-selection-bar-non-focused:" + LIME + ";"
        );

        ObservableList<College> colleges =
                FXCollections.observableArrayList(

                        new College(
                                "COEP01",
                                "College of Engineering Pune",
                                "Pune",
                                "Computer Engineering",
                                "Government"
                        ),

                        new College(
                                "VIT01",
                                "Vishwakarma Institute of Technology",
                                "Pune",
                                "Information Technology",
                                "Private"
                        ),

                        new College(
                                "PICT01",
                                "Pune Institute of Computer Technology",
                                "Pune",
                                "Computer Engineering",
                                "Private"
                        ),

                        new College(
                                "WCE01",
                                "Walchand College of Engineering",
                                "Kolhapur",
                                "Computer Engineering",
                                "Government Aided"
                        ),

                        new College(
                                "SPIT01",
                                "Sardar Patel Institute of Technology",
                                "Mumbai",
                                "Information Technology",
                                "Private"
                        )
                );

        table.setItems(colleges);

        Label resultLabel =
                new Label("5 colleges found");

        resultLabel.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        searchButton.setOnAction(e -> {

            ObservableList<College> filtered =
                    FXCollections.observableArrayList();

            for (College c : colleges) {

                boolean districtMatch =
                        district.getValue().equals("All Districts")
                        || c.getDistrict()
                        .equals(district.getValue());

                boolean nameMatch =
                        collegeName.getText().isBlank()
                        || c.getName()
                        .toLowerCase()
                        .contains(
                                collegeName.getText()
                                        .toLowerCase()
                        );

                boolean branchMatch =
                        branch.getValue().equals("All Branches")
                        || c.getBranch()
                        .equals(branch.getValue());

                boolean typeMatch =
                        collegeType.getValue().equals("All Types")
                        || c.getType()
                        .equals(collegeType.getValue());

                boolean universityMatch =
                        university.getValue()
                                .equals("All Universities")
                        || university.getValue()
                                .equals("SPPU");

                if (
                        districtMatch &&
                        nameMatch &&
                        branchMatch &&
                        typeMatch &&
                        universityMatch
                ) {
                    filtered.add(c);
                }
            }

            table.setItems(filtered);

            resultLabel.setText(
                    filtered.size() + " colleges found"
            );
        });

        Button informationButton =
                new Button("View College Information →");

        informationButton.setPrefHeight(42);

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
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                Navigation.goTo(
                        CollegeInfoPage.getScene(selected)
                );
            }
        });

        Button backButton =
                new Button("← Back");

        backButton.setPrefHeight(42);

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

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        backButton,
                        spacer,
                        informationButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox tableCard =
                new VBox(
                        12,
                        createSectionTitle("AVAILABLE COLLEGES"),
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
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

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

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );

        return label;
    }

    private static Label createLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        return label;
    }

    private static void styleField(
            Control control
    ) {

        control.setPrefHeight(38);

        control.setPrefWidth(190);

        control.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-prompt-text-fill:" + MUTED + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;"
        );
    }

    public static class College {

        private final String code;
        private final String name;
        private final String district;
        private final String branch;
        private final String type;

        public College(
                String code,
                String name,
                String district,
                String branch,
                String type
        ) {

            this.code = code;
            this.name = name;
            this.district = district;
            this.branch = branch;
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getDistrict() {
            return district;
        }

        public String getBranch() {
            return branch;
        }

        public String getType() {
            return type;
        }
    }
}