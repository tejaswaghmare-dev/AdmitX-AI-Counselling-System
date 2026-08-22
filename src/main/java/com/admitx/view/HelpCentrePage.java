package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class HelpCentrePage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Help Centre");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Find answers, contact information and guidance for the CAP counselling process."
                );

        subtitle.setWrapText(true);

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

        Label sectionTitle =
                new Label("HELP & SUPPORT");

        sectionTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        TitledPane faq =
                createPane(
                        "Frequently Asked Questions",
                        "Frequently asked questions about registration, documents, merit list and CAP rounds."
                );

        TitledPane counsellor =
                createPane(
                        "Counsellor Contact",
                        "Dummy Counsellor: 020-12345678\n"
                                + "Email: counsellor@example.com"
                );

        TitledPane guide =
                createPane(
                        "User Guide",
                        "Complete registration, application, document verification and option form."
                );

        TitledPane cap =
                createPane(
                        "CAP Process Guide",
                        "Registration → Application → Merit List → Option Form → CAP Rounds → Admission"
                );

        VBox help =
                new VBox(
                        10,
                        faq,
                        counsellor,
                        guide,
                        cap
                );

        VBox helpCard =
                new VBox(
                        14,
                        sectionTitle,
                        help
                );

        helpCard.setPadding(
                new Insets(22)
        );

        helpCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label note =
                new Label(
                        "If your issue is not covered here, contact the counsellor support team."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;"
        );

        Button dashboard =
                new Button("← Dashboard");

        dashboard.setPrefHeight(42);

        dashboard.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        dashboard.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        helpCard,
                        note,
                        dashboard
                );

        content.setPadding(
                new Insets(30)
        );

        content.setAlignment(
                Pos.TOP_LEFT
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Help Centre",
                        content
                )
        );
    }

    private static TitledPane createPane(
            String title,
            String text
    ) {

        Label content =
                new Label(text);

        content.setWrapText(true);

        content.setPadding(
                new Insets(14)
        );

        content.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 12px;"
        );

        TitledPane pane =
                new TitledPane(
                        title,
                        content
                );

        pane.setExpanded(false);

        pane.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;"
        );

        return pane;
    }
}