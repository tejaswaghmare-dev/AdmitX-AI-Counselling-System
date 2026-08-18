package com.admitx.view;

import com.admitx.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class CounsellorLoginPage {

    public static Scene getScene() {

        // Dark gradient background
        BackgroundFill gradientFill = new BackgroundFill(
                new javafx.scene.paint.LinearGradient(
                        0, 0, 1, 1,
                        true,
                        javafx.scene.paint.CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(0, Color.web("#0A0A0F")),
                        new javafx.scene.paint.Stop(0.4, Color.web("#1A1A2E")),
                        new javafx.scene.paint.Stop(0.7, Color.web("#16213E")),
                        new javafx.scene.paint.Stop(1, Color.web("#0A0A0F"))
                ),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );

        Background background = new Background(gradientFill);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setBackground(background);

        // Icon
        Text iconText = new Text("👨‍🏫");
        iconText.setFont(javafx.scene.text.Font.font("Segoe UI Emoji", 56));

        Label title = new Label("Counsellor Login");
        title.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );
        DropShadow titleShadow = new DropShadow(20, Color.web("#4A7FB5", 0.2));
        title.setEffect(titleShadow);

        Label subtitle = new Label("MHT CET CAP Counselling Portal");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;"
        );

        // Card
        VBox card = new VBox(15);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(35, 40, 40, 40));
        card.setMaxWidth(400);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 42px;" +
                "-fx-pref-width: 280px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 14px;";

        TextField username = new TextField();
        username.setPromptText("Counsellor ID / Email");
        username.setStyle(fieldStyle);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setStyle(fieldStyle);

        Label error = new Label();
        error.setStyle("-fx-text-fill: #F87171; -fx-font-size: 13px;");

        Button login = new Button("Login");
        login.setPrefWidth(280);
        login.setPrefHeight(44);
        login.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.6), 15, 0, 0, 5);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
        );
        login.setOnMouseEntered(e ->
            login.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.8), 20, 0, 0, 8);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        login.setOnMouseExited(e ->
            login.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.6), 15, 0, 0, 5);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );

        // Keep original logic
        login.setOnAction(e -> {
            if (username.getText().isBlank() || password.getText().isBlank()) {
                error.setText("Please enter Counsellor ID and Password.");
                return;
            }
            Navigation.goTo(CounsellorDashboardPage.getScene());
        });

        Button back = new Button("← Back to Welcome");
        back.setPrefWidth(280);
        back.setPrefHeight(40);
        back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        back.setOnMouseEntered(e ->
            back.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnMouseExited(e ->
            back.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        back.setOnAction(e -> Navigation.goTo(WelcomePage.getScene()));

        card.getChildren().addAll(
                title,
                subtitle,
                username,
                password,
                error,
                login,
                back
        );

        root.getChildren().add(card);

        Scene scene = new Scene(root, 1000, 650);
        
        // Fade-in animation
        javafx.animation.FadeTransition ft = new javafx.animation.FadeTransition(
                javafx.util.Duration.millis(600), root
        );
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        return scene;
    }
}
