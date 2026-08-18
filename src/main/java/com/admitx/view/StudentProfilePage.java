package com.admitx.view;

import com.admitx.view.Navigation;
import com.admitx.view.StudentLayout;

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

        // Main content with dark theme
        VBox content = new VBox(25);
        content.setPadding(new Insets(35, 40, 40, 40));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle(
                "-fx-background-color: #0A0A0F;"
        );

        // Title
        Label title = new Label("Student Profile");
        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        // Profile Details Card
        VBox detailsCard = new VBox(20);
        detailsCard.setPadding(new Insets(25, 30, 30, 30));
        detailsCard.setStyle(
                "-fx-background-color: rgba(26, 26, 46, 0.6);" +
                "-fx-background-radius: 16px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.15);" +
                "-fx-border-radius: 16px;" +
                "-fx-border-width: 1px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 20, 0, 0, 10);"
        );
        detailsCard.setMaxWidth(900);
        detailsCard.setMinWidth(600);

        // Details Grid
        GridPane details = new GridPane();
        details.setHgap(40);
        details.setVgap(20);
        details.setPadding(new Insets(10, 0, 5, 0));

        addDetail(details, "Full Name", "Yash Batte", 0, 0);
        addDetail(details, "Application ID", "MHTCET20260001", 2, 0);
        addDetail(details, "Email", "student@example.com", 0, 1);
        addDetail(details, "Mobile", "9876543210", 2, 1);
        addDetail(details, "Category", "Open", 0, 2);
        addDetail(details, "MHT CET Percentile", "95.50", 2, 2);

        // Password Section
        VBox passwordSection = new VBox(15);
        passwordSection.setPadding(new Insets(15, 0, 0, 0));

        Label passwordTitle = new Label("Change Password");
        passwordTitle.setStyle(
                "-fx-font-size: 16px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-family: 'Segoe UI';"
        );

        // Password fields with dark theme
        String fieldStyle = 
                "-fx-background-color: rgba(10, 10, 15, 0.6);" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-prompt-text-fill: #5A7D9E;" +
                "-fx-pref-height: 42px;" +
                "-fx-pref-width: 320px;" +
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
        confirmPassword.setPromptText("Confirm New Password");
        confirmPassword.setStyle(fieldStyle);

        // Password buttons
        VBox buttonBox = new VBox(12);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(5, 0, 0, 0));

        Button changePassword = new Button("Change Password");
        changePassword.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );
        changePassword.setOnMouseEntered(e -> 
            changePassword.setStyle(
                "-fx-background-color: #2A4A75;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(42, 74, 117, 0.6), 15, 0, 0, 6);" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );
        changePassword.setOnMouseExited(e -> 
            changePassword.setStyle(
                "-fx-background-color: #1E3A5F;" +
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 170px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(30, 58, 95, 0.4), 10, 0, 0, 4);" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
            )
        );

        Button dashboard = new Button("Dashboard");
        dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
        );
        dashboard.setOnMouseEntered(e -> 
            dashboard.setStyle(
                "-fx-background-color: rgba(74, 127, 181, 0.1);" +
                "-fx-text-fill: #A8C4DF;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.4);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );
        dashboard.setOnMouseExited(e -> 
            dashboard.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 14px;" +
                "-fx-pref-width: 150px;" +
                "-fx-pref-height: 42px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.2);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;" +
                "-fx-cursor: hand;"
            )
        );

        // Keep original action
        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        buttonBox.getChildren().addAll(changePassword, dashboard);

        // Assemble password section
        passwordSection.getChildren().addAll(
                passwordTitle,
                oldPassword,
                newPassword,
                confirmPassword,
                buttonBox
        );

        // Assemble details card
        detailsCard.getChildren().addAll(
                details,
                passwordSection
        );

        // Assemble main content
        content.getChildren().addAll(
                title,
                detailsCard
        );

        // Return scene with StudentLayout
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
                "-fx-text-fill: #8AA8C7;" +
                "-fx-font-size: 13px;" +
                "-fx-opacity: 0.8;"
        );

        Label valueLabel = new Label(value);
        valueLabel.setStyle(
                "-fx-text-fill: #E8EDF5;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: 500;"
        );

        VBox box = new VBox(6);
        box.getChildren().addAll(label, valueLabel);
        box.setPrefWidth(280);
        box.setPadding(new Insets(10, 14, 10, 14));
        box.setStyle(
                "-fx-background-color: rgba(10, 10, 15, 0.4);" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: rgba(74, 127, 181, 0.08);" +
                "-fx-border-radius: 8px;" +
                "-fx-border-width: 1px;"
        );

        grid.add(box, column, row);
    }
}