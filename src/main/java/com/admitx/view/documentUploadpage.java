package com.admitx.view;

import com.admitx.model.ApplicationData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Map;

public class DocumentUploadPage {

    private static final ApplicationData data =
            ApplicationData.getInstance();

    private static final String BG = "#0B100B";
    private static final String CARD = "#141B14";
    private static final String BORDER = "#293529";
    private static final String LIME = "#B7FF00";
    private static final String WHITE = "#F5F7F2";
    private static final String MUTED = "#9AA59A";

    public static Scene getScene() {

        Label title = new Label(
                "Document Upload"
        );

        title.setStyle(
                "-fx-font-size: 26px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label description = new Label(
                "Upload the required documents for your MHT CET CAP application."
        );

        description.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox heading = new VBox(
                6,
                title,
                description
        );

        Label progressTitle = new Label(
                "APPLICATION PROGRESS"
        );

        progressTitle.setStyle(
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        HBox progress = new HBox(
                8,

                createStep("1", "Personal", true),
                createLine(true),

                createStep("2", "Address", true),
                createLine(true),

                createStep("3", "Academic", true),
                createLine(true),

                createStep("4", "University", true),
                createLine(true),

                createStep("5", "Reservation", true),
                createLine(true),

                createStep("6", "Documents", true),
                createLine(true),

                createStep("7", "Preview", false)
        );

        progress.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox progressCard = new VBox(
                10,
                progressTitle,
                progress
        );

        progressCard.setPadding(
                new Insets(16)
        );

        progressCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        Label uploadTitle =
                new Label("REQUIRED DOCUMENTS");

        uploadTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + LIME + ";"
        );

        Label uploadDescription =
                new Label(
                        "Accepted formats: PDF, JPG, JPEG and PNG"
                );

        uploadDescription.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        VBox documents = new VBox(
                10
        );

        documents.getChildren().add(
                createDocumentRow("SSC Marksheet")
        );

        documents.getChildren().add(
                createDocumentRow("HSC Marksheet")
        );

        documents.getChildren().add(
                createDocumentRow("CET Scorecard")
        );

        documents.getChildren().add(
                createDocumentRow("Leaving Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Domicile")
        );

        documents.getChildren().add(
                createDocumentRow("Caste Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Validity")
        );

        documents.getChildren().add(
                createDocumentRow("NCL")
        );

        documents.getChildren().add(
                createDocumentRow("Income Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("EWS Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Gap Certificate")
        );

        documents.getChildren().add(
                createDocumentRow("Photo")
        );

        documents.getChildren().add(
                createDocumentRow("Signature")
        );

        VBox documentCard = new VBox(
                16,
                uploadTitle,
                uploadDescription,
                documents
        );

        documentCard.setPadding(
                new Insets(22)
        );

        documentCard.setStyle(
                "-fx-background-color: " + CARD + ";" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 12px;" +
                "-fx-background-radius: 12px;"
        );

        Button backButton =
                new Button("←  Back");

        styleSecondaryButton(
                backButton
        );

        Button nextButton =
                new Button("Save & Continue  →");

        stylePrimaryButton(
                nextButton
        );

        backButton.setOnAction(e ->
                Navigation.goTo(
                        ReservationDetailsPage.getScene()
                )
        );

        nextButton.setOnAction(e ->
                Navigation.goTo(
                        PreviewApplicationPage.getScene()
                )
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox buttons = new HBox(
                12,
                backButton,
                spacer,
                nextButton
        );

        buttons.setAlignment(
                Pos.CENTER_LEFT
        );

        VBox content = new VBox(
                22,
                heading,
                progressCard,
                documentCard,
                buttons
        );

        content.setPadding(
                new Insets(5)
        );

        ScrollPane scrollPane =
                new ScrollPane(content);

        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(
                ScrollPane.ScrollBarPolicy.NEVER
        );

        scrollPane.setStyle(
                "-fx-background: " + BG + ";" +
                "-fx-background-color: " + BG + ";"
        );

        BorderPane page =
                new BorderPane();

        page.setCenter(
                scrollPane
        );

        page.setStyle(
                "-fx-background-color: " + BG + ";"
        );

        return new Scene(
                StudentLayout.create(
                        "Document Upload",
                        page
                )
        );
    }

    private static HBox createDocumentRow(
            String documentName
    ) {

        Label documentLabel =
                new Label(documentName);

        documentLabel.setPrefWidth(220);

        documentLabel.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: " + WHITE + ";"
        );

        Label fileName =
                new Label("No file selected");

        fileName.setMaxWidth(
                Double.MAX_VALUE
        );

        fileName.setStyle(
                "-fx-font-size: 11px;" +
                "-fx-text-fill: " + MUTED + ";"
        );

        Region spacer =
                new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        Button uploadButton =
                new Button("Choose File");

        styleUploadButton(
                uploadButton
        );

        uploadButton.setOnAction(e -> {

            FileChooser fileChooser =
                    new FileChooser();

            fileChooser.setTitle(
                    "Select " + documentName
            );

            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                            "Documents",
                            "*.pdf",
                            "*.jpg",
                            "*.jpeg",
                            "*.png"
                    )
            );

            Stage stage =
                    (Stage) uploadButton
                            .getScene()
                            .getWindow();

            File file =
                    fileChooser.showOpenDialog(stage);

            if (file != null) {

                data.getUploadedDocuments().put(
                        documentName,
                        file
                );

                fileName.setText(
                        file.getName()
                );

                fileName.setStyle(
                        "-fx-font-size: 11px;" +
                        "-fx-text-fill: " + LIME + ";" +
                        "-fx-font-weight: bold;"
                );
            }
        });

