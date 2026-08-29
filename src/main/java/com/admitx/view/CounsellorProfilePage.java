package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    // =========================================================
    // HARDCODED COUNSELLOR DETAILS
    // =========================================================

    private static final String COUNSELLOR_NAME =
            "Counsellor Admin";

    private static final String COUNSELLOR_ID =
            "COUN001";

    private static final String COUNSELLOR_EMAIL =
            "counsellor@admitx.com";

    private static final String COUNSELLOR_ROLE =
            "Counsellor";

    public static Scene getScene() {

        // =========================================================
        // TITLE
        // =========================================================

        Label title =
                new Label(
                        "Counsellor Profile"
                );

        title.setStyle(
                "-fx-font-size:28px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "View your counsellor account information."
                );

        subtitle.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox heading =
                new VBox(
                        4,
                        title,
                        subtitle
                );

        // =========================================================
        // AVATAR
        // =========================================================

        Label avatar =
                new Label(
                        getInitials(
                                COUNSELLOR_NAME
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
                "-fx-background-color:" + LIME + ";" +
                "-fx-background-radius:50%;" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;"
        );

        // =========================================================
        // IDENTITY
        // =========================================================

        Label counsellorName =
                new Label(
                        COUNSELLOR_NAME
                );

        counsellorName.setStyle(
                "-fx-font-size:20px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + TEXT + ";"
        );

        Label counsellorId =
                new Label(
                        COUNSELLOR_ID
                );

        counsellorId.setStyle(
                "-fx-font-size:11px;" +
                "-fx-text-fill:" + MUTED + ";"
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

        // =========================================================
        // PROFILE DETAILS
        // =========================================================

        GridPane details =
                new GridPane();

        details.setHgap(
                16
        );

        details.setVgap(
                14
        );

        addDetail(
                details,
                "Full Name",
                COUNSELLOR_NAME,
                0,
                0
        );

        addDetail(
                details,
                "Counsellor ID",
                COUNSELLOR_ID,
                1,
                0
        );

        addDetail(
                details,
                "Email",
                COUNSELLOR_EMAIL,
                0,
                1
        );

        addDetail(
                details,
                "Role",
                COUNSELLOR_ROLE,
                1,
                1
        );

        ColumnConstraints c1 =
                new ColumnConstraints();

        c1.setPercentWidth(
                50
        );

        ColumnConstraints c2 =
                new ColumnConstraints();

        c2.setPercentWidth(
                50
        );

        details.getColumnConstraints()
                .addAll(
                        c1,
                        c2
                );

        // =========================================================
        // PROFILE CARD
        // =========================================================

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
                new Insets(
                        20
                )
        );

        profileCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:10px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:10px;"
        );

        // =========================================================
        // ACCOUNT INFORMATION
        // =========================================================

        Label accountMessage =
                new Label(
                        "This counsellor account uses fixed administrative credentials."
                );

        accountMessage.setWrapText(
                true
        );

        accountMessage.setStyle(
                "-fx-font-size:13px;" +
                "-fx-text-fill:" + TEXT + ";"
        );

        Label securityMessage =
                new Label(
                        "Password management is disabled because the counsellor login is configured directly in the application."
                );

        securityMessage.setWrapText(
                true
        );

        securityMessage.setStyle(
                "-fx-font-size:12px;" +
                "-fx-text-fill:" + MUTED + ";"
        );

        VBox accountCard =
                new VBox(
                        10,
                        createSectionTitle(
                                "ACCOUNT INFORMATION"
                        ),
                        accountMessage,
                        securityMessage
                );

        accountCard.setPadding(
                new Insets(
                        20
                )
        );

        accountCard.setStyle(
                "-fx-background-color:" + CARD + ";" +
                "-fx-background-radius:10px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:10px;"
        );

        // =========================================================
        // DASHBOARD BUTTON
        // =========================================================

        Button dashboard =
                createSecondaryButton(
                        "← Dashboard",
                        130
                );

        dashboard.setOnAction(e ->

                Navigation.goTo(
                        CounsellorDashboardPage
                                .getScene()
                )
        );

        // =========================================================
        // LOGOUT BUTTON
        // =========================================================

        Button logout =
                createDangerButton(
                        "Logout",
                        120
                );

        logout.setOnAction(e ->

                Navigation.goTo(
                        CounsellorLoginPage
                                .getScene()
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
                        12,
                        dashboard,
                        spacer,
                        logout
                );

        bottom.setAlignment(
                Pos.CENTER_LEFT
        );

        // =========================================================
        // CONTENT
        // =========================================================

        VBox root =
                new VBox(
                        20,
                        heading,
                        profileCard,
                        accountCard,
                        bottom
                );

        root.setPadding(
                new Insets(
                        25
                )
        );

        root.setStyle(
                "-fx-background-color:" + BG + ";"
        );

        // =========================================================
        // SCROLL
        // =========================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        root
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        scrollPane.setStyle(
                "-fx-background:" + BG + ";" +
                "-fx-background-color:" + BG + ";" +
                "-fx-border-color:transparent;"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "Profile",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
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
                "-fx-font-size:10px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + LIME + ";"
        );

        return label;
    }

    // =============================================================
    // DETAIL
    // =============================================================

    private static void addDetail(
            GridPane grid,
            String labelText,
            String value,
            int column,
            int row
    ) {

        Label label =
                new Label(
                        labelText
                );

        label.setStyle(
                "-fx-font-size:11px;" +
                "-fx-font-weight:bold;" +
                "-fx-text-fill:" + MUTED + ";"
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
                "-fx-text-fill:" + TEXT + ";"
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
                "-fx-background-color:" + ROW + ";" +
                "-fx-background-radius:8px;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-radius:8px;"
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

    // =============================================================
    // SECONDARY BUTTON
    // =============================================================

    private static Button createSecondaryButton(
            String text,
            double width
    ) {

        Button button =
                new Button(
                        text
                );

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color:#171F17;" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-border-color:#344034;" +
                "-fx-border-radius:7px;" +
                "-fx-background-radius:7px;" +
                "-fx-font-weight:bold;" +
                "-fx-cursor:hand;"
        );

        return button;
    }

    // =============================================================
    // DANGER BUTTON
    // =============================================================

    private static Button createDangerButton(
            String text,
            double width
    ) {

        Button button =
                new Button(
                        text
                );

        button.setPrefWidth(
                width
        );

        button.setPrefHeight(
                40
        );

        button.setStyle(
                "-fx-background-color:#DC2626;" +
                "-fx-text-fill:white;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:7px;" +
                "-fx-cursor:hand;"
        );

        return button;
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

            return "CA";
        }

        String[] parts =
                name.trim()
                        .split("\\s+");

        if (
                parts.length == 1
        ) {

            return parts[0]
                    .substring(
                            0,
                            Math.min(
                                    2,
                                    parts[0].length()
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
}