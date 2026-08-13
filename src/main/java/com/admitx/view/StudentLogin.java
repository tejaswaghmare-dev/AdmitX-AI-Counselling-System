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
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class StudentLogin {
        private Scene studentloginScene;

        public Scene getScenefromStudentlogin(Runnable returntosignup){
                // =====================================================
                // LEFT SIDE - LOGIN FORM
                // =====================================================

                VBox loginPanel = new VBox(18);

                loginPanel.setPrefWidth(500);
                loginPanel.setPadding(
                        new Insets(60, 70, 60, 70)
                );

                loginPanel.setAlignment(Pos.CENTER_LEFT);

                loginPanel.setStyle("""
                        -fx-background-color: white;
                        """);


                // =====================================================
                // TITLE
                // =====================================================

                Label logo = new Label("Login Page");
                logo.setFont(
                        Font.font("sans-serif")
                );

                logo.setStyle("""
                        -fx-font-family: Arial;
                        -fx-font-size: 30px;
                        -fx-font-weight: bold;
                        -fx-text-fill: #9C075C;
                        """);


                Label title = new Label("Welcome Back,\nStudent");
                title.setFont(
                        Font.font("sans-serif")
                );

                title.setStyle("""
                        -fx-font-family: Arial;
                        -fx-font-size: 32px;
                        -fx-font-weight: bold;
                        -fx-text-fill: #171717;
                        """);


                Label subtitle = new Label(
                        "Login to continue your counseling journey"
                );
                subtitle.setFont(
                        Font.font("sans-serif")
                );

                subtitle.setStyle("""
                        -fx-font-size: 15px;
                        -fx-text-fill: #6b7280;
                        """);


                // =====================================================
                // EMAIL
                // =====================================================

                Label emailLabel = new Label("Email Address");
                emailLabel.setFont(
                        Font.font("sans-serif")
                );

                emailLabel.setStyle("""
                        -fx-font-size: 14px;
                        -fx-font-weight: bold;
                        -fx-text-fill: #374151;
                        """);

                TextField emailField = new TextField();

                emailField.setPromptText("Enter your email");

                emailField.setPrefHeight(50);

                emailField.setStyle("""
                        -fx-background-color: #f9fafb;
                        -fx-border-color: #d1d5db;
                        -fx-border-radius: 10;
                        -fx-background-radius: 10;
                        -fx-padding: 0 15;
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

                Label passwordLabel = new Label("Password");
                passwordLabel.setFont(
                        Font.font("sans-serif")
                );

                passwordLabel.setStyle("""
                        -fx-font-size: 14px;
                        -fx-font-weight: bold;
                        -fx-text-fill: #374151;
                        """);

                PasswordField passwordField = new PasswordField();

                passwordField.setPromptText("Enter your password");
                

                passwordField.setPrefHeight(50);

                passwordField.setStyle("""
                        -fx-background-color: #f9fafb;
                        -fx-border-color: #d1d5db;
                        -fx-border-radius: 10;
                        -fx-background-radius: 10;
                        -fx-padding: 0 15;
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
                forgotPassword.setFont(
                        Font.font("sans-serif")
                );

                forgotPassword.setStyle("""
                        -fx-text-fill: #eb4325;
                        -fx-font-size: 13px;
                        -fx-font-weight: bold;
                        -fx-border-color: transparent;
                        """);

                HBox forgotRow = new HBox(
                        forgotPassword
                );

                forgotRow.setAlignment(Pos.CENTER_RIGHT);


                // =====================================================
                // LOGIN BUTTON
                // =====================================================

                Button loginButton = new Button("Login");
                loginButton.setFont(
                        Font.font("sans-serif")
                );

                loginButton.setPrefWidth(360);
                loginButton.setPrefHeight(50);

                loginButton.setStyle("""
                        -fx-background-color: #c40d00;
                        -fx-text-fill: white;
                        -fx-font-size: 16px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 10;
                        -fx-cursor: hand;
                        """);

                loginButton.setOnMouseEntered(e ->
                        loginButton.setStyle("""
                                -fx-background-color: #c9480c;
                                -fx-text-fill: white;
                                -fx-font-size: 16px;
                                -fx-font-weight: bold;
                                -fx-background-radius: 10;
                                -fx-cursor: hand;
                                """)
                );

                loginButton.setOnMouseExited(e ->
                        loginButton.setStyle("""
                                -fx-background-color: #ff0800;
                                -fx-text-fill: white;
                                -fx-font-size: 16px;
                                -fx-font-weight: bold;
                                -fx-background-radius: 10;
                                -fx-cursor: hand;
                                """)
                );


                // =====================================================
                // SIGN UP LINK
                // =====================================================

                Label accountLabel =
                        new Label("Don't have an account?");

                accountLabel.setFont(
                        Font.font("sans-serif")
                );

                accountLabel.setStyle("""
                        -fx-text-fill: #6b7280;
                        -fx-font-size: 14px;
                        """);

                Hyperlink signUp =
                        new Hyperlink("Sign Up");

                // return to sign up page 

                signUp.setOnAction(e->{
                        returntosignup.run();
                });

                signUp.setFont(
                        Font.font("sans-serif")
                );

                signUp.setStyle("""
                        -fx-text-fill: #7c0000;
                        -fx-font-size: 14px;
                        -fx-font-weight: bold;
                        -fx-border-color: transparent;
                        """);

                HBox signupRow = new HBox(
                        5,
                        accountLabel,
                        signUp
                );

                signupRow.setAlignment(Pos.CENTER);


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
                        "Student login successful!"
                );

                // Later connect this to MySQL/Firebase.
                });


                // =====================================================
                // ADD TO LOGIN PANEL
                // =====================================================

                loginPanel.getChildren().addAll(
                        logo,
                        title,
                        subtitle,
                        new Region(),
                        emailBox,
                        passwordBox,
                        forgotRow,
                        new Region(),
                        loginButton,
                        signupRow
                );


                // =====================================================
                // RIGHT SIDE - LARGE IMAGE
                // =====================================================

                StackPane imagePanel = new StackPane();

                imagePanel.setPrefWidth(700);

                imagePanel.setStyle("""
                        -fx-background-color:
                        linear-gradient(
                        to bottom right,
                        #b80606,
                        #e8f1ff
                        );
                        """);


                // =====================================================
                // LARGE 1700 x 1200 IMAGE
                // =====================================================

                Image studentImage = new Image(
                        getClass().getResourceAsStream(
                                "/assets/images/studentsignin.png"
                        )
                );

                ImageView imageView =
                        new ImageView(studentImage);

                // Large display size
                imageView.setFitWidth(650);
                imageView.setFitHeight(550);

                // IMPORTANT:
                // Keeps original image proportions
                imageView.setPreserveRatio(true);

                imageView.setSmooth(true);


                // =====================================================
                // IMAGE CARD / FRAME
                // =====================================================

                StackPane imageFrame = new StackPane(
                        imageView
                );

                imageFrame.setPrefSize(
                        660,
                        580
                );

                imageFrame.setMaxSize(
                        660,
                        580
                );

                imageFrame.setAlignment(Pos.CENTER);

                imageFrame.setStyle("""
                        -fx-background-color:
                        rgba(255,255,255,0.55);
                        -fx-background-radius: 30;
                        -fx-border-radius: 30;
                        -fx-border-color:
                        rgba(156,7,92,0.15);
                        -fx-border-width: 2;
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
                // ADD IMAGE
                // =====================================================

                imagePanel.getChildren().add(
                        imageFrame
                );


                // =====================================================
                // FINAL ROOT
                // =====================================================

                HBox root = new HBox(
                        loginPanel,
                        imagePanel
                );
                root.setAlignment(Pos.CENTER);

                root.setStyle(
                        "-fx-background-color: white;"
                );


                // =====================================================
                // SCENE
                // =====================================================

                Scene scene = new Scene(
                        root,
                        1366,
                        700
                );
                studentloginScene =scene;

                return studentloginScene;
        }

}

