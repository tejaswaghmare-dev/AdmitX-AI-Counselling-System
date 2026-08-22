package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.*;

public class StudentProfilePage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Student Profile");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "View your account information and manage your password."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        Label avatar =
                new Label("YB");

        avatar.setMinSize(
                64,
                64
        );

        avatar.setMaxSize(
                64,
                64
        );

        avatar.setAlignment(
                Pos.CENTER
        );

        avatar.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        Label studentName =
                new Label("Yash Batte");

        studentName.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label applicationId =
                new Label("MHTCET20260001");

        applicationId.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox identity =
                new VBox(
                        4,
                        studentName,
                        applicationId
                );

        HBox profileHeader =
                new HBox(
                        16,
                        avatar,
                        identity
                );

        profileHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox profileCard =
                new VBox(
                        18,
                        createSectionTitle("PROFILE INFORMATION"),
                        profileHeader
                );

        GridPane details =
                new GridPane();

        details.setHgap(18);
        details.setVgap(15);

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
                1,
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
                1,
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
                1,
                2
        );

        ColumnConstraints first =
                new ColumnConstraints();

        first.setPercentWidth(50);

        ColumnConstraints second =
                new ColumnConstraints();

        second.setPercentWidth(50);

        details.getColumnConstraints()
                .addAll(
                        first,
                        second
                );

        profileCard.getChildren().add(
                details
        );

        profileCard.setPadding(
                new Insets(22)
        );

        profileCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
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

        stylePasswordField(
                oldPassword
        );

        stylePasswordField(
                newPassword
        );

        stylePasswordField(
                confirmPassword
        );

        Label passwordDescription =
                new Label(
                        "Choose a strong password and confirm it before saving."
                );

        passwordDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Button changePassword =
                new Button("Change Password");

        changePassword.setPrefHeight(42);

        changePassword.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 0 20 0 20;" +
                "-fx-cursor: hand;"
        );

        VBox passwordCard =
                new VBox(
                        12,
                        createSectionTitle("CHANGE PASSWORD"),
                        passwordDescription,
                        oldPassword,
                        newPassword,
                        confirmPassword,
                        changePassword
                );

        passwordCard.setPadding(
                new Insets(22)
        );

        passwordCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Button dashboard =
                new Button("← Dashboard");

        dashboard.setPrefHeight(42);

        dashboard.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        dashboard.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        profileCard,
                        passwordCard,
                        dashboard
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Student Profile",
                        content
                )
        );
    }

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    private static void addDetail(
            GridPane grid,
            String name,
            String value,
            int column,
            int row
    ) {

        Label label =
                new Label(name);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setWrapText(true);

        valueLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        VBox box =
                new VBox(
                        5,
                        label,
                        valueLabel
                );

        box.setPadding(
                new Insets(12)
        );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        GridPane.setFillWidth(
                box,
                true
        );

        grid.add(
                box,
                column,
                row
        );
    }

    private static void stylePasswordField(
            PasswordField field
    ) {

        field.setPrefHeight(40);
        field.setMaxWidth(420);

        field.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: " + MUTED + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 13px;"
        );
    }
}