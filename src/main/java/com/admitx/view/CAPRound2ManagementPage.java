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

public class CAPRound2ManagementPage {

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
                    return new Round2Data(
                            dao.getNextRoundEligibleCount(1),
                            dao.getRound1FrozenCount(),
                            dao.getTotalAvailableSeatCount(),
                            dao.getRoundAllotmentCount(2),
                            dao.isRoundPublished(2)
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
                            "Could not load CAP Round 2 data."
                    );
                }
        );

        return loadingScene;
    }

    private static Scene createManagementScene(
            Round2Data pageData
    ) {

        int bettermentStudents = pageData.bettermentStudents();
        int frozenStudents = pageData.frozenStudents();
        long vacantSeats = pageData.vacantSeats();
        int generatedAllotments = pageData.generatedAllotments();
        boolean roundPublished = pageData.roundPublished();

        Label title =
                new Label("CAP Round 2 Management");

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Process betterment requests and publish CAP Round 2 results."
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
                                ? "●  ROUND 2 PUBLISHED"
                                : generatedAllotments > 0
                                        ? "●  ROUND 2 COMPLETED"
                                        : "●  ROUND 2 READY"
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
                new Label("Current Status");

        currentStatus.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Label currentValue =
                new Label(
                        roundPublished
                                ? "Round 2 Results Published"
                                : generatedAllotments > 0
                                        ? "Ready to Publish"
                                        : "Ready for Betterment"
                );

        currentValue.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label statusDescription =
                new Label(
                        roundPublished
                                ? "CAP Round 2 results are published and visible to eligible students."
                                : generatedAllotments > 0
                                        ? "Round 2 allotments are generated. Publish the results to students."
                                        : "Students requesting betterment and students not allotted a seat in Round 1 are eligible for CAP Round 2."
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

        /*
         * ROUND 2 ACTIONS
         */

        Button run =
                createPrimaryAction(
                        "Run Betterment",
                        "Process Round 1 betterment requests for CAP Round 2."
                );

        Button publish =
                createAction(
                        "Publish Results",
                        "Make CAP Round 2 betterment results visible to students."
                );

        run.setDisable(
                roundPublished
                        || generatedAllotments > 0
                        || bettermentStudents <= 0
        );

        publish.setDisable(
                roundPublished
                        || generatedAllotments <= 0
        );

        /*
         * RUN BETTERMENT
         */

        run.setOnAction(e -> {

            run.setDisable(true);

            currentValue.setText(
                    "Processing Betterment..."
            );

            AsyncTaskRunner.run(
                    () -> new CAPAllotmentDAO().runRound2Allotment(),
                    success -> {

            if (Boolean.TRUE.equals(success)) {

                currentValue.setText(
                        "Betterment Completed"
                );

                statusBadge.setText(
                        "●  ROUND 2 COMPLETED"
                );

                statusDescription.setText(
                        "CAP Round 2 betterment processing is complete. Publish the results to students."
                );

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Round 2 Betterment",
                        "CAP Round 2 betterment completed successfully."
                );

                Navigation.goTo(getScene());

            } else {

                currentValue.setText(
                        "No Betterment Processed"
                );

                showMessage(
                        Alert.AlertType.WARNING,
                        "Round 2 Betterment",
                        "No students were processed.\n\nMake sure Round 1 has eligible betterment or unallotted students."
                );
            }

            run.setDisable(false);
                    },
                    error -> {
                        run.setDisable(false);
                        error.printStackTrace();
                        currentValue.setText("No Betterment Processed");
                        showMessage(
                                Alert.AlertType.ERROR,
                                "Round 2 Betterment",
                                "Unable to run CAP Round 2 betterment."
                        );
                    }
            );
        });

        /*
         * PUBLISH ROUND 2
         */

        publish.setOnAction(e -> {

            publish.setDisable(true);

            AsyncTaskRunner.run(
                    () -> new CAPAllotmentDAO().publishRound2(),
                    success -> {

            if (Boolean.TRUE.equals(success)) {

                currentValue.setText(
                        "Round 2 Results Published"
                );

                statusBadge.setText(
                        "●  ROUND 2 PUBLISHED"
                );

                statusDescription.setText(
                        "CAP Round 2 results are now visible to eligible students."
                );

                showMessage(
                        Alert.AlertType.INFORMATION,
                        "Results Published",
                        "CAP Round 2 results have been published successfully."
                );

                Navigation.goTo(getScene());

            } else {

                showMessage(
                        Alert.AlertType.ERROR,
                        "Publish Failed",
                        "CAP Round 2 results could not be published."
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
                                "CAP Round 2 results could not be published."
                        );
                    }
            );
        });

        VBox actionsCard =
                new VBox(
                        12,
                        createSectionTitle(
                                "ROUND 2 ACTIONS"
                        ),
                        run,
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
                                "ROUND 2 OVERVIEW"
                        ),

                        createStatRow(
                                "Students Eligible for Betterment",
                                String.valueOf(
                                        bettermentStudents
                                )
                        ),

                        createStatRow(
                                "Round 1 Frozen Seats",
                                String.valueOf(
                                        frozenStudents
                                )
                        ),

                        createStatRow(
                                "Vacant Seats",
                                String.valueOf(vacantSeats)
                        ),

                        createStatRow(
                                "Generated Allotments",
                                String.valueOf(generatedAllotments)
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
                        "Round 2 includes students who requested betterment and eligible students who were not allotted a seat in Round 1."
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
                createRoundNavigation(2);

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
                "-fx-background-color: " + BG + ";"
        );

        BorderPane layout =
                CounsellorLayout.create(
                        "CAP Round 2",
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

        Label label = new Label("Loading CAP Round 2...");
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
                CounsellorLayout.create("CAP Round 2", content),
                1400,
                800
        );
    }

    private record Round2Data(
            int bettermentStudents,
            int frozenStudents,
            long vacantSeats,
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
