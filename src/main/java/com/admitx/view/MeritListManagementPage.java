package com.admitx.view;

import com.admitx.dao.MeritDAO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MeritListManagementPage {

    private static final String BG = "#0B100B";
    private static final String CARD = "#131A13";
    private static final String ROW = "#0F150F";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String TEXT = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        MeritDAO meritDAO =
                new MeritDAO();

        // =====================================================
        // FIRESTORE STATUS
        // =====================================================

        boolean provisionalGenerated =
                meritDAO.isProvisionalGenerated();

        boolean provisionalPublished =
                meritDAO.isProvisionalPublished();

        boolean finalGenerated =
                meritDAO.isFinalGenerated();

        boolean finalPublished =
                meritDAO.isFinalPublished();

        int eligibleStudents =
                meritDAO.getEligibleStudentCount();

        int provisionalPublishedCount =
                meritDAO.getPublishedMeritCount();

        int finalPublishedCount =
                meritDAO.getFinalPublishedCount();

        int pendingGrievances =
                meritDAO.getUnresolvedGrievanceCount();

        // =====================================================
        // HEADING
        // =====================================================

        Label title =
                new Label(
                        "Merit List Management"
                );

        title.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label subtitle =
                new Label(
                        "Generate, review and publish provisional and final merit lists."
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

        // =====================================================
        // CURRENT STATUS
        // =====================================================

        String badgeText;
        String currentStatusText;
        String statusDescriptionText;

        if (finalPublished) {

            badgeText =
                    "●  FINAL MERIT PUBLISHED";

            currentStatusText =
                    "Final Merit List Published";

            statusDescriptionText =
                    "The final merit list is now visible to students.";

        } else if (finalGenerated) {

            badgeText =
                    "●  FINAL MERIT READY";

            currentStatusText =
                    "Final Merit List Ready";

            statusDescriptionText =
                    "Final merit has been generated and is ready for publication.";

        } else if (provisionalPublished) {

            badgeText =
                    "●  PROVISIONAL MERIT PUBLISHED";

            currentStatusText =
                    "Grievance Review Phase";

            if (pendingGrievances > 0) {

                statusDescriptionText =
                        pendingGrievances
                                + " grievance(s) are still pending review.";

            } else {

                statusDescriptionText =
                        "All grievances are resolved. "
                                + "You can now generate the final merit list.";
            }

        } else if (provisionalGenerated) {

            badgeText =
                    "●  PROVISIONAL MERIT READY";

            currentStatusText =
                    "Provisional Merit List Ready";

            statusDescriptionText =
                    "The provisional merit list is ready for publication.";

        } else {

            badgeText =
                    "●  NOT GENERATED";

            currentStatusText =
                    "Merit Process Not Started";

            statusDescriptionText =
                    "Generate the provisional merit list from verified applications.";
        }

        Label statusBadge =
                new Label(
                        badgeText
                );

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
                        currentStatusText
                );

        currentValue.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label statusDescription =
                new Label(
                        statusDescriptionText
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
                        10,
                        statusBadge,
                        currentStatus,
                        currentValue,
                        statusDescription
                );

        statusCard.setPadding(
                new Insets(20)
        );

        styleCard(
                statusCard
        );

        // =====================================================
        // PROVISIONAL ACTIONS
        // =====================================================

        Button generateProvisional =
                createActionButton(
                        "Generate Provisional Merit List",
                        "Create merit rankings from verified student applications."
                );

        Button publishProvisional =
                createPrimaryActionButton(
                        "Publish Provisional Merit List",
                        "Make provisional merit ranks visible to students."
                );

        Button grievances =
                createActionButton(
                        "Review Grievances",
                        "Review, approve or reject student grievance requests."
                );

        // =====================================================
        // FINAL ACTIONS
        // =====================================================

        Button generateFinal =
                createActionButton(
                        "Generate Final Merit List",
                        "Generate final rankings after all grievances are resolved."
                );

        Button publishFinal =
                createPrimaryActionButton(
                        "Publish Final Merit List",
                        "Publish final merit ranks for students."
                );

        Button refresh =
                createActionButton(
                        "Refresh Merit Status",
                        "Reload merit and grievance information from Firestore."
                );

        // =====================================================
        // GENERATE PROVISIONAL
        // =====================================================

        generateProvisional.setOnAction(e -> {

            int eligible =
                    meritDAO.getEligibleStudentCount();

            if (eligible == 0) {

                message(
                        Alert.AlertType.WARNING,
                        "No Eligible Students",
                        "There are no verified student applications "
                                + "available for merit generation."
                );

                return;
            }

            Alert confirm =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirm.setTitle(
                    "Generate Provisional Merit"
            );

            confirm.setHeaderText(
                    "Generate provisional merit list?"
            );

            confirm.setContentText(
                    "Merit ranks will be generated using "
                            + "verified students' CET percentile."
            );

            if (
                    confirm.showAndWait()
                            .orElse(ButtonType.CANCEL)
                            != ButtonType.OK
            ) {

                return;
            }

            int generated =
                    meritDAO.generateProvisionalMeritList();

            if (generated > 0) {

                message(
                        Alert.AlertType.INFORMATION,
                        "Merit Generated",
                        "Provisional merit list generated successfully for "
                                + generated
                                + " student(s)."
                );

                reload();

            } else {

                message(
                        Alert.AlertType.ERROR,
                        "Generation Failed",
                        "Unable to generate the provisional merit list."
                );
            }
        });

        // =====================================================
        // PUBLISH PROVISIONAL
        // =====================================================

        publishProvisional.setOnAction(e -> {

            if (!meritDAO.isProvisionalGenerated()) {

                message(
                        Alert.AlertType.WARNING,
                        "Not Generated",
                        "Generate the provisional merit list first."
                );

                return;
            }

            Alert confirm =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirm.setTitle(
                    "Publish Provisional Merit"
            );

            confirm.setHeaderText(
                    "Publish provisional merit list?"
            );

            confirm.setContentText(
                    "Students will be able to view their provisional merit ranks."
            );

            if (
                    confirm.showAndWait()
                            .orElse(ButtonType.CANCEL)
                            != ButtonType.OK
            ) {

                return;
            }

            boolean success =
                    meritDAO.publishProvisionalMeritList();

            if (success) {

                message(
                        Alert.AlertType.INFORMATION,
                        "Published",
                        "Provisional merit list published successfully."
                );

                reload();

            } else {

                message(
                        Alert.AlertType.ERROR,
                        "Publish Failed",
                        "Unable to publish the provisional merit list."
                );
            }
        });

        // =====================================================
        // GRIEVANCES
        // =====================================================

        grievances.setOnAction(e ->

                Navigation.goTo(
                        GrievanceManagementPage.getScene()
                )
        );

        // =====================================================
        // GENERATE FINAL
        // =====================================================

        generateFinal.setOnAction(e -> {

            if (!meritDAO.isProvisionalPublished()) {

                message(
                        Alert.AlertType.WARNING,
                        "Provisional Merit Required",
                        "Publish the provisional merit list before generating final merit."
                );

                return;
            }

            int unresolved =
                    meritDAO.getUnresolvedGrievanceCount();

            if (unresolved > 0) {

                message(
                        Alert.AlertType.WARNING,
                        "Pending Grievances",
                        "There are "
                                + unresolved
                                + " pending grievance(s).\n\n"
                                + "Approve or reject all grievances "
                                + "before generating the final merit list."
                );

                return;
            }

            Alert confirm =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirm.setTitle(
                    "Generate Final Merit"
            );

            confirm.setHeaderText(
                    "Generate final merit list?"
            );

            confirm.setContentText(
                    "All grievances are resolved. "
                            + "Final rankings will now be generated."
            );

            if (
                    confirm.showAndWait()
                            .orElse(ButtonType.CANCEL)
                            != ButtonType.OK
            ) {

                return;
            }

            int generated =
                    meritDAO.generateFinalMeritList();

            if (generated > 0) {

                message(
                        Alert.AlertType.INFORMATION,
                        "Final Merit Generated",
                        "Final merit list generated successfully for "
                                + generated
                                + " student(s)."
                );

                reload();

            } else if (generated == -1) {

                message(
                        Alert.AlertType.WARNING,
                        "Pending Grievances",
                        "Resolve all pending grievances first."
                );

            } else if (generated == -2) {

                message(
                        Alert.AlertType.WARNING,
                        "Provisional Merit Required",
                        "Publish the provisional merit list first."
                );

            } else {

                message(
                        Alert.AlertType.ERROR,
                        "Generation Failed",
                        "Unable to generate the final merit list."
                );
            }
        });

        // =====================================================
        // PUBLISH FINAL
        // =====================================================

        publishFinal.setOnAction(e -> {

            if (!meritDAO.isFinalGenerated()) {

                message(
                        Alert.AlertType.WARNING,
                        "Final Merit Not Generated",
                        "Generate the final merit list before publishing."
                );

                return;
            }

            if (
                    meritDAO.getUnresolvedGrievanceCount()
                            > 0
            ) {

                message(
                        Alert.AlertType.WARNING,
                        "Pending Grievances",
                        "Resolve all pending grievances before publishing final merit."
                );

                return;
            }

            Alert confirm =
                    new Alert(
                            Alert.AlertType.CONFIRMATION
                    );

            confirm.setTitle(
                    "Publish Final Merit"
            );

            confirm.setHeaderText(
                    "Publish final merit list?"
            );

            confirm.setContentText(
                    "Students will be able to view their final merit ranks."
            );

            if (
                    confirm.showAndWait()
                            .orElse(ButtonType.CANCEL)
                            != ButtonType.OK
            ) {

                return;
            }

            boolean success =
                    meritDAO.publishFinalMeritList();

            if (success) {

                message(
                        Alert.AlertType.INFORMATION,
                        "Final Merit Published",
                        "Final merit list published successfully."
                );

                reload();

            } else {

                message(
                        Alert.AlertType.ERROR,
                        "Publish Failed",
                        "Unable to publish the final merit list."
                );
            }
        });

        // =====================================================
        // REFRESH
        // =====================================================

        refresh.setOnAction(e ->
                reload()
        );

        // =====================================================
        // BUTTON AVAILABILITY
        // =====================================================

        publishProvisional.setDisable(
                !provisionalGenerated ||
                provisionalPublished
        );

        grievances.setDisable(
                !provisionalPublished
        );

        generateFinal.setDisable(
                !provisionalPublished ||
                pendingGrievances > 0 ||
                finalPublished
        );

        publishFinal.setDisable(
                !finalGenerated ||
                finalPublished ||
                pendingGrievances > 0
        );

        // =====================================================
        // PROVISIONAL CARD
        // =====================================================

        VBox provisionalActions =
                new VBox(
                        12,
                        createSectionTitle(
                                "PROVISIONAL MERIT"
                        ),
                        generateProvisional,
                        publishProvisional,
                        grievances
                );

        provisionalActions.setPadding(
                new Insets(20)
        );

        styleCard(
                provisionalActions
        );

        // =====================================================
        // FINAL CARD
        // =====================================================

        VBox finalActions =
                new VBox(
                        12,
                        createSectionTitle(
                                "FINAL MERIT"
                        ),
                        generateFinal,
                        publishFinal,
                        refresh
                );

        finalActions.setPadding(
                new Insets(20)
        );

        styleCard(
                finalActions
        );

        HBox actionCards =
                new HBox(
                        16,
                        provisionalActions,
                        finalActions
                );

        HBox.setHgrow(
                provisionalActions,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                finalActions,
                Priority.ALWAYS
        );

        provisionalActions.setMaxWidth(
                Double.MAX_VALUE
        );

        finalActions.setMaxWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // STATS
        // =====================================================

        VBox statsCard =
                new VBox(
                        12,

                        createSectionTitle(
                                "MERIT OVERVIEW"
                        ),

                        createStatRow(
                                "Eligible Students",
                                String.valueOf(
                                        eligibleStudents
                                )
                        ),

                        createStatRow(
                                "Provisional Published",
                                String.valueOf(
                                        provisionalPublishedCount
                                )
                        ),

                        createStatRow(
                                "Pending Grievances",
                                String.valueOf(
                                        pendingGrievances
                                )
                        ),

                        createStatRow(
                                "Final Merit Generated",
                                finalGenerated
                                        ? "Yes"
                                        : "No"
                        ),

                        createStatRow(
                                "Final Published",
                                String.valueOf(
                                        finalPublishedCount
                                )
                        ),

                        createStatRow(
                                "Current Phase",
                                getCurrentPhase(
                                        provisionalGenerated,
                                        provisionalPublished,
                                        finalGenerated,
                                        finalPublished,
                                        pendingGrievances
                                )
                        )
                );

        statsCard.setPadding(
                new Insets(20)
        );

        styleCard(
                statsCard
        );

        // =====================================================
        // PROCESS FLOW
        // =====================================================

        VBox processCard =
                new VBox(
                        10,

                        createSectionTitle(
                                "MERIT PROCESS"
                        ),

                        createProcessRow(
                                "01",
                                "Generate Provisional Merit",
                                provisionalGenerated
                        ),

                        createProcessRow(
                                "02",
                                "Publish Provisional Merit",
                                provisionalPublished
                        ),

                        createProcessRow(
                                "03",
                                "Resolve Grievances",
                                provisionalPublished &&
                                        pendingGrievances == 0
                        ),

                        createProcessRow(
                                "04",
                                "Generate Final Merit",
                                finalGenerated
                        ),

                        createProcessRow(
                                "05",
                                "Publish Final Merit",
                                finalPublished
                        )
                );

        processCard.setPadding(
                new Insets(20)
        );

        styleCard(
                processCard
        );

        HBox information =
                new HBox(
                        16,
                        statsCard,
                        processCard
                );

        HBox.setHgrow(
                statsCard,
                Priority.ALWAYS
        );

        HBox.setHgrow(
                processCard,
                Priority.ALWAYS
        );

        statsCard.setMaxWidth(
                Double.MAX_VALUE
        );

        processCard.setMaxWidth(
                Double.MAX_VALUE
        );

        // =====================================================
        // NOTE
        // =====================================================

        String noteText;

        if (finalPublished) {

            noteText =
                    "Final merit is published. Students can now view "
                            + "their final merit rank and continue to the CAP process.";

        } else if (finalGenerated) {

            noteText =
                    "Final merit is generated. Review the status and publish "
                            + "the final merit list when ready.";

        } else if (
                provisionalPublished &&
                pendingGrievances > 0
        ) {

            noteText =
                    "Final merit cannot be generated because "
                            + pendingGrievances
                            + " grievance(s) are still pending.";

        } else if (provisionalPublished) {

            noteText =
                    "All grievances are resolved. "
                            + "The final merit list can now be generated.";

        } else {

            noteText =
                    "Only counsellor-verified applications are included "
                            + "in merit list generation.";
        }

        Label note =
                new Label(
                        noteText
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

        // =====================================================
        // ROOT
        // =====================================================

        VBox root =
                new VBox(
                        20,
                        heading,
                        statusCard,
                        actionCards,
                        information,
                        note
                );

        root.setPadding(
                new Insets(20)
        );

        root.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        // =====================================================
        // SCROLL
        // =====================================================

        ScrollPane scrollPane =
                new ScrollPane(
                        root
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

        BorderPane layout =
                CounsellorLayout.create(
                        "Merit List",
                        scrollPane
                );

        return new Scene(
                layout,
                1400,
                800
        );
    }

    // =========================================================
    // RELOAD
    // =========================================================

    private static void reload() {

        Navigation.goTo(
                MeritListManagementPage.getScene()
        );
    }

    // =========================================================
    // CURRENT PHASE
    // =========================================================

    private static String getCurrentPhase(
            boolean provisionalGenerated,
            boolean provisionalPublished,
            boolean finalGenerated,
            boolean finalPublished,
            int pendingGrievances
    ) {

        if (finalPublished) {

            return "Final Published";
        }

        if (finalGenerated) {

            return "Final Ready";
        }

        if (provisionalPublished) {

            if (pendingGrievances > 0) {

                return "Grievance Review";
            }

            return "Ready for Final Merit";
        }

        if (provisionalGenerated) {

            return "Provisional Ready";
        }

        return "Not Started";
    }

    // =========================================================
    // PROCESS ROW
    // =========================================================

    private static HBox createProcessRow(
            String number,
            String text,
            boolean complete
    ) {

        Label numberLabel =
                new Label(
                        number
                );

        numberLabel.setMinWidth(
                32
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color: "
                        + (
                        complete
                                ? "#1D2A10"
                                : ROW
                )
                        + ";" +
                "-fx-text-fill: "
                        + (
                        complete
                                ? LIME
                                : MUTED
                )
                        + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8px;" +
                "-fx-background-radius: 7px;"
        );

        Label textLabel =
                new Label(
                        text
                );

        textLabel.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label status =
                new Label(
                        complete
                                ? "Completed"
                                : "Pending"
                );

        status.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: "
                        + (
                        complete
                                ? LIME
                                : MUTED
                )
                        + ";"
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
                        numberLabel,
                        textLabel,
                        spacer,
                        status
                );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(8)
        );

        row.setStyle(
                "-fx-background-color: " + ROW + ";" +
                "-fx-background-radius: 7px;"
        );

        return row;
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
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        return label;
    }

    // =========================================================
    // ACTION BUTTON
    // =========================================================

    private static Button createActionButton(
            String title,
            String description
    ) {

        Label titleLabel =
                new Label(
                        title
                );

        titleLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + TEXT + ";"
        );

        Label descriptionLabel =
                new Label(
                        description
                );

        descriptionLabel.setWrapText(
                true
        );

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
                58
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

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private static Button createPrimaryActionButton(
            String title,
            String description
    ) {

        Button button =
                createActionButton(
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

    // =========================================================
    // STAT ROW
    // =========================================================

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

    // =========================================================
    // CARD STYLE
    // =========================================================

    private static void styleCard(
            Region region
    ) {

        region.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 10px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;"
        );
    }

    // =========================================================
    // MESSAGE
    // =========================================================

    private static void message(
            Alert.AlertType type,
            String title,
            String text
    ) {

        Alert alert =
                new Alert(
                        type
                );

        alert.setTitle(
                title
        );

        alert.setHeaderText(
                null
        );

        alert.setContentText(
                text
        );

        alert.showAndWait();
    }
}