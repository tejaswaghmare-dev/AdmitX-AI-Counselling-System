package com.admitx.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ApplicationStatusPage {

    public static Scene getScene() {

        VBox content = new VBox();
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40));
        content.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("📋 Application Status");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("Track your application progress");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Card
        VBox card = new VBox(18);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(30, 35, 35, 35));
        card.setMaxWidth(650);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label applicationIdLabel = new Label("Application ID: MHTCET20260001");
        applicationIdLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #8AA8C7;"
        );

        Label statusTitle = new Label("Current Status");
        statusTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label status = new Label("✅ Submitted");
        status.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #4ADE80;" +
                "-fx-padding: 5 0 5 0;"
        );

        Label description = new Label(
                "Your application has been successfully submitted " +
                "and is currently under verification."
        );
        description.setWrapText(true);
        description.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #8AA8C7;"
        );

        Label stepsTitle = new Label("Application Progress");
        stepsTitle.setStyle(
                "-fx-font-size: 17px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';" +
                "-fx-padding: 10 0 5 0;"
        );

        Label steps = new Label(
                "✅ Draft\n" +
                "✅ Submitted\n" +
                "🔄 Under Verification\n" +
                "○ Verified\n" +
                "○ Merit List"
        );
        steps.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-line-spacing: 8px;"
        );

        Button dashboardButton = new Button("← Dashboard");
        dashboardButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
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
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
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
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboardButton.setOnAction(e -> Navigation.goTo(StudentDashboardPage.getScene()));

        Button meritButton = new Button("📊 View Provisional Merit List");
        meritButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        meritButton.setOnMouseEntered(e ->
            meritButton.setStyle(
                "-fx-background-color: #078A5C;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        meritButton.setOnMouseExited(e ->
            meritButton.setStyle(
                "-fx-background-color: #064E3B;" +
                "-fx-text-fill: #6EE7B7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 220px;" +
                "-fx-pref-height: 40px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(110, 231, 183, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        meritButton.setOnAction(e -> Navigation.goTo(ProvisionalMeritPage.getScene()));

        card.getChildren().addAll(
                applicationIdLabel,
                statusTitle,
                status,
                description,
                stepsTitle,
                steps,
                dashboardButton,
                meritButton
        );

        content.getChildren().addAll(title, subtitle, card);

        return new Scene(
                StudentLayout.create("Application Status", content)
        );
    }
}