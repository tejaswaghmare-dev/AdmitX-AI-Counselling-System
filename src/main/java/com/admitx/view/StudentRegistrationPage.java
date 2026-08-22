package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StudentRegistrationPage {

    private static final String BLACK = "#050705";
    private static final String DARK = "#0C110B";

    private static final String LIME = "#B7FF00";
    private static final String LIME_DARK = "#8CC900";

    private static final String WHITE = "#F8FAF5";
    private static final String GREY = "#9BA69A";
    private static final String BORDER = "#283326";

    public static Scene getScene() {

        StackPane root = new StackPane();

        root.setStyle(
                "-fx-background-color:" +
                "linear-gradient(to bottom right," +
                BLACK + "," +
                DARK + ",#172016);"
        );

        VBox card = new VBox(14);

        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(440);

        card.setPadding(
                new Insets(
                        36,
                        40,
                        36,
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
                new Label("STUDENT PORTAL");

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
                new Label("CREATE ACCOUNT");

        title.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        29
                )
        );

        title.setTextFill(
                Color.web(WHITE)
        );

        Label subtitle =
                new Label(
                        "Create your AdmitX student account"
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
        // NAME
        // -------------------------------------------------

        TextField name =
                new TextField();

        name.setPromptText(
                "Enter Full Name"
        );

        VBox nameBox =
                createFieldBox(
                        "FULL NAME",
                        name
                );

        // -------------------------------------------------
        // EMAIL
        // -------------------------------------------------

        TextField email =
                new TextField();

        email.setPromptText(
                "Enter Email Address"
        );

        VBox emailBox =
                createFieldBox(
                        "EMAIL ADDRESS",
                        email
                );

        // -------------------------------------------------
        // MOBILE
        // -------------------------------------------------

        TextField mobile =
                new TextField();

        mobile.setPromptText(
                "Enter Mobile Number"
        );

        VBox mobileBox =
                createFieldBox(
                        "MOBILE NUMBER",
                        mobile
                );

        // -------------------------------------------------
        // PASSWORD
        // -------------------------------------------------

        PasswordField password =
                new PasswordField();

        password.setPromptText(
                "Create Password"
        );

        VBox passwordBox =
                createFieldBox(
                        "PASSWORD",
                        password
                );

        // -------------------------------------------------
        // CONFIRM PASSWORD
        // -------------------------------------------------

        PasswordField confirmPassword =
                new PasswordField();

        confirmPassword.setPromptText(
                "Confirm Password"
        );

        VBox confirmBox =
                createFieldBox(
                        "CONFIRM PASSWORD",
                        confirmPassword
                );

        // -------------------------------------------------
        // REGISTER
        // -------------------------------------------------

        Button registerButton =
                new Button(
                        "CREATE STUDENT ACCOUNT     →"
                );

        stylePrimaryButton(
                registerButton
        );

        registerButton.setOnMouseEntered(
                e -> registerButton.setStyle(
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

        registerButton.setOnMouseExited(
                e -> stylePrimaryButton(
                        registerButton
                )
        );

        registerButton.setOnAction(e -> {

            if (
                    name.getText().isBlank() ||
                    email.getText().isBlank() ||
                    mobile.getText().isBlank() ||
                    password.getText().isBlank() ||
                    confirmPassword.getText().isBlank()
            ) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Registration",
                        "Please fill all fields."
                );

                return;
            }

            if (
                    !password.getText()
                            .equals(
                                    confirmPassword.getText()
                            )
            ) {

                showMessage(
                        Alert.AlertType.WARNING,
                        "Registration",
                        "Passwords do not match."
                );

                return;
            }

            showMessage(
                    Alert.AlertType.INFORMATION,
                    "Registration Successful",
                    "Student account created successfully."
            );

            Navigation.goTo(
                    StudentLoginPage.getScene()
            );
        });

        // -------------------------------------------------
        // EXISTING ACCOUNT
        // -------------------------------------------------

        Label loginText =
                new Label(
                        "Already have an account?"
                );

        loginText.setTextFill(
                Color.web(GREY)
        );

        Button loginButton =
                new Button("LOGIN");

        loginButton.setCursor(
                Cursor.HAND
        );

        loginButton.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;"
        );

        loginButton.setOnAction(e ->
                Navigation.goTo(
                        StudentLoginPage.getScene()
                )
        );

        HBox loginBox =
                new HBox(
                        5,
                        loginText,
                        loginButton
                );

        loginBox.setAlignment(
                Pos.CENTER
        );

        // -------------------------------------------------
        // BACK
        // -------------------------------------------------

        Button backButton =
                new Button(
                        "← BACK TO HOME"
                );

        styleSecondaryButton(
                backButton
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
                e -> styleSecondaryButton(
                        backButton
                )
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        WelcomePage.getScene()
                )
        );

        card.getChildren().addAll(
                portal,
                title,
                subtitle,

                createSpacing(5),

                nameBox,
                emailBox,
                mobileBox,
                passwordBox,
                confirmBox,

                createSpacing(3),

                registerButton,
                loginBox,
                backButton
        );

        ScrollPane scrollPane =
                new ScrollPane(card);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setPannable(true);

        scrollPane.setStyle(
                "-fx-background: transparent;" +
                "-fx-background-color: transparent;"
        );

        StackPane wrapper =
                new StackPane(scrollPane);

        wrapper.setPadding(
                new Insets(30)
        );

        root.getChildren().add(
                wrapper
        );

        return new Scene(root);
    }

    private static VBox createFieldBox(
            String labelText,
            TextInputControl field
    ) {

        Label label =
                new Label(labelText);

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

        field.setMaxWidth(
                Double.MAX_VALUE
        );

        field.setPrefHeight(45);

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

        VBox box =
                new VBox(
                        6,
                        label,
                        field
                );

        return box;
    }

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

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(44);

        button.setCursor(
                Cursor.HAND
        );

        button.setStyle(
                "-fx-background-color:transparent;" +
                "-fx-text-fill:" + GREY + ";" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:9;" +
                "-fx-background-radius:9;" +
                "-fx-font-weight:bold;"
        );
    }

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

    private static void showMessage(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}