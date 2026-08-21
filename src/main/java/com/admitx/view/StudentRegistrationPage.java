package com.admitx.view;

import com.admitx.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class StudentRegistrationPage {

    public static Scene getScene() {

        Label title = new Label("Student Registration");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        TextField name = new TextField();
        name.setPromptText("Full Name");
        name.setMaxWidth(320);

        TextField email = new TextField();
        email.setPromptText("Email");
        email.setMaxWidth(320);

        TextField mobile = new TextField();
        mobile.setPromptText("Mobile Number");
        mobile.setMaxWidth(320);

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setMaxWidth(320);

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        confirmPassword.setMaxWidth(320);

        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        registerButton.setStyle(
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

        backButton.setOnAction(e ->
                Navigation.goTo(WelcomePage.getScene())
        );

        registerButton.setOnAction(e ->
                Navigation.goTo(StudentLoginPage.getScene())
        );

        VBox root = new VBox(
                14,
                title,
                name,
                email,
                mobile,
                password,
                confirmPassword,
                registerButton,
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
