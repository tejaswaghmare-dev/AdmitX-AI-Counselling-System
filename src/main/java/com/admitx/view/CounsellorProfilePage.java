package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CounsellorProfilePage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String INPUT = "#0D120D";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title =
                new Label("Counsellor Profile");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "View your counsellor account information and manage your password."
                );

        subtitle.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        Label avatar =
                new Label("CA");

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

        Label counsellorName =
                new Label("Counsellor Admin");

        counsellorName.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label counsellorId =
                new Label("COUN001");

        counsellorId.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox identity =
                new VBox(
                        4,
                        counsellorName,
                        counsellorId
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

        GridPane details =
                new GridPane();

        details.setHgap(16);
        details.setVgap(14);

        addDetail(
                details,
                "Full Name",
                "Counsellor Admin",
                0,
                0
        );

        addDetail(
                details,
                "Counsellor ID",
                "COUN001",
                1,
                0
        );

        addDetail(
                details,
                "Email",
                "counsellor@example.com",
                0,
                1
        );

        addDetail(
                details,
                "Role",
                "Counsellor",
                1,
                1
        );

        ColumnConstraints c1 =
                new ColumnConstraints();

        c1.setPercentWidth(50);

        ColumnConstraints c2 =
                new ColumnConstraints();

        c2.setPercentWidth(50);

        details.getColumnConstraints()
                .addAll(
                        c1,
                        c2
                );

        VBox profileCard =
                new VBox(
                        16,
                        createSectionTitle(
                                "PROFILE INFORMATION"
                        ),
                        profileHeader,
                        details
                );

        profileCard.setPadding(
                new Insets(20)
        );

        profileCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
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

        VBox currentBox =
                createPasswordBox(
                        "Current Password",
                        oldPassword
                );

        VBox newBox =
                createPasswordBox(
                        "New Password",
                        newPassword
                );

        VBox confirmBox =
                createPasswordBox(
                        "Confirm New Password",
                        confirmPassword
                );

        Button changePassword =
                createPrimaryButton(
                        "Change Password",
                        170
                );

        changePassword.setOnAction(e -> {

            if (
                    oldPassword.getText().isBlank()
                    || newPassword.getText().isBlank()
                    || confirmPassword.getText().isBlank()
            ) {

                show(
                        "Password",
                        "Please fill all password fields."
                );

                return;
            }

            if (
                    !newPassword.getText()
                            .equals(
                                    confirmPassword.getText()
                            )
            ) {

                show(
                        "Password",
                        "New password and confirmation do not match."
                );

                return;
            }

            show(
                    "Password",
                    "Password changed successfully."
            );

            oldPassword.clear();
            newPassword.clear();
            confirmPassword.clear();
        });

        Label passwordDescription =
                new Label(
                        "Use a strong password and confirm it before saving."
                );

        passwordDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox passwordCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "CHANGE PASSWORD"
                        ),
                        passwordDescription,
                        currentBox,
                        newBox,
                        confirmBox,
                        changePassword
                );

        passwordCard.setPadding(
                new Insets(20)
        );

        passwordCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        Button logout =
                createDangerButton(
                        "Logout",
                        120
                );

        logout.setOnAction(e ->
                Navigation.goTo(
                        CounsellorLoginPage.getScene()
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox bottom =
                new HBox(
                        spacer,
                        logout
                );

        bottom.setAlignment(
                Pos.CENTER_RIGHT
        );

        VBox root =
                new VBox(
                        20,
                        heading,
                        profileCard,
                        passwordCard,
                        bottom
                );

        root.setPadding(
                new Insets(5)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
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

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    private static void addDetail(
            GridPane grid,
            String labelText,
            String value,
            int column,
            int row
    ) {

        Label label =
                new Label(labelText);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(value);

        valueLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
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

        GridPane.setHgrow(
                box,
                Priority.ALWAYS
        );

        grid.add(
                box,
                column,
                row
        );
    }

    private static VBox createPasswordBox(
            String text,
            PasswordField field
    ) {

        Label label =
                new Label(text);

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        return new VBox(
                6,
                label,
                field
        );
    }

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
                "-fx-background-color: " + INPUT + ";" +
                "-fx-text-fill: white;" +
                "-fx-prompt-text-fill: #687268;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-padding: 0 12 0 12;"
        );
    }

    private static Button createPrimaryButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(width);
        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private static Button createDangerButton(
            String text,
            double width
    ) {

        Button button =
                new Button(text);

        button.setPrefWidth(width);
        button.setPrefHeight(40);

        button.setStyle(
                "-fx-background-color: #DC2626;" +
                "-fx-text-fill: white;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 7px;" +
                "-fx-cursor: hand;"
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