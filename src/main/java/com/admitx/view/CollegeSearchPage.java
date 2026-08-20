package com.admitx.view;


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

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("🏛️ College Search");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Search and explore colleges");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Filter Card
        VBox filterCard = new VBox(15);
        filterCard.setPadding(new Insets(20, 25, 25, 25));
        filterCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 180px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 13px;";

        GridPane filters = new GridPane();
        filters.setHgap(15);
        filters.setVgap(12);

        ComboBox<String> district = new ComboBox<>();
        district.getItems().addAll("All Districts", "Pune", "Mumbai", "Nagpur", "Nashik", "Kolhapur", "Aurangabad");
        district.setValue("All Districts");
        district.setStyle(fieldStyle);

        TextField collegeName = new TextField();
        collegeName.setPromptText("Search College Name");
        collegeName.setStyle(fieldStyle);

        ComboBox<String> branch = new ComboBox<>();
        branch.getItems().addAll("All Branches", "Computer Engineering", "Information Technology", "Mechanical Engineering", "Electronics Engineering", "Civil Engineering");
        branch.setValue("All Branches");
        branch.setStyle(fieldStyle);

        ComboBox<String> collegeType = new ComboBox<>();
        collegeType.getItems().addAll("All Types", "Government", "Government Aided", "Private");
        collegeType.setValue("All Types");
        collegeType.setStyle(fieldStyle);

        ComboBox<String> university = new ComboBox<>();
        university.getItems().addAll("All Universities", "SPPU", "Mumbai University", "Shivaji University", "Nagpur University");
        university.setValue("All Universities");
        university.setStyle(fieldStyle);

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

        Button searchButton = new Button("🔍 Search");
        searchButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        searchButton.setOnMouseEntered(e ->
            searchButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        searchButton.setOnMouseExited(e ->
            searchButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 38px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        filterCard.getChildren().addAll(filters, searchButton);

        // Table
        TableView<College> table = new TableView<>();
        table.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );

        TableColumn<College, String> codeColumn = new TableColumn<>("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        codeColumn.setPrefWidth(100);
        codeColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, String> nameColumn = new TableColumn<>("College Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(300);
        nameColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, String> districtColumn = new TableColumn<>("District");
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
        districtColumn.setPrefWidth(120);
        districtColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, String> branchColumn = new TableColumn<>("Branch");
        branchColumn.setCellValueFactory(new PropertyValueFactory<>("branch"));
        branchColumn.setPrefWidth(180);
        branchColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        TableColumn<College, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setPrefWidth(120);
        typeColumn.setStyle("-fx-text-fill: #E8EDF5; -fx-font-size: 13px;");

        table.getColumns().addAll(codeColumn, nameColumn, districtColumn, branchColumn, typeColumn);
        table.setPrefHeight(350);

        table.setRowFactory(tv -> new TableRow<College>() {
            @Override
            protected void updateItem(College item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    setStyle("-fx-background-color: rgba(10, 10, 15, 0.4);" +
                             "-fx-text-fill: #E8EDF5;" +
                             "-fx-border-color: rgba(74, 127, 181, 0.05);");
                }
            }
        });

        ObservableList<College> colleges = FXCollections.observableArrayList(
                new College("COEP01", "College of Engineering Pune", "Pune", "Computer Engineering", "Government"),
                new College("VIT01", "Vishwakarma Institute of Technology", "Pune", "Information Technology", "Private"),
                new College("PICT01", "Pune Institute of Computer Technology", "Pune", "Computer Engineering", "Private"),
                new College("WCE01", "Walchand College of Engineering", "Kolhapur", "Computer Engineering", "Government Aided"),
                new College("SPIT01", "Sardar Patel Institute of Technology", "Mumbai", "Information Technology", "Private")
        );

        table.setItems(colleges);

        // Keep search logic
        searchButton.setOnAction(e -> {
            ObservableList<College> filtered = FXCollections.observableArrayList();
            for (College c : colleges) {
                boolean districtMatch = district.getValue().equals("All Districts") || c.getDistrict().equals(district.getValue());
                boolean nameMatch = collegeName.getText().isBlank() || c.getName().toLowerCase().contains(collegeName.getText().toLowerCase());
                boolean branchMatch = branch.getValue().equals("All Branches") || c.getBranch().equals(branch.getValue());
                boolean typeMatch = collegeType.getValue().equals("All Types") || c.getType().equals(collegeType.getValue());
                boolean universityMatch = university.getValue().equals("All Universities") || university.getValue().equals("SPPU");
                if (districtMatch && nameMatch && branchMatch && typeMatch && universityMatch) {
                    filtered.add(c);
                }
            }
            table.setItems(filtered);
        });

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e ->
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e ->
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 120px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnAction(e -> Navigation.goTo(FinalMeritPage.getScene()));

        Button informationButton = new Button("ℹ️ View College Information");
        informationButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        informationButton.setOnMouseEntered(e ->
            informationButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        informationButton.setOnMouseExited(e ->
            informationButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
            )
        );
        informationButton.setOnAction(e -> {
            College selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                Navigation.goTo(CollegeInfoPage.getScene(selected));
            }
        });

        buttons.getChildren().addAll(backButton, informationButton);

        content.getChildren().addAll(title, subtitle, filterCard, table, buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #0A0A0F;" +
                "-fx-background-color: #0A0A0F;"
        );

        return new Scene(
                StudentLayout.create("College Search", scrollPane)
        );
    }

    private static Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 12px;"
        );
        return label;
    }

    public static class College {
        private final String code;
        private final String name;
        private final String district;
        private final String branch;
        private final String type;

        public College(String code, String name, String district, String branch, String type) {
            this.code = code;
            this.name = name;
            this.district = district;
            this.branch = branch;
            this.type = type;
        }

        public String getCode() { return code; }
        public String getName() { return name; }
        public String getDistrict() { return district; }
        public String getBranch() { return branch; }
        public String getType() { return type; }
    }
}