package com.admitx.view;

import com.example.view.Navigation;
import com.example.view.StudentLayout;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class OptionConfirmationPage {

    public static Scene getScene() {

        ObservableList<PreferenceFillingPage.Preference> preferences = PreferenceFillingPage.getPreferences();

        VBox content = new VBox(20);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("Option Form Confirmation");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        // Card
        VBox card = new VBox(20);
        card.setPadding(new Insets(30, 35, 35, 35));
        card.setMaxWidth(700);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label success = new Label("✅ Option Form Successfully Locked");
        success.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4ADE80;"
        );

        Label message = new Label(
                "Your college and branch preferences have been successfully locked for CAP counselling."
        );
        message.setWrapText(true);
        message.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-alignment: CENTER;"
        );

        Label preferenceCount = new Label("📋 Total Preferences Locked: " + preferences.size());
        preferenceCount.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-padding: 10 0 5 0;"
        );

        // Preference List
        VBox preferenceList = new VBox(8);
        preferenceList.setPadding(new Insets(5, 0, 10, 0));

        for (PreferenceFillingPage.Preference preference : preferences) {
            Label item = new Label(
                    preference.getPreferenceNumber() + ". " +
                    preference.getCollege() + " - " +
                    preference.getBranch()
            );
            item.setMaxWidth(Double.MAX_VALUE);
            item.setPadding(new Insets(10, 15, 10, 15));
            item.setStyle(
                    "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                    "-fx-border-color: rgba(74, 127, 181, 0.1);" +
                    "-fx-border-radius: 6px;" +
                    "-fx-background-radius: 6px;" +
                    "-fx-text-fill: #E8EDF5;" +
                    "-fx-font-size: 14px;"
            );
            preferenceList.getChildren().add(item);
        }

        // Buttons
        VBox buttons = new VBox(12);
        buttons.setAlignment(Pos.CENTER);

        Button downloadButton = new Button("📥 Download Preference List");
        downloadButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        downloadButton.setOnMouseEntered(e ->
            downloadButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        downloadButton.setOnMouseExited(e ->
            downloadButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        downloadButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Preference List");
            alert.setHeaderText(null);
            alert.setContentText("Dummy preference list download completed.");
            alert.showAndWait();
        });

        Button capRoundButton = new Button("🔄 Continue to CAP Round 1");
        capRoundButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        capRoundButton.setOnMouseEntered(e ->
            capRoundButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        capRoundButton.setOnMouseExited(e ->
            capRoundButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-pref-width: 210px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        capRoundButton.setOnAction(e -> Navigation.goTo(CAPRound1Page.getScene()));

        Button dashboardButton = new Button("🏠 Go to Dashboard");
        dashboardButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        dashboardButton.setOnMouseEntered(e ->
            dashboardButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboardButton.setOnMouseExited(e ->
            dashboardButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboardButton.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        buttons.getChildren().addAll(downloadButton, capRoundButton, dashboardButton);

        card.getChildren().addAll(
                success,
                message,
                preferenceCount,
                preferenceList,
                buttons
        );

        content.getChildren().addAll(title, card);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: #0A0A0F;" +
                "-fx-background-color: #0A0A0F;"
        );

        return new Scene(
                StudentLayout.create("Option Form Confirmation", scrollPane)
        );
    }
}
