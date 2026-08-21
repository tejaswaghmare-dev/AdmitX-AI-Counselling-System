package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

public class CollegeSearchPage {

    public static Scene getScene() {

        Label title = new Label("College Search");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
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
        collegeName.setPromptText("Search College Name");

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

        GridPane filters = new GridPane();

        filters.setHgap(15);
        filters.setVgap(10);

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

        Button searchButton = new Button("Search");

        searchButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 36px;"
        );

        TableView<College> table = new TableView<>();

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

        table.setPrefHeight(400);

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
                        university.getValue().equals("All Universities")
        ||              university.getValue().equals("SPPU");

                if (districtMatch
                        && nameMatch
                        && branchMatch
                        && typeMatch
                        && universityMatch) {

                    filtered.add(c);
                }
            }

            table.setItems(filtered);
        });

        Button informationButton =
                new Button("View College Information");

        informationButton.setStyle(
                "-fx-background-color: #3F6212;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;"
        );

        informationButton.setOnAction(e -> {

            College selected =
                    table.getSelectionModel()
                            .getSelectedItem();

            if (selected != null) {

                Navigation.goTo(
                        CollegeInfoPage.getScene(
                                selected
                        )
                );
            }
        });

        Button backButton =
                new Button("Back");

        backButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;"
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        FinalMeritPage.getScene()
                )
        );

        HBox buttons =
                new HBox(
                        15,
                        backButton,
                        informationButton
                );

        buttons.setAlignment(
                Pos.CENTER_RIGHT
        );

        VBox content =
                new VBox(
                        20,
                        title,
                        filters,
                        searchButton,
                        table,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        return new Scene(
                StudentLayout.create(
                        "College Search",
                        scrollPane
                )
        );
    }

    private static Label createLabel(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        return label;
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