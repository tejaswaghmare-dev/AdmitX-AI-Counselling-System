package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CounsellorLoginPage {

    private static final String COUNSELLOR_ID = "YASH";
    private static final String COUNSELLOR_PASSWORD = "123";

    private static final String BLACK = "#050705";
    private static final String DARK = "#0C110B";
    private static final String LIME = "#B7FF00";
    private static final String LIME_DARK = "#8CC900";
    private static final String WHITE = "#F8FAF5";
    private static final String GREY = "#9BA69A";
    private static final String BORDER = "#283326";
    private static final String RED = "#FF5C5C";

    public static Scene getScene() {

        StackPane root =
                new StackPane();

        // -------------------------------------------------
        // BACKGROUND IMAGE
        // -------------------------------------------------

        Image backgroundImage = new Image(
                CounsellorLoginPage.class
                        .getResource(
                                "/assets/images/counsellor.png"
                        )
                        .toExternalForm()
        );

        ImageView backgroundView =
                new ImageView(backgroundImage);

        backgroundView.setPreserveRatio(false);

        backgroundView.fitWidthProperty()
                .bind(root.widthProperty());

        backgroundView.fitHeightProperty()
                .bind(root.heightProperty());

        // Dark overlay so the card remains clearly visible
        Region backgroundOverlay =
                new Region();

        backgroundOverlay.setStyle(
                "-fx-background-color: rgba(5,7,5,0.55);"
        );

        backgroundOverlay.prefWidthProperty()
                .bind(root.widthProperty());

        backgroundOverlay.prefHeightProperty()
                .bind(root.heightProperty());

        // -------------------------------------------------
        // LOGIN CARD
        // -------------------------------------------------

        VBox card =
                new VBox(16);

        card.setAlignment(
                Pos.CENTER
        );

        card.setMaxWidth(
                420
        );

        card.setPadding(
                new Insets(
                        40
                )
        );

        card.setStyle(
                "-fx-background-color:rgba(18,24,18,0.97);" +
                "-fx-background-radius:22;" +
                "-fx-border-color:rgba(183,255,0,0.20);" +
                "-fx-border-radius:22;" +
                "-fx-border-width:1;"
        );

        card.setEffect(
                new DropShadow(
                        45,
                        Color.color(
                                0,
                                0,
                                0,
                                0.80
                        )
                )
        );

        // -------------------------------------------------
        // HEADER
        // -------------------------------------------------

        Label portal =
                new Label(
                        "COUNSELLOR PORTAL"
                );

        portal.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        portal.setTextFill(
                Color.web(LIME)
        );

        Label title =
                new Label(
                        "COUNSELLOR LOGIN"
                );

        title.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(WHITE)
        );

        Label subtitle =
                new Label(
                        "MHT CET CAP Counselling Portal"
                );

        subtitle.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        // -------------------------------------------------
        // USERNAME
        // -------------------------------------------------

        TextField username =
                new TextField();

        username.setPromptText(
                "Enter Counsellor ID / Email"
        );

        // -------------------------------------------------
        // PASSWORD
        // -------------------------------------------------

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Enter Password"
        );

        styleField(
                username
        );

        styleField(
                password
        );

        VBox usernameBox =
                createFieldBox(
                        "COUNSELLOR ID / EMAIL",
                        username
                );

        VBox passwordBox =
                createFieldBox(
                        "PASSWORD",
                        password
                );

        // -------------------------------------------------
        // ERROR
        // -------------------------------------------------

        Label error =
                new Label();

        error.setWrapText(
                true
        );

        error.setStyle(
                "-fx-text-fill:" + RED + ";" +
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;"
        );

        // -------------------------------------------------
        // LOGIN BUTTON
        // -------------------------------------------------

        Button login =
                new Button(
                        "LOGIN TO COUNSELLOR PORTAL     →"
                );

        stylePrimaryButton(
                login
        );

        login.setOnMouseEntered(
                e -> login.setStyle(
                        "-fx-background-color:" +
                        LIME + ";" +
                        "-fx-text-fill:" +
                        BLACK + ";" +
                        "-fx-background-radius:10;" +
                        "-fx-font-weight:bold;" +
                        "-fx-font-size:13px;" +
                        "-fx-effect:dropshadow(" +
                        "gaussian," +
                        "rgba(183,255,0,0.40)," +
                        "20,0,0,4);"
                )
        );

        login.setOnMouseExited(
                e -> stylePrimaryButton(
                        login
                )
        );

        login.setOnAction(e -> {

            error.setText("");

            String enteredId =
                    username.getText().trim();

            String enteredPassword =
                    password.getText();

            if (
                    enteredId.isBlank() ||
                    enteredPassword.isBlank()
            ) {

                error.setText(
                        "Please enter Counsellor ID and Password."
                );

                return;
            }

            if (
                    enteredId.equals(COUNSELLOR_ID) &&
                    enteredPassword.equals(COUNSELLOR_PASSWORD)
            ) {

                Navigation.goTo(
                        CounsellorDashboardPage.getScene()
                );

            } else {

                error.setText(
                        "Invalid Counsellor ID or Password."
                );

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setTitle(
                        "Login Failed"
                );

                alert.setHeaderText(
                        "Invalid Counsellor Credentials"
                );

                alert.setContentText(
                        "Please enter a valid Counsellor ID and Password."
                );

                alert.showAndWait();
            }
        });

        // -------------------------------------------------
        // BACK
        // -------------------------------------------------

        Button back =
                new Button(
                        "← BACK TO WELCOME"
                );

        styleSecondaryButton(
                back
        );

        back.setOnMouseEntered(
                e -> back.setStyle(
                        "-fx-background-color:" +
                        "rgba(183,255,0,0.07);" +
                        "-fx-text-fill:" +
                        LIME + ";" +
                        "-fx-border-color:" +
                        LIME_DARK + ";" +
                        "-fx-border-radius:9;" +
                        "-fx-background-radius:9;" +
                        "-fx-font-size:12px;" +
                        "-fx-font-weight:bold;"
                )
        );

        back.setOnMouseExited(
                e -> styleSecondaryButton(
                        back
                )
        );

        back.setOnAction(e ->
                Navigation.goTo(
                        WelcomePage.getScene()
                )
        );

        // -------------------------------------------------
        // DEMO
        // -------------------------------------------------

        Label demo =
                new Label(
                        "Demo Counsellor Access"
                );

        demo.setTextFill(
                Color.web("#687266")
        );

        demo.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        // -------------------------------------------------
        // CARD CONTENT
        // -------------------------------------------------

        card.getChildren().addAll(
                portal,
                title,
                subtitle,
                createSpacing(8),
                usernameBox,
                passwordBox,
                error,
                createSpacing(3),
                login,
                back,
                createSpacing(3),
                demo
        );

        // -------------------------------------------------
        // ROOT LAYERS
        //
        // Background Image
        //       ↓
        // Dark Overlay
        //       ↓
        // Login Card
        // -------------------------------------------------

        root.getChildren().addAll(
                backgroundView,
                backgroundOverlay,
                card
        );

        // -------------------------------------------------
        // RETURN SCENE
        // -------------------------------------------------

        return new Scene(
                root,
                1000,
                650
        );
    }

    // -------------------------------------------------
    // FIELD BOX
    // -------------------------------------------------

    private static VBox createFieldBox(
            String labelText,
            TextInputControl field
    ) {

        Label label =
                new Label(
                        labelText
                );

        label.setTextFill(
                Color.web(GREY)
        );

        label.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        10
                )
        );

        VBox box =
                new VBox(
                        6,
                        label,
                        field
                );

        return box;
    }

    // -------------------------------------------------
    // FIELD STYLE
    // -------------------------------------------------

    private static void styleField(
            TextInputControl field
    ) {

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setPrefHeight(
                46
        );

        field.setStyle(
                "-fx-background-color:#0C110B;" +
                "-fx-text-fill:" +
                WHITE + ";" +
                "-fx-prompt-text-fill:#687266;" +
                "-fx-border-color:" +
                BORDER + ";" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;" +
                "-fx-padding:0 14 0 14;" +
                "-fx-font-size:13px;"
        );
    }

    // -------------------------------------------------
    // PRIMARY BUTTON
    // -------------------------------------------------

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                50
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color:" +
                LIME_DARK + ";" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:10;" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:13px;"
        );
    }

    // -------------------------------------------------
    // SECONDARY BUTTON
    // -------------------------------------------------

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                44
        );

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" +
                GREY + ";" +
                "-fx-border-color:" +
                BORDER + ";" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );
    }

    // -------------------------------------------------
    // SPACING
    // -------------------------------------------------

    private static Region createSpacing(
            double height
    ) {

        Region region =
                new Region();

        region.setPrefHeight(
                height
        );

        return region;
    }
}