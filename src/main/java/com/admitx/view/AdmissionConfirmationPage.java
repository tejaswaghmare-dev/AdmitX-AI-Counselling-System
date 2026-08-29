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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class AdmissionConfirmationPage {

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

        Student student =
                Student.getInstance();

        String studentEmail =
                student.getEmail();

        /*
         * CHECK LOGIN
         */

        if (
                studentEmail == null ||
                studentEmail.isBlank()
        ) {

            showMessage(
                    Alert.AlertType.WARNING,
                    "Login Required",
                    "Please login before viewing admission confirmation."
            );

            return StudentLoginPage.getScene();
        }

        /*
         * LOAD FINAL ROUND RESULT
         */

        CAPAllotment finalAllotment =
                capDAO.getStudentAllotment(3);

        /*
         * CHECK ROUND 3 RESULT
         */

        if (finalAllotment == null) {

            return createUnavailableScene(
                    "Final CAP Round 3 result is not available.",
                    "Your final allotment must be published before admission can be confirmed."
            );
        }

        /*
         * CHECK ADMISSION ACCEPTED
         */

        String decision =
                finalAllotment.getDecision();

        if (
                decision == null ||
                !"Admission Accepted"
                        .equalsIgnoreCase(
                                decision
                        )
        ) {

            return createUnavailableScene(
                    "Admission Not Yet Confirmed",
                    "You must accept your final CAP Round 3 allotment before viewing admission confirmation."
            );
        }

        /*
         * PAGE TITLE
         */

        Label title =
                new Label(
                        "Admission Confirmation"
                );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Your CAP admission has been successfully confirmed."
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
         * SUCCESS CARD
         */

        Label check =
                new Label("✓");

        check.setMinSize(
                62,
                62
        );

        check.setMaxSize(
                62,
                62
        );

        check.setAlignment(
                Pos.CENTER
        );

        check.setStyle(
                "-fx-background-color: " + LIME + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: #0B100B;" +
                "-fx-font-size: 30px;" +
                "-fx-font-weight: bold;"
        );

        Label success =
                new Label(
                        "Admission Successfully Confirmed"
                );

        success.setStyle(
                "-fx-font-size: 22px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label successMessage =
                new Label(
                        "Your final seat has been accepted and your admission confirmation is recorded."
                );

        successMessage.setWrapText(
                true
        );

        successMessage.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox successText =
                new VBox(
                        5,
                        success,
                        successMessage
                );

        HBox successRow =
                new HBox(
                        16,
                        check,
                        successText
                );

        successRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox successCard =
                new VBox(
                        successRow
                );

        successCard.setPadding(
                new Insets(22)
        );

        successCard.setStyle(
                "-fx-background-color: #18220F;" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 12px;"
        );

        /*
         * ADMISSION DETAILS
         */

        Label admissionTitle =
                createSectionTitle(
                        "ADMISSION DETAILS"
                );

        VBox admissionCard =
                new VBox(
                        12,

                        admissionTitle,

                        createDetail(
                                "Student",
                                safe(
                                        student.getUsername()
                                )
                        ),

                        createDetail(
                                "Student Email",
                                safe(
                                        studentEmail
                                )
                        ),

                        createDetail(
                                "Final Seat",
                                safe(
                                        finalAllotment.getStatus()
                                )
                        ),

                        createDetail(
                                "College",
                                safe(
                                        finalAllotment.getCollege()
                                )
                        ),

                        createDetail(
                                "Branch",
                                safe(
                                        finalAllotment.getBranch()
                                )
                        ),

                        createDetail(
                                "Allotted Preference",
                                "Preference No. "
                                        + finalAllotment
                                                .getPreferenceNumber()
                        ),

                        createDetail(
                                "CAP Round",
                                "Round 3"
                        ),

                        createDetail(
                                "Admission Status",
                                "Admission Accepted"
                        ),

                        createDetail(
                                "Reporting Status",
                                "Pending"
                        )
                );

        admissionCard.setPadding(
                new Insets(22)
        );

        admissionCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        /*
         * DOCUMENT SECTION
         */

        Label documentsTitle =
                createSectionTitle(
                        "ADMISSION DOCUMENTS"
                );

        Label documentsDescription =
                new Label(
                        "Your allotment letter and admission receipt will be available from this section."
                );

        documentsDescription.setWrapText(
                true
        );

        documentsDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Button allotmentLetter =
                new Button(
                        "Download Allotment Letter"
                );

        stylePrimaryButton(
                allotmentLetter
        );

        /*
         * PDF generation can be connected later.
         */

        allotmentLetter.setOnAction(e ->

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Allotment Letter",
                        "Allotment letter generation will be available here."
                )
        );

        Button receipt =
                new Button(
                        "Download Admission Receipt"
                );

        styleSecondaryButton(
                receipt
        );

        receipt.setOnAction(e ->

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Admission Receipt",
                        "Admission receipt generation will be available here."
                )
        );

        HBox downloadButtons =
                new HBox(
                        12,
                        allotmentLetter,
                        receipt
                );

        downloadButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox documentCard =
                new VBox(
                        12,
                        documentsTitle,
                        documentsDescription,
                        downloadButtons
                );

        documentCard.setPadding(
                new Insets(22)
        );

        documentCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        /*
         * REPORTING NOTE
         */

        Label note =
                new Label(
                        "Your seat has been accepted successfully. "
                                + "Reporting status is currently pending. "
                                + "Complete the required college reporting process to finish admission."
                );

        note.setWrapText(
                true
        );

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;"
        );

        /*
         * DASHBOARD BUTTON
         */

        Button dashboard =
                new Button(
                        "← Go to Dashboard"
                );

        styleSecondaryButton(
                dashboard
        );

        dashboard.setOnAction(e ->

                Navigation.goTo(
                        StudentDashboardPage.getScene()
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox bottomButtons =
                new HBox(
                        dashboard,
                        spacer
                );

        bottomButtons.setAlignment(
                Pos.CENTER_LEFT
        );

        /*
         * CONTENT
         */

        VBox content =
                new VBox(
                        22,
                        heading,
                        successCard,
                        admissionCard,
                        documentCard,
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
                        "Admission Confirmation",
                        scrollPane
                )
        );
    }

    // =========================================================
    // UNAVAILABLE PAGE
    // =========================================================

    private static Scene createUnavailableScene(
            String headingText,
            String descriptionText
    ) {

        Label title =
                new Label(
                        "Admission Confirmation"
                );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label status =
                new Label(
                        headingText
                );

        status.setWrapText(
                true
        );

        status.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description =
                new Label(
                        descriptionText
                );

        description.setWrapText(
                true
        );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox card =
                new VBox(
                        12,
                        status,
                        description
                );

        card.setPadding(
                new Insets(22)
        );

        card.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Button round3 =
                new Button(
                        "Go to CAP Round 3"
                );

        stylePrimaryButton(
                round3
        );

        round3.setOnAction(e ->

                Navigation.goTo(
                        CAPRound3Page.getScene()
                )
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

        HBox buttons =
                new HBox(
                        12,
                        dashboard,
                        round3
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        title,
                        card,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: "
                        + BG
                        + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Admission Confirmation",
                        content
                )
        );
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private static Label createSectionTitle(
            String text
    ) {

        Label label =
                new Label(
                        text
                );

        label.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private static VBox createDetail(
            String labelText,
            String value
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

        box.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        return box;
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
                        18,
                        0,
                        18
                )
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

    // =========================================================
    // SAFE STRING
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

    // =========================================================
    // MESSAGE
    // =========================================================

    private static void showMessage(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

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