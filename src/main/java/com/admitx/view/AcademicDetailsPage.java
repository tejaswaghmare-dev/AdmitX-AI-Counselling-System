package com.admitx.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;
import com.example.model.ApplicationData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AcademicDetailsPage {

    public static Scene getScene() {

        ApplicationData data = ApplicationData.getInstance();

        Label title = new Label("Academic Details");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        TextField sscDetails = new TextField();
        sscDetails.setPromptText("SSC Percentage / Details");

        TextField hscDetails = new TextField();
        hscDetails.setPromptText("HSC Percentage / Details");

        TextField diplomaDetails = new TextField();
        diplomaDetails.setPromptText("Diploma Percentage / Details");

        TextField pcmMarks = new TextField();
        pcmMarks.setPromptText("PCM Marks");

        TextField cetPercentile = new TextField();
        cetPercentile.setPromptText("MHT CET Percentile");

        TextField jeePercentile = new TextField();
        jeePercentile.setPromptText("JEE Main Percentile");

        TextField yearOfPassing = new TextField();
        yearOfPassing.setPromptText("Year of Passing");

        GridPane form = new GridPane();

        form.setHgap(20);
        form.setVgap(20);
        form.setPadding(new Insets(20));

        addField(
                form,
                "SSC Details",
                sscDetails,
                0,
                0
        );

        addField(
                form,
                "HSC Details",
                hscDetails,
                2,
                0
        );

        addField(
                form,
                "Diploma Details",
                diplomaDetails,
                0,
                1
        );

        addField(
                form,
                "PCM Marks",
                pcmMarks,
                2,
                1
        );

        addField(
                form,
                "MHT CET Percentile",
                cetPercentile,
                0,
                2
        );

        addField(
                form,
                "JEE Main Percentile",
                jeePercentile,
                2,
                2
        );

        addField(
                form,
                "Year of Passing",
                yearOfPassing,
                0,
                3
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
                Navigation.goTo(AddressDetailsPage.getScene())
        );

        nextButton.setOnAction(e -> {

            data.setSscDetails(
                    sscDetails.getText()
            );

            data.setHscDetails(
                    hscDetails.getText()
            );

            data.setDiplomaDetails(
                    diplomaDetails.getText()
            );

            data.setPcmMarks(
                    pcmMarks.getText()
            );

            data.setCetPercentile(
                    cetPercentile.getText()
            );

            data.setJeePercentile(
                    jeePercentile.getText()
            );

            data.setYearOfPassing(
                    yearOfPassing.getText()
            );

            Navigation.goTo(
                    HomeUniversityPage.getScene()
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
                        "Academic Details",
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