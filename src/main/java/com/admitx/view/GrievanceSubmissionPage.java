package com.admitx.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class GrievanceSubmissionPage {

    private static File proofFile;

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";
    private static final String ORANGE = "#F97316";

    public static Scene getScene() {

        Label title =
                new Label("Raise Grievance");

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label subtitle =
                new Label(
                        "Submit your grievance regarding the provisional merit list."
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

        Label grievanceLabel =
                new Label("GRIEVANCE DETAILS");

        grievanceLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        TextArea grievance =
                new TextArea();

        grievance.setPromptText(
                "Describe your grievance clearly..."
        );

        grievance.setPrefRowCount(7);
        grievance.setWrapText(true);

        grievance.setStyle(
                "-fx-control-inner-background: " + CARD + ";" +
                "-fx-background-color: " + CARD + ";" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-prompt-text-fill: #667066;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 12px;"
        );

        VBox grievanceBox =
                new VBox(
                        10,
                        grievanceLabel,
                        grievance
                );

        grievanceBox.setPadding(
                new Insets(20)
        );

        grievanceBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label proofLabel =
                new Label("SUPPORTING PROOF");

        proofLabel.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label fileName =
                new Label("No proof uploaded");

        fileName.setStyle(
                "-fx-text-fill: " + MUTED + ";" +
                "-fx-font-size: 13px;"
        );

        Button upload =
                new Button("Choose File");

        upload.setPrefHeight(40);

        upload.setStyle(
                "-fx-background-color: #1B2615;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #3D5520;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        upload.setOnAction(e -> {

            FileChooser chooser =
                    new FileChooser();

            chooser.setTitle(
                    "Select Supporting Proof"
            );

            chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.jpg",
                            "*.jpeg",
                            "*.png"
                    )
            );

            Stage stage =
                    (Stage) upload
                            .getScene()
                            .getWindow();

            File file =
                    chooser.showOpenDialog(stage);

            if (file != null) {

                proofFile = file;

                fileName.setText(
                        file.getName()
                );

                fileName.setStyle(
                        "-fx-text-fill: " + LIME + ";" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;"
                );
            }
        });

        HBox uploadRow =
                new HBox(
                        15,
                        upload,
                        fileName
                );

        uploadRow.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox proofBox =
                new VBox(
                        10,
                        proofLabel,
                        uploadRow
                );

        proofBox.setPadding(
                new Insets(20)
        );

        proofBox.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-background-radius: 12px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;"
        );

        Label note =
                new Label(
                        "Supported formats: PDF, JPG, JPEG and PNG."
                );

        note.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Button back =
                new Button("← Back");

        back.setPrefHeight(42);

        back.setStyle(
                "-fx-background-color: #171F17;" +
                "-fx-text-fill: " + WHITE + ";" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: #344034;" +
                "-fx-border-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        back.setOnAction(e ->
                Navigation.goTo(
                        ProvisionalMeritPage.getScene()
                )
        );

        Button submit =
                new Button("Submit Grievance");

        submit.setPrefHeight(42);

        submit.setStyle(
                "-fx-background-color: " + ORANGE + ";" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
        );

        submit.setOnAction(e ->
                Navigation.goTo(
                        FinalMeritPage.getScene()
                )
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons =
                new HBox(
                        12,
                        back,
                        spacer,
                        submit
                );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content =
                new VBox(
                        22,
                        heading,
                        grievanceBox,
                        proofBox,
                        note,
                        buttons
                );

        content.setPadding(
                new Insets(30)
        );

        content.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Grievance",
                        content
                )
        );
    }

    public static File getProofFile() {

        return proofFile;
    }
}