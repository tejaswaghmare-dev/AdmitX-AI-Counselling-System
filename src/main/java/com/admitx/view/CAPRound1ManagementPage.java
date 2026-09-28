package com.admitx.view;

import com.admitx.dao.CAPAllotmentDAO;
import com.admitx.util.AsyncTaskRunner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import java.util.Optional;
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

        Scene loadingScene = createLoadingScene("CAP Round 1");

        AsyncTaskRunner.run(
                CAPRound1ManagementPage::loadOverviewData,
                data -> Navigation.goTo(
                        createManagementScene(data)
                ),
                error -> {
                    error.printStackTrace();
                    showMessage(
                            Alert.AlertType.ERROR,
                            "Loading Error",
                            "Could not load CAP Round 1 data."
                    );
                }
        );

        return loadingScene;
    }

    private static Scene createManagementScene(
            OverviewData overview
    ) {

        int lockedStudents = overview.lockedStudents;
        int eligibleStudents = overview.eligibleStudents;
        long availableSeats = overview.availableSeats;

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
                        6,
                        title,
                        subtitle
                );

        Label statusBadge =
                new Label("●  ROUND 1 READY");

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

        Button resetCycle =
                createAction(
                        "Start New CAP Cycle",
                        "Reset old CAP allotments and restore all seats before running CAP again."
                );

        /*
         * RUN ROUND 1
         */

        run.setOnAction(e -> {

            run.setDisable(true);

            currentValue.setText(
                    "Processing Seat Allotment..."
            );

            AsyncTaskRunner.run(
                    () -> new CAPAllotmentDAO()
                            .runRound1Allotment(),
                    success -> {

            if (Boolean.TRUE.equals(success)) {

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
                        "No students were allotted seats.\n\n"
                                + "Check that students are verified, their option forms are locked, "
                                + "their CET percentile is saved, and matching college/branch seats are available."
                );
            }

            run.setDisable(false);
                    },
                    error -> {
                        run.setDisable(false);
                        error.printStackTrace();
                        currentValue.setText("Allotment Not Completed");
                        showMessage(
                                Alert.AlertType.ERROR,
                                "Seat Allotment",
                                "Unable to run CAP Round 1 allotment."
                        );
                    }
            );
        });

        /*
         * PUBLISH ROUND 1
         */

        publish.setOnAction(e -> {

            publish.setDisable(true);

            AsyncTaskRunner.run(
                    () -> new CAPAllotmentDAO()
                            .publishRound1(),
                    success -> {

            if (Boolean.TRUE.equals(success)) {

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
                    },
                    error -> {
                        publish.setDisable(false);
                        error.printStackTrace();
                        showMessage(
                                Alert.AlertType.ERROR,
                                "Publish Failed",
                                "CAP Round 1 results could not be published."
                        );
                    }
            );
        });

        /*
         * START NEW CAP CYCLE
         */

        resetCycle.setOnAction(e -> {

            Alert confirm =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirm.setTitle(
                    "Start New CAP Cycle"
            );

            confirm.setHeaderText(
                    "Reset the completed CAP process?"
            );

            confirm.setContentText(
                    "This will delete old CAP allotments, reset Round 1/2/3 publication status, "
                            + "and restore available seats from each college intake.\n\n"
                            + "Student applications, merit list and locked preferences will NOT be deleted."
            );

            Optional<ButtonType> result =
                    confirm.showAndWait();

            if (result.isEmpty()
                    || result.get() != ButtonType.OK) {
                return;
            }

            resetCycle.setDisable(true);
            run.setDisable(true);
            publish.setDisable(true);

            currentValue.setText(
                    "Resetting CAP Cycle..."
            );

            statusDescription.setText(
                    "Restoring seats and clearing previous CAP round data."
            );

            AsyncTaskRunner.run(
                    () -> new CAPAllotmentDAO()
                            .resetCAPCycle(),
                    success -> {

                        resetCycle.setDisable(false);
                        run.setDisable(false);
                        publish.setDisable(false);

                        if (Boolean.TRUE.equals(success)) {

                            currentValue.setText(
                                    "Ready for Seat Allotment"
                            );

                            statusBadge.setText(
                                    "●  NEW CAP CYCLE READY"
                            );

                            statusDescription.setText(
                                    "Previous CAP data has been cleared and all college seats have been restored. Round 1 can be run again."
                            );

                            showMessage(
                                    Alert.AlertType.INFORMATION,
                                    "CAP Reset Complete",
                                    "A new CAP cycle is ready. You can now run CAP Round 1 again."
                            );

                        } else {

                            currentValue.setText(
                                    "CAP Reset Failed"
                            );

                            showMessage(
                                    Alert.AlertType.ERROR,
                                    "CAP Reset Failed",
                                    "The CAP cycle could not be reset. Please check the console for the Firebase error."
                            );
                        }
                    },
                    error -> {

                        resetCycle.setDisable(false);
                        run.setDisable(false);
                        publish.setDisable(false);

                        error.printStackTrace();

                        currentValue.setText(
                                "CAP Reset Failed"
                        );

                        showMessage(
                                Alert.AlertType.ERROR,
                                "CAP Reset Failed",
                                "The CAP cycle could not be reset."
                        );
                    }
            );
        });


        /*
         * REPORT
         */

        report.setOnAction(e ->

                Navigation.goTo(
                        ReportsPage.getScene()
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
                        report,
                        resetCycle
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
                                "ROUND 1 OVERVIEW"
                        ),

                        createStatRow(
                                "Eligible Students",
                                String.valueOf(
                                        eligibleStudents
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
                                String.valueOf(availableSeats)
                        ),

                        createStatRow(
                                "Round Status",
                                "Ready"
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
                        "Only students who have locked their preference forms will be processed in CAP Round 1."
                );

        note.setWrapText(true);

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
                createRoundNavigation(1);

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
                        "CAP Round 1",
                        root
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    private static OverviewData loadOverviewData() {

        CAPAllotmentDAO dao = new CAPAllotmentDAO();

        OverviewData data = new OverviewData();
        data.lockedStudents = dao.getLockedPreferenceCount();
        data.eligibleStudents = dao.getEligibleRound1StudentCount();
        data.availableSeats = dao.getTotalAvailableSeatCount();

        return data;
    }


    private static class OverviewData {
        private int lockedStudents;
        private int eligibleStudents;
        private long availableSeats;
    }


    private static Scene createLoadingScene(
            String pageTitle
    ) {

        ProgressIndicator progress = new ProgressIndicator();
        progress.setPrefSize(42, 42);

        Label label = new Label("Loading " + pageTitle + "...");
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
                CounsellorLayout.create(pageTitle, content),
                1400,
                800
        );
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

        descriptionLabel.setWrapText(true);

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
