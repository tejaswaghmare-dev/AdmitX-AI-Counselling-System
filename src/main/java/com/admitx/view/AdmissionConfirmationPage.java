package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

        Label title =
                new Label("Admission Confirmation");

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
                        "Your final seat has been accepted and your admission record has been created."
                );

        successMessage.setWrapText(true);

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

        Label admissionTitle =
                createSectionTitle(
                        "ADMISSION DETAILS"
                );

        VBox admissionCard =
                new VBox(
                        12,
                        admissionTitle,

                        createDetail(
                                "Final Seat",
                                "Allotted"
                        ),

                        createDetail(
                                "College",
                                "Vishwakarma Institute of Technology"
                        ),

                        createDetail(
                                "Branch",
                                "Information Technology"
                        ),

                        createDetail(
                                "Reporting Status",
                                "Pending"
                        ),

                        createDetail(
                                "Admission Status",
                                "Complete"
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

        Label documentsTitle =
                createSectionTitle(
                        "ADMISSION DOCUMENTS"
                );

        Label documentsDescription =
                new Label(
                        "You can download the dummy allotment letter and admission receipt below."
                );

        documentsDescription.setWrapText(true);

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

        allotmentLetter.setOnAction(e ->
                showMessage(
                        "Download",
                        "Dummy Allotment Letter downloaded."
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
                        "Download",
                        "Dummy Admission Receipt downloaded."
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

        Label note =
                new Label(
                        "Reporting status is currently pending. Follow the college reporting instructions to complete the final admission process."
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
                new Button("← Go to Dashboard");

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
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Admission Confirmation",
                        content
                )
        );
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

    private static VBox createDetail(
            String labelText,
            String value
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

        valueLabel.setWrapText(true);

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

    private static void stylePrimaryButton(
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
                "-fx-background-color: " + LIME + ";" +
                "-fx-text-fill: #0B100B;" +
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