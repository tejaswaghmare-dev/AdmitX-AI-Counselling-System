package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Round1ConfirmationPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#FF6B6B";

    public static Scene getScene(String decision) {

        Label title =
                new Label("CAP Round 1 Confirmation");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Your CAP Round 1 decision has been recorded."
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

        boolean accepted =
                decision.equals("Seat Accepted");

        boolean betterment =
                decision.equals("Betterment Requested");

        String accent =
                accepted || betterment
                        ? LIME
                        : RED;

        String symbol =
                accepted
                        ? "✓"
                        : betterment
                        ? "↑"
                        : "×";

        Label icon =
                new Label(symbol);

        icon.setMinSize(
                58,
                58
        );

        icon.setMaxSize(
                58,
                58
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setStyle(
                "-fx-background-color: " + accent + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;"
        );

        Label result =
                new Label(decision);

        result.setStyle(
                "-fx-font-size: 23px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + accent + ";"
        );

        Label message;

        if (accepted) {

            message = new Label(
                    "You have accepted the allotted seat."
            );

        } else if (betterment) {

            message = new Label(
                    "You have requested betterment in the next CAP round."
            );

        } else {

            message = new Label(
                    "You have rejected the allotted seat."
            );
        }

        message.setWrapText(true);

        message.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox resultText =
                new VBox(
                        5,
                        result,
                        message
                );

        HBox resultHeader =
                new HBox(
                        16,
                        icon,
                        resultText
                );

        resultHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox resultCard =
                new VBox(
                        18,
                        resultHeader,
                        createDetail(
                                "College",
                                "College of Engineering Pune"
                        ),
                        createDetail(
                                "Branch",
                                "Computer Engineering"
                        ),
                        createDetail(
                                "Category",
                                "Open"
                        )
                );

        resultCard.setPadding(
                new Insets(22)
        );

        resultCard.setStyle(
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

        Label nextMessage =
                new Label(
                        accepted
                                ? "You can continue to the next CAP round or return to the dashboard."
                                : betterment
                                ? "You are eligible to participate in CAP Round 2 for betterment."
                                : "You can continue to CAP Round 2 if eligible."
                );

        nextMessage.setWrapText(true);

        nextMessage.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox nextCard =
                new VBox(
                        7,
                        nextTitle,
                        nextMessage
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

        styleSecondaryButton(
                dashboard
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        Button nextRound =
                new Button(
                        "Continue to CAP Round 2 →"
                );

        stylePrimaryButton(
                nextRound
        );

        nextRound.setOnAction(e ->
                Navigation.goTo(
                        CAPRound2Page.getScene()
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
                        nextRound
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        resultCard,
                        nextCard,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "CAP Round 1 Confirmation",
                        content
                )
        );
    }

    private static VBox createDetail(
            String labelText,
            String value
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

        valueLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        VBox box =
                new VBox(
                        5,
                        label,
                        valueLabel
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

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }
}