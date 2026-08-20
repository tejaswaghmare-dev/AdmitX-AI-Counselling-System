package com.admitx.view;

import com.admitx.controller.AuthController;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StudentLoginPage {

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

        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setBackground(background);

        // Icon
        Text iconText = new Text("🔐");
        iconText.setFont(javafx.scene.text.Font.font("Segoe UI Emoji", 52));

        Label title = new Label("Student Login");
        title.setStyle(
                "-fx-font-size: 32px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );
        DropShadow titleShadow = new DropShadow(20, Color.web("#4A7FB5", 0.2));
        title.setEffect(titleShadow);

        Label subtitle = new Label("Welcome back! Login to continue");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;"
        );

        // Form card
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

        HBox idBox = new HBox(10);
        idBox.setAlignment(Pos.CENTER);
        Label idIcon = new Label("🆔");
        idIcon.setStyle("-fx-font-size: 16px;");
        TextField applicationId = new TextField();
        applicationId.setPromptText("Email");
        applicationId.setStyle(fieldStyle);
        idBox.getChildren().addAll(idIcon, applicationId);

        HBox passBox = new HBox(10);
        passBox.setAlignment(Pos.CENTER);
        Label passIcon = new Label("🔒");
        passIcon.setStyle("-fx-font-size: 16px;");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setStyle(fieldStyle);
        passBox.getChildren().addAll(passIcon, password);

        Button loginButton = new Button("Login");
        loginButton.setStyle(
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
        loginButton.setOnMouseEntered(e ->
            loginButton.setStyle(
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
        loginButton.setOnMouseExited(e ->
            loginButton.setStyle(
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

        Button backButton = new Button("← Back");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        backButton.setOnMouseEntered(e ->
            backButton.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        backButton.setOnMouseExited(e ->
            backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );

        AuthController controller = new AuthController();
        loginButton.setOnAction(e ->{
                String name = applicationId.getText();
                String pass = password.getText();

                boolean flag = controller.signIn(name,pass);

                if(flag){
                        System.out.println("login successfully");
                        Navigation.goTo(StudentDashboardPage.getScene());
                }else{
                        System.out.println("log in failed");
                }      

        });
               
        

        backButton.setOnAction(e ->
                Navigation.goTo(WelcomePage.getScene())
        );

        // =========================
        // SIGN UP LINK
        // =========================

        HBox signupBox = new HBox(5);
        signupBox.setAlignment(Pos.CENTER);

        Label signupText = new Label("Don't have an account?");
        signupText.setStyle(
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;"
        );

        Button signupButton = new Button("Sign Up");
        signupButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #6FA8DC;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: transparent;" +
                "-fx-border-color: transparent;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 0;"
        );

        signupButton.setOnMouseEntered(e ->
                signupButton.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: #A8C4DF;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-underline: true;" +
                        "-fx-border-color: transparent;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 0;"
                )
        );

        signupButton.setOnMouseExited(e ->
                signupButton.setStyle(
                        "-fx-background-color: transparent;" +
                        "-fx-text-fill: #6FA8DC;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-border-color: transparent;" +
                        "-fx-cursor: hand;" +
                        "-fx-padding: 0;"
                )
        );

        // Go to Signup page
        signupButton.setOnAction(e ->
                Navigation.goTo(StudentSignupPage.getScene())
        );

        signupBox.getChildren().addAll(
                signupText,
                signupButton
        );

        formCard.getChildren().addAll(
                idBox,
                passBox,
                loginButton,
                signupBox,
                backButton
        );

        // Footer
        Label footer = new Label("© 2026 AdmitX · Secure Login");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;"
        );

        root.getChildren().addAll(
                iconText,
                title,
                subtitle,
                formCard,
                footer
        );

        Scene scene = new Scene(root, 900, 700);
        
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