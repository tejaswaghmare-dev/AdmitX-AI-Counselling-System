package com.example.view;

import com.example.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StudentLoginPage {

    public static Scene getScene() {

        Label title = new Label("Student Login");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        TextField applicationId = new TextField();
        applicationId.setPromptText("Application ID / Email");
        applicationId.setMaxWidth(320);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(320);

        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        loginButton.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 42px;"
        );

        backButton.setStyle(
                "-fx-background-color: #3F6212;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 320px;" +
                "-fx-pref-height: 42px;"
        );

        loginButton.setOnAction(e ->
                Navigation.goTo(StudentDashboardPage.getScene())
        );

        backButton.setOnAction(e ->
                Navigation.goTo(WelcomePage.getScene())
        );

        VBox root = new VBox(
                18,
                title,
                applicationId,
                password,
                loginButton,
                backButton
        );

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));

        root.setStyle(
                "-fx-background-color: #0A0A0A;"
        );

        return new Scene(root);
    }
}