package com.admitx.view;

import com.admitx.model.College;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CollegeInfoPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String FIELD = "#101610";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene(College college) {

        Label title = new Label("College Information");

        title.setStyle(
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        Label subtitle = new Label(
                "View complete information about the selected college."
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

        Label collegeName = new Label(
                safe(college.getCollegeName())
        );

        collegeName.setWrapText(true);

        collegeName.setStyle(
                "-fx-font-size:23px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );

        Label location = new Label(
                safe(college.getDistrict())
                + "  •  "
                + safe(college.getUniversity())
        );

        location.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox collegeHeader = new VBox(
                8,
                collegeName,
                location
        );

        collegeHeader.setPadding(
                new Insets(22)
        );

        collegeHeader.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        GridPane details = new GridPane();

        details.setHgap(35);
        details.setVgap(22);

        addDetail(
                details,
                "College ID",
                safe(college.getCollegeID()),
                0,
                0
        );

        addDetail(
                details,
                "College Name",
                safe(college.getCollegeName()),
                1,
                0
        );

        addDetail(
                details,
                "District",
                safe(college.getDistrict()),
                0,
                1
        );

        addDetail(
                details,
                "University",
                safe(college.getUniversity()),
                1,
                1
        );

        VBox detailsCard = new VBox(
                18,
                createSectionTitle("COLLEGE DETAILS"),
                details
        );

        detailsCard.setPadding(
                new Insets(22)
        );

        detailsCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        GridPane courseDetails = new GridPane();

        courseDetails.setHgap(35);
        courseDetails.setVgap(22);

        addDetail(
                courseDetails,
                "Available Branch",
                safe(college.getBranch()),
                0,
                0
        );

        addDetail(
                courseDetails,
                "Intake",
                String.valueOf(college.getIntake()),
                1,
                0
        );

        VBox courseCard = new VBox(
                18,
                createSectionTitle("COURSE & INTAKE"),
                courseDetails
        );

        courseCard.setPadding(
                new Insets(22)
        );

        courseCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        Label infoTitle =
                createSectionTitle("DATABASE INFORMATION");

        Label info = new Label(
                "This college information is provided from the "
                + "college records managed by the counsellor."
        );

        info.setWrapText(true);

        info.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox informationCard = new VBox(
                10,
                infoTitle,
                info
        );

        informationCard.setPadding(
                new Insets(22)
        );

        informationCard.setStyle(
                "-fx-background-color:" + FIELD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        Button backButton =
                new Button("← Back");

        backButton.setPrefWidth(120);
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
                        CollegeSearchPage.getScene()
                )
        );

        Button preferenceButton =
                new Button("Add to Preferences");

        preferenceButton.setPrefWidth(190);
        preferenceButton.setPrefHeight(42);

        preferenceButton.setStyle(
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#101510;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-cursor:hand;"
        );

        preferenceButton.setOnAction(e ->
                Navigation.goTo(
                        PreferenceFillingPage.getScene()
                )
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons = new HBox(
                15,
                backButton,
                spacer,
                preferenceButton
        );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content = new VBox(
                22,
                heading,
                collegeHeader,
                detailsCard,
                courseCard,
                informationCard,
                buttons
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

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background:" + BG + ";" +
                "-fx-background-color:" + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "College Information",
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

    private static void addDetail(
            GridPane grid,
            String labelText,
            String value,
            int column,
            int row
    ) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setWrapText(true);

        valueLabel.setStyle(
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + WHITE + ";"
        );

        VBox box = new VBox(
                6,
                label,
                valueLabel
        );

        box.setPrefWidth(300);

        grid.add(
                box,
                column,
                row
        );
    }

    private static String safe(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {
            return "Not Available";
        }

        return value;
    }
}