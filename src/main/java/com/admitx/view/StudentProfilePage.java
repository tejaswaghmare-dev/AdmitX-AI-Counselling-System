package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StudentProfilePage {

    // =========================================================
    // COLOR PALETTE
    // =========================================================

    private static final String BG = "#040505";
    private static final String CARD = "#101211";
    private static final String CARD_LIGHT = "#161813";

    private static final String LIME = "#C6E92F";
    private static final String LIME_MEDIUM = "#8AA30B";
    private static final String LIME_DARK = "#5E7107";

    private static final String WHITE = "#F7F7F5";
    private static final String SECONDARY = "#9A9D91";
    private static final String BORDER = "#2B2F28";
    private static final String INPUT = "#0B0D0C";

    // =========================================================
    // MAIN SCENE
    // =========================================================

    public static Scene getScene() {

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        VBox content = new VBox(22);

        content.setPadding(
                new Insets(30, 40, 40, 40)
        );

        content.setAlignment(
                Pos.TOP_CENTER
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // PAGE HEADER
        // =====================================================

        VBox header = new VBox(5);

        header.setAlignment(
                Pos.CENTER_LEFT
        );

        header.setMaxWidth(900);

        Label smallTitle =
                new Label("ACCOUNT");

        smallTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        11
                )
        );

        smallTitle.setTextFill(
                Color.web(LIME)
        );

        smallTitle.setStyle(
                "-fx-letter-spacing: 2px;"
        );

        Label title =
                new Label("Student Profile");

        title.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        30
                )
        );

        title.setTextFill(
                Color.web(WHITE)
        );

        Label subtitle =
                new Label(
                        "View your admission details and manage your account"
                );

        subtitle.setFont(
                Font.font(
                        "Segoe UI",
                        13
                )
        );

        subtitle.setTextFill(
                Color.web(SECONDARY)
        );

        header.getChildren().addAll(
                smallTitle,
                title,
                subtitle
        );

        // =====================================================
        // PROFILE CARD
        // =====================================================

        VBox profileCard =
                new VBox(20);

        profileCard.setMaxWidth(900);

        profileCard.setPadding(
                new Insets(28)
        );

        profileCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 18;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 18;" +
                "-fx-border-width: 1;" +
                "-fx-effect: dropshadow(" +
                "gaussian, rgba(0,0,0,0.55), 25, 0, 0, 10);"
        );

        // =====================================================
        // PROFILE HEADER
        // =====================================================

        HBox profileHeader =
                new HBox(15);

        profileHeader.setAlignment(
                Pos.CENTER_LEFT
        );

        // Avatar
        Label avatar =
                new Label("Y");

        avatar.setAlignment(
                Pos.CENTER
        );

        avatar.setPrefSize(
                58,
                58
        );

        avatar.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        23
                )
        );

        avatar.setTextFill(
                Color.web("#050505")
        );

        avatar.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 30;"
        );

        // Name section
        VBox identity =
                new VBox(3);

        Label studentName =
                new Label("Yash Batte");

        studentName.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        19
                )
        );

        studentName.setTextFill(
                Color.web(WHITE)
        );

        Label applicationId =
                new Label(
                        "Application ID  •  MHTCET20260001"
                );

        applicationId.setFont(
                Font.font(
                        "Segoe UI",
                        11
                )
        );

        applicationId.setTextFill(
                Color.web(SECONDARY)
        );

        identity.getChildren().addAll(
                studentName,
                applicationId
        );

        profileHeader.getChildren().addAll(
                avatar,
                identity
        );

        // =====================================================
        // DIVIDER
        // =====================================================

        HBox divider =
                createDivider();

        // =====================================================
        // PROFILE DETAILS TITLE
        // =====================================================

        Label detailsTitle =
                new Label("Personal Information");

        detailsTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        16
                )
        );

        detailsTitle.setTextFill(
                Color.web(WHITE)
        );

        // =====================================================
        // DETAILS GRID
        // =====================================================

        GridPane details =
                new GridPane();

        details.setHgap(15);
        details.setVgap(15);

        details.setMaxWidth(
                Double.MAX_VALUE
        );

        addDetail(
                details,
                "FULL NAME",
                "Yash Batte",
                0,
                0
        );

        addDetail(
                details,
                "APPLICATION ID",
                "MHTCET20260001",
                1,
                0
        );

        addDetail(
                details,
                "EMAIL ADDRESS",
                "student@example.com",
                0,
                1
        );

        addDetail(
                details,
                "MOBILE NUMBER",
                "9876543210",
                1,
                1
        );

        addDetail(
                details,
                "CATEGORY",
                "Open",
                0,
                2
        );

        addDetail(
                details,
                "MHT CET PERCENTILE",
                "95.50",
                1,
                2
        );

        // =====================================================
        // SCORE BADGE
        // =====================================================

        VBox scoreBox =
                new VBox(3);

        scoreBox.setAlignment(
                Pos.CENTER
        );

        scoreBox.setPadding(
                new Insets(14)
        );

        scoreBox.setStyle(
                "-fx-background-color: rgba(198,233,47,0.06);" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: rgba(198,233,47,0.18);" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1;"
        );

        Label scoreLabel =
                new Label("95.50");

        scoreLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.EXTRA_BOLD,
                        22
                )
        );

        scoreLabel.setTextFill(
                Color.web(LIME)
        );

        Label scoreText =
                new Label("MHT CET Percentile");

        scoreText.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        scoreText.setTextFill(
                Color.web(SECONDARY)
        );

        scoreBox.getChildren().addAll(
                scoreLabel,
                scoreText
        );

        // =====================================================
        // PASSWORD SECTION
        // =====================================================

        VBox passwordSection =
                new VBox(16);

        passwordSection.setPadding(
                new Insets(8, 0, 0, 0)
        );

        Label passwordTitle =
                new Label("Security");

        passwordTitle.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        16
                )
        );

        passwordTitle.setTextFill(
                Color.web(WHITE)
        );

        Label passwordSubtitle =
                new Label(
                        "Update your password to keep your account secure"
                );

        passwordSubtitle.setFont(
                Font.font(
                        "Segoe UI",
                        11
                )
        );

        passwordSubtitle.setTextFill(
                Color.web(SECONDARY)
        );

        // =====================================================
        // PASSWORD FIELDS
        // =====================================================

        PasswordField oldPassword =
                createPasswordField(
                        "Current Password"
                );

        PasswordField newPassword =
                createPasswordField(
                        "New Password"
                );

        PasswordField confirmPassword =
                createPasswordField(
                        "Confirm New Password"
                );

        HBox passwordFields =
                new HBox(12);

        passwordFields.setAlignment(
                Pos.CENTER
        );

        HBox.setHgrow(
                oldPassword,
                javafx.scene.layout.Priority.ALWAYS
        );

        HBox.setHgrow(
                newPassword,
                javafx.scene.layout.Priority.ALWAYS
        );

        HBox.setHgrow(
                confirmPassword,
                javafx.scene.layout.Priority.ALWAYS
        );

        passwordFields.getChildren().addAll(
                oldPassword,
                newPassword,
                confirmPassword
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        HBox buttonBox =
                new HBox(12);

        buttonBox.setAlignment(
                Pos.CENTER_LEFT
        );

        Button changePassword =
                new Button("CHANGE PASSWORD");

        changePassword.setPrefHeight(44);
        changePassword.setPrefWidth(175);

        changePassword.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        12
                )
        );

        changePassword.setCursor(
                javafx.scene.Cursor.HAND
        );

        applyPrimaryButtonStyle(
                changePassword,
                false
        );

        changePassword.setOnMouseEntered(e ->
                applyPrimaryButtonStyle(
                        changePassword,
                        true
                )
        );

        changePassword.setOnMouseExited(e ->
                applyPrimaryButtonStyle(
                        changePassword,
                        false
                )
        );

        Button dashboard =
                new Button("← DASHBOARD");

        dashboard.setPrefHeight(44);
        dashboard.setPrefWidth(145);

        dashboard.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        12
                )
        );

        dashboard.setCursor(
                javafx.scene.Cursor.HAND
        );

        applySecondaryButtonStyle(
                dashboard,
                false
        );

        dashboard.setOnMouseEntered(e ->
                applySecondaryButtonStyle(
                        dashboard,
                        true
                )
        );

        dashboard.setOnMouseExited(e ->
                applySecondaryButtonStyle(
                        dashboard,
                        false
                )
        );

        // =====================================================
        // ACTION
        // =====================================================

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        changePassword.setOnAction(e -> {

            System.out.println(
                    "Change password clicked"
            );

            // Add password update logic here.
        });

        buttonBox.getChildren().addAll(
                changePassword,
                dashboard
        );

        // =====================================================
        // PASSWORD SECTION ASSEMBLY
        // =====================================================

        VBox securityHeader =
                new VBox(3);

        securityHeader.getChildren().addAll(
                passwordTitle,
                passwordSubtitle
        );

        passwordSection.getChildren().addAll(
                securityHeader,
                passwordFields,
                buttonBox
        );

        // =====================================================
        // PROFILE CARD ASSEMBLY
        // =====================================================

        profileCard.getChildren().addAll(
                profileHeader,
                divider,
                detailsTitle,
                details,
                passwordSection
        );

        // =====================================================
        // FOOTER
        // =====================================================

        Label footer =
                new Label(
                        "© 2026 AdmitX  •  Student Account"
                );

        footer.setFont(
                Font.font(
                        "Segoe UI",
                        10
                )
        );

        footer.setTextFill(
                Color.web(SECONDARY, 0.45)
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        content.getChildren().addAll(
                header,
                profileCard,
                footer
        );

        // =====================================================
        // STUDENT LAYOUT
        // =====================================================

        return new Scene(
                StudentLayout.create(
                        "Student Profile",
                        content
                )
        );
    }

    // =========================================================
    // DETAIL CARD
    // =========================================================

    private static void addDetail(
            GridPane grid,
            String name,
            String value,
            int column,
            int row
    ) {

        VBox box =
                new VBox(5);

        box.setPadding(
                new Insets(15)
        );

        box.setPrefHeight(72);

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color: " + CARD_LIGHT + ";" +
                "-fx-background-radius: 11;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 11;" +
                "-fx-border-width: 1;"
        );

        Label label =
                new Label(name);

        label.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        9
                )
        );

        label.setTextFill(
                Color.web(SECONDARY)
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setFont(
                Font.font(
                        "Segoe UI",
                        FontWeight.BOLD,
                        14
                )
        );

        valueLabel.setTextFill(
                Color.web(WHITE)
        );

        box.getChildren().addAll(
                label,
                valueLabel
        );

        grid.add(
                box,
                column,
                row
        );

        GridPane.setHgrow(
                box,
                javafx.scene.layout.Priority.ALWAYS
        );
    }

    // =========================================================
    // PASSWORD FIELD
    // =========================================================

    private static PasswordField createPasswordField(
            String prompt
    ) {

        PasswordField field =
                new PasswordField();

        field.setPromptText(
                prompt
        );

        field.setPrefHeight(45);

        field.setFont(
                Font.font(
                        "Segoe UI",
                        12
                )
        );

        applyFieldStyle(
                field,
                false
        );

        field.focusedProperty().addListener(
                (obs, oldValue, focused) ->
                        applyFieldStyle(
                                field,
                                focused
                        )
        );

        return field;
    }

    // =========================================================
    // INPUT STYLE
    // =========================================================

    private static void applyFieldStyle(
            PasswordField field,
            boolean focused
    ) {

        String border =
                focused
                        ? LIME
                        : BORDER;

        String shadow =
                focused
                        ? "-fx-effect: dropshadow(" +
                          "gaussian, rgba(198,233,47,0.20), " +
                          "12, 0, 0, 0);"
                        : "";

        field.setStyle(
                "-fx-background-color: " + INPUT + ";" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: #666A61;" +
                "-fx-background-radius: 9;" +
                "-fx-border-color: " + border + ";" +
                "-fx-border-radius: 9;" +
                "-fx-border-width: " +
                (focused ? "1.5" : "1") + ";" +
                "-fx-padding: 0 13 0 13;" +
                "-fx-font-size: 12px;" +
                shadow
        );
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private static void applyPrimaryButtonStyle(
            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(
                    "-fx-background-color: " + LIME + ";" +
                    "-fx-text-fill: #050505;" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12px;" +
                    "-fx-effect: dropshadow(" +
                    "gaussian, rgba(198,233,47,0.45), " +
                    "20, 0, 0, 5);"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: " + LIME_MEDIUM + ";" +
                    "-fx-text-fill: #FFFFFF;" +
                    "-fx-background-radius: 9;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12px;" +
                    "-fx-effect: dropshadow(" +
                    "gaussian, rgba(138,163,11,0.25), " +
                    "12, 0, 0, 3);"
            );
        }
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private static void applySecondaryButtonStyle(
            Button button,
            boolean hover
    ) {

        if (hover) {

            button.setStyle(
                    "-fx-background-color: rgba(198,233,47,0.08);" +
                    "-fx-text-fill: " + LIME + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-border-color: " + LIME_DARK + ";" +
                    "-fx-border-radius: 9;" +
                    "-fx-border-width: 1;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12px;"
            );

        } else {

            button.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: " + SECONDARY + ";" +
                    "-fx-background-radius: 9;" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 9;" +
                    "-fx-border-width: 1;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 12px;"
            );
        }
    }

    // =========================================================
    // DIVIDER
    // =========================================================

    private static HBox createDivider() {

        HBox divider =
                new HBox();

        divider.setPrefHeight(1);

        divider.setMaxWidth(
                Double.MAX_VALUE
        );

        divider.setStyle(
                "-fx-background-color: " + BORDER + ";"
        );

        return divider;
    }
}