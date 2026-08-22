package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class CounsellorDashboardPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Counsellor Dashboard");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Monitor students, verification activity and CAP counselling progress."
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

        GridPane stats =
                new GridPane();

        stats.setHgap(16);
        stats.setVgap(16);

        VBox totalStudents =
                createStatCard(
                        "TOTAL STUDENTS",
                        "1,250",
                        "Registered candidates"
                );

        VBox verifiedStudents =
                createStatCard(
                        "VERIFIED STUDENTS",
                        "980",
                        "78.4% verified"
                );

        VBox pendingVerification =
                createStatCard(
                        "PENDING VERIFICATION",
                        "270",
                        "Requires counsellor review"
                );

        VBox capStatus =
                createStatCard(
                        "CAP ROUND STATUS",
                        "Round 1",
                        "Currently active"
                );

        stats.add(totalStudents, 0, 0);
        stats.add(verifiedStudents, 1, 0);
        stats.add(pendingVerification, 2, 0);
        stats.add(capStatus, 3, 0);

        for (int i = 0; i < 4; i++) {

            ColumnConstraints column =
                    new ColumnConstraints();

            column.setPercentWidth(25);

            stats.getColumnConstraints()
                    .add(column);
        }

        Label activityTitle =
                createSectionTitle(
                        "COUNSELLING OVERVIEW"
                );

        VBox progressCard =
                createProgressCard();

        Label actionTitle =
                createSectionTitle(
                        "PENDING ACTIONS"
                );

        VBox pendingActions =
                new VBox(
                        10,
                        createActionRow(
                                "Student Verifications",
                                "12 applications pending"
                        ),
                        createActionRow(
                                "Document Review",
                                "8 document sets pending"
                        ),
                        createActionRow(
                                "Grievances",
                                "4 grievances require review"
                        ),
                        createActionRow(
                                "College Updates",
                                "3 records need attention"
                        )
                );

        VBox actionCard =
                new VBox(
                        14,
                        actionTitle,
                        pendingActions
                );

        actionCard.setPadding(
                new Insets(20)
        );

        actionCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label recentTitle =
                createSectionTitle(
                        "RECENT ACTIVITY"
                );

        VBox recentActivity =
                new VBox(
                        10,
                        createActivityRow(
                                "New student registered",
                                "Application MHTCET20260048"
                        ),
                        createActivityRow(
                                "Documents verified",
                                "Application MHTCET20260031"
                        ),
                        createActivityRow(
                                "Merit list updated",
                                "Provisional merit data published"
                        ),
                        createActivityRow(
                                "Option form locked",
                                "Student preference list submitted"
                        )
                );

        VBox recentCard =
                new VBox(
                        14,
                        recentTitle,
                        recentActivity
                );

        recentCard.setPadding(
                new Insets(20)
        );

        recentCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        HBox lowerSection =
                new HBox(
                        16,
                        progressCard,
                        actionCard,
                        recentCard
                );

        HBox.setHgrow(
                progressCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                actionCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                recentCard,
                Priority.ALWAYS
        );

        VBox root =
                new VBox(
                        24,
                        heading,
                        stats,
                        lowerSection
                );

        root.setPadding(
                new Insets(30)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(root);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "Dashboard",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static VBox createStatCard(
            String title,
            String value,
            String description
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
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
                        7,
                        titleLabel,
                        valueLabel,
                        descriptionLabel
                );

        card.setPadding(
                new Insets(18)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        return card;
    }

    private static VBox createProgressCard() {

        Label title =
                createSectionTitle(
                        "CAP ROUND ACTIVITY"
                );

        Label round =
                new Label("Round 1");

        round.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label percentage =
                new Label("82%");

        percentage.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Region track =
                new Region();

        track.setPrefHeight(8);

        track.setStyle(
                "-fx-background-color: #263026;" +
                "-fx-background-radius: 10px;"
        );

        Region progress =
                new Region();

        progress.setPrefHeight(8);
        progress.setPrefWidth(230);

        progress.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 10px;"
        );

        StackPane progressBar =
                new StackPane(
                        track,
                        progress
                );

        progressBar.setAlignment(
                Pos.CENTER_LEFT
        );

        Label applications =
                new Label(
                        "Applications: 1,250"
                );

        Label allotted =
                new Label(
                        "Seats Allotted: 840"
                );

        Label pending =
                new Label(
                        "Pending: 410"
                );

        applications.setStyle(
                "-fx-text-fill: " + MUTED + ";"
        );

        allotted.setStyle(
                "-fx-text-fill: " + MUTED + ";"
        );

        pending.setStyle(
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox card =
                new VBox(
                        12,
                        title,
                        round,
                        percentage,
                        progressBar,
                        applications,
                        allotted,
                        pending
                );

        card.setPadding(
                new Insets(20)
        );

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        return card;
    }

    private static HBox createActionRow(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        descriptionLabel
                );

        Label dot =
                new Label("●");

        dot.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 10px;"
        );

        HBox row =
                new HBox(
                        10,
                        dot,
                        text
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(10)
        );

        row.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 8px;"
        );

        return row;
    }

    private static HBox createActivityRow(
            String title,
            String description
    ) {

        Label icon =
                new Label("✓");

        icon.setMinSize(
                28,
                28
        );

        icon.setAlignment(
                Pos.CENTER
        );

        icon.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-weight: bold;"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        descriptionLabel
                );

        HBox row =
                new HBox(
                        10,
                        icon,
                        text
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(8)
        );

        return row;
    }

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }
}