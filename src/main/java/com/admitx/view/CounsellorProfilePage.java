package com.admitx.view;



import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CounsellorProfilePage {

    public static Scene getScene() {

        VBox root = new VBox(25);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #0A0A0F;");

        Label title = new Label("👤 Counsellor Profile");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        Label subtitle = new Label("View and manage your profile");
        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-opacity: 0.7;" +
                "-fx-padding: 0 0 10 0;"
        );

        // Card
        VBox card = new VBox(15);
        card.setPadding(new Insets(30, 35, 35, 35));
        card.setMaxWidth(500);
        card.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );

        Label name = new Label("Name: Counsellor Admin");
        name.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-padding: 5 0 5 0;"
        );

        Label email = new Label("Email: counsellor@example.com");
        email.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-padding: 5 0 5 0;"
        );

        Label id = new Label("Counsellor ID: COUN001");
        id.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-padding: 5 0 5 0;"
        );

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: rgba(74, 127, 181, 0.1);");

        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 38px;" +
                "-fx-pref-width: 280px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-padding: 0 15 0 15;" +
                "-fx-font-size: 14px;";

        PasswordField oldPassword = new PasswordField();
        oldPassword.setPromptText("Current Password");
        oldPassword.setStyle(fieldStyle);

        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("New Password");
        newPassword.setStyle(fieldStyle);

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");
        confirmPassword.setStyle(fieldStyle);

        Button changePassword = createButton("🔒 Change Password", "#1E3A5F", "#E8EDF5");
        changePassword.setOnAction(e -> show("Password", "Password changed successfully."));

        Button logout = createButton("🚪 Logout", "#7F1D1D", "#FCA5A5");
        logout.setOnAction(e -> Navigation.goTo(CounsellorLoginPage.getScene()));

        Button dashboard = createButton("← Dashboard", "transparent", "#8AA8C7");
        dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
        );
        dashboard.setOnMouseEntered(e ->
            dashboard.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnMouseExited(e ->
            dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-pref-width: 200px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnAction(e -> Navigation.goTo(CounsellorDashboardPage.getScene()));

        card.getChildren().addAll(
                name,
                email,
                id,
                separator,
                oldPassword,
                newPassword,
                confirmPassword,
                changePassword
        );

        // Footer
        Label footer = new Label("© 2026 AdmitX · Counsellor Profile");
        footer.setStyle(
                "-fx-text-fill: #2A3D55;" +
                "-fx-font-size: 11px;" +
                "-fx-opacity: 0.5;" +
                "-fx-padding: 20 0 0 0;"
        );

        root.getChildren().addAll(title, subtitle, card, dashboard, logout, footer);

        return new Scene(root, 1000, 750);
    }

    private static Button createButton(String text, String bgColor, String textColor) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(42);
        button.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                "-fx-text-fill: " + textColor + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-font-size: 14px;"
        );
        if (!bgColor.equals("transparent")) {
            button.setOnMouseEntered(e ->
                button.setStyle(
                    "-fx-background-color: " + (bgColor.equals("#7F1D1D") ? "#991B1B" : "#2A4A75") + ";" +
                    "-fx-text-fill: " + textColor + ";" +
                    "-fx-background-radius: 8px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;" +
                    "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                    "-fx-border-radius: 8px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-font-size: 14px;"
                )
            );
            button.setOnMouseExited(e ->
                button.setStyle(
                    "-fx-background-color: " + bgColor + ";" +
                    "-fx-text-fill: " + textColor + ";" +
                    "-fx-background-radius: 8px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-cursor: hand;" +
                    "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                    "-fx-border-radius: 8px;" +
                    "-fx-border-width: 1px;" +
                    "-fx-font-size: 14px;"
                )
            );
        }
        return button;
    }

    private static void show(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
