package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class CAPRound1Page {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#DC2626";

    public static Scene getScene() {

        Label title =
                new Label("CAP Round 1");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "View your Round 1 allotment and choose your preferred action."
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

        Label roundBadge =
                new Label("●  ALLOTMENT PUBLISHED");

        roundBadge.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 20px;"
        );

        Label allotmentTitle =
                createSectionTitle(
                        "ROUND 1 ALLOTMENT"
                );

        GridPane details =
                new GridPane();

        details.setHgap(18);
        details.setVgap(15);

        addDetail(
                details,
                "Application ID",
                "MHTCET20260001",
                0,
                0
        );

        addDetail(
                details,
                "Allotment Status",
                "Seat Allotted",
                1,
                0
        );

        addDetail(
                details,
                "College",
                "College of Engineering Pune",
                0,
                1
        );

        addDetail(
                details,
                "Branch",
                "Computer Engineering",
                1,
                1
        );

        addDetail(
                details,
                "Category",
                "Open",
                0,
                2
        );

        addDetail(
                details,
                "Allotted Preference",
                "Preference No. 1",
                1,
                2
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

        VBox resultCard =
                new VBox(
                        16,
                        allotmentTitle,
                        roundBadge,
                        details
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

        Label actionTitle =
                createSectionTitle(
                        "CHOOSE YOUR ACTION"
                );

        Label actionDescription =
                new Label(
                        "Choose carefully. Freeze accepts the current seat, " +
                        "Betterment keeps the seat while allowing you to participate " +
                        "in the next round, and Reject declines the allotment."
                );

        actionDescription.setWrapText(true);

        actionDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox freezeCard =
                createActionCard(
                        "FREEZE",
                        "Accept Current Seat",
                        "Confirm this allotment and proceed towards admission.",
                        LIME
                );

        VBox bettermentCard =
                createActionCard(
                        "BETTERMENT",
                        "Try for Higher Preference",
                        "Keep this seat while participating in the next CAP round.",
                        "#A3E635"
                );

        VBox rejectCard =
                createActionCard(
                        "REJECT",
                        "Decline Allotted Seat",
                        "Reject the current allotment and do not accept this seat.",
                        "#FF6B6B"
                );

        Button freezeButton =
                new Button("Freeze Seat");

        stylePrimaryButton(
                freezeButton
        );

        freezeButton.setOnAction(e -> {

            showMessage(
                    "Seat Frozen",
                    "Your allotted seat has been accepted."
            );

            Navigation.goTo(
                    Round1ConfirmationPage.getScene(
                            "Seat Accepted"
                    )
            );
        });

        freezeCard.getChildren()
                .add(freezeButton);

        Button bettermentButton =
                new Button("Request Betterment");

        styleSecondaryActionButton(
                bettermentButton
        );

        bettermentButton.setOnAction(e -> {

            showMessage(
                    "Betterment Requested",
                    "You have requested betterment for the next CAP round."
            );

            Navigation.goTo(
                    Round1ConfirmationPage.getScene(
                            "Betterment Requested"
                    )
            );
        });

        bettermentCard.getChildren()
                .add(bettermentButton);

        Button rejectButton =
                new Button("Reject Seat");

        styleDangerButton(
                rejectButton
        );

        rejectButton.setOnAction(e -> {

            showMessage(
                    "Seat Rejected",
                    "Your allotted seat has been rejected."
            );

            Navigation.goTo(
                    Round1ConfirmationPage.getScene(
                            "Seat Rejected"
                    )
            );
        });

        rejectCard.getChildren()
                .add(rejectButton);

        HBox actionCards =
                new HBox(
                        14,
                        freezeCard,
                        bettermentCard,
                        rejectCard
                );

        HBox.setHgrow(
                freezeCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                bettermentCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                rejectCard,
                Priority.ALWAYS
        );

        VBox actionCard =
                new VBox(
                        14,
                        actionTitle,
                        actionDescription,
                        actionCards
                );

        actionCard.setPadding(
                new Insets(22)
        );

        actionCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label note =
                new Label(
                        "Important: Your selected action will determine your participation "
                        + "in the next CAP round."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #211F0F;" +
                "-fx-text-fill: #D9E6C8;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #665F20;" +
                "-fx-border-radius: 8px;"
        );

        Button dashboardButton =
                new Button("← Dashboard");

        styleSecondaryButton(
                dashboardButton
        );

        dashboardButton.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        HBox bottomButtons =
                new HBox(
                        dashboardButton
                );

        bottomButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        resultCard,
                        actionCard,
                        note,
                        bottomButtons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "CAP Round 1",
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
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
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
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setWrapText(true);

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

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        GridPane.setFillWidth(
                box,
                true
        );

        grid.add(
                box,
                column,
                row
        );
    }

    private static VBox createActionCard(
            String tag,
            String title,
            String description,
            String accent
    ) {

        Label tagLabel =
                new Label(tag);

        tagLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + accent + ";"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setWrapText(true);

        titleLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox card =
                new VBox(
                        8,
                        tagLabel,
                        titleLabel,
                        descriptionLabel
                );

        card.setPadding(
                new Insets(18)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setMinHeight(
                180
        );

        card.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        return card;
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(40);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleSecondaryActionButton(
            Button button
    ) {

        button.setPrefHeight(40);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setStyle(
                "-fx-background-color: #25351A;" +
                "-fx-text-fill: #C7FF4D;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #486025;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleDangerButton(
            Button button
    ) {

        button.setPrefHeight(40);
        button.setMaxWidth(Double.MAX_VALUE);

        button.setStyle(
                "-fx-background-color: " + RED + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
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

    private static void showMessage(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}