package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ApplicationStatusPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Application Status");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Track the current status of your MHT CET CAP application."
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

        Label applicationId =
                new Label(
                        "APPLICATION ID   MHTCET20260001"
                );

        applicationId.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label statusLabel =
                new Label("CURRENT STATUS");

        statusLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label status =
                new Label("SUBMITTED");

        status.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label description =
                new Label(
                        "Your application has been successfully submitted " +
                        "and is currently under verification."
                );

        description.setWrapText(true);

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox statusRow =
                new HBox(
                        15,
                        createStatusIndicator(),
                        new VBox(
                                5,
                                statusLabel,
                                status,
                                description
                        )
                );

        statusRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox statusCard =
                new VBox(
                        18,
                        applicationId,
                        statusRow
                );

        statusCard.setPadding(
                new Insets(22)
        );

        statusCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label progressTitle =
                new Label("APPLICATION PROGRESS");

        progressTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox timeline =
                new VBox(
                        0,

                        createTimelineItem(
                                "01",
                                "Application Draft",
                                "Application created",
                                true,
                                true
                        ),

                        createTimelineItem(
                                "02",
                                "Application Submitted",
                                "Application successfully submitted",
                                true,
                                true
                        ),

                        createTimelineItem(
                                "03",
                                "Under Verification",
                                "Documents are being verified",
                                true,
                                true
                        ),

                        createTimelineItem(
                                "04",
                                "Application Verified",
                                "Waiting for verification",
                                false,
                                true
                        ),

                        createTimelineItem(
                                "05",
                                "Provisional Merit List",
                                "Merit status will be published",
                                false,
                                false
                        ),

                        createTimelineItem(
                                "06",
                                "CAP Rounds",
                                "Seat allotment process",
                                false,
                                false
                        ),

                        createTimelineItem(
                                "07",
                                "Admission",
                                "Confirm your allotted seat",
                                false,
                                false
                        )
                );

        VBox progressCard =
                new VBox(
                        18,
                        progressTitle,
                        timeline
                );

        progressCard.setPadding(
                new Insets(22)
        );

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Button dashboardButton =
                new Button("←  Go to Dashboard");

        styleSecondaryButton(
                dashboardButton
        );

        dashboardButton.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        Button meritButton =
                new Button("View Provisional Merit List  →");

        stylePrimaryButton(
                meritButton
        );

        meritButton.setOnAction(e ->
                Navigation.goTo(
                        ProvisionalMeritPage.getScene()
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
                        dashboardButton,
                        spacer,
                        meritButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        Label note =
                new Label(
                        "ⓘ  Keep checking your dashboard for verification updates, " +
                        "merit list publication and CAP round announcements."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;" +
                "-fx-font-size: 12px;"
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        statusCard,
                        progressCard,
                        note,
                        buttons
                );

        content.setPadding(
                new Insets(5)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Application Status",
                        content
                )
        );
    }

    private static Region createStatusIndicator() {

        Label check =
                new Label("✓");

        check.setMinSize(
                55,
                55
        );

        check.setMaxSize(
                55,
                55
        );

        check.setAlignment(
                Pos.CENTER
        );

        check.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;"
        );

        return check;
    }

    private static VBox createTimelineItem(
            String number,
            String title,
            String description,
            boolean completed,
            boolean line
    ) {

        Label numberLabel =
                new Label(
                        completed ? "✓" : number
                );

        numberLabel.setMinSize(
                34,
                34
        );

        numberLabel.setMaxSize(
                34,
                34
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color: " +
                        (completed
                                ? LIME
                                : "#252D25") + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " +
                        (completed
                                ? "#0B100B"
                                : MUTED) + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " +
                        (completed
                                ? WHITE
                                : MUTED) + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox text =
                new VBox(
                        4,
                        titleLabel,
                        descriptionLabel
                );

        HBox row =
                new HBox(
                        14,
                        numberLabel,
                        text
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        8,
                        0,
                        8,
                        0
                )
        );

        VBox item =
                new VBox(
                        row
                );

        if (line) {

            Region connector =
                    new Region();

            connector.setPrefWidth(2);
            connector.setPrefHeight(18);

            connector.setTranslateX(16);

            connector.setStyle(
                    "-fx-background-color: " +
                            (completed
                                    ? LIME
                                    : BORDER) + ";"
            );

            item.getChildren().add(
                    connector
            );
        }

        return item;
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
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }
}