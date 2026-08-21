package com.admitx.view;

import com.admitx.view.Navigation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class CounsellorProfilePage {

    public static Scene getScene() {

        Label title =
                new Label("Counsellor Profile");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label name =
                new Label(
                        "Name: Counsellor Admin"
                );

        Label email =
                new Label(
                        "Email: counsellor@example.com"
                );

        Label id =
                new Label(
                        "Counsellor ID: COUN001"
                );

        name.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #1A1A1A;"
        );

        email.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #1A1A1A;"
        );

        id.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #1A1A1A;"
        );

        PasswordField oldPassword =
                new PasswordField();

        oldPassword.setMaxWidth(Double.MAX_VALUE);

        oldPassword.setPromptText(
                "Current Password"
        );

        PasswordField newPassword =
                new PasswordField();

        newPassword.setMaxWidth(Double.MAX_VALUE);

        newPassword.setPromptText(
                "New Password"
        );

        PasswordField confirmPassword =
                new PasswordField();

        confirmPassword.setMaxWidth(Double.MAX_VALUE);

        confirmPassword.setPromptText(
                "Confirm Password"
        );

        Button changePassword =
                button("Change Password");

        changePassword.setOnAction(e ->
                show(
                        "Password",
                        "Password changed successfully."
                )
        );

        Button logout =
                button("Logout");

        logout.setOnAction(e ->
                Navigation.goTo(
                        CounsellorLoginPage.getScene()
                )
        );

       

        VBox card =
                new VBox(
                        15,
                        name,
                        email,
                        id,
                        new Separator(),
                        oldPassword,
                        newPassword,
                        confirmPassword,
                        changePassword
                );

        card.setPadding(
                new Insets(30)
        );

        card.setMaxWidth(500);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: #D9F99D;"
        );

        VBox root =
                new VBox(
                        25,
                        title,
                        card,
                        
                        logout
                );

        root.setAlignment(
                Pos.TOP_CENTER
        );

        root.setPadding(
                new Insets(30)
        );

        root.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        BorderPane layout =
        CounsellorLayout.create(
                "Profile",
                root
        );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static Button button(
            String text
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(200);
        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: #0A0A0A;" +
                "-fx-text-fill: white;"
        );

        return button;
    }

    private static void show(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}