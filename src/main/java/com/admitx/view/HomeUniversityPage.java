package com.admitx.view;

import com.admitx.model.ApplicationData;

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

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("Home University & Eligibility");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Please provide your university and eligibility details");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Form Card
        VBox formCard = new VBox(20);
        formCard.setPadding(new Insets(25, 30, 30, 30));
        formCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        GridPane form = new GridPane();
        form.setHgap(40);
        form.setVgap(20);
        form.setPadding(new Insets(10, 0, 5, 0));

        String comboStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 300px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 10 0 10;" +
                "-fx-font-size: 14px;";

        ComboBox<String> state = new ComboBox<>();
        state.getItems().addAll("Maharashtra", "Gujarat", "Karnataka", "Madhya Pradesh", "Goa", "Other");
        state.setPromptText("Select State");
        state.setStyle(comboStyle);

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
        homeUniversity.setStyle(comboStyle);

        ComboBox<String> candidateType = new ComboBox<>();
        candidateType.getItems().addAll(
                "Maharashtra State Candidate",
                "All India Candidate",
                "Minority Candidate",
                "Other"
        );
        candidateType.setPromptText("Select Candidate Type");
        candidateType.setStyle(comboStyle);

        ComboBox<String> maharashtraType = new ComboBox<>();
        maharashtraType.getItems().addAll("Type A", "Type B", "Type C", "Type D", "Type E");
        maharashtraType.setPromptText("Select Maharashtra Type");
        maharashtraType.setStyle(comboStyle);

        ComboBox<String> domicileStatus = new ComboBox<>();
        domicileStatus.getItems().addAll(
                "Maharashtra Domicile",
                "Other State Domicile",
                "Not Applicable"
        );
        domicileStatus.setPromptText("Select Domicile Status");
        domicileStatus.setStyle(comboStyle);

        addField(form, "State", state, 0, 0);
        addField(form, "Home University", homeUniversity, 2, 0);
        addField(form, "Candidate Type", candidateType, 0, 1);
        addField(form, "Maharashtra Type", maharashtraType, 2, 1);
        addField(form, "Domicile Status", domicileStatus, 0, 2);

        formCard.getChildren().add(form);

        // Buttons
        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e ->
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e ->
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 140px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnAction(e -> Navigation.goTo(AcademicDetailsPage.getScene()));

        Button nextButton = new Button("Save & Continue →");
        nextButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        nextButton.setOnMouseEntered(e ->
            nextButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        nextButton.setOnMouseExited(e ->
            nextButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 160px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        // Keep original logic
        nextButton.setOnAction(e -> {
            data.setState(state.getValue());
            data.setHomeUniversity(homeUniversity.getValue());
            data.setCandidateType(candidateType.getValue());
            data.setMaharashtraType(maharashtraType.getValue());
            data.setDomicileStatus(domicileStatus.getValue());
            Navigation.goTo(ReservationDetailsPage.getScene());
        });

        buttons.getChildren().addAll(backButton, nextButton);

        content.getChildren().addAll(title, subtitle, formCard, buttons);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #0A0A0F;" +
                "-fx-background-color: #0A0A0F;"
        );

        return new Scene(
                StudentLayout.create("Home University & Eligibility", scrollPane)
        );
    }

    private static void addField(GridPane grid, String labelText, Control control, int column, int row) {
        Label label = new Label(labelText);
        label.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #8AA8C7;"
        );
        control.setPrefWidth(300);
        control.setPrefHeight(38);
        VBox box = new VBox(6, label, control);
        grid.add(box, column, row);
    }
}