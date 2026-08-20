package com.admitx.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class StudentDashboardPage {

    public static Scene getScene() {

        // Main content with dark theme
        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_LEFT);
        content.setStyle("-fx-background-color: #0A0A0F;");

        // Welcome Header
        VBox headerBox = new VBox(5);
        Label title = new Label("Welcome, Student");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );
        DropShadow titleShadow = new DropShadow(20, Color.web("#4A7FB5", 0.2));
        title.setEffect(titleShadow);

        Label subtitle = new Label("Here's your admission journey overview");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;"
        );
        headerBox.getChildren().addAll(title, subtitle);

        // Status Card
        VBox statusCard = new VBox(15);
        statusCard.setPadding(new Insets(25, 30, 30, 30));
        statusCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );
        statusCard.setMaxWidth(500);

        Label statusTitle = new Label("📊 Application Status");
        statusTitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: rgba(74, 127, 181, 0.1);");

        Label status = new Label(
                "Profile Completion: 40%\n\n" +
                "Application Status: Draft\n\n" +
                "Document Status: Pending\n\n" +
                "Merit Status: Not Published\n\n" +
                "CAP Round Status: Not Started"
        );
        status.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-line-spacing: 4px;"
        );

        Button nextStep = new Button("Next Step →");
        nextStep.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
        );
        nextStep.setOnMouseEntered(e ->
            nextStep.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        nextStep.setOnMouseExited(e ->
            nextStep.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 180px;" +
                "-fx-pref-height: 44px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );

        // Keep original action
        nextStep.setOnAction(e ->
                Navigation.goTo(PersonalDetailsPage.getScene())
        );

        statusCard.getChildren().addAll(
                statusTitle,
                separator,
                status,
                nextStep
        );

        // Footer
        Label footer = new Label("© 2026 AdmitX · Student Dashboard");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;" +
                "-fx-padding: 10 0 0 0;"
        );

        content.getChildren().addAll(
                headerBox,
                statusCard,
                footer
        );

        // Create BorderPane from StudentLayout
        BorderPane layout = StudentLayout.create("Student Dashboard", content);
        
        // Wrap in Scene
        Scene scene = new Scene(layout);
        
        return scene;
    }
}