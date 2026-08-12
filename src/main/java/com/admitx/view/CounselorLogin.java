package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CounselorLogin {

    private Scene counselorLoginScene;

    public Scene getSceneFromCounselorLogin() {

        // =====================================================
        // LEFT SIDE - LOGIN FORM
        // =====================================================

        VBox loginPanel = new VBox(18);

        loginPanel.setAlignment(Pos.CENTER_LEFT);

        loginPanel.setPadding(
                new Insets(45, 65, 45, 65)
        );

        loginPanel.setStyle("""
                -fx-background-color: white;
                """);


        // =====================================================
        // LOGO
        // =====================================================

        Label logo = new Label("AdmitX");

        logo.setStyle("""
                -fx-font-family: Arial;
                -fx-font-size: 30px;
                -fx-font-weight: bold;
                -fx-text-fill: #9C075C;
                """);


        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label(
                "Welcome Back, Counselor"
        );

        title.setStyle("""
                -fx-font-family: Arial;
                -fx-font-size: 30px;
                -fx-font-weight: bold;
                -fx-text-fill: #171717;
                """);


        Label subtitle = new Label(
                "Login to manage counseling and seat allocation"
        );

        subtitle.setStyle("""
                -fx-font-size: 14px;
                -fx-text-fill: #6b7280;
                """);


        // =====================================================
        // EMAIL / COUNSELOR ID
        // =====================================================

        Label emailLabel = new Label(
                "Email Address / Counselor ID"
        );

        emailLabel.setStyle("""
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-fill: #374151;
                """);

        TextField emailField = new TextField();

        emailField.setPromptText(
                "Enter email or counselor ID"
        );

        emailField.setPrefHeight(48);

        emailField.setStyle("""
                -fx-background-color: #f9fafb;
                -fx-border-color: #d1d5db;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                -fx-padding: 0 14;
                -fx-font-size: 14px;
                """);

        VBox emailBox = new VBox(
                7,
                emailLabel,
                emailField
        );


        // =====================================================
        // PASSWORD
        // =====================================================

        Label passwordLabel =
                new Label("Password");

        passwordLabel.setStyle("""
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-fill: #374151;
                """);

        PasswordField passwordField =
                new PasswordField();

        passwordField.setPromptText(
                "Enter your password"
        );

        passwordField.setPrefHeight(48);

        passwordField.setStyle("""
                -fx-background-color: #f9fafb;
                -fx-border-color: #d1d5db;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                -fx-padding: 0 14;
                -fx-font-size: 14px;
                """);

        VBox passwordBox = new VBox(
                7,
                passwordLabel,
                passwordField
        );


        // =====================================================
        // FORGOT PASSWORD
        // =====================================================

        Hyperlink forgotPassword =
                new Hyperlink("Forgot Password?");

        forgotPassword.setStyle("""
                -fx-text-fill: #9C075C;
                -fx-font-size: 13px;
                -fx-font-weight: bold;
                -fx-border-color: transparent;
                """);

        HBox forgotRow =
                new HBox(forgotPassword);

        forgotRow.setAlignment(
                Pos.CENTER_RIGHT
        );


        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        Button loginButton =
                new Button("Login");

        loginButton.setPrefHeight(50);

        loginButton.setMaxWidth(
                Double.MAX_VALUE
        );

        loginButton.setStyle("""
                -fx-background-color: #14B8A6;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-background-radius: 10;
                -fx-cursor: hand;
                """);


        // =====================================================
        // SIGN UP
        // =====================================================

        Label accountLabel =
                new Label("Don't have an account?");

        accountLabel.setStyle("""
                -fx-text-fill: #6b7280;
                -fx-font-size: 14px;
                """);

        Hyperlink signUp =
                new Hyperlink("Sign Up");

        signUp.setStyle("""
                -fx-text-fill: #9C075C;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-border-color: transparent;
                """);

        HBox signupRow =
                new HBox(
                        5,
                        accountLabel,
                        signUp
                );

        signupRow.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // LOGIN ACTION
        // =====================================================

        loginButton.setOnAction(e -> {

            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {

                System.out.println(
                        "Please enter email and password."
                );

                return;
            }

            System.out.println(
                    "Counselor login successful!"
            );
        });


        // =====================================================
        // SPACERS
        // =====================================================

        Region spacer1 = new Region();
        VBox.setVgrow(
                spacer1,
                javafx.scene.layout.Priority.ALWAYS
        );

        Region spacer2 = new Region();
        VBox.setVgrow(
                spacer2,
                javafx.scene.layout.Priority.ALWAYS
        );


        // =====================================================
        // ADD TO LOGIN PANEL
        // =====================================================

        loginPanel.getChildren().addAll(
                logo,
                title,
                subtitle,
                spacer1,
                emailBox,
                passwordBox,
                forgotRow,
                spacer2,
                loginButton,
                signupRow
        );


        // =====================================================
        // RIGHT SIDE - IMAGE PANEL
        // =====================================================

        StackPane imagePanel = new StackPane();

        imagePanel.setStyle("""
                -fx-background-color: linear-gradient(
                    to bottom right,
                    #f5e8f1,
                    #e8f8f6
                );
                """);


        // =====================================================
        // COUNSELOR IMAGE
        // =====================================================

        Image counselorImage = new Image(
                getClass().getResourceAsStream(
                        "/assets/images/counselorlogin.png"
                )
        );

        ImageView counselorView =
                new ImageView(counselorImage);

        counselorView.setFitWidth(447);
        counselorView.setFitHeight(447);

        counselorView.setPreserveRatio(true);
        counselorView.setSmooth(true);


        // =====================================================
        // IMAGE FRAME
        // =====================================================

        StackPane imageFrame =
                new StackPane(counselorView);

        imageFrame.setPrefWidth(500);
        imageFrame.setPrefHeight(500);

        imageFrame.setMaxWidth(500);
        imageFrame.setMaxHeight(500);

        imageFrame.setAlignment(
                Pos.CENTER
        );

        imageFrame.setStyle("""
                -fx-background-color:
                    rgba(255,255,255,0.55);

                -fx-background-radius: 30;

                -fx-border-color:
                    rgba(156,7,92,0.15);

                -fx-border-width: 2;

                -fx-border-radius: 30;

                -fx-effect: dropshadow(
                    three-pass-box,
                    rgba(0,0,0,0.12),
                    20,
                    0,
                    0,
                    8
                );
                """);


        // =====================================================
        // ADMITX LOGO ON IMAGE SIDE
        // =====================================================

        Label imageLogo = new Label("AdmitX");

        imageLogo.setStyle("""
                -fx-font-family: Arial;
                -fx-font-size: 28px;
                -fx-font-weight: bold;
                -fx-text-fill: #9C075C;
                """);

        StackPane.setAlignment(
                imageLogo,
                Pos.TOP_RIGHT
        );

        StackPane.setMargin(
                imageLogo,
                new Insets(25)
        );


        // =====================================================
        // TAGLINE
        // =====================================================

        Label tagline = new Label(
                "Smart Counseling. Better Outcomes."
        );

        tagline.setStyle("""
                -fx-font-family: Arial;
                -fx-font-size: 19px;
                -fx-font-weight: bold;
                -fx-text-fill: #374151;
                """);

        StackPane.setAlignment(
                tagline,
                Pos.BOTTOM_CENTER
        );

        StackPane.setMargin(
                tagline,
                new Insets(0, 0, 25, 0)
        );


        imagePanel.getChildren().addAll(
                imageFrame,
                imageLogo,
                tagline
        );


        // =====================================================
        // ROOT
        // =====================================================

        HBox root =
                new HBox(
                        loginPanel,
                        imagePanel
                );


        // 45% LOGIN
        loginPanel.prefWidthProperty().bind(
                root.widthProperty().multiply(0.45)
        );

        // 55% IMAGE
        imagePanel.prefWidthProperty().bind(
                root.widthProperty().multiply(0.55)
        );


        // =====================================================
        // SCENE
        // =====================================================

        counselorLoginScene =
                new Scene(
                        root,
                        1366,
                        700
                );

        return counselorLoginScene;
    }
}