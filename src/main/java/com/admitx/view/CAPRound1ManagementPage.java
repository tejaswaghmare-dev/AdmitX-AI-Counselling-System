package com.admitx.view;

import com.admitx.dao.CAPAllotmentDAO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CAPRound1ManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        CAPAllotmentDAO capDAO =
                new CAPAllotmentDAO();

        Label title =
                new Label("CAP Round 1 Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Run seat allotment, publish results and review Round 1 reports."
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

        Label statusBadge =
                new Label("●  ROUND 1 READY");

        statusBadge.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 7 12 7 12;" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 18px;"
        );

        Label currentStatus =
                new Label("Current Status");

        currentStatus.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label currentValue =
                new Label("Ready for Seat Allotment");

        currentValue.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label statusDescription =
                new Label(
                        "Eligible students and locked option forms are ready for CAP Round 1 processing."
                );

        statusDescription.setWrapText(true);

        statusDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox statusCard =
                new VBox(
                        10,
                        statusBadge,
                        currentStatus,
                        currentValue,
                        statusDescription
                );

        statusCard.setPadding(
                new Insets(20)
        );

        statusCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        Button run =
                createPrimaryAction(
                        "Run Seat Allotment",
                        "Process students with locked preference forms."
                );

        Button publish =
                createAction(
                        "Publish Results",
                        "Make CAP Round 1 allotment results visible to students."
                );

        Button report =
                createAction(
                        "View Allotment Report",
                        "Review seat distribution and Round 1 allotment details."
                );

        /*
         * RUN ROUND 1
         */

        run.setOnAction(e -> {

            run.setDisable(true);

            currentValue.setText(
                    "Processing Seat Allotment..."
            );

            boolean success =
                    capDAO.runRound1Allotment();

            if (success) {

                currentValue.setText(
                        "Seat Allotment Completed"
                );

                statusBadge.setText(
                        "●  ALLOTMENT COMPLETED"
                );

                statusDescription.setText(
                        "CAP Round 1 allotment has been generated. Publish the results to make them visible to students."
                );

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Seat Allotment",
                        "CAP Round 1 seat allotment completed successfully."
                );

            } else {

                currentValue.setText(
                        "Allotment Not Completed"
                );

                showMessage(
                        Alert.AlertType.WARNING,
                        "Seat Allotment",
                        "No students were allotted seats.\n\nMake sure students have locked their preference forms."
                );
            }

            run.setDisable(false);
        });

        /*
         * PUBLISH ROUND 1
         */

        publish.setOnAction(e -> {

            publish.setDisable(true);

            boolean success =
                    capDAO.publishRound1();

            if (success) {

                currentValue.setText(
                        "Results Published"
                );

                statusBadge.setText(
                        "●  ROUND 1 PUBLISHED"
                );

                statusDescription.setText(
                        "CAP Round 1 results are now available to students."
                );

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Results Published",
                        "CAP Round 1 results have been published successfully."
                );

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Publish Failed",
                        "CAP Round 1 results could not be published."
                );
            }

            publish.setDisable(false);
        });

        /*
         * REPORT
         */

        report.setOnAction(e ->

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Allotment Report",
                        "Round 1 allotment report will be connected after the CAP flow is completed."
                )
        );

        VBox actionsCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "ROUND 1 ACTIONS"
                        ),
                        run,
                        publish,
                        report
                );

        actionsCard.setPadding(
                new Insets(20)
        );

        actionsCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        /*
         * FIRESTORE COUNTS
         */

        int lockedStudents =
                capDAO.getLockedPreferenceCount();

        VBox overviewCard =
                new VBox(
                        12,

                        createSectionTitle(
                                "ROUND 1 OVERVIEW"
                        ),

                        createStatRow(
                                "Eligible Students",
                                String.valueOf(
                                        lockedStudents
                                )
                        ),

                        createStatRow(
                                "Locked Option Forms",
                                String.valueOf(
                                        lockedStudents
                                )
                        ),

                        createStatRow(
                                "Available Seats",
                                "Not Connected"
                        ),

                        createStatRow(
                                "Round Status",
                                "Ready"
                        )
                );

        overviewCard.setPadding(
                new Insets(20)
        );

        overviewCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );

        HBox lower =
                new HBox(
                        16,
                        actionsCard,
                        overviewCard
                );

        HBox.setHgrow(
                actionsCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                overviewCard,
                Priority.ALWAYS
        );

        Label note =
                new Label(
                        "Only students who have locked their preference forms will be processed in CAP Round 1."
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

        VBox root =
                new VBox(
                        20,
                        heading,
                        statusCard,
                        lower,
                        note
                );

        root.setPadding(
                new Insets(5)
        );

        root.setStyle(
                "-fx-background-color: "
                        + BG
                        + ";"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "CAP Round 1",
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

    private static Button createAction(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(title);

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label descriptionLabel =
                new Label(description);

        descriptionLabel.setWrapText(true);

        descriptionLabel.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox text =
                new VBox(
                        3,
                        titleLabel,
                        descriptionLabel
                );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label arrow =
                new Label("→");

        arrow.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 16px;"
        );

        HBox graphic =
                new HBox(
                        10,
                        text,
                        spacer,
                        arrow
                );

        graphic.setAlignment(
                Pos.CENTER_LEFT
        );

        Button button =
                new Button();

        button.setGraphic(
                graphic
        );

        button.setMaxWidth(
                Double.MAX_VALUE
        );

        button.setPrefHeight(
                60
        );

        button.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private static Button createPrimaryAction(
            String title,
            String description
    ) {

        Button button =
                createAction(
                        title,
                        description
                );

        button.setStyle(
                "-fx-background-color: #18220F;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;" +
                "-fx-padding: 8 14 8 14;" +
                "-fx-cursor: hand;"
        );

        return button;
    }

    private static HBox createStatRow(
            String label,
            String value
    ) {

        Label labelText =
                new Label(label);

        labelText.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Label valueText =
                new Label(value);

        valueText.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        HBox row =
                new HBox(
                        labelText,
                        spacer,
                        valueText
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(10)
        );

        row.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 7px;"
        );

        return row;
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