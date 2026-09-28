package com.admitx.view;

import com.admitx.dao.CAPAllotmentDAO;
import com.admitx.model.CAPAllotment;
import com.admitx.model.Student;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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

        Scene loadingScene =
                createLoadingScene();

        AsyncTaskRunner.run(
                () -> new CAPAllotmentDAO()
                        .getStudentAllotment(1),
                allotment -> Navigation.goTo(
                        createRoundScene(
                                allotment,
                                studentEmail
                        )
                ),
                error -> {
                    error.printStackTrace();
                    showLoadingError();
                }
        );

        return loadingScene;
    }

    private static Scene createRoundScene(
            CAPAllotment allotment,
            String studentEmail
    ) {

        /*
         * PAGE HEADING
         */

        Label title =
                new Label("CAP Round 1");

        title.setStyle(
                "-fx-font-size: 28px;" +
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
                    new Insets(22)
            );

            waitingCard.setStyle(
                    "-fx-background-color: " + CARD + ";" +
                    "-fx-background-radius: 12px;" +
                    "-fx-border-color: " + BORDER + ";" +
                    "-fx-border-radius: 12px;"
            );

            

            VBox content =
                    new VBox(
                            22,
                            heading,
                            waitingCard
                            
                    );

            content.setPadding(
                    new Insets(20, 24, 30, 24)
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

            scrollPane.setVbarPolicy(
                    ScrollPane.ScrollBarPolicy.NEVER
            );
            scrollPane.setPannable(true);

            scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );
        scrollPane.setPannable(true);

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
                allotment.getPreferenceNumber() > 0
                        ? "Preference No. " + allotment.getPreferenceNumber()
                        : "Not Allotted",
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
                        "Choose carefully. Freeze accepts the current seat and proceeds to admission confirmation, "
                                + "while Betterment keeps the seat and allows you to participate in the next round."
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
            saveDecisionAsync(
                    freezeButton,
                    1,
                    "Seat Accepted",
                    "Seat Frozen",
                    "Your Round 1 allotted seat has been accepted.",
                    () -> Navigation.goTo(
                            AdmissionConfirmationPage.getScene()
                    )
            );
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
            saveDecisionAsync(
                    bettermentButton,
                    1,
                    "Betterment Requested",
                    "Betterment Requested",
                    "Your betterment request has been saved. You will be considered for CAP Round 2.",
                    () -> Navigation.goTo(
                            Round1ConfirmationPage.getScene(
                                    "Betterment Requested"
                            )
                    )
            );
        });

        bettermentCard.getChildren()
                .add(
                        bettermentButton
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

        boolean hasSeat =
                allotment.getPreferenceNumber() > 0
                        && allotment.getCollege() != null
                        && !allotment.getCollege().isBlank();

        String existingDecision =
                allotment.getDecision();

        if (!hasSeat) {
            freezeButton.setDisable(true);
            bettermentButton.setDisable(true);
            actionDescription.setText(
                    "No seat was allotted in Round 1. You will automatically be considered in CAP Round 2 using your locked preferences."
            );
        }

        if (hasSeat && (
                existingDecision != null &&
                !existingDecision.isBlank() &&
                !"Pending".equalsIgnoreCase(
                        existingDecision
                )
        )) {

            freezeButton.setDisable(true);
            bettermentButton.setDisable(true);

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
                "-fx-padding: 16px;" +
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
                new Insets(20, 24, 30, 24)
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

        scrollPane.setVbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );
        scrollPane.setPannable(true);

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

    private static Scene createLoadingScene() {

        ProgressIndicator progress = new ProgressIndicator();
        progress.setPrefSize(42, 42);

        Label label = new Label("Loading CAP Round 1 allotment...");
        label.setStyle(
                "-fx-text-fill:" + MUTED + ";" +
                "-fx-font-size:13px;"
        );

        VBox loadingBox = new VBox(14, progress, label);
        loadingBox.setAlignment(Pos.CENTER);

        BorderPane content = new BorderPane();
        content.setCenter(loadingBox);
        content.setStyle("-fx-background-color:" + BG + ";");

        return new Scene(
                StudentLayout.create("CAP Round 1", content)
        );
    }

    private static void showLoadingError() {
        showMessage(
                "Loading Error",
                "Could not load CAP Round 1 allotment."
        );
    }

    private static void saveDecisionAsync(
            Button button,
            int round,
            String decision,
            String successTitle,
            String successMessage,
            Runnable onSuccess
    ) {
        button.setDisable(true);
        AsyncTaskRunner.run(
                () -> new CAPAllotmentDAO()
                        .saveDecision(round, decision),
                saved -> {
                    button.setDisable(false);
                    if (Boolean.TRUE.equals(saved)) {
                        showMessage(successTitle, successMessage);
                        onSuccess.run();
                    } else {
                        showMessage(
                                "Error",
                                "Unable to save your decision. Please try again."
                        );
                    }
                },
                error -> {
                    button.setDisable(false);
                    error.printStackTrace();
                    showMessage(
                            "Error",
                            "Unable to save your decision. Please try again."
                    );
                }
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
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
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
                "-fx-cursor: hand;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        String normalStyle = button.getStyle();

        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) {
                button.setOpacity(0.88);
            }
        });

        button.setOnMouseExited(e -> {
            button.setOpacity(1.0);
            if (!button.isDisabled()) {
                button.setStyle(normalStyle);
            }
        });
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

        String normalStyle = button.getStyle();

        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) {
                button.setOpacity(0.88);
            }
        });

        button.setOnMouseExited(e -> {
            button.setOpacity(1.0);
            if (!button.isDisabled()) {
                button.setStyle(normalStyle);
            }
        });
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

        String normalStyle = button.getStyle();

        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) {
                button.setOpacity(0.88);
            }
        });

        button.setOnMouseExited(e -> {
            button.setOpacity(1.0);
            if (!button.isDisabled()) {
                button.setStyle(normalStyle);
            }
        });
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

        String normalStyle = button.getStyle();

        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) {
                button.setOpacity(0.88);
            }
        });

        button.setOnMouseExited(e -> {
            button.setOpacity(1.0);
            if (!button.isDisabled()) {
                button.setStyle(normalStyle);
            }
        });
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
