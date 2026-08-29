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

public class CAPRound3Page {

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
                    Alert.AlertType.WARNING,
                    "Login Required",
                    "Please login before viewing CAP Round 3."
            );

            return StudentLoginPage.getScene();
        }

        /*
         * LOAD ROUND 3 RESULT
         */

        CAPAllotment allotment =
                capDAO.getStudentAllotment(3);

        /*
         * PAGE HEADING
         */

        Label title =
                new Label("CAP Round 3");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Review your final CAP allotment and proceed with admission."
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
         * RESULT NOT AVAILABLE
         */

        if (allotment == null) {

            Label waitingBadge =
                    new Label(
                            "●  FINAL RESULT NOT AVAILABLE"
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
                            "CAP Round 3 result is not available."
                    );

            waitingTitle.setStyle(
                    "-fx-font-size: 18px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: " + WHITE + ";"
            );

            Label waitingDescription =
                    new Label(
                            "Your final allotment will appear here after the counsellor runs and publishes CAP Round 3."
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
                            "CAP Round 3",
                            scrollPane
                    )
            );
        }

        /*
         * FINAL RESULT
         */

        Label finalBadge =
                new Label(
                        "●  FINAL ALLOTMENT PUBLISHED"
                );

        finalBadge.setStyle(
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
                        "FINAL SEAT ALLOTMENT"
                );

        GridPane details =
                new GridPane();

        details.setHgap(18);
        details.setVgap(15);

        /*
         * FIRESTORE DATA
         */

        addDetail(
                details,
                "Final Status",
                safe(
                        allotment.getStatus()
                ),
                0,
                0
        );

        addDetail(
                details,
                "CAP Round",
                "Round 3",
                1,
                0
        );

        addDetail(
                details,
                "Final College",
                safe(
                        allotment.getCollege()
                ),
                0,
                1
        );

        addDetail(
                details,
                "Final Branch",
                safe(
                        allotment.getBranch()
                ),
                1,
                1
        );

        addDetail(
                details,
                "Final Preference",
                "Preference No. "
                        + allotment.getPreferenceNumber(),
                0,
                2
        );

        addDetail(
                details,
                "Upgrade Status",
                safe(
                        allotment.getUpgradeStatus()
                ),
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
                        finalBadge,
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
         * ADMISSION SECTION
         */

        Label admissionTitle =
                createSectionTitle(
                        "NEXT STEP"
                );

        Label admissionHeading =
                new Label(
                        "Confirm Your Admission"
                );

        admissionHeading.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label admissionDescription =
                new Label(
                        "This is your final CAP allotment. Accept the allotted seat "
                                + "to continue to the admission confirmation process."
                );

        admissionDescription.setWrapText(
                true
        );

        admissionDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        /*
         * ACTUAL FINAL COLLEGE INFO
         */

        Label info =
                new Label(
                        "College: "
                                + safe(allotment.getCollege())
                                + "\n"
                                + "Branch: "
                                + safe(allotment.getBranch())
                );

        info.setWrapText(
                true
        );

        info.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-line-spacing: 5px;" +
                "-fx-padding: 14px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        /*
         * ACCEPT ADMISSION
         */

        Button accept =
                new Button(
                        "Accept Admission  ✓"
                );

        stylePrimaryButton(
                accept
        );

        accept.setOnAction(e -> {

            boolean saved =
                    capDAO.acceptFinalAdmission();

            if (saved) {

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Admission Accepted",
                        "Your final CAP Round 3 admission has been accepted successfully."
                );

                Navigation.goTo(
                        AdmissionConfirmationPage.getScene()
                );

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Admission Error",
                        "Unable to save your admission confirmation. Please try again."
                );
            }
        });

        /*
         * IF ALREADY ACCEPTED
         */

        String existingDecision =
                allotment.getDecision();

        if (
                existingDecision != null &&
                "Admission Accepted".equalsIgnoreCase(
                        existingDecision
                )
        ) {

            accept.setDisable(
                    true
            );

            accept.setText(
                    "Admission Accepted ✓"
            );

            admissionDescription.setText(
                    "Your final CAP Round 3 admission has already been accepted."
            );
        }

        VBox admissionCard =
                new VBox(
                        12,
                        admissionTitle,
                        admissionHeading,
                        admissionDescription,
                        info,
                        accept
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

        Label note =
                new Label(
                        "Once you accept the final allotment, continue with the required admission confirmation and reporting process."
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
                        admissionCard,
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
                        "CAP Round 3",
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

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(
                42
        );

        button.setMaxWidth(
                Double.MAX_VALUE
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

    private static void showMessage(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert =
                new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}