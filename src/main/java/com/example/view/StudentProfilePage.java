package com.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class StudentProfilePage {

    public static Scene getScene() {

        Label title = new Label("Student Profile");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #0A0A0A;"
        );

        GridPane details = new GridPane();

        details.setHgap(30);
        details.setVgap(18);

        addDetail(
                details,
                "Full Name",
                "Yash Batte",
                0,
                0
        );

        addDetail(
                details,
                "Application ID",
                "MHTCET20260001",
                2,
                0
        );

        addDetail(
                details,
                "Email",
                "student@example.com",
                0,
                1
        );

        addDetail(
                details,
                "Mobile",
                "9876543210",
                2,
                1
        );

        addDetail(
                details,
                "Category",
                "Open",
                0,
                2
        );

        addDetail(
                details,
                "MHT CET Percentile",
                "95.50",
                2,
                2
        );

        Label passwordTitle =
                new Label("Change Password");

        passwordTitle.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        PasswordField oldPassword =
                new PasswordField();

        oldPassword.setPromptText(
                "Current Password"
        );

        PasswordField newPassword =
                new PasswordField();

        newPassword.setPromptText(
                "New Password"
        );

        PasswordField confirmPassword =
                new PasswordField();

        confirmPassword.setPromptText(
                "Confirm New Password"
        );

        Button changePassword =
                new Button("Change Password");

        changePassword.setStyle(
                "-fx-background-color: #65A30D;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 40px;"
        );

        Button dashboard =
                new Button("Dashboard");

        dashboard.setStyle(
                "-fx-background-color: #4D7C0F;" +
                "-fx-text-fill: white;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 40px;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        25,
                        title,
                        details,
                        passwordTitle,
                        oldPassword,
                        newPassword,
                        confirmPassword,
                        changePassword,
                        dashboard
                );

        content.setPadding(
                new Insets(30)
        );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setStyle(
                "-fx-background-color: #F7FEE7;"
        );

        return new Scene(
                StudentLayout.create(
                        "Student Profile",
                        content
                )
        );
    }

    private static void addDetail(
            GridPane grid,
            String name,
            String value,
            int column,
            int row
    ) {

        Label label = new Label(name);

        label.setStyle(
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #1A1A1A;"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-text-fill: #3F6212;"
        );

        VBox box = new VBox(
                5,
                label,
                valueLabel
        );

        box.setPrefWidth(280);

        grid.add(
                box,
                column,
                row
        );
    }
}