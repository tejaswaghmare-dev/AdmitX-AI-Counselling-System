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

public class CAPRound2Page {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        CAPAllotmentDAO capDAO =
                new CAPAllotmentDAO();

        String studentEmail =
                Student.getInstance().getEmail();

        /*
         * CHECK LOGIN
         */

        if (
                studentEmail == null ||
                studentEmail.isBlank()
        ) {

            showMessage(
                    "Login Required",
                    "Please login before viewing CAP Round 2."
            );

            return StudentLoginPage.getScene();
        }

        /*
         * LOAD ROUND 2
         */

        CAPAllotment allotment =
                capDAO.getStudentAllotment(2);

        /*
         * HEADING
         */

        Label title =
                new Label("CAP Round 2");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Review your Round 2 allotment and choose your next action."
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
         * NO ROUND 2 RESULT
         */

        if (allotment == null) {

            Label waitingBadge =
                    new Label(
                            "●  ROUND 2 RESULT NOT AVAILABLE"
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
                            "CAP Round 2 result is not available."
                    );

            waitingTitle.setStyle(
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + WHITE + ";"
            );

            Label waitingDescription =
                    new Label(
                            "Round 2 results are available only after Betterment processing and result publication."
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

            Button dashboard =
                    new Button(
                            "← Dashboard"
                    );

            styleSecondaryButton(
                    dashboard
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
                            waitingCard,
                            dashboard
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
                            "CAP Round 2",
                            scrollPane
                    )
            );
        }

        /*
         * RESULT PUBLISHED
         */

        Label status =
                new Label(
                        "●  ROUND 2 ALLOTMENT PUBLISHED"
                );

        status.setStyle(
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
                        "UPGRADE DETAILS"
                );

        GridPane details =
                new GridPane();

        details.setHgap(18);
        details.setVgap(15);

        /*
         * FIRESTORE VALUES
         */

        addDetail(
                details,
                "Previous College",
                safe(
                        allotment.getPreviousCollege()
                ),
                0,
                0
        );

        addDetail(
                details,
                "Previous Branch",
                safe(
                        allotment.getPreviousBranch()
                ),
                1,
                0
        );

        addDetail(
                details,
                "Round 2 College",
                safe(
                        allotment.getCollege()
                ),
                0,
                1
        );

        addDetail(
                details,
                "Round 2 Branch",
                safe(
                        allotment.getBranch()
                ),
                1,
                1
        );

        addDetail(
                details,
                "Upgrade Status",
                safe(
                        allotment.getUpgradeStatus()
                ),
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
                        status,
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
                        "Freeze accepts your Round 2 seat and ends further CAP participation. "
                                + "Betterment keeps your current seat while allowing you to participate in CAP Round 3."
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
                        "Accept Round 2 Seat",
                        "Accept this allotment and stop participating in further CAP rounds."
                );

        Button freeze =
                new Button(
                        "Freeze Seat"
                );

        stylePrimaryButton(
                freeze
        );

        freeze.setOnAction(e -> {

            boolean saved =
                    capDAO.saveDecision(
                            2,
                            "Seat Accepted"
                    );

            if (saved) {

                showMessage(
                        "Seat Frozen",
                        "Your Round 2 seat has been accepted. You will not participate in CAP Round 3."
                );

                /*
                 * IMPORTANT:
                 * Freeze does NOT go to Round 3.
                 */

                Navigation.goTo(
                        StudentDashboardPage.getScene()
                );

            } else {

                showMessage(
                        "Error",
                        "Unable to save your Round 2 decision."
                );
            }
        });

        freezeCard.getChildren()
                .add(
                        freeze
                );

        /*
         * BETTERMENT
         */

        VBox bettermentCard =
                createActionCard(
                        "BETTERMENT",
                        "Participate in Round 3",
                        "Keep your Round 2 seat while trying for a higher preference."
                );

        Button betterment =
                new Button(
                        "Request Betterment"
                );

        styleBettermentButton(
                betterment
        );

        betterment.setOnAction(e -> {

            boolean saved =
                    capDAO.saveDecision(
                            2,
                            "Betterment Requested"
                    );

            if (saved) {

                showMessage(
                        "Betterment Requested",
                        "Your request has been saved. You are now eligible for CAP Round 3."
                );

                /*
                 * Round 3 is not necessarily published yet.
                 * Return to dashboard.
                 */

                Navigation.goTo(
                        StudentDashboardPage.getScene()
                );

            } else {

                showMessage(
                        "Error",
                        "Unable to save your Round 2 betterment request."
                );
            }
        });

        bettermentCard.getChildren()
                .add(
                        betterment
                );

        HBox actionCards =
                new HBox(
                        14,
                        freezeCard,
                        bettermentCard
                );

        HBox.setHgrow(
                freezeCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                bettermentCard,
                Priority.ALWAYS
        );

        VBox actions =
                new VBox(
                        14,
                        actionTitle,
                        actionDescription,
                        actionCards
                );

        actions.setPadding(
                new Insets(22)
        );

        actions.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        /*
         * PREVENT DECISION CHANGES
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

            freeze.setDisable(true);
            betterment.setDisable(true);

            actionDescription.setText(
                    "Your Round 2 decision has already been submitted: "
                            + existingDecision
            );
        }

        Label note =
                new Label(
                        "Your Round 2 decision determines whether you keep this seat or continue to CAP Round 3."
                );

        note.setWrapText(true);

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;"
        );

        Button dashboard =
                new Button(
                        "← Dashboard"
                );

        styleSecondaryButton(
                dashboard
        );

        dashboard.setOnAction(e ->
                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        HBox bottomButtons =
                new HBox(
                        dashboard
                );

        bottomButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        resultCard,
                        actions,
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
                        "CAP Round 2",
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
                new Label(
                        labelText
                );

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label valueLabel =
                new Label(
                        value
                );

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
            String description
    ) {

        Label tagLabel =
                new Label(tag);

        tagLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
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
                165
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

    private static void styleBettermentButton(
            Button button
    ) {

        button.setPrefHeight(40);

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setStyle(
                "-fx-background-color: #25351A;" +
                "-fx-text-fill: #C7FF4D;" +
                "-fx-border-color: #486025;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
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