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


public class StudentSignup  {
        private Scene studentsignupScene;

        public Scene getScenefromStudentsignup(){

                // =====================================================
                // LEFT SIDE
                // =====================================================

                StackPane imageContainer = new StackPane();

                imageContainer.setPrefWidth(520);
                imageContainer.setMaxWidth(520);

                imageContainer.setStyle("""
                        -fx-background-color:
                        linear-gradient(to right, #20005a, #5f00db);
                        """);

                // AdmitX logo
                Label logo = new Label("AdmitX");
                
                logo.setFont(Font.font("sans-serif", 30));
                logo.setStyle("""
                        -fx-font-weight: bold;
                        -fx-text-fill: #9C075C;
                        """);

                StackPane.setAlignment(logo, Pos.TOP_LEFT);
                StackPane.setMargin(logo, new Insets(30));

                // =====================================================
                // 350 x 350 IMAGE
                // =====================================================

                Image image = new Image(
                        getClass().getResourceAsStream(
                                "/assets/images/StudentSignUp.jpg"
                        )
                );

                ImageView imageView = new ImageView(image);

                imageView.setFitWidth(350);
                imageView.setFitHeight(350);
                imageView.setPreserveRatio(true);

                // =====================================================
                // DECORATIVE BRACKET / FRAME
                // =====================================================

                VBox imageFrame = new VBox();

                imageFrame.setPrefSize(390, 390);
                imageFrame.setMaxSize(390, 390);

                imageFrame.setAlignment(Pos.CENTER);

                imageFrame.setStyle("""
                        -fx-background-color: rgba(255, 255, 255, 0.9);
                        -fx-background-radius: 30;
                        -fx-border-color: rgba(1, 38, 107, 0.2);
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

                imageFrame.getChildren().add(imageView);

                // Center image
                StackPane.setAlignment(imageFrame, Pos.CENTER);

                // =====================================================
                // LEFT SIDE TEXT
                // =====================================================

                Label leftTitle = new Label(
                
                );

                leftTitle.setFont(Font.font("sans-serif", 24));

                leftTitle.setStyle("""
                        -fx-font-weight: bold;
                        -fx-text-fill: #202020;
                        """);

                leftTitle.setAlignment(Pos.CENTER);

                StackPane.setAlignment(leftTitle, Pos.BOTTOM_CENTER);
                StackPane.setMargin(
                        leftTitle,
                        new Insets(0, 0, 45, 0)
                );

                // Add everything
                imageContainer.getChildren().addAll(
                        logo,
                        imageFrame,
                        leftTitle
                );


                // =====================================================
                // RIGHT SIDE
                // =====================================================

                VBox form = new VBox(18);

                form.setPrefWidth(500);
                form.setMaxWidth(500);

                form.setPadding(
                        new Insets(50, 70, 50, 70)
                );

                form.setAlignment(Pos.CENTER_LEFT);

                form.setStyle(
                        "-fx-background-color: white;"
                );


                // =====================================================
                // TITLE
                // =====================================================

                Label title = new Label("SignUp Page");

                title.setFont(
                        Font.font("sans-serif", 32)
                );

                title.setStyle("""
                        -fx-font-weight: bold;
                        -fx-text-fill: #171717;
                        """);


                Label subtitle = new Label(
                        "Sign up to start your counseling journey"
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

                emailField.setPrefHeight(48);

                emailField.setStyle("""
                        -fx-background-color: #f9fafb;
                        -fx-border-color: #d1d5db;
                        -fx-border-radius: 10;
                        -fx-background-radius: 10;
                        -fx-padding: 0 15;
                        -fx-font-size: 14px;
                        """);


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

                passwordField.setPrefHeight(48);

                passwordField.setStyle("""
                        -fx-background-color: #f9fafb;
                        -fx-border-color: #d1d5db;
                        -fx-border-radius: 10;
                        -fx-background-radius: 10;
                        -fx-padding: 0 15;
                        -fx-font-size: 14px;
                        """);


                // =====================================================
                // CONFIRM PASSWORD
                // =====================================================

                Label confirmLabel = new Label("Confirm Password");
                confirmLabel.setFont(
                        Font.font("sans-serif")
                );

                confirmLabel.setStyle("""
                        -fx-font-size: 14px;
                        -fx-font-weight: bold;
                        -fx-text-fill: #374151;
                        """);

                PasswordField confirmPasswordField = new PasswordField();

                confirmPasswordField.setPromptText(
                        "Confirm your password"
                );

                confirmPasswordField.setPrefHeight(48);

                confirmPasswordField.setStyle("""
                        -fx-background-color: #f9fafb;
                        -fx-border-color: #d1d5db;
                        -fx-border-radius: 10;
                        -fx-background-radius: 10;
                        -fx-padding: 0 15;
                        -fx-font-size: 14px;
                        """);


                // =====================================================
                // SIGN UP BUTTON
                // =====================================================

                Button signupButton = new Button("Sign Up");

                signupButton.setPrefWidth(360);
                signupButton.setPrefHeight(50);

                signupButton.setStyle("""
                        -fx-background-color: #150a3f;
                        -fx-text-fill: white;
                        -fx-font-size: 16px;
                        -fx-font-weight: bold;
                        -fx-background-radius: 10;
                        -fx-cursor: hand;
                        """);


                // =====================================================
                // BUTTON HOVER
                // =====================================================

                


                // =====================================================
                // ALREADY HAVE ACCOUNT
                // =====================================================

                Label accountLabel = new Label(
                        "Already have an account?"
                );
                accountLabel.setFont(
                        Font.font("sans-serif")
                );

                accountLabel.setStyle("""
                        -fx-text-fill: #6b7280;
                        -fx-font-size: 14px;
                        """);

                Hyperlink signIn = new Hyperlink("Sign In");
                signIn.setFont(
                        Font.font("sans-serif")
                );

                // navigation for sign in 
                signIn.setOnAction(e->{
                        StudentLogin studentLogin = new StudentLogin();

                        Runnable callbacktoreturnlogin = ()->{
                                LandingPage.LandingPagestage.setScene(studentsignupScene);
                        };

                        LandingPage.LandingPagestage.setScene(studentLogin.getScenefromStudentlogin(callbacktoreturnlogin));
                });
                

                signIn.setStyle("""
                        -fx-text-fill: #62328f;
                        -fx-font-size: 14px;
                        -fx-font-weight: bold;
                        """);

                HBox loginRow = new HBox(
                        5,
                        accountLabel,
                        signIn
                );

                loginRow.setAlignment(Pos.CENTER);


                // =====================================================
                // SIGN UP ACTION
                // =====================================================

                signupButton.setOnAction(e -> {

                String email = emailField.getText();
                String password = passwordField.getText();
                String confirmPassword =
                        confirmPasswordField.getText();

                if (email.isEmpty()
                        || password.isEmpty()
                        || confirmPassword.isEmpty()) {

                        System.out.println(
                                "Please fill all fields."
                        );

                        return;
                }

                if (!password.equals(confirmPassword)) {

                        System.out.println(
                                "Passwords do not match."
                        );

                        return;
                }

                System.out.println(
                        "Student account created successfully!"
                );

                // Later you can connect this to MySQL/Firebase.
                });


                // =====================================================
                // FORM LAYOUT
                // =====================================================

                VBox emailBox = new VBox(
                        7,
                        emailLabel,
                        emailField
                );

                VBox passwordBox = new VBox(
                        7,
                        passwordLabel,
                        passwordField
                );

                VBox confirmBox = new VBox(
                        7,
                        confirmLabel,
                        confirmPasswordField
                );

                form.getChildren().addAll(
                        title,
                        subtitle,
                        new Region(),
                        emailBox,
                        passwordBox,
                        confirmBox,
                        new Region(),
                        signupButton,
                        loginRow
                );


                // =====================================================
                // ROOT
                // =====================================================

                HBox root = new HBox(
                        imageContainer,
                        form
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
                studentsignupScene = scene;

                
                return studentsignupScene;
        }
}