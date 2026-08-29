package com.admitx.view;

import com.admitx.dao.CAPAllotmentDAO;
import com.admitx.model.CAPAllotment;
import com.admitx.model.Student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class CAPRound1Page {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#DC2626";

    public static Scene getScene() {

        CAPAllotmentDAO capDAO =
                new CAPAllotmentDAO();

        /*
         * GET LOGGED-IN STUDENT
         */

        String studentEmail =
                Student.getInstance().getEmail();

        if (
                studentEmail == null ||
                studentEmail.isBlank()
        ) {

            showMessage(
                    "Login Required",
                    "Please login before viewing CAP Round 1."
            );

            return StudentLoginPage.getScene();
        }

        /*
         * LOAD ROUND 1 FROM FIRESTORE
         */

        CAPAllotment allotment =
                capDAO.getStudentAllotment(1);

        /*
         * PAGE HEADING
         */

        Label title =
                new Label("CAP Round 1");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "View your Round 1 allotment and choose your preferred action."
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

        /*
         * NO RESULT / NOT PUBLISHED
         */

        if (allotment == null) {

            Label waitingBadge =
                    new Label(
                            "●  RESULT NOT AVAILABLE"
                    );

            waitingBadge.setStyle(
                    "-fx-background-color: #211F0F;" +
                    "-fx-text-fill: #FACC15;" +
                    "-fx-font-size: 11px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-padding: 8 14 8 14;" +
                    "-fx-background-radius: 20px;" +
                    "-fx-border-color: #665F20;" +
                    "-fx-border-radius: 20px;"
            );

            Label waitingTitle =
                    new Label(
                            "CAP Round 1 result is not available yet."
                    );

            waitingTitle.setStyle(
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + WHITE + ";"
            );

            Label waitingDescription =
                    new Label(
                            "Your allotment will appear here after the counsellor runs CAP Round 1 and publishes the results."
                    );

            waitingDescription.setWrapText(true);

            waitingDescription.setStyle(
                    "-fx-font-size: 13px;" +
                    "-fx-text-fill: " + MUTED + ";"
            );

            VBox waitingCard =
                    new VBox(
                            15,
                            waitingBadge,
                            waitingTitle,
                            waitingDescription
                    );

            waitingCard.setPadding(
                    new Insets(25)
            );

            waitingCard.setStyle(
                    "-fx-background-color: " + CARD + ";" +
                    "-fx-background-radius: 12px;" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 12px;"
            );

            Button dashboardButton =
                    new Button(
                            "← Dashboard"
                    );

            styleSecondaryButton(
                    dashboardButton
            );

            dashboardButton.setOnAction(e ->
                    Navigation.goTo(
                            StudentDashboardPage.getScene()
                    )
            );

            VBox content =
                    new VBox(
                            22,
                            heading,
                            waitingCard,
                            dashboardButton
                    );

            content.setPadding(
                    new Insets(30)
            );

            content.setStyle(
                    "-fx-background-color: "
                            + BG
                            + ";"
            );

            ScrollPane scrollPane =
                    new ScrollPane(content);

            scrollPane.setFitToWidth(true);

            scrollPane.setHbarPolicy(
                    ScrollPane.ScrollBarPolicy.NEVER
            );

            scrollPane.setStyle(
                    "-fx-background: " + BG + ";" +
                    "-fx-background-color: " + BG + ";"
            );

            return new Scene(
                    StudentLayout.create(
                            "CAP Round 1",
                            scrollPane
                    )
            );
        }

        /*
         * ALLOTMENT PUBLISHED
         */

        Label roundBadge =
                new Label(
                        "●  ALLOTMENT PUBLISHED"
                );

        roundBadge.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-background-radius: 20px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 20px;"
        );

        Label allotmentTitle =
                createSectionTitle(
                        "ROUND 1 ALLOTMENT"
                );

        GridPane details =
                new GridPane();

        details.setHgap(18);
        details.setVgap(15);

        /*
         * REAL FIRESTORE DATA
         */

        addDetail(
                details,
                "Student",
                studentEmail,
                0,
                0
        );

        addDetail(
                details,
                "Allotment Status",
                safe(
                        allotment.getStatus()
                ),
                1,
                0
        );

        addDetail(
                details,
                "College",
                safe(
                        allotment.getCollege()
                ),
                0,
                1
        );

        addDetail(
                details,
                "Branch",
                safe(
                        allotment.getBranch()
                ),
                1,
                1
        );

        addDetail(
                details,
                "CAP Round",
                "Round 1",
                0,
                2
        );

        addDetail(
                details,
                "Allotted Preference",
                "Preference No. "
                        + allotment.getPreferenceNumber(),
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

        VBox resultCard =
                new VBox(
                        16,
                        allotmentTitle,
                        roundBadge,
                        details
                );

        resultCard.setPadding(
                new Insets(22)
        );

        resultCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        /*
         * ACTION SECTION
         */

        Label actionTitle =
                createSectionTitle(
                        "CHOOSE YOUR ACTION"
                );

        Label actionDescription =
                new Label(
                        "Choose carefully. Freeze accepts the current seat, "
                                + "Betterment keeps the seat while allowing you to participate "
                                + "in the next round, and Reject declines the allotment."
                );

        actionDescription.setWrapText(true);

        actionDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        /*
         * FREEZE
         */

        VBox freezeCard =
                createActionCard(
                        "FREEZE",
                        "Accept Current Seat",
                        "Confirm this allotment and proceed towards admission.",
                        LIME
                );

        Button freezeButton =
                new Button(
                        "Freeze Seat"
                );

        stylePrimaryButton(
                freezeButton
        );

        freezeButton.setOnAction(e -> {

            boolean saved =
                    capDAO.saveDecision(
                            1,
                            "Seat Accepted"
                    );

            if (saved) {

                showMessage(
                        "Seat Frozen",
                        "Your Round 1 allotted seat has been accepted."
                );

                Navigation.goTo(
                        Round1ConfirmationPage.getScene(
                                "Seat Accepted"
                        )
                );

            } else {

                showMessage(
                        "Error",
                        "Unable to save your decision. Please try again."
                );
            }
        });

        freezeCard.getChildren()
                .add(
                        freezeButton
                );

        /*
         * BETTERMENT
         */

        VBox bettermentCard =
                createActionCard(
                        "BETTERMENT",
                        "Try for Higher Preference",
                        "Keep this seat while participating in CAP Round 2.",
                        "#A3E635"
                );

        Button bettermentButton =
                new Button(
                        "Request Betterment"
                );

        styleSecondaryActionButton(
                bettermentButton
        );

        bettermentButton.setOnAction(e -> {

            boolean saved =
                    capDAO.saveDecision(
                            1,
                            "Betterment Requested"
                    );

            if (saved) {

                showMessage(
                        "Betterment Requested",
                        "Your betterment request has been saved. You will be considered for CAP Round 2."
                );

                Navigation.goTo(
                        Round1ConfirmationPage.getScene(
                                "Betterment Requested"
                        )
                );

            } else {

                showMessage(
                        "Error",
                        "Unable to save your betterment request."
                );
            }
        });

        bettermentCard.getChildren()
                .add(
                        bettermentButton
                );

        /*
         * REJECT
         */

        VBox rejectCard =
                createActionCard(
                        "REJECT",
                        "Decline Allotted Seat",
                        "Reject the current allotment and do not accept this seat.",
                        "#FF6B6B"
                );

        Button rejectButton =
                new Button(
                        "Reject Seat"
                );

        styleDangerButton(
                rejectButton
        );

        rejectButton.setOnAction(e -> {

            boolean saved =
                    capDAO.saveDecision(
                            1,
                            "Seat Rejected"
                    );

            if (saved) {

                showMessage(
                        "Seat Rejected",
                        "Your Round 1 allotted seat has been rejected."
                );

                Navigation.goTo(
                        Round1ConfirmationPage.getScene(
                                "Seat Rejected"
                        )
                );

            } else {

                showMessage(
                        "Error",
                        "Unable to save your decision."
                );
            }
        });

        rejectCard.getChildren()
                .add(
                        rejectButton
                );

        HBox actionCards =
                new HBox(
                        14,
                        freezeCard,
                        bettermentCard,
                        rejectCard
                );

        HBox.setHgrow(
                freezeCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                bettermentCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                rejectCard,
                Priority.ALWAYS
        );

        VBox actionCard =
                new VBox(
                        14,
                        actionTitle,
                        actionDescription,
                        actionCards
                );

        actionCard.setPadding(
                new Insets(22)
        );

        actionCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        /*
         * EXISTING DECISION
         */

        String existingDecision =
                allotment.getDecision();

        if (
                existingDecision != null &&
                !existingDecision.isBlank() &&
                !"Pending".equalsIgnoreCase(
                        existingDecision
                )
        ) {

            freezeButton.setDisable(true);
            bettermentButton.setDisable(true);
            rejectButton.setDisable(true);

            actionDescription.setText(
                    "Your Round 1 decision has already been submitted: "
                            + existingDecision
            );
        }

        Label note =
                new Label(
                        "Important: Your selected action will determine your participation in the next CAP round."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #211F0F;" +
                "-fx-text-fill: #D9E6C8;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #665F20;" +
                "-fx-border-radius: 8px;"
        );

        Button dashboardButton =
                new Button(
                        "← Dashboard"
                );

        styleSecondaryButton(
                dashboardButton
        );

        dashboardButton.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        HBox bottomButtons =
                new HBox(
                        dashboardButton
                );

        bottomButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        resultCard,
                        actionCard,
                        note,
                        bottomButtons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: "
                        + BG
                        + ";"
        );

        ScrollPane scrollPane =
                new ScrollPane(
                        content
                );

        scrollPane.setFitToWidth(
                true
        );

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "CAP Round 1",
                        scrollPane
                )
        );
    }

    private static String safe(
            String value
    ) {

        if (
                value == null ||
                value.isBlank()
        ) {
            return "Not Available";
        }

        return value;
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

        valueLabel.setWrapText(
                true
        );

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

    private static VBox createActionCard(
            String tag,
            String title,
            String description,
            String accent
    ) {

        Label tagLabel =
                new Label(tag);

        tagLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + accent + ";"
        );

        Label titleLabel =
                new Label(title);

        titleLabel.setWrapText(
                true
        );

        titleLabel.setStyle(
                "-fx-font-size: 15px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox card =
                new VBox(
                        8,
                        tagLabel,
                        titleLabel,
                        descriptionLabel
                );

        card.setPadding(
                new Insets(18)
        );

        card.setMaxWidth(
                Double.MAX_VALUE
        );

        card.setMinHeight(
                180
        );

        card.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        return card;
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(40);
        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleSecondaryActionButton(
            Button button
    ) {

        button.setPrefHeight(40);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setStyle(
                "-fx-background-color: #25351A;" +
                "-fx-text-fill: #C7FF4D;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-border-color: #486025;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleDangerButton(
            Button button
    ) {

        button.setPrefHeight(40);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setStyle(
                "-fx-background-color: " + RED + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

        button.setPadding(
                new Insets(
                        0,
                        18,
                        0,
                        18
                )
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }

    private static void showMessage(
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