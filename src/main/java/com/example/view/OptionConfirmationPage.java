package com.example.view;



import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class OptionConfirmationPage {

    public static Scene getScene() {

        ObservableList<
                PreferenceFillingPage.Preference
                > preferences =
                PreferenceFillingPage.getPreferences();

        Label title =
                new Label("Option Form Confirmation");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label success =
                new Label("✓ Option Form Successfully Locked");

        success.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #65A30D;"
        );

        Label message =
                new Label(
                        "Your college and branch preferences have been " +
                        "successfully locked for CAP counselling."
                );

        message.setWrapText(true);

        message.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #3F6212;"
        );

        Label preferenceCount =
                new Label(
                        "Total Preferences Locked: "
                                + preferences.size()
                );

        preferenceCount.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        VBox preferenceList =
                new VBox(10);

        for (
                PreferenceFillingPage.Preference preference :
                preferences
        ) {

            Label item =
                    new Label(
                            preference.getPreferenceNumber()
                                    + ". "
                                    + preference.getCollege()
                                    + " - "
                                    + preference.getBranch()
                    );

            item.setMaxWidth(Double.MAX_VALUE);

            item.setPadding(
                    new Insets(12)
            );

            item.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-border-color: #D9F99D;" +
                    "-fx-border-radius: 6px;" +
                    "-fx-background-radius: 6px;" +
                    "-fx-text-fill: #1A1A1A;"
            );

            preferenceList.getChildren()
                    .add(item);
        }

        Button downloadButton =
                new Button("Download Preference List");

        downloadButton.setStyle(
                "-fx-background-color: #3F6212;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 40px;"
        );

        downloadButton.setOnAction(e -> {

            javafx.scene.control.Alert alert =
                    new javafx.scene.control.Alert(
                            javafx.scene.control.Alert.AlertType.INFORMATION
                    );

            alert.setTitle("Preference List");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Dummy preference list download completed."
            );

            alert.showAndWait();
        });

        Button dashboardButton =
                new Button("Go to Dashboard");

        dashboardButton.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;"
        );

        dashboardButton.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        Button capRoundButton =
                new Button("Continue to CAP Round 1");

        capRoundButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 40px;"
        );

        capRoundButton.setOnAction(e ->
                Navigation.goTo(
                        CAPRound1Page.getScene()
                )
        );

        VBox buttons =
                new VBox(
                        12,
                        downloadButton,
                        capRoundButton,
                        dashboardButton
                );

        buttons.setAlignment(
                Pos.CENTER
        );

        VBox card =
                new VBox(
                        20,
                        success,
                        message,
                        preferenceCount,
                        preferenceList,
                        buttons
                );

        card.setPadding(
                new Insets(30)
        );

        card.setMaxWidth(700);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 10px;"
        );

        VBox content =
                new VBox(
                        20,
                        title,
                        card
                );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        return new Scene(
                StudentLayout.create(
                        "Option Form Confirmation",
                        scrollPane
                )
        );
    }
}