        HBox row = new HBox(
                15,
                documentLabel,
                fileName,
                spacer,
                uploadButton
        );

        row.setAlignment(
                Pos.CENTER_LEFT
        );

        row.setPadding(
                new Insets(13, 15, 13, 15)
        );

        row.setMaxWidth(
                Double.MAX_VALUE
        );

        row.setStyle(
                "-fx-background-color: #0F150F;" +
                "-fx-background-radius: 8px;" +
                "-fx-border-color: " + BORDER + ";" +
                "-fx-border-radius: 8px;"
        );

        return row;
    }

    private static HBox createStep(
            String number,
            String text,
            boolean active
    ) {

        Label numberLabel =
                new Label(number);

        numberLabel.setMinSize(
                26,
                26
        );

        numberLabel.setAlignment(
                Pos.CENTER
        );

        numberLabel.setStyle(
                "-fx-background-color: " +
                        (active
                                ? LIME
                                : "#252D25") + ";" +
                "-fx-background-radius: 50%;" +
                "-fx-text-fill: " +
                        (active
                                ? "#0B100B"
                                : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        Label textLabel =
                new Label(text);

        textLabel.setStyle(
                "-fx-text-fill: " +
                        (active
                                ? WHITE
                                : MUTED) + ";" +
                "-fx-font-size: 10px;" +
                "-fx-font-weight: bold;"
        );

        HBox step =
                new HBox(
                        6,
                        numberLabel,
                        textLabel
                );

        step.setAlignment(
                Pos.CENTER_LEFT
        );

        return step;
    }

    private static Region createLine(
            boolean active
    ) {

        Region line =
                new Region();

        line.setPrefWidth(25);
        line.setPrefHeight(2);

        line.setStyle(
                "-fx-background-color: " +
                        (active
                                ? LIME
                                : "#293229") + ";"
        );

        return line;
    }

    private static void styleUploadButton(
            Button button
    ) {

        button.setPrefHeight(36);

        button.setPadding(
                new Insets(
                        0,
                        16,
                        0,
                        16
                )
        );

        button.setStyle(
                "-fx-background-color: #202B20;" +
                "-fx-text-fill: " + LIME + ";" +
                "-fx-border-color: #3B4A3B;" +
                "-fx-border-radius: 7px;" +
                "-fx-background-radius: 7px;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-cursor: hand;"
        );

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: " + LIME + ";" +
                        "-fx-text-fill: #0B100B;" +
                        "-fx-border-color: " + LIME + ";" +
                        "-fx-border-radius: 7px;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: #202B20;" +
                        "-fx-text-fill: " + LIME + ";" +
                        "-fx-border-color: #3B4A3B;" +
                        "-fx-border-radius: 7px;" +
                        "-fx-background-radius: 7px;" +
                        "-fx-font-size: 11px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                )
        );
    }

    private static void stylePrimaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

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

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #D0FF4D;" +
                        "-fx-text-fill: #0B100B;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: " + LIME + ";" +
                        "-fx-text-fill: #0B100B;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-cursor: hand;"
                )
        );
    }

    private static void styleSecondaryButton(
            Button button
    ) {

        button.setPrefHeight(42);

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

        button.setOnMouseEntered(e ->
                button.setStyle(
                        "-fx-background-color: #202B20;" +
                        "-fx-text-fill: " + WHITE + ";" +
                        "-fx-border-color: " + LIME + ";" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                )
        );

        button.setOnMouseExited(e ->
                button.setStyle(
                        "-fx-background-color: #171F17;" +
                        "-fx-text-fill: " + WHITE + ";" +
                        "-fx-border-color: #344034;" +
                        "-fx-border-radius: 8px;" +
                        "-fx-background-radius: 8px;" +
                        "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-cursor: hand;"
                )
        );
    }

    public static Map<String, File> getUploadedDocuments() {

        return data.getUploadedDocuments();
    }
}