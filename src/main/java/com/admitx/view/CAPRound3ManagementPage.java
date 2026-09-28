package com.admitx.view;

import com.admitx.dao.CAPAllotmentDAO;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CAPRound3ManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Scene loadingScene = createLoadingScene();

        AsyncTaskRunner.run(
                () -> {
                    CAPAllotmentDAO dao = new CAPAllotmentDAO();
                    return new Round3Data(
                            dao.getNextRoundEligibleCount(2),
                            dao.getTotalAvailableSeatCount(),
                            dao.getFinalAdmissionCount(),
                            dao.getRoundAllotmentCount(3),
                            dao.isRoundPublished(3)
                    );
                },
                pageData -> Navigation.goTo(
                        createManagementScene(pageData)
                ),
                error -> {
                    error.printStackTrace();
                    showMessage(
                            Alert.AlertType.ERROR,
                            "Loading Error",
                            "Could not load CAP Round 3 data."
                    );
                }
        );

        return loadingScene;
    }

    private static Scene createManagementScene(
            Round3Data pageData
    ) {

        int remainingStudents = pageData.remainingStudents();
        long availableSeats = pageData.availableSeats();
        int confirmedAdmissions = pageData.confirmedAdmissions();
        int generatedAllotments = pageData.generatedAllotments();
        boolean roundPublished = pageData.roundPublished();

        Label title =
                new Label("CAP Round 3 Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Process the final CAP allotment and publish Round 3 results."
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

        Label statusBadge =
                new Label(
                        roundPublished
                                ? "●  ROUND 3 PUBLISHED"
                                : generatedAllotments > 0
                                        ? "●  ROUND 3 COMPLETED"
                                        : "●  ROUND 3 READY"
                );

        statusBadge.setStyle(
                "-fx-background-color: #1D2A10;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 7 12 7 12;" +
                "-fx-background-radius: 18px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 18px;"
        );

        Label currentStatus =
                new Label(
                        "Current Status"
                );

        currentStatus.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label currentValue =
                new Label(
                        roundPublished
                                ? "Final Results Published"
                                : generatedAllotments > 0
                                        ? "Ready to Publish"
                                        : "Ready for Final Allotment"
                );

        currentValue.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label statusDescription =
                new Label(
                        roundPublished
                                ? "CAP Round 3 final results are published and visible to students."
                                : generatedAllotments > 0
                                        ? "Round 3 final allotments are generated. Publish the results to students."
                                        : "Students requesting betterment and students still unallotted after Round 2 are ready for final Round 3 processing."
                );

        statusDescription.setWrapText(
                true
        );

        statusDescription.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox statusCard =
                new VBox(
                        12,
                        statusBadge,
                        currentStatus,
                        currentValue,
                        statusDescription
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

        /*
         * ROUND 3 ACTIONS
         */

        Button finalAllotment =
                createPrimaryAction(
                        "Run Final Allotment",
                        "Process Round 2 betterment students and finalize CAP Round 3 seats."
                );

        Button publish =
                createAction(
                        "Publish Results",
                        "Make the final CAP Round 3 results visible to students."
                );

        finalAllotment.setDisable(
                roundPublished
                        || generatedAllotments > 0
                        || remainingStudents <= 0
        );

        publish.setDisable(
                roundPublished
                        || generatedAllotments <= 0
        );

        /*
         * RUN FINAL ALLOTMENT
         */

        finalAllotment.setOnAction(e -> {

            finalAllotment.setDisable(
                    true
            );

            currentValue.setText(
                    "Processing Final Allotment..."
            );

            AsyncTaskRunner.run(
                    () -> new CAPAllotmentDAO().runRound3Allotment(),
                    success -> {

            if (Boolean.TRUE.equals(success)) {

                currentValue.setText(
                        "Final Allotment Completed"
                );

                statusBadge.setText(
                        "●  ROUND 3 COMPLETED"
                );

                statusDescription.setText(
                        "CAP Round 3 final allotment has been generated. Publish results to make them visible to students."
                );

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Final Allotment",
                        "CAP Round 3 final allotment completed successfully."
                );

                Navigation.goTo(getScene());

            } else {

                currentValue.setText(
                        "No Final Allotment Processed"
                );

                showMessage(
                        Alert.AlertType.WARNING,
                        "Final Allotment",
                        "No students were processed.\n\nMake sure Round 2 has eligible betterment or unallotted students."
                );
            }

            finalAllotment.setDisable(false);
                    },
                    error -> {
                        finalAllotment.setDisable(false);
                        error.printStackTrace();
                        currentValue.setText("No Final Allotment Processed");
                        showMessage(
                                Alert.AlertType.ERROR,
                                "Final Allotment",
                                "Unable to run CAP Round 3 allotment."
                        );
                    }
            );
        });

        /*
         * PUBLISH ROUND 3
         */

        publish.setOnAction(e -> {

            publish.setDisable(
                    true
            );

            AsyncTaskRunner.run(
                    () -> new CAPAllotmentDAO().publishRound3(),
                    success -> {

            if (Boolean.TRUE.equals(success)) {

                currentValue.setText(
                        "Final Results Published"
                );

                statusBadge.setText(
                        "●  ROUND 3 PUBLISHED"
                );

                statusDescription.setText(
                        "CAP Round 3 final allotment results are now available to students."
                );

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Results Published",
                        "CAP Round 3 results have been published successfully."
                );

                Navigation.goTo(getScene());

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Publish Failed",
                        "CAP Round 3 results could not be published."
                );
            }

            publish.setDisable(false);
                    },
                    error -> {
                        publish.setDisable(false);
                        error.printStackTrace();
                        showMessage(
                                Alert.AlertType.ERROR,
                                "Publish Failed",
                                "CAP Round 3 results could not be published."
                        );
                    }
            );
        });

        VBox actionsCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "ROUND 3 ACTIONS"
                        ),
                        finalAllotment,
                        publish
                );

        actionsCard.setPadding(
                new Insets(22)
        );

        actionsCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        /*
         * FIRESTORE COUNTS
         */

        VBox overviewCard =
                new VBox(
                        12,

                        createSectionTitle(
                                "ROUND 3 OVERVIEW"
                        ),

                        createStatRow(
                                "Students Remaining",
                                String.valueOf(
                                        remainingStudents
                                )
                        ),

                        createStatRow(
                                "Available Seats",
                                String.valueOf(availableSeats)
                        ),

                        createStatRow(
                                "Generated Final Allotments",
                                String.valueOf(generatedAllotments)
                        ),

                        createStatRow(
                                "Confirmed Admissions",
                                String.valueOf(confirmedAdmissions)
                        ),

                        createStatRow(
                                "Round Status",
                                roundPublished
                                        ? "Published"
                                        : generatedAllotments > 0
                                                ? "Generated"
                                                : "Ready"
                        )
                );

        overviewCard.setPadding(
                new Insets(22)
        );

        overviewCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
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
                        "Round 3 is final. It processes Round 2 betterment requests and students who are still unallotted."
                );

        note.setWrapText(
                true
        );

        note.setStyle(
                "-fx-background-color: #151B10;" +
                "-fx-text-fill: #B9C5B2;" +
                "-fx-font-size: 12px;" +
                "-fx-padding: 16px;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #38452B;" +
                "-fx-border-radius: 8px;"
        );

        HBox roundNavigation =
                createRoundNavigation(3);

        VBox root =
                new VBox(
                        22,
                        roundNavigation,
                        heading,
                        statusCard,
                        lower,
                        note
                );

        root.setPadding(
                new Insets(18, 24, 30, 24)
        );

        root.setFillWidth(true);
        root.setMaxWidth(Double.MAX_VALUE);

        root.setStyle(
                "-fx-background-color: "
                        + BG
                        + ";"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "CAP Round 3",
                        root
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static Scene createLoadingScene() {

        ProgressIndicator progress = new ProgressIndicator();
        progress.setPrefSize(42, 42);

        Label label = new Label("Loading CAP Round 3...");
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
                CounsellorLayout.create("CAP Round 3", content),
                1400,
                800
        );
    }

    private record Round3Data(
            int remainingStudents,
            long availableSeats,
            int confirmedAdmissions,
            int generatedAllotments,
            boolean roundPublished
    ) {
    }


    private static HBox createRoundNavigation(
            int activeRound
    ) {

        Button round1 = createRoundTab(
                "CAP Round 1",
                activeRound == 1
        );

        Button round2 = createRoundTab(
                "CAP Round 2",
                activeRound == 2
        );

        Button round3 = createRoundTab(
                "CAP Round 3",
                activeRound == 3
        );

        round1.setOnAction(e -> {
            if (activeRound != 1) {
                Navigation.goTo(
                        CAPRound1ManagementPage.getScene()
                );
            }
        });

        round2.setOnAction(e -> {
            if (activeRound != 2) {
                Navigation.goTo(
                        CAPRound2ManagementPage.getScene()
                );
            }
        });

        round3.setOnAction(e -> {
            if (activeRound != 3) {
                Navigation.goTo(
                        CAPRound3ManagementPage.getScene()
                );
            }
        });

        HBox tabs =
                new HBox(
                        8,
                        round1,
                        round2,
                        round3
                );

        tabs.setAlignment(
                Pos.CENTER_LEFT
        );

        tabs.setPadding(
                new Insets(4, 0, 2, 0)
        );

        return tabs;
    }

    private static Button createRoundTab(
            String text,
            boolean active
    ) {

        Button button =
                new Button(text);

        button.setPrefHeight(40);
        button.setMinWidth(135);

        String activeStyle =
                "-fx-background-color:" + LIME + ";" +
                "-fx-text-fill:#0B100B;" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:8px;" +
                "-fx-border-radius:8px;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String normalStyle =
                "-fx-background-color:" + ROW + ";" +
                "-fx-text-fill:" + TEXT + ";" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:" + BORDER + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        String hoverStyle =
                "-fx-background-color:#1A241A;" +
                "-fx-text-fill:" + LIME + ";" +
                "-fx-font-size:12px;" +
                "-fx-font-weight:bold;" +
                "-fx-border-color:" + LIME + ";" +
                "-fx-border-width:1px;" +
                "-fx-border-radius:8px;" +
                "-fx-background-radius:8px;" +
                "-fx-padding:0 18 0 18;" +
                "-fx-cursor:hand;";

        button.setStyle(
                active
                        ? activeStyle
                        : normalStyle
        );

        if (!active) {
            button.setOnMouseEntered(
                    e -> button.setStyle(hoverStyle)
            );

            button.setOnMouseExited(
                    e -> button.setStyle(normalStyle)
            );
        }

        return button;
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

        descriptionLabel.setWrapText(
                true
        );

        descriptionLabel.setStyle(
                "-fx-font-size: 11px;" +
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
                new Label(
                        "→"
                );

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

        String normalStyle = button.getStyle();

        button.setOnMouseEntered(e -> {
            if (!button.isDisabled()) {
                button.setStyle(
                        "-fx-background-color:#172017;" +
                        "-fx-border-color:" + LIME + ";" +
                        "-fx-border-width:1px;" +
                        "-fx-border-radius:9px;" +
                        "-fx-background-radius:9px;" +
                        "-fx-padding:8 14 8 14;" +
                        "-fx-cursor:hand;"
                );
            }
        });

        button.setOnMouseExited(e -> {
            if (!button.isDisabled()) {
                button.setStyle(normalStyle);
            }
        });

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
                new Label(
                        label
                );

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
                new Label(
                        value
                );

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
