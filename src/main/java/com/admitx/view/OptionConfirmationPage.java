package com.admitx.view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class OptionConfirmationPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        ObservableList<PreferenceFillingPage.Preference> preferences =
                PreferenceFillingPage.getPreferences();

        Label title =
                new Label("Option Form Confirmation");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Your preference list has been locked successfully."
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

        Label check =
                new Label("✓");

        check.setMinSize(
                60,
                60
        );

        check.setMaxSize(
                60,
                60
        );

        check.setAlignment(
                Pos.CENTER
        );

        check.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;"
        );

        Label success =
                new Label(
                        "Option Form Successfully Locked"
                );

        success.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label message =
                new Label(
                        "Your college and branch preferences have been "
                        + "submitted for CAP counselling."
                );

        message.setWrapText(true);

        message.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox successText =
                new VBox(
                        5,
                        success,
                        message
                );

        HBox successRow =
                new HBox(
                        16,
                        check,
                        successText
                );

        successRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox successCard =
                new VBox(
                        successRow
                );

        successCard.setPadding(
                new Insets(22)
        );

        successCard.setStyle(
                "-fx-background-color: #18220F;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 12px;"
        );

        Label countTitle =
                new Label("LOCKED PREFERENCES");

        countTitle.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label preferenceCount =
                new Label(
                        preferences.size()
                        + " preferences locked"
                );

        preferenceCount.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox preferenceList =
                new VBox(10);

        for (
                PreferenceFillingPage.Preference preference :
                preferences
        ) {

            Label number =
                    new Label(
                            String.valueOf(
                                    preference.getPreferenceNumber()
                            )
                    );

            number.setMinSize(
                    32,
                    32
            );

            number.setAlignment(
                    Pos.CENTER
            );

            number.setStyle(
                    "-fx-background-color: " + LIME + ";" +
                    "-fx-background-radius: 50%;" +
                    "-fx-text-fill: #0B100B;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;"
            );

            Label college =
                    new Label(
                            preference.getCollege()
                    );

            college.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + WHITE + ";"
            );

            Label branch =
                    new Label(
                            preference.getBranch()
                    );

            branch.setStyle(
                    "-fx-font-size: 11px;" +
                    "-fx-text-fill: " + MUTED + ";"
            );

            VBox text =
                    new VBox(
                            3,
                            college,
                            branch
                    );

            HBox item =
                    new HBox(
                            14,
                            number,
                            text
                    );

            item.setAlignment(
                    Pos.CENTER_LEFT
            );

            item.setPadding(
                    new Insets(12)
            );

            item.setStyle(
                    "-fx-background-color: " + ROW + ";" +
                    "-fx-background-radius: 8px;" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 8px;"
            );

            preferenceList.getChildren()
                    .add(item);
        }

        VBox preferenceCard =
                new VBox(
                        12,
                        countTitle,
                        preferenceCount,
                        preferenceList
                );

        preferenceCard.setPadding(
                new Insets(22)
        );

        preferenceCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label note =
                new Label(
                        "Your locked preferences will now be considered during CAP seat allotment."
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

        Button downloadButton =
                new Button(
                        "Download Preference List"
                );

        styleSecondaryButton(
                downloadButton
        );

        downloadButton.setOnAction(e -> {

            Alert alert =
                    new Alert(
                            Alert.AlertType.INFORMATION
                    );

            alert.setTitle(
                    "Preference List"
            );

            alert.setHeaderText(
                    null
            );

            alert.setContentText(
                    "Dummy preference list download completed."
            );

            alert.showAndWait();
        });

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

        Button capRoundButton =
                new Button(
                        "Continue to CAP Round 1 →"
                );

        stylePrimaryButton(
                capRoundButton
        );

        capRoundButton.setOnAction(e ->
                Navigation.goTo(
                        CAPRound1Page.getScene()
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
                        downloadButton,
                        spacer,
                        capRoundButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        successCard,
                        preferenceCard,
                        note,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Option Form Confirmation",
                        scrollPane
                )
        );
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