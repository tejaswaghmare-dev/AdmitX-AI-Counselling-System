package com.admitx.view;

import com.admitx.controller.StudentAuthController;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StudentLoginPage {

    private static final String BLACK = "#050705";
    private static final String DARK = "#0C110B";
    private static final String CARD = "#171E16";
    private static final String LIME = "#B7FF00";
    private static final String LIME_DARK = "#8CC900";
    private static final String WHITE = "#F8FAF5";
    private static final String GREY = "#9BA69A";
    private static final String BORDER = "#283326";

    public static Scene getScene() {

        StackPane root = new StackPane();

        // -------------------------------------------------
        // BACKGROUND IMAGE
        // -------------------------------------------------

        Image backgroundImage = new Image(
                StudentLoginPage.class
                        .getResource("/assets/images/login.jpeg")
                        .toExternalForm()
        );

        ImageView backgroundView = new ImageView(backgroundImage);

        backgroundView.setPreserveRatio(false);
        backgroundView.fitWidthProperty().bind(root.widthProperty());
        backgroundView.fitHeightProperty().bind(root.heightProperty());

        // Dark overlay so login card remains clearly visible
        Region backgroundOverlay = new Region();

        backgroundOverlay.setStyle(
                "-fx-background-color: rgba(5,7,5,0.55);"
        );

        backgroundOverlay.prefWidthProperty().bind(root.widthProperty());
        backgroundOverlay.prefHeightProperty().bind(root.heightProperty());

        // -------------------------------------------------
        // LOGIN CARD
        // -------------------------------------------------

        VBox card = new VBox(16);

        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(420);

        card.setPadding(
                new Insets(40)
        );

        card.setStyle(
                "-fx-background-color: rgba(18,24,18,0.97);" +
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

        Label smallTitle =
                new Label("STUDENT PORTAL");

        smallTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        smallTitle.setTextFill(
                Color.web(LIME)
        );

        Label title =
                new Label("WELCOME BACK");

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
                        "Login to continue your admission journey"
                );

        subtitle.setTextFill(
                Color.web(GREY)
        );

        subtitle.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );

        // -------------------------------------------------
        // APPLICATION ID
        // -------------------------------------------------

        Label applicationLabel =
                createFieldLabel(
                        "APPLICATION ID / EMAIL"
                );

        TextField applicationId =
                new TextField();

        applicationId.setPromptText(
                "Enter Application ID or Email"
        );

        styleField(applicationId);

        // -------------------------------------------------
        // PASSWORD
        // -------------------------------------------------

        Label passwordLabel =
                createFieldLabel("PASSWORD");

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Enter Password"
        );

        styleField(password);

        // -------------------------------------------------
        // LOGIN BUTTON
        // -------------------------------------------------

        Button loginButton =
                new Button(
                        "LOGIN TO ADMITX     →"
                );

        stylePrimaryButton(
                loginButton
        );

        loginButton.setOnMouseEntered(
                e -> loginButton.setStyle(
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

        loginButton.setOnMouseExited(
                e -> stylePrimaryButton(
                        loginButton
                )
        );

        loginButton.setOnAction(e ->{

                StudentAuthController controller = new StudentAuthController();

                boolean flag = controller.signIn(applicationId.getText(),password.getText());

                if(flag){
                        System.out.println("sign in is done successfully");
                        Navigation.goTo(
                        StudentDashboardPage.getScene());            
                }else{
                        System.out.println("sign in failed");
                       
                }

        });

        // -------------------------------------------------
        // CREATE ACCOUNT
        // -------------------------------------------------

        Label accountText =
                new Label(
                        "Don't have an account?"
                );

        accountText.setTextFill(
                Color.web(GREY)
        );

        Button registerButton =
                new Button(
                        "CREATE ACCOUNT"
                );

        registerButton.setCursor(
                Cursor.HAND
        );

        registerButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-weight:bold;" +
                "-fx-font-size:12px;"
        );

        registerButton.setOnAction(e ->
                Navigation.goTo(
                        StudentRegistrationPage.getScene()
                )
        );

        HBox registerBox =
                new HBox(
                        5,
                        accountText,
                        registerButton
                );

        registerBox.setAlignment(
                Pos.CENTER
        );

        // -------------------------------------------------
        // BACK
        // -------------------------------------------------

        Button backButton =
                new Button(
                        "← BACK TO HOME"
                );

        backButton.setCursor(
                Cursor.HAND
        );

        backButton.setMaxWidth(
                Double.MAX_VALUE
        );

        backButton.setPrefHeight(44);

        backButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + GREY + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;" +
                "-fx-font-weight:bold;"
        );

        backButton.setOnMouseEntered(
                e -> backButton.setStyle(
                        "-fx-background-color:" +
                        "rgba(183,255,0,0.07);" +
                        "-fx-text-fill:" + LIME + ";" +
                        "-fx-border-color:" +
                        LIME_DARK + ";" +
                        "-fx-border-radius:9;" +
                        "-fx-background-radius:9;" +
                        "-fx-font-weight:bold;"
                )
        );

        backButton.setOnMouseExited(
                e -> backButton.setStyle(
                        "-fx-background-color:transparent;" +
                        "-fx-text-fill:" + GREY + ";" +
                        "-fx-border-color:" + BORDER + ";" +
                        "-fx-border-radius:9;" +
                        "-fx-background-radius:9;" +
                        "-fx-font-weight:bold;"
                )
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        WelcomePage.getScene()
                )
        );

        // -------------------------------------------------
        // FORM
        // -------------------------------------------------

        VBox applicationBox =
                new VBox(
                        6,
                        applicationLabel,
                        applicationId
                );

        VBox passwordBox =
                new VBox(
                        6,
                        passwordLabel,
                        password
                );

        card.getChildren().addAll(
                smallTitle,
                title,
                subtitle,
                createSpacing(8),
                applicationBox,
                passwordBox,
                createSpacing(4),
                loginButton,
                registerBox,
                backButton
        );

        // -------------------------------------------------
        // ADD EVERYTHING TO ROOT
        // Background -> Overlay -> Card
        // -------------------------------------------------

        root.getChildren().addAll(
                backgroundView,
                backgroundOverlay,
                card
        );

        StackPane.setAlignment(
                card,
                Pos.CENTER
        );

        // IMPORTANT: Scene is still returned
        return new Scene(root);
    }

    // -------------------------------------------------
    // FIELD LABEL
    // -------------------------------------------------

    private static Label createFieldLabel(
            String text
    ) {

        Label label =
                new Label(text);

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

        return label;
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

        field.setPrefHeight(46);

        field.setStyle(
                "-fx-background-color:#0C110B;" +
                "-fx-text-fill:" + WHITE + ";" +
                "-fx-prompt-text-fill:#687266;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;" +
                "-fx-padding:0 14 0 14;" +
                "-fx-font-size:13px;"
        );
    }

    // -------------------------------------------------
    // PRIMARY BUTTON STYLE
    // -------------------------------------------------

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(50);

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
    // SPACING
    // -------------------------------------------------

    private static Region createSpacing(
            double height
    ) {

        Region region =
                new Region();

        region.setPrefHeight(height);

        return region;
    }
}