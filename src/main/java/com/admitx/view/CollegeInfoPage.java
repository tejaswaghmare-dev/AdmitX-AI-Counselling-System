package com.admitx.view;

import com.admitx.model.College;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CollegeInfoPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String FIELD = "#0F150F";
    private static final String BORDER = "#2F3B2F";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene(College college) {

        // =====================================================
        // PAGE HEADING
        // =====================================================

        Label title =
                new Label("College Information");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "View complete information about the selected college."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        // =====================================================
        // COLLEGE HEADER
        // =====================================================

        Label collegeName =
                new Label(
                        safe(college.getCollegeName())
                );

        collegeName.setWrapText(true);

        collegeName.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label location =
                new Label(
                        safe(college.getDistrict())
                                + "  •  "
                                + safe(college.getUniversity())
                );

        location.setWrapText(true);

        location.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox collegeHeader =
                new VBox(
                        8,
                        collegeName,
                        location
                );

        collegeHeader.setPadding(
                new Insets(22)
        );

        collegeHeader.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // =====================================================
        // COLLEGE DETAILS GRID
        // =====================================================

        GridPane details =
                new GridPane();

        details.setHgap(30);
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

        ColumnConstraints first =
                new ColumnConstraints();

        first.setPercentWidth(50);

        ColumnConstraints second =
                new ColumnConstraints();

        second.setPercentWidth(50);

        details.getColumnConstraints()
                .addAll(
                        first,
                        second
                );

        VBox detailsCard =
                new VBox(
                        18,
                        createSectionTitle(
                                "COLLEGE DETAILS"
                        ),
                        details
                );

        detailsCard.setPadding(
                new Insets(22)
        );

        detailsCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // =====================================================
        // COURSE DETAILS
        // =====================================================

        GridPane courseDetails =
                new GridPane();

        courseDetails.setHgap(30);
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
                String.valueOf(
                        college.getIntake()
                ),
                1,
                0
        );

        ColumnConstraints courseFirst =
                new ColumnConstraints();

        courseFirst.setPercentWidth(50);

        ColumnConstraints courseSecond =
                new ColumnConstraints();

        courseSecond.setPercentWidth(50);

        courseDetails
                .getColumnConstraints()
                .addAll(
                        courseFirst,
                        courseSecond
                );

        VBox courseCard =
                new VBox(
                        18,
                        createSectionTitle(
                                "COURSE & INTAKE"
                        ),
                        courseDetails
                );

        courseCard.setPadding(
                new Insets(22)
        );

        courseCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // =====================================================
        // DATABASE INFORMATION
        // =====================================================

        Label infoTitle =
                createSectionTitle(
                        "DATABASE INFORMATION"
                );

        Label info =
                new Label(
                        "This college information is provided from the college records managed by the counsellor."
                );

        info.setWrapText(true);

        info.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox informationCard =
                new VBox(
                        10,
                        infoTitle,
                        info
                );

        informationCard.setPadding(
                new Insets(22)
        );

        informationCard.setStyle(
                "-fx-background-color: " + FIELD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        Button backButton =
                new Button("←  Back");

        styleSecondaryButton(
                backButton
        );

        Button preferenceButton =
                new Button(
                        "Add to Preferences"
                );

        stylePrimaryButton(
                preferenceButton
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        CollegeSearchPage.getScene()
                )
        );

        preferenceButton.setOnAction(e ->
                Navigation.goTo(
                        PreferenceFillingPage.getScene()
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
                        15,
                        backButton,
                        spacer,
                        preferenceButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        collegeHeader,
                        detailsCard,
                        courseCard,
                        informationCard,
                        buttons
                );

        content.setPadding(
                new Insets(
                        10,
                        15,
                        30,
                        15
                )
        );

        content.setFillWidth(true);

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";" +
                "-fx-border-color: transparent;"
        );

        return new Scene(
                StudentLayout.create(
                        "College Information",
                        scrollPane
                )
        );
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    // =========================================================
    // DETAIL FIELD
    // =========================================================

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
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setWrapText(true);

        valueLabel.setMaxWidth(
                Double.MAX_VALUE
        );

        valueLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        VBox valueBox =
                new VBox(
                        7,
                        label,
                        valueLabel
                );

        valueBox.setPadding(
                new Insets(
                        14
                )
        );

        valueBox.setMaxWidth(
                Double.MAX_VALUE
        );

        valueBox.setStyle(
                "-fx-background-color: " + FIELD + ";" +
                "-fx-border-color: #273227;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
        );

        GridPane.setHgrow(
                valueBox,
                Priority.ALWAYS
        );

        GridPane.setFillWidth(
                valueBox,
                true
        );

        grid.add(
                valueBox,
                column,
                row
        );
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(44);

        button.setMinWidth(
                190
        );

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        String normal =
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;";

        String hover =
                "-fx-background-color: #D0FF4D;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;";

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        hover
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        normal
                )
        );
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(44);

        button.setMinWidth(
                120
        );

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        String normal =
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #3A493A;" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        String hover =
                "-fx-background-color: #202B20;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: " + LIME + ";" +
                "-fx-border-width: 1px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;";

        button.setStyle(
                normal
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        hover
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        normal
                )
        );
    }

    // =========================================================
    // SAFE VALUE
    // =========================================================

    private static String safe(
            String value
    ) {

        if (
                value == null
                        || value.isBlank()
        ) {

            return "Not Available";
        }

        return value;
    }
}