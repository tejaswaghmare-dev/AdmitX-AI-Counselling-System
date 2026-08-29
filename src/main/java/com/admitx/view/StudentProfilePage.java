package com.admitx.view;

import com.admitx.controller.StudentInfoAddController;
import com.admitx.model.Student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StudentProfilePage {

    private static final String BG =
            "#0B100B";

    private static final String CARD =
            "#141B14";

    private static final String ROW =
            "#0F150F";

    private static final String BORDER =
            "#293529";

    private static final String LIME =
            "#B7FF00";

    private static final String WHITE =
            "#F5F7F2";

    private static final String MUTED =
            "#9AA59A";

    public static Scene getScene() {

        // =========================================================
        // CURRENT STUDENT
        // =========================================================

        Student sessionStudent =
                Student.getInstance();

        String loggedInEmail =
                sessionStudent.getEmail();

        if (
                loggedInEmail == null
                ||
                loggedInEmail.isBlank()
        ) {

            show(
                    Alert.AlertType.WARNING,
                    "Student Profile",
                    "Please login to view your profile."
            );

            return StudentLoginPage
                    .getScene();
        }

        StudentInfoAddController controller =
                new StudentInfoAddController();

        Student student =
                controller.getStudentProfile(
                        loggedInEmail
                );

        if (
                student == null
        ) {

            student =
                    sessionStudent;
        }

        // =========================================================
        // VALUES
        // =========================================================

        String fullName =
                firstAvailable(
                        student.getCandidateName(),
                        student.getUsername(),
                        "Student"
                );

        String email =
                value(
                        student.getEmail()
                );

        String mobile =
                value(
                        student.getMobileno()
                );

        String category =
                value(
                        student.getCategory()
                );

        String cetPercentile =
                value(
                        student.getCetPercentile()
                );

        String homeUniversity =
                value(
                        student.getHomeUniversity()
                );

        String district =
                value(
                        student.getDistrict()
                );

        String candidature =
                value(
                        student.getCandidateType()
                );

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Student Profile"
                );

        title.setStyle(
                "-fx-font-size:26px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "View your account information and manage your password."
                );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        6,
                        title,
                        subtitle
                );

        // =========================================================
        // AVATAR
        // =========================================================

        Label avatar =
                new Label(
                        getInitials(
                                fullName
                        )
                );

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
                "-fx-background-color:"
                        + LIME + ";" +
                "-fx-background-radius:50%;" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        // =========================================================
        // IDENTITY
        // =========================================================

        Label studentName =
                new Label(
                        fullName
                );

        studentName.setStyle(
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + WHITE + ";"
        );

        Label studentEmail =
                new Label(
                        email
                );

        studentEmail.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        VBox identity =
                new VBox(
                        4,
                        studentName,
                        studentEmail
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

        // =========================================================
        // PROFILE DETAILS
        // =========================================================

        GridPane details =
                new GridPane();

        details.setHgap(
                18
        );

        details.setVgap(
                15
        );

        addDetail(
                details,
                "Full Name",
                fullName,
                0,
                0
        );

        addDetail(
                details,
                "Email",
                email,
                1,
                0
        );

        addDetail(
                details,
                "Mobile",
                mobile,
                0,
                1
        );

        addDetail(
                details,
                "Category",
                category,
                1,
                1
        );

        addDetail(
                details,
                "MHT CET Percentile",
                cetPercentile,
                0,
                2
        );

        addDetail(
                details,
                "Home University",
                homeUniversity,
                1,
                2
        );

        addDetail(
                details,
                "District",
                district,
                0,
                3
        );

        addDetail(
                details,
                "Candidature Type",
                candidature,
                1,
                3
        );

        ColumnConstraints first =
                new ColumnConstraints();

        first.setPercentWidth(
                50
        );

        ColumnConstraints second =
                new ColumnConstraints();

        second.setPercentWidth(
                50
        );

        details.getColumnConstraints()
                .addAll(
                        first,
                        second
                );

        // =========================================================
        // PROFILE CARD
        // =========================================================

        VBox profileCard =
                new VBox(
                        18,
                        createSectionTitle(
                                "PROFILE INFORMATION"
                        ),
                        profileHeader,
                        details
                );

        profileCard.setPadding(
                new Insets(
                        22
                )
        );

        profileCard.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        // =========================================================
        // PASSWORD FIELDS
        // =========================================================

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
                        "Enter your current password and choose a new password."
                );

        passwordDescription.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        // =========================================================
        // CHANGE PASSWORD
        // =========================================================

        Button changePassword =
                new Button(
                        "Change Password"
                );

        changePassword.setPrefHeight(
                42
        );

        changePassword.setStyle(
                "-fx-background-color:"
                        + LIME + ";" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:13px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:0 20 0 20;" +
                "-fx-cursor:hand;"
        );

        final String currentEmail =
                loggedInEmail;

        changePassword.setOnAction(e -> {

            String oldValue =
                    oldPassword
                            .getText();

            String newValue =
                    newPassword
                            .getText();

            String confirmValue =
                    confirmPassword
                            .getText();

            if (
                    oldValue.isBlank()
                    ||
                    newValue.isBlank()
                    ||
                    confirmValue.isBlank()
            ) {

                show(
                        Alert.AlertType.WARNING,
                        "Change Password",
                        "Please fill all password fields."
                );

                return;
            }

            if (
                    newValue.length()
                            < 6
            ) {

                show(
                        Alert.AlertType.WARNING,
                        "Change Password",
                        "New password must contain at least 6 characters."
                );

                return;
            }

            if (
                    !newValue.equals(
                            confirmValue
                    )
            ) {

                show(
                        Alert.AlertType.WARNING,
                        "Change Password",
                        "New password and confirmation do not match."
                );

                return;
            }

            if (
                    oldValue.equals(
                            newValue
                    )
            ) {

                show(
                        Alert.AlertType.WARNING,
                        "Change Password",
                        "New password must be different from the current password."
                );

                return;
            }

            boolean changed =
                    controller.changePassword(
                            currentEmail,
                            oldValue,
                            newValue
                    );

            if (
                    changed
            ) {

                show(
                        Alert.AlertType.INFORMATION,
                        "Password Changed",
                        "Your password has been changed successfully."
                );

                oldPassword.clear();
                newPassword.clear();
                confirmPassword.clear();

            } else {

                show(
                        Alert.AlertType.ERROR,
                        "Change Password",
                        "Current password is incorrect."
                );
            }
        });

        // =========================================================
        // PASSWORD CARD
        // =========================================================

        VBox passwordCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "CHANGE PASSWORD"
                        ),
                        passwordDescription,
                        oldPassword,
                        newPassword,
                        confirmPassword,
                        changePassword
                );

        passwordCard.setPadding(
                new Insets(
                        22
                )
        );

        passwordCard.setStyle(
                "-fx-background-color:"
                        + CARD + ";" +
                "-fx-background-radius:12px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:12px;"
        );

        // =========================================================
        // DASHBOARD BUTTON
        // =========================================================

        Button dashboard =
                new Button(
                        "← Dashboard"
                );

        dashboard.setPrefHeight(
                42
        );

        dashboard.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        dashboard.setStyle(
                "-fx-background-color:#171F17;" +
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-border-color:#344034;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );

        dashboard.setOnAction(e ->

                Navigation.goTo(
                        StudentDashboardPage
                                .getScene()
                )
        );

        // =========================================================
        // CONTENT
        // =========================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        profileCard,
                        passwordCard,
                        dashboard
                );

        content.setPadding(
                new Insets(
                        30
                )
        );

        content.setStyle(
                "-fx-background-color:"
                        + BG + ";"
        );

        // =========================================================
        // SCROLL
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane
                        .ScrollBarPolicy
                        .AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background:"
                        + BG + ";" +
                "-fx-background-color:"
                        + BG + ";" +
                "-fx-border-color:transparent;"
        );

        return new Scene(
                StudentLayout.create(
                        "Student Profile",
                        scrollPane
                )
        );
    }

    // =============================================================
    // SECTION TITLE
    // =============================================================

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + LIME + ";"
        );

        return label;
    }

    // =============================================================
    // DETAIL CARD
    // =============================================================

    private static void addDetail(
            GridPane grid,
            String name,
            String value,
            int column,
            int row
    ) {

        Label label =
                new Label(
                        name
                );

        label.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + MUTED + ";"
        );

        Label valueLabel =
                new Label(
                        value
                );

        valueLabel.setWrapText(
                true
        );

        valueLabel.setStyle(
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:"
                        + WHITE + ";"
        );

        VBox box =
                new VBox(
                        5,
                        label,
                        valueLabel
                );

        box.setPadding(
                new Insets(
                        12
                )
        );

        box.setMaxWidth(
                Double.MAX_VALUE
        );

        box.setStyle(
                "-fx-background-color:"
                        + ROW + ";" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:8px;"
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

    // =============================================================
    // PASSWORD STYLE
    // =============================================================

    private static void stylePasswordField(
            PasswordField field
    ) {

        field.setPrefHeight(
                40
        );

        field.setMaxWidth(
                420
        );

        field.setStyle(
                "-fx-background-color:"
                        + ROW + ";" +
                "-fx-text-fill:"
                        + WHITE + ";" +
                "-fx-prompt-text-fill:"
                        + MUTED + ";" +
                "-fx-border-color:"
                        + BORDER + ";" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-size:13px;"
        );
    }

    // =============================================================
    // INITIALS
    // =============================================================

    private static String getInitials(
            String name
    ) {

        if (
                name == null
                ||
                name.isBlank()
        ) {

            return "ST";
        }

        String[] parts =
                name.trim()
                        .split("\\s+");

        if (
                parts.length == 1
        ) {

            String value =
                    parts[0];

            return value
                    .substring(
                            0,
                            Math.min(
                                    2,
                                    value.length()
                            )
                    )
                    .toUpperCase();
        }

        return (
                parts[0]
                        .substring(
                                0,
                                1
                        )
                +
                parts[
                        parts.length - 1
                ].substring(
                        0,
                        1
                )
        ).toUpperCase();
    }

    // =============================================================
    // SAFE VALUE
    // =============================================================

    private static String value(
            String text
    ) {

        if (
                text == null
                ||
                text.isBlank()
        ) {

            return "Not Provided";
        }

        return text;
    }

    private static String firstAvailable(
            String first,
            String second,
            String fallback
    ) {

        if (
                first != null
                &&
                !first.isBlank()
        ) {

            return first;
        }

        if (
                second != null
                &&
                !second.isBlank()
        ) {

            return second;
        }

        return fallback;
    }

    // =============================================================
    // ALERT
    // =============================================================

    private static void show(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(
                        type
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                message
        );

        alert.showAndWait();
    }
}