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

public class CounselorSignup {

    private Scene counselorScene;

    public Scene getScenefromCounselorsignup() {

        // =====================================================
        // LEFT SIDE - IMAGE PANEL
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
        // ADMITX LOGO
        // =====================================================

        Label logo = new Label("AdmitX");

        logo.setStyle("""
                -fx-font-family: Arial;
                -fx-font-size: 30px;
                -fx-font-weight: bold;
                -fx-text-fill: #9C075C;
                """);

        StackPane.setAlignment(
                logo,
                Pos.TOP_LEFT
        );

        StackPane.setMargin(
                logo,
                new Insets(25)
        );


        // =====================================================
        // COUNSELOR IMAGE
        // =====================================================

        Image counselorImage = new Image(
                getClass().getResourceAsStream(
                        "/assets/images/counselorsignup.jpg"
                )
        );

        ImageView counselorView =
                new ImageView(counselorImage);

        /*
         * Original image = 3000 x 2000
         *
         * Display size = 600 x 400
         *
         * Ratio remains 3:2
         * So the image will NOT stretch.
         */
        counselorView.setFitWidth(600);
        counselorView.setFitHeight(400);
        counselorView.setPreserveRatio(true);
        counselorView.setSmooth(true);


        // =====================================================
        // IMAGE FRAME
        // =====================================================

        StackPane imageFrame =
                new StackPane(counselorView);

        imageFrame.setPrefWidth(620);
        imageFrame.setPrefHeight(430);

        imageFrame.setMaxWidth(620);
        imageFrame.setMaxHeight(430);

        imageFrame.setAlignment(Pos.CENTER);

        imageFrame.setStyle("""
                -fx-background-color: rgba(255,255,255,0.60);
                -fx-background-radius: 30;
                -fx-border-color: rgba(156,7,92,0.15);
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
        // LEFT SIDE TAGLINE
        // =====================================================

        Label tagline = new Label(
                "Empowering Better Counseling Decisions."
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


        // Add to image panel
        imagePanel.getChildren().addAll(
                imageFrame,
                logo,
                tagline
        );


        // =====================================================
        // RIGHT SIDE - FORM PANEL
        // =====================================================

        VBox formPanel = new VBox(20);

        formPanel.setAlignment(
                Pos.CENTER_LEFT
        );

        formPanel.setPadding(
                new Insets(35, 55, 35, 55)
        );

        formPanel.setStyle("""
                -fx-background-color: white;
                """);


        // =====================================================
        // TITLE
        // =====================================================

        Label title = new Label(
                "Create Counselor Account"
        );

        title.setStyle("""
                -fx-font-family: Arial;
                -fx-font-size: 30px;
                -fx-font-weight: bold;
                -fx-text-fill: #171717;
                """);


        Label subtitle = new Label(
                "Manage students, counseling rounds and seat allocation"
        );

        subtitle.setStyle("""
                -fx-font-size: 14px;
                -fx-text-fill: #6b7280;
                """);


        // =====================================================
        // EMAIL
        // =====================================================

        Label emailLabel =
                new Label("Email Address");

        emailLabel.setStyle("""
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-fill: #374151;
                """);

        TextField emailField =
                new TextField();

        emailField.setPromptText(
                "Enter your email"
        );

        emailField.setPrefHeight(45);

        emailField.setStyle("""
                -fx-background-color: #f9fafb;
                -fx-border-color: #d1d5db;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                -fx-padding: 0 14;
                -fx-font-size: 14px;
                """);

        VBox emailBox =
                new VBox(
                        6,
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

        passwordField.setPrefHeight(45);

        passwordField.setStyle("""
                -fx-background-color: #f9fafb;
                -fx-border-color: #d1d5db;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                -fx-padding: 0 14;
                -fx-font-size: 14px;
                """);

        VBox passwordBox =
                new VBox(
                        6,
                        passwordLabel,
                        passwordField
                );


        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        Label confirmLabel =
                new Label("Confirm Password");

        confirmLabel.setStyle("""
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-text-fill: #374151;
                """);

        PasswordField confirmField =
                new PasswordField();

        confirmField.setPromptText(
                "Confirm your password"
        );

        confirmField.setPrefHeight(45);

        confirmField.setStyle("""
                -fx-background-color: #f9fafb;
                -fx-border-color: #d1d5db;
                -fx-border-radius: 10;
                -fx-background-radius: 10;
                -fx-padding: 0 14;
                -fx-font-size: 14px;
                """);

        VBox confirmBox =
                new VBox(
                        6,
                        confirmLabel,
                        confirmField
                );


        // =====================================================
        // SIGN UP BUTTON
        // =====================================================

        Button signupButton =
                new Button("Sign Up");

        signupButton.setPrefHeight(48);

        signupButton.setMaxWidth(
                Double.MAX_VALUE
        );

        signupButton.setStyle("""
                -fx-background-color: #14B8A6;
                -fx-text-fill: white;
                -fx-font-size: 16px;
                -fx-font-weight: bold;
                -fx-background-radius: 10;
                -fx-cursor: hand;
                """);


        // =====================================================
        // ALREADY HAVE ACCOUNT
        // =====================================================

        Label accountLabel =
                new Label("Already have an account?");

        accountLabel.setStyle("""
                -fx-text-fill: #6b7280;
                -fx-font-size: 14px;
                """);

        Hyperlink signIn =
                new Hyperlink("Sign In");


        // navigation for sign in

        signIn.setOnAction(e->{
                CounselorLogin counselorLogin = new CounselorLogin();

                LandingPage.LandingPagestage.setScene(counselorLogin.getSceneFromCounselorLogin());
        });

        signIn.setStyle("""
                -fx-text-fill: #9C075C;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-border-color: transparent;
                """);

        HBox signInRow =
                new HBox(
                        5,
                        accountLabel,
                        signIn
                );

        signInRow.setAlignment(
                Pos.CENTER
        );


        // =====================================================
        // SIGN UP ACTION
        // =====================================================

        signupButton.setOnAction(e -> {

            String email =
                    emailField.getText();

            String password =
                    passwordField.getText();

            String confirmPassword =
                    confirmField.getText();

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
                    "Counselor account created successfully!"
            );
        });


        // =====================================================
        // FORM CONTENT
        // =====================================================

        // Region spacer1 = new Region();
        // VBox.setVgrow(
        //         spacer1,
        //         javafx.scene.layout.Priority.ALWAYS
        // );

        // Region spacer2 = new Region();
        // VBox.setVgrow(
        //         spacer2,
        //         javafx.scene.layout.Priority.ALWAYS
        // );

        formPanel.getChildren().addAll(
                title,
                subtitle,
                // spacer1,
                emailBox,
                passwordBox,
                confirmBox,
                // spacer2,
                signupButton,
                signInRow
        );


        // =====================================================
        // ROOT
        // =====================================================

        HBox root =
                new HBox(
                        imagePanel,
                        formPanel
                );


        /*
         * 55% IMAGE
         * 45% FORM
         */
        imagePanel.prefWidthProperty().bind(
                root.widthProperty().multiply(0.55)
        );

        formPanel.prefWidthProperty().bind(
                root.widthProperty().multiply(0.45)
        );


        // =====================================================
        // SCENE
        // =====================================================

        counselorScene =
                new Scene(
                        root,
                        1366,
                        700
                );

        return counselorScene;
    }
}