package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class StudentDashboardPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#273327";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String GREEN = "#65A30D";

    public static Scene getScene() {

        Label welcome = new Label("Welcome back, Student");

        welcome.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Track your MHT CET CAP counselling progress from one place."
        );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading = new VBox(
                6,
                welcome,
                description
        );

        Label profileTitle = new Label("PROFILE COMPLETION");

        profileTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label profileValue = new Label("40%");

        profileValue.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Region profileBackground = new Region();

        profileBackground.setPrefHeight(7);

        profileBackground.setStyle(
                "-fx-background-color: #293329;" +
                "-fx-background-radius: 10px;"
        );

        Region profileProgress = new Region();

        profileProgress.setPrefHeight(7);
        profileProgress.setMaxWidth(100);

        profileProgress.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 10px;"
        );

        HBox progressBox = new HBox(profileBackground);
        progressBox.setMaxWidth(Double.MAX_VALUE);

        HBox.setHgrow(profileBackground, Priority.ALWAYS);

        StackPaneWrapper progressWrapper = new StackPaneWrapper(
                profileBackground,
                profileProgress
        );

        Label profileHint = new Label(
                "Complete your application to continue."
        );

        profileHint.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox profileCard = createCard(
                profileTitle,
                profileValue,
                progressWrapper,
                profileHint
        );

        Label nextTitle = new Label("NEXT STEP");

        nextTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label nextHeading = new Label(
                "Complete your Personal Details"
        );

        nextHeading.setStyle(
                "-fx-font-size: 19px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label nextDescription = new Label(
                "Add your personal information to continue your application."
        );

        nextDescription.setWrapText(true);

        nextDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Button continueButton = new Button(
                "Continue Application  →"
        );

        continueButton.setPrefHeight(40);

        continueButton.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 0 18 0 18;" +
                "-fx-cursor: hand;"
        );

        continueButton.setOnAction(e ->
                Navigation.goTo(PersonalDetailsPage.getScene())
        );

        VBox nextCard = new VBox(
                10,
                nextTitle,
                nextHeading,
                nextDescription,
                continueButton
        );

        nextCard.setPadding(new Insets(20));

        nextCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        GridPane statusGrid = new GridPane();

        statusGrid.setHgap(15);
        statusGrid.setVgap(15);

        statusGrid.add(
                createStatusCard(
                        "APPLICATION",
                        "Draft",
                        "Complete your application",
                        "✎"
                ),
                0,
                0
        );

        statusGrid.add(
                createStatusCard(
                        "DOCUMENTS",
                        "Pending",
                        "Documents need to be uploaded",
                        "▣"
                ),
                1,
                0
        );

        statusGrid.add(
                createStatusCard(
                        "MERIT STATUS",
                        "Not Published",
                        "Merit list will appear here",
                        "★"
                ),
                0,
                1
        );

        statusGrid.add(
                createStatusCard(
                        "CAP ROUND",
                        "Not Started",
                        "CAP rounds will appear here",
                        "◉"
                ),
                1,
                1
        );

        Label progressTitle = new Label("CAP COUNSELLING PROGRESS");

        progressTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        HBox step1 = createStep("01", "Registration", true);
        HBox step2 = createStep("02", "Application", true);
        HBox step3 = createStep("03", "Merit List", false);
        HBox step4 = createStep("04", "Seat Allotment", false);

        HBox capProgress = new HBox(
                25,
                step1,
                step2,
                step3,
                step4
        );

        capProgress.setAlignment(Pos.CENTER_LEFT);

        VBox progressCard = new VBox(
                15,
                progressTitle,
                capProgress
        );

        progressCard.setPadding(new Insets(20));

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        HBox topCards = new HBox(
                15,
                profileCard,
                nextCard
        );

        HBox.setHgrow(profileCard, Priority.ALWAYS);
        HBox.setHgrow(nextCard, Priority.ALWAYS);

        VBox content = new VBox(
                22,
                heading,
                topCards,
                statusGrid,
                progressCard
        );

        content.setPadding(new Insets(5));
        content.setFillWidth(true);

        BorderPane wrapper = new BorderPane();
        wrapper.setCenter(content);

        wrapper.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Student Dashboard",
                        wrapper
                )
        );
    }

    private static VBox createCard(javafx.scene.Node... nodes) {

        VBox card = new VBox(8, nodes);

        card.setPadding(new Insets(20));

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        return card;
    }

    private static VBox createStatusCard(
            String title,
            String value,
            String description,
            String icon
    ) {

        Label iconLabel = new Label(icon);

        iconLabel.setStyle(
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 18px;"
        );

        Label titleLabel = new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        HBox header = new HBox(
                10,
                iconLabel,
                titleLabel
        );

        header.setAlignment(Pos.CENTER_LEFT);

        Label valueLabel = new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        Label descriptionLabel = new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 11px;"
        );

        VBox card = new VBox(
                12,
                header,
                valueLabel,
                descriptionLabel
        );

        card.setPadding(new Insets(18));

        card.setPrefWidth(260);
        card.setMinHeight(135);

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        return card;
    }

    private static HBox createStep(
            String number,
            String text,
            boolean completed
    ) {

        Label numberLabel = new Label(
                completed ? "✓" : number
        );

        numberLabel.setMinSize(30, 30);
        numberLabel.setAlignment(Pos.CENTER);

        numberLabel.setStyle(
                "-fx-background-color: " +
                        (completed ? LIME : "#202820") + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " +
                        (completed ? "#0B100B" : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Label textLabel = new Label(text);

        textLabel.setStyle(
                "-fx-text-fill: " +
                        (completed ? WHITE : MUTED) + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        HBox step = new HBox(
                8,
                numberLabel,
                textLabel
        );

        step.setAlignment(Pos.CENTER_LEFT);

        return step;
    }

    private static class StackPaneWrapper extends javafx.scene.layout.StackPane {

        public StackPaneWrapper(
                Region background,
                Region progress
        ) {

            getChildren().addAll(
                    background,
                    progress
            );

            setAlignment(
                    progress,
                    Pos.CENTER_LEFT
            );

            setMaxWidth(Double.MAX_VALUE);
        }
    }
}