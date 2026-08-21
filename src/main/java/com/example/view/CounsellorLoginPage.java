package com.example.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CounsellorLoginPage {
        private static final String COUNSELLOR_ID = "YASH";
        private static final String COUNSELLOR_PASSWORD = "123";
        
    public static Scene getScene() {
        

        Label title =
                new Label("Counsellor Login");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        Label subtitle =
                new Label(
                        "MHT CET CAP Counselling Portal"
                );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #4D7C0F;"
        );

        TextField username =
                new TextField();

        username.setMaxWidth(Double.MAX_VALUE);

        username.setPromptText(
                "Counsellor ID / Email"
        );

        PasswordField password =
                new PasswordField();

        password.setMaxWidth(Double.MAX_VALUE);

        password.setPromptText(
                "Password"
        );

        Label error =
                new Label();

        error.setStyle(
                "-fx-text-fill: #DC2626;"
        );

        Button login =
                new Button("Login");

        login.setPrefWidth(250);
        login.setPrefHeight(42);

        login.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 15px;"
        );


        login.setOnAction(e -> {

            if (username.getText().isBlank()
                    || password.getText().isBlank()) {

                error.setText(
                        "Please enter Counsellor ID and Password."
                );

                return;
            }
           

            if (username.getText().equals(COUNSELLOR_ID)
                && password.getText().equals(COUNSELLOR_PASSWORD)) {

                Navigation.goTo(
                        CounsellorDashboardPage.getScene()
                );

                } else {

                Alert alert = new Alert(
                        Alert.AlertType.ERROR
                );

                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid Counsellor Credentials");
                alert.setContentText(
                        "Please enter a valid Counsellor ID and Password."
                );

                alert.showAndWait();
                }

            
            
        });



        Button back =
                new Button("Back to Welcome");

        back.setPrefWidth(250);
        back.setPrefHeight(40);

        back.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;"
        );

        back.setOnAction(e -> {
                Navigation.goTo(WelcomePage.getScene());
        });

        VBox card =
                new VBox(
                        15,
                        title,
                        subtitle,
                        username,
                        password,
                        error,
                        login,
                        back
                );

        card.setAlignment(
                Pos.CENTER
        );

        card.setPadding(
                new Insets(35)
        );

        card.setMaxWidth(400);

        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #D9F99D;" +
                "-fx-border-radius: 12px;"
        );

        VBox root =
                new VBox(card);

        root.setAlignment(
                Pos.CENTER
        );

        root.setPadding(
                new Insets(30)
        );

        root.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(root, 1000, 650);
    }
}