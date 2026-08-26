package com.admitx.view;

import com.admitx.model.Student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class FinalMeritPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Student data =
                Student.getInstance();

        Label title =
                new Label("Final Merit List");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Your final merit details are now available."
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

        Label published =
                new Label("●  FINAL MERIT PUBLISHED");

        published.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 20px;"
        );

        HBox status =
                new HBox(published);

        status.setAlignment(
                Pos.CENTER_LEFT
        );

        Label candidateSection =
                new Label("CANDIDATE");

        candidateSection.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox candidateCard =
                new VBox(
                        15,
                        candidateSection,
                        detail(
                                "Candidate",
                                value(data.getCandidateName())
                        ),
                        detail(
                                "Application ID",
                                "MHTCET20260001"
                        )
                );

        candidateCard.setPadding(
                new Insets(22)
        );

        candidateCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label meritSection =
                new Label("FINAL MERIT INFORMATION");

        meritSection.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox meritCard =
                new VBox(
                        15,
                        meritSection,

                        createRankCard(
                                "FINAL MERIT RANK",
                                "1498"
                        ),

                        detail(
                                "Category Rank",
                                "Open - 701"
                        ),

                        detail(
                                "Eligible CAP Rounds",
                                "CAP Round 1, 2 and 3"
                        )
                );

        meritCard.setPadding(
                new Insets(22)
        );

        meritCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label nextTitle =
                new Label("NEXT STEP");

        nextTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label nextDescription =
                new Label(
                        "Your final merit rank is ready. " +
                        "You can now search for colleges and " +
                        "continue with preference filling."
                );

        nextDescription.setWrapText(true);

        nextDescription.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox nextCard =
                new VBox(
                        8,
                        nextTitle,
                        nextDescription
                );

        nextCard.setPadding(
                new Insets(18)
        );

        nextCard.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 10px;"
        );

        Button dashboard =
                new Button("← Dashboard");

        dashboard.setPrefHeight(42);

        dashboard.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        Button collegeSearch =
                new Button("Proceed to College Search →");

        collegeSearch.setPrefHeight(42);

        collegeSearch.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        collegeSearch.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #101510;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        collegeSearch.setOnAction(e ->
                Navigation.goTo(
                        CollegeSearchPage.getScene()
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
                        dashboard,
                        spacer,
                        collegeSearch
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        status,
                        candidateCard,
                        meritCard,
                        nextCard,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        BorderPane page =
                new BorderPane();

        page.setCenter(content);

        page.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Final Merit List",
                        page
                )
        );
    }

    private static VBox detail(
            String label,
            String value
    ) {

        Label labelText =
                new Label(label);

        labelText.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueText =
                new Label(value);

        valueText.setWrapText(true);

        valueText.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        VBox box =
                new VBox(
                        5,
                        labelText,
                        valueText
                );

        box.setPadding(
                new Insets(12)
        );

        box.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        return box;
    }

    private static VBox createRankCard(
            String label,
            String rank
    ) {

        Label labelText =
                new Label(label);

        labelText.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label rankText =
                new Label(rank);

        rankText.setStyle(
                "-fx-font-size: 36px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox box =
                new VBox(
                        5,
                        labelText,
                        rankText
                );

        box.setPadding(
                new Insets(16)
        );

        box.setStyle(
                "-fx-background-color: #18220F;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 10px;"
        );

        return box;
    }

    private static String value(
            String text
    ) {

        if (
                text == null ||
                text.isBlank()
        ) {
            return "Not Available";
        }

        return text;
    }
}