package com.example.view;


import com.example.model.ApplicationData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HomeUniversityPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        Label title = new Label("Home University & Eligibility");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        ComboBox<String> state = new ComboBox<>();
        state.getItems().addAll(
                "Maharashtra",
                "Gujarat",
                "Karnataka",
                "Madhya Pradesh",
                "Goa",
                "Other"
        );
        state.setPromptText("Select State");

        ComboBox<String> homeUniversity = new ComboBox<>();
        homeUniversity.getItems().addAll(
                "Savitribai Phule Pune University",
                "University of Mumbai",
                "Shivaji University",
                "Rashtrasant Tukadoji Maharaj Nagpur University",
                "Dr. Babasaheb Ambedkar Marathwada University",
                "Other"
        );
        homeUniversity.setPromptText("Select Home University");

        ComboBox<String> candidateType = new ComboBox<>();
        candidateType.getItems().addAll(
                "Maharashtra State Candidate",
                "All India Candidate",
                "Minority Candidate",
                "Other"
        );
        candidateType.setPromptText("Select Candidate Type");

        ComboBox<String> maharashtraType = new ComboBox<>();
        maharashtraType.getItems().addAll(
                "Type A",
                "Type B",
                "Type C",
                "Type D",
                "Type E"
        );
        maharashtraType.setPromptText("Select Maharashtra Type");

        ComboBox<String> domicileStatus = new ComboBox<>();
        domicileStatus.getItems().addAll(
                "Maharashtra Domicile",
                "Other State Domicile",
                "Not Applicable"
        );
        domicileStatus.setPromptText("Select Domicile Status");

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(20);
        form.setPadding(new Insets(20));

        addField(
                form,
                "State",
                state,
                0,
                0
        );

        addField(
                form,
                "Home University",
                homeUniversity,
                2,
                0
        );

        addField(
                form,
                "Candidate Type",
                candidateType,
                0,
                1
        );

        addField(
                form,
                "Maharashtra Type",
                maharashtraType,
                2,
                1
        );

        addField(
                form,
                "Domicile Status",
                domicileStatus,
                0,
                2
        );

        Button backButton = new Button("Back");
        Button nextButton = new Button("Save & Continue");

        backButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        nextButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 6px;"
        );

        backButton.setOnAction(e ->
                Navigation.goTo(AcademicDetailsPage.getScene())
        );

        nextButton.setOnAction(e -> {

            data.setState(
                    state.getValue()
            );

            data.setHomeUniversity(
                    homeUniversity.getValue()
            );

            data.setCandidateType(
                    candidateType.getValue()
            );

            data.setMaharashtraType(
                    maharashtraType.getValue()
            );

            data.setDomicileStatus(
                    domicileStatus.getValue()
            );

            Navigation.goTo(
                    ReservationDetailsPage.getScene()
            );
        });

        HBox buttons = new HBox(
                15,
                backButton,
                nextButton
        );

        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox content = new VBox(
                15,
                title,
                form,
                buttons
        );

        content.setPadding(new Insets(30));

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        ScrollPane scrollPane = new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setStyle(
                "-fx-background: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Home University & Eligibility",
                        scrollPane
                )
        );
    }

    private static void addField(
            GridPane grid,
            String labelText,
            Control control,
            int column,
            int row
    ) {

        Label label = new Label(labelText);

        label.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        control.setPrefWidth(300);
        control.setPrefHeight(38);

        VBox box = new VBox(
                6,
                label,
                control
        );

        grid.add(box, column, row);
    }
}