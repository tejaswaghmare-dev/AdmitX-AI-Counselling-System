package com.admitx.view;

import com.admitx.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StudentRegistrationPage {

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

        // Main container
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40, 40, 40, 40));
        root.setBackground(background);

        // Back button (top-left)
        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 6px;" +
                "-fx-border-width: 1px;"
        );
        backButton.setOnMouseEntered(e -> 
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 6px;" +
                "-fx-border-width: 1px;"
            )
        );
        backButton.setOnMouseExited(e -> 
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 6px;" +
                "-fx-border-width: 1px;"
            )
        );
        
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(0, 0, 10, 0));
        topBar.getChildren().add(backButton);

        // Icon
        Text iconText = new Text("📝");
        iconText.setFont(Font.font("Segoe UI Emoji", 48));
        
        // Title
        Label title = new Label("Create Account");
        title.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );
        DropShadow titleShadow = new DropShadow(20, Color.web("#4A7FB5", 0.2));
        title.setEffect(titleShadow);

        Label subtitle = new Label("Join AdmitX and start your admission journey");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;"
        );

        // Registration form card
        VBox formCard = new VBox(14);
        formCard.setAlignment(Pos.CENTER);
        formCard.setPadding(new Insets(30, 40, 35, 40));
        formCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );
        formCard.setMaxWidth(420);
        formCard.setMinWidth(380);

        // Input field style
        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 44px;" +
                "-fx-pref-width: 320px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 14px;";

        // Name field with icon
        HBox nameBox = new HBox(10);
        nameBox.setAlignment(Pos.CENTER);
        Label nameIcon = new Label("👤");
        nameIcon.setStyle("-fx-font-size: 16px;");
        TextField name = new TextField();
        name.setPromptText("Full Name");
        name.setStyle(fieldStyle);
        nameBox.getChildren().addAll(nameIcon, name);

        // Email field with icon
        HBox emailBox = new HBox(10);
        emailBox.setAlignment(Pos.CENTER);
        Label emailIcon = new Label("📧");
        emailIcon.setStyle("-fx-font-size: 16px;");
        TextField email = new TextField();
        email.setPromptText("Email Address");
        email.setStyle(fieldStyle);
        emailBox.getChildren().addAll(emailIcon, email);

        // Mobile field with icon
        HBox mobileBox = new HBox(10);
        mobileBox.setAlignment(Pos.CENTER);
        Label mobileIcon = new Label("📱");
        mobileIcon.setStyle("-fx-font-size: 16px;");
        TextField mobile = new TextField();
        mobile.setPromptText("Mobile Number");
        mobile.setStyle(fieldStyle);
        mobileBox.getChildren().addAll(mobileIcon, mobile);

        // Password field with icon
        HBox passwordBox = new HBox(10);
        passwordBox.setAlignment(Pos.CENTER);
        Label passwordIcon = new Label("🔒");
        passwordIcon.setStyle("-fx-font-size: 16px;");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setStyle(fieldStyle);
        passwordBox.getChildren().addAll(passwordIcon, password);

        // Confirm Password field with icon
        HBox confirmPasswordBox = new HBox(10);
        confirmPasswordBox.setAlignment(Pos.CENTER);
        Label confirmIcon = new Label("✓");
        confirmIcon.setStyle("-fx-font-size: 16px; -fx-text-fill: #4A7FB5;");
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        confirmPassword.setStyle(fieldStyle);
        confirmPasswordBox.getChildren().addAll(confirmIcon, confirmPassword);

        // Register Button
        Button registerButton = new Button("Create Account");
        registerButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 48px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.6), 15, 0, 0, 5);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
        );
        registerButton.setOnMouseEntered(e -> 
            registerButton.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 48px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.8), 20, 0, 0, 8);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );
        registerButton.setOnMouseExited(e -> 
            registerButton.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 15px;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 48px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.6), 15, 0, 0, 5);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 10px;" +
                "-fx-border-width: 1px;"
            )
        );

        // Login link
        HBox loginLinkBox = new HBox(5);
        loginLinkBox.setAlignment(Pos.CENTER);
        Label haveAccount = new Label("Already have an account?");
        haveAccount.setStyle("-fx-text-fill: #8AA8C7; -fx-font-size: 13px;");
        
        Hyperlink loginLink = new Hyperlink("Sign In");
        loginLink.setStyle(
                "-fx-text-fill: #4A7FB5;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;"
        );
        loginLink.setOnMouseEntered(e -> 
            loginLink.setStyle(
                "-fx-text-fill: #6C8CBF;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: true;"
            )
        );
        loginLink.setOnMouseExited(e -> 
            loginLink.setStyle(
                "-fx-text-fill: #4A7FB5;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-underline: false;"
            )
        );
        
        loginLinkBox.getChildren().addAll(haveAccount, loginLink);

        // Assemble form
        formCard.getChildren().addAll(
                nameBox,
                emailBox,
                mobileBox,
                passwordBox,
                confirmPasswordBox,
                registerButton,
                loginLinkBox
        );

        // Footer
        Label footer = new Label("© 2026 AdmitX · Secure registration");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;"
        );

        // Assemble everything
        root.getChildren().addAll(
                topBar,
                iconText,
                title,
                subtitle,
                formCard,
                footer
        );
        VBox.setMargin(formCard, new Insets(10, 0, 15, 0));

        // Action handlers
        backButton.setOnAction(e -> Navigation.goTo(WelcomePage.getScene()));
        registerButton.setOnAction(e -> {
            // Registration logic here
            Navigation.goTo(StudentLoginPage.getScene());
        });
        loginLink.setOnAction(e -> Navigation.goTo(StudentLoginPage.getScene()));

        // Scene
        Scene scene = new Scene(root, 900, 750);
        
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