package com.admitx.view;

import com.admitx.dao.ApplicationDAO;
import com.admitx.model.Student;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ApplicationStatusPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String RED = "#FF5A5A";
    private static final String ORANGE = "#F59E0B";

    public static Scene getScene() {

        // =====================================================
        // LOAD FIRESTORE DATA
        // =====================================================

        Student student =
                Student.getInstance();

        ApplicationDAO applicationDAO =
                new ApplicationDAO();

        String studentEmail =
                student.getEmail();

        String applicationStatus =
                applicationDAO.getApplicationStatus();

        String verificationStatus =
                applicationDAO.getVerificationStatus();

        boolean submitted =
                applicationDAO.isApplicationSubmitted();

        boolean verified =
                "Verified".equalsIgnoreCase(
                        verificationStatus
                );

        boolean rejected =
                "Rejected".equalsIgnoreCase(
                        verificationStatus
                );

        boolean pending =
                submitted &&
                !verified &&
                !rejected;

        // =====================================================
        // PAGE HEADING
        // =====================================================

        Label title =
                new Label(
                        "Application Status"
                );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Track the current status of your MHT CET CAP application."
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

        // =====================================================
        // APPLICATION IDENTIFIER
        // =====================================================

        Label applicationId =
                new Label(
                        "STUDENT EMAIL   "
                                + safe(studentEmail)
                );

        applicationId.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        // =====================================================
        // CURRENT STATUS
        // =====================================================

        Label statusLabel =
                new Label(
                        "CURRENT STATUS"
                );

        statusLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        String statusText;
        String statusDescription;
        String statusColor;
        String indicatorText;

        if (verified) {

            statusText =
                    "VERIFIED";

            statusDescription =
                    "Your application has been successfully verified "
                            + "by the counsellor.";

            statusColor =
                    LIME;

            indicatorText =
                    "✓";

        } else if (rejected) {

            statusText =
                    "REJECTED";

            statusDescription =
                    "Your application was rejected during verification. "
                            + "Please review the counsellor remarks.";

            statusColor =
                    RED;

            indicatorText =
                    "✕";

        } else if (pending) {

            statusText =
                    "UNDER VERIFICATION";

            statusDescription =
                    "Your application has been submitted successfully "
                            + "and is waiting for counsellor verification.";

            statusColor =
                    ORANGE;

            indicatorText =
                    "…";

        } else {

            statusText =
                    "DRAFT";

            statusDescription =
                    "Your application has not been submitted yet. "
                            + "Complete all required details and submit it.";

            statusColor =
                    MUTED;

            indicatorText =
                    "!";
        }

        Label status =
                new Label(
                        statusText
                );

        status.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + statusColor + ";"
        );

        Label description =
                new Label(
                        statusDescription
                );

        description.setWrapText(
                true
        );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox statusRow =
                new HBox(
                        15,
                        createStatusIndicator(
                                indicatorText,
                                statusColor
                        ),
                        new VBox(
                                5,
                                statusLabel,
                                status,
                                description
                        )
                );

        statusRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox statusCard =
                new VBox(
                        18,
                        applicationId,
                        statusRow
                );

        statusCard.setPadding(
                new Insets(22)
        );

        statusCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        // =====================================================
        // STATUS INFORMATION
        // =====================================================

        Label detailsTitle =
                new Label(
                        "APPLICATION DETAILS"
                );

        detailsTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        VBox details =
                new VBox(
                        0,
                        createDetailRow(
                                "Application Status",
                                applicationStatus
                        ),
                        createDetailRow(
                                "Verification Status",
                                verificationStatus
                        ),
                        createDetailRow(
                                "Student Email",
                                safe(studentEmail)
                        )
                );

        VBox detailsCard =
                new VBox(
                        15,
                        detailsTitle,
                        details
                );

        detailsCard.setPadding(
                new Insets(22)
        );

        detailsCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        // =====================================================
        // APPLICATION PROGRESS
        // =====================================================

        Label progressTitle =
                new Label(
                        "APPLICATION PROGRESS"
                );

        progressTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        boolean draftComplete =
                true;

        boolean submittedComplete =
                submitted;

        boolean verificationStarted =
                submitted;

        boolean verificationComplete =
                verified;

        VBox timeline =
                new VBox(
                        0,

                        createTimelineItem(
                                "01",
                                "Application Draft",
                                "Application created",
                                draftComplete,
                                true
                        ),

                        createTimelineItem(
                                "02",
                                "Application Submitted",
                                submitted
                                        ? "Application successfully submitted"
                                        : "Application not submitted yet",
                                submittedComplete,
                                true
                        ),

                        createTimelineItem(
                                "03",
                                "Under Verification",
                                rejected
                                        ? "Application verification completed with rejection"
                                        : verified
                                        ? "Counsellor verification completed"
                                        : submitted
                                        ? "Waiting for counsellor verification"
                                        : "Submit your application first",
                                verificationStarted,
                                true
                        ),

                        createTimelineItem(
                                "04",
                                rejected
                                        ? "Application Rejected"
                                        : "Application Verified",
                                rejected
                                        ? "Application was rejected by counsellor"
                                        : verified
                                        ? "Application verified successfully"
                                        : "Waiting for verification",
                                verificationComplete || rejected,
                                true
                        ),

                        createTimelineItem(
                                "05",
                                "Provisional Merit List",
                                verified
                                        ? "Waiting for merit list publication"
                                        : "Application verification required",
                                false,
                                true
                        ),

                        createTimelineItem(
                                "06",
                                "CAP Rounds",
                                "Seat allotment process",
                                false,
                                true
                        ),

                        createTimelineItem(
                                "07",
                                "Admission",
                                "Confirm your final allotted seat",
                                false,
                                false
                        )
                );

        VBox progressCard =
                new VBox(
                        18,
                        progressTitle,
                        timeline
                );

        progressCard.setPadding(
                new Insets(22)
        );

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        // =====================================================
        // STATUS MESSAGE
        // =====================================================

        Label note =
                new Label();

        note.setWrapText(
                true
        );

        if (verified) {

            note.setText(
                    "✓  Your application has been verified successfully. "
                            + "You are now eligible to continue with the "
                            + "next stages of the counselling process."
            );

            note.setStyle(
                    "-fx-background-color: #15210F;" +
                    "-fx-text-fill: #C8F59A;" +
                    "-fx-padding: 14px;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-border-color: #425A2D;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-font-size: 12px;"
            );

        } else if (rejected) {

            note.setText(
                    "✕  Your application was rejected during verification. "
                            + "Please check the counsellor remarks or contact "
                            + "the counselling authority."
            );

            note.setStyle(
                    "-fx-background-color: #251313;" +
                    "-fx-text-fill: #FFAAAA;" +
                    "-fx-padding: 14px;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-border-color: #633333;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-font-size: 12px;"
            );

        } else if (submitted) {

            note.setText(
                    "ⓘ  Your application is currently under verification. "
                            + "Keep checking your dashboard for updates."
            );

            note.setStyle(
                    "-fx-background-color: #211D10;" +
                    "-fx-text-fill: #E7CC80;" +
                    "-fx-padding: 14px;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-border-color: #5C4D20;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-font-size: 12px;"
            );

        } else {

            note.setText(
                    "ⓘ  Your application is still in Draft status. "
                            + "Complete the required information and submit it."
            );

            note.setStyle(
                    "-fx-background-color: #151B10;" +
                    "-fx-text-fill: #B9C5B2;" +
                    "-fx-padding: 14px;" +
                    "-fx-background-radius: 8px;" +
                    "-fx-border-color: #38452B;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-font-size: 12px;"
            );
        }

        // =====================================================
        // BUTTONS
        // =====================================================

        Button dashboardButton =
                new Button(
                        "←  Go to Dashboard"
                );

        styleSecondaryButton(
                dashboardButton
        );

        dashboardButton.setOnAction(e ->

                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        Button actionButton =
                new Button();

        stylePrimaryButton(
                actionButton
        );

        if (verified) {

            actionButton.setText(
                    "View Provisional Merit List  →"
            );

            actionButton.setOnAction(e ->

                    Navigation.goTo(
                            ProvisionalMeritPage.getScene()
                    )
            );

        } else if (submitted) {

            actionButton.setText(
                    "Refresh Status"
            );

            actionButton.setOnAction(e ->

                    Navigation.goTo(
                            ApplicationStatusPage.getScene()
                    )
            );

        } else {

            actionButton.setText(
                    "Continue Application  →"
            );

            actionButton.setOnAction(e ->

                    Navigation.goTo(
                            PersonalDetailsPage.getScene()
                    )
            );
        }

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        dashboardButton,
                        spacer,
                        actionButton
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        // =====================================================
        // CONTENT
        // =====================================================

        VBox content =
                new VBox(
                        22,
                        heading,
                        statusCard,
                        detailsCard,
                        progressCard,
                        note,
                        buttons
                );

        content.setPadding(
                new Insets(5)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
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
                        "Application Status",
                        scrollPane
                )
        );
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private static HBox createDetailRow(
            String name,
            String value
    ) {

        Label nameLabel =
                new Label(
                        name
                );

        nameLabel.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 12px;"
        );

        Label valueLabel =
                new Label(
                        safe(value)
                );

        valueLabel.setStyle(
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox row =
                new HBox(
                        10,
                        nameLabel,
                        spacer,
                        valueLabel
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        13,
                        4,
                        13,
                        4
                )
        );

        row.setStyle(
                "-fx-border-color: transparent transparent "
                        + BORDER
                        + " transparent;" +
                "-fx-border-width: 0 0 1 0;"
        );

        return row;
    }

    // =========================================================
    // STATUS INDICATOR
    // =========================================================

    private static Region createStatusIndicator(
            String text,
            String color
    ) {

        Label indicator =
                new Label(
                        text
                );

        indicator.setMinSize(
                55,
                55
        );

        indicator.setMaxSize(
                55,
                55
        );

        indicator.setAlignment(
                Pos.CENTER
        );

        indicator.setStyle(
                "-fx-background-color: "
                        + color
                        + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;"
        );

        return indicator;
    }

    // =========================================================
    // TIMELINE ITEM
    // =========================================================

    private static VBox createTimelineItem(
            String number,
            String title,
            String description,
            boolean completed,
            boolean line
    ) {

        Label numberLabel =
                new Label(
                        completed
                                ? "✓"
                                : number
                );

        numberLabel.setMinSize(
                34,
                34
        );

        numberLabel.setMaxSize(
                34,
                34
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color: "
                        + (
                        completed
                                ? LIME
                                : "#252D25"
                )
                        + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: "
                        + (
                        completed
                                ? "#0B100B"
                                : MUTED
                )
                        + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;"
        );

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + (
                        completed
                                ? WHITE
                                : MUTED
                )
                        + ";"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox text =
                new VBox(
                        4,
                        titleLabel,
                        descriptionLabel
                );

        HBox row =
                new HBox(
                        14,
                        numberLabel,
                        text
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(
                        8,
                        0,
                        8,
                        0
                )
        );

        VBox item =
                new VBox(
                        row
                );

        if (line) {

            Region connector =
                    new Region();

            connector.setPrefWidth(
                    2
            );

            connector.setPrefHeight(
                    18
            );

            connector.setTranslateX(
                    16
            );

            connector.setStyle(
                    "-fx-background-color: "
                            + (
                            completed
                                    ? LIME
                                    : BORDER
                    )
                            + ";"
            );

            item.getChildren().add(
                    connector
            );
        }

        return item;
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setPadding(
                new Insets(
                        0,
                        20,
                        0,
                        20
                )
        );

        button.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );
    }

    // =========================================================
    // SAFE
    // =========================================================

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
